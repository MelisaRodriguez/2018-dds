package edu.repositorios;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class GenericoRepos<T> {
	private Class<T> clazz;

	private GenericoRepos() {
	}

	protected GenericoRepos(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void agregar(T entidad) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		em.getTransaction().begin();
		em.persist(entidad);
		em.getTransaction().commit();
		em.close();
	}

	public void agregar(List<T> entidades) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		em.getTransaction().begin();
		entidades.stream().forEach(e -> em.persist(e));
		em.getTransaction().commit();
		em.close();
	}

	public T findByID(int id) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		T entidad = em.find(clazz, id);
		em.close();
		return entidad;
	}

	public List<T> getEntidades() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		List<T> entidades = em.createQuery("from " + clazz.getName(), clazz).getResultList();
		em.close();
		return entidades;
	}
}