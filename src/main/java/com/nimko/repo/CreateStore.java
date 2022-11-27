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
    private final int numProdForStore;
    public static final String STORE = "store";
    public CreateStore(int numStore, int numProdForStore) {
        this.numStore = numStore;
        this.numProdForStore = numProdForStore;
    }

    public List<StoreDto> createStores() {
        StoreGenerator gen = StoreGenerator.getGen(numProdForStore);
        ValidateServices<StoreDto> validateServices = new ValidateServices<>();
        return Stream.generate(gen::getStore).filter(validateServices::isValid).limit(numStore).collect(Collectors.toList());
    }

    public void addStoresInDb(){
        List<StoreDto> stores = createStores();
        log.info("{} store was created!", stores.size());
        DataBase base = new DataBase();
        MongoCollection<StoreDto> store = base.getDatabase().getCollection(STORE,StoreDto.class);
        store.insertMany(stores);
        base.close();
    }
}
