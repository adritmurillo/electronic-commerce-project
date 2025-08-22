package com.joaquin.Shop.domain.usecase;

import com.joaquin.Shop.domain.exception.CartNotFoundException;
import com.joaquin.Shop.domain.exception.ProductNotFoundException;
import com.joaquin.Shop.domain.model.Cart;
import com.joaquin.Shop.domain.model.Product;
import com.joaquin.Shop.domain.port.CartPort;
import com.joaquin.Shop.domain.port.ProductPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public class CartManagementUseCaseImpl implements CartManagementUseCase{
    private final CartPort cartPort;
    private final ProductPort productPort;

    public CartManagementUseCaseImpl(CartPort cartPort, ProductPort productPort) {
        this.cartPort = cartPort;
        this.productPort = productPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> findAll(){
        return cartPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Cart findById(UUID id){
        return cartPort.findById(id).orElseThrow(() -> new CartNotFoundException(id));
    }

    @Override
    public Cart create(Cart cart) {
        return cartPort.save(cart);
    }

    @Override
    @Transactional
    public Cart addProduct(UUID cartId, UUID productId) {
        Cart cart = cartPort.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        Product product = productPort.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        cart.getProducts().add(product);
        return cartPort.save(cart);
    }

    @Override
    @Transactional
    public Cart removeProduct(UUID cartId, UUID productId) {
        Cart cart = cartPort.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        Product product = productPort.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        cart.getProducts().remove(product);
        return cartPort.save(cart);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if(cartPort.findById(id).isEmpty()) {
            throw new CartNotFoundException(id);
        }
        cartPort.deleteById(id);
    }


}
