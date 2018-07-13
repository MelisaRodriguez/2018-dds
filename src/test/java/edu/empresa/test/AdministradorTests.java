package edu.empresa.test;

import org.junit.Test;

import junit.framework.Assert;

public class AdministradorTests extends AdministradorFixture {

	@Test
	public void testCuantoTiempoHaceQueEsAdmin() 
	{
		// Gaston Prieto es administrador desde hace 14 meses
		Assert.assertEquals(14, admin.getMesesComoAdmin());
	}

}
