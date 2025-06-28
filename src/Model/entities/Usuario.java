package Model.entities;

import java.util.Objects;

import enums.TipoUsuario;

public abstract class Usuario {
	
	private String email;
	private String senha;
	private String nome;
	private String telefone;
	private String matricula;
	private TipoUsuario tipo;
	
	
	
	
	
	
	public Usuario(String email, String senha, String nome, String telefone, String matricula, TipoUsuario tipo) {
	
	
		this.email = email;
		this.senha = senha;
		this.nome = nome;
		this.telefone = telefone;
		this.matricula = matricula;
		this.tipo = tipo;
	}


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getTelefone() {
		return telefone;
	}



	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getMatricula() {
		return matricula;
	}



	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}



	public TipoUsuario getTipo() {
		return tipo;
	}



	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}



	public void agendarEspaço() {
		
	}

	public void verificarHorario() {
		
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, senha);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(email, other.email) && Objects.equals(senha, other.senha);
	}
	
	
}


