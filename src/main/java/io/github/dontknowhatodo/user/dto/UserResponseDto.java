package io.github.dontknowhatodo.user.dto;

import io.github.dontknowhatodo.user.UserInfo;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String id;
    private final String username;
    private final String email;
    private final String name;
    private final String roles;
    private final String bio;
    private final String company;
    private final String imageUrl;

    public UserResponseDto(UserInfo entity) {
        this.id = entity.getId().toString();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.name = entity.getName();
        // Cut the phrase "ROLE_" out of the result for better UX
        this.roles = entity.getRoles().substring(5);
        this.bio = entity.getBio();
        this.company = entity.getCompany();
        this.imageUrl = entity.getImageUrl();
    }
}