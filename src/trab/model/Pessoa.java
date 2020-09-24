package trab.model;

public class Pessoa {
	
	private String cpf;
	private String nome;
	private String fone;
	
	
	
	public Pessoa() {

	}


	public Pessoa(String cpf, String nome, String fone) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.fone = fone;
	}
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	
	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", fone=" + fone + "]";
	}
	
	
	
	
}
