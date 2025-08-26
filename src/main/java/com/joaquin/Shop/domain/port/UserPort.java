package com.joaquin.Shop.domain.port;

import com.joaquin.Shop.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserPort {
    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findById(UUID id);
}
