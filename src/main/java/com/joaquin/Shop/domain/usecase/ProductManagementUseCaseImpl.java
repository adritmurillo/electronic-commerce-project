package com.joaquin.Shop.domain.usecase;

import com.joaquin.Shop.domain.model.Product;
import com.joaquin.Shop.domain.port.ProductPort;
import com.joaquin.Shop.domain.exception.ProductAlreadyExistsException;
import com.joaquin.Shop.domain.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductManagementUseCaseImpl implements ProductManagementUseCase {
    private final ProductPort productPort;

    public ProductManagementUseCaseImpl(ProductPort productPort) {
        this.productPort = productPort;
    }

    // LECTURAS
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(UUID id) {
        return productPort.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product create(Product product) {
        if (productPort.existsBySku(product.getSku())) {
            throw new ProductAlreadyExistsException(product.getSku());
        }
        return productPort.save(product);
    }

    // ESCRITURAS
    @Override
    @Transactional
    public Product update(UUID id, Product changes) {
        Product existingProduct = productPort.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.setName(changes.getName());
        existingProduct.setPrice(changes.getPrice());
        existingProduct.setDescription(changes.getDescription());
        existingProduct.setSku(changes.getSku());

        return productPort.save(existingProduct);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (productPort.findById(id).isEmpty()) { // Usa isEmpty() en lugar de !isPresent()
            throw new ProductNotFoundException(id);
        }
        productPort.deleteById(id);
    }
}