package com.bookpauldeitel.CollectionSetHashTableSortedMapExample;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CollectionSetHashTableSortedMapExample {

    public static void main(String[] args) {
    }

    public static void createdMap_4(Map<String, Integer> map) {
        Set<String> keys = map.keySet();
        TreeSet<String> sortedKey = new TreeSet<String>(keys);
        for (String key : sortedKey) {
            System.out.printf("%-10s%10s%n", key, map.get(key));
        }
    }

    public static void createMap_3(Map<String, Integer> map) {
        Set<String> keys = map.keySet();
        TreeSet<String> sortedKeys = new TreeSet<String>(keys);
        for (String key : sortedKeys) {
            System.out.printf("%-10s%10s%n", key, map.get(key));
        }
    }

    public static void createMap_2(Map<String, Integer> map) {
        Set<String> keys = map.keySet();
        TreeSet<String> sortedKeys = new TreeSet<String>(keys);
        for (String key : sortedKeys) {
            System.out.printf("%-10s%10s%n", key, map.get(key));
        }
    }

    /* Criando Maps */
    private static void createMaps(Map<String, Integer> map) {
        Set<String> keys = map.keySet(); // obtém chaves
        TreeSet<String> sortedKeys = new TreeSet<String>(keys); // classifica as chaves
        for (String key : sortedKeys) {
            System.out.printf("%-10s%10s%n", key, map.get(key));
        }
        System.out.printf("%nsize: %d%nisEmpty: %b%n", map.size(), map.isEmpty());
    }

}
