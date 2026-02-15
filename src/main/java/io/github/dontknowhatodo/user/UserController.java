package io.github.dontknowhatodo.user;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import io.github.dontknowhatodo.annonation.User;
import io.github.dontknowhatodo.user.dto.EditProfileRequestDto;
import io.github.dontknowhatodo.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserInfoService userInfoService;

    @QueryMapping
    public UserResponseDto getMe(@User UserPrincipal userPrincipal) {
        UserInfo user = userInfoService.getUserById(userPrincipal.getId().toString());
        return new UserResponseDto(user);
    }

    @MutationMapping
    public UserResponseDto updateProfile(@User UserPrincipal userPrincipal, @Argument EditProfileRequestDto editProfileRequestDto) {
        UserInfo user = userInfoService.updateProfile(userPrincipal.getId().toString(), editProfileRequestDto);
        return new UserResponseDto(user);
    }
} 