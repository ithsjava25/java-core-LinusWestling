package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {

    private final UUID id;
    private final String name;
    Category category;
    private BigDecimal price;

    // Public constructor
    public Product(){
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
    public Category cateogry(){
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

}
