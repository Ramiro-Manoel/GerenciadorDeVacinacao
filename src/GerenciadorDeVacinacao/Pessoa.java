package GerenciadorDeVacinacao;

import java.io.Serializable;

public class Pessoa implements Serializable{
	private String nome;
	private String cpf;
	private int idade;

	public Pessoa() {}
	
	public Pessoa(String nome, String cpf, int idade) {
		this.nome = nome;
		this.cpf = cpf;
		this.idade = idade;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String exibePessoa() {
		return  nome + ", " + cpf + ", " + idade;
	}

	
	public boolean verificaCPF(String cpf) {
        if (verificaNumRepetido(cpf)) {
            return false;
        }
        int[] teste = new int[cpf.length()];
        for (int i = 0; i < cpf.length(); i++) {
            teste[i] = Integer.valueOf(cpf.substring(i, i + 1));
        }

        int primeiroDigito = 0;
        for (int i = 0; i < (teste.length - 2); i++) {
            primeiroDigito += teste[i] * (10 - i);
        }

        int segundoDigito = 0;
        for (int i = 0; i < (teste.length - 1); i++) {
            segundoDigito += teste[i] * (11 - i);
        }

        if ((primeiroDigito * 10) % 11 == teste[9] && (segundoDigito * 10) % 11 == teste[10]) {
            this.cpf = cpf;
            return true;
        }
        return false;
    }

    public boolean verificaNumRepetido(String cpf) {
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean verificaNome(String nome) {
        if (nome.length() >= 10) {
            return false;
        }

        for (int i = 0; i < nome.length(); i++) {
            if (nome.charAt(i) == ' ') {
                return false;
            }
        }
        this.nome = nome;
        return true;
    }

    public boolean verificaIdade(int idade) {
        if(idade >= 1 && idade <= 110) {
            return false;
        }

        this.idade = idade;
        return true;
    }
	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", idade=" + idade + "]";
	}
}
