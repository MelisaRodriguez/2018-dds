package Server;


import Controller.ControllerAdministrador;
import Controller.LoginController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		
		Spark.staticFiles.location("/public");	
		
		Spark.get("/", LoginController::init);
		
		Spark.post("/", LoginController::processLogin);	
		
		Spark.get("/admin", ControllerAdministrador::indexPrimero, transformer);	
		
		Spark.get("/admin/Clientes/:idCliente", ControllerAdministrador::indexMedio, transformer);	
		
		Spark.get("/admin/Clientes/:idCliente/DispositivosInteligentes", ControllerAdministrador::indexUltimoInteligente, transformer);	
		Spark.post("/admin/Clientes/:idCliente/DispositivosInteligentes", ControllerAdministrador::registrarDispoInt);
		
		Spark.get("/admin/Clientes/:idCliente/DispositivosEstandar", ControllerAdministrador::indexUltimoEstandar, transformer);	
		Spark.post("/admin/Clientes/:idCliente/DispositivosEstandar", ControllerAdministrador::registrarDispoEst);
		
		Spark.post("/admin/out", ControllerAdministrador::logOut);	
	}
	
}
