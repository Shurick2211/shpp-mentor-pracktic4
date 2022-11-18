package com.nimko.repo;

import com.nimko.util.LoadProperties;
import com.nimko.util.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DataBase {
    private static final Logger log = LoggerFactory.getLogger(DataBase.class);
    private final Connection connection;

    private static final String URL;
    private static final String APP_PROPERTIES = "myApp.properties";
    static { LoadProperties properties = new LoadProperties(APP_PROPERTIES);
       URL =properties.getProperty("db.url") + "?user="
                + properties.getProperty("db.user") + "&password="
                + properties.getProperty("db.pass");
    }

    public DataBase() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            log.error("Connection Failed!",e);
            throw new MyRuntimeException(e);
        }
    }

    public Statement getStatement(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.warn("Created statement was fail!",e);
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement statement1 = null;
        try {
            statement1 = connection.prepareStatement(sql);
        } catch (SQLException e) {
            log.warn("Created preparedStatement was fail!",e);
        }
        return statement1;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Connection don't close",e);
            throw new MyRuntimeException(e);
        }
    }
}
