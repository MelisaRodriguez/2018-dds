package edu.repositorios;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.SanyoTelevisor;

public class Runner {

	
	public static void main(String[] args) {
		DispositivoInteligente d1 = new DispositivoInteligente("Heladera",LocalDate.of(2018, 5, 18),new SanyoTelevisor());
		
		
		
		
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
        
        List<DispositivoInteligente> dispositivos = 
        		Arrays.asList(d1);        
        
        List<DispositivoInteligente> dispositivos = 
        		Arrays.asList(d1);
        
        
        String json = gson.toJson(dispositivos);
        System.out.println(json);
        
        //CREACION DEL JSON ARRIBA
        
        final TypeToken<List<DispositivoInteligente>> dispositivoListType 
        	= new TypeToken<List<DispositivoInteligente>>() {};
        List<DispositivoInteligente> parseado = gson.fromJson(json, dispositivoListType.getType());
	}
}
