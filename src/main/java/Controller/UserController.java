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
import edu.dominio.empresa.Simplex;
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
		Sony fabricantemock = new Sony();
		
		Fabricante sony = new Fabricante("Sony", fabricantemock);

		DispositivoInteligente aireAcondicionado = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2017, 4, 28), sony, 90.0, 360.0);
		DispositivoInteligente televisor = new DispositivoInteligente("Televisor", LocalDate.of(2017, 4, 28), sony, 90.0, 360.0);
		
		ArrayList<RegistroMedicion> medicionesAire = new ArrayList<RegistroMedicion>();
		medicionesAire.add(new RegistroMedicion(LocalDate.of(2017, 4, 29), 10.0, 20));
		medicionesAire.add(new RegistroMedicion(LocalDate.of(2017, 4, 30), 10.0, 20));
		aireAcondicionado.setRegistrosConsumo(medicionesAire);
		
		ArrayList<RegistroMedicion> medicionesTele = new ArrayList<RegistroMedicion>();
		medicionesTele.add(new RegistroMedicion(LocalDate.of(2017, 4, 30), 15.0, 20));
		televisor.setRegistrosConsumo(medicionesTele);
		
		ArrayList<DispositivoInteligente> inteligentes = new ArrayList<DispositivoInteligente>();
		inteligentes.add(aireAcondicionado);
		inteligentes.add(televisor);
		
		
		ArrayList<DispositivoEstandar> estandar = new ArrayList<DispositivoEstandar>();
		estandar.add(new DispositivoEstandar("Lampara", 10, 5, null, 0, 1550));
		return new Cliente("Jorge", "Perez", TipoDocumento.DNI, "1111", "4444", "Nazca 156", LocalDate.of(2017, 4, 28), inteligentes, estandar, true,new Punto(-0.127512, 51.507222), "usuario123"); 
	}
	
	
	public static Cliente usuario = inicializarCliente();
	
	public static ModelAndView user(Request req, Response res) {

		HashMap<String,Object> viewModel = new HashMap<>(); 

		viewModel.put("cliente", usuario);
		viewModel.put("dispositivosInteligentes", usuario.getDispositivosInteligentes());
		viewModel.put("dispositivosEstandar", usuario.getDispositivosEstandar());
		return new ModelAndView(viewModel, "user.hbs");
		
	}
	
	
	public static ModelAndView consumoRecomendado(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();
		List<OptimizacionRecomendada> dispositivos = new ArrayList<OptimizacionRecomendada>();
		dispositivos.add(new OptimizacionRecomendada("Aire Acondicionado", 500.0));
		dispositivos.add(new OptimizacionRecomendada("Smart TV", 400.0));
		viewModel.put("optimizacionRecomendada", dispositivos);
		return new ModelAndView(viewModel, "simplex.hbs");

	}

	public static ModelAndView consumoEnPeriodo(Request req, Response res) {

		HashMap<String, Object> viewModel = new HashMap<>();
		System.out.println(req.queryParams("periodo"));
		double consumo = usuario.getConsumoTotalEnPeriodo(LocalDate.of(2017, 1, 1), LocalDate.of(2018, 12, 31));
		viewModel.put("consumo", consumo);
		return new ModelAndView(viewModel, "consumo.hbs");

	}
}
