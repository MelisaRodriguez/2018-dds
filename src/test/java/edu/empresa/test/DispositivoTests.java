package edu.empresa.test;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;

import junit.framework.Assert;

public class DispositivoTests extends DispositivoFixture {
	@Test
	public void testConsumoEstandar() {
		Assert.assertEquals(50.0, dispositivoEstandar.getCalcularConsumo());
	}

	@Test
	public void testConsumoInteligente() {
		when(fabricantemock.cuantoConsume(dispositivoInteligente)).thenReturn(10.0);
		dispositivoInteligente.agregarNuevoRegistroDeConsumo();

		Assert.assertEquals(10.0,
				dispositivoInteligente.consumoTotalEnPeriodo(LocalDate.of(2018, 8, 9), LocalDate.of(2019, 11, 15)));
	}

	@Test
	public void apagarDispositivoInteligente() {
		dispositivoInteligente.apagarse();
		when(fabricantemock.estaApagado(dispositivoInteligente)).thenReturn(true);

		Assert.assertTrue(dispositivoInteligente.estaApagado());
		verify(fabricantemock).apagar(dispositivoInteligente);
	}

	@Test
	public void encenderDispositivoInteligente() {
		dispositivoInteligente.encenderse();
		when(fabricantemock.estaEncendido(dispositivoInteligente)).thenReturn(true);

		Assert.assertTrue(dispositivoInteligente.estaEncendido());
		verify(fabricantemock).encender(dispositivoInteligente);
	}

	@Test
	public void activarAhorroDeEnergiaDispositivoInteligente() {
		dispositivoInteligente.modoAhorroEnergia();
		when(fabricantemock.estaModoAhorroEnergia(dispositivoInteligente)).thenReturn(true);

		Assert.assertTrue(dispositivoInteligente.estaModoAhorroEnergia());
		verify(fabricantemock).activarAhorroDeEnergia(dispositivoInteligente);
	}

}
