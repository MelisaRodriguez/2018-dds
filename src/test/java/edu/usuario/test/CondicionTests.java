package edu.usuario.test;

import org.junit.Test;

import junit.framework.Assert;
import static org.mockito.Mockito.*;


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
