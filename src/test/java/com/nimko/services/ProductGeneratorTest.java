package com.nimko.services;

import com.nimko.model.ProductDto;
import junit.framework.TestCase;

public class ProductGeneratorTest extends TestCase {
    ProductGenerator generator = new ProductGenerator();

    public void testGetProduct() {
        assertEquals(ProductDto.class, generator.getProduct().getClass());
        assertNotNull(generator.getProduct());
    }
}