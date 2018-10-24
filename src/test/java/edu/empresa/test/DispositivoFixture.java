package edu.empresa.test;

import org.junit.Before;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteMock;

import static org.mockito.Mockito.*;

import java.time.LocalDate;


public class DispositivoFixture {
	
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
			return 100;
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
		
		@Override
		public String getEstado(DispositivoInteligente d) {
			return "Encencido";
		}
	}
	
	
	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoEstandar dispositivoEstandar;
	protected Fabricante fabricante;
	protected Sony fabricantemock;
	
	
	@Before
	public void fixture() {
		
		fabricantemock= mock(Sony.class);
		
		fabricante=new Fabricante("el alberto",fabricantemock);
		
		dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), fabricante, 0, 0);
		dispositivoEstandar = new DispositivoEstandar("Televisor", 10, 5, fabricante, 0, 0);
	}
	
	
	
}
