package edu.empresa.test;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.Before;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;

public class DispositivoFixture {

	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoEstandar dispositivoEstandar;
	protected Fabricante fabricante;
	protected Sony fabricantemock;

	@Before
	public void fixture() {

		fabricantemock = mock(Sony.class);
		fabricante = new Fabricante("el alberto", fabricantemock);
		dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), fabricante, 0, 0);
		dispositivoEstandar = new DispositivoEstandar("Televisor", 10, 5, fabricante, 0, 0);
	}
}
