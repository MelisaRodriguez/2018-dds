package edu.dominio.empresa;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoZonaGeografica;

public class Transformador {
	private Point2D lugar;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public Transformador(Point2D lugar)
	{
		this.lugar = lugar;
		RepoZonaGeografica.getSingletonInstance().agregarTransformador(this, lugar);
	}
	
	public void agregarCliente(Cliente unCliente)
	{
		this.clientes.add(unCliente);
	}
	
	public double calcularConsumo()
	{
		return this.clientes.stream().mapToDouble(c->c.consumoTotal()).sum();
	}
	
	public Point2D getLugar()
	{
		return this.lugar;
	}
}
