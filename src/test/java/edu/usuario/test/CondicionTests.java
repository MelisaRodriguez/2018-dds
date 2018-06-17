package edu.usuario.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Sensor;
import junit.framework.Assert;


public class CondicionTests {
	private Condicion condicion;
	private Sensor sensor;
	
	@Before
	public void Setup(){
		sensor = mock(Sensor.class);
		condicion = new Condicion(sensor, (Double valor) -> {return valor > 20;});
	}
	
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
