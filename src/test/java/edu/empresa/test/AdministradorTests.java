package edu.empresa.test;

import org.junit.Test;

import junit.framework.Assert;

public class AdministradorTests extends AdministradorFixture {

	@Test
	public void testCuantoTiempoHaceQueEsAdmin() {
		// Gaston Prieto es administrador desde hace 18 meses
		Assert.assertEquals(18, admin.getMesesComoAdmin());
	}

}
