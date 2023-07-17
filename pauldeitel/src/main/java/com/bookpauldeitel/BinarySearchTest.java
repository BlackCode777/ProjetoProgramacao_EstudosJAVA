package com.bookpauldeitel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearchTest {

    public static void main(String[] args) {

        String[] colors = { "red", "white", "blue", "black", "yellow", "purple", "tan", "pink" };
        // transformando o objeto em lista
        List<String> list = Arrays.asList(colors);

        // ordenando a lista
        Collections.sort(list);

        System.out.printf("Sorted ArrayList: %s%n", list);
        PrintSearchResult.funcPrintSearchResult(list, "black");
        PrintSearchResult.funcPrintSearchResult(list, "red");
        PrintSearchResult.funcPrintSearchResult(list, "pink");
        PrintSearchResult.funcPrintSearchResult(list, "aqua");

    }

}
