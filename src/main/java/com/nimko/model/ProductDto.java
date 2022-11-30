package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    private String type;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @Min(value = 1000)
    private int price;

    public ProductDto(String type, String brand, String model, int price) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public ProductDto() {
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

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
