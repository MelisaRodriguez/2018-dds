package edu.usuario.test;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import org.junit.Before;
import java.time.LocalDate;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Cliente;

import edu.repositorios.RuntimeTypeAdapterFactory;

public class ClienteFixture {
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected SanyoTelevisor televisor;
	
	public List<Cliente> clientes;
	
	@Before
	public void fixture() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		dispositivoInteligente = new DispositivoInteligente("Smart TV",LocalDate.now(), new SanyoTelevisor(),0,0);
        final RuntimeTypeAdapterFactory<Fabricante> typeFabricante = RuntimeTypeAdapterFactory
                .of(Fabricante.class, "type")
                .registerSubtype(SanyoTelevisor.class);//AGREGAR 1 X 1
                //.registerSubtype(FabricanteY.class);
        /*
        final RuntimeTypeAdapterFactory<Dispositivo> typeDispositivo = RuntimeTypeAdapterFactory
                .of(Dispositivo.class, "type")
                .registerSubtype(DispositivoInteligente.class)//AGREGAR 1 X 1
                .registerSubtype(DispositivoEstandar.class);
        */
        final Gson gson = new GsonBuilder()
        		.registerTypeAdapterFactory(typeFabricante)
        		//.registerTypeAdapterFactory(typeDispositivo)
        		.create();
        
        final TypeToken<List<Cliente>> clienteListType 
    	= new TypeToken<List<Cliente>>() {};
    	
    	BufferedReader br = null;
    	try {
			br = new BufferedReader( new FileReader("Clientes.json") );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	clientes = gson.fromJson( br, clienteListType.getType());
	}


	public List<Cliente> getClientes() {
		return clientes;
	}
	
}
