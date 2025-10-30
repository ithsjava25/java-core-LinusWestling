package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

//Ändring för att kunna pusha igen
public class Warehouse {

    private static final Map<String, Warehouse> INSTANCES = new HashMap<>();
    private Map<Category, List<Product>> CACHE = new HashMap<>();
    private final Set<UUID> changedProducts = new HashSet<>();
    private String name = "default";

    private Warehouse(String name){
        this.name = name;
    }

    public static Warehouse getInstance(String name) {
        return INSTANCES.computeIfAbsent(name, k -> new Warehouse(k));
    }

    public static Warehouse getInstance() {
        return getInstance("default");
    }

    public void clearProducts() {
        CACHE.clear();
    }

    public boolean isEmpty() {
        return CACHE.isEmpty();
    }

    public void addProduct(Product product){
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        boolean alreadyExists = CACHE.values().stream()
                .flatMap(List::stream)
                .anyMatch(p -> p.uuid().equals(product.uuid()));
        if (alreadyExists) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        Category category = product.category();
        CACHE.computeIfAbsent(category, k -> new ArrayList<>()).add(product);
    }

    // Return list of all products
    public List<Product> getProducts() {
        return CACHE.values().stream()
                .flatMap(List -> List.stream())
                .toList();
    }

    public Optional<Product> getProductById(UUID id){
        return CACHE.values().stream()
                .flatMap(List::stream)
                .filter(p -> p.uuid().equals(id))
                .findFirst();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories(){
        return CACHE.values().stream()
                .flatMap(List -> List.stream())
                .collect(Collectors.groupingBy(Product::category));
    }


    // Remove object with matching UUID. They are always unique (Product.java row 22)
    public void remove(UUID id) {
        CACHE.values().forEach(list ->
                list.removeIf(product -> product.uuid().equals(id))
        );
        CACHE.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        for (List<Product> products : CACHE.values()) {
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                if (p.uuid().equals(id)) {
                    Product updated = p.cloneWithPrice(newPrice);
                    products.set(i, updated);
                    changedProducts.add(id);
                    return;
                }
            }
        }
        throw new NoSuchElementException("Product not found with id: " + id);
    }


    public List<Perishable> expiredProducts(){
        return CACHE.values().stream()
                .flatMap(List::stream)
                .filter(p -> p instanceof Perishable)
                .map(p -> (Perishable) p)
                .filter(p -> p.isExpired())
                .collect(Collectors.toList());
    }

    public List<Shippable> shippableProducts() {
        return CACHE.values().stream()
                .flatMap(List::stream)
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
