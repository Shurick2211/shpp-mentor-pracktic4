package com.nimko.services;

import com.nimko.repo.CreateProductsAndAddToStore;
import com.nimko.repo.CreateStore;
import com.nimko.repo.GetOperation;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainLogicServices {
    private static final int NUM_STORES = 10;
    private static final int NUM_PRODS = 3_000_000;
    private static final String TYPE = "type";
    private final StopWatch stopWatch = StopWatch.createStarted();
    private static final Logger log = LoggerFactory.getLogger(MainLogicServices.class);
    public MainLogicServices() {

        stopWatch.stop();
        log.info("Time created table: {} ms", stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();

        new CreateStore(NUM_STORES).addStoresInDb();
        stopWatch.stop();
        log.info("Time created stores table: {} ms", stopWatch.getTime());
        stopWatch.reset();

        CreateProductsAndAddToStore cP = new CreateProductsAndAddToStore(NUM_STORES,NUM_PRODS);
        stopWatch.start();

        cP.createProducts();

        stopWatch.stop();
        log.info("Time created products table: {} ms", stopWatch.getTime());
        log.info("RPS products: {} ms", NUM_PRODS/TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        stopWatch.reset();
        stopWatch.start();

        stopWatch.stop();
        log.info("Time created POS table: {} ms", stopWatch.getTime());
        log.info("RPS: {} ms", NUM_PRODS/ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        stopWatch.reset();
    }

    public void printResult(){
        String sysProp = System.getProperty(TYPE);
        stopWatch.start();
        log.info("It's address store, where maximal number products of entered type: {}",
                GetOperation.getAddress(sysProp == null ? getTypeFromConsole() : sysProp));
        stopWatch.stop();
        log.info("Time for searched address: {}", stopWatch.getTime());
    }

    private String getTypeFromConsole(){
        Scanner scanner = new Scanner(System.in);
        log.info("Enter type of product?");
        return scanner.nextLine().trim();
    }
}
