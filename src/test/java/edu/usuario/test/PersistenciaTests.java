package edu.usuario.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.RegistroMedicion;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Operador;
import edu.dominio.usuario.Regla;
import edu.dominio.usuario.Sensor;
import edu.dominio.usuario.TipoDocumento;
import junit.framework.Assert;

public class PersistenciaTests {
	
	public class Sony implements FabricanteMock{

		public Sony() {}
		@Override
		public void apagar(DispositivoInteligente d) {}

		@Override
		public void encender(DispositivoInteligente d) {}

		@Override
		public void activarAhorroDeEnergia(DispositivoInteligente d) {}

		@Override
		public double cuantoConsume(DispositivoInteligente d) {
			return 1;
		}

		@Override
		public boolean estaEncendido(DispositivoInteligente d) {
			return true;
		}

		@Override
		public boolean estaApagado(DispositivoInteligente d) {
			return false;
		}

		@Override
		public boolean estaModoAhorroEnergia(DispositivoInteligente d) {
			return false;
		}

		@Override
		public double getPotencia(DispositivoInteligente d) {
			return 0;
		}

		@Override
		public double getHorasEncendido(DispositivoInteligente d) {
			return 0;
		}
	}
	
	public Sony s;
	
	@Test
	public void testCasoDePruebaUno() {
		
		Fabricante sony=new Fabricante("Sony",new Sony());
		DispositivoInteligente dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), sony, 0, 0);
		
		List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
		dispositivosInteligentes.add(dispositivoInteligente);
		
		DispositivoEstandar dispositivoEstandar = new DispositivoEstandar ("Licuadora", 6, 3, sony, 2, 10);
		
		List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
		dispositivosEstandares.add(dispositivoEstandar);
		
		Cliente unCliente = new Cliente ("Fede", "Perez", TipoDocumento.DNI ,"41919911", "23456379", "De la puerta para adentro", LocalDate.of(2018, 5, 20), dispositivosInteligentes, dispositivosEstandares, false, new Punto(-0.127512 , 51.507222) ) ;
		
		EntityManager manager = PerThreadEntityManagers.getEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(unCliente);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		Cliente supuestamenteElMismoCliente = manager.find(Cliente.class, 1);
		
		supuestamenteElMismoCliente.setUbicacion(new Punto (0.127512 ,- 51.507222));
		
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		
		Cliente supuestamenteElMismoClienteModificado = manager.find(Cliente.class, 1);
		
		Assert.assertEquals(supuestamenteElMismoClienteModificado, supuestamenteElMismoCliente);
		manager.getTransaction().commit();
		
		manager.close();
	}
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
	
	@Test
	public void testCasoDePruebaDos() {
		
		Fabricante sony=new Fabricante("Sony",null);
		DispositivoInteligente dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), sony, 0, 0);
		
		EntityManager manager = PerThreadEntityManagers.getEntityManager();
		
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
		
		manager.close();
	}
	
	@Test
	public void testCasoDePruebaTres() {
		
		EntityManager manager = PerThreadEntityManagers.getEntityManager();

		// Creamos un dispositivo al cual se le va a aplicar la regla
		Fabricante fabricante = new Fabricante ("Samsung",null);
		DispositivoInteligente dispositivo = new DispositivoInteligente("Smartphone", LocalDate.now(), fabricante, 150.0, 300.0);
		
		// Creamos un sensor y tomamos una medicion
		Sensor sensor = new Sensor();
		sensor.tomarMedicion(25);
		
		// Creamos una lista con una condicion
		
		Condicion condicion = new Condicion(sensor,Operador.MAYOR,20.0);
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(condicion);
		
		// Creamos una lista con un actuador, ASOCIANDOLA al dispositivo.
		
		Actuador actuador = new Actuador(dispositivo);
		List<Actuador> actuadores = new ArrayList<Actuador>();
		actuadores.add(actuador);
		
		// Creamos la regla y le asociamos las condiciones y los actuadores.
		Regla regla = new Regla(condiciones,actuadores);
		
		manager.getTransaction().begin();
		manager.persist(regla); // Persistimos la Regla
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		Regla reglaPersistida = manager.find(Regla.class, 1); // Recuperamos la regla
		System.out.println(reglaPersistida.getIdRegla()); // Verificamos que se haya persitida la regla.
		reglaPersistida.ejecutar(); // Ejecutamos la regla
		Condicion condicionPersistida = reglaPersistida.getCondiciones().get(0);
		// Modificamos una condición.
		condicionPersistida.setOperador(Operador.MAYOR_IGUAL);
		condicionPersistida.setValor(25);
		manager.getTransaction().commit(); // Volvemos a persistir.
		
		manager.getTransaction().begin();
		reglaPersistida = manager.find(Regla.class, 1); // Recuperamos de nuevo la regla.
		condicionPersistida = reglaPersistida.getCondiciones().get(0);
		manager.getTransaction().commit();
		manager.close();
		
		Assert.assertEquals(Operador.MAYOR_IGUAL + " 25.0", condicionPersistida.getOperador() + " " + condicionPersistida.getValor());
		
	}
	
}
