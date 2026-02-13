package io.github.dontknowhatodo.auth.dto;

import io.github.dontknowhatodo.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter 
@Builder
@NoArgsConstructor  
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String email;
    private String password;

    public void loadFromEntity(UserInfo entity) {
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}