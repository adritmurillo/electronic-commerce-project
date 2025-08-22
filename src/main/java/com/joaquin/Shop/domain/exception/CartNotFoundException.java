package com.joaquin.Shop.domain.exception;

import java.util.UUID;

public class CartNotFoundException extends RuntimeException{
    private final UUID id;
    public CartNotFoundException(UUID id){
        super("Cart not found with id: " + id);
        this.id = id;
    }


    public UUID getId(){return id;}
}
