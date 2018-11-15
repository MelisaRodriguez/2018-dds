package main.server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Bootstrap.init();

		DebugScreen.enableDebugScreen();
		
		Spark.port(4444);

		Router.configure();

	}
}
