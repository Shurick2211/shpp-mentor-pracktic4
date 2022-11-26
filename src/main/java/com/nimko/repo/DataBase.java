package com.nimko.repo;

import com.nimko.util.LoadProperties;
import com.nimko.util.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DataBase {
    private static final Logger log = LoggerFactory.getLogger(DataBase.class);

    private static final String URL;
    private static final String USER;
    private static final String PASS;
    private static final String APP_PROPERTIES = "myApp.properties";

    static { LoadProperties properties = new LoadProperties(APP_PROPERTIES);
       URL = properties.getProperty("db.url");
       USER = properties.getProperty("db.user");
       PASS = properties.getProperty("db.pass");
    }

    public DataBase() {

    }






}
