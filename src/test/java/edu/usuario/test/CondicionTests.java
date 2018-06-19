package edu.usuario.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import junit.framework.Assert;


public class CondicionTests extends CondicionFixture {
	
	
	@Test
	public void cumpleCondicion(){
		when(sensor.getMedida()).thenReturn(21.0);
		
		Assert.assertTrue(condicion.medicionCumpleCondicion());
		
		verify(sensor).getMedida();
	}
	
	@Test
	public void noCumpleCondicion(){
		when(sensor.getMedida()).thenReturn(19.0);
		
		Assert.assertFalse(condicion.medicionCumpleCondicion());
		
		verify(sensor).getMedida();
	}
}
