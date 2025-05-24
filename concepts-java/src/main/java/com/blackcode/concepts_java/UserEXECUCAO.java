package com.blackcode.concepts_java;

import java.lang.reflect.Field;
import java.util.List;

public class UserEXECUCAO {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		List<UserRecord> registros = List.of(
	            new UserRecord(1L, "Ativo", "Administrador"),
	            new UserRecord(2L, "Desativado", "Usuário"),
	            new UserRecord(3L, "Desativado", "Gerente")
	        );
		
		// 2) Monta o model com TODOS os registros
        UsuarioModel usuario = new UsuarioModel(
            1L, "Andre", "andre@andre.com", "123456789", "senha123", registros
        );

     // 3) Itera e imprime cada campo via reflection
        System.out.println("### Imprimindo via Reflection ###");
        for (UserRecord rec : usuario.getUserRecord()) {
            for (Field f : rec.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                System.out.printf("%s: %s%n", f.getName(), f.get(rec));
            }
            System.out.println("---------------------------");
        }
		
     // 4) Exemplo de acesso direto aos componentes do record (sem reflection)
        System.out.println("### Imprimindo via Accessors ###");
        usuario.getUserRecord().forEach(rec -> {
            System.out.println("idPerfil: " + rec.idPerfil());
            System.out.println("status:   " + rec.Status());
            System.out.println("perfil:   " + rec.perfil());
            System.out.println("===========================");
        });
       

	}

}
