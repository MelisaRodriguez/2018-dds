package edu.usuario.test;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Regla;
import edu.dominio.usuario.Sensor;

public class PersistenciaTests {
	
	public class Samsung extends Fabricante
	{
		public Samsung () {}
		
		public Samsung(String nombre)
		{
			this.nombre = nombre;
		}
		@Override
		public void apagar() {
			//MOCK
		}

		@Override
		public void encender() {
			//MOCK
		}

		@Override
		public void activarAhorroDeEnergia() {
			//MOCK
		}
		
		@Override
		public double cuantoConsume() {
			return 1.0;
			//MOCK
		}
		
		@Override
		public boolean estaEncendido() {
			return true;
			//MOCK
		}
		
		@Override
		public boolean estaApagado() {
			return true;
			//MOCK
		}
		
		@Override
		public boolean estaModoAhorroEnergia() {
			return true;
			//MOCK
		}

		@Override
		public double getPotencia() {
			return 1.0;
			// MOCK
		}
		
		@Override
		public double getHorasEncendido() {
			return 1.0;
			// MOCK
		}
	}
	@Test
	public void ReglaTest() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("db");
		EntityManager manager = fabrica.createEntityManager();
		
		manager.getTransaction().begin();
		
		// Creamos un dispositivo al cual se le va a aplicar la regla
		Fabricante fabricante = new Samsung();
		DispositivoInteligente dispositivo = new DispositivoInteligente("Smartphone", LocalDate.now(), fabricante, 150.0, 300.0);
		
		// Creamos un sensor y tomamos una medicion
		Sensor sensor = new Sensor();
		sensor.tomarMedicion(25);
		
		// Creamos una lista con una condicion
		
		Condicion condicion = new Condicion(sensor, (Double valor) -> {return valor > 20;});
		
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(condicion);
		
		// Creamos una lista con un actuador, ASOCIANDOLA al dispositivo.
		
		Actuador actuador = new Actuador(dispositivo);
		
		List<Actuador> actuadores = new ArrayList<Actuador>();
		actuadores.add(actuador);
		
		// Creamos la regla y le asociamos las condiciones y los actuadores.
		Regla regla = new Regla(condiciones,actuadores);
		
		manager.persist(regla);
		
		manager.getTransaction().commit();
		manager.close();
		
		System.out.println("Comienza el select");
		
		manager.getTransaction().begin();
		
		Regla regla2 = manager.find(Regla.class, 1);
		
		System.out.println(regla2.toString());
	}
	
}
