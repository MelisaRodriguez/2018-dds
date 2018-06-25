package edu.repositorios;

import java.awt.geom.Point2D;
import java.util.function.Predicate;

import edu.dominio.empresa.Transformador;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.usuario.Cliente;

public class RepoZonaGeografica extends GenericoRepos<ZonaGeografica> {
	private static RepoZonaGeografica repoZona = null;
	
	private RepoZonaGeografica(){}
	
	private ZonaGeografica conseguirZonaSegun(Predicate<ZonaGeografica> cond)
	{
		return this.entidades.stream().filter(cond).findFirst().get();
	}
	
	public void agregarTransformador(Transformador unTransformador, Point2D lugar)
	{
		conseguirZona(lugar).agregarTransformador(unTransformador);
	}

	private ZonaGeografica conseguirZona(Point2D lugar) {
		return this.conseguirZonaSegun(zona->zona.estaEnRango(lugar));
	}
	
	public void SolicitarTransformador(Cliente unCliente, Point2D lugar){
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
