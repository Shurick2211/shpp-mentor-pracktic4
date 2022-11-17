package com.nimko.services;

import com.nimko.model.Product;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class ProductGenerator {
    private int id;
    private static final int DELTA_ALPHABET = 26;
    private static final int START_ALPHABET = 'a';
    private static final Random RANDOM = new Random();

    public ProductGenerator() {
        id = 1;
    }

    public Product getProduct(){
        final int min = 1000;
        final int max = 20000;
        return new Product(id++, TYPES.get(RANDOM.nextInt(TYPES.size() - 1)),
                BRANDS.get(RANDOM.nextInt(BRANDS.size() - 1)),
                getModel(), min + RANDOM.nextInt(max));
    }

    private String getModel() {
        final int minSize = 2;
        final int maxSize = 10;
        final int x = 9;
        return Stream.generate(String::new).map(s -> RANDOM.nextBoolean()
                    ? String.valueOf((char) (START_ALPHABET + RANDOM.nextInt(DELTA_ALPHABET))).toUpperCase()
                    : String.valueOf(RANDOM.nextInt(x)))
                .limit((minSize + RANDOM.nextInt(maxSize))).reduce(String::concat).orElse("");
    }

    private static final List<String> TYPES = List.of("телевізор","холодильник","ноутбук",
            "смартфон", "планшет", "праска", "електрочайник", "фен", "кондиціонер", "годинник",
            "монітор", "колонки", "пилосмок", "пральна машина", "лампа", "принтер", "блендер",
            "міксер", "мультіварка", "обігрівач");
    private static final List<String> BRANDS = List.of("Sony", "Lg", "Panasonic", "Xiaomi"
            , "Philips", "Samsung", "Apple", "Asus", "Lenovo", "Toshiba", "Pioneer", "Sven", "BBK");
}
