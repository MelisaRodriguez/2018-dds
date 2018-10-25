package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoClientes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAdministradorCliente {
	public static ModelAndView index(Request req, Response res) {
		///EN EL OTRO CONTROLLER
		
		int id=Integer.parseInt(req.params(":idCliente"));//ID  le pego repo conseguir user x id 
		
		//Cliente cliente=Repo.BuscarCliente(id);
		
		RepoClientes repo=new RepoClientes();
		Cliente buscado=repo.buscarPorId(id,Cliente.class);
		
		
		HashMap<String, Object> viewModel = new HashMap<>();
		

		viewModel.put("id", id);
		viewModel.put("cliente", buscado);
		viewModel.put("inteligentes", buscado.getDispositivosInteligentes());
		viewModel.put("estandares", buscado.getDispositivosEstandar());
		
		
		return new ModelAndView(
				viewModel, 
				"AdministradorCliente.hbs");
	}
}
