package com.joaquin.Shop.adapter.out.persistence;

import com.joaquin.Shop.domain.model.Product;
import com.joaquin.Shop.domain.port.ProductPort;
import com.joaquin.Shop.adapter.out.persistence.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistenceAdapter implements ProductPort {
    private final ProductRepository repository;

    public ProductPersistenceAdapter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id); // O lanza excepción si prefieres
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public boolean existsBySku(String sku) {
        return repository.existsBySku(sku);
    }
}