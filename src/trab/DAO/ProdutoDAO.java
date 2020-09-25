package trab.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import trab.db.dbConnection;
import trab.model.Cliente;
import trab.model.Produto;

public class ProdutoDAO extends dbConnection {

	final String SQL_INSERT_PRODUTO = "INSERT INTO PRODUTO(NOME, VALOR, DATA) VALUES(?, ?, ?)";
	final String SQL_SELECT_PRODUTO = "SELECT * FROM PRODUTO";
	final String SQL_SELECT_PRODUTO_ID = "SELECT * FROM PRODUTO WHERE ID = ?";
	final String SQL_UPDATE_PRODUTO_ID = "UPDATE PRODUTO SET NOME = ?, VALOR = ?, DATA = ? WHERE ID = ?";
	final String SQL_DELETE_PRODUTO = "DELETE FROM PRODUTO WHERE ID = ?";
	
	
public int inserir(Produto produto) {
		
		int quantidade = 0;
		
		try(Connection connection = this.conectar();
				PreparedStatement pst = connection.prepareStatement(SQL_INSERT_PRODUTO);){
			
			pst.setString(1, produto.getNome());
			pst.setFloat(2, produto.getValor());
			pst.setDate(3, java.sql.Date.valueOf(produto.getData().toString()));
			
			quantidade = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return quantidade;
	}
	
public List<Produto> listarTodos() {
	List<Produto> listaProdutos = new ArrayList<Produto>();
	
	
	try(Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PRODUTO);){
		
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Produto produto = new Produto();
			
			produto.setId(rs.getInt("ID"));
			produto.setNome(rs.getString("NOME"));
			produto.setValor(rs.getFloat("VALOR"));
			produto.setData(rs.getDate("DATA"));

			
			listaProdutos.add(produto);
		
		}	
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return listaProdutos;
	
}

public Produto findById(int id) {
	
	Produto produto = null;
	
	try(Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PRODUTO_ID);){
		
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			produto = new Produto();
			
			produto.setNome(rs.getString("NOME"));
			produto.setValor(rs.getFloat("VALOR"));
			produto.setData(rs.getDate("DATA"));
		}
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	return produto;
}

public int alterar(Produto produto) {
	int quantidade = 0;
	
	try(Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_UPDATE_PRODUTO_ID);){
		
		pst.setString(1, produto.getNome());
		pst.setFloat(2, produto.getValor());
		pst.setDate(3, java.sql.Date.valueOf(produto.getData().toString()));
		pst.setInt(4, produto.getId());
		
		quantidade = pst.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return quantidade;

}

public void excluir(int id) {
	try(Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_DELETE_PRODUTO);){
		
		pst.setInt(1, id);
		pst.execute();
		System.out.println("Sucesso!");
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
}

}
