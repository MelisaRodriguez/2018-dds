package Controller;

import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.RegistroMedicion;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.TipoDocumento;
import spark.ModelAndView;

public class UserController {

	// Borrar después de que esté implementado el login!
	public static Cliente inicializarCliente()
	{
		LocalDate inicio = LocalDate.of(2017, 4, 28);
		LocalDate fin = LocalDate.of(2017, 5, 28);
				
		Sony fabricantemock = new Sony();
		
		Fabricante sony = new Fabricante("Sony", fabricantemock);

		DispositivoInteligente aireAcondicionado = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2017, 4, 28), sony, 90.0, 360.0);

		ArrayList<RegistroMedicion> mediciones = new ArrayList<RegistroMedicion>();
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 29), 10.0, 20));
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 30), 10.0, 20));
		aireAcondicionado.setRegistrosConsumo(mediciones);
		
		ArrayList<DispositivoInteligente> inteligentes = new ArrayList<DispositivoInteligente>();
		inteligentes.add(aireAcondicionado);
		
		ArrayList<DispositivoEstandar> estandar = new ArrayList<DispositivoEstandar>();
		return new Cliente("Jorge", "Perez", TipoDocumento.DNI, "1111", "4444", "Nazca 156", LocalDate.of(2017, 4, 28), inteligentes, estandar, true,new Punto(-0.127512, 51.507222), "usuario123"); 
	}
	
	
	public static ModelAndView user(Request req, Response res) {
		
		//Cliente hardcodeado, acá reemplazar por el usuario obtenido del login!
		Cliente cliente = inicializarCliente();
		List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		dispositivos = cliente.todosSusDispositivos();
		HashMap<String,Object> viewModel = new HashMap<>(); 
		viewModel.put("cliente", cliente);
		viewModel.put("dispositivos", dispositivos);
		
		return new ModelAndView(viewModel, "user.hbs");
		
	}
}
