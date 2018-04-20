package Group10.TP_Anual;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Aplicacion {

	public static void main(String[] args) {
		List<Dispositivo> lista= leerArchivo("Dispositivo.txt");
	}
	
	public static List<Dispositivo> leerArchivo(String ruta){
		String json = readFileAsString(ruta);
		Type listType = new TypeToken<List<Dispositivo>>(){}.getType();
			
		List<Dispositivo> listaDeEmpresas = new Gson().fromJson(json, listType); 
	
		return listaDeEmpresas;
	}
	
	public static String readFileAsString(String fileName){
	    StringBuilder salida = new StringBuilder();
	    String line;
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader(fileName));
	        line = br.readLine();
	        while (line != null) {
	        	salida.append(line);
	            line = br.readLine();
	        }
	        br.close();
	    }
	    catch (IOException e) {
			throw new RuntimeException("Archivo no encontrado!");
	    	//e.printStackTrace();
		}
	    return salida.toString();
	}
}
