package edu.empresa.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public final class SimplexTests extends SimplexFixture{
	
	@Test
	public void generarRecomendacion() {
		// Coeficientes  
		when(fabricanteAireAcondicionado.getPotencia(aireAcondicionado)).thenReturn(1.013);
		when(fabricanteLampara.getPotencia(lampara)).thenReturn(0.011);
		when(fabricanteTelevisor.getPotencia(televisor)).thenReturn(0.08);
		when(fabricantePC.getPotencia(pc)).thenReturn(0.4);
		when(fabricanteVentilador.getPotencia(ventilador)).thenReturn(0.06);	
		
		Assert.assertEquals( ArrayList.class ,llamarSimplex.generarRecomendacion(cliente).getClass());	
	}
	
	@Test
	public void dispositivoApagadoTest()
	{
		when(heladera.getPotencia()).thenReturn(1.013);
		when(heladera.getRestriccionMaxima()).thenReturn(360.0);
		when(heladera.getRestriccionMinima()).thenReturn(90.0);
		when(heladera.horasTotalesEnPeriodo(llamarSimplex.primerDiaDelMes(LocalDate.now()), LocalDate.now())).thenReturn(500.0);		
		llamarSimplex.optimizacionAutomatica(cliente2); 
		verify((Heladera)heladera).accionar();
	}

}
