package Model.entities;

public class Equipamento {
	private static int contador = 0;
	private int id;
	private String nome;
	private String modelo;
	
	public Equipamento(String nome, String modelo) {
		
		id = contador ++;
		this.nome = nome;
		this.modelo = modelo;
	}

	public long getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public String toString() {
	    return "Equipamento{id=" + id + ", nome='" + nome + "', modelo='" + modelo + "'}";
	}
	
	
	
}
