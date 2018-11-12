package edu.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class GenericoRepos<T> {
	protected List<T> entidades = new ArrayList<>();

	public List<T> getEntidades() {
		return entidades;
	}

	public void agregar(T entidad) {
		entidades.add(entidad);
	}

	public void persistirEntidades() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		em.getTransaction().begin();
		entidades.stream().forEach(e -> em.persist(e));
		em.getTransaction().commit();
		em.close();
		entidades = new ArrayList<>();
	}

}