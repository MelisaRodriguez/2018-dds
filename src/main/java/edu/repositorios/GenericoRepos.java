package edu.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class GenericoRepos<T> {
	protected List<T> entidades = new ArrayList<>();

	public List<T> getEntidades() {
		return entidades;
	}
	
	public void agregar(T entidad)
	{
		entidades.add(entidad);
	}
	
	
	
	
	
	
	protected <T> List<T>  getFromDB(Class<T> clase) {		
		//Por suerte la tabla se llama igual que la clase, por eso en el from puedo poner clase.getName(), asi solo paso un parametro
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from " + clase.getName(), clase).getResultList();		
	}
	public  <T> void  addInstanceToDB(Class<T> clase,T ObjetoAPersistir) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
					
			tx.begin();
			try {
				em.persist(ObjetoAPersistir);			
				tx.commit();
			}
			catch (Exception ex) {
				tx.rollback();
				burbujeoExcepecion(ObjetoAPersistir);
			}
		}


	protected <T> void burbujeoExcepecion(T ObjetoAPersistir)
	{
		
	}
		
	protected <T> boolean existe(String nombre,Class<T> clase) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		return !em.createQuery("from " + clase.getName() + " c where c.nombre = :nombre", clase) //
		        .setParameter("nombre", nombre) //
		        .getResultList().isEmpty();
	}
	
	protected <T> T buscar(String nombre, Class <T> clase){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		List <T> resultado = em.createQuery("from " + clase.getName() + " c where c.nombre = :nombre", clase) //
		        .setParameter("nombre", nombre) //
		        .getResultList();
		return (resultado.isEmpty()) ? null : resultado.get(0);
	}
	
	
	
	public <T> T buscarPorId(int id, Class <T> clase){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		List <T> resultado = em.createQuery("from " + clase.getName() + " c where c.id = :id", clase) //
		        .setParameter("id", id) //
		        .getResultList();
		return (resultado.isEmpty()) ? null : resultado.get(0);
	}
	/*
	protected <T> T buscarPorId(String id, Class <T> clase){
		return buscarPorId(Long.valueOf(id).longValue(), clase);
	}*/
	
	
	public <T> void borrar2(int id,Class<T> clase) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		T resultado = buscarPorId(id, clase);
		if(resultado != null) {
			em.getTransaction().begin();
			em.remove(resultado);
			em.getTransaction().commit();
		}
		em.close();
		
		System.out.println("EEEEEEEEEEEEEEEEEEEEEHHHHHHHHHHHHHHHHHHH");
	}
	
	public <T> void borrar(String nombre,Class<T> clase) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		T resultado = buscar(nombre, clase);
		if(resultado != null) {
			em.getTransaction().begin();
			em.remove(resultado);
			em.getTransaction().commit();
		}
		em.close();
		
		System.out.println("EEEEEEEEEEEEEEEEEEEEEHHHHHHHHHHHHHHHHHHH");
	}
}