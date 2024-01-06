package test.java;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.dao.ClienteDAO;
import main.java.dao.IClienteDAO;
import main.java.domain.Cliente;
import main.java.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDAOTest {

	private IClienteDAO clienteDao;

	private Cliente cliente;

	public ClienteDAOTest() {
		clienteDao = new ClienteDAO();
	}

	@Before
	public void init() throws TipoChaveNaoEncontradaException {
		cliente = new Cliente();
		cliente.setCpf(12312312312L);
		cliente.setNome("Carlos");
		cliente.setCidade("Curitiba");
		cliente.setEnd("Rua A");
		cliente.setEstado("PR");
		cliente.setNumero(10);
		cliente.setTel(41999999999l);
		clienteDao.cadastrar(cliente);
	}

	@Test
	public void pesquisarCliente() {
		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
		Assert.assertNotNull(clienteConsultado);
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setCpf(12312312323l);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);
	}

	@Test
	public void excluirCliente() {
		clienteDao.excluir(cliente.getCpf());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException {
		cliente.setNome("Carlos");
		clienteDao.alterar(cliente);
		Assert.assertEquals("Carlos", cliente.getNome());
	}

	@Test
	public void buscarTodos() {
		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);
	}
}
