package main.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.usuario.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {

	public static ModelAndView user(Request req, Response res) {
		Cliente usuario = req.session().attribute("Cliente");
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("cliente", usuario);
		viewModel.put("dispositivosInteligentes", usuario.getDispositivosInteligentes());
		viewModel.put("dispositivosEstandar", usuario.getDispositivosEstandar());
		return new ModelAndView(viewModel, "user.hbs");
	}

	public static ModelAndView consumoRecomendado(Request req, Response res) {
		Cliente usuario = req.session().attribute("Cliente");
		HashMap<String, Object> viewModel = new HashMap<>();
		HashMap<String, Double> recomendaciones = new HashMap<>();

		List<Double> optimizaciones = usuario.solicitarRecomendacion(620);
		int i = 0;
		for (Dispositivo dispositivo : usuario.todosSusDispositivos()) {
			recomendaciones.put(dispositivo.getNombre(), optimizaciones.get(i));
			i++;
		}

		viewModel.put("recomendaciones", recomendaciones);
		viewModel.put("cliente", usuario);

		return new ModelAndView(viewModel, "simplex.hbs");
	}

	public static ModelAndView consumoEnPeriodo(Request req, Response res) {

		Cliente usuario = req.session().attribute("Cliente");
		HashMap<String, Object> viewModel = new HashMap<>();
		String[] fechas = req.queryParams("periodo").split(" - ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

		double consumo = usuario.getConsumoTotalEnPeriodo(LocalDate.parse(fechas[0], formatter),
				LocalDate.parse(fechas[1], formatter));

		viewModel.put("cliente", usuario);
		viewModel.put("consumo", consumo);

		return new ModelAndView(viewModel, "consumo.hbs");
	}

	public static ModelAndView logOut(Request req, Response res) {
		req.session().removeAttribute("username");
		PerThreadEntityManagers.getEntityManager().remove(req.session().attribute("Cliente"));
		req.session().removeAttribute("Cliente");
		res.redirect("/");
		return null;
	}
}
