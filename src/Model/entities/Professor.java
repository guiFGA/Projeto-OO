package Model.entities;

import enums.CargoProfessor;
import enums.TipoUsuario;

public class Professor extends Usuario {
	private CargoProfessor cargo;
	private String cursoVinculado;

	public Professor(String email, String senha, String nome, String telefone, String matricula, TipoUsuario tipo, String cursoVinculado, CargoProfessor cargo) {
		super(email, senha, nome, telefone, matricula, tipo);
		this.cargo = cargo;
		this.cursoVinculado = cursoVinculado;
	}

	public CargoProfessor getCargo() {
		return cargo;
	}

	public void setCargo(CargoProfessor cargo) {
		this.cargo = cargo;
	}

	public String getCursoVinculado() {
		return cursoVinculado;
	}

	public void setCursoVinculado(String cursoVinculado) {
		this.cursoVinculado = cursoVinculado;
	}
	
	
	
	
}
