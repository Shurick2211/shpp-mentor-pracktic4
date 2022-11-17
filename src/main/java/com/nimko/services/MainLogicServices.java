package com.nimko.services;

import com.nimko.repo.CreateProductsAndAddToStore;
import com.nimko.repo.CreateStore;
import com.nimko.repo.CreateTables;
import com.nimko.repo.GetOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainLogicServices {
    private static final int NUM_STORES = 10;
    private static final int NUM_PRODS = 3000000;
    private static final String TYPE = "type";

    private static final Logger log = LoggerFactory.getLogger(MainLogicServices.class);
    public MainLogicServices() {
        CreateTables.createTables();
        new CreateStore(NUM_STORES).addStoresInDb();
        CreateProductsAndAddToStore cP = new CreateProductsAndAddToStore(NUM_STORES,NUM_PRODS);
        cP.createProducts();
        cP.addProductInStores();
    }

    public void printResult(){
        String sysProp = System.getProperty(TYPE);
        log.info("It's address store, where maximal number products of entered type: {}",
                GetOperation.getAddress(sysProp == null ? getTypeFromConsole() : sysProp));
    }

    private String getTypeFromConsole(){
        Scanner scanner = new Scanner(System.in);
        log.info("Enter type of product?");
        return scanner.nextLine().trim();
    }
}
