package edu.repositorios;

import java.util.function.Predicate;

import edu.dominio.empresa.Transformador;
import edu.dominio.empresa.ZonaGeografica;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;

public class RepoZonaGeografica extends GenericoRepos<ZonaGeografica> {
	private static RepoZonaGeografica repoZona = null;
	
	public RepoZonaGeografica(){}
	
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
