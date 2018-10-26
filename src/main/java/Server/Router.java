package Server;

import Controller.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;



public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();
		
		Spark.staticFiles.location("/public");	
		
		Spark.get("/userPanel/", UserController::user, transformer);	
		Spark.get("/userPanel/consumoRecomendado", UserController::consumoRecomendado, transformer);
		Spark.post("/userPanel/consumoEnPeriodo", UserController::consumoEnPeriodo, transformer);
		
	}
}
