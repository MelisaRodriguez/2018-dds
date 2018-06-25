package edu.dominio.empresa;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.geotools.referencing.GeodeticCalculator;
import edu.dominio.usuario.Cliente;

public class ZonaGeografica {

	private String ID;
	private List<Transformador> transformadores = new ArrayList<>();
	private double radio;
	private Point2D centro;
	
	ZonaGeografica(String ID, Point2D centro, double radio)
	{
		this.ID = ID;
		this.centro = centro;
		this.radio = radio;
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public void agregarTransformador(Transformador nuevoTransformador)
	{
		transformadores.add(nuevoTransformador);
	}
	
	private double calcularDistancia(Point2D p1, Point2D p2)
	{
		GeodeticCalculator calc = new GeodeticCalculator();
		calc.setStartingGeographicPoint(p1);
		calc.setDestinationGeographicPoint(p2);
		return calc.getOrthodromicDistance();
	}
	
	public void agregarCliente(Cliente unCliente, Point2D lugar)
	{
		transformadores.stream().min(Comparator.comparing(t->calcularDistancia(t.getLugar(), lugar))).get().agregarCliente(unCliente);
	}
	
	public void agregarTransformadores(List<Transformador> nuevosTransformadores)
	{
		this.transformadores.addAll(nuevosTransformadores);
	}
	
	public double verConsumo()
	{
		return this.transformadores.stream().mapToDouble(transf->transf.calcularConsumo()).sum();
	}
	
	public boolean estaEnRango(Point2D unPunto)
	{
		return radio > this.calcularDistancia(unPunto, centro);
	}
	
}
