package com.bookpauldeitel;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // adiciona elementos no array colors a listar
        String[] colors = { "MAGENTA", "RED", "WHITE", "BLUE", "CYAN" };
        List<String> list = new ArrayList<String>();
        for (String color : colors) {
            list.add(color);
            System.out.println(" RESULT <@> " + list);
        }

        // Remove elementos da lista
        String[] removeColors = { "RED", "WHITE", "BLUE" };
        List<String> removeList = new ArrayList<String>();
        for (String color : removeColors) {
            removeList.add(color);
            System.out.println(removeList);
        }

        System.out.println("ArrayList: ");
        for (int count = 0; count < list.size(); count++) {
            System.out.printf("%s ", list.get(count));
        }
        // removeColors(list, removeList);

    }

    // private static void removeColors(Collection<String> collection1,
    // Collection<String> removeList) {
    // // cria um objeto iterator
    // Iterator iterator = (Iterator) collection1.iterator();
    // // itera enquanto a coleção tem itens
    // while (((java.util.Iterator<String>) iterator).hasNext()) {
    // // se removeList contém o item atual
    // if (removeList.contains(iterator.next())) {
    // ((java.util.Iterator<String>) iterator).remove();
    // }
    // }
    // }

}
