package io.github.dontknowhatodo.user;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.dontknowhatodo.auth.dto.SignUpDto;
import io.github.dontknowhatodo.config.BaseEntityConfig;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserInfo extends BaseEntityConfig {
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String roles;

    public void loadFromSignupDto(SignUpDto dto) {
        this.username = dto.getUsername();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.roles = "ROLE_USER";
    }
}