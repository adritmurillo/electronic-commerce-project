package com.joaquin.Shop.adapter.in.web;

import com.joaquin.Shop.domain.model.Product;
import com.joaquin.Shop.domain.usecase.ProductManagementUseCase;
import com.joaquin.Shop.domain.exception.ProductAlreadyExistsException;
import com.joaquin.Shop.domain.exception.ProductNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")

public class ProductRestAdapter {
    private final ProductManagementUseCase productManagementUseCase;

    public ProductRestAdapter(ProductManagementUseCase productManagementUseCase){
        this.productManagementUseCase = productManagementUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> products = productManagementUseCase.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable UUID id){
        Product product = productManagementUseCase.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product){
        Product createdProduct = productManagementUseCase.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody Product product){
        Product updatedProduct = productManagementUseCase.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        productManagementUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ProductNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<String> handleProductAlreadyExists(ProductAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
}
