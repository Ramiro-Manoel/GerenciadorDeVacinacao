package GerenciadorDeVacinacao;

import java.io.Serializable;
import java.util.Comparator;

public class CustomIntegerComparator implements Comparator<Pessoa>, Serializable{

	@Override
	public int compare(Pessoa p1, Pessoa p2) {
		if(p1.getIdade() < p2.getIdade()) {
			return 1;
		}
		return -1;
	}
}