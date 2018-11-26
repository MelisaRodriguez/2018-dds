package main.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import spark.Spark;
import spark.debug.DebugScreen;


public class Server {

	public static void main(String[] args) {
		File archivo=new File("./Logs.log");			
		if(archivo.exists()) 
		{
			try 
			{
				Bootstrap.init();
				DebugScreen.enableDebugScreen();
				Spark.port(4444);
				Router.configure();
				escribirLog("./Logs.log","El servidor inició correctamente.");
			}
			catch(Exception e) {
				escribirLog("./Logs.log","No se ha podido iniciar el servidor. CAUSA: "+ e);
			}
		}
		else {
			System.out.println("No existe archivo");
		}
	}
	
	public static void escribirLog(String archivo,String cadena) {
		try {
			FileWriter fw=new FileWriter(archivo,true);
			//DateFormat Formato = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
			fw.write(LocalDateTime.now() + ": " + cadena+"\r\n");
			fw.close();
		}
		catch(IOException x) {
			x.printStackTrace();
		}
	}
	
}
