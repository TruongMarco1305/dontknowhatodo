package io.github.dontknowhatodo.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dontknowhatodo.auth.dto.SignUpDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void addNewUser(@RequestBody SignUpDto signUpInfo) {
        this.authService.signUp(signUpInfo);
    }
}