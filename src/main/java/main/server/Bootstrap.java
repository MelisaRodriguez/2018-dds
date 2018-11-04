package main.server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
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
		Sony s=new Sony();
	       
		final RuntimeTypeAdapterFactory<FabricanteMock> typeFabricante = RuntimeTypeAdapterFactory
                .of(FabricanteMock.class, "type")
                .registerSubtype(Sony.class);
        final Gson gson = new GsonBuilder()
        		.registerTypeAdapterFactory(typeFabricante)
        		//.registerTypeAdapterFactory(typeDispositivo)
        		.create();
        
        final TypeToken<List<Cliente>> clienteListType 
    	= new TypeToken<List<Cliente>>() {};
    	List<Cliente> clientes = gson.fromJson(abrirJSON("Clientes.json"), clienteListType.getType());
    	clientes.get(0).getDispositivosInteligentes().stream().forEach(x->x.getFabricante().setFabricanteMock(s));
    	RepoClientes.getInstanceOfSingleton().getEntidades().addAll(clientes); 
    	RepoClientes.getInstanceOfSingleton().persistirEntidades();
    	
    	final TypeToken<List<Usuario>> userListType 
    	= new TypeToken<List<Usuario>>() {};
    	List<Usuario> usuarios = gson.fromJson(abrirJSON("Usuarios.json"), userListType.getType());
    	for(int i = 0; i<usuarios.size(); i++)
    		usuarios.get(i).setId_user(clientes.get(i).getId());
    	RepoUsuarios.getInstanceOfSingleton().getEntidades().addAll(usuarios);
    	RepoUsuarios.getInstanceOfSingleton().persistirEntidades();
    	
    	//Tal vez iniciar zonas tambien

	}
	
	private static JsonReader abrirJSON(String nombre)
	{
		String path = Bootstrap.class.getClassLoader().getResource(nombre).getPath();
    	JsonReader br = null;
    	try {
			br = new JsonReader(new FileReader(path) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return br;
	}

}
