package edu.dominio.empresa;

import java.util.ArrayList;
import java.util.List;

import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoZonaGeografica;

public class Transformador {
	
	private Punto lugar;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public Transformador(Punto lugar)
	{
		this.lugar = lugar;
		RepoZonaGeografica.getSingletonInstance().agregarTransformador(this, lugar);
	}
	
	public void agregarCliente(Cliente unCliente)
	{
		this.clientes.add(unCliente);
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public double calcularConsumo()
	{
		return this.clientes.stream().mapToDouble(c->c.consumoTotal()).sum();
	}
	
	public Punto getLugar()
	{
		return this.lugar;
	}
}
