package com.nimko.repo;

import com.mongodb.client.MongoCollection;
import com.nimko.model.ProductDto;
import com.nimko.services.ProductGenerator;
import com.nimko.services.ValidateServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateProductsAndAddToStore {

    private static final Logger log = LoggerFactory.getLogger(CreateProductsAndAddToStore.class);

    private static final int BATCH_SIZE = 1000;
    private final int numStores;
    private final int numProducts;
    public static final String PRODUCT = "product";

    public CreateProductsAndAddToStore(int numStores, int numProducts) {
        this.numStores = numStores;
        this.numProducts = numProducts;

    }

    public List<ProductDto> createProductsInDb() {
        ProductGenerator generator = new ProductGenerator();
        ValidateServices<ProductDto> validateServices = new ValidateServices<>();
        return  Stream.generate(generator::getProduct).filter(validateServices::isValid)
                .limit(numProducts).collect(Collectors.toList());
    }

    public void createProducts(){
        DataBase base = new DataBase();

        MongoCollection<ProductDto> store = base.getDatabase().getCollection(PRODUCT,ProductDto.class);
        store.insertMany(createProductsInDb());
        base.close();
    }


}
