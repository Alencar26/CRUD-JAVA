package trab.view.produto;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import trab.DAO.ClienteDAO;
import trab.DAO.ProdutoDAO;
import trab.model.Cliente;
import trab.model.Produto;
import java.time.ZoneId;

public class ControllerProduto{

    @FXML
    private TextField txtNomeProduto;

    @FXML
    private TextField txtValor;

    @FXML
    private DatePicker txtData;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TextArea txtAreaListaProdutos;

    @FXML
    void AdicionarProduto(ActionEvent event) throws ParseException {
    	Produto produto = pegaDados();
    	int qnd = new ProdutoDAO().inserir(produto);
    	limpaCampo();
    	listarProdutos();
    	System.out.println(qnd);  	
    }

    @FXML
    void BuscarProduto(ActionEvent event) {
    	String p = txtPesquisar.getText();
    	Produto produto = null;
    	if(!p.equals("")) {
    		try {
    			
    			int id = Integer.parseInt(p);
    			produto = new ProdutoDAO().findById(id);
    			
    		}catch(Exception e) {
    			
    		}
    		
    		if(produto != null) {
    			txtNomeProduto.setText(produto.getNome());
    			txtValor.setText(produto.getValor() + "");
    			Date data = produto.getData();
    			LocalDate d = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    			txtData.setValue(d);
    		}
    	}
    	
    	
    }

    @FXML
    void DeletarProduto(ActionEvent event) {
    	int id = Integer.parseInt(txtPesquisar.getText());
    	ProdutoDAO produtoDAO = new ProdutoDAO();
    	produtoDAO.excluir(id);
    	listarProdutos();
    	limpaCampo();
    }

    @FXML
    void EditarProduto(ActionEvent event) {
    	Produto produto = pegaDadosId();
    	int qnd = new ProdutoDAO().alterar(produto);
    	listarProdutos();
    	limpaCampo();
    }
    
    private Produto pegaDados() throws ParseException {
    	float valor = Float.parseFloat(txtValor.getText());
    	LocalDate data = txtData.getValue();
    	Date d = java.sql.Date.valueOf(data);
    	return new Produto(txtNomeProduto.getText(), valor, d);
    }
    
    private Produto pegaDadosId() {
    	float valor = Float.parseFloat(txtValor.getText());
    	LocalDate data = txtData.getValue();
    	Date d = java.sql.Date.valueOf(data);
    	int id = Integer.parseInt(txtPesquisar.getText());
    	return new Produto(id,txtNomeProduto.getText(), valor, d);
    }

    private void limpaCampo() {
    	txtNomeProduto.clear();
    	txtValor.clear();
    	txtData.getEditor().clear();
    	txtData.setValue(null);
    	txtPesquisar.clear();
    }
    
    private void listarProdutos() {
    	txtAreaListaProdutos.clear();
    	List<Produto> listaProdutos = new ProdutoDAO().listarTodos();
    	
    	listaProdutos.forEach(produto -> {
    		txtAreaListaProdutos.appendText(produto.toString() + "\n");
    	});
    }
    
}
