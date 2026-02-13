package io.github.dontknowhatodo.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.dontknowhatodo.exception.ConflictException;
import io.github.dontknowhatodo.exception.InternalServerErrorException;
import io.github.dontknowhatodo.exception.NotFoundException;

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
            throw new UsernameNotFoundException("User not found");
        }

        UserInfoDetails userDetails = new UserInfoDetails(userInfo.get());
        return new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public UserInfo addUser(UserInfo userInfo) {
        try {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            return repository.save(userInfo);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("User with the same email or username already exists");
        } catch (Exception e) {
            throw new InternalServerErrorException("Something went wrong. Please try again later");
        }
    }

    public UserInfo getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Invalid email or password"));
    }

    public UserInfo getUserById(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException("Invalid user"));
    }

    public UserInfo getUserByUsername(String username){
        return repository.findByUsername(username).
            orElseThrow(() -> new NotFoundException("Invalid user"));
    }
}
