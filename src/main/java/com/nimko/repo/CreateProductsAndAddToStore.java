package com.nimko.repo;

import com.nimko.model.Product;
import com.nimko.services.ProductGenerator;
import com.nimko.util.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateProductsAndAddToStore {
    private static final Logger log = LoggerFactory.getLogger(CreateProductsAndAddToStore.class);
    private static final int BATCH_SIZE = 1000;
    private final int numStores;
    private final int numProducts;
    private final PreparedStatement statement;
    private final PreparedStatement statementPos;

    private final DataBase db;

    public CreateProductsAndAddToStore(int numStores, int numProducts) {
        this.numStores = numStores;
        this.numProducts = numProducts;
        String sql = "insert into products(id,type,brand,model,price) values(?,?,?,?,?);";
        statement = new DataBase().getPreparedStatement(sql);
        String sqlPos = "insert into pos(store_id, prod_id) values(?,?);";
        db = new DataBase();
        statementPos = db.getPreparedStatement(sqlPos);
    }

    public Stream<Product> createSqlForAddProductsInDb() {
        ProductGenerator generator = new ProductGenerator();
        return  Stream.generate(generator::getProduct).limit(numProducts);
    }

    public CreateProductsAndAddToStore createProducts(){
        try {
            statement.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            log.error("Connection fail!", e);
            throw new MyRuntimeException(e);
        }
        createSqlForAddProductsInDb().forEach(prod -> {
            try {
                statement.setInt(1, prod.getId());
                statement.setString(2, prod.getType());
                statement.setString(3, prod.getBrand());
                statement.setString(4, prod.getModel());
                statement.setInt(5, prod.getPrice());
                statement.addBatch();
                if (prod.getId() % BATCH_SIZE == 0) {
                        statement.executeBatch();
                        statement.getConnection().commit();
                    statement.clearBatch();
                }
            } catch (SQLException e) { log.warn("Insert products fail!",e);}});
        log.info("Products added in the table!");
        return this;
    }

    public void addProductInStores() {
        try {
            statementPos.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            log.error("Connection fail!", e);
            throw new MyRuntimeException(e);
        }
        Random random = new Random();
        IntStream.range(1, numProducts + 1).forEach(x -> {
            try {
                statementPos.setInt(1, 1 + random.nextInt(numStores));
                statementPos.setInt(2, x);
                statementPos.addBatch();
                if (x % BATCH_SIZE == 0) {
                        statementPos.executeBatch();
                        statementPos.getConnection().commit();
                    statementPos.clearBatch();
                }
            } catch (SQLException e) { log.warn("Insert products in stores fail!",e);}});
        log.info("Products added in stores!");
        db.closeConnection();
    }
}
