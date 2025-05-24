package com.blackcode.concepts_java;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class StudingOfTheRecordsInJava_EXECUTE {

	public static void main(String[] args) {
		
		// Using class StudingOfTheRecordsInJava_OldFeature
		
		StudingOfTheRecordsInJava_NewFeature rec1 = new StudingOfTheRecordsInJava_NewFeature(
	            1L, "Anderson", 33, "Rua A, 123", "21-9999-0000", "anderson@example.com",
	            "Dev Fullstack", "BlackCodeCorp", "Engenheiro de Software", "Mestrado",
	            "Programação", "Java, Angular", "OCA, AWS", "Tecnologia", "@blackcode77"
	        );
        StudingOfTheRecordsInJava_NewFeature rec2 = new StudingOfTheRecordsInJava_NewFeature(
            2L, "Maria", 28, "Av. B, 456", "21-8888-1111", "maria@example.com",
            "Analista", "TechSolutions", "Analista de Dados", "Bacharelado",
            "Leitura", "SQL, Python", "OCP", "Esportes", "@maria77"
        );
        
        List<StudingOfTheRecordsInJava_NewFeature> recordList = new ArrayList<>();
        recordList.add(rec1);
        recordList.add(rec2);
		
		StudingOfTheRecordsInJava_OldFeature oldJava = new StudingOfTheRecordsInJava_OldFeature(100L, recordList);
		
		// Trabalhando com os dados da classe StudingOfTheRecordsInJava_OldFeature
		System.out.println("ID: " + oldJava.getIdJavaOldFeature());
		System.out.println("Lista de registros:");
		oldJava.getRecodsUsingList().forEach(record -> {
			
			// iterar sobre os campos da classe StudingOfTheRecordsInJava_NewFeature com for
			for (Field field : record.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				try {
					System.out.println(field.getName() + ": " + field.get(record));					
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("===================================");
			
		});
		
		
		
		
		
	}

}
