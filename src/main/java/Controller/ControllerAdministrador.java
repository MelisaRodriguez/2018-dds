package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Optional;

import edu.dominio.empresa.Administrador;
import edu.repositorios.RepoAdministrador;
import edu.repositorios.RepoZonaGeografica;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAdministrador {
	public static ModelAndView index(Request req, Response res) {
		
		RepoAdministrador user = RepoAdministrador.getInstanceOfSingleton();

		//String apodo = req.queryParams("apodo");
		
		List<Administrador> administradores = 
				user.getEntidades();
		
		//HashMap<String, Object> viewModel  = new HashMap<>();
		
		Administrador admin = new Administrador("Gaston", "Prieto", "Mozart 1800 - CABA", 101, LocalDate.of(2017, 4, 28));

		
		ArrayList<Administrador> lalalla=new ArrayList<Administrador>();
		lalalla.add(admin);
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		//viewModel.put("nombre", apodo);
		viewModel.put("user", lalalla);
		
		
		
		
		
		return new ModelAndView(
				viewModel, 
				"Untitled.hbs");
	}
}
