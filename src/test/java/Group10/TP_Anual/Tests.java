package Group10.TP_Anual;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class Tests 
{
	private Cliente unCliente;
	private List<Cliente> clientes;
	private Administrador admin; 

 
	@Before
    public void fixture()
    {
        clientes = JSONReader.leerJson();
        RepoCategorias.instanciarCategorias();
        admin = new Administrador("Gastón","Prieto","Mozart 1800 - CABA",101,new Fecha(28,3,2017));
    }
	
	// formato guía para los tests:
    // public void testNombre() // Clase(métodos que se están probando)
    @Test 
    public void testLeerJason()
    {
    	// El archivo Jason arma una lista de clientes con longitud 2 (para el actual .json)
    	Assert.assertEquals(2,clientes.size());
    }

	@Test
	public void testClienteCuantosKwConsume() // Cliente (consumoTotal)
	{
		// El cliente Juan Pérez consume 162 kw por hora con sus dispositivos
		unCliente = clientes.get(0);
		Assert.assertEquals(162.0, unCliente.consumoTotal());
	}
	@Test
	public void testCuantosDispositivosTieneCliente() // Cliente(cantDispositivos)
	{
		// El cliente Juan Pérez tiene 3 dispositivos
		unCliente = clientes.get(0);
		Assert.assertEquals(3, unCliente.cantDispositivos());
	}
	@Test
	public void testCantidadDispositivosEncendidos() // Cliente (cantDispositivosEncendidos)
	{
		// El cliente Juan Pérez tiene un dispositivo encendido
		unCliente = clientes.get(0);
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}
	@Test
	public void testCantidadDispositivosApagados() // Cliente (cantDispositivosApagados)
	{
		// El cliente Juan Pérez tiene dos dispositivos apagados
		unCliente = clientes.get(0);
		Assert.assertEquals(2, unCliente.cantDispositivosApagados());
	}
	@Test
	public void testNingunDispositivoEncendido() // Cliente (tieneDispositivoEncendido)
	{
		// El cliente Manuel Rodríguez no tiene dispositivos encendidos
		unCliente = clientes.get(1);
		Assert.assertFalse(unCliente.tieneDispositivoEncendido());
	}
	@Test
	public void testRecategorizarCliente() // Cliente (recategorizar, getCategoria), RepoCategorias (solicitarCategoria, getCategorias), Categoria (estaEnLimites)
	{
		// El cliente Juan Pérez se recategoriza a Categoría R2
		unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getCategorias().get(1), unCliente.getCategoria());
		//No se puede poner en el valor esperado new Categoría(parámetros) porque tendría una categoria
		//con los mismos parámetros pero en otra dirección de memoria, es decir, no serian equivalentes
	}
	@Test
	public void testCalculaTarifaEstimadaCliente() // Categoría (calcularTarifaEstimada) , Cliente (getCategoria, consumoTotal)
	{
		// El cliente Manuel Rodríguez tiene que pagar una tarifa estimada en $52,892
		unCliente = clientes.get(1);
		Assert.assertEquals(52.892, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}
	@Test
	public void testCuantoTiempoHaceQueEsAdmin() // Administrador (getMesesComoAdmin)
	{
		// Gastón Prieto es administrador desde hace 12 meses
		Assert.assertEquals(12,admin.getMesesComoAdmin());
	}

	
	// PEQUEÑA GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
}
