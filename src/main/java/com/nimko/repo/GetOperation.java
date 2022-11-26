package com.nimko.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetOperation {
    private static final Logger log = LoggerFactory.getLogger(GetOperation.class);
    private static final String SQL_ADDRESS =
            "select s.store_address as address, count(*) " +
            "as number from products pr inner join pos p on pr.id=p.prod_id " +
            "inner join stores s on s.id=p.store_id where type=?" +
            "group by s.id order by number desc limit 1;";

    private GetOperation() {
    }

    public static String getAddress(String type) {
    return "";
    }
}
