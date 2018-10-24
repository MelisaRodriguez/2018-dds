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
import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.usuario.Cliente;

import edu.repositorios.RuntimeTypeAdapterFactory;
import edu.usuario.test.ZonaFixture.Sony;

public class ClienteFixture {
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	
	
	public class Sony implements FabricanteMock{

		public Sony() {}
		@Override
		public void apagar(DispositivoInteligente d) {}

		@Override
		public void encender(DispositivoInteligente d) {}

		@Override
		public void activarAhorroDeEnergia(DispositivoInteligente d) {}

		@Override
		public double cuantoConsume(DispositivoInteligente d) {
			return 1;
		}

		@Override
		public boolean estaEncendido(DispositivoInteligente d) {
			return true;
		}

		@Override
		public boolean estaApagado(DispositivoInteligente d) {
			return false;
		}

		@Override
		public boolean estaModoAhorroEnergia(DispositivoInteligente d) {
			return false;
		}

		@Override
		public double getPotencia(DispositivoInteligente d) {
			return 0;
		}

		@Override
		public double getHorasEncendido(DispositivoInteligente d) {
			return 0;
		}
		
		@Override
		public String getEstado(DispositivoInteligente d) {
			return "Encencido";
		}
	}
	
	public Sony s;
	
	
	public List<Cliente> clientes;
	
	@Before
	public void fixture() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		s=new Sony();
       
		final RuntimeTypeAdapterFactory<FabricanteMock> typeFabricante = RuntimeTypeAdapterFactory
                .of(FabricanteMock.class, "type")
                .registerSubtype(Sony.class);//AGREGAR 1 X 1
                //.registerSubtype(FabricanteY.class);*/
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
    	dispositivoInteligente = new DispositivoInteligente("Smart TV",LocalDate.now(), new Fabricante("SONY",s),0,0);
	
    	clientes.get(0).getDispositivosInteligentes().stream().forEach(x->x.getFabricante().setFabricanteMock(s));

	
	
	}


	public List<Cliente> getClientes() {
		return clientes;
	}
	
}
