package edu.usuario.test;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Before;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.repositorios.RepoZonaGeografica;

public class ZonaFixture{
	
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected SanyoTelevisor televisor;
	
	public List<ZonaGeografica> zona;
	
	@Before
	public void fixture() throws JsonIOException, JsonSyntaxException, FileNotFoundException
	{
        zona = RepoZonaGeografica.getSingletonInstance().getEntidades();
	}

	public List<ZonaGeografica> getClientes() {
		return zona;
	}
}