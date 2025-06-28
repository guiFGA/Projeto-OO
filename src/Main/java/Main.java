package Main.java;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.entities.Aluno;
import Model.entities.EspacoFisico;
import Model.entities.Professor;
import Model.entities.Reservas;
import Model.entities.Servidor;
import Model.entities.Usuario;
import Model.services.AgendamentoService;
import enums.CargoProfessor;
import enums.TipoEspaco;
import enums.TipoUsuario;
import exception.DiasExcedidosException;
import exception.HorarioIndisponivelException;

public class Main {
	
	  static List<Usuario> usuarios = new ArrayList<>();
      static List<EspacoFisico> espacosFisicos = new ArrayList<>();
      static List<Reservas> reservas = new ArrayList<>();
	
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

     

        int opcao;
        Usuario usuarioLogado = null;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Cadastrar Espaço Físico");
            System.out.println("3 - Listar Usuários");
            System.out.println("4 - Listar Espaços Físicos");
            System.out.println("5 - Fazer Login");
            System.out.println("6 - Agendar Espaço Físico");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarUsuario(sc, usuarios);
                    break;
                case 2:
                    cadastrarEspacoFisico(sc, espacosFisicos);
                    break;
                case 3:
                    listarUsuarios(sc, usuarios);
                    break;
                case 4:
                    listarEspacos(sc, espacosFisicos);
                    break;
                case 5:
                    usuarioLogado = fazerLogin(sc, usuarios);
                    if (usuarioLogado != null) {
                        System.out.println("Login realizado com sucesso. Bem-vindo(a), " + usuarioLogado.getNome() + "!");
                        System.out.println("Pressione ENTER para continuar...");
                        sc.nextLine();
                    } else {
                        System.out.println("Falha no login. Email ou senha incorretos.");
                        System.out.println("Pressione ENTER para continuar...");
                    }
                    break;
                    
                case 6:
                    if (usuarioLogado == null) {
                        System.out.println("Faça login antes de agendar um espaço.");
                        System.out.println("Pressione ENTER para continuar...");
                        sc.nextLine();
                    } else {
                        try {
                            agendarEspaco(sc, usuarioLogado, espacosFisicos, reservas);
                        } catch (Exception e) {
                            System.out.println("Erro ao agendar: " + e.getMessage());
                            System.out.println("Pressione ENTER para continuar...");
                            sc.nextLine();  
                        }
                    }
                    break;
                
                case 0:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
        
        //Gerar arquivo .CSV
        System.out.println("Arquivo será salvo em: " + new java.io.File("reservas.csv").getAbsolutePath());
        try(PrintWriter writer = new PrintWriter("reservas.csv")){
        	writer.println("Id, Usuario, Espaco, Data de Inicio, Horario de Inicio, Horario de Fim, Data de Fim");
        	for(Reservas r : reservas) {
        		writer.println(r.toCSV());
        	}
        	System.out.println("Um relatório contendo as reservas feitas foi gerado!");
        	
        }catch(Exception e){
        	System.out.println("Erro ao salvar agendamentos: " + e.getMessage());
        }

        sc.close();
    }

    // Métodos auxiliares

    public static void cadastrarUsuario(Scanner sc, List<Usuario> usuarios) {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        System.out.print("Tipo de usuário (1-Aluno, 2-Professor, 3-Servidor): ");
        int tipoUsuario = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome completo: ");
        String nome = sc.nextLine();

        System.out.print("Email institucional: ");
        String email = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Senha: ");
        String senha = sc.nextLine();

        switch (tipoUsuario) {
            case 1:
                System.out.print("Curso: ");
                String curso = sc.nextLine();

                System.out.print("Número de matrícula: ");
                String matricula = sc.nextLine();

                System.out.print("Semestre (ex: 3): ");
                String semestre = sc.nextLine();
                
                System.out.print("ano de Ingresso ");
                String ingresso = sc.nextLine();
                

                usuarios.add(new Aluno(email, senha, nome, telefone, matricula, TipoUsuario.ALUNO, curso, semestre, ingresso ));
                break;
            case 2:
                System.out.print("Matrícula institucional: ");
                String matriculaProf = sc.nextLine();

                System.out.print("Curso que ministra: ");
                String cursoMinistra = sc.nextLine();

                System.out.print("Cargo acadêmico: (1-Auxiliar, 2-Assistente, 3-Adjunto, 4-Associado, 5-Titular)");
                int cargo = sc.nextInt();
                
                switch(cargo) {
                case 1:
                	usuarios.add(new Professor(email, senha, nome, telefone, matriculaProf, TipoUsuario.PROFESSOR, cursoMinistra, CargoProfessor.AUXILIAR));
                	break;
                case 2:
                	usuarios.add(new Professor(email, senha, nome, telefone, matriculaProf, TipoUsuario.PROFESSOR, cursoMinistra, CargoProfessor.ASSISTENTE));
                	break;
                case 3:
                	usuarios.add(new Professor(email, senha, nome, telefone, matriculaProf, TipoUsuario.PROFESSOR, cursoMinistra, CargoProfessor.ADJUNTO));
                	break;
                case 4:
                	usuarios.add(new Professor(email, senha, nome, telefone, matriculaProf, TipoUsuario.PROFESSOR, cursoMinistra, CargoProfessor.ASSOCIADO));
                	break;
                case 5:
                	usuarios.add(new Professor(email, senha, nome, telefone, matriculaProf, TipoUsuario.PROFESSOR, cursoMinistra, CargoProfessor.TITULAR));
                	break;
                	
                default:
                	System.out.println("Entrada inválida.");
                }
                
                
                
                
                
                break;
            case 3:
                System.out.print("Matrícula institucional: ");
                String matriculaAdm = sc.nextLine();

                System.out.print("Função/cargo: ");
                String funcao = sc.nextLine();

                System.out.print("Departamento: ");
                String departamento = sc.nextLine();

                usuarios.add(new Servidor(departamento, funcao, matriculaAdm, email, senha, nome, telefone, TipoUsuario.SERVIDOR_ADMINISTRATIVO));
                break;
            default:
                System.out.println("Tipo de usuário inválido.");
        }
    }

    public static void cadastrarEspacoFisico(Scanner sc, List<EspacoFisico> espacosFisicos) {
        System.out.println("\n=== CADASTRO DE ESPAÇO FÍSICO ===");

        System.out.print("Nome do espaço: ");
        String nome = sc.nextLine();

        System.out.print("Capacidade: ");
        int capacidade = sc.nextInt();
        sc.nextLine();

        System.out.print("Localização: ");
        String localizacao = sc.nextLine();

        System.out.print("Tipo (1-SALA_AULA, 2-LABORATORIO, 3-AUDITORIO, 4-SALA_ESTUDOS): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        TipoEspaco tipoEspaco;
        switch (tipo) {
            case 1:
                tipoEspaco = TipoEspaco.SALA_AULA;
                break;
            case 2:
                tipoEspaco = TipoEspaco.LABORATORIO;
                break;
            case 3:
                tipoEspaco = TipoEspaco.AUDITORIO;
                break;
            case 4:
                tipoEspaco = TipoEspaco.SALA_ESTUDO;
                break;
            default:
                System.out.println("Tipo inválido. Usando SALA_AULA por padrão.");
                tipoEspaco = TipoEspaco.SALA_AULA;
        }

        EspacoFisico espaco = new EspacoFisico(nome, capacidade, localizacao, tipoEspaco);
        espacosFisicos.add(espaco);

        System.out.println("Espaço cadastrado com sucesso!");
    }

    public static void listarUsuarios(Scanner sc, List<Usuario> usuarios) {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u.getNome() + " - " + u.getTipo());
                
            }
        }
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();  
    }

    public static void listarEspacos(Scanner sc, List<EspacoFisico> espacos) {
        System.out.println("\n=== LISTA DE ESPAÇOS FÍSICOS ===");
        if (espacos.isEmpty()) {
            System.out.println("Nenhum espaço cadastrado.");
        } else {
        	int i = 1;
            for (EspacoFisico ef : espacos) {
                System.out.println(i+ "-" + ef.getNome() + " (" + ef.getTipo() + ") - Capacidade: " + ef.getCapacidade());
                i++;
            }
        }
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();  
    }
    
    public static Usuario fazerLogin(Scanner sc, List<Usuario> usuarios) {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Email institucional: ");
        String email = sc.nextLine();
        
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u; // Login bem-sucedido
            }
        }
        return null; // Login falhou
    }
    
    public static void agendarEspaco(Scanner sc, Usuario usuario, List<EspacoFisico> espacos, List<Reservas> reservas)
            throws HorarioIndisponivelException, DiasExcedidosException {

        System.out.println("\n=== AGENDAMENTO DE ESPAÇO ===");
        listarEspacos(sc, espacos);

        System.out.print("Digite o ID do espaço: ");
        long idEspaco = sc.nextLong();
        sc.nextLine();
        EspacoFisico espaco = espacos.stream().filter(e -> e.getId() == idEspaco).findFirst().orElse(null);

        if (espaco == null) {
            System.out.println("Espaço não encontrado.");
            System.out.println("Pressione ENTER para continuar...");
            sc.nextLine();
            return;
        }

        System.out.print("Data de início (YYYY-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(sc.nextLine());

        System.out.print("Hora de início (HH:MM): ");
        LocalTime horaInicio = LocalTime.parse(sc.nextLine());

        System.out.print("Data de fim (YYYY-MM-DD): ");
        LocalDate dataFim = LocalDate.parse(sc.nextLine());

        System.out.print("Hora de fim (HH:MM): ");
        LocalTime horaFim = LocalTime.parse(sc.nextLine());

        AgendamentoService service = new AgendamentoService();
        service.agendarEspaco(usuario, espaco, dataInicio, horaInicio, dataFim, horaFim);

        System.out.println("Reserva confirmada de " + dataInicio + " às " + dataFim + ", das " + horaInicio + " às " + horaFim + ".");
        Reservas reserva = new Reservas (usuario, espaco, dataInicio, horaInicio, dataFim, horaFim);
        
        reservas.add(reserva);
        
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();  
    }
    
    
    public static List<LocalTime> gerarIntervaloHorarios(LocalTime inicio, LocalTime fim) {
        List<LocalTime> intervalo = new ArrayList<>();
        LocalTime atual = inicio;
        while (!atual.isAfter(fim)) {
            intervalo.add(atual);
            atual = atual.plusHours(1); 
        }
        return intervalo;
    }
}
