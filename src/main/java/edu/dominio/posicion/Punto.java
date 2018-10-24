package edu.dominio.posicion;

import java.awt.geom.Point2D;

import javax.persistence.Embeddable;

import org.geotools.referencing.GeodeticCalculator;

@Embeddable
public class Punto {
	private double x;
	private double y;

	public Punto() {}
	
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
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	private Point2D getPunto()
	{
		return new Point2D.Double(x, y);
	}

	public static double calcularDistancia(Punto p1, Punto p2)
	{
		GeodeticCalculator calc = new GeodeticCalculator();
		calc.setStartingGeographicPoint(p1.getPunto());
		calc.setDestinationGeographicPoint(p2.getPunto());
		return calc.getOrthodromicDistance();
	}
}