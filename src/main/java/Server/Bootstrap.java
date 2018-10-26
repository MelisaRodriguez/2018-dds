package Server;

import java.time.LocalDate;
import java.util.ArrayList;
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

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{

	
	
	public static void init() {
		
		LocalDate inicio = LocalDate.of(2017, 4, 28);
		LocalDate fin = LocalDate.of(2017, 5, 28);
		DispositivoInteligente aireAcondicionado = new DispositivoInteligente("Aire acondicionado", LocalDate.of(2017, 4, 28), new Fabricante("Sony",new Sony()), 90.0, 360.0);
 		ArrayList<RegistroMedicion> mediciones = new ArrayList<RegistroMedicion>();
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 29), 10.0, 20));
		mediciones.add(new RegistroMedicion(LocalDate.of(2017, 4, 30), 10.0, 20));
		aireAcondicionado.setRegistrosConsumo(mediciones);
		
		ArrayList<DispositivoInteligente> inteligentes = new ArrayList<DispositivoInteligente>();
		inteligentes.add(aireAcondicionado);
		
		ArrayList<DispositivoEstandar> estandar = new ArrayList<DispositivoEstandar>();
		estandar.add(new DispositivoEstandar("Licuadora",80,10,null,8,15));
		
		Bootstrap.addInstanceToDB(Cliente.class,
				new Cliente("Jorge", "Perez", TipoDocumento.DNI, 
				"1111", "4444", "Nazca 156", LocalDate.of(2017, 4, 28), inteligentes, estandar, 
				true,new Punto(-0.127512, 51.507222),"cachocachondo") );
		
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties().setListDelimiterHandler(new DefaultListDelimiterHandler(','))
		        .setFileName("usuarios.properties"));
		Configuration config = null;
		try {
			config = builder.getConfiguration();
		} catch (org.apache.commons.configuration2.ex.ConfigurationException e) {

		}
		
		List<String> clientes= config.getList(String.class, "clientes");
		List<String> conCientes= config.getList(String.class, "con-clientes");
		List<Integer> idCientes= config.getList(Integer.class, "id-clientes");
		
		List<String> admin= config.getList(String.class, "admin");
		List<String> conAdmin= config.getList(String.class, "con-admin");
		List<Integer> idAdmin = config.getList(Integer.class, "id-admin");
		
		List<dummyUser> users = new ArrayList<dummyUser>();
		
		conCientes = conCientes.stream().map(pInput->Cifrado.Encrypt(pInput)).collect(Collectors.toList());
		conAdmin = conAdmin.stream().map(pInput->Cifrado.Encrypt(pInput)).collect(Collectors.toList());
		
		for(int i = 0;i<clientes.size();i++)
		{
			users.add(new dummyUser(clientes.get(i), conCientes.get(i), false, idCientes.get(i)));
		}
		for(int i = 0;i<admin.size();i++)
		{
			users.add(new dummyUser(admin.get(i), conAdmin.get(i), true, idAdmin.get(i)));
		}
		
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction txn = entityManager.getTransaction();
		txn.begin();
		users.stream().forEach(user-> entityManager.persist(user));
		txn.commit();
		entityManager.close();
	}
	
	public static <T> void  addInstanceToDB(Class<T> clase,T ObjetoAPersistir) {
		EntityManager em = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = em.getTransaction();
					
			tx.begin();
			try {
				em.persist(ObjetoAPersistir);			
				tx.commit();
			}
			catch (Exception ex) {
				tx.rollback();
			}
			em.close();
		}	
	
}
