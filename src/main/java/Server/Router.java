package Server;


import Controller.ControllerAdministrador;
import Controller.ControllerAdministradorCliente;
import Controller.ControllerAdministradorClienteDisp;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		
		ControllerAdministrador adminController=new ControllerAdministrador();
		ControllerAdministradorCliente cliController=new ControllerAdministradorCliente();
		ControllerAdministradorClienteDisp disController=new ControllerAdministradorClienteDisp();
		
		Spark.staticFiles.location("/public");	
		
		Spark.get("/", ControllerAdministrador::index, transformer);	
		
		Spark.get("/Clientes/:idCliente", ControllerAdministradorCliente::index, transformer);	
		
		Spark.get("/Clientes/:idCliente/DispositivosInteligentes", ControllerAdministradorClienteDisp::index, transformer);	
		
		
		Spark.post("/Clientes/:idCliente/DispositivosInteligentes", ControllerAdministradorClienteDisp::registrarDispoInt);
	}
	
}
