package com.nimko.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Receiver extends Thread{

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);
    private final PreparedStatement statement;

    public Receiver(PreparedStatement statement) {
        this.statement = statement;
    }

    @Override
    public void run() {
        try {
            statement.executeBatch();
            statement.getConnection().commit();
        } catch (SQLException e) {
            log.warn("Receiver fail!",e);
        }
        log.info("Receiver done!");
    }
}
