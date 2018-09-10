package edu.empresa.test;
import java.time.LocalDate;

import org.junit.Test;

import junit.framework.Assert;
import static org.mockito.Mockito.*;

public class DispositivoTests extends DispositivoFixture{
	@Test
	public void testConsumoEstandar() 
	{
		Assert.assertEquals(50.0, dispositivoEstandar.calcularConsumo());	
	}

	@Test
	public void testConsumoInteligente() 
	{
		when(fabricantemock.cuantoConsume(dispositivoInteligente)).thenReturn(10.0);
		dispositivoInteligente.agregarNuevoRegistroDeConsumo(); 
	
		//System.out.println(fabricantemock.cuantoConsume(dispositivoInteligente));
		
		Assert.assertEquals(10.0, dispositivoInteligente.consumoTotalEnPeriodo(LocalDate.of(2018, 8, 9), LocalDate.of(2018, 10, 15)));		
	}
	
	@Test
	public void apagarDispositivoInteligente() 
	{
		dispositivoInteligente.apagarse();
		when(fabricantemock.estaApagado(dispositivoInteligente)).thenReturn(true);
		
		Assert.assertTrue(dispositivoInteligente.estaApagado());	
		verify(fabricantemock).apagar(dispositivoInteligente);
	}
	
	@Test
	public void encenderDispositivoInteligente() 
	{
		dispositivoInteligente.encenderse();
		when(fabricantemock.estaEncendido(dispositivoInteligente)).thenReturn(true);
		
		Assert.assertTrue(dispositivoInteligente.estaEncendido());	
		verify(fabricantemock).encender(dispositivoInteligente);
	}
	
	@Test
	public void activarAhorroDeEnergiaDispositivoInteligente() 
	{
		dispositivoInteligente.modoAhorroEnergia();
		when(fabricantemock.estaModoAhorroEnergia(dispositivoInteligente)).thenReturn(true);
		
		Assert.assertTrue(dispositivoInteligente.estaModoAhorroEnergia());	
		verify(fabricantemock).activarAhorroDeEnergia(dispositivoInteligente);
	}

}
