package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class StoreDto {

    @NotNull
    private final String name;
    @NotNull
    private final String address;

    private List<ProductDto> products;

    public StoreDto(String name, String address, List<ProductDto> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "StoreDto{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", products=" + products +
                '}';
    }
}
