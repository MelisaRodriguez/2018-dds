package edu.repositorios;

import edu.dominio.empresa.Administrador;

public class RepoAdministrador extends GenericoRepos<Administrador> {

	private RepoAdministrador() {
		super(Administrador.class);
	}

	private static RepoAdministrador repo = null;

	public static RepoAdministrador getInstanceOfSingleton() {
		if (repo == null)
			repo = new RepoAdministrador();
		return repo;
	}
}
