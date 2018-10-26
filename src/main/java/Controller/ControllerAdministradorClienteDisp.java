package Controller;

import java.time.LocalDate;
import java.util.HashMap;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ControllerAdministradorClienteDisp {
	
	public static int id;
	public static String cadena;
	
	public static ModelAndView index(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		
		id=Integer.parseInt(req.params(":idCliente"));//ID  le pego repo conseguir user x id 
		cadena=req.params(":idCliente");
		
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("wea", id);

		
		return new ModelAndView(
				viewModel, 
				"Inteligente.hbs");
	}
	
	public static Void registrarDispoInt(Request req, Response res) {	
		//res.
		req.queryParams("nombreDispo");
		req.queryParams("minima");
		req.queryParams("maxima");

		
		
		RepoClientes repo=new RepoClientes();
		Cliente buscado=repo.buscarPorId(id,Cliente.class);
		
		DispositivoInteligente dispositivo=new DispositivoInteligente(req.queryParams("nombreDispo"),LocalDate.now(),
				new Fabricante("Sony",new Sony()),  Double.parseDouble(req.queryParams("minima")), Double.parseDouble(req.queryParams("maxima")) );
		
		buscado.agregarDispositivo(dispositivo);
		
		
		
		repo.borrar2(id,Cliente.class);
		
		
		repo.agregarUsuario(buscado);
		
		
		System.out.println("PRIMMMMMMMMMMMMMMMMMMMM "+repo.getEntidades().get(0).getId());
		
		
		
		res.redirect("/Clientes/"+cadena);
		return null;
	}	
}
