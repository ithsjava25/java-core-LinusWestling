package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product{

    /*
    Implements Shippable.
Fields: int warrantyMonths, BigDecimal weight (kg).
Validation: negative warranty -> IllegalArgumentException("Warranty months cannot be negative.").
productDetails() should look like: "Electronics: Laptop, Warranty: 24 months".
Shipping rule: base 79, add 49 if weight > 5.0 kg.
     */

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
        this.weight = weight;
    }
}
