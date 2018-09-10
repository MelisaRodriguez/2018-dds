package edu.usuario.test;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.RegistroMedicion;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Regla;
import edu.dominio.usuario.Sensor;
import junit.framework.Assert;

public class PersistenciaTests {
	
	/*public class Samsung extends Fabricante
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
	}*/
	
	/*
	@Test
	public void ReglaTest() {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("db");
		EntityManager manager = fabrica.createEntityManager();
		
		manager.getTransaction().begin();
		
		// Creamos un dispositivo al cual se le va a aplicar la regla
		Fabricante fabricante = new Fabricante ("Samsung",null);
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
	}*/
	
	@Test
	public void testCreacion() {
		
		Fabricante sony=new Fabricante("Sony",null);
		DispositivoInteligente dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), sony, 0, 0);
		
		EntityManager manager = PerThreadEntityManagers.getEntityManager();
		//EntityManager manager = fabrica.createEntityManager();
		
		RegistroMedicion registro1=new RegistroMedicion(LocalDate.of(2018, 5, 18),80,10 );
		RegistroMedicion registro2=new RegistroMedicion(LocalDate.of(2018, 5, 25),2000,22);
		
		List<RegistroMedicion> registros=new ArrayList<>();
		registros.add(registro1);
		registros.add(registro2);
		
		dispositivoInteligente.setRegistrosConsumo(registros);
		
		manager.getTransaction().begin();
		manager.persist(dispositivoInteligente);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		DispositivoInteligente televisor=manager.find(DispositivoInteligente.class, 1);
		List<RegistroMedicion> registrosEncontrados=televisor.getRegistrosConsumo();
		registrosEncontrados.stream().forEach(r->System.out.println(r.toString()));
		
		televisor.setNombre("Plasma");
		
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		DispositivoInteligente plasma=manager.find(DispositivoInteligente.class, 1);
		
		Assert.assertEquals("Plasma", plasma.getNombre() );
		manager.getTransaction().commit();
		
		
	}
	
}
