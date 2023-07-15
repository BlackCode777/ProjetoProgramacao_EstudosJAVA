package com.bookpauldeitel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

    }

    // cria uma LinkedList, adiciona elementos e converte em array
    public static void criaLinkedListAdicionaConvertePArray() {
        String[] colors = { "black", "blue", "yellow" };
        LinkedList<String> links = new LinkedList<String>(Arrays.asList(colors));

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
