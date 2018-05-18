package edu.usuario.test;

import org.junit.Test;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import edu.empresa.DispositivoEstandar;
import edu.empresa.DispositivoInteligente;
import edu.usuario.Cliente;
import edu.usuario.RepoCategorias;
import edu.usuario.TipoDocumento;
import junit.framework.Assert;

public class ClienteTests extends ClienteFixture {
	@Test
	public void testLeerJson() {
		// El archivo Json arma una lista de clientes con longitud 2 (para el actual
		// .json)
		//System.out.println(clientes.size());
		Assert.assertEquals(1, clientes.size());
	}

	@Test
	public void testClienteCuantosKwConsume() // Cliente (consumoTotal)
	{
		// El cliente Juan Pérez consume 162 kw por hora con sus dispositivos
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(162.0, unCliente.consumoTotal());
	}

	@Test
	public void testCuantosDispositivosTieneCliente() // Cliente(cantDispositivos)
	{
		// El cliente Juan Pérez tiene 3 dispositivos
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(3, unCliente.cantDispositivos());
	}

	@Test
	public void testCantidadDispositivosEncendidos() // Cliente (cantDispositivosEncendidos)
	{
		// El cliente Juan Pérez tiene un dispositivo encendido
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagados() // Cliente (cantDispositivosApagados)
	{
		// El cliente Juan Pérez tiene dos dispositivos apagados
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(2, unCliente.cantDispositivosApagados());
	}

	@Test
	public void testNingunDispositivoEncendido() // Cliente (tieneDispositivoEncendido)
	{
		// El cliente Manuel Rodríguez no tiene dispositivos encendidos
		Cliente unCliente = clientes.get(1);
		Assert.assertFalse(unCliente.tieneDispositivoEncendido());
	}

	@Test
	public void testRecategorizarCliente() // Cliente (recategorizar, getCategoria), RepoCategorias (solicitarCategoria,
											// getCategorias), Categoria (estaEnLimites)
	{
		// El cliente Juan Pérez se recategoriza a Categoría R2
		Cliente unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getSingletonInstance().getInfo().get(1), unCliente.getCategoria());
		// No se puede poner en el valor esperado new Categoría(parámetros) porque
		// tendría una categoria
		// con los mismos parámetros pero en otra dirección de memoria, es decir, no
		// serian equivalentes
	}

	@Test
	public void testCalculaTarifaEstimadaCliente() // Categoria (calcularTarifaEstimada) , Cliente (getCategoria,
													// consumoTotal)
	{
		// El cliente Manuel Rodríguez tiene que pagar una tarifa estimada en $52,892
		Cliente unCliente = clientes.get(1);
		Assert.assertEquals(52.892, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}

	// PEQUE�A GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
