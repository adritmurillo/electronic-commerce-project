package com.joaquin.Shop.domain.port;

import com.joaquin.Shop.domain.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPort {
    List<Product> findAll();
    Optional<Product> findById(UUID id);
    Product save(Product product);
    void deleteById(UUID id);
    boolean existsBySku(String sku);
}