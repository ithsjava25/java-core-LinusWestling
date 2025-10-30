package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class FoodProduct extends Product implements Perishable, Shippable{

    private final LocalDate expirationDate;
    private BigDecimal weight;

    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight){
        super(id, name, category, price);

        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date and weight cannot be null.");
        }
        if(weight.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }

        this.expirationDate = expirationDate;
        this.weight = weight;

    }

    public double weight(){
        return weight.doubleValue();
    }

    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }
    @Override
    public Product cloneWithPrice(BigDecimal newPrice) {
        return new FoodProduct(this.uuid(), this.name(), this.category(), newPrice, this.expirationDate, this.weight);
    }

    public String productDetails(){
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
    public BigDecimal calculateShippingCost(){
        return BigDecimal.valueOf(weight()).multiply(BigDecimal.valueOf(50));

    }
}
