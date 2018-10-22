package edu.repositorios;

import java.util.List;

import edu.dominio.empresa.Administrador;

public class RepoAdministrador extends GenericoRepos<Administrador>{
	private static RepoAdministrador repo = null;
	
	public static RepoAdministrador getInstanceOfSingleton()
	{
		if (repo == null)
				repo = new RepoAdministrador();
		return repo;
	}
	
	public static void cargarAdministrador(List<Administrador> administrador)
	{
		repo.entidades = administrador;
	}
}
