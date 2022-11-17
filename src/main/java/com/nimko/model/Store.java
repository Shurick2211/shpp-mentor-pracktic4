package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class Store {
    @NotNull
    @Min(value = 1)
    private final int id;
    @NotNull
    private final String name;
    @NotNull
    private final String address;

    public Store(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
