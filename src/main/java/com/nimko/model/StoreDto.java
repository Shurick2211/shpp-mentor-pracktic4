package com.nimko.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class StoreDto {
    @NotNull
    @Min(value = 1)
    private final int id;
    @NotNull
    private final String name;
    @NotNull
    private final String address;

    public StoreDto(int id, String name, String address) {
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

}
