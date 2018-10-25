package edu.repositorios;
import java.util.List;

import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;
	
	public static RepoClientes getInstanceOfSingleton()
	{
		if (repo == null)
				repo = new RepoClientes();
		return repo;
	}
	
	 public List<Cliente> traerClientesDeBD() {
		 return this.getFromDB(Cliente.class);
	 }
	
	public void agregarUsuario(Cliente cliente) {
		this.addInstanceToDB(Cliente.class,  cliente);
	}
	
	public static void cargarClientes(List<Cliente> clientes)
	{
		repo.entidades = clientes;
	}	
}
