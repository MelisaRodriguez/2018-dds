package edu.usuario.test;

import org.junit.Test;

import edu.dominio.posicion.Punto;
import junit.framework.Assert;

public class ZonaTest extends ZonaFixture {
	@Test
	public void testCantidadDeTransformadoresEnZona() {
		Assert.assertEquals( 1, zona.size() );
	}
	
	@Test
	public void testTransformadorLugar() {
		Punto punto = new Punto(-0.127512, 51.507222);
		Assert.assertEquals(punto.getX(), zona.get(0).getTransformadores().get(0).getLugar().getX());
		Assert.assertEquals(punto.getY(), zona.get(0).getTransformadores().get(0).getLugar().getY());
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