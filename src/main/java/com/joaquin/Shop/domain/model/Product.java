package com.joaquin.Shop.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = "sku")})
public class Product {
    @Id
    @GeneratedValue(generator = "uuid2") // Usa generador personalizado
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator") // Hibernate genera UUID
    @Column(columnDefinition = "BINARY(16)") // Mapea a BINARY(16) en MySQL
    private UUID id;

    @NotNull(message = "SKU cannot be null")
    @NotEmpty(message = "SKU cannot be empty")
    @Column(nullable = false, unique = true)
    private String sku;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Column(nullable = false)
    private String name;

    private String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Column(nullable = true)
    private BigDecimal price;

    @Min(value = 0, message = "Stock cannot be negative")
    @Column(nullable = false)
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