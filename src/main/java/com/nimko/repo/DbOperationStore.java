package com.nimko.repo;

import com.nimko.model.Store;
import com.nimko.services.StoreGenerator;
import com.nimko.services.ValidateServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

public class DbOperationStore {
    private static final Logger log = LoggerFactory.getLogger(DbOperationStore.class);
    private final int numStore;

    public DbOperationStore(int numStore) {
        this.numStore = numStore;
    }

    public Stream<String> createSqlForAdd() {
        StoreGenerator gen = StoreGenerator.getGen();
        ValidateServices<Store> validateServices = new ValidateServices<>();
        return Stream.generate(gen::getStore).filter(validateServices::isValid).limit(numStore)
                .map(store -> "insert into stores(id,store_name,store_address)" +
                        "values("+store.getId() + ",'" + store.getName() + "','"
                        + store.getAddress() + "');");
    }

    public void addStoresInDb(){
        Statement statement = new DataBase("myApp.properties").getStatement();
        createSqlForAdd().forEach(sql -> {
            try {
                statement.addBatch(sql);
            } catch (SQLException e) { log.warn("Insert store fail!",e);}});
        try {
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            log.warn("Store creates fail!",e);
        }
        log.info("{} stores was create", numStore);
    }
}
