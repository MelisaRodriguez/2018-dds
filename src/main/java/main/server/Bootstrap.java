package main.server;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteAdapter;
import edu.dominio.usuario.Categoria;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.Usuario;
import edu.repositorios.RepoCategorias;
import edu.repositorios.RepoClientes;
import edu.repositorios.RepoUsuarios;
import edu.repositorios.RepoZonaGeografica;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public static void init() {

		List<Categoria> categorias = new ArrayList<>();
		categorias.add(new Categoria("R1", 18.76, 0.644, 0, 150));
		categorias.add(new Categoria("R2", 35.32, 0.644, 151, 325));
		categorias.add(new Categoria("R3", 60.71, 0.681, 326, 400));
		categorias.add(new Categoria("R4", 71.74, 0.738, 401, 450));
		categorias.add(new Categoria("R5", 110.38, 0.794, 451, 500));
		categorias.add(new Categoria("R6", 220.75, 0.832, 501, 600));
		categorias.add(new Categoria("R7", 443.59, 0.851, 601, 700));
		categorias.add(new Categoria("R8", 545.96, 0.851, 701, 1400));
		categorias.add(new Categoria("R9", 887.19, 0.851, 1401, Long.MAX_VALUE));

		RepoCategorias.getSingletonInstance().agregar(categorias);

		final Gson gson = new Gson();
		
		final TypeToken<List<Cliente>> clienteListType = new TypeToken<List<Cliente>>() {
		};
		List<Cliente> clientes = gson.fromJson(abrirJSON("Clientes.json"), clienteListType.getType());
		clientes.stream().map(c -> c.todosSusDispositivos()).flatMap(m -> m.stream())
				.forEach(d -> d.setFabricante(new Fabricante("Sony", FabricanteAdapter.SONY)));
		RepoClientes.getInstanceOfSingleton().agregar(clientes);

		final TypeToken<List<Usuario>> userListType = new TypeToken<List<Usuario>>() {
		};
		List<Usuario> usuarios = gson.fromJson(abrirJSON("Usuarios.json"), userListType.getType());
		usuarios.stream().forEach(u -> u.setContraseña(Cifrado.Encrypt(u.getContraseña())));
		List<Usuario> userClientes = usuarios.stream().filter(u -> !u.isAdmin()).collect(Collectors.toList());
		List<Usuario> userAdmin = usuarios.stream().filter(u -> u.isAdmin()).collect(Collectors.toList());
		for (int i = 0; i < userClientes.size(); i++)
			userClientes.get(i).setId_user(clientes.get(i).getId());
		RepoUsuarios.getInstanceOfSingleton().agregar(userAdmin);
		RepoUsuarios.getInstanceOfSingleton().agregar(userClientes);
	}

	public static List<ZonaGeografica> importadorZonas()
	{
		final TypeToken<List<ZonaGeografica>> ZonaListType = new TypeToken<List<ZonaGeografica>>() {
		};
		return new Gson().fromJson(abrirJSON("Zonas.json"), ZonaListType.getType());
	}
	
	private static JsonReader abrirJSON(String nombre) {
		InputStream stream = Bootstrap.class.getClassLoader().getResourceAsStream(nombre);
		JsonReader br = null;
		try {
			br = new JsonReader(new InputStreamReader(stream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return br;
	}

}
