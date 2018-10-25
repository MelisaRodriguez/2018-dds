package Controller;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAdministradorClienteDisp {
	public static ModelAndView index(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		Long.parseLong(req.params(":idCliente/Dispositivos"));//ID  le pego repo conseguir user x id 

		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		

		
		return new ModelAndView(
				viewModel, 
				"Untitled.hbs");
	}
}
