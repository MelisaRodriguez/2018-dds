package main.server;

import main.controller.ControllerAdministrador;
import main.controller.LoginController;
import main.controller.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();

		Spark.staticFiles.location("/public");

		Spark.before("/admin", LoginController::validarLogin);
		Spark.before("/userPanel", LoginController::validarLogin);
		Spark.before("/consumoRecomendado", LoginController::validarLogin);
		Spark.before("/consumoEnPeriodo", LoginController::validarLogin);

		Spark.get("/", LoginController::init);

		Spark.post("/login", LoginController::processLogin);

		Spark.path("/admin", () -> { 
		Spark.get("", ControllerAdministrador::indexViewDatosGenerales, transformer);	
		Spark.get("/Clientes/:idCliente", ControllerAdministrador::indexViewDatosDeUnCliente, transformer);	
		Spark.get("/clientes/:idCliente/dispositivos", ControllerAdministrador::indexViewAgregarDispositivo, transformer);	
		Spark.post("/clientes/:idCliente/dispositivos", ControllerAdministrador::registrarDispo);
		});
		
		Spark.get("/out", ControllerAdministrador::logOut, transformer);

		Spark.get("/userPanel", UserController::user, transformer);
		Spark.get("/consumoRecomendado", UserController::consumoRecomendado, transformer);
		Spark.post("/consumoEnPeriodo", UserController::consumoEnPeriodo, transformer);
		
	}
}
