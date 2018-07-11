package edu.empresa.test;

import org.junit.Test;

import junit.framework.Assert;

public class AdministradorTests extends AdministradorFixture {

	@Test
	public void testCuantoTiempoHaceQueEsAdmin() // Administrador (getMesesComoAdmin)
	{
		// Gastón Prieto es administrador desde hace 15 meses
		Assert.assertEquals(15, admin.getMesesComoAdmin());
	}

	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
