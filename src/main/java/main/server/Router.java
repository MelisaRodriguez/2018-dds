package main.server;

import main.controller.AdministradorController;
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
			Spark.get("", AdministradorController::indexViewDatosGenerales, transformer);
			Spark.get("/Clientes/:idCliente", AdministradorController::indexViewDatosDeUnCliente, transformer);
			Spark.get("/clientes/:idCliente/dispositivos", AdministradorController::indexViewAgregarDispositivo,
					transformer);
			Spark.post("/clientes/:idCliente/dispositivos", AdministradorController::registrarDispo);
		});

		Spark.get("/userPanel", UserController::user, transformer);
		Spark.get("/userLogout", UserController::logOut, transformer);
		Spark.get("/consumoRecomendado", UserController::consumoRecomendado, transformer);
		Spark.post("/consumoEnPeriodo", UserController::consumoEnPeriodo, transformer);

	}
}
