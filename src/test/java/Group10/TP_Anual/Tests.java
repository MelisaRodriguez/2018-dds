package Group10.TP_Anual;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class Tests 
{
	private Cliente unCliente;
	private List<Cliente> clientes;
	
	@Before
    public void fixture()
    {
        clientes = JSONReader.leerJson();
        RepoCategorias.instanciarCategorias();
    }
	
	@Test
	public void testClienteCuantosKwConsume()
	{
		unCliente = clientes.get(0);
		Assert.assertEquals(162.0, unCliente.consumoTotal());
	}
	@Test
	public void testCantidadDispositivosEncendidos()
	{
		unCliente = clientes.get(0);
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}
	@Test
	public void testCantidadDispositivosApagados()
	{
		unCliente = clientes.get(0);
		Assert.assertEquals(2, unCliente.cantDispositivosApagados());
	}
	@Test
	public void testNingunDispositivoEncendido()
	{
		unCliente = clientes.get(1);
		Assert.assertFalse(unCliente.tieneDispositivoEncendido());
	}
	@Test
	public void testRecategorizarCliente()
	{
		unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getCategorias().get(1), unCliente.getCategoria());
		//No se puede poner en el valor esperado new Categoría porque tendria una categoria
		//con los mismos parámetros pero en otra dirección de memoria, es decir, no serian equivalentes
	}
	@Test
	public void testCalculaTarifaEstimadaCliente()
	{
		unCliente = clientes.get(1);
		Assert.assertEquals(53.3428, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}
	
	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
