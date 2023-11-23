package GerenciadorDeVacinacao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
		while(!paciente.verificaNome(sc.nextLine())) {
			System.out.println("digite um nome válido (pelo menos 10 caracteres e um espaço)");
		}
		
		System.out.println("digite o cpf do paciente a ser adicionado na fila(sem ponto e traço)");
		while(!paciente.verificaCPF(sc.nextLine())) {
			System.out.println("digite um cpf válido (de acordo com as regras de cpf");
		}
		System.out.println("digite a idade do paciente");
		while(!paciente.verificaIdade(recebeInt())) {
			System.out.println("digite uma idade valida (entre 1 e 110)");
		}
		return paciente;
	}
	
	public static void removeDaFila(ArrayList<Pessoa> lista, Queue<Pessoa> fila) {
		if(fila.isEmpty() == false) {
			System.out.println("O paciente removido foi o:\n" + fila.peek().exibePessoa());
			lista.add(fila.poll());
			} else {
				System.out.println("A fila está vazia!");
			}
	}
	
	public static boolean procuraCPFFila(Queue<Pessoa> fila, String cpf) {
		for(Pessoa e : fila) {
			if(e.getCpf().equals(cpf)) {
				System.out.println(e.exibePessoa() );
				return true;
			}
		}
		return false;
	}
	
	public static boolean procuraCPFLista(ArrayList<Pessoa> lista, String cpf) {
		for(Pessoa e : lista) {
			if(e.getCpf().equals(cpf)) {
				System.out.println(e.exibePessoa() );
				return true;
			}
		}
		return false;
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
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		int op = 0;
		fila.clear();
		lista.clear();
	
		
		do {
			System.out.println("1. Inserir paciente na Fila\n" + "2. Remover paciente da Fila\n"
					+ "3. Visualizar Fila\n" + "4. Visualizar Lista\n" + "5. Visualizar Lista em ordem Alfabética\n"
					+ "6. Filtrar Pacientes por faixa etária na Fila\n"
					+ "7. Filtrar Pacientes por faixa etária na Lista\n" + "8. Buscar Paciente por CPF na Fila\n"
					+ "9. Buscar Paciente por CPF na Lista\n" + "10. Contabilizar número total de vacinados na Lista\n"
					+ "11. Gerar Relatório\n" + "0. Salvar e sair\n");

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
				for(Pessoa e: fila) {
					System.out.println(e.exibePessoa());;
				}
				
				break;
			case 4:
				for(int i = 0 ; i < lista.size() ; i ++) {
				System.out.println(i + " - " + lista.get(i).exibePessoa());	
				}
				break;
			case 5:
				break;
			case 6:
				System.out.println("digite a margem do filtro de faixa ");
				break;
			case 7:
				break;
			case 8:
				System.out.println("digite o cpf do paciente que deseja buscar na fila:");
				if(!procuraCPFFila(fila, sc.nextLine())) {
					System.out.println("não foi encontrado nenhum paciente com esse cpf na fila");
				}
				
				break;
			case 9:
				System.out.println("digite o cpf do paciente que deseja buscar na lista:");
				if(!procuraCPFFila(fila, sc.nextLine())) {
					System.out.println("não foi encontrado nenhum paciente com esse cpf na lista");
				}
				break;
			case 10:
				System.out.println("o número total de vacinados na lista é: " + lista.size());
				break;
			case 11:
				break;
			case 0:
				ObjectOutputStream objectOut;
				try {
					objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("vacinacao.dat")));
					objectOut.writeObject(fila);
					objectOut.writeObject(lista);
					objectOut.close();
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
