package io.github.dontknowhatodo.user;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import io.github.dontknowhatodo.user.dto.UserResponseDto;

@Controller
public class UserController {

    private final UserInfoService userInfoService;

    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @QueryMapping
    public UserResponseDto getMe(@AuthenticationPrincipal UserDetails userDetails) {
        UserInfo user = userInfoService.getUserByUsername(userDetails.getUsername());
        return new UserResponseDto(user);
    }
}