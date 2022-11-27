package com.nimko.repo;

import com.mongodb.client.MongoCollection;
import com.nimko.model.StoreDto;
import com.nimko.services.StoreGenerator;
import com.nimko.services.ValidateServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreateStore {
    private static final Logger log = LoggerFactory.getLogger(CreateStore.class);
    private final int numStore;

    public CreateStore(int numStore) {
        this.numStore = numStore;
    }

    public List<StoreDto> createStores() {
        StoreGenerator gen = StoreGenerator.getGen();
        ValidateServices<StoreDto> validateServices = new ValidateServices<>();
        return Stream.generate(gen::getStore).filter(validateServices::isValid).limit(numStore).collect(Collectors.toList());
    }

    public void addStoresInDb(){
        DataBase base = new DataBase();
        MongoCollection<StoreDto> store = base.getDatabase().getCollection("store",StoreDto.class);
        store.insertMany(createStores());
        base.close();
    }
}
