package edu.empresa.test;

import org.junit.Before;

import static org.mockito.Mockito.mock;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.fabricante.SanyoTelevisor;


import java.time.LocalDate;


public class DispositivoFixture {
	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoEstandar dispositivoEstandar;
	protected SanyoTelevisor televisor;
	
	@Before
	public void fixture() {
		televisor = mock(SanyoTelevisor.class);
		dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), televisor);
		dispositivoEstandar = new DispositivoEstandar("Televisor", 10, 5, televisor);
	}
}
