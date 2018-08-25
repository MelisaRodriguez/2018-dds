package edu.empresa.test;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Before;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.Simplex;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.TipoDocumento;

import static org.mockito.Mockito.*;

public class SimplexFixture {
	protected DispositivoInteligente aireAcondicionado;
	protected DispositivoInteligente televisor;
	protected DispositivoEstandar lavarropas;
	protected DispositivoInteligente ventilador;
	protected DispositivoInteligente lampara;
	protected DispositivoInteligente pc;
	protected DispositivoEstandar microondas;
	protected DispositivoEstandar plancha;
	
	protected Cliente cliente;
	protected Cliente cliente2;
	protected Simplex llamarSimplex;
	
	protected Fabricante fabricante;
	protected Fabricante fabricanteAireAcondicionado;
	protected Fabricante fabricanteLampara;
	protected Fabricante fabricanteTelevisor;
	protected Fabricante fabricantePC;
	protected Fabricante fabricanteVentilador;
	protected Fabricante fabricanteHeladera;
	
	protected Heladera heladera;
	
	protected ArrayList<DispositivoEstandar> dispositivosEstandar;
	protected ArrayList<DispositivoInteligente> inteligentes;
	protected ArrayList<DispositivoInteligente> inteligentes2;
	
	class Heladera extends DispositivoInteligente{

		public Heladera(String nombre, LocalDate fechaDeRegistro, Fabricante fabricante, double restriccionMinima,
				double restriccionMaxima) {
			super(nombre, fechaDeRegistro, fabricante, restriccionMinima, restriccionMaxima);
		}
		
		@Override
		public void accionar()
		{
			this.apagarse();
		}
	}
	@Before
	public void fixture() {
		fabricante = mock(Fabricante.class);
		fabricanteAireAcondicionado = mock(Fabricante.class);
		fabricanteLampara = mock(Fabricante.class);
		fabricanteTelevisor = mock(Fabricante.class);
		fabricantePC = mock(Fabricante.class);
		fabricanteVentilador = mock(Fabricante.class);
		
		heladera = mock(Heladera.class);
		aireAcondicionado = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2017, 4, 28), fabricanteAireAcondicionado, 90.0, 360.0);
		lampara = new DispositivoInteligente("Lampara", LocalDate.of(2017, 4, 28), fabricanteLampara, 90.0, 360.0);
		televisor = new DispositivoInteligente("Televisor", LocalDate.of(2017, 4, 28), fabricanteTelevisor, 90.0, 360.0);
		pc = new DispositivoInteligente("PC", LocalDate.of(2017, 4, 28), fabricantePC, 90.0, 360.0);
		lavarropas = new DispositivoEstandar("Lavarropas", 0.1275, 5, fabricante, 6.0, 30.0);
		microondas = new DispositivoEstandar("Microondas", 0.64, 5, fabricante, 3.0, 15.0);
		plancha = new DispositivoEstandar("Plancha", 0.75, 5, fabricante, 3.0, 30.0);
		ventilador = new DispositivoInteligente("Ventilador", LocalDate.of(2017, 4, 28), fabricanteVentilador, 120.0, 360.0);

		
		dispositivosEstandar = new ArrayList<DispositivoEstandar>();
		dispositivosEstandar.add(lavarropas);
		dispositivosEstandar.add(microondas);
		dispositivosEstandar.add(plancha);
		
		inteligentes = new ArrayList<DispositivoInteligente>();
		inteligentes.add(aireAcondicionado);
		inteligentes.add(lampara);
		inteligentes.add(televisor);
		inteligentes.add(pc);
		inteligentes.add(ventilador);
		
		inteligentes2 = new ArrayList<DispositivoInteligente>();
		inteligentes2.add(heladera);
		
		cliente = new Cliente("Melisa", "Rodriguez", TipoDocumento.DNI, "1111", "4444", "Nazca 156", LocalDate.of(2017, 4, 28), inteligentes, dispositivosEstandar, true,new Punto(-0.127512, 51.507222));	
		cliente2 = new Cliente("Facundo", "Fraguaga", TipoDocumento.DNI, "1110", "464", "Calle Falsa 123", LocalDate.of(2017,4,27),inteligentes2,dispositivosEstandar, true,new Punto(-0.127512, 51.507222));

		llamarSimplex = new Simplex(612);
	}
}