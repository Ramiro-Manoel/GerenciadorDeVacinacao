package GerenciadorDeVacinacao;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) {
		
		
		Queue<Pessoa> fila = new PriorityQueue<>();
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		
		
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("vacinacao.dat")));
			fila = (Queue<Pessoa>)in.readObject();
			lista = (ArrayList<Pessoa>) in.readObject();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Pessoa pessoa = new Pessoa("corto", "11111", 23);
		lista.get(0).exibePessoa();
		lista.get(0).setNome("ramiro");
		
		ObjectOutputStream objectOut;
		try {
			objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("vacinacao.dat")));
			objectOut.writeObject(fila);
			objectOut.writeObject(lista);
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
	}	
	
}
