package Controller;

import java.util.HashMap;

import model.Indicador;
import model.Usuario;
import model.Builders.IndicadorBuilder;
import model.Excepciones.DuplicateExecption;
import model.Excepciones.ParserException;
import model.Excepciones.RecursiveException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ControllerAdministradorClienteDisp {
	public static ModelAndView index(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		
		Long id=Long.parseLong(req.params(":idCliente"));//ID  le pego repo conseguir user x id 
		
		
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("wea", id);

		
		return new ModelAndView(
				viewModel, 
				"Inteligente.hbs");
	}
	
	public Void registrarDispoInt(Request req, Response res) {
		
		res.
		
		
		
		res.redirect("/../");
		return null;
	}
	
	
}
