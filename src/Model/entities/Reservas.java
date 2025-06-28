package Model.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservas {
	private static int contador = 0;
	private int id;
	private Usuario usuario;
	private EspacoFisico espaco;
	private LocalDate dataInicio;
	private LocalTime horaInicio;
	private LocalDate dataFim;
	private LocalTime horaFim;
	public Reservas(Usuario usuario, EspacoFisico espaco, LocalDate dataInicio, LocalTime horaInicio, LocalDate dataFim,
			LocalTime horaFim) {
		id = contador++;
		this.usuario = usuario;
		this.espaco = espaco;
		this.dataInicio = dataInicio;
		this.horaInicio = horaInicio;
		this.dataFim = dataFim;
		this.horaFim = horaFim;
	}
	
	 public String toCSV() {
	        return id + ", " + usuario.getNome() + ", " + espaco.getNome() + ", " + dataInicio + ", " + horaInicio + ", " + horaFim + ", " + dataFim;
	    }
	
	
}
