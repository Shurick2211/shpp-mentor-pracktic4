package com.nimko.repo;

import com.nimko.model.StoreDto;
import com.nimko.services.StoreGenerator;
import com.nimko.services.ValidateServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class CreateStore {
    private static final Logger log = LoggerFactory.getLogger(CreateStore.class);
    private final int numStore;

    public CreateStore(int numStore) {
        this.numStore = numStore;
    }

    public Stream<String> createSqlForAdd() {
        StoreGenerator gen = StoreGenerator.getGen();
        ValidateServices<StoreDto> validateServices = new ValidateServices<>();
        return Stream.generate(gen::getStore).filter(validateServices::isValid).limit(numStore)
                .map(store -> "insert into stores(id,store_name,store_address)" +
                        "values("+store.getId() + ",'" + store.getName() + "','"
                        + store.getAddress() + "');");
    }

    public void addStoresInDb(){

    }
}
