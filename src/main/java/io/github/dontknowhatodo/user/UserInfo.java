package io.github.dontknowhatodo.user;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.dontknowhatodo.config.BaseEntityConfig;
import io.github.dontknowhatodo.user.dto.EditProfileRequestDto;
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

    @Column(nullable = false)
    private String name;

    @Column()
    private String bio;

    @Column(columnDefinition="TEXT")
    private String imageUrl;

    @Column()
    private String company;

    public void update(EditProfileRequestDto data) {
        data.getBio().ifPresent(this::setBio);
        data.getCompany().ifPresent(this::setCompany);
        data.getName().ifPresent(this::setName);
        this.setUpdatedAt(LocalDateTime.now());
    }
}
