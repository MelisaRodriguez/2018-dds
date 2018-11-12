package main.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


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
	
	public static Administrador admin; 
	public static int id;
	public static String cadena;
	public static List<Cliente> clientes;
	static RepoClientes repo = new RepoClientes();
	public static Cliente clienteSeleccionado = null;

	public static void setAdmin(Administrador admin) {
		ControllerAdministrador.admin = admin;
	}
	

	
	public static ModelAndView indexViewDatosGenerales(Request req, Response res) {
	
		HashMap<String, Object> viewModel = new HashMap<>();
			
		clientes = repo.getFromDB(Cliente.class);
		clientes.stream().forEach(c->c.getDispositivosInteligentes().stream()
				.forEach(d -> d.setFabricante(new Fabricante("Sony", new Sony()))));
		viewModel.put("user", clientes);
		return new ModelAndView(
				viewModel, 
				"Administrador.hbs"); 
	}
	
	public static ModelAndView indexViewDatosDeUnCliente(Request req, Response res) {
		if (clienteSeleccionado == null) {
			int id=Integer.parseInt(req.params(":idCliente"));
			clienteSeleccionado = repo.buscarPorId(id,Cliente.class);	
			clienteSeleccionado.getDispositivosInteligentes().stream()
					.forEach(d -> d.setFabricante(new Fabricante("Sony", new Sony())));
		}
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("id", clienteSeleccionado.getId() );
		viewModel.put("cliente", clienteSeleccionado);
		return new ModelAndView(
				viewModel, 
				"AdministradorCliente.hbs");
	}
	
	public static ModelAndView indexViewAgregarDispositivo(Request req, Response res) {
		id=Integer.parseInt(req.params(":idCliente"));
		cadena=req.params(":idCliente");
		String tipo=req.queryParams("tipo");
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("id", cadena ); 
		if(tipo != null && tipo.equals("inteligente")) {
			return new ModelAndView(
					viewModel, 
					"Inteligente.hbs");
		}
		else {
			return new ModelAndView(
					viewModel, 
					"estandars.hbs");
		}		
	}
	
	public static Void registrarDispo(Request req, Response res) {	
		String id=req.params("idCliente");	
		String tipo=req.queryParams("tipo"); 
		
		if(tipo.equals("inteligente")) {
			DispositivoInteligente d=new DispositivoInteligente(req.queryParams("nombreDispo"),LocalDate.now(),
					new Fabricante("Sony",new Sony()),  Double.parseDouble(req.queryParams("minima")), Double.parseDouble(req.queryParams("maxima")) );
			clienteSeleccionado.agregarDispositivo(d);
		}
		else {
			DispositivoEstandar d=new DispositivoEstandar(req.queryParams("nombreDispo"),Double.parseDouble(req.queryParams("kw")),
					Double.parseDouble(req.queryParams("hsUso")),new Fabricante("Sony",new Sony()),  
					Double.parseDouble(req.queryParams("minima")), Double.parseDouble(req.queryParams("maxima")) );
			
			clienteSeleccionado.agregarDispositivo(d);
		}
		res.redirect("/admin/Clientes/"+id+"");
		return null;
	}
	
	
	public static Void logOut(Request req, Response res) {

		repo.addInstanceToDB(Cliente.class, clienteSeleccionado);

		req.session().removeAttribute("username");
		res.redirect("/login/loginView.html"); 
		return null;
	}
	
}
