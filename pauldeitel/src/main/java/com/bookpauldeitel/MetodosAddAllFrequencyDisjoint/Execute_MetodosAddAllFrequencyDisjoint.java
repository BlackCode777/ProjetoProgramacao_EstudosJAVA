package com.bookpauldeitel.MetodosAddAllFrequencyDisjoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Execute_MetodosAddAllFrequencyDisjoint {

    public static void main(String[] args) {

        String[] colors = { "red", "white", "yellow", "blue" };
        List<String> list1 = Arrays.asList(colors);

        // Abrindo espaço para inserir mais elementos
        ArrayList<String> list2 = new ArrayList<String>(list1);
        list2.add("black");
        list2.add("red");
        list2.add("green");

        // Imprimindo as listas
        System.out.printf("Before addAll, list2 contains:");

        for (String s : list2) {
            System.out.printf(" %s ", s);
        }
        Collections.addAll(list2, colors);

        System.out.printf("%nAfter addAll, list2 contains:");

        // Exibe os elementos da lista2
        for (String s : list2) {
            System.out.printf(" %s ", s);
        }

        int frequency = Collections.frequency(list2, "red");
        System.out.printf("%nFrequency of red in list2: %d%n", frequency);

        boolean disjoin = Collections.disjoint(list1, list2);
        System.out.printf("list1 and list2 %s elements in common%n", (disjoin ? "do not have" : "have"));

    }

}
