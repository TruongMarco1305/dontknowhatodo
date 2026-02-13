package io.github.dontknowhatodo.auth;

import org.springframework.stereotype.Service;

import io.github.dontknowhatodo.auth.dto.SignUpDto;
import io.github.dontknowhatodo.user.UserInfo;
import io.github.dontknowhatodo.user.UserInfoService;

@Service
public class AuthService {
    private final UserInfoService userInfoService;
    // private final JwtService jwtService;
    // private final AuthenticationManager authenticationManager;

    public AuthService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public void signUp(SignUpDto signUpInfo) {
        UserInfo userInfo = new UserInfo();
        userInfo.loadFromSignupDto(signUpInfo);
        this.userInfoService.addUser(userInfo);
    }
}