package com.nimko.repo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.nimko.model.StoreDto;
import com.nimko.util.LoadProperties;
import com.nimko.util.MyRuntimeException;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

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
    private final MongoDatabase database;
    private final MongoClient mongoClient;

    public DataBase() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("pracktic5").withCodecRegistry(pojoCodecRegistry);

    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close(){
        mongoClient.close();
    }

}
