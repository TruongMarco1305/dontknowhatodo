package io.github.dontknowhatodo.auth.dto;

import io.github.dontknowhatodo.user.UserInfo;
import lombok.Getter;

@Getter
public class SignUpDto {
    private String username;
    private String email;
    private String password;
    private String name;

    public void loadFromEntity(UserInfo entity) {
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.name = entity.getName();
    }

    public UserInfo toEntity() {
        return UserInfo.builder()
            .username(this.username)
            .email(this.email)
            .password(this.password)
            .name(this.name)
            .roles("ROLE_USER")
            .build();
    }
}