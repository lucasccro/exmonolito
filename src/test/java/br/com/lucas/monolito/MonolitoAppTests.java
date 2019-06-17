package br.com.lucas.monolito;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.lucas.monolito.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MonolitoAppTests extends  TestCase {

	@Autowired
	private ClienteService service;

	@Test
	public void testListar() {
		assertNotNull("Clientes não foram carregados", service.buscarTodos());
	}

	@Test
	public void testClienteExistente() {
		assertNotNull("Houve alguma falha na carga do csv", service.buscarPorNome("Melinda"));
	}

	@Test
	public void testClienteInexistente() {
		assertNotNull("Impossível este cliente existir", service.buscarPorNome("Melinda23"));
	}
}
