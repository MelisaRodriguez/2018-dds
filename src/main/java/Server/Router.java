package Server;

import Controller.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;



public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = new HandlebarsTemplateEngine();
		
		Spark.staticFiles.location("/public");	
		
		Spark.get("/", UserController::user, transformer);	// Hay que cambiar por el INDEX de la página!!!	
		
		
	}
}
