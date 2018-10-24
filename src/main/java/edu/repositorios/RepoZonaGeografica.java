package edu.repositorios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.Transformador;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;

public class RepoZonaGeografica extends GenericoRepos<ZonaGeografica> {
	private static RepoZonaGeografica repoZona = null;
	
	public RepoZonaGeografica(){
		/*
		final RuntimeTypeAdapterFactory<Fabricante> typeFabricante = RuntimeTypeAdapterFactory
                .of(Fabricante.class, "type")
                .registerSubtype(SanyoTelevisor.class);//AGREGAR 1 X 1
                //.registerSubtype(FabricanteY.class);*/
        
        final RuntimeTypeAdapterFactory<Dispositivo> typeDispositivo = RuntimeTypeAdapterFactory
                .of(Dispositivo.class, "type")
                .registerSubtype(DispositivoInteligente.class)//AGREGAR 1 X 1
                .registerSubtype(DispositivoEstandar.class);
        
        final Gson gson = new GsonBuilder()
        		//.registerTypeAdapterFactory(typeFabricante)
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
    	entidades = gson.fromJson( br, clienteListType.getType());
	}
	
	private ZonaGeografica conseguirZonaSegun(Predicate<ZonaGeografica> cond)
	{	
		return this.entidades.stream().filter(cond).findFirst().get();
	}
	
	public void agregarTransformador(Transformador unTransformador, Punto lugar)
	{
		conseguirZona(lugar).agregarTransformador(unTransformador);
	}

	private ZonaGeografica conseguirZona(Punto lugar) {
		return this.conseguirZonaSegun(zona->zona.estaEnRango(lugar));
	}
	
	public void SolicitarTransformador(Cliente unCliente, Punto lugar){
		conseguirZona(lugar).agregarCliente(unCliente, lugar);
	}
	public double consultarConsumo(String ID)
	{
		return this.conseguirZonaSegun(zona->zona.getID().equalsIgnoreCase(ID)).verConsumo();
	}
	
	public static RepoZonaGeografica getSingletonInstance() {
		if (repoZona == null) {
			repoZona = new RepoZonaGeografica();
		}
		return repoZona;
	}
}
