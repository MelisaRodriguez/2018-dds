package Server;


import Controller.ControllerAdministrador;
import Controller.LoginController;
import Controller.UserController;
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
		
		
		
		
		Spark.get("/admin/clientes/:idCliente/dispositivos", ControllerAdministrador::indexUltimo, transformer);	
		Spark.post("/admin/clientes/:idCliente/dispositivos", ControllerAdministrador::registrarDispo);
		
		Spark.post("/admin/out", ControllerAdministrador::logOut);	
		
		Spark.get("/userPanel", UserController::user, transformer);	
		Spark.get("/consumoRecomendado", UserController::consumoRecomendado, transformer);
		Spark.post("/consumoEnPeriodo", UserController::consumoEnPeriodo, transformer);
	}
}

