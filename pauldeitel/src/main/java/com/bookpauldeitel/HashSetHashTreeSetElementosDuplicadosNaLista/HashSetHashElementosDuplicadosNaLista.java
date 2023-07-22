package com.bookpauldeitel.HashSetHashTreeSetElementosDuplicadosNaLista;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetHashElementosDuplicadosNaLista {

    public static void main(String[] args) {

        // HashSet utilizado para remover valores duplicados do array de strings
        String[] colors = { "red", "white", "blue", "green", "gray", "orange", "tan", "white", "cyan", "peach", "gray",
                "orange" };
        List<String> list = Arrays.asList(colors);
        System.out.printf("List: %s%n", list);

        printNonDuplicates(list);

    }

    /* Retira os elementos duplicados da lista */
    private static void printNonDuplicates(Collection<String> list) {
        Set<String> set = new HashSet<String>(list);
        System.out.printf("%nNonduplicates are: ");

        for (String value : set) {
            System.out.printf("%s ", value);
        }
        System.out.println();
    }

}
