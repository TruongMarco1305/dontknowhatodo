package io.github.dontknowhatodo.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dontknowhatodo.auth.dto.LoginDto;
import io.github.dontknowhatodo.auth.dto.SignUpDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${spring.profiles.active}")
    private String environment;

    @PostMapping("/signup")
    public void signup(@RequestBody SignUpDto signUpInfo, HttpServletResponse response) {
        String token = this.authService.signUp(signUpInfo);
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(this.environment.equals("production"))
                .path("/")
                .maxAge(jwtExpiration)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginInfo, HttpServletResponse response) {
        String token = this.authService.login(loginInfo);
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(this.environment.equals("production"))
                .path("/")
                .maxAge(jwtExpiration)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(this.environment.equals("production"))
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
