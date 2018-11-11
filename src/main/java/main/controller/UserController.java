package main.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.usuario.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {

	public static Cliente usuario;

	public static ModelAndView user(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();

		viewModel.put("cliente", usuario);
		viewModel.put("dispositivosInteligentes", usuario.getDispositivosInteligentes());
		viewModel.put("dispositivosEstandar", usuario.getDispositivosEstandar());
		return new ModelAndView(viewModel, "user.hbs");

	}

	public static ModelAndView consumoRecomendado(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();

		List<Double> optimizaciones = usuario.solicitarRecomendacion(620);
		List<OptimizacionRecomendada> recomendaciones = new ArrayList<OptimizacionRecomendada>();

		int i = 0;
		for (Dispositivo dispositivo : usuario.todosSusDispositivos()) {
			recomendaciones.add(new OptimizacionRecomendada(dispositivo.getNombre(), optimizaciones.get(i)));
			i++;
		}

		viewModel.put("optimizacionRecomendada", recomendaciones);

		return new ModelAndView(viewModel, "simplex.hbs");

	}

	public static ModelAndView consumoEnPeriodo(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();
		String[] fechas = req.queryParams("periodo").split(" - ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");

		double consumo = usuario.getConsumoTotalEnPeriodo(LocalDate.parse(fechas[0], formatter),
				LocalDate.parse(fechas[1], formatter));

		viewModel.put("consumo", consumo);

		return new ModelAndView(viewModel, "consumo.hbs");

	}
}
