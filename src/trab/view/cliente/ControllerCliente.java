package trab.view.cliente;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import trab.DAO.ClienteDAO;
import trab.model.Cliente;


public class ControllerCliente extends Application implements Initializable{

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtFone;

    @FXML
    private CheckBox checkMensalista;

    @FXML
    private Label lblCadastrar;
    
    @FXML
    private Label lblAtualizar;
    
    @FXML
    private Button btnAdicionar;

    @FXML
    private Label lblCPF;

    @FXML
    private TextArea txtAreaListClientes;

    @FXML
    private TextField txtPesquisaCPF;
    
    @FXML
    private Label lblCPFAtual;
    
    @FXML
    private Button btnBuscar;
    
    @FXML
    private Button btnAlterar;
    
    @FXML
    private Label lblNomeAtual;

    @FXML
    private Label lblTelefoneAtual;

    @FXML
    private Label lblMensalistaAtual;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblTelefone;

    @FXML
    private Label lblMensalista;
  
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnExcluir;
    
    @FXML
    private Button btnSair;
    
    @FXML
    private Button btnProduto;
    
    @FXML
    void AdicionarCliente(ActionEvent event) { 	
    	//checkMensalista.setSelected(false);
    	Cliente cliente = pegaDados();
    	int qnd = new ClienteDAO().inserir(cliente);
    	limpaCampo();
    	listarClientes();
    	System.out.println(qnd);  	
    }
    
    @FXML
    void BuscarCliente(ActionEvent event) {
    	String CPF = txtPesquisaCPF.getText();
    	Cliente cliente = null;
    	if(!CPF.equals("")) {
    		try {
    			cliente = new ClienteDAO().findByCPF(CPF);
    		}catch(Exception e) {
    			
    		}
    		if(cliente != null) {
    			lblCPFAtual.setVisible(true);
    			lblCPF.setVisible(true);
    			lblNomeAtual.setVisible(true);
    			lblTelefoneAtual.setVisible(true);
    			lblMensalistaAtual.setVisible(true);
    			lblNome.setVisible(true);
    			lblTelefone.setVisible(true);
    			    			
    			lblCPF.setText(cliente.getCpf());
    			lblNome.setText(cliente.getNome());
    			lblTelefone.setText(cliente.getFone());
    			
    			boolean mensalista = cliente.isMensalista();
    			if(mensalista == true) {
    				lblMensalista.setVisible(true);
    				lblMensalista.setText("Sim");
    			}else if(mensalista == false) {
    				lblMensalista.setVisible(true);
    				lblMensalista.setText("Não");
    			}
    			
    			
    			lblCadastrar.setVisible(false);
    			lblAtualizar.setVisible(true);
    			
    			btnAdicionar.setVisible(false);
    			btnAlterar.setVisible(true);
    			
    			txtCPF.setText(cliente.getCpf());
    			txtNome.setText(cliente.getNome());
    			txtFone.setText(cliente.getFone());
    			checkMensalista.setSelected(cliente.isMensalista());
    			
    			
    			btnCancelar.setVisible(true);
    			btnExcluir.setVisible(true);
    		}
    	}
    }
  
    @FXML
    void AlterarCliente(ActionEvent event) {
    	Cliente cliente = pegaDados();
    	int qnd = new ClienteDAO().alterar(cliente);
    	listarClientes();
    	limpaCampo();
    }
    
    @FXML
    void CancelarAcao(ActionEvent event) {
    	limpaCampo();
    }
    
    @FXML
    void ExcluirCliente(ActionEvent event) {
    	String CPF = txtPesquisaCPF.getText();
    	ClienteDAO clienteDAO = new ClienteDAO();
    	clienteDAO.excluir(CPF);
    	listarClientes();
    	limpaCampo();
    }
    
    @FXML
    void SairAplicacao(ActionEvent event) {
    	btnSair.setOnAction(e -> Platform.exit());
    }
    
    @FXML
    void TelaProdutos(ActionEvent event) throws IOException {
    	Pane testPane = FXMLLoader
				.load(getClass().getResource("/trab/view/produto/Produto.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Produtos");
		primaryStage.show();
		
    }
     
    public Cliente pegaDados() {
    	return new Cliente(txtCPF.getText(), txtNome.getText(), txtFone.getText(), checkMensalista.isSelected());
    }
 
    private void listarClientes() {
    	txtAreaListClientes.clear();
    	List<Cliente> listaClientes = new ClienteDAO().listarTodos();
    	
    	listaClientes.forEach(cliente -> {
    		txtAreaListClientes.appendText(cliente.toString() + "\n");
    	});
    	
    }
     
    private void limpaCampo() {
    	txtCPF.clear();
    	txtNome.clear();
    	txtFone.clear();
    	txtPesquisaCPF.clear();
    	checkMensalista.setSelected(false);
    	
    	lblCPFAtual.setVisible(false);
		lblCPF.setVisible(false);
		lblNomeAtual.setVisible(false);
		lblTelefoneAtual.setVisible(false);
		lblMensalistaAtual.setVisible(false);
		lblNome.setVisible(false);
		lblTelefone.setVisible(false);
		    			
		lblCPF.setText("");
		lblNome.setText("");
		lblTelefone.setText("");
		lblMensalista.setText("");
		
		lblCadastrar.setVisible(true);
		lblAtualizar.setVisible(false);
		
		btnAdicionar.setVisible(true);
		btnAlterar.setVisible(false);
    	
    	txtCPF.requestFocus();
    	
    	btnCancelar.setVisible(false);
    	btnExcluir.setVisible(false);
    }
    
    public void execute() {
    	launch();	
    }
    
    @Override
    public void start(Stage stage) {
    	
    	try {
    		AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Cliente.fxml"));
    		stage.setTitle("Cadastro Cliente");
    		Scene sc = new Scene(pane);
    		stage.setScene(sc);
    		stage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarClientes();	
	}

}
