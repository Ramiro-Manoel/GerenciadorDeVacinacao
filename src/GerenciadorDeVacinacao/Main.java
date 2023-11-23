package GerenciadorDeVacinacao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static int recebeInt() {
		Scanner sc = new Scanner(System.in);

		int retorno = Integer.MAX_VALUE;
		do {
			try {
				retorno = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("você digitou um caractere inválido, por favor digite um número");
			}
			sc.nextLine();
		} while (retorno == Integer.MAX_VALUE);

		return retorno;
	}

	public static Pessoa criaPessoa() {
		Pessoa paciente = new Pessoa();
		Scanner sc = new Scanner(System.in);

		System.out.println("digite o nome compelto do paciente a ser adicionado na fila");
		while (!paciente.verificaNome(sc.nextLine())) {
			System.out.println("digite um nome válido (pelo menos 10 caracteres e um espaço)");
		}

		System.out.println("digite o cpf do paciente a ser adicionado na fila(sem ponto e traço)");
		while (!paciente.verificaCPF(sc.nextLine())) {
			System.out.println("digite um cpf válido (de acordo com as regras de cpf");
		}
		System.out.println("digite a idade do paciente");
		while (!paciente.verificaIdade(recebeInt())) {
			System.out.println("digite uma idade valida (entre 1 e 110)");
		}
		return paciente;
	}

	public static void removeDaFila(ArrayList<Pessoa> lista, Queue<Pessoa> fila) {
		if (fila.isEmpty() == false) {
			System.out.println("O paciente removido foi o:\n" + fila.peek().exibePessoa());
			lista.add(fila.poll());
		} else {
			System.out.println("A fila está vazia!");
		}
	}

	public static boolean procuraCPFFila(Queue<Pessoa> fila, String cpf) {
		for (Pessoa e : fila) {
			if (e.getCpf().equals(cpf)) {
				System.out.println(e.exibePessoa());
				return true;
			}
		}
		return false;
	}

	public static boolean procuraCPFLista(ArrayList<Pessoa> lista, String cpf) {
		for (Pessoa e : lista) {
			if (e.getCpf().equals(cpf)) {
				System.out.println(e.exibePessoa());
				return true;
			}
		}
		return false;
	}

	public static boolean filtroFaixaEtariaFila(Queue<Pessoa> fila, int idadeMin, int idadeMax) {

		boolean retorno = false;

		for (Pessoa e : fila) {
			if (e.getIdade() >= idadeMin && e.getIdade() <= idadeMax) {
				System.out.println(e.exibePessoa());
				retorno = true;
			}
		}
		return retorno;
	}

	public static boolean filtroFaixaEtariaLista(ArrayList<Pessoa> lista, int idadeMin, int idadeMax) {

		boolean retorno = false;

		for (Pessoa e : lista) {
			if (e.getIdade() >= idadeMin && e.getIdade() <= idadeMax) {
				System.out.println(e.exibePessoa());
				retorno = true;
			}
		}
		return false;
	}

	public static boolean listaAlfabetica(ArrayList<Pessoa> lista) {
		Collections.sort(lista);

		if (lista.isEmpty()) {
			return false;
		}
		System.out.println("Lista em ordem alfabetica:");
		for (Pessoa p : lista) {
			System.out.println(p.exibePessoa());
		}

		return true;
	}

	public static boolean gerarRelatorio(ArrayList<Pessoa> lista) {
		if (lista.isEmpty()) {
			return false;
		}
		Collections.sort(lista);

		System.out.println("\nRelatório:");
		System.out.println("pacientes com idade entre 1 e 6:");
		filtroFaixaEtariaLista(lista, 1, 6);
		System.out.println("\npacientes com idade entre 7 e 12:");
		filtroFaixaEtariaLista(lista, 7, 12);
		System.out.println("\npacientes com idade entre 13 e 18:");
		filtroFaixaEtariaLista(lista, 13, 18);
		System.out.println("\npacientes com idade entre 19 e 35:");
		filtroFaixaEtariaLista(lista, 19, 35);
		System.out.println("\npacientes com idade entre 36 e 55:");
		filtroFaixaEtariaLista(lista, 36, 55);
		System.out.println("\npacientes com idade entre 56 e 80:");
		filtroFaixaEtariaLista(lista, 56, 80);
		System.out.println("\npacientes com idade entre 81 e 110:");
		filtroFaixaEtariaLista(lista, 81, 110);
		return true;
	}

	public static void addPessoasFilaELista(ArrayList<Pessoa> lista, Queue<Pessoa> fila) {
		lista.add(new Pessoa("João Silva", "12345678909", 25));
		lista.add(new Pessoa("Maria Oliveira", "98765432100", 53));
		lista.add(new Pessoa("Pedro Santos", "11122233344", 78));
		lista.add(new Pessoa("Ana Souza", "55566677788", 42));
		lista.add(new Pessoa("Carlos Pereira", "99988877766", 17));
		lista.add(new Pessoa("Mariana Costa", "33322211100", 90));
		lista.add(new Pessoa("José Lima", "44455566677", 5));
		lista.add(new Pessoa("Camila Rocha", "77788899911", 35));
		lista.add(new Pessoa("Fernando Almeida", "22211144455", 60));
		lista.add(new Pessoa("Amanda Nunes", "66655544433", 21));
		lista.add(new Pessoa("Ricardo Santos", "88877766655", 8));
		lista.add(new Pessoa("Patrícia Silva", "44433322211", 75));
		lista.add(new Pessoa("Lucas Oliveira", "99911122233", 47));
		lista.add(new Pessoa("Cristina Pereira", "11155599900", 28));
		lista.add(new Pessoa("Felipe Souza", "77722288844", 10));
		lista.add(new Pessoa("Tatiane Costa", "33344455566", 95));
		lista.add(new Pessoa("Anderson Lima", "66677788899", 50));
		lista.add(new Pessoa("Danielle Rocha", "55544433322", 31));
		lista.add(new Pessoa("Roberto Almeida", "22233344455", 70));
		lista.add(new Pessoa("Isabela Nunes", "88899911122", 15));
		lista.add(new Pessoa("Bruno Santos", "44422266633", 82));
		lista.add(new Pessoa("Vanessa Silva", "11166633344", 3));
		lista.add(new Pessoa("Leonardo Oliveira", "77711199900", 65));
		lista.add(new Pessoa("Laura Pereira", "33388855566", 45));
		lista.add(new Pessoa("Gustavo Lima", "99944422211", 23));

		fila.add(new Pessoa("Renata Oliveira", "12345678909", 25));
		fila.add(new Pessoa("Vinícius Silva", "98765432100", 53));
		fila.add(new Pessoa("Larissa Santos", "11122233344", 78));
		fila.add(new Pessoa("Ricardo Souza", "55566677788", 42));
		fila.add(new Pessoa("Juliana Pereira", "99988877766", 17));
		fila.add(new Pessoa("Anderson Costa", "33322211100", 90));
		fila.add(new Pessoa("Fernanda Lima", "44455566677", 5));
		fila.add(new Pessoa("Lucas Rocha", "77788899911", 35));
		fila.add(new Pessoa("Mariana Almeida", "22211144455", 60));
		fila.add(new Pessoa("Gustavo Nunes", "66655544433", 21));
		fila.add(new Pessoa("Camila Santos", "88877766655", 8));
		fila.add(new Pessoa("Pedro Silva", "44433322211", 75));
		fila.add(new Pessoa("Aline Oliveira", "99911122233", 47));
		fila.add(new Pessoa("Rafael Pereira", "11155599900", 28));
		fila.add(new Pessoa("Thiago Souza", "77722288844", 10));
		fila.add(new Pessoa("Isabel Costa", "33344455566", 95));
		fila.add(new Pessoa("Carlos Lima", "66677788899", 50));
		fila.add(new Pessoa("Mariane Rocha", "55544433322", 31));
		fila.add(new Pessoa("Rafael Almeida", "22233344455", 70));
		fila.add(new Pessoa("Ana Nunes", "88899911122", 15));
		fila.add(new Pessoa("Bruno Santos", "44422266633", 82));
		fila.add(new Pessoa("Laura Silva", "11166633344", 3));
		fila.add(new Pessoa("Leonardo Oliveira", "77711199900", 65));
		fila.add(new Pessoa("Carla Pereira", "33388855566", 45));
		fila.add(new Pessoa("Gabriel Lima", "99944422211", 23));
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Queue<Pessoa> fila = new PriorityQueue<>(new CustomIntegerComparator());
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("vacinacao.dat")));
			fila = (Queue<Pessoa>) in.readObject();
			lista = (ArrayList<Pessoa>) in.readObject();
			in.close();
			System.out.println("lista e fila restauradas do arquivo local\n");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		int op = 0;
		int idadeMax;
		int idadeMin;

		do {
			System.out.println("\nMENU: \n1. Inserir paciente na Fila\n" + "2. Remover paciente da Fila\n"
					+ "3. Visualizar Fila\n" + "4. Visualizar Lista\n" + "5. Visualizar Lista em ordem Alfabética\n"
					+ "6. Filtrar Pacientes por faixa etária na Fila\n"
					+ "7. Filtrar Pacientes por faixa etária na Lista\n" + "8. Buscar Paciente por CPF na Fila\n"
					+ "9. Buscar Paciente por CPF na Lista\n" + "10. Contabilizar número total de vacinados na Lista\n"
					+ "11. Gerar Relatório\n" + "12. Limpar lista e fila\n"
					+ "13. adicionar 25 pessoas a lista e 25 pessoas a fila\n" + "0. Salvar e sair\n");

			System.out.println("digite a opção desejada:");
			op = recebeInt();

			switch (op) {
			case 1:
				fila.add(criaPessoa());
				break;

			case 2:
				removeDaFila(lista, fila);
				break;

			case 3:
				for (Pessoa e : fila) {
					System.out.println(e.exibePessoa());
					;
				}

				break;
			case 4:
				for (int i = 0; i < lista.size(); i++) {
					System.out.println(i + " - " + lista.get(i).exibePessoa());
				}
				break;
			case 5:
				if (!listaAlfabetica(lista)) {
					System.out.println("não existe nenhum paciente na lista");
				}
				break;
			case 6:
				System.out.println("digite a margem do filtro de faixa etária da fila\n" + "idade mínima:");
				idadeMin = recebeInt();
				System.out.println("idade máxima:");
				idadeMax = recebeInt();

				System.out.println("pacientes na fila entre as idades: " + idadeMin + " e " + idadeMax + ": ");
				if (!filtroFaixaEtariaFila(fila, idadeMin, idadeMax)) {
					System.out.println("não foi encontrado nenhum paciente dentro das idades informadas");
				}
				break;
			case 7:
				System.out.println("digite a margem do filtro de faixa etária da fila\n" + "idade mínima:");
				idadeMin = recebeInt();
				System.out.println("idade máxima:");
				idadeMax = recebeInt();

				System.out.println("pacientes na lista entre as idades: " + idadeMin + " e " + idadeMax + ": ");
				if (!filtroFaixaEtariaLista(lista, idadeMin, idadeMax)) {
					System.out.println("não foi encontrado nenhum paciente dentro das idades informadas");
				}
				break;
			case 8:
				System.out.println("digite o cpf do paciente que deseja buscar na fila:");
				if (!procuraCPFFila(fila, sc.nextLine())) {
					System.out.println("não foi encontrado nenhum paciente com esse cpf na fila");
				}

				break;
			case 9:
				System.out.println("digite o cpf do paciente que deseja buscar na lista:");
				if (!procuraCPFFila(fila, sc.nextLine())) {
					System.out.println("não foi encontrado nenhum paciente com esse cpf na lista");
				}
				break;
			case 10:
				System.out.println("o número total de vacinados na lista é: " + lista.size());
				break;
			case 11:
				gerarRelatorio(lista);
				break;
			case 12:
				lista.clear();
				fila.clear();
				System.out.println("Lista e fila esvaziadas");
				break;
			case 13:
				addPessoasFilaELista(lista, fila);
				System.out.println("25 pessoas adicionadas a fila e  25 pessoas adicionadas a lista");
				break;
			case 0:
				ObjectOutputStream objectOut;
				try {
					objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("vacinacao.dat")));
					objectOut.writeObject(fila);
					objectOut.writeObject(lista);
					objectOut.close();
					System.out.println("lista e fila salvas no arqivo local");
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("digite uma opção válida");
				break;
			}
		} while (op != 0);

	}

}
