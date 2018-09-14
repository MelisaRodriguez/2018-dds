package edu.usuario.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.empresa.Administrador;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.RegistroMedicion;
import edu.dominio.empresa.Transformador;
import edu.dominio.empresa.ZonaGeografica;
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
import edu.repositorios.RepoZonaGeografica;
import junit.framework.Assert;

public class PersistenciaTests extends PersistenciaFixture{
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCasoDePrueba1() {
		Fabricante sony=new Fabricante("Sony",new Sony());
		DispositivoInteligente dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), sony, 0, 0);
		
		List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
		dispositivosInteligentes.add(dispositivoInteligente);
		
		DispositivoEstandar dispositivoEstandar = new DispositivoEstandar ("Licuadora", 6, 3, sony, 2, 10);
		
		List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
		dispositivosEstandares.add(dispositivoEstandar);
		
		Cliente unCliente = new Cliente ("Fede", "Perez", TipoDocumento.DNI ,"41919911", "23456379", "De la puerta para adentro", LocalDate.of(2018, 5, 20), dispositivosInteligentes, dispositivosEstandares, false, new Punto(-0.127512 , 51.507222) ) ;
		
		manager.getTransaction().begin();
		manager.persist(unCliente);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		Cliente supuestamenteElMismoCliente = manager.find(Cliente.class, unCliente.getId());
		
		supuestamenteElMismoCliente.setUbicacion(new Punto (0.127512 ,- 51.507222));
		
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		
		Cliente supuestamenteElMismoClienteModificado = manager.find(Cliente.class, unCliente.getId());
		
		Assert.assertEquals(supuestamenteElMismoClienteModificado, supuestamenteElMismoCliente);
		manager.getTransaction().commit();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCasoDePrueba2() {
		Fabricante sony=new Fabricante("Sony",null);
		DispositivoInteligente dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), sony, 0, 0);
		
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
		DispositivoInteligente televisor=manager.find(DispositivoInteligente.class, dispositivoInteligente.getId());
		List<RegistroMedicion> registrosEncontrados=televisor.getRegistrosConsumo();
		registrosEncontrados.stream().forEach(r->System.out.println(r.toString()));
		
		televisor.setNombre("Plasma");
		
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		DispositivoInteligente plasma=manager.find(DispositivoInteligente.class, dispositivoInteligente.getId());
		
		Assert.assertEquals("Plasma", plasma.getNombre() );
		manager.getTransaction().commit();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCasoDePrueba3() {
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
		Regla reglaPersistida = manager.find(Regla.class, regla.getIdRegla()); // Recuperamos la regla
		System.out.println(reglaPersistida.getIdRegla()); // Verificamos que se haya persitida la regla.
		reglaPersistida.ejecutar(); // Ejecutamos la regla
		Condicion condicionPersistida = reglaPersistida.getCondiciones().get(0);
		// Modificamos una condición.
		condicionPersistida.setOperador(Operador.MAYOR_IGUAL);
		condicionPersistida.setValor(25);
		manager.getTransaction().commit(); // Volvemos a persistir.
		
		manager.getTransaction().begin();
		reglaPersistida = manager.find(Regla.class, regla.getIdRegla()); // Recuperamos de nuevo la regla.
		condicionPersistida = reglaPersistida.getCondiciones().get(0);
		manager.getTransaction().commit();
		
		Assert.assertEquals(Operador.MAYOR_IGUAL + " 25.0", condicionPersistida.getOperador() + " " + condicionPersistida.getValor());
	}
	
	@Test
	public void testCasoDePrueba4() {
		EntityManager manager = PerThreadEntityManagers.getEntityManager();
		
		List<Transformador> transformadores;
		
		List<ZonaGeografica> zona = RepoZonaGeografica.getSingletonInstance().getEntidades();
		transformadores = zona.get(0).getTransformadores();
		
		manager.getTransaction().begin();
		transformadores.forEach(t -> manager.persist(t));
		manager.getTransaction().commit();
		
		List<Transformador> ts;
	
		manager.getTransaction().begin();
		ts = manager.createQuery("from Transformador", Transformador.class).getResultList();
		manager.getTransaction().commit();
		
		System.out.println(ts.get(0).toString());
		
		assertEquals(transformadores.size(), ts.size());
		
		Transformador t = new Transformador(new Punto(3.5, 4.7));
		transformadores.add(t);
		
		manager.getTransaction().begin();
		manager.persist(t);
		manager.getTransaction().commit();
		
		manager.getTransaction().begin();
		ts.add(manager.find(Transformador.class, t.getIdTransformador()));
		manager.getTransaction().commit();
		
		assertEquals(2, ts.size());
	}
	
	@Test
	public void testCasoDePrueba5() {
		LocalDate inicio = LocalDate.of(2017, 4, 28);
		LocalDate fin = LocalDate.of(2017, 5, 28);
		
		Fabricante sony=new Fabricante("Sony", fabricantemock);
		
		DispositivoInteligente aireAcondicionado = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2017, 4, 28), sony, 90.0, 360.0);

		ArrayList<RegistroMedicion> mediciones = new ArrayList<RegistroMedicion>();
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 29), 10.0, 20));
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 30), 10.0, 20));
		aireAcondicionado.setRegistrosConsumo(mediciones);
		
		ArrayList<DispositivoInteligente> inteligentes = new ArrayList<DispositivoInteligente>();
		inteligentes.add(aireAcondicionado);
		
		DispositivoEstandar lavarropas = new DispositivoEstandar("Lavarropas", 0.1275, 5, sony, 6.0, 30.0);

		ArrayList<DispositivoEstandar> dispositivosEstandar = new ArrayList<DispositivoEstandar>();
		dispositivosEstandar.add(lavarropas);
		
		Cliente cliente = new Cliente("Jorge", "Perez", TipoDocumento.DNI, "1111", "4444", "Nazca 156", LocalDate.of(2017, 4, 28), inteligentes, dispositivosEstandar, true,new Punto(-0.127512, 51.507222));
	
		Transformador t = new Transformador();
		t.setClientes(new ArrayList<Cliente>());
		t.getClientes().add(cliente);
		t.setLugar(new Punto(4,5));
		
		manager.getTransaction().begin();
		manager.persist(t); 
		manager.getTransaction().commit();
		
		//Dado un hogar y un período, mostrar por consola (interfaz de comandos) el consumo total.
		manager.getTransaction().begin();
		cliente = manager.find(Cliente.class, cliente.getId());
		System.out.println("Consumo hogar = " + cliente.consumoTotalEnPeriodo(inicio, fin));
		manager.getTransaction().commit();
		
		//Dado un dispositivo y un período, mostrar por consola su consumo promedio.
		manager.getTransaction().begin();
		aireAcondicionado = manager.find(DispositivoInteligente.class, cliente.getDispositivosInteligentes().get(0).getId());
		System.out.println("Promedio consumo dispositivo = " + aireAcondicionado.consumoTotalEnPeriodo(inicio, fin)/aireAcondicionado.getRegistrosConsumo().size());
		manager.getTransaction().commit();
		
		//Dado un transformador y un período, mostrar su consumo promedio.
		manager.getTransaction().begin();
		Transformador transformador = manager.find(Transformador.class, t.getIdTransformador());
		System.out.println("NOMBRE: " + transformador.getClientes().get(0).getNombre()); //TODO
		System.out.println("SIZE: " + transformador.getClientes().get(0).cantRegistrosMedicion()); // TODO
		System.out.println("Promedio consumo transformador = " + transformador.consumoTotalEnPeriodo(inicio, fin)/transformador.getClientes().get(0).dispositivosInteligentes().get(0).getRegistrosConsumo().size());
		manager.getTransaction().commit();
		
		//Recuperar un dispositivo asociado a un hogar de ese transformador e incrementar un 1000 % el consumo para ese período.
		manager.getTransaction().begin();
		transformador = manager.find(Transformador.class, t.getIdTransformador());
		transformador.getClientes().get(0).getDispositivosInteligentes().get(0).getRegistrosConsumo().stream().forEach(res -> res.setKwConsumidos(res.getKwConsumidos() * 10));
		manager.getTransaction().commit();
		
		//Nuevamente mostrar el consumo para ese transformador.
		manager.getTransaction().begin();
		transformador = manager.find(Transformador.class, t.getIdTransformador());
		System.out.println("Consumo final: " + transformador.consumoTotalEnPeriodo(inicio, fin));
		manager.getTransaction().commit();
	}
}