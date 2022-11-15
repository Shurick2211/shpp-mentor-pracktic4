package com.nimko.repo;

import com.nimko.services.ProductGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DbOperationProduct {
    private static final Logger log = LoggerFactory.getLogger(DbOperationProduct.class);
    private final int numStores;
    private final int numProducts;
    private final Statement statement = new DataBase("myApp.properties").getStatement();

    public DbOperationProduct(int numStores, int numProducts) {
        this.numStores = numStores;
        this.numProducts = numProducts;
    }

    public Stream<String> createSqlForAddProductsInDb() {
        ProductGenerator generator = new ProductGenerator();
        return  Stream.generate(generator::getProduct).limit(numProducts)
                .map(prod -> "insert into products(id,type,brand,model,price)" +
                        "values(" + prod.getId() + ",'" + prod.getType() + "','" + prod.getBrand()
                        + "','" + prod.getModel() + "',"
                        + prod.getPrice() + ");");
    }

    public DbOperationProduct createProducts(){
        createSqlForAddProductsInDb().forEach(sql -> {
            try {
                statement.addBatch(sql);
            } catch (SQLException e) { log.warn("Insert products fail!",e);}});
        try {
            statement.executeBatch();
        } catch (SQLException e) {
            log.warn("Products creates fail!",e);
        }
        log.info("Products was create!");
        return this;
    }

    public void addProductInStores() {
        Random random = new Random();
        IntStream.range(1, numProducts + 1).mapToObj(pN -> "insert into pos(store_id,prod_id)" +
                "values(" + (1 + random.nextInt(numStores)) + "," + pN + ");")
                .forEach(sql -> {
                    try {
                        statement.addBatch(sql);
                    } catch (SQLException e) { log.warn("Insert products in stores fail!",e);}});
        try {
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            log.warn("Products in stores creates fail!",e);
        }
        log.info("{} products added in stores!", numProducts);
    }
}
