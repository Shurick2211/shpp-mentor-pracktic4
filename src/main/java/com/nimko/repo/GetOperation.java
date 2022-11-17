package com.nimko.repo;

import com.nimko.util.MyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetOperation {
    private static final Logger log = LoggerFactory.getLogger(GetOperation.class);
    private static final String SQL_ADDRESS = "select address from " +
            "(select s.store_address as address, count(*) " +
            "as number from products pr inner join pos p on pr.id=p.prod_id " +
            "inner join stores s on s.id=p.store_id where type=?" +
            "group by s.id order by number desc) an limit 1;";

    private GetOperation() {
    }

    public static String getAddress(String type) {
        PreparedStatement statement = new DataBase("myApp.properties")
                .getPreparedStatement(SQL_ADDRESS);
        try {
            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() ? resultSet.getString(1): "ERROR";
        } catch (SQLException e) {
            log.error("Error in getAddress!", e);
            throw new MyRuntimeException(e);
        }
    }
}
