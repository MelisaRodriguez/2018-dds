package edu.usuario.test;

import java.awt.geom.Point2D;

import org.junit.Test;

import junit.framework.Assert;

public class ZonaTest extends ZonaFixture {
	@Test
	public void testCantidadDeTransformadoresEnZona() {
		Assert.assertEquals( 1, zona.size() );
	}
	
	@Test
	public void testTransformadorLugar() {
		Assert.assertEquals( new Point2D.Double(-0.127512, 51.507222), zona.get(0).getTransformadores().get(0).getLugar() );
	}	
	
	@Test
	public void testTransformadorConsumidor() {
		Assert.assertEquals( 2028.0 , zona.get(0).getTransformadores().get(0).calcularConsumo() );
	}	
	
	@Test
	public void testZonaGeograficaRadio() {
		Assert.assertEquals( 8000.0 , zona.get(0).getRadio() );
	}	
	
	@Test
	public void testZonaGeograficaConsumo() {
		Assert.assertEquals( 2028.0 , zona.get(0).verConsumo() );
	}	
}
