package com.joaquin.Shop.domain.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2") // Usa generador personalizado
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator") // Hibernate genera UUID
    @Column(columnDefinition = "BINARY(16)") // Mapea a BINARY(16) en MySQL
    private UUID id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;
    private BigDecimal price;
    private int stock;

    public Product() {}

    public Product(String sku, String name, String description, BigDecimal price, int stock) {
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    // Getters y setters
    public UUID getId() { return id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}