package edu.repositorios;

import java.util.function.Predicate;

import edu.dominio.empresa.Transformador;
import edu.dominio.empresa.ZonaGeografica;

public class RepoZonaGeografica extends GenericoRepos<ZonaGeografica> {
	private static RepoZonaGeografica repoZona = null;
	
	private RepoZonaGeografica(){}
	
	private ZonaGeografica conseguirZonaSegun(Predicate<ZonaGeografica> cond)
	{
		return this.entidades.stream().filter(cond).findFirst().get();
	}
	
	public void agregarTransformador(Transformador unTransformador)
	{
		ZonaGeografica zonaResultado = this.conseguirZonaSegun(zona->zona.puedeAgregarTransformador(unTransformador.getLugar()));
		zonaResultado.agregarTransformador(unTransformador);
	}
	
	public long consultarConsumo(String ID)
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
