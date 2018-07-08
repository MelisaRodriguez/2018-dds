package edu.empresa.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

public final class LlamarSimplexTests extends LlamarSimplexFixture{
	@Test
	public void gererarRecomndacion() {
		when(cliente.cantDispositivosEnTotal()).thenReturn(8);
		when(cliente.todosSusDispositivos()).thenReturn(dispositivos);
		
		// Coeficientes  
		when(aireAcondicionado.getPotencia()).thenReturn(1.013);
		when(lampara.getPotencia()).thenReturn(0.011);
		when(televisor .getPotencia()).thenReturn(0.08);
		when(pc.getPotencia()).thenReturn(0.4);
		when(lavarropas.getPotencia()).thenReturn(0.1275);
		when(microondas.getPotencia()).thenReturn(0.64);
		when(plancha.getPotencia()).thenReturn(0.75);
		when(ventilador.getPotencia()).thenReturn(0.06);
		
		// Restricciones
		when(aireAcondicionado.getRestriccionMinima()).thenReturn(90.0);
		when(aireAcondicionado.getRestriccionMaxima()).thenReturn(360.0);
		when(lampara.getRestriccionMinima()).thenReturn(90.0);
		when(lampara.getRestriccionMaxima()).thenReturn(360.0);
		when(televisor.getRestriccionMinima()).thenReturn(90.0);
		when(televisor.getRestriccionMaxima()).thenReturn(360.0);
		when(pc.getRestriccionMinima()).thenReturn(90.0);
		when(pc.getRestriccionMaxima()).thenReturn(360.0);
		when(lavarropas.getRestriccionMinima()).thenReturn(6.0);
		when(lavarropas.getRestriccionMaxima()).thenReturn(30.0);
		when(microondas.getRestriccionMinima()).thenReturn(3.0);
		when(microondas.getRestriccionMaxima()).thenReturn(15.0);
		when(plancha.getRestriccionMinima()).thenReturn(3.0);
		when(plancha.getRestriccionMaxima()).thenReturn(30.0);
		when(ventilador.getRestriccionMinima()).thenReturn(120.0);
		when(ventilador.getRestriccionMaxima()).thenReturn(360.0);
		
		Assert.assertEquals(0.0, llamarSimplex.generarRecomendacion(cliente)); //TODO
	}

}

	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
