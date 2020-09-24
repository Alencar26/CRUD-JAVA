package trab.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import trab.db.dbConnection;
import trab.model.Cliente;

public class ClienteDAO extends dbConnection {

	final String SQL_INSERT_CLIENTE = "INSERT INTO CLIENTE(CPF, NOME, TELEFONE, MENSALISTA) VALUES(?, ?, ?, ?)";
	final String SQL_SELECT_CLIENTE = "SELECT * FROM CLIENTE";
	final String SQL_SELECT_CLIENTE_CPF = "SELECT * FROM CLIENTE WHERE CPF = ?";
	final String SQL_UPDATE_CLIENTE_CPF = "UPDATE CLIENTE SET CPF = ?, NOME = ?, TELEFONE = ?, MENSALISTA = ? WHERE CPF = ?";
	final String SQL_DELETE_CLIENTE = "DELETE FROM CLIENTE WHERE CPF = ?";
	
	public int inserir(Cliente cliente) {
		
		int quantidade = 0;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_CLIENTE);){
			
			pst.setString(1, cliente.getCpf());
			pst.setString(2, cliente.getNome());
			pst.setString(3, cliente.getFone());
			pst.setBoolean(4, cliente.isMensalista());
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
		
	public List<Cliente> listarTodos() {
	List<Cliente> listaClientes = new ArrayList<Cliente>();
	
	
	try(Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_CLIENTE);){
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Cliente cliente = new Cliente();
			
			cliente.setCpf(rs.getString("CPF"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setFone(rs.getString("TELEFONE"));
			cliente.setMensalista(rs.getBoolean("MENSALISTA"));
			
			listaClientes.add(cliente);
		
		}	
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return listaClientes;
	
}

	public Cliente findByCPF(String CPF) {
	
	Cliente cliente = null;
	
	try(Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_CLIENTE_CPF);){
		
		pst.setString(1, CPF);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			cliente = new Cliente();
			
			cliente.setCpf(rs.getString("CPF"));
			cliente.setNome(rs.getString("NOME"));
			cliente.setFone(rs.getString("TELEFONE"));
			cliente.setMensalista(rs.getBoolean("MENSALISTA"));
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return cliente;
}

	
	public int alterar(Cliente cliente) {
int quantidade = 0;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_CLIENTE_CPF);){
			
			pst.setString(1, cliente.getCpf());
			pst.setString(2, cliente.getNome());
			pst.setString(3, cliente.getFone());
			pst.setBoolean(4, cliente.isMensalista());
			pst.setString(5, cliente.getCpf());
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return quantidade;
	
	}

	public void excluir(String CPF) {
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_DELETE_CLIENTE);){
			
			pst.setString(1, CPF);
			pst.execute();
			System.out.println("Sucesso!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
