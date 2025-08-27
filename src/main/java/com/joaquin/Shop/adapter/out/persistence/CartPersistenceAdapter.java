package com.joaquin.Shop.adapter.out.persistence;

import com.joaquin.Shop.domain.model.Cart;
import com.joaquin.Shop.domain.port.CartPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CartPersistenceAdapter implements CartPort {
    private final CartRepository repository;

    public CartPersistenceAdapter(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Cart> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return repository.save(cart);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Cart> findByUserId(UUID userId) {
        return repository.findByUserId(userId);
    }
}