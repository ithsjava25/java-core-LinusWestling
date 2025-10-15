package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable{

    // Implements Shippable.

    private int warrantyMonths;
    private BigDecimal weight;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight){
        super(id, name, category, price);
        if(warrantyMonths < 0){
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        } else{
            this.warrantyMonths = warrantyMonths;
        }
        if(weight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        } else {
            this.weight = weight;
        }
    }

    public double weight(){
        return weight.doubleValue();
    }

    public String productDetails(){
        return "Electronics: " + name() + "Warranty: " + warrantyMonths;
    }
    public BigDecimal calculateShippingCost() {
        if (weight() < 5) {
            return BigDecimal.valueOf(79);
        } else {
            return BigDecimal.valueOf(79).add(BigDecimal.valueOf(49));
        }
    }

}
