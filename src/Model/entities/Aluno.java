package Model.entities;

import enums.TipoUsuario;

public class Aluno extends Usuario {
	private String curso;
	private String semeste;
	private String ingresso;
	
	public Aluno(String email, String senha, String nome, String telefone, String matricula, TipoUsuario tipo, String curso, String semeste, String ingresso) {
		super(email, senha, nome, telefone, matricula, tipo);
		this.curso = curso;
		this.semeste = semeste;
		this.ingresso = ingresso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getSemeste() {
		return semeste;
	}

	public void setSemeste(String semeste) {
		this.semeste = semeste;
	}

	public String getIngresso() {
		return ingresso;
	}

	public void setIngresso(String ingresso) {
		this.ingresso = ingresso;
	}
	
	
	
}
