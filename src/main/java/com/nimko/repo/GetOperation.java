package com.nimko.repo;

import com.mongodb.client.MongoCollection;
import com.nimko.model.StoreDto;
import java.util.HashMap;
import java.util.Map;

import static com.nimko.repo.CreateStore.STORE;

public class GetOperation {

    private final DataBase base;
    private final MongoCollection<StoreDto> store;
    private GetOperation() {
        base = new DataBase();
        store = base.getDatabase().getCollection(STORE,StoreDto.class);
    }

    public static String getAddress(String type) {
        GetOperation operation = new GetOperation();
        Map<Long,String> stores = new HashMap<>();
        operation.store.find().forEach(s ->
            stores.put(s.getProducts().stream().filter(p -> p.getType().equals(type)).count(),s.getAddress()));
        operation.base.close();
        return stores.get(stores.keySet().stream().max(Long::compareTo).orElse(0L));
    }
}
