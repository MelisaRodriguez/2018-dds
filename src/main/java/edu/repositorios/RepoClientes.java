package edu.repositorios;

import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;
	
	public static RepoClientes getInstanceOfSingleton()
	{
		if (repo == null)
				repo = new RepoClientes();
		return repo;
	}
}
