package io.github.dontknowhatodo.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dontknowhatodo.auth.dto.LoginDto;
import io.github.dontknowhatodo.auth.dto.SignUpDto;
import io.github.dontknowhatodo.config.AppProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppProperties appProperties;

    @PostMapping("/signup")
    public void signup(@RequestBody SignUpDto signUpInfo, HttpServletResponse response) {
        String token = this.authService.signUp(signUpInfo);
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(this.appProperties.isProduction())
                .path("/")
                .maxAge(this.appProperties.getSecurity().getJwt().getExpiration())
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginInfo, HttpServletResponse response) {
        String token = this.authService.login(loginInfo);
        ResponseCookie cookie = ResponseCookie.from("accessToken", token)
                .httpOnly(true)
                .secure(this.appProperties.isProduction())
                .path("/")
                .maxAge(this.appProperties.getSecurity().getJwt().getExpiration())
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    @DeleteMapping("/logout")
    public void logout(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(this.appProperties.isProduction())
                .path("/")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
