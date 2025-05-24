package com.blackcode.concepts_java;

import java.util.List;
import java.util.Objects;

public class UsuarioModel {
	
	private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    
    private List<UserRecord> userRecord;
    
    public UsuarioModel(Long id, String nome, String email, String telefone, String senha, List<UserRecord> userRecord) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.userRecord = userRecord;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<UserRecord> getUserRecord() {
		return userRecord;
	}

	public void setUserRecord(List<UserRecord> userRecord) {
		this.userRecord = userRecord;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioModel other = (UsuarioModel) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}

}
