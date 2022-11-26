package com.nimko.services;

import com.nimko.model.StoreDto;

import java.util.List;
import java.util.Random;

public class StoreGenerator {

    private static final List<String> STREETS = List.of("вул.Плиткова", "вул.Квіткова",
            "перев.Авеню", "вул.Космічна", "перев.Героїв", "вул.Авіаторів",
            "вул.Залізнична", "шоссе.Придорожне", "просп.Річковий", "вул.Виноградна");
    private int n;

    private StoreGenerator() {
        n = 1;
    }

    public static StoreGenerator getGen(){
        return new StoreGenerator();
    }

    public StoreDto getStore() {
        return new StoreDto(n, name(), address());
    }

    private  String address() {
        Random r = new Random();
        final int max = 200;
        return STREETS.get(r.nextInt(STREETS.size() - 1)) + "-"
                + (1 + r.nextInt(max));
    }

    private String name() {
        return "Епіцентр-" + (n++);
    }


}
