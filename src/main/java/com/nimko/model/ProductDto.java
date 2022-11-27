package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ProductDto {

    @NotNull
    private final String type;
    @NotNull
    private final String brand;
    @NotNull
    private final String model;
    @Min(value = 1000)
    private final int price;

    public ProductDto(String type, String brand, String model, int price) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.price = price;
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

}
