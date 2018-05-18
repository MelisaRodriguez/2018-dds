package edu.usuario.test;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import edu.empresa.estadoEncendido;
import junit.framework.Assert;
public class DispositivoTests extends DispositivoFixture{
	@Test
	public void testConsumoEnNHoras() 
	{
		Assert.assertEquals(240.0, dispositivoInteligente.consumoTotalEnPeriodo(LocalDate.of(2017, 3, 28), LocalDate.of(2017, 3, 29)));	
	}
	
	@Test
	public void testDebeAccionar()
	{
		sensorCumpleMedicion.tomarMedicion(valor);
		reglaCumpleMedicion.ocurrioEvento();
		Assert.assertTrue(dispositivoInteligente.getAccionoAlgunaVez());
	}
	
	@Test
	public void testNoDebeAccionar()
	{
		sensorNoCumpleMedicion.tomarMedicion(valor);
		reglaNoCumpleMedicion.ocurrioEvento();
		Assert.assertFalse(dispositivoInteligente.getAccionoAlgunaVez());
	}
	
	@Test
	public void testApagarDesdeFormaEnvio()
	{
		dispositivoInteligente.setEstado(new estadoEncendido());
		formaDeEnvio.apagar(dispositivoInteligente);
		Assert.assertFalse(dispositivoInteligente.estaEncendido());
	}
	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
