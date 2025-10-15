package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {

    private Map<String, List<Product>> CACHE = new HashMap<>();
    private final Set<UUID> changedProducts = new HashSet<>();
    private final String name;

    private Warehouse(String name){
        this.name = name;
    }

    public void addProduct(String name, Product product){
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        } else {
            CACHE.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
        }
    }

    public List<Product> getProducts(String name) {
        return CACHE.getOrDefault(name, List.of());
    }

    // Returnera produkten ifall det finns ett matchande ID
    public List<UUID> getProductById(UUID id){
        return CACHE.values().stream()
                .flatMap(List::stream)
                .map(p -> p.uuid())
                .filter(uuid -> uuid.equals(id))
                .toList();

    }

    public void updateProductPrice(UUID id, BigDecimal price){
        Product product = CACHE.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.uuid().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));

        product.setPrice(price); // kräver att Product har setPrice(...)
        changedProducts.add(id);
    }

    public List<Product> expiredProducts(){
        return CACHE.values().stream()
                .flatMap(List::stream)
                .filter(p -> p instanceof FoodProducts)
                .map(p -> (FoodProducts) p)
                .filter(p -> p.isExpired())
                .collect(Collectors.toList());
    }

    public List<Product> shippableProducts(){
        return CACHE.values().stream()
                .flatMap(List -> List.stream())
                .toList();
    }
}
