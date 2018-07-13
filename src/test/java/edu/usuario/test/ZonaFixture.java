package edu.usuario.test;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.junit.Before;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RuntimeTypeAdapterFactory;

public class ZonaFixture{
	//protected List<Cliente> clientes;
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected SanyoTelevisor televisor;
	
	public List<ZonaGeografica> zona;
	
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
        
        final TypeToken<List<ZonaGeografica>> clienteListType 
    	= new TypeToken<List<ZonaGeografica>>() {};
    	
    	BufferedReader br = null;
    	try {
			br = new BufferedReader( new FileReader("zonas.json") );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	zona = gson.fromJson( br, clienteListType.getType());
	}

	public List<ZonaGeografica> getClientes() {
		return zona;
	}
}