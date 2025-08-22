package com.joaquin.Shop.adapter.in.web;

import com.joaquin.Shop.domain.exception.CartNotFoundException;
import com.joaquin.Shop.domain.model.Cart;
import com.joaquin.Shop.domain.usecase.CartManagementUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class CartRestAdapter {
    private final CartManagementUseCase cartManagementUseCase;

    public CartRestAdapter(CartManagementUseCase cartManagementUseCase){
        this.cartManagementUseCase = cartManagementUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findAll(){
        return ResponseEntity.ok(cartManagementUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> findById(@PathVariable UUID id){
        return ResponseEntity.ok(cartManagementUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cart> create(@RequestBody Cart cart){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartManagementUseCase.create(cart));
    }

    @PutMapping("/{id}/add-product/{productId}")
    public ResponseEntity<Cart> addProduct(@PathVariable UUID id, @PathVariable UUID productId){
        return ResponseEntity.ok(cartManagementUseCase.addProduct(id, productId));
    }

    @PutMapping("/{id}/remove-product/{productId}")
    public ResponseEntity<Cart> removeProduct(@PathVariable UUID id, @PathVariable UUID productId){
        return ResponseEntity.ok(cartManagementUseCase.removeProduct(id, productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        cartManagementUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
