package Controller;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ControllerAdministradorClienteDisp {
	public static ModelAndView index(Request req, Response res) {
		///EN EL OTRO CONTROLLER
	//Long id=Long.parseLong(req.params(":idCliente/DispositivosInteligentes"));//ID  le pego repo conseguir user x id 

		System.out.println("CONOOOOOOO 80");
		
		int id=80;
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("wea", id);

		
		return new ModelAndView(
				viewModel, 
				"Inteligente.hbs");
	}
}
