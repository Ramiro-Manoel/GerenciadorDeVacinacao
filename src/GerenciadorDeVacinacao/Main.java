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

	public static int recebeInt(String mensagem) {
		Scanner sc = new Scanner(System.in);

		int retorno = Integer.MAX_VALUE;
		System.out.println(mensagem);
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
	
	public static void removeDaFila(ArrayList<Pessoa> lista, Queue<Pessoa> fila) {
		if(fila.isEmpty() == false) {
			System.out.println("O paciente removido foi o:\n" + fila.peek().exibePessoa());
			lista.add(fila.poll());
			} else {
				System.out.println("A fila está vazia!");
			}
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
		
		fila.add(new Pessoa("dudu", "123", 5));
		fila.add(new Pessoa("corto", "321", 10));
		fila.add(new Pessoa("ramiro", "321", 15));
		
		do {
			System.out.println("1. Inserir paciente na Fila\n" + "2. Remover paciente da Fila\n"
					+ "3. Visualizar Fila\n" + "4. Visualizar Lista\n" + "5. Visualizar Lista em ordem Alfabética\n"
					+ "6. Filtrar Pacientes por faixa etária na Fila\n"
					+ "7. Filtrar Pacientes por faixa etária na Lista\n" + "8. Buscar Paciente por CPF na Fila\n"
					+ "9. Buscar Paciente por CPF na Lista\n" + "10. Contabilizar número total de vacinados na Lista\n"
					+ "11. Gerar Relatório\n" + "0. Salvar e sair\n");

			op = recebeInt("digite a opção desejada:");

			switch (op) {
			case 1:
				break;
				
			case 2:
				removeDaFila(lista, fila);
				break;
				
			case 3:
				for(Pessoa e: fila) {
					System.out.println(e);;
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
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
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
