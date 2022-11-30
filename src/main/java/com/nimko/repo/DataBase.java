package com.nimko.repo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.nimko.util.LoadProperties;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DataBase {

    private static final String URL;

    private static final String APP_PROPERTIES = "myApp.properties";

    static { LoadProperties properties = new LoadProperties(APP_PROPERTIES);
       URL = properties.getProperty("db.url");

    }
    private final MongoDatabase base;
    private final MongoClient mongoClient;

    public DataBase() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        mongoClient = MongoClients.create(URL);
        base = mongoClient.getDatabase("pracktic5").withCodecRegistry(pojoCodecRegistry);

    }

    public MongoDatabase getBase() {
        return base;
    }

    public void close(){
        mongoClient.close();
    }

    public static void drop(){
        DataBase dataBase = new DataBase();
        dataBase.getBase().getCollection(CreateStore.STORE).drop();
        dataBase.close();
    }

}
