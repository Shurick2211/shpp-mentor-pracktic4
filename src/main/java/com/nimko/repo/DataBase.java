package com.nimko.repo;

import com.nimko.util.LoadProperties;
import com.nimko.util.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private static final Logger log = LoggerFactory.getLogger(DataBase.class);
    private final Connection connection;
    private final Statement statement;

    public DataBase(String propertiesFile) {
        LoadProperties properties = new LoadProperties(propertiesFile);
        String url =properties.getProperty("db.url") + "?user="
                + properties.getProperty("db.user") + "&password="
                + properties.getProperty("db.pass");
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.error("Connection Failed!",e);
            throw new MyRuntimeException(e);
        }
        log.info("Connection to database was open!");
    }

    public Statement getStatement(){
        return this.statement;
    }


    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Connection don't close",e);
            throw new MyRuntimeException(e);
        }
        log.info("Connection to database was close!");
    }

}
