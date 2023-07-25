package com.bookpauldeitel.ColecoesSincronizadas_16_13;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class ColecoesSincronizadas {

    /*
     * Coleções sincronizadas - são coleções que são seguras para uso em
     * aplicativos multithreaded
     * 
     * Coleções sincronizadas - são implementadas na classe Collections
     * 
     * Coleções sincronizadas - as coleções na estrutura das coleções são des-
     * sincronizadas por padrão, assim elas podem funcionar eficientemente quando
     * multithreading não for necessário.
     * 
     * Coleções sincronizadas usam wrappers para evitarem resultados inesperados
     * 
     * Coleções sincronizadas - Para evitar potenciais problemas de threading,
     * empacotadores de
     * sincronização são usados para as coleções que podem ser acessadas por
     * múltiplas threads. Um
     * objeto empacotador (wrapper) recebe chamadas de método, adiciona
     * sincronização de thread
     * (para evitar acesso simultâneo à coleção) e delega as chamadas para o objeto
     * de coleção
     * empacotado
     * 
     * EXEMPLOS DE MÉTODOS DE COLEÇÕES SINCRONIZADAS
     * ---------------------------------
     * Cabeçalhos do método public static
     * <T> Collection<T> synchronizedCollection(Collection<T> c)
     * <T> List<T> synchronizedList(List<T> aList)
     * <T> Set<T> synchronizedSet(Set<T> s)
     * <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s)
     * <K, V> Map<K, V> synchronizedMap(Map<K, V> m)
     * <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> m)
     * ---------------------------------
     */

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        List<String> list2 = Collections.synchronizedList(list);
        Set<String> listSet = new HashSet<String>();
        Collection<String> list3 = Collections.synchronizedCollection(list);
        Collection<String> list4 = Collections.synchronizedSet(listSet);
        SortedSet sortedSet = Collections.synchronizedSortedSet(new TreeSet());
        Map<String, String> list5 = Collections.synchronizedMap(new HashMap());
        Map<String, String> list6 = Collections.synchronizedSortedMap(new TreeMap());

        System.out.println("List: " + list);
        System.out.println("synchronizedList: " + list2);
        System.out.println("synchronizedCollection: " + list3);
        System.out.println("synchronizedSet: " + list4);
        System.out.println("synchronizedSortedSet: " + sortedSet);
        System.out.println("synchronizedMap: " + list5);
        System.out.println("synchronizedSortedMap: " + list6);

    }
}
