package edu.usuario.test;

import java.util.List;
import org.junit.Before;

import edu.dominio.fabricante.MensajeObjetos;
import edu.empresa.DispositivoEstandar;
import edu.empresa.DispositivoInteligente;
import edu.fabricante.Fabricante;
import edu.usuario.Cliente;
import edu.usuario.RepoClientes;

public class ClienteFixture {
	protected List<Cliente> clientes;
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected Fabricante fabricante;
	protected MensajeObjetos formaDeEnvio;

	@Before
	public void fixture() // algun dia manejar errores
	{
		clientes = RepoClientes.getInstanceOfSingleton().getInfo();
		formaDeEnvio = new MensajeObjetos();
		fabricante = new Fabricante("GAMA", formaDeEnvio);
		dispositivoInteligente = new DispositivoInteligente("Heladera", 10, fabricante);
	}
}
