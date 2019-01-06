package edu.repositorios;

import java.util.function.Predicate;

import edu.dominio.usuario.Categoria;

public class RepoCategorias extends GenericoRepos<Categoria> {

	private static RepoCategorias repoCategorias = null;

	private RepoCategorias() {
		super(Categoria.class);
	}

	public Categoria solicitarCategoria(Predicate<Categoria> condicion) {
		return getEntidades().stream().filter(condicion).findFirst().get();
	}

	public static RepoCategorias getSingletonInstance() {
		if (repoCategorias == null) {
			repoCategorias = new RepoCategorias();
		}
		return repoCategorias;
	}
}