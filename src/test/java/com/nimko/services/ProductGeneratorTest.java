package com.nimko.services;

import com.nimko.model.Product;
import junit.framework.TestCase;

public class ProductGeneratorTest extends TestCase {
    ProductGenerator generator = new ProductGenerator();

    public void testGetProduct() {
        assertEquals(Product.class, generator.getProduct().getClass());
        assertNotNull(generator.getProduct());
    }
}