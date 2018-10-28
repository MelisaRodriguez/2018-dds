package Server;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import Server.Cifrado;
import Server.dummyUser;
import edu.dominio.empresa.Administrador;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.RegistroMedicion;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.TipoDocumento;
import edu.repositorios.RepoAdministrador;
import edu.repositorios.RepoClientes;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {

	public static void init() {

		// Precargamos la base con clientes.

		DispositivoInteligente aireAcondicionado1 = new DispositivoInteligente("Aire acondicionado",
				LocalDate.of(2018, 4, 28), new Fabricante("Sony", new Sony()), 90.0, 360.0);
		ArrayList<RegistroMedicion> medicionesAire = new ArrayList<RegistroMedicion>();
		medicionesAire.add(new RegistroMedicion(LocalDate.of(2018, 10, 15), 10.0, 20));
		medicionesAire.add(new RegistroMedicion(LocalDate.of(2018, 10, 20), 10.0, 20));
		aireAcondicionado1.setRegistrosConsumo(medicionesAire);

		DispositivoInteligente smartTV = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2018, 4, 28),
				new Fabricante("Sony", new Sony()), 90.0, 360.0);
		ArrayList<RegistroMedicion> medicionesTV = new ArrayList<RegistroMedicion>();
		medicionesTV.add(new RegistroMedicion(LocalDate.of(2018, 10, 4), 15.0, 20));
		smartTV.setRegistrosConsumo(medicionesTV);

		ArrayList<DispositivoInteligente> dispositivosInteligentes1 = new ArrayList<DispositivoInteligente>();
		ArrayList<DispositivoInteligente> dispositivosInteligentes2 = new ArrayList<DispositivoInteligente>();
		ArrayList<DispositivoEstandar> dispositivosEstandar1 = new ArrayList<DispositivoEstandar>();
		ArrayList<DispositivoEstandar> dispositivosEstandar2 = new ArrayList<DispositivoEstandar>();
		dispositivosInteligentes1.add(aireAcondicionado1);
		dispositivosEstandar1.add(new DispositivoEstandar("Lámpara", 10, 5, null, 0, 1550));
		dispositivosInteligentes2.add(smartTV);
		dispositivosEstandar2.add(new DispositivoEstandar("Plancha", 20, 1, null, 0, 1000));

		Cliente cliente1 = new Cliente("Fede", "Perez", TipoDocumento.DNI, "41919911", "23456379", "Cerrito 1546, CABA",
				LocalDate.of(2018, 5, 20), dispositivosInteligentes1, dispositivosEstandar1, false,
				new Punto(-0.127512, 51.507222), "Luci69");
		Cliente cliente2 = new Cliente("Jorge", "Perez", TipoDocumento.DNI, "1111", "4444", "Nazca 156",
				LocalDate.of(2017, 4, 28), dispositivosInteligentes2, dispositivosEstandar2, true,
				new Punto(-0.127512, 51.507222), "Facu96");

		addInstanceToDB(Cliente.class, cliente1);
		addInstanceToDB(Cliente.class, cliente2);

		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
				PropertiesConfiguration.class)
						.configure(params.properties().setListDelimiterHandler(new DefaultListDelimiterHandler(','))
								.setFileName("usuarios.properties"));
		Configuration config = null;
		try {
			config = builder.getConfiguration();
		} catch (org.apache.commons.configuration2.ex.ConfigurationException e) {

		}

		List<String> clientes = config.getList(String.class, "clientes");
		List<String> conCientes = config.getList(String.class, "con-clientes");
		List<Integer> idCientes = Arrays.asList(cliente1.getId(), cliente2.getId());

		List<String> admin = config.getList(String.class, "admin");
		List<String> conAdmin = config.getList(String.class, "con-admin");

		List<dummyUser> users = new ArrayList<dummyUser>();
		conCientes = conCientes.stream().map(pInput -> Cifrado.Encrypt(pInput)).collect(Collectors.toList());
		conAdmin = conAdmin.stream().map(pInput -> Cifrado.Encrypt(pInput)).collect(Collectors.toList());

		for (int i = 0; i < clientes.size(); i++) {
			users.add(new dummyUser(clientes.get(i), conCientes.get(i), false, idCientes.get(i)));
		}
		for (int i = 0; i < admin.size(); i++) {
			users.add(new dummyUser(admin.get(i), conAdmin.get(i), true, -1));
		}

		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction txn = entityManager.getTransaction();
		txn.begin();
		users.stream().forEach(user -> entityManager.persist(user));
		txn.commit();
		entityManager.close();

	}

	public static <T> void addInstanceToDB(Class<T> clase, T ObjetoAPersistir) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(ObjetoAPersistir);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		}
		em.close();
	}

}
