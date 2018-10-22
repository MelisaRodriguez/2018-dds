package Server;


import Controller.ControllerAdministrador;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
			
		
		Spark.get("/", ControllerAdministrador::index, transformer);		
		
		
	}
	
}
