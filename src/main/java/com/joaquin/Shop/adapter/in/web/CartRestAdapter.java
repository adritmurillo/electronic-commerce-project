//antes del ultimo cambio

package com.joaquin.Shop.adapter.in.web;

import com.joaquin.Shop.domain.exception.CartNotFoundException;
import com.joaquin.Shop.domain.model.Cart;
import com.joaquin.Shop.domain.model.User;
import com.joaquin.Shop.domain.usecase.CartManagementUseCase;
import com.joaquin.Shop.domain.port.UserPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CartRestAdapter {

    private final CartManagementUseCase cartManagementUseCase;
    private final UserPort userPort;

    @Autowired
    public CartRestAdapter(CartManagementUseCase cartManagementUseCase, UserPort userPort) {
        this.cartManagementUseCase = cartManagementUseCase;
        this.userPort = userPort;
    }

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> findAll(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UUID userId = mapUsernameToUserId(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(cartManagementUseCase.findAllByUser(userId));
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> findById(@PathVariable UUID id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UUID userId = mapUsernameToUserId(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart cart = cartManagementUseCase.findById(id);
        if (cart != null && !userId.equals(cart.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/carts")
    public ResponseEntity<Cart> create(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UUID userId = mapUsernameToUserId(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart createdCart = cartManagementUseCase.create(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }

    @PutMapping("/carts/{id}/add-product/{productId}")
    public ResponseEntity<Cart> addProduct(@PathVariable UUID id, @PathVariable UUID productId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UUID userId = mapUsernameToUserId(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart cart = cartManagementUseCase.findById(id);
        if (cart != null && !userId.equals(cart.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(cartManagementUseCase.addProduct(id, productId));
    }

    @PutMapping("/carts/{id}/remove-product/{productId}")
    public ResponseEntity<Cart> removeProduct(@PathVariable UUID id, @PathVariable UUID productId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UUID userId = mapUsernameToUserId(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart cart = cartManagementUseCase.findById(id);
        if (cart != null && !userId.equals(cart.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(cartManagementUseCase.removeProduct(id, productId));
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String username = authentication.getName();
        UUID userId = mapUsernameToUserId(username);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Cart cart = cartManagementUseCase.findById(id);
        if (cart != null && !userId.equals(cart.getUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        cartManagementUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<String> handleCartNotFoundException(CartNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    private UUID mapUsernameToUserId(String username) {
        return userPort.findByUsername(username)
                .map(User::getId)
                .orElse(null);
    }
}