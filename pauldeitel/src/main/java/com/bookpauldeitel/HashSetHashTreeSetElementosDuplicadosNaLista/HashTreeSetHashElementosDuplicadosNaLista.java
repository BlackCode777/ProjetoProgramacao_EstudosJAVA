package com.bookpauldeitel.HashSetHashTreeSetElementosDuplicadosNaLista;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class HashTreeSetHashElementosDuplicadosNaLista {

    public static void main(String[] args) {

        // TreeSet utilizado para remover valores duplicados do array de strings
        String[] colors = { "red", "white", "blue", "green", "gray", "orange", "tan", "white", "cyan", "peach", "gray",
                "orange" };
        SortedSet<String> tree = new TreeSet<String>(Arrays.asList(colors));
        System.out.printf("SortedSet: %s%n", tree);

        System.out.print("headSet (\"orange\"): ");
        printSet(tree.headSet("orange"));

        System.out.print("tailSet (\"orange\"): ");
        printSet(tree.tailSet("orange"));

        System.out.printf("first: %s%n", tree.first());
        System.out.printf("last: %s%n", tree.last());

    }

    /* envia SortedSet para a saída usando a instrução for aprimorada */
    private static void printSet(SortedSet<String> headSet) {
        for (String str : headSet) {
            System.out.printf("%s ", str);
        }
        System.out.println();
    }

}
