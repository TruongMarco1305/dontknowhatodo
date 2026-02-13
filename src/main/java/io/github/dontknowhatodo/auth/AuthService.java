package io.github.dontknowhatodo.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.dontknowhatodo.auth.dto.LoginDto;
import io.github.dontknowhatodo.auth.dto.SignUpDto;
import io.github.dontknowhatodo.exception.BadRequestException;
import io.github.dontknowhatodo.user.UserInfo;
import io.github.dontknowhatodo.user.UserInfoService;

@Service
public class AuthService {

    private final UserInfoService userInfoService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserInfoService userInfoService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userInfoService = userInfoService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public String signUp(SignUpDto signUpInfo) {
        UserInfo newUser = this.userInfoService.addUser(signUpInfo.toEntity());
        return this.jwtService.generateToken(newUser.getId());
    }

    public String login(LoginDto loginInfo) {
        UserInfo user = this.userInfoService.getUserByEmail(loginInfo.getEmail());
        if (!passwordEncoder.matches(loginInfo.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid email or password");
        }
        return this.jwtService.generateToken(user.getId());
    }
}
