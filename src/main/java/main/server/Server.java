package main.server;

import spark.Spark;
import spark.debug.DebugScreen;


import java.io.IOException;

import java.io.File;
import java.io.FileWriter;


public class Server {

	public static void main(String[] args) {
		File archivo=new File("./Logs.log");			
		if(archivo.exists()) 
		{
			try 
			{
				//int a=1/0;
				Bootstrap.init();
				DebugScreen.enableDebugScreen();
				Spark.port(4444);
				Router.configure();
			}
			catch(Exception e) {
				escribirLog("No se ha podido iniciar el servidor:\n"+e.getStackTrace().toString());
			}
		}
		else {
			System.out.println("No existe archivo");
		}
	}
	
	public static void escribirLog(String cadena) {
		try {
			FileWriter fw=new FileWriter("./Logs.log",true);
			fw.write(cadena+"\r\n");
			fw.close();
		}
		catch(IOException x) {
			x.printStackTrace();
		}
	}
	
}
