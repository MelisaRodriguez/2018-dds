package Group10.TP_Anual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONReader {

	public static List<Cliente> leerJson()
	{
		Gson gson = new Gson();
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader("Clientes.json"));
			JSON clientes = gson.fromJson(br, JSON.class);
			for(Cliente c: clientes.getClientes())
			{
				System.out.println("DATOS DEL CLIENTE");
				System.out.println("=================");
				System.out.println(c.toString());
				System.out.println("=================");
				System.out.println("DISPOSITIVOS");
				System.out.println();
				for(Dispositivo d: c.getDispositivos())
				{
					System.out.println("Nombre generico: " + d.nombreGenerico() + " | Encendido: " + d.estaEncendido() + " | Consumo: " + d.kwConsumoxHora() + "kW/h");
				}
				System.out.println();
				
			}
			return clientes.getClientes();
			
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

class JSON {
	
	@SerializedName("clientes")
	@Expose
	private List<Cliente> clientes = null;
	
	public List<Cliente> getClientes() {
		return clientes;
		}
}
