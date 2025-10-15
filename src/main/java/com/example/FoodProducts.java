package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProducts extends Product{

     //   Implements Perishable and Shippable.

    private final LocalDate expirationDate;

    public FoodProducts(UUID id, String name, Category category, BigDecimal price, BigDecimal weight, LocalDate expirationDate){
        super(id, name, category, price, weight);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String productDetails(){
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
    public int costOfShipping(){
        return weight().multiply(BigDecimal.valueOf(50));
    }
}
