package edu.usuario.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.Before;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;
import edu.repositorios.RuntimeTypeAdapterFactory;

public class ClienteFixture {
	protected List<Cliente> clientes;
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected SanyoTelevisor televisor;

	@Before
	public void fixture()
	{
		RuntimeTypeAdapterFactory<Fabricante> adapterFactory = RuntimeTypeAdapterFactory.of(Fabricante.class, "type")
				.registerSubtype(SanyoTelevisor.class); // Repetir ultimo punto por cada nueva implementacion adapter
		Gson leer = new GsonBuilder().registerTypeAdapterFactory(adapterFactory).create();
		Type auxTipo = new TypeToken<List<Cliente>>(){}.getType();
		try {
			RepoClientes.cargarClientes(leer.fromJson(new FileReader("Clientes.json"), auxTipo));
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientes = RepoClientes.getInstanceOfSingleton().getEntidades();
		dispositivoInteligente = mock(DispositivoInteligente.class);
	}
}
