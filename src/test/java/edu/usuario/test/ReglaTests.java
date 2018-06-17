package edu.usuario.test;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import edu.dominio.usuario.Regla;
import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Condicion;

public class ReglaTests {
	private Regla regla;
	private Actuador actuador;
	private Condicion condicion;
	
	@Before
	public void Setup(){
		actuador = mock(Actuador.class);
		condicion = mock(Condicion.class);
		
		regla = new Regla(Arrays.asList(condicion), Arrays.asList(actuador));
	}
	
	@Test
	public void ejecutaRegla(){
		when(condicion.medicionCumpleCondicion()).thenReturn(true);
		regla.ejecutar();
		
		verify(actuador).enviarAccion();
		verify(condicion, times(1)).medicionCumpleCondicion();
	}
	
	@Test
	public void noEjecutaRegla(){
		when(condicion.medicionCumpleCondicion()).thenReturn(false);
		regla.ejecutar();
		
		verify(actuador, never()).enviarAccion();
		verify(condicion, times(1)).medicionCumpleCondicion();
	}
}
