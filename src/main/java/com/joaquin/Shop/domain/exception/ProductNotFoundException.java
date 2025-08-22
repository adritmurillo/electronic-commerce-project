package com.joaquin.Shop.domain.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    private final UUID id;

    public ProductNotFoundException(UUID id) {
        super("Product not found with id: " + id);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
