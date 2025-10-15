package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Category {

    private static final Map<String, Category> CACHE = new HashMap<>();

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }
        String normalized = capitalize(trimmed);

        // Flyweight: return cached instance if exists
        return CACHE.computeIfAbsent(normalized, Category::new);
    }

    private static String capitalize(String input) {
        if (input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }

    // Optional: equals and hashCode for value object semantics
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
