package trab.model;

public class Cliente extends Pessoa {

	private boolean mensalista;
	
	public Cliente() {
	}

	public Cliente(String cpf, String nome, String fone, boolean mensalista) {
		super(cpf, nome, fone);
		this.mensalista = mensalista;
	}

	public boolean isMensalista() {
		return mensalista;
	}

	public void setMensalista(boolean mensalista) {
		this.mensalista = mensalista;
	}

	@Override
	public String toString() {
		return "Cliente: [Mensalista: " + mensalista  + ", Nome: " + super.getNome() + ", CPF: "
				+ super.getCpf() + ", Telefone: " + super.getFone() + "]";
	}
	
	
	
	
}
