package edu.repositorios;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.usuario.Usuario;

public class RepoUsuarios extends GenericoRepos<Usuario> {

	private static RepoUsuarios repo = null;

	public static RepoUsuarios getInstanceOfSingleton() {
		if (repo == null)
			repo = new RepoUsuarios();
		return repo;
	}

	public boolean existeUsuario(String username, String password) {
		Optional<Usuario> usuario = entidades.stream()
				.filter(c -> c.getContraseña() == password && c.getUsuario() == username).findFirst();
		if (!usuario.isPresent()) {
			try {
				EntityManager em = PerThreadEntityManagers.getEntityManager();
				em.getTransaction().begin();
				agregar(em.createQuery("from Usuario c where c.usuario = :u and c.contraseña = :p", Usuario.class)
						.setParameter("u", username).setParameter("p", password).getSingleResult());
				em.getTransaction().commit();
				em.close();
			} catch (Exception x) {
				return false;
			}
		}
		return true;
	}

	public Usuario getUsuario(String password, String username) {
		return this.entidades.stream().filter(u -> u.getContraseña() == password && u.getUsuario() == username)
				.findFirst().get();
	}
}
