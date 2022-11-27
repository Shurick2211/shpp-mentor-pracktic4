package com.nimko.services;

import com.nimko.model.ProductDto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateProductsForStore {

    private final int numProducts;

    public CreateProductsForStore(int numProducts) {
        this.numProducts = numProducts;
    }

    public List<ProductDto> createProducts() {
        ProductGenerator generator = new ProductGenerator();
        ValidateServices<ProductDto> validateServices = new ValidateServices<>();
        return  Stream.generate(generator::getProduct).filter(validateServices::isValid)
                .limit(numProducts).collect(Collectors.toList());
    }
}
