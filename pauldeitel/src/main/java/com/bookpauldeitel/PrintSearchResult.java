package com.bookpauldeitel;

import java.util.Collections;
import java.util.List;

public class PrintSearchResult {

    /* Function for order list */
    public static void funcPrintSearchResult(List<String> list, String key) {
        int result = 0;
        System.out.printf("%nSearching for: %s%n", key);
        result = Collections.binarySearch(list, key);
        if (result >= 0) {
            System.out.printf("Found at index %d%n", result);
        } else {
            System.out.printf("Not Found (%d)%n", result);
        }
    }

    // PrintSearchResult.printSearchResult(list, "black");

}
