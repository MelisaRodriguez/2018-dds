package Server;

import spark.Spark;

public class Server {
	public static void main(String[] args) {
		Bootstrap.init();

		Spark.port(4444);

		Router.configure();

	}
}
