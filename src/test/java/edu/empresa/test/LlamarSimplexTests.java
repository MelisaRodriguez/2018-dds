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
		when(aireAcondicionado.calcularConsumo()).thenReturn(1.013);
		when(lampara.calcularConsumo()).thenReturn(0.011);
		when(televisor .calcularConsumo()).thenReturn(0.08);
		when(pc.calcularConsumo()).thenReturn(0.4);
		when(lavarropas.calcularConsumo()).thenReturn(0.1275);
		when(microondas.calcularConsumo()).thenReturn(0.64);
		when(plancha.calcularConsumo()).thenReturn(0.75);
		when(ventilador.calcularConsumo()).thenReturn(0.06);
		
		// Restricciones
		when(llamarSimplexMaxima.getTipoRestriccion()).thenReturn("<=");
		when(llamarSimplexMaxima.getValor()).thenReturn(612.0);
		
		when(aireAcondicionado.getMinima()).thenReturn(aireAcondicionadoMinima);
		when(aireAcondicionado.getMaxima()).thenReturn(aireAcondicionadoMaxima);
		when(lampara.getMinima()).thenReturn(lamparaMinima);
		when(lampara.getMaxima()).thenReturn(lamparaMaxima);
		when(televisor.getMinima()).thenReturn(televisorMinima);
		when(televisor.getMaxima()).thenReturn(televisorMaxima);
		when(pc.getMinima()).thenReturn(pcMinima);
		when(pc.getMaxima()).thenReturn(pcMaxima);
		when(lavarropas.getMinima()).thenReturn(lavarropasMinima);
		when(lavarropas.getMaxima()).thenReturn(lavarropasMaxima);
		when(microondas.getMinima()).thenReturn(microondasMinima);
		when(microondas.getMaxima()).thenReturn(microondasMaxima);
		when(plancha.getMinima()).thenReturn(planchaMinima);
		when(plancha.getMaxima()).thenReturn(planchaMaxima);
		when(ventilador.getMinima()).thenReturn(ventiladorMinima);
		when(ventilador.getMaxima()).thenReturn(ventiladorMaxima);
		
		
		when(aireAcondicionadoMinima.getTipoRestriccion()).thenReturn(">=");
		when(aireAcondicionadoMinima.getValor()).thenReturn(90.0);
		when(aireAcondicionadoMaxima.getTipoRestriccion()).thenReturn("<=");
		when(aireAcondicionadoMaxima.getValor()).thenReturn(360.0);
		when(lamparaMinima.getTipoRestriccion()).thenReturn(">=");
		when(lamparaMinima.getValor()).thenReturn(90.0);
		when(lamparaMaxima.getTipoRestriccion()).thenReturn("<=");
		when(lamparaMaxima.getValor()).thenReturn(360.0);
		when(televisorMinima.getTipoRestriccion()).thenReturn(">=");
		when(televisorMinima.getValor()).thenReturn(90.0);
		when(televisorMaxima.getTipoRestriccion()).thenReturn("<=");
		when(televisorMaxima.getValor()).thenReturn(360.0);
		when(pcMinima.getTipoRestriccion()).thenReturn(">=");
		when(pcMinima.getValor()).thenReturn(90.0);
		when(pcMaxima.getTipoRestriccion()).thenReturn("<=");
		when(pcMaxima.getValor()).thenReturn(360.0);
		when(lavarropasMinima.getTipoRestriccion()).thenReturn(">=");
		when(lavarropasMinima.getValor()).thenReturn(6.0);
		when(lavarropasMaxima.getTipoRestriccion()).thenReturn("<=");
		when(lavarropasMaxima.getValor()).thenReturn(30.0);
		when(microondasMinima.getTipoRestriccion()).thenReturn(">=");
		when(microondasMinima.getValor()).thenReturn(3.0);
		when(microondasMaxima.getTipoRestriccion()).thenReturn("<=");
		when(microondasMaxima.getValor()).thenReturn(15.0);
		when(planchaMinima.getTipoRestriccion()).thenReturn(">=");
		when(planchaMinima.getValor()).thenReturn(3.0);
		when(planchaMaxima.getTipoRestriccion()).thenReturn("<=");
		when(planchaMaxima.getValor()).thenReturn(30.0);
		when(ventiladorMinima.getTipoRestriccion()).thenReturn(">=");
		when(ventiladorMinima.getValor()).thenReturn(120.0);
		when(ventiladorMaxima.getTipoRestriccion()).thenReturn("<=");
		when(ventiladorMaxima.getValor()).thenReturn(360.0);
			
		
		Assert.assertEquals(0.0, llamarSimplex.generarRecomendacion(cliente));
	}

}

	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
