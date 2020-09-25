package trab.model;

import java.util.Date;

public class Produto {

	private int id;
	private String nome;
	private float valor;
	private Date data;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	public Produto(String nome, float valor, Date data) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.data = data;
	}
	
	public Produto(int id, String nome, float valor, Date data) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.data = data;
	}
	public Produto() {
		
	}
	@Override
	public String toString() {
		return "Produto [Id= "+ id +"nome=" + nome + ", valor=" + valor + ", data de entrada=" + data + "]";
	}
	
	
}
