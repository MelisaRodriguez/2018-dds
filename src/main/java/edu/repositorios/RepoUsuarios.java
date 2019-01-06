package edu.repositorios;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.usuario.Usuario;

public class RepoUsuarios extends GenericoRepos<Usuario> {

	private RepoUsuarios() {
		super(Usuario.class);
	}

	private static RepoUsuarios repo = null;

	public static RepoUsuarios getInstanceOfSingleton() {
		if (repo == null)
			repo = new RepoUsuarios();
		return repo;
	}

	public Usuario getUsuario(String username, String password) throws NoResultException {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		try {
			Usuario u = em.createQuery("from Usuario c where c.usuario = :u and c.contraseña = :p", Usuario.class)
					.setParameter("u", username).setParameter("p", password).getSingleResult();
			em.close();
			return u;
		} catch (NoResultException e) {
			em.close();
			throw new NoResultException();
		}
	}
}
