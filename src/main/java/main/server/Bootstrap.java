package main.server;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.Usuario;
import edu.repositorios.RepoClientes;
import edu.repositorios.RepoUsuarios;
import edu.repositorios.RuntimeTypeAdapterFactory;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public static void init() {
		Sony s = new Sony();

		final RuntimeTypeAdapterFactory<FabricanteMock> typeFabricante = RuntimeTypeAdapterFactory
				.of(FabricanteMock.class, "type").registerSubtype(Sony.class);
		final Gson gson = new GsonBuilder().registerTypeAdapterFactory(typeFabricante).create();

		final TypeToken<List<Cliente>> clienteListType = new TypeToken<List<Cliente>>() {
		};
		List<Cliente> clientes = gson.fromJson(abrirJSON("Clientes.json"), clienteListType.getType());
		clientes.get(0).getDispositivosInteligentes().stream().forEach(x -> x.getFabricante().setFabricanteMock(s));
		RepoClientes.getInstanceOfSingleton().getEntidades().addAll(clientes);
		RepoClientes.getInstanceOfSingleton().persistirEntidades();

		final TypeToken<List<Usuario>> userListType = new TypeToken<List<Usuario>>() {
		};
		List<Usuario> usuarios = gson.fromJson(abrirJSON("Usuarios.json"), userListType.getType());
		usuarios.stream().forEach(u -> u.setContraseña(Cifrado.Encrypt(u.getContraseña())));
		List<Usuario> userClientes = usuarios.stream().filter(u -> !u.isAdmin()).collect(Collectors.toList());
		List<Usuario> userAdmin = usuarios.stream().filter(u -> u.isAdmin()).collect(Collectors.toList());
		for (int i = 0; i < userClientes.size(); i++)
			userClientes.get(i).setId_user(clientes.get(i).getId());
		RepoUsuarios.getInstanceOfSingleton().getEntidades().addAll(usuarios);
		RepoUsuarios.getInstanceOfSingleton().getEntidades().addAll(userAdmin);
		RepoUsuarios.getInstanceOfSingleton().persistirEntidades();

		// Tal vez iniciar zonas tambien

	}

	private static JsonReader abrirJSON(String nombre) {
		InputStream stream = Bootstrap.class.getClassLoader().getResourceAsStream(nombre);
		JsonReader br = null;
		try {
			br = new JsonReader(new InputStreamReader(stream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}

}
