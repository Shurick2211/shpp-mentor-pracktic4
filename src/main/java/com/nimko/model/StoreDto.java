package com.nimko.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class StoreDto {

    @NotNull
    private String name;
    @NotNull
    private String address;

    private List<ProductDto> products;

    public StoreDto(String name, String address, List<ProductDto> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }

    public StoreDto() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
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
