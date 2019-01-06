package main.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteAdapter;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AdministradorController {

	public static ModelAndView indexViewDatosGenerales(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();
		List<Cliente> clientes = RepoClientes.getInstanceOfSingleton().getEntidades();
		viewModel.put("user", clientes);
		return new ModelAndView(viewModel, "Administrador.hbs");
	}

	public static ModelAndView indexViewDatosDeUnCliente(Request req, Response res) {
		int id = Integer.parseInt(req.params(":idCliente"));
		Cliente clienteSeleccionado = RepoClientes.getInstanceOfSingleton().findByID(id);
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("id", clienteSeleccionado.getId());
		viewModel.put("cliente", clienteSeleccionado);
		return new ModelAndView(viewModel, "AdministradorCliente.hbs");
	}

	public static ModelAndView indexViewAgregarDispositivo(Request req, Response res) {
		String cadena = req.params(":idCliente");
		String tipo = req.queryParams("tipo");
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("id", cadena);
		if (tipo != null && tipo.equals("inteligente")) {
			return new ModelAndView(viewModel, "Inteligente.hbs");
		} else {
			return new ModelAndView(viewModel, "estandars.hbs");
		}
	}

	public static Void registrarDispo(Request req, Response res) {
		String id = req.params("idCliente");
		String tipo = req.queryParams("tipo");

		Cliente clienteSeleccionado = RepoClientes.getInstanceOfSingleton().findByID(Integer.parseInt(id));

		if (req.queryParams("nombreDispo").length() == 0) {
			// aca pondria cosas para hacer un pop up que dice que no ingreso nombre
			// Si supiera javascript para hacerlo
			return null;
		}

		if (tipo.equals("inteligente")) {
			DispositivoInteligente d = new DispositivoInteligente(req.queryParams("nombreDispo"), LocalDate.now(),
					new Fabricante("Sony", FabricanteAdapter.SONY), Double.parseDouble(req.queryParams("minima")),
					Double.parseDouble(req.queryParams("maxima")));
			clienteSeleccionado.agregarDispositivo(d);
		} else {
			DispositivoEstandar d = new DispositivoEstandar(req.queryParams("nombreDispo"),
					Double.parseDouble(req.queryParams("kw")), Double.parseDouble(req.queryParams("hsUso")),
					new Fabricante("Sony", FabricanteAdapter.SONY), Double.parseDouble(req.queryParams("minima")),
					Double.parseDouble(req.queryParams("maxima")));
			clienteSeleccionado.agregarDispositivo(d);
		}
		RepoClientes.getInstanceOfSingleton().agregar(clienteSeleccionado);
		res.redirect("/admin/Clientes/" + id + "");
		return null;
	}

}
