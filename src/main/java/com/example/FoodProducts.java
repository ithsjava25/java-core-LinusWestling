package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProducts extends Product{

     //   Implements Perishable and Shippable.

    private final LocalDate expirationDate;
    private final BigDecimal weight;

    public FoodProducts(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight){
        super(id, name, category, price);
        this.expirationDate = expirationDate;
        if(weight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        } else {
            this.weight = weight;
        }
    }

    public String productDetails(){
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
    public BigDecimal costOfShipping(){
        return weight.multiply(BigDecimal.valueOf(50));
    }
}
