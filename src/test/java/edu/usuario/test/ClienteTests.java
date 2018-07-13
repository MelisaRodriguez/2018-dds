package edu.usuario.test;

import org.junit.Test;

import edu.dominio.usuario.Cliente;
import junit.framework.Assert;

public class ClienteTests extends ClienteFixture {
	
	//public List<Cliente> clientes;
	
	@Test
	public void testLeerJson() {
		
		// El archivo Json arma una lista de clientes con longitud 3 (para el actual
		// .json)
		System.out.println(clientes.size());
		Assert.assertEquals( 3, clientes.size() );
	}
	
	@Test
	public void testClienteCuantosKwConsume() // Cliente (consumoTotal)
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(676.0, unCliente.consumoTotal());
	}

	@Test
	public void testCuantosDispositivosTieneCliente() // Cliente(cantDispositivos)
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(2, unCliente.cantDispositivosEnTotal());
	}

	@Test
	public void testCantidadDispositivosEncendidos() // Cliente (cantDispositivosEncendidos)
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagados() // Cliente (cantDispositivosApagados)
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(0, unCliente.cantDispositivosApagados());
	}

	@Test
	public void testNingunDispositivoEncendido() // Cliente (tieneDispositivoEncendido)
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(true,unCliente.tieneDispositivoEncendido());
	}
/*
	@Test
	public void testRecategorizarCliente() // Cliente (recategorizar, getCategoria), RepoCategorias (solicitarCategoria,
											// getCategorias), Categoria (estaEnLimites)
	{
		Cliente unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getSingletonInstance().getEntidades().get(0), unCliente.getCategoria());
		// No se puede poner en el valor esperado new Categor�a(par�metros) porque
		// tendr�a una categoria
		// con los mismos par�metros pero en otra direcci�n de memoria, es decir, no
		// serian equivalentes
	}*/
	

	@Test
	public void testCalculaTarifaEstimadaCliente() // Categoria (calcularTarifaEstimada) , Cliente (getCategoria,
													// consumoTotal)
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1018.866, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}
	
	
	@Test
	public void testRegistrarDispositivo()
	{
		Cliente unCliente = clientes.get(1);
		unCliente.agregarDispositivo(dispositivoInteligente);
		Assert.assertEquals(30, unCliente.getPuntos());
	}	
	
	@Test
	public void testPuntosEnConvetirDispositivoEstandarInteligente()
	{
		Cliente unCliente = clientes.get(2);
		unCliente.convertirDispositivo(0);
		Assert.assertEquals(25, unCliente.getPuntos());
	}
	

	// PEQUEnA GUIA PARA TESTS

	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
