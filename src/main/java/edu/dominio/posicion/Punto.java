package edu.dominio.posicion;

import java.awt.geom.Point2D;

import org.geotools.referencing.GeodeticCalculator;

public class Punto {
	private double x;
	private double y;
	
	public Punto(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	private Point2D getPosicion()
	{
		return new Point2D.Double(x, y);
	}
	
	public static double calcularDistancia(Punto p1, Punto p2)
	{
		GeodeticCalculator calc = new GeodeticCalculator();
		calc.setStartingGeographicPoint(p1.getPosicion());
		calc.setDestinationGeographicPoint(p2.getPosicion());
		return calc.getOrthodromicDistance();
	}
}
