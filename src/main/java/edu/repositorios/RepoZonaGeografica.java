package edu.repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.google.gson.reflect.TypeToken;

import edu.dominio.empresa.Transformador;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;

public class RepoZonaGeografica{
	private static RepoZonaGeografica repoZona = null;
	private List<ZonaGeografica> zonas = new ArrayList<>();
	
	private RepoZonaGeografica() {
		zonas = main.server.Bootstrap.importadorZonas();
	}

	private ZonaGeografica conseguirZonaSegun(Predicate<ZonaGeografica> cond) {
		return zonas.stream().filter(cond).findFirst().get();
	}

	public void agregarTransformador(Transformador unTransformador, Punto lugar) {
		conseguirZona(lugar).agregarTransformador(unTransformador);
	}

	private ZonaGeografica conseguirZona(Punto lugar) {
		return this.conseguirZonaSegun(zona -> zona.estaEnRango(lugar));
	}

	public void SolicitarTransformador(Cliente unCliente, Punto lugar) {
		conseguirZona(lugar).agregarCliente(unCliente, lugar);
	}

	public double consultarConsumo(String ID) {
		return this.conseguirZonaSegun(zona -> zona.getID().equalsIgnoreCase(ID)).verConsumo();
	}

	public static RepoZonaGeografica getSingletonInstance() {
		if (repoZona == null) {
			repoZona = new RepoZonaGeografica();
		}
		return repoZona;
	}
}
