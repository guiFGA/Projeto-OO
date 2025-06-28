package Model.entities;

import enums.TipoUsuario;

public class Servidor extends Usuario {
	private String departamento;
	private String cargo;
	
	
	
	public Servidor(String departamento, String cargo, String matricula, String email, String senha, String nome, String telefone, TipoUsuario tipo) {
		super(email, senha, nome, telefone, matricula, tipo);
		this.departamento = departamento;
		this.cargo = cargo;
		
	}
	
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	
	
	
	
	
	
}
