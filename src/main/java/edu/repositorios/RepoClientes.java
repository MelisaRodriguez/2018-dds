package edu.repositorios;

import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;

	private RepoClientes() {
		super(Cliente.class);
	}

	public static RepoClientes getInstanceOfSingleton() {
		if (repo == null)
			repo = new RepoClientes();
		return repo;
	}

}
