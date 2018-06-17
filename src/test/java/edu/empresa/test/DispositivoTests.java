package edu.empresa.test;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
public class DispositivoTests extends DispositivoFixture{
	@Test
	public void testConsumoEstandar() 
	{
		Assert.assertEquals(50.0, dispositivoEstandar.calcularConsumo());	
	}
	
	/* // esta comentado porque todavía falta fabricante, y para hacer el consumo usa el fabricante
	@Test
	public void testConsumoInteligente() 
	{
		Assert.assertEquals(240.0, dispositivoInteligente.consumoTotalEnPeriodo(LocalDate.of(2017, 3, 28), LocalDate.of(2017, 3, 29)));	
	}
	// calcular consumo para los dos??
	*/
	
	
	// con fabricante hacer que apague/encienda un dispositivo?? No lo prueba porque todavía falta el fabricante 
	
	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
