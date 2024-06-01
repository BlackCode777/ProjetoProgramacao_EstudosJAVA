package com.estudosobrecarga.sobrecarga.Vetores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListasParte2 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add(0, "********");
        list.add(1, "Anderosn");
        list.add(2, "Carlos");
        list.add(3, "Andre");
        list.add(4, "Damião");
        list.add(5, "Edinaldo");
        list.add(6, "Marcos");
        list.add(7, "Inácio");
        list.add(8, "Angelo");

        List<String> treamParaConjunto = new ArrayList<>();
        treamParaConjunto.add(0, "Cachorro");
        treamParaConjunto.add(1, "Gato");
        treamParaConjunto.add(2, "Papagaio");
        treamParaConjunto.add(3, "Cavalo");
        treamParaConjunto.add(4, "Galinha");
        treamParaConjunto.add(5, "Pato");
        treamParaConjunto.add(6, "Cachorro");

        // reduce() - Reduz os elementos de um stream
        Optional<String> reduce = list.stream().reduce((x, y) -> x + ", " + y);
        reduce.ifPresent(res -> System.out.println(" reduce() >>> " + reduce));

        // count() - Conta os elementos de um stream
        long count = list.stream().count();
        System.out.println(" count() >>> " + count);

        // max() - Retorna o maior elemento de um stream
        Optional<String> max = list.stream().max((x, y) -> x.compareTo(y));
        max.ifPresent(res -> System.out.println(" max() >>> " + max));

        // min() - Retorna o menor elemento de um stream
        Optional<String> min = list.stream().min((x, y) -> x.compareTo(y));
        min.ifPresent(res -> System.out.println(" min() >>> " + min));

        // findFirst() - Retorna o primeiro elemento de um stream
        Optional<String> findFirst = list.stream().findFirst();
        findFirst.ifPresent(res -> System.out.println(" findFirst() >>> " + findFirst));

        // findAny() - Retorna qualquer elemento de um stream
        Optional<String> findAny = list.stream().findAny();
        findAny.ifPresent(res -> System.out.println(" findAny() >>> " + findAny));

        // anyMatch() - Verifica se algum elemento satisfaz um predicado
        boolean anyMatch = list.stream().anyMatch(x -> x.charAt(0) == 'A');
        System.out.println(" anyMatch() >>> " + anyMatch);

        // allMatch() - Verifica se todos os elementos satisfazem um predicado
        boolean allMatch = list.stream().allMatch(x -> x.charAt(0) == 'A');
        System.out.println(" allMatch() >>> " + allMatch);

        // noneMatch() - Verifica se nenhum elemento satisfaz um predicado
        boolean noneMatch = list.stream().noneMatch(x -> x.charAt(0) == 'A');
        System.out.println(" noneMatch() >>> " + noneMatch);

        // sorted() - Ordena os elementos de um stream
        list.stream().sorted().forEach(System.out::println);

        // toArray() - Converte um stream para um vetor
        Object[] toArray = list.stream().toArray();
        for (Object obj : toArray) {
            System.out.println(" toArray() >>> " + obj);
        }

        // sum() - usar a função sum() para Somar os elementos de um stream de números
        double sum = list.stream().map(x -> x.length()).reduce(0, (x, y) -> x + y);
        System.out.println(" sum() >>> " + sum);

        int sum2 = list.stream().map(x -> x.length()).reduce(0, (x, y) -> x + y);
        System.out.println(" sum2() >>> " + sum2);

        int sum3 = list.stream().mapToInt(String::length).sum();
        System.out.println(" sum3() >>> " + sum3);

        // Simplifique este tercho de código "int sum2 = list.stream().map(x ->
        // x.length()).reduce(0, (x, y) -> x + y);"
        int sum4 = list.stream().map(x -> x.length()).reduce(0, Integer::sum);
        System.out.println(" sum3() >>> " + sum4);

        int sum5 = list.stream().mapToInt(String::length).sum();
        System.out.println(" sum5() >>> " + sum5);

        // average() - Calcula a média dos elementos de um stream
        double average = list.stream().map(x -> x.length()).reduce(0, (x, y) -> x + y) / (double) list.size();
        System.out.println(" average() >>> " + average);

        double average1 = list.stream().mapToInt(String::length).average().orElse(0.0);
        System.out.println(" average1() >>> " + average1);

        // limit() - Limita a quantidade de elementos de um stream
        list.stream().limit(3).forEach(System.out::println);

        // skip() - Pula uma quantidade de elementos de um stream
        list.stream().skip(count - 3).forEach(System.out::println);

        // peek() - Executa uma ação para cada elemento do stream
        list.stream().peek(x -> System.out.println(" peek() >>> " + x)).count();

        // distinct() - Elimina elementos duplicados de um stream
        list.stream().distinct().forEach(System.out::println);

        // parallelStream() - Cria um stream paralelo da listas
        list.parallelStream().forEach(System.out::println);

        // contains() - Verifica se um elemento está na lista
        list.listIterator().forEachRemaining(System.out::println);

        // binarySearch() - Busca um elemento na lista
        int binarySearch = list.indexOf("Carlos");
        System.out.println(" binarySearch() >>> " + binarySearch);

        // Todos os métodos da classe List

        // Adiciona um elemento na lista:
        // add() - Adiciona um elemento na lista
        // remove() - Remove um elemento da lista
        // size() - Retorna o tamanho da lista
        // indexOf() - Retorna a posição de um elemento na lista
        // lastIndexOf() - Retorna a última posição de um elemento na lista
        // get() - Retorna um elemento da lista
        // set() - Substitui um elemento da lista por outro
        // removeIf() - Remove elementos de acordo com um predicado
        // forEach() - Executa uma ação para cada elemento da lista
        // sort() - Ordena a lista de acordo com um comparador
        // clear() - Limpa a lista
        // isEmpty() - Verifica se a lista está vazia
        // toArray() - Converte a lista para um vetor
        // addAll() - Adiciona todos os elementos de uma coleção na lista
        // removeAll() - Remove todos os elementos de uma coleção da lista
        // retainAll() - Remove da lista todos os elementos que não estão em uma coleção
        // subList() - Retorna uma sublista da lista
        // replaceAll() - Substitui todos os elementos da lista por outros
        // removeRange() - Remove de uma posição inicial até uma posição final
        // equals() - Compara se duas listas são iguais
        // iterator() - Cria um iterador para a lista
        // of() - Cria uma lista imutável com elementos
        // ofNullable() - Cria uma lista imutável com elementos não nulos
        // min() - Retorna o menor elemento da lista
        // max() - Retorna o maior elemento da lista
        // replaceAll() - Substitui todos os elementos da lista por outros
        // fill() - Preenche a lista com um elemento
        // nCopies() - Cria uma lista com n cópias de um elemento
        // reverse() - Inverte a ordem dos elementos da lista
        // rotate() - Rotaciona os elementos da lista
        // swap() - Troca dois elementos de posição
        // shuffle() - Embaralha os elementos da lista
        // disjoint() - Verifica se duas listas são disjuntas
        // frequency() - Retorna a frequência de um elemento na lista
        // indexOfSubList() - Retorna a posição inicial de uma sublista
        // lastIndexOfSubList() - Retorna a posição final de uma sublista
        // replaceAll() - Substitui todos os elementos da lista por outros

    }
}

// Convertendo um stream para um conjunto com toSet()
// Set<String> setStremConjunto =
// treamParaConjunto.stream().collect(Collectors.toSet());
// for (String s : setStremConjunto) {
// System.out.println(" Conjunto (Set) resultante: " + s);
// }
// setStremConjunto.forEach(System.out::println);

// List<String> result = list.stream().filter(x -> x.charAt(0) ==
// 'D').collect(Collectors.toList());
// for (String res : result) {
// System.out.println(" stream() >>> " + res);
// }

// List<String> resulttoMap = list.stream().filter(x -> x.charAt(0) ==
// 'D').collect(Collectors.toList());
// Convertendo um stream para mapa com toMap()
// Map<String, Integer> map = resulttoMap.stream().collect(Collectors.toMap(x ->
// x, x -> x.length()));
// for (String key : map.keySet()) {
// System.out.println(" Mapa resultante: " + key + " - " + map.get(key));
// }

/*
 * 
 * // toSet() - Converte um stream para um conjunto
 * List<String> result2 = list.stream().filter(x -> x.charAt(0) ==
 * 'C').collect(Collectors.toList());
 * 
 * // Convertendo um stream para um conjunto com toSet()
 * System.out.println("Lista 2: ");
 * for (String res2 : result2) {
 * System.out.println(" stream() >>> " + res2);
 * }
 * 
 */

/*
 * List<String> result = list.stream().filter(x -> x.charAt(0) ==
 * 'D').collect(Collectors.toList());for(
 * String res:result)
 * {
 * System.out.println(" stream() >>> " + res);
 * }
 * 
 * System.out.println("Lista 3: "+result);
 */

// Como remover um elemento da lista
// System.out.println(list.remove(-1));

// Iterando sobre a lista
// System.out.println(list);
// for (String l : list) {
// System.out.println("[" + l + "]");
// }

// Como remover um elemento da lista começe com a letra "A"
// charAt(0) - Pega o primeiro caractere da string
// removeIf() - Remove elementos de acordo com um predicado
// list.removeIf(x -> x.charAt(0) == 'A');
// System.out.println(list);
// for (String lis : list) {
// System.out.println("[" + lis + "]");
// }

// System.out.println("*****************************************");
// List<String> list2 = list.stream().filter(x -> x.charAt(0) ==
// 'C').collect(Collectors.toList());
// System.out.println("Lista 2: ");
// for (String lis2 : list2) {
// System.out.println("[" + lis2 + "]");
// }