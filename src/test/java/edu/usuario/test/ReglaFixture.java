package edu.usuario.test;

import java.util.Arrays;

import org.junit.Before;

import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Regla;

import static org.mockito.Mockito.*;

public class ReglaFixture {
	protected Regla regla;
	protected Actuador actuador;
	protected Condicion condicion;
	
	@Before
	public void fixture(){
		actuador = mock(Actuador.class);
		condicion = mock(Condicion.class);
		
		regla = new Regla(Arrays.asList(condicion), Arrays.asList(actuador));
	}
}
