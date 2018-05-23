package edu.repositorios;

import java.util.function.Predicate;

import edu.dominio.usuario.Categoria;

public class RepoCategorias extends GenericoRepos<Categoria> {

	private static RepoCategorias repoCategorias = null;

	private RepoCategorias() {
		entidades.add(new Categoria("R1", 18.76, 0.644, 0, 150));
		entidades.add(new Categoria("R2", 35.32, 0.644, 151, 325));
		entidades.add(new Categoria("R3", 60.71, 0.681, 326, 400));
		entidades.add(new Categoria("R4", 71.74, 0.738, 401, 450));
		entidades.add(new Categoria("R5", 110.38, 0.794, 451, 500));
		entidades.add(new Categoria("R6", 220.75, 0.832, 501, 600));
		entidades.add(new Categoria("R7", 443.59, 0.851, 601, 700));
		entidades.add(new Categoria("R8", 545.96, 0.851, 701, 1400));
		entidades.add(new Categoria("R9", 887.19, 0.851, 1401, Long.MAX_VALUE));
	}

	public Categoria solicitarCategoria(Predicate<Categoria> condicion) {
		return entidades.stream().filter(condicion).findFirst().get();
	}

	public static RepoCategorias getSingletonInstance() {
		if (repoCategorias == null) {
			repoCategorias = new RepoCategorias();
		}
		return repoCategorias;
	}
}
