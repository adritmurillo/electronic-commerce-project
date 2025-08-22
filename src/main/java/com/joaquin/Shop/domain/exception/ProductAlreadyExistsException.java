package com.joaquin.Shop.domain.exception;

public class ProductAlreadyExistsException extends RuntimeException {
    private final String sku;

    public ProductAlreadyExistsException(String sku) {
        super("There is already a product with sku: " + sku);
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }
}
