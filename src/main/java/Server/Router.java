package Server;


import Controller.ControllerAdministrador;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {

	public static void configure() {
		HandlebarsTemplateEngine transformer = 
				new HandlebarsTemplateEngine();
		
		
		ControllerAdministrador adminController=new ControllerAdministrador();
		
		Spark.staticFiles.location("/public");	
		
		Spark.get("/", ControllerAdministrador::indexPrimero, transformer);	
		
		Spark.get("/Clientes/:idCliente", ControllerAdministrador::indexMedio, transformer);	
		
		Spark.get("/Clientes/:idCliente/DispositivosInteligentes", ControllerAdministrador::indexUltimoInteligente, transformer);	
		Spark.post("/Clientes/:idCliente/DispositivosInteligentes", ControllerAdministrador::registrarDispoInt);
		
		Spark.get("/Clientes/:idCliente/DispositivosEstandar", ControllerAdministrador::indexUltimoEstandar, transformer);	
		Spark.post("/Clientes/:idCliente/DispositivosEstandar", ControllerAdministrador::registrarDispoEst);
		
		Spark.post("/out", ControllerAdministrador::logOut);	
	}
	
}
