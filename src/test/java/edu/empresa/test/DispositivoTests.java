package edu.empresa.test;
import java.time.LocalDate;

import org.junit.Test;

import junit.framework.Assert;
import static org.mockito.Mockito.*;

public class DispositivoTests extends DispositivoFixture{
	@Test
	public void testConsumoEstandar() 
	{
		Assert.assertEquals(50.0, dispositivoEstandar.calcularConsumo());	
	}
	
	@Test
	public void testConsumoInteligente() 
	{
		when(televisor.cuantoConsume()).thenReturn(10.0);
		dispositivoInteligente.agregarNuevoRegistroDeConsumo(); 
	
		Assert.assertEquals(10.0, dispositivoInteligente.consumoTotalEnPeriodo(LocalDate.of(2018, 7, 9), LocalDate.of(2018, 7, 11)));		
	}
	
	@Test
	public void apagarDispositivoInteligente() 
	{
		dispositivoInteligente.apagarse();
		when(televisor.estaApagado()).thenReturn(true);
		
		Assert.assertTrue(dispositivoInteligente.estaApagado());	
		verify(televisor).apagar();
	}
	
	@Test
	public void encenderDispositivoInteligente() 
	{
		dispositivoInteligente.encenderse();
		when(televisor.estaEncendido()).thenReturn(true);
		
		Assert.assertTrue(dispositivoInteligente.estaEncendido());	
		verify(televisor).encender();
	}
	
	@Test
	public void activarAhorroDeEnergiaDispositivoInteligente() 
	{
		dispositivoInteligente.modoAhorroEnergia();
		when(televisor.estaModoAhorroEnergia()).thenReturn(true);
		
		Assert.assertTrue(dispositivoInteligente.estaModoAhorroEnergia());	
		verify(televisor).activarAhorroDeEnergia();
	}
	
	// PEQUE�A GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
