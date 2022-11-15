package com.nimko.services;

import com.nimko.model.Store;
import junit.framework.TestCase;

public class StoreGeneratorTest extends TestCase {

    public void testGetStore() {
        StoreGenerator generator = StoreGenerator.getGen();
        assertEquals(Store.class, generator.getStore().getClass());
        assertNotNull(generator.getStore());
        assertEquals("Епіцентр-3", generator.getStore().getName());
    }

}