package edu.usuario.test;

import org.junit.Test;
import java.time.LocalDate; 

import edu.usuario.Cliente;
import junit.framework.Assert;

<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
public class ClienteTests extends ClienteFixture {
	
=======
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
        admin = new Administrador("Gaston","Prieto","Mozart 1800 - CABA",101,LocalDate.of(2018,4, 3));
    }
	
	// formato guia para los tests:
    // public void testNombre() // Clase(metodos que se estan probando)
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
    @Test 
    public void testLeerJson()
    {
    	// El archivo Json arma una lista de clientes con longitud 2 (para el actual .json)
    	Assert.assertEquals(2,clientes.size());
    }
    
	@Test
	public void testClienteCuantosKwConsume() // Cliente (consumoTotal)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Juan Pérez consume 162 kw por hora con sus dispositivos
		Cliente unCliente = clientes.get(0);
=======
		// El cliente Juan Perez consume 162 kw por hora con sus dispositivos
		unCliente = clientes.get(0);
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
		Assert.assertEquals(162.0, unCliente.consumoTotal());
	}
	
	@Test
	public void testCuantosDispositivosTieneCliente() // Cliente(cantDispositivos)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Juan Pérez tiene 3 dispositivos
		Cliente unCliente = clientes.get(0);
=======
		// El cliente Juan Perez tiene 3 dispositivos
		unCliente = clientes.get(0);
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
		Assert.assertEquals(3, unCliente.cantDispositivos());
	}
	
	@Test
	public void testCantidadDispositivosEncendidos() // Cliente (cantDispositivosEncendidos)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Juan Pérez tiene un dispositivo encendido
		Cliente unCliente = clientes.get(0);
=======
		// El cliente Juan Perez tiene un dispositivo encendido
		unCliente = clientes.get(0);
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
		Assert.assertEquals(1, unCliente.cantDispositivosEncendidos());
	}
	
	@Test
	public void testCantidadDispositivosApagados() // Cliente (cantDispositivosApagados)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Juan Pérez tiene dos dispositivos apagados
		Cliente unCliente = clientes.get(0);
=======
		// El cliente Juan Perez tiene dos dispositivos apagados
		unCliente = clientes.get(0);
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
		Assert.assertEquals(2, unCliente.cantDispositivosApagados());
	}
	
	@Test
	public void testNingunDispositivoEncendido() // Cliente (tieneDispositivoEncendido)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Manuel Rodríguez no tiene dispositivos encendidos
		Cliente unCliente = clientes.get(1);
=======
		// El cliente Manuel Rodriguez no tiene dispositivos encendidos
		unCliente = clientes.get(1);
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
		Assert.assertFalse(unCliente.tieneDispositivoEncendido());
	}
	
	@Test
	public void testRecategorizarCliente() // Cliente (recategorizar, getCategoria), RepoCategorias (solicitarCategoria, getCategorias), Categoria (estaEnLimites)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Juan Pérez se recategoriza a Categoría R2
		Cliente unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(clientes.get(1), unCliente.getCategoria());
		//No se puede poner en el valor esperado new Categoría(parámetros) porque tendría una categoria
		//con los mismos parámetros pero en otra dirección de memoria, es decir, no serian equivalentes
=======
		// El cliente Juan Perez se recategoriza a Categoria R2
		unCliente = clientes.get(0);
		unCliente.recategorizar();
		Assert.assertEquals(RepoCategorias.getCategorias().get(1), unCliente.getCategoria());
		//No se puede poner en el valor esperado new Categoria(parametros) porque tendria una categoria
		//con los mismos parametros pero en otra direccion de memoria, es decir, no serian equivalentes
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
	}
	
	@Test
	public void testCalculaTarifaEstimadaCliente() // Categoria (calcularTarifaEstimada) , Cliente (getCategoria, consumoTotal)
	{
<<<<<<< HEAD:src/test/java/edu/usuario/test/ClienteTests.java
		// El cliente Manuel Rodríguez tiene que pagar una tarifa estimada en $52,892
		Cliente unCliente = clientes.get(1);
		Assert.assertEquals(52.892, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}
=======
		// El cliente Manuel Rodriguez tiene que pagar una tarifa estimada en $52,892
		unCliente = clientes.get(1);
		Assert.assertEquals(52.892, unCliente.getCategoria().calcularTarifaEstimada(unCliente.consumoTotal()));
	}
	@Test
	public void testCuantoTiempoHaceQueEsAdmin() // Administrador (getMesesComoAdmin)
	{
		// Gaston Prieto es administrador desde hace 12 meses
		Assert.assertEquals(1,admin.getMesesComoAdmin());
	}

	
	// PEQUE�A GUIA PARA TESTS
	// Assert.assertTrue(metodo que devuelva un true)
	// Assert.assertFalse(metodo que devuelva false)
	// Assert.assertEquals(metodo que devuelva valor, metodo que devuelva valor)
	// @Test (expected = TipoDeExcepcionEsperadaException)
>>>>>>> f59c22edccf91683ad5820f5ff0848fe0023adbf:src/test/java/Group10/TP_Anual/Tests.java
}
