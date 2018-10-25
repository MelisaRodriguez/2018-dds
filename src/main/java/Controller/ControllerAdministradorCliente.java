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
		
		Long id=Long.parseLong(req.params(":idCliente"));//ID  le pego repo conseguir user x id 
		
		//Cliente cliente=Repo.BuscarCliente(id);
		
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		

		viewModel.put("id", id);
		
		//viewModel.put("cliente", cliente);
		
		return new ModelAndView(
				viewModel, 
				"AdministradorCliente.hbs");
	}
}
