package edu.usuario.test;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoCategorias;
import junit.framework.Assert;

public class ClienteTests extends ClienteFixture {
	
	@Test
	public void testLeerJson() {
		
		// El archivo Json arma una lista de clientes con longitud 3 (para el actual
		// .json)
		Assert.assertEquals(3, clientes.size() );
	}

	@Test
	public void testClienteCuantosKwConsume() 
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(676.0, unCliente.consumoTotal());
	}
	
	@Test
	public void testCuantosDispositivosTieneCliente() 
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(2, unCliente.cantDispositivosEnTotal());
	}
	

	@Test
	public void testCantidadDispositivosEncendidos() 
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}

	@Test
	public void testCantidadDispositivosApagados() 
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(0, unCliente.cantDispositivosApagados());
	}

	@Test
	public void testNingunDispositivoEncendido()
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(true,unCliente.tieneDispositivoEncendido());
	}

	@Test
	public void testRecategorizarCliente()
	{
		Cliente unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getSingletonInstance().getEntidades().get(6), unCliente.getCategoria());
		// No se puede poner en el valor esperado new Categoría(parámetros) porque
		// tendría una categoria
		// con los mismos parámetros pero en otra dirección de memoria, es decir, no
		// serian equivalentes
	}
	

	@Test
	public void testCalculaTarifaEstimadaCliente() 
	{
		Cliente unCliente = clientes.get(0);
		Assert.assertEquals(1018.866, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}
	
	@Test
	public void testRegistrarDispositivo()
	{
		Cliente unCliente = clientes.get(1);
		unCliente.agregarDispositivo(dispositivoInteligente);
		Assert.assertEquals(15, unCliente.getPuntos());
	}	
	
	@Test
	public void testPuntosEnConvetirDispositivoEstandarInteligente()
	{
		Cliente unCliente = clientes.get(2);
		unCliente.convertirDispositivo(0);
		Assert.assertEquals(10, unCliente.getPuntos());
	}
}
