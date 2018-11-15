package edu.usuario.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;

import org.junit.Before;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RuntimeTypeAdapterFactory;

public class ClienteFixture {

	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected Sony s;
	protected List<Cliente> clientes;

	@Before
	public void fixture() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		s = new Sony();

		final RuntimeTypeAdapterFactory<FabricanteMock> typeFabricante = RuntimeTypeAdapterFactory
				.of(FabricanteMock.class, "type").registerSubtype(Sony.class);
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFabricante)
				// .registerTypeAdapterFactory(typeDispositivo)
				.create();

		final TypeToken<List<Cliente>> clienteListType = new TypeToken<List<Cliente>>() {
		};

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/main/resources/Clientes.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientes = gson.fromJson(br, clienteListType.getType());
		dispositivoInteligente = new DispositivoInteligente("Smart TV", LocalDate.now(), new Fabricante("SONY", s), 0,
				0);

		clientes.get(0).getDispositivosInteligentes().stream().forEach(x -> x.getFabricante().setFabricanteMock(s));
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
}
