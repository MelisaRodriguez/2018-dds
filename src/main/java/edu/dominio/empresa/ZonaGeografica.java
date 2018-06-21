package edu.dominio.empresa;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.geotools.referencing.GeodeticCalculator;

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
	
	public void agregarTransformadores(List<Transformador> nuevosTransformadores)
	{
		this.transformadores.addAll(nuevosTransformadores);
	}
	
	public long verConsumo()
	{
		return this.transformadores.stream().mapToLong(transf->transf.getConsumo()).sum();
	}
	
	public boolean puedeAgregarTransformador(Point2D unPunto)
	{
		GeodeticCalculator calc = new GeodeticCalculator();
		calc.setStartingGeographicPoint(centro);
		calc.setDestinationGeographicPoint(unPunto);
		return radio > calc.getOrthodromicDistance();
	}
	
}
