package com.joaquin.Shop.adapter.in.web;

import com.joaquin.Shop.domain.exception.CartNotFoundException;
import com.joaquin.Shop.domain.model.Cart;
import com.joaquin.Shop.domain.usecase.CartManagementUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController // Añadido para que Spring reconozca esta clase como controlador
@RequestMapping("/api") // Define el prefijo base para todas las rutas
public class CartRestAdapter {

    private final CartManagementUseCase cartManagementUseCase;

    @Autowired // Opcional, pero buena práctica
    public CartRestAdapter(CartManagementUseCase cartManagementUseCase) {
        this.cartManagementUseCase = cartManagementUseCase;
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> findAll() {
        return ResponseEntity.ok(cartManagementUseCase.findAll());
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(cartManagementUseCase.findById(id));
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> create() {
        Cart cart = cartManagementUseCase.create();
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    @PutMapping("/carts/{id}/add-product/{productId}")
    public ResponseEntity<Cart> addProduct(@PathVariable UUID id, @PathVariable UUID productId) {
        return ResponseEntity.ok(cartManagementUseCase.addProduct(id, productId));
    }

    @PutMapping("/carts/{id}/remove-product/{productId}")
    public ResponseEntity<Cart> removeProduct(@PathVariable UUID id, @PathVariable UUID productId) {
        return ResponseEntity.ok(cartManagementUseCase.removeProduct(id, productId));
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cartManagementUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}