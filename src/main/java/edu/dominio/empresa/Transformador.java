package edu.dominio.empresa;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoZonaGeografica;

public class Transformador {
	
	private Point2D.Double lugar;
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public Transformador(Point2D.Double lugar)
	{
		ZonaGeografica bolivia=new ZonaGeografica("Bolivia1",new Point2D.Double(-0.127512, 51.507222),8000);
		this.lugar = lugar;
		RepoZonaGeografica.getSingletonInstance(bolivia).agregarTransformador(this, lugar);
	}
	
	public void agregarCliente(Cliente unCliente)
	{
		this.clientes.add(unCliente);
	}
	
	public double calcularConsumo()
	{
		return this.clientes.stream().mapToDouble(c->c.consumoTotal()).sum();
	}
	
	public Point2D.Double getLugar()
	{
		return this.lugar;
	}
}
