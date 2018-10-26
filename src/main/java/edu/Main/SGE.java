package edu.Main;

import spark.Spark;

public class SGE {

	public static void main(String[] args) {
		Bootstrap.inicializar();
		
		Spark.staticFileLocation("public");
		
		Spark.port(4444);
		
		Spark.get("/", LoginController::init);
		
		Spark.post("/", LoginController::processLogin);	
	}

}
