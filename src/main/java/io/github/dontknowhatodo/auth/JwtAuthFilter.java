package io.github.dontknowhatodo.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.dontknowhatodo.exception.UnauthorizedException;
import io.github.dontknowhatodo.user.UserInfo;
import io.github.dontknowhatodo.user.UserInfoDetails;
import io.github.dontknowhatodo.user.UserInfoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserInfoService userInfoService;

    @Override 
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String jwt;
        String subject;

        jwt = authHeader.substring(7);
        subject = jwtService.extractSubject(jwt);

        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserInfo userInfo = this.userInfoService.getUserById(subject);
            UserInfoDetails userDetails = new UserInfoDetails(userInfo);
            if (jwtService.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new UnauthorizedException("Invalid Credentials");
            }
        }
        filterChain.doFilter(request, response);
    }
}