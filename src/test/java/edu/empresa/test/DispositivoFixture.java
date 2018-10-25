package edu.empresa.test;

import org.junit.Before;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.FabricanteMock;
import edu.dominio.fabricante.Sony;

import static org.mockito.Mockito.*;

import java.time.LocalDate;


public class DispositivoFixture {
	
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
