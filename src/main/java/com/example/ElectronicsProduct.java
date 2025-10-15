package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public class ElectronicsProduct extends Product implements  Shippable{

    // Implements Shippable.

    private int warrantyMonths;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, BigDecimal weight, int warrantyMonths){
        super(id, name, category, price, weight);
        if(warrantyMonths < 0){
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        } else{
            this.warrantyMonths = warrantyMonths;
        }
    }

        /*
        public int getWarrantyMonths() {
            return warrantyMonths;
        }
         */

    public String productDetails(){
        return "Electronics: " + name() + "Warranty: " + warrantyMonths;
    }
    public BigDecimal calculateShippingCost() {
        if (weight().compareTo(BigDecimal.valueOf(5)) < 0) {
            return BigDecimal.valueOf(79);
        } else {
            return BigDecimal.valueOf(79 + 49);
        }
    }

}
