package edu.repositorios;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;
	
	public static RepoClientes getInstanceOfSingleton()
	{
		if (repo == null)
				repo = new RepoClientes();
		return repo;
	}
	
	// logica para preguntar a la DB
	
	public static <T> List<T>  getFromDB(Class<T> clase) {		
		//Por suerte la tabla se llama igual que la clase, por eso en el from puedo poner clase.getName(), asi solo paso un parametro
			EntityManager em = PerThreadEntityManagers.getEntityManager();
			return em.createQuery("from " + clase.getName(), clase).getResultList();		
	}
	
	public static  <T> void  addInstanceToDB(Class<T> clase,T ObjetoAPersistir) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
					
			tx.begin();
			try {
				em.persist(ObjetoAPersistir);			
				tx.commit();
			}
			catch (Exception ex) {
				tx.rollback();
			}
		}

	public static <T> T buscarPorId(int id, Class <T> clase){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		List <T> resultado = em.createQuery("from " + clase.getName() + " c where c.id = :id", clase) //
		        .setParameter("id", id) //
		        .getResultList();
		return (resultado.isEmpty()) ? null : resultado.get(0);
	}
}


