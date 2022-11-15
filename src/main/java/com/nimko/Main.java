package com.nimko;

import com.nimko.repo.DbOperationProduct;
import com.nimko.repo.DbOperationStore;
import com.nimko.repo.Tables;

/**
 * Hello world!
 *
 */
public class Main{
    private static final int NUM_STORES = 10;
    private static final int NUM_PRODS = 2000;

    public static void main( String[] args ){
        Tables.createTables();
        new DbOperationStore(NUM_STORES).addStoresInDb();
        new DbOperationProduct(NUM_STORES,NUM_PRODS)
                .createProducts()
                .addProductInStores();
    }
}
