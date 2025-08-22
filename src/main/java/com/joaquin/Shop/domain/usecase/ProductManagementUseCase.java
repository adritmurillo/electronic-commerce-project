package com.joaquin.Shop.domain.usecase;

import com.joaquin.Shop.domain.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductManagementUseCase {
    List<Product> findAll();
    Product findById(UUID id);
    Product create(Product product);
    Product update(UUID id, Product product);
    void delete(UUID id);
}
