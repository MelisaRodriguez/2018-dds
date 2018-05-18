package edu.usuario.test;

import java.util.List;
import org.junit.Before;

import edu.usuario.Cliente;
import edu.usuario.RepoClientes;

public class ClienteFixture {
	protected List<Cliente> clientes;

	@Before
	public void fixture() // algun dia manejar errores
	{
		clientes = RepoClientes.getInstanceOfSingleton().getInfo();
	}
}
