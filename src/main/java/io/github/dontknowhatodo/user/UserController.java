package io.github.dontknowhatodo.user;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import io.github.dontknowhatodo.annonation.User;
import io.github.dontknowhatodo.user.dto.UserResponseDto;

@Controller
public class UserController {

    private final UserInfoService userInfoService;

    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @QueryMapping
    public UserResponseDto getMe(@User UserInfoDetails userInfoDetails) {
        UserInfo user = userInfoService.getUserById(userInfoDetails.getId().toString());
        return new UserResponseDto(user);
    }
}