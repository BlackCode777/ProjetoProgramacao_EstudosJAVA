package com.bookpauldeitel.CreateFunctionWithReceiveVeryParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class CreateFunctionWithReceiveVeryParams {

    public static void main(String[] args) {

        List<String> string = new ArrayList<String>();
        SortedSet<String> sortedMap = new TreeSet<String>();
        TreeSet<String> treeSet = new TreeSet<String>();
        int inteiro = 0;

        createFunctionWithReceiveVeryParams(string, sortedMap, treeSet, inteiro);

    }

    /*
     * 1) - string é uma colleção lista de strings
     * 
     * 2) - inteiro - se o numero maior que 0 e menor que 70,
     * - então o usuário digita uma string e adiciona no sortedMap
     * - e adiciona o sortedmap na coleção string
     * - exibe a coleção de listas de string
     * 
     * 3) - se o numero maior que 71 e menor que 100,
     * - então o usuário digita uma string e adiciona no treeSet
     * - e adiciona o treeSet na coleção string
     * - exibe a coleção de listas de string
     */

    public static void createFunctionWithReceiveVeryParams(List<String> string, SortedSet<String> sortedMap,
            TreeSet<String> treeSet, int inteiro) {

        System.out.println("Número aleatório: " + inteiro);
        inteiro = (int) (Math.random() * 100);

        if (inteiro >= 0 && inteiro <= 70) {
            System.out.println("Digite uma string: ");
            Scanner scanner = new Scanner(System.in);
            /* converte scanner em SortedMap e Adiciona o sortedMap a lista string */
            string = new ArrayList<String>(Arrays.asList(scanner.nextLine()));
            // string += 1;
            sortedMap.add(string.get(0));
            System.out.println("SortedMap: " + sortedMap);
        } else {
            if (inteiro >= 71 && inteiro <= 100) {
                System.out.println("Digite uma string: ");
                Scanner scanner = new Scanner(System.in);
                /* converte scanner em TreeSet e Adiciona o treeSet a lista string */
                string = new ArrayList<String>(Arrays.asList(scanner.nextLine()));
                treeSet.add(string.get(0));
                System.out.println("TreeSet: " + treeSet);
            }
        }
        System.out.println("Lista de strings: " + string);
    }

}

/*
 * 
 * 
 * if (inteiro >= 0 && inteiro <= 70) {
 * System.out.println("Digite uma string: ");
 * Scanner scanner = new Scanner(System.in);
 * 
 * string = new ArrayList<String>(scanner.nextLine().hashCode());
 * sortedMap.add(string.get(0));
 * System.out.println("SortedMap: " + sortedMap);
 * 
 * } else {
 * 
 * if (inteiro >= 71 && inteiro <= 100) {
 * System.out.println("Digite uma string: ");
 * Scanner scanner = new Scanner(System.in);
 * 
 * treeSet = new TreeSet<String>(Arrays.asList(scanner.nextLine().split(" ")));
 * treeSet.add(string.get(0));
 * System.out.println("TreeSet: " + treeSet);
 * }
 * 
 * }
 * 
 * 
 */