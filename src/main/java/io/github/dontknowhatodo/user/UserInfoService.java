package io.github.dontknowhatodo.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByUsername(username);
        
        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        
        UserInfoDetails userDetails = new UserInfoDetails(userInfo.get());
        return new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public void addUser(UserInfo userInfo) {
        try {
            userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
            repository.save(userInfo);
        } catch (Exception error) {
            throw new RuntimeException("Error saving user: " + error.getMessage());
        }
    }
}