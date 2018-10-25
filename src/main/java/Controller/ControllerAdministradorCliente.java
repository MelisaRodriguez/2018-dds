package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import edu.dominio.usuario.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAdministradorCliente {
	public static ModelAndView index(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		Long.parseLong(req.params(":idCliente"));//ID  le pego repo conseguir user x id 

		
		
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		

		
		return new ModelAndView(
				viewModel, 
				"Untitled.hbs");
	}
}
