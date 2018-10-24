package edu.usuario.test;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.fabricante.Sony;
import edu.repositorios.RepoZonaGeografica;

public class ZonaFixture{
	
	protected Sony s;
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