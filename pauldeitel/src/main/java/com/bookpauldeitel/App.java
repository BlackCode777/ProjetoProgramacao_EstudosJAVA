package com.bookpauldeitel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // String[] varGlobal = { "Black", "Green", "blue" };
        // classificandonaOrdemCrescenteComSortTeste(varGlobal);

        // List<Time> list = new ArrayList<Time>();
        // list.add(new Time(6, 24, 34));
        // list.add(new Time(18, 14, 58));
        // System.out.printf("Unsorted array elements:%n%s%n", list);
        // List<Time> list2 = new ArrayList<Time>();
        // comparandoDoisParametros(list, list2);

        List<Time> list = new ArrayList<Time>();

        list.add(new Time(6, 24, 34));
        list.add(new Time(18, 14, 58));
        list.add(new Time(6, 05, 34));
        System.out.printf("Unsorted array elements:%n%s%n", list);// .iterator()

        // Collections.sort(list, new TimeComparator());

    }

    private static int comparandoDoisParametros(List<Time> list, List<Time> list2) {
        int hourDifference = ((Date) list).getHours() - ((Date) list2).getHours();
        if (hourDifference != 0) {
            System.out.println("Resultado da comparação - hourDifference : " + hourDifference);
            return hourDifference;
        }
        int minuteDifference = ((Date) list).getMinutes() - ((Date) list2).getMinutes();
        if (minuteDifference != 0) {
            System.out.println("Resultado da comparação - minuteDifference : " + minuteDifference);
            return minuteDifference;
        }
        int secondDifference = ((Date) list).getSeconds() - ((Date) list2).getSeconds();
        System.out.println("Resultado da comparação - secondDifference : " + secondDifference);
        return hourDifference;
    }

    // Criando um método de comparação entre dois parametros
    private int comparandoDoisParametros2(List<Date> list, List<Date> list2) {
        int hourDifference = ((Date) list).getHours() - ((Date) list2).getHours();
        if (hourDifference != 0) {
            return hourDifference;
        }
        int minuteDifference = ((Date) list).getMinutes() - ((Date) list2).getMinutes();
        if (minuteDifference != 0) {
            return minuteDifference;
        }
        int secondDifference = ((Date) list).getSeconds() - ((Date) list2).getSeconds();
        return secondDifference;
    }

    private static void classificandonaOrdemCrescenteComSortTeste(String[] varGlobal) {
        String[] varLocal = varGlobal;
        List<String> list = Arrays.asList(varLocal);
        if (list.size() == 0) {
            System.out.println("Lista vazia");
        } else {
            System.out.println("Lista não vazia" + list);
            // exibir os elementos da lista
            for (String string : list) {
                Collections.reverseOrder();
                System.out.println();
            }
        }

    }

    // private static List<String>
    // classificandonaOrdemCrescenteComSortTeste(List<String> param_1) {
    // List<String> varLocal = param_1;
    // // Verificando se a lista esta vazia
    // if (varLocal.size() == 0) {
    // System.out.println("Lista vazia");
    // } else {
    // // Ordenando a lista
    // Collections.sort(varLocal);
    // System.out.println("Lista não vazia");

    // // Invertendo a ordem dos elementos da lista
    // Collections.reverse(varLocal);
    // }
    // return varLocal;
    // }

    private static void classificandonaOrdemCrescenteComSort(List<String> list2) {
        List<String> suit = list2;
        // Verificando se a lista esta vazia
        if (suit.isEmpty()) {
            System.out.println("Lista vazia");
        } else {
            Collections.sort(suit, Collections.reverseOrder());
            System.out.println("Lista não vazia");
        }
        // Invertendo os elementos da lista
        // Collections.reverse(suit);
        System.out.println(suit);
    }

    // cria uma LinkedList, adiciona elementos e converte em array
    public static void criaLinkedListAdicionaConvertePArray(LinkedList<String> links) {
        String[] colors = { "black", "blue", "yellow" };
        links = new LinkedList<String>(Arrays.asList(colors));

        links.addLast("red");
        links.add("pink");
        links.add(3, "green");// para inserir um elemento em uma posição especifica da lista
        links.addFirst("Cyan"); // adiciona um elemento no inicio da lista
        links.add("Magenta");

        System.out.println("LinkedList: ");
        for (String color : links) {
            System.out.println(color);
        }
    }

    private static List<String> colorRemoved_2() {
        // adiciona elementos no array colors a listar
        String[] colors = { "MAGENTA", "RED", "WHITE", "BLUE", "CYAN" };
        List<String> list = new ArrayList<String>();
        for (String color : colors) {
            list.add(color);
            System.out.println(" RESULT <@> " + list);
        }
        return list;
    }

    private static void colorRemoved() {
        // Remove elementos da lista
        String[] removeColors = { "RED", "WHITE", "BLUE" };
        List<String> removeList = new ArrayList<String>();
        for (String color : removeColors) {
            removeList.add(color);
            System.out.println(removeList);
        }
    }

    private static void extracted_2(List<String> list) {
        System.out.println("ArrayList: ");
        for (int count = 0; count < list.size(); count++) {
            System.out.printf("%s ", list.get(count));
        }
    }

    private static void getLinkList() {
        /* 16.6.2 LinkedList */
        String[] colorLink = { "MAGENTA", "RED", "WHITE", "BLUE", "CYAN", "YELLOW", "BLACK", "SILVER", "PURPLE",
                "ORANGE" };
        List<String> linkList = new LinkedList<String>();
        for (String colorList : colorLink) {
            linkList.add(colorList);
            System.out.println("Lista Adicionada <> " + linkList);
        }
    }

}
