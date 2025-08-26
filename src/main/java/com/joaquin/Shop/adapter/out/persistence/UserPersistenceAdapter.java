package com.joaquin.Shop.adapter.out.persistence;

import com.joaquin.Shop.domain.model.User;
import com.joaquin.Shop.domain.port.UserPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserPort {
    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

}
