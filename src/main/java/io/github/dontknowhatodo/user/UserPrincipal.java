package io.github.dontknowhatodo.user;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal implements OAuth2User, UserDetails {

    private final UUID id;
    private final String name;
    private final String username;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public UserPrincipal(UserInfo userInfo) {
        this.name = userInfo.getName();
        this.email = userInfo.getEmail();
        this.id = userInfo.getId();
        this.username = userInfo.getUsername();
        this.password = userInfo.getPassword();
        this.authorities = List.of(userInfo.getRoles().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static UserPrincipal create(UserInfo userInfo, Map<String,Object> attributes) {
        UserPrincipal UserPrincipal = new UserPrincipal(userInfo);
        UserPrincipal.setAttributes(attributes);
        return UserPrincipal;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}