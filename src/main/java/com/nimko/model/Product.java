package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Product {
    @NotNull
    @Min(value = 1)
    private final int id;
    @NotNull
    private final String type;
    @NotNull
    private final String brand;
    @NotNull
    private final String model;
    @Min(value = 1000)
    private final int price;

    public Product(int id, String type, String brand, String model, int price) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getPrice() == product.getPrice() && getType().equals(product.getType()) && getBrand().equals(product.getBrand()) && model.equals(product.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getBrand(), model, getPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id="+id+
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
