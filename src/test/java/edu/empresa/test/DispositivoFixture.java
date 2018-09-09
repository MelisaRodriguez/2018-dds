package edu.empresa.test;

import org.junit.Before;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.fabricante.Fabricante;
import static org.mockito.Mockito.*;

import java.time.LocalDate;


public class DispositivoFixture {
	
	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoEstandar dispositivoEstandar;
	protected Fabricante fabricante;
	
	
	//protected SanyoTelevisor televisor;
	
	@Before
	public void fixture() {
		fabricante=new Fabricante("el alberto",null);
		
		//televisor = mock(SanyoTelevisor.class);
		dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), fabricante, 0, 0);
		dispositivoEstandar = new DispositivoEstandar("Televisor", 10, 5, fabricante, 0, 0);
	}
}
