package io.github.dontknowhatodo.auth.dto;

import io.github.dontknowhatodo.user.UserInfo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpDto {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Name is required")
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