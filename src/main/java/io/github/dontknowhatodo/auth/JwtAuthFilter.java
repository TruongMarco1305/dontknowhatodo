package io.github.dontknowhatodo.auth;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.dontknowhatodo.exception.UnauthorizedException;
import io.github.dontknowhatodo.user.UserInfo;
import io.github.dontknowhatodo.user.UserInfoService;
import io.github.dontknowhatodo.user.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
        String jwt = this.extractCredentialFromCookie(request);
        String subject; 

        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        subject = jwtService.extractSubject(jwt);
        if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserInfo userInfo = this.userInfoService.getUserById(subject);
            UserPrincipal userPrincipal = new UserPrincipal(userInfo);
            if (jwtService.validateToken(jwt, userPrincipal)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userPrincipal,
                        null,
                        userPrincipal.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new UnauthorizedException("Invalid Credentials");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractCredentialFromCookie(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}