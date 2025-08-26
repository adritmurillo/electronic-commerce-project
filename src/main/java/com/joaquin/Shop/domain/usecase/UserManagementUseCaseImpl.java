package com.joaquin.Shop.domain.usecase;

import com.joaquin.Shop.domain.model.User;
import com.joaquin.Shop.domain.port.UserPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManagementUseCaseImpl implements UserManagementUseCase {
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;

    public UserManagementUseCaseImpl(UserPort userPort, PasswordEncoder passwordEncoder) {
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String username, String password) {
        if (username == null || username.trim().isEmpty()){
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty()){
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        User user = new User(username, passwordEncoder.encode(password));
        return userPort.save(user);
    }
}
