package test.java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.dao.IClienteDAO;
import main.java.domain.Cliente;
import main.java.exceptions.TipoChaveNaoEncontradaException;
import main.java.services.ClienteService;
import main.java.services.IClienteService;
import test.java.dao.ClienteDaoMock;

public class ClienteServiceTest {

	private IClienteService clienteService;

	private Cliente cliente;

	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDaoMock();
		clienteService = new ClienteService(dao);
	}

	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Carlos");
		cliente.setCidade("Curitiba");
		cliente.setEnd("Rua A");
		cliente.setEstado("PR");
		cliente.setNumero(10);
		cliente.setTel(41999999999l);
	}

	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		Boolean retorno = clienteService.cadastrar(cliente);

		Assert.assertTrue(retorno);
	}

	@Test
	public void excluirCliente() {
		clienteService.excluir(cliente.getCpf());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Carlos");
		clienteService.alterar(cliente);

		Assert.assertEquals("Carlos", cliente.getNome());
	}
}
