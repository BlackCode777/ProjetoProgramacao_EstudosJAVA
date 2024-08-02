package com.bookpauldeitel.PropertiesHashTableSetPropertyGetProperty_16_12;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesHashTableSetPropertyGetProperty {

    public static void main(String[] args) {

        Properties table = new Properties();
        table.setProperty("color", "blue");
        table.setProperty("valor", "100");
        table.setProperty("valor_1", "134");

        listProperty(table);
        saveProperty(table);

        table.clear();

        // Verifica se o valor está na tabela
        if (table.containsKey("color")) {
            System.out.println("Valor: " + table.getProperty("color"));
        } else {
            System.out.println("Valor não encontrado");
        }

    }

    private static void saveProperty(Properties props) {
        try {

            FileOutputStream output = new FileOutputStream("props.dat");
            props.store(output, "Sample Properties"); // Salva as propriedades
            output.close();
            System.out.println("Salvo com sucesso");
            listProperty(props);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadProperties(Properties props) {
        try {

            FileInputStream file = new FileInputStream("props.dat");
            props.load(file); // Carrega as propriedades
            props.clone();
            System.out.println("Carregado com sucesso");
            listProperty(props);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void listProperty(Properties props) {

        Set<Object> keys = props.keySet(); // Obtém as chaves

        for (Object key : keys) {
            System.out.println(key + " = " + props.getProperty((String) key));
        }
        System.out.println();
    }

}
