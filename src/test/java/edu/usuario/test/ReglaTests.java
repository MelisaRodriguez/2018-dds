package edu.usuario.test;

import org.junit.Test;

public class ReglaTests extends ReglaFixture{
	
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
