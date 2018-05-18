package edu.usuario.test;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import edu.empresa.Dispositivo;
import junit.framework.Assert;
public class DispositivoTests extends DispositivoFixture{
	@Test
	public void testConsumoEnNHoras() 
	{
		Assert.assertEquals(0, dispositivoInteligente.consumoTotalEnPeriodo(LocalDate.of(2017, 3, 28), LocalDate.of(2017, 3, 29)));
	}

	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
