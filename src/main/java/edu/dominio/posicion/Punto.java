package edu.dominio.posicion;

import java.awt.geom.Point2D;

import javax.persistence.Embeddable;

import org.geotools.referencing.GeodeticCalculator;

@Embeddable
public class Punto {
	private double x;
	private double y;
	
	public Punto(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public static double calcularDistancia(Punto p1, Punto p2)
	{
		GeodeticCalculator calc = new GeodeticCalculator();
		calc.setStartingGeographicPoint(new Point2D.Double(p1.x, p1.y));
		calc.setDestinationGeographicPoint(new Point2D.Double(p2.x, p2.y));
		return calc.getOrthodromicDistance();
	}
}