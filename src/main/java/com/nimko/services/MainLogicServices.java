package com.nimko.services;

import com.nimko.model.StoreDto;
import com.nimko.repo.CreateStore;
import com.nimko.repo.DataBase;
import com.nimko.repo.GetOperation;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MainLogicServices {
    private static final int NUM_STORES = 10;
    private static final int NUM_PRODS = 1_000_000;
    private static final String TYPE = "type";
    private final StopWatch stopWatch = StopWatch.create();
    private static final Logger log = LoggerFactory.getLogger(MainLogicServices.class);
    public MainLogicServices() {
        CreateStore storesCreator =  new CreateStore(NUM_STORES, NUM_PRODS/NUM_STORES);

        stopWatch.start();
        List<StoreDto> stores = storesCreator.createStores();
        stopWatch.stop();
        log.warn("Time creation all stores: {} ms", stopWatch.getTime());
        log.warn("RPS creation products & stores: {} p/s",
                NUM_PRODS/ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        stopWatch.reset();
        log.warn("\n_____________________________________________________");

        stopWatch.start();
        storesCreator.addStoresInDb(stores);
        stopWatch.stop();
        log.warn("Time added stores table: {} ms", stopWatch.getTime());
        log.warn("RPS added on DB products & stores: {} p/s",
               (NUM_PRODS + NUM_STORES)/ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        stopWatch.reset();
        log.warn("\n____________________________________________________");

        printResult();
        log.warn("\n____________________________________________________");
        DataBase.drop();
    }

    public void printResult(){
        String type = System.getProperty(TYPE) == null ? getTypeFromConsole() : System.getProperty(TYPE);
        stopWatch.start();
        log.warn("It's address store, where maximal number products of {} type: {}", type,
                GetOperation.getAddress(type));
        stopWatch.stop();
        log.warn("Time for searched address: {}", stopWatch.getTime());
    }

    private String getTypeFromConsole(){
        Scanner scanner = new Scanner(System.in);
        log.warn("Enter type of product?");
        return scanner.nextLine().trim();
    }
}
