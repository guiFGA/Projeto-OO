package Model.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Model.entities.Aluno;
import Model.entities.EspacoFisico;
import Model.entities.Usuario;
import exception.DiasExcedidosException;
import exception.HorarioIndisponivelException;

public class AgendamentoService {
	public void agendarEspaco(Usuario usuario, EspacoFisico espaco, LocalDate dataInicio, LocalTime horaInicio, LocalDate dataFim, LocalTime horaFim)
            throws HorarioIndisponivelException, DiasExcedidosException {

        if (usuario instanceof Aluno && !dataInicio.equals(dataFim)) {
            throw new DiasExcedidosException("Alunos só podem reservar por um único dia.");
        }

        List<LocalDate> dias = dataInicio.datesUntil(dataFim.plusDays(1)).toList();
        List<LocalTime> horas = gerarIntervaloHorarios(horaInicio, horaFim);

        for (LocalDate data : dias) {
            for (LocalTime hora : horas) {
                if (!espaco.estaDisponivel(data, hora)) {
                    throw new HorarioIndisponivelException("Horário já reservado em " + data + " às " + hora);
                }
            }
        }

        for (LocalDate data : dias) {
            for (LocalTime hora : horas) {
                espaco.adicionarHorarioIndisponivel(data, hora);
            }
        }
    }

    private List<LocalTime> gerarIntervaloHorarios(LocalTime inicio, LocalTime fim) {
        List<LocalTime> intervalo = new ArrayList<>();
        LocalTime atual = inicio;
        while (!atual.isAfter(fim)) {
            intervalo.add(atual);
            atual = atual.plusHours(1);
        }
        return intervalo;
    }
}
