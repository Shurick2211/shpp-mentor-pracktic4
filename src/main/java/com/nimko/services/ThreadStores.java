package com.nimko.services;

import com.nimko.model.StoreDto;
import com.nimko.repo.CreateStore;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadStores implements Runnable {
    private final StopWatch stopWatch = StopWatch.create();
    private static final Logger log = LoggerFactory.getLogger(ThreadStores.class);
    private final int numStores;
    private final int numProds;

    public ThreadStores(int numStores, int numProds) {
        this.numStores = numStores;
        this.numProds = numProds;
    }

    @Override
    public void run() {

        CreateStore storesCreator =  new CreateStore(numStores, numProds/numStores);
        stopWatch.start();
        List<StoreDto> stores = storesCreator.createStores();
        stopWatch.stop();
        log.warn("Time creation all stores: {} ms", stopWatch.getTime());
        log.warn("RPS creation products & stores: {} p/s",
                numProds/ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        stopWatch.reset();
        log.warn("\n_____________________________________________________");

        stopWatch.start();
        storesCreator.addStoresInDb(stores);
        stopWatch.stop();
        log.warn("Time added stores table: {} ms", stopWatch.getTime());
        log.warn("RPS added on DB products & stores: {} p/s",
                (numProds + numStores)/ TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
        stopWatch.reset();
        log.warn("\n____________________________________________________");

    }
}
