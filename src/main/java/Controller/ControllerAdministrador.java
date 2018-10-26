package Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.empresa.Administrador;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAdministrador {
	
	public static Administrador admin; //TODO gas, hace tu brujeria
	public static int id;
	public static String cadena;
	public static List <Cliente> clientes;
	static RepoClientes repo=new RepoClientes();
	public static Cliente clienteSeleccionado = null;
	
	public static void setAdmin(Administrador admin) {
		ControllerAdministrador.admin = admin;
	}

	
	
	public static ModelAndView indexPrimero(Request req, Response res) {
	
		HashMap<String, Object> viewModel = new HashMap<>();
		
		
		clientes = ControllerAdministrador.getFromDB(Cliente.class);
		
		viewModel.put("user", clientes);
		
		return new ModelAndView(
				viewModel, 
				"Untitled.hbs");
	}
	
	public static ModelAndView indexMedio(Request req, Response res) {
		
		if (clienteSeleccionado == null) {
			int id=Integer.parseInt(req.params(":idCliente"));
			clienteSeleccionado = ControllerAdministrador.buscarPorId(id,Cliente.class);	
		}
		
		HashMap<String, Object> viewModel = new HashMap<>();
		

		viewModel.put("id", clienteSeleccionado.getId() );
		viewModel.put("cliente", clienteSeleccionado);
		
		
		return new ModelAndView(
				viewModel, 
				"AdministradorCliente.hbs");
	}
	
	public static ModelAndView indexUltimoInteligente(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		
		id=Integer.parseInt(req.params(":idCliente"));
		cadena=req.params(":idCliente");
		
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("wea", clienteSeleccionado.getId() );

		
		return new ModelAndView(
				viewModel, 
				"Inteligente.hbs");
	}
	
	public static Void registrarDispoInt(Request req, Response res) {	
		req.queryParams("nombreDispo");
		req.queryParams("minima");
		req.queryParams("maxima");
		
		DispositivoInteligente dispositivo=new DispositivoInteligente(req.queryParams("nombreDispo"),LocalDate.now(),
				new Fabricante("Sony",new Sony()),  Double.parseDouble(req.queryParams("minima")), Double.parseDouble(req.queryParams("maxima")) );
		
		clienteSeleccionado.agregarDispositivo(dispositivo);
		
		
		res.redirect("/Clientes/"+cadena);
		return null;
	}
	
	public static ModelAndView indexUltimoEstandar(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		
		id=Integer.parseInt(req.params(":idCliente"));
		cadena=req.params(":idCliente");
		
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("wea", clienteSeleccionado.getId() );

		
		return new ModelAndView(
				viewModel, 
				"estandars.hbs");
	}
	
	public static Void registrarDispoEst(Request req, Response res) {	
		req.queryParams("nombreDispo");
		req.queryParams("kw");
		req.queryParams("hsUso");
		req.queryParams("minima");
		req.queryParams("maxima");
		
		DispositivoEstandar dispositivo=new DispositivoEstandar(req.queryParams("nombreDispo"),Double.parseDouble(req.queryParams("kw")),
				Double.parseDouble(req.queryParams("hsUso")),new Fabricante("Sony",new Sony()),  
				Double.parseDouble(req.queryParams("minima")), Double.parseDouble(req.queryParams("maxima")) );
			
		clienteSeleccionado.agregarDispositivo(dispositivo);
		
		res.redirect("/Clientes/"+cadena);
		return null;
	}
	
	public static Void logOut (Request req, Response res) {
		
		ControllerAdministrador.addInstanceToDB(Cliente.class, clienteSeleccionado);
		
		res.redirect(" "); //TODO aca tiene que ir la path del menu de login
		return null;
	}
	
	
	
	
	protected static <T> List<T>  getFromDB(Class<T> clase) {		
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
