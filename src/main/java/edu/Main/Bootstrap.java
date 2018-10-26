package edu.Main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public final class Bootstrap {

	public static void inicializar()
	{
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
		
		conCientes.stream().forEach(pInput->Cifrado.Encrypt(pInput));
		conAdmin.stream().forEach(pInput->Cifrado.Encrypt(pInput));
		
		for(int i = 0;i<clientes.size();i++)
		{
			users.add(new dummyUser(i, clientes.get(i), conCientes.get(i), false, idCientes.get(i)));
		}
		for(int i = 0;i<admin.size();i++)
		{
			users.add(new dummyUser(i, admin.get(i), conAdmin.get(i), true, idAdmin.get(i)));
		}
		
	/*	EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction txn = entityManager.getTransaction();
		txn.begin();
		users.stream().forEach(user-> entityManager.persist(user));
		txn.commit();
		entityManager.close();*/
	}
}
