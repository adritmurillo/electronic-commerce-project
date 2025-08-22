package com.joaquin.Shop.domain.port;

import com.joaquin.Shop.domain.model.Cart;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartPort {
    List<Cart> findAll();
    Optional<Cart> findById(UUID id);
    Cart save(Cart cart);
    void deleteById(UUID id);
    void delete(Cart cart);
}
