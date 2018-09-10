package edu.usuario.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

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
		
		Condicion condicion = new Condicion(sensor, (Double valor) -> {return valor > 20;});
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(condicion);
		
		// Creamos una lista con un actuador, ASOCIANDOLA al dispositivo.
		
		Actuador actuador = new Actuador(dispositivo);
		List<Actuador> actuadores = new ArrayList<Actuador>();
		actuadores.add(actuador);
		
		// Creamos la regla y le asociamos las condiciones y los actuadores.
		Regla regla = new Regla(condiciones,actuadores);
		
		manager.getTransaction().begin();
		manager.persist(regla);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		Regla reglaPersist = manager.find(Regla.class, 1);
		System.out.println("ID" + reglaPersist.getIdRegla()); // <-- No va, solo para verificar y seguir testeando.
		reglaPersist.ejecutar();
		reglaPersist.getCondiciones().get(0).setCondicionLogica((Double valor) -> {return valor >23;});
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		// Ver condicion modificada;
		manager.getTransaction().commit();
		manager.close();
		
		/* NOTA: Rompe el test, porque no sabe como convertir la Function en un dato posible para una tabla en
		 * SQL. Esto es normal, y era bastante predecible. Se me ocurrió cambiar la clase Condicion.
		 * Para esto haría @Transient la Function, porque no la vamos a poder persistir, pero sí dejarla en el dominio
		 * de objetos para poder ejecutarla con el apply(). La modificación sería agregar un valor numérico que
		 * sea el que haya que superar o no alcanzar para cumplir una condición, (por ejemplo, el número 20 si la
		 * temperatura tiene que ser mayor a 20) y un enumerado (una cagada) para el operador de comparación,
		 *  (>, <, >= o <=). Luego hacer dos métodos setters, uno para el enum y otro para el operador que se podría
		 *  ingresar como un String, ejemplo: ">", y un ultimo metodo set que es el de armar la Function según
		 *  esos valores ingresados, algo así como
		 *  
		 *  setCondicionLogica()
		 *  { 	
		 *  	this.condicionLogica =  (Double valor) -> {return valor this.operador this.numero;} 
		 *  }
		 *  y convirtiendo el operador ">" al > posta, que acá no sé que hacer, esta es la complicación posta.
		 *  (y este mismo método sirve para hacer la modificación en el test).
		 *  Aclaración: estos dos atributos serían persistibles, y habría que agregar la unidad: "Temperatura", 
		 *  
		 *  Después no afecta a ningún lado del dominio.
		 * */
	}
	
}
