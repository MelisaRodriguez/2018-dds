package edu.usuario.test;

import java.time.LocalDate;
import java.util.List;
import org.junit.Before;

import static org.mockito.Mockito.mock;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;

public class ClienteFixture {
	protected List<Cliente> clientes;
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected SanyoTelevisor televisor;

	@Before
	public void fixture() // algun dia manejar errores
	{
		clientes = RepoClientes.getInstanceOfSingleton().getEntidades();
		//televisor = new SanyoTelevisor();
		//dispositivoInteligente = new DispositivoInteligente("Heladera", LocalDate.of(2017, 3, 28), televisor);
		dispositivoInteligente = mock(DispositivoInteligente.class);
	}
}
