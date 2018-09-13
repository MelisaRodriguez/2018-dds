package edu.usuario.test;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.fabricante.FabricanteMock;
import edu.repositorios.RepoZonaGeografica;

public class ZonaFixture{
	
	
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
			return false;
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
	}
	
	public Sony s;
	
	
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	public List<ZonaGeografica> zona;
	@Before
	public void fixture() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
		s=new Sony();
        zona = RepoZonaGeografica.getSingletonInstance().getEntidades();
		zona.get(0).getTransformadores().get(0).getClientes().get(0).getDispositivosInteligentes().stream().forEach(x->x.getFabricante().setFabricanteMock(s));
        
	}
	public List<ZonaGeografica> getClientes() {
		return zona;
	}
}