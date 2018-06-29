package edu.usuario.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import org.junit.Before;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import static org.mockito.Mockito.mock;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RuntimeTypeAdapterFactory;

public class ClienteFixture {
	protected List<Cliente> clientes;
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected SanyoTelevisor televisor;

	@Before
	public void fixture() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
        final RuntimeTypeAdapterFactory<Fabricante> typeFabricante = RuntimeTypeAdapterFactory
                .of(Fabricante.class, "type")
                .registerSubtype(SanyoTelevisor.class);//AGREGAR 1 X 1
                //.registerSubtype(FabricanteY.class);
        
        final RuntimeTypeAdapterFactory<Dispositivo> typeDispositivo = RuntimeTypeAdapterFactory
                .of(Dispositivo.class, "type")
                .registerSubtype(DispositivoInteligente.class)//AGREGAR 1 X 1
                .registerSubtype(DispositivoEstandar.class);
        
        final Gson gson = new GsonBuilder()
        		.registerTypeAdapterFactory(typeFabricante)
        		.registerTypeAdapterFactory(typeDispositivo)
        		.create();
        
        final TypeToken<List<Cliente>> clienteListType 
    	= new TypeToken<List<Cliente>>() {};
    	try {
    		List<Cliente> clientes = gson.fromJson( new FileReader("Clientes.json"), clienteListType.getType());
    	}
    	catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
			e.printStackTrace();
		}
    	dispositivoInteligente = mock(DispositivoInteligente.class);
	}
}
