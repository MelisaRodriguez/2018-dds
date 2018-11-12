package edu.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityTransaction;

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


