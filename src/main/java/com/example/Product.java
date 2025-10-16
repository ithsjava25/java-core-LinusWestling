package com.example;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class Product {

    private final UUID id;
    private final String name;
    private Category category;
    private BigDecimal price;
    private static final Set<UUID> USED_IDS = new HashSet<>();

    // Public constructor
    public Product(UUID id, String name, Category category, BigDecimal price){

        if (id == null || name == null || category == null || price == null) {
            throw new IllegalArgumentException("Id, name, category, and price cannot be null.");
        }
        if (USED_IDS.contains(id)) {
            throw new IllegalArgumentException("Duplicate UUID: " + id + ", use UUID.randomUUID() while constructing a new product");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;

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
