package edu.empresa.test;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;


import static org.mockito.Mockito.*;

public final class LlamarSimplexTests extends LlamarSimplexFixture{
	@Test
	public void gererarRecomndacion() {
		when(cliente.cantDispositivosEnTotal()).thenReturn(8);
		when(cliente.todosSusDispositivos()).thenReturn(dispositivos);
		when(cliente.dispositivosInteligentes()).thenReturn(inteligentes);
		when(cliente.getAhorroAutomatico()).thenReturn(true);
		
		// Coeficientes  
		when(aireAcondicionado.getPotencia()).thenReturn(1.013);
		when(lampara.getPotencia()).thenReturn(0.011);
		when(televisor .getPotencia()).thenReturn(0.08);
		when(pc.getPotencia()).thenReturn(0.4);
		when(lavarropas.getPotencia()).thenReturn(0.1275);
		when(microondas.getPotencia()).thenReturn(0.64);
		when(plancha.getPotencia()).thenReturn(0.75);
		when(ventilador.getPotencia()).thenReturn(0.06);
		
		// Nombres:
		when(aireAcondicionado.getNombre()).thenReturn("Aire acondicionado");
		when(lampara.getNombre()).thenReturn("Lámpara");
		when(televisor .getNombre()).thenReturn("Televisor");
		when(pc.getNombre()).thenReturn("PC");
		when(lavarropas.getNombre()).thenReturn("Lavarropas");
		when(microondas.getNombre()).thenReturn("Microondas");
		when(plancha.getNombre()).thenReturn("Plancha");
		when(ventilador.getNombre()).thenReturn("Ventilador");
		
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
		
		when(aireAcondicionado.horasTotalesEnPeriodo(LocalDate.of(2018, 7, 1), LocalDate.now())).thenReturn(500.0);
		
		llamarSimplex.generarRecomendacion(cliente);
		verify(aireAcondicionado).apagarse();
		Assert.assertTrue(true);
	
	}

}
