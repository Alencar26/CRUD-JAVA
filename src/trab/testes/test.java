package trab.testes;

import java.util.List;

import trab.DAO.ClienteDAO;
import trab.model.Cliente;

public class test {
	
	
	public static void main(String[] args) {

	//TESTE INSERIR VEICULO DAO
	
		/*
			ClienteDAO clienteDAO = new ClienteDAO();
			
			Cliente cliente = new Cliente("08520639550", "Maria", "33333333", true);
			int quantidade = clienteDAO.inserir(cliente);
			System.out.println(quantidade);
	*/		
			//LISTAR
				
			List<Cliente> listaClientes = new ClienteDAO().listarTodos();
			
			for(Cliente cliente : listaClientes) {
				System.out.println(cliente);
			}
			
	
	
	
	}
}
