package trab.testes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trab.DAO.ClienteDAO;
import trab.model.*;

public class DAOClienteTest {

	@Before
	public void before() {
		System.out.println("Iniciando o teste. ");
	}
	
	@After
	public void after() {
		System.out.println("Teste finalizado.");
	}
	
	@Test
	public void findByCPF() {
		
		
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		String CPFVerdadeiro = "123465789";
		String CPFFalso = "123123123";

		Cliente clienteDiferente = new Cliente();
		clienteDiferente.setCpf("123465789");
		clienteDiferente.setNome("BiroBiro");
		clienteDiferente.setFone("222554455");
		clienteDiferente.setMensalista(true);
		
		//CPF que não existe na base de dados
		cliente = clienteDAO.findByCPF(CPFFalso);
		Assert.assertNull(cliente);
		
		//Passando valor nulo
		cliente = clienteDAO.findByCPF(null);
		Assert.assertNull(cliente);
		
		//Passando string vazia
		cliente = clienteDAO.findByCPF("");
		Assert.assertNull(cliente);
		
		//CPF que existe na base de dados
		cliente = clienteDAO.findByCPF(CPFVerdadeiro);
		if(cliente != null) {
			Assert.assertNotNull(cliente);
		}else {
			Assert.assertNull(cliente);
		}
		//Comparando cpf do objeto
		cliente = clienteDAO.findByCPF(CPFVerdadeiro);
		if(cliente != null) {
			Assert.assertEquals("Comparando CPF", "123465789", cliente.getCpf());
		}else {
			Assert.assertNull(cliente);
		}
		//Comparando Nome do objeto
		cliente = clienteDAO.findByCPF(CPFVerdadeiro);
		if(cliente != null) {
			Assert.assertEquals("Comparando Nome", "Biro Biro", cliente.getNome());
		}else {
			Assert.assertNull(cliente);
		}
		// verificando se o cliente é mensalista (espera-se receber verdadeiro)
		cliente = clienteDAO.findByCPF(CPFVerdadeiro);
		if(cliente != null && cliente.isMensalista() == true) {
			Assert.assertTrue(cliente.isMensalista());
		}else if(cliente != null && cliente.isMensalista() == false) {
			Assert.assertFalse(cliente.isMensalista());
		}else {
			Assert.assertNull(cliente);
		}
		
		//Compara os dois objetos
		cliente = clienteDAO.findByCPF(CPFVerdadeiro);
		Assert.assertNotSame("Comparando objetos",clienteDiferente, cliente);
		
	}
	

	
	@Test
	public void alterar() {
		
		Cliente clienteVerdadeiro = new Cliente();
		Cliente clienteFalso = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		int retorno;
		
		//CLIENTE EXISTENTE
		clienteVerdadeiro.setCpf("123465789");
		clienteVerdadeiro.setNome("Biro Biro");
		clienteVerdadeiro.setFone("222554455");
		clienteVerdadeiro.setMensalista(true);
		
		//CLIENTE FALSO
		clienteFalso.setCpf("88558844776");
		clienteFalso.setNome("Vampeta");
		clienteFalso.setFone("9898989888");
		clienteFalso.setMensalista(false);
		
		// Verificando se cliente não é nulo
		retorno = clienteDAO.alterar(clienteVerdadeiro);	
		Assert.assertNotNull(retorno);
		
		// Passando cliente verdadeiro
		retorno = clienteDAO.alterar(clienteVerdadeiro);
		if(retorno == 1) {
		Assert.assertEquals("Esperando retorno 1 para sucesso", 1, retorno);
		}else {
			Assert.assertEquals("Esperando retorno 0 para falha", 0, retorno);
		}
		// Passando cliente Falso
		retorno = clienteDAO.alterar(clienteFalso);	
		Assert.assertEquals("Esperando retorno 0 para falha", 0, retorno);
		
		// Verificando se cliente é nulo
		retorno = clienteDAO.alterar(null);	
		Assert.assertEquals("Esperando retorno 0 para falha (valor null)", 0, retorno);
		
	}
	
	@Test
	public void inserir() {
		
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();
		int retorno;
		
		cliente.setCpf("123465789");
		cliente.setNome("Biro Biro");
		cliente.setFone("222554455");
		cliente.setMensalista(true);
		
		//Se o cliente não existe retorna 1
		//Se o cliente existe retorna 0
		retorno = clienteDAO.inserir(cliente);
		if(retorno == 1) {
			Assert.assertEquals("Esperando retorno 1 para sucesso", 1, retorno);
		}else if(retorno == 0) {
			Assert.assertEquals("Esperando retorno 0 para ja existe", 0, retorno);
		}
		
		//Se for nulo retorna 0
		retorno = clienteDAO.inserir(null);
		Assert.assertEquals("Esperando retorno 0 para falha", 0, retorno);
		
		retorno = clienteDAO.inserir(null);
		Assert.assertTrue(retorno < 1);
		
		
	}
	
	
	@Test
	public void excluir() {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		String CPF = "88558844776";
		boolean retorno;
		
		
		retorno = clienteDAO.excluir(CPF);
		if(retorno == true) {
			Assert.assertTrue(retorno);
		}else {
			Assert.assertFalse(retorno);
		}
		
		retorno = clienteDAO.excluir(null);
		Assert.assertFalse(retorno);

	} 
	
	
	

	
}







