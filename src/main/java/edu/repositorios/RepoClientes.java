package edu.repositorios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityTransaction;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;

	public static RepoClientes getInstanceOfSingleton() {
		if (repo == null)
			repo = new RepoClientes();
		return repo;
	}

	// los 2 methodos de abajo hacen lo mismo, los deje para comparar cual es el mas powah de los 2
	
	public static Cliente buscarPorId(int id){
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		List <Cliente> resultado = em.createQuery("from Cliente c where c.id = :id", Cliente.class) 
		        .setParameter("id", id) 
		        .getResultList();
		return (resultado.isEmpty()) ? null : resultado.get(0);
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
			unCliente.dispositivosInteligentes().stream().forEach(d -> d.getFabricante().setFabricanteMock(new Sony()
			return unCliente;
		}

	}
	
	@Override
	public List<Cliente> getEntidades() {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		this.entidades = em.createQuery("from Cliente", Cliente.class).getResultList(); 
		
		return entidades;
	}
	
	public static  <T> void  persistirCliente(Cliente Cliente) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
					
			tx.begin();
			try {
				em.persist(Cliente);			
				tx.commit();
			}
			catch (Exception ex) {
				tx.rollback();
			}
	}


	
	
}


