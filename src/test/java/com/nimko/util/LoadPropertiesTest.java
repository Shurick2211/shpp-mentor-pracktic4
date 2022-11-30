package com.nimko.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadPropertiesTest {

    @Test
    public void getProperty() {
        LoadProperties properties = new LoadProperties("myApp.properties");
        assertEquals("mongodb://localhost:27017", properties.getProperty("db.local"));
    }


}