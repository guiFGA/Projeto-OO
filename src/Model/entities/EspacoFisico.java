package Model.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.TipoEspaco;

public class EspacoFisico {
	private static int contador = 0;
	private int id;
	private String nome;
	private int capacidade;
	private String localizacao;
	private List<Equipamento> equipamentos;
	private TipoEspaco tipo;
	private Map<LocalDate, List <LocalTime>> horariosIndisponiveis;
	
	public EspacoFisico(String nome, int capacidade, String localizacao, TipoEspaco tipo) {
		this.equipamentos = new ArrayList<>();
		this.horariosIndisponiveis = new HashMap<>();
		id = contador++;
		this.nome = nome;
		this.capacidade = capacidade;
		this.localizacao = localizacao;
		this.tipo = tipo;
	}
	
	
	
	public long getId() {
		return id;
	}



	public String getNome() {
		return nome;
	}



	public int getCapacidade() {
		return capacidade;
	}



	public String getLocalizacao() {
		return localizacao;
	}



	public void listarEquipamentos() {
	    if (equipamentos.isEmpty()) {
	        System.out.println("Nenhum equipamento cadastrado para este espaço.");
	    } else {
	        System.out.println("Equipamentos do espaço " + nome + ":");
	        for (Equipamento e : equipamentos) {
	            System.out.println("- " + e.getNome() + " (Modelo: " + e.getModelo() + ")");
	        }
	    }
	}


	public TipoEspaco getTipo() {
		return tipo;
	}



	public Map<LocalDate, List<LocalTime>> getHorariosIndisponiveis() {
		return horariosIndisponiveis;
	}
	
	public void adicionarHorarioIndisponivel(LocalDate data, LocalTime horario) {
		horariosIndisponiveis.computeIfAbsent(data, k -> new ArrayList<>()).add(horario);
	}

	
	public boolean estaDisponivel(LocalDate data, LocalTime horario) {
        List<LocalTime> indisponiveis = horariosIndisponiveis.getOrDefault(data, new ArrayList<>());
        return !indisponiveis.contains(horario);
    }
	
	public void adicionarEquipamento(Equipamento e) {
		equipamentos.add(e);
	}
	
	
}
