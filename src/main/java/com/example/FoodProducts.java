package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProducts extends Product implements Perishable, Shippable{

    private final LocalDate expirationDate;

    public FoodProducts(UUID id, String name, Category category, BigDecimal price, BigDecimal weight, LocalDate expirationDate){
        super(id, name, category, price, weight);
        this.expirationDate = expirationDate;
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    public String productDetails(){
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
    public BigDecimal calculateShippingCost(){
        return weight().multiply(BigDecimal.valueOf(50));
    }
}
