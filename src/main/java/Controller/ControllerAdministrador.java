package Controller;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.base.Optional;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import edu.dominio.empresa.Administrador;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.RegistroMedicion;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.TipoDocumento;
import edu.repositorios.RepoAdministrador;
import edu.repositorios.RepoZonaGeografica;
import edu.usuario.test.ClienteFixture;
import edu.usuario.test.PersistenciaFixture;
import edu.usuario.test.PersistenciaFixture.Sony;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerAdministrador {
	
	public static Cliente inicializarCliente()
	{
		LocalDate inicio = LocalDate.of(2017, 4, 28);
		LocalDate fin = LocalDate.of(2017, 5, 28);
		DispositivoInteligente aireAcondicionado = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2017, 4, 28), null, 90.0, 360.0);
 		ArrayList<RegistroMedicion> mediciones = new ArrayList<RegistroMedicion>();
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 29), 10.0, 20));
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 30), 10.0, 20));
		aireAcondicionado.setRegistrosConsumo(mediciones);
		
		ArrayList<DispositivoInteligente> inteligentes = new ArrayList<DispositivoInteligente>();
		inteligentes.add(aireAcondicionado);
		
		ArrayList<DispositivoEstandar> estandar = new ArrayList<DispositivoEstandar>();
		return new Cliente("Jorge", "Perez", TipoDocumento.DNI, "1111", "4444", "Nazca 156", LocalDate.of(2017, 4, 28), inteligentes, estandar, true,new Punto(-0.127512, 51.507222)); 
	}
	
	
	public static ModelAndView index(Request req, Response res) {
		
		ArrayList<Cliente> clientes=new ArrayList<Cliente>();
		clientes.add(inicializarCliente());
		
		HashMap<String, Object> viewModel = new HashMap<>();
		
		
		double consumo=clientes.get(0).consumoTotal();
		
		
		viewModel.put("consumo", consumo);
		
		viewModel.put("user", clientes);
		
		return new ModelAndView(
				viewModel, 
				"Untitled.hbs");
	}
}
