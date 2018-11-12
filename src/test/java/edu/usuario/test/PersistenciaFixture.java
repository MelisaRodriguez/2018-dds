package edu.usuario.test;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.fabricante.Sony;

public class PersistenciaFixture {

	protected static EntityManager manager;
	protected Sony fabricantemock = new Sony();

	@BeforeClass
	public static void fixture() {
		manager = PerThreadEntityManagers.getEntityManager();
	}

	@AfterClass
	public static void end() {
		manager.close();
	}

}
