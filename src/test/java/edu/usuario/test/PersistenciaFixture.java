package edu.usuario.test;


import static org.mockito.Mockito.mock;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.FabricanteMock;
import edu.empresa.test.DispositivoFixture.Sony;


public class PersistenciaFixture {
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
			return 0;
		}

		@Override
		public boolean estaEncendido(DispositivoInteligente d) {
			return false;
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
	
	protected static EntityManager manager;
	protected Sony fabricantemock = new Sony();
	
	@BeforeClass
	public static void fixture(){
		manager = PerThreadEntityManagers.getEntityManager();
	}
	
	@AfterClass
	public static void end() {
		manager.close();
	}
	
	
}
