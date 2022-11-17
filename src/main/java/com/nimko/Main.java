package com.nimko;

import com.nimko.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Main{
    private static final int NUM_STORES = 10;
    private static final int NUM_PRODS = 3000000;
    private static final String TYPE = "type";

    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main( String[] args ){
        String sysProp = System.getProperty(TYPE);
        CreateTables.createTables();
        new CreateStore(NUM_STORES).addStoresInDb();
        new CreateProductsAndAddToStore(NUM_STORES,NUM_PRODS)
                .createProducts()
                .addProductInStores();

        log.info("It's address store, where maximal number products of entered type: {}",
                 GetOperation.getAddress(sysProp == null ? getTypeFromConsole() : sysProp));
    }

    private static String getTypeFromConsole(){
        Scanner scanner = new Scanner(System.in);
        log.info("Enter type of product?");
        return scanner.nextLine().trim();
    }
}
