package com.nimko.repo;

import com.nimko.model.StoreDto;
import com.nimko.services.StoreGenerator;
import com.nimko.services.ValidateServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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
        DataBase db = new DataBase();
        Statement statement = db.getStatement();
        createSqlForAdd().forEach(sql -> {
            try {
                statement.addBatch(sql);
            } catch (SQLException e) { log.warn("Insert store fail!",e);}});

        try {
            int[] counts = statement.executeBatch();
            log.info("{} stores was create", Arrays.stream(counts).filter(n -> n >= 0).count());
            statement.close();
        } catch (SQLException e) {
            log.warn("Store creates fail!",e);
        }
        db.closeConnection();
    }
}
