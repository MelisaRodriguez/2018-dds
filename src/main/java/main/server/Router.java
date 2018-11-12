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
