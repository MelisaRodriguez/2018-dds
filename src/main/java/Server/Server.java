package Server;


import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Bootstrap.init();
		Spark.port(9000);
		
		DebugScreen.enableDebugScreen();
		
		Router.configure();
		
		//Spark.get("/hello", (req, res) -> "Hello World");
		
	}
}
