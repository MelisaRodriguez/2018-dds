package edu.repositorios;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;

	public static RepoClientes getInstanceOfSingleton() {
		if (repo == null)
			repo = new RepoClientes();
		return repo;
	}

	public Cliente getCliente(int id) {
		// TODO En el LoginView había que hardcodear un new Fabricante. Ver
		Optional<Cliente> cliente = entidades.stream().filter(c -> c.getId() == id).findFirst();
		if (cliente.isPresent())
			return cliente.get();
		else {
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			em.getTransaction().begin();
			Cliente unCliente = em.find(Cliente.class, id);
			em.getTransaction().commit();
			em.close();
			agregar(unCliente);
			return unCliente;
		}
	}
}
