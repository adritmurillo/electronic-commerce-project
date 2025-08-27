package com.joaquin.Shop.domain.usecase;

import com.joaquin.Shop.domain.model.Cart;
import java.util.List;
import java.util.UUID;

public interface CartManagementUseCase {
    List<Cart> findAllByUser(UUID userId);
    List<Cart> findAll();
    Cart findById(UUID id);
    Cart create(UUID userId);
    Cart addProduct(UUID cartId, UUID productId);
    Cart removeProduct(UUID cartId, UUID productId);
    void delete(UUID id);
}