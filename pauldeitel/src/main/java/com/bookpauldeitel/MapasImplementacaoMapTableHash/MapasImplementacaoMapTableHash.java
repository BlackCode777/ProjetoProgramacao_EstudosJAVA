package com.bookpauldeitel.MapasImplementacaoMapTableHash;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class MapasImplementacaoMapTableHash {

    /*
     * Maps - associam chaves a valores
     * 1) - Map pode implementar um mapeamento de um para um
     * 2) - Map pode implementar um mapeamento de um para muitos
     * Maps - contêm chaves e valores
     * Maps - tem 3 classes Hashtable, HashMap e TreeMap
     * 1) - Hashtable / HashMap - armazenam elementos em tabelas de hash
     * 2) - TreeMaps - armazenam elementos em árvores.
     * 
     */

    public static void main(String[] args) {

        Map<String, Integer> myMap = new HashMap<String, Integer>();

        /* Chama método createmap() e displayMap() */
        createMap(myMap);
        displayMap(myMap);

    }

    /* Cria método createMap() */
    private static void createMap(Map<String, Integer> map) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String input = scanner.nextLine();

        /* Tokeniza a entrada */
        String[] tokens = input.split(" ");

        /* Processando o texto da entrada */
        for (String token : tokens) {
            String word = token.toLowerCase();

            /* Se o mapa contiver a palavra */
            if (map.containsKey(word)) {
                int count = map.get(word);
                map.put(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }

    }

    /* Cria método displayMap() */
    private static void displayMap(Map<String, Integer> map) {

        Set<String> keys = map.keySet(); // obtém chaves

        /* Classificando a chave com TreeSet */
        TreeSet<String> sortedKeys = new TreeSet<String>(keys);

        System.out.printf("%nMap contains:%nKey\t\tValue%n");

        /* Obtém as chaves */
        for (String key : sortedKeys) {
            System.out.printf("%-10s%10s%n", key, map.get(key));
        }

        System.out.printf("%nsize: %d%nisEmpty: %b%n", map.size(), map.isEmpty());

    }

}
