package com.nimko.repo;

import com.nimko.util.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.sql.Statement;

public final class CreateTables {
    private static final Logger log = LoggerFactory.getLogger(CreateTables.class);

    private CreateTables() {
    }

    public static void createTables(){
        DataBase dataBase = new DataBase();
        CreateTables createTables = new CreateTables();
        Statement statement = dataBase.getStatement();
        try {
            statement.addBatch(createTables.dropTables());
            statement.addBatch(createTables.creatTableProduct());
            statement.addBatch(createTables.creatTableStores());
            statement.addBatch(createTables.creatTablePOS());
            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            log.error("Tables don't creates!");
            throw new MyRuntimeException(e);
        }
        dataBase.closeConnection();
    }

    private  String creatTableStores(){
        return  "CREATE TABLE IF NOT EXISTS public.stores(" +
                "id serial," +
                "store_name character varying(20)," +
                "store_address character varying(20)," +
                "CONSTRAINT store_pkey PRIMARY KEY (id))";
    }

    private  String creatTableProduct(){
        return  "CREATE TABLE IF NOT EXISTS public.products ("+
                "id int,"+
                "type character varying(20),"+
                "brand character varying(20),"+
                "model character varying(20),"+
                "price int,"+
                "CONSTRAINT product_pkey PRIMARY KEY (id))";
    }

    private  String creatTablePOS(){
        return "CREATE TABLE IF NOT EXISTS public.pos(" +
                "store_id int," +
                "prod_id int,"+
                "CONSTRAINT fk_store FOREIGN KEY (store_id) "+
                "REFERENCES public.stores (id) "+
                "ON DELETE CASCADE, "+
                "CONSTRAINT fk_product FOREIGN KEY (prod_id) "+
                "REFERENCES public.products (id) "+
                "ON DELETE CASCADE, "+
                "CONSTRAINT pos_pkey PRIMARY KEY (store_id, prod_id))";
    }

    private String dropTables(){
        return  "DROP TABLE IF EXISTS public.products, " +
                "public.stores, public.pos";
    }
}
