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
		
		//Spark.path(path, routeGroup);
		
		/*
		path("/api", () -> {
		    before("/*", (q, a) -> log.info("Received api call"));
		    path("/email", () -> {
		        post("/add",       EmailApi.addEmail);
		        put("/change",     EmailApi.changeEmail);
		        delete("/remove",  EmailApi.deleteEmail);
		    });
		    path("/username", () -> {
		        post("/add",       UserApi.addUsername);
		        put("/change",     UserApi.changeUsername);
		        delete("/remove",  UserApi.deleteUsername);
		    });
		});
		*/
		
		
		
		
		Spark.get("/", LoginController::init);
		
		Spark.post("/", LoginController::processLogin);			
		
		Spark.get("/admin", ControllerAdministrador::indexViewDatosGenerales, transformer);	
		Spark.get("/admin/Clientes/:idCliente", ControllerAdministrador::indexViewDatosDeUnCliente, transformer);	
		Spark.get("/admin/clientes/:idCliente/dispositivos", ControllerAdministrador::indexViewAgregarDispositivo, transformer);	
		Spark.post("/admin/clientes/:idCliente/dispositivos", ControllerAdministrador::registrarDispo);
		
		Spark.post("/admin/out", ControllerAdministrador::logOut);	
		
		
		
		
		
		
		Spark.get("/userPanel", UserController::user, transformer);	
		Spark.get("/consumoRecomendado", UserController::consumoRecomendado, transformer);
		Spark.post("/consumoEnPeriodo", UserController::consumoEnPeriodo, transformer);
	}
}

