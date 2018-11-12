package edu.usuario.test;

import org.junit.Test;

import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoCategorias;
import junit.framework.Assert;

public class ClienteTests extends ClienteFixture {

	@Test
	public void testLeerJson() {

		// El archivo Json arma una lista de clientes con longitud 3 (para el actual
		// .json)
		Assert.assertEquals(3, clientes.size());
	}

	@Test
	public void testPuntosEnConvetirDispositivoEstandarInteligente() {
		Cliente unCliente = clientes.get(2);
		unCliente.convertirDispositivo(0);
		Assert.assertEquals(10, unCliente.getPuntos());
	}

	@Test
	public void testCuantosDispositivosTieneCliente() {
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(2, unCliente.cantDispositivosEnTotal());
	}

	@Test
	public void testRegistrarDispositivo() {
		Cliente unCliente = clientes.get(1);
		unCliente.agregarDispositivo(dispositivoInteligente);
		Assert.assertEquals(15, unCliente.getPuntos());
	}

	@Test
	public void testCantidadDispositivosEncendidos() {
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}

	@Test
	public void testClienteCuantosKwConsume() {
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(685.0, unCliente.getConsumoTotal());
	}

	@Test
	public void testCantidadDispositivosApagados() {
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(0, unCliente.cantDispositivosApagados());
	}

	@Test
	public void testNingunDispositivoEncendido() {
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(true, unCliente.tieneDispositivoEncendido());
	}

	@Test
	public void testRecategorizarCliente() {
		Cliente unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getSingletonInstance().getEntidades().get(6), unCliente.getCategoria());
		// No se puede poner en el valor esperado new Categor?a(par?metros) porque
		// tendr?a una categoria
		// con los mismos par?metros pero en otra direcci?n de memoria, es decir, no
		// serian equivalentes
	}

	@Test
	public void testCalculaTarifaEstimadaCliente() {
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1026.525, unCliente.getCategoria().calcularTarifaEstimada(unCliente.getConsumoTotal()));
	}
}
