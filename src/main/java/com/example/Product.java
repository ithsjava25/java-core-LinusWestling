package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {

    private final UUID id;
    private final String name;
    Category category;
    private BigDecimal price;

    // Public constructor
    public Product(UUID id, String name, Category category, BigDecimal price){
        this.id = id;
        this.name = name;
        this.category = category;
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        } else {
            this.price = price;
        }
    }

    // Get methods
    public UUID uuid(){
        return id;
    }
    public String name(){
        return name;
    }
    public Category category(){
        return category;
    }
    public BigDecimal price(){
        return price;
    }

    // Set method (only price as instructed)
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract String productDetails();

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
