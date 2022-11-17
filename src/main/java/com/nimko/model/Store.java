package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

public class Store {
    @NotNull
    @Min(value = 1)
    private int id;
    @NotNull
    private String name;
    @NotNull
    private  String address;
    private List<Product> products;

    public Store() {
    }

    public Store(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return getName().equals(store.getName()) && getAddress().equals(store.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAddress());
    }

    @Override
    public String toString() {
        return "Store{" +
                "id="+id+
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
