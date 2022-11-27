package com.nimko.services;

import com.nimko.model.ProductDto;
import com.nimko.model.StoreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class StoreGenerator {
    private static final Logger log = LoggerFactory.getLogger(StoreGenerator.class);
    private static final List<String> STREETS = List.of("вул.Плиткова", "вул.Квіткова",
            "перев.Авеню", "вул.Космічна", "перев.Героїв", "вул.Авіаторів",
            "вул.Залізнична", "шоссе.Придорожне", "просп.Річковий", "вул.Виноградна");
    private int n;
    private final int numberOfProducts;

    private StoreGenerator(int numberOfProducts) {
        n = 1;
        this.numberOfProducts = numberOfProducts;
    }

    public static StoreGenerator getGen(int num){
        return new StoreGenerator(num);
    }

    public StoreDto getStore() {
        CreateProductsForStore productsForStore = new CreateProductsForStore(numberOfProducts);
        List<ProductDto> prods = productsForStore.createProducts();
        log.debug("{} products in the store-{}",prods.size(),n);
        return new StoreDto(name(), address(), prods);
    }

    private  String address() {
        Random r = new Random();
        final int max = 200;
        return STREETS.get(r.nextInt(STREETS.size() - 1)) + "-"
                + (1 + r.nextInt(max));
    }

    private String name() {
        return "Епіцентр-" + (n++);
    }


}
