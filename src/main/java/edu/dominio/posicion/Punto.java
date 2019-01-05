package edu.dominio.posicion;

import java.awt.geom.Point2D;

import javax.persistence.Embeddable;

import org.geotools.referencing.GeodeticCalculator;

@Embeddable
public class Punto {
	private double latitud;
	private double longitud;

	public Punto() {
	}

	public Punto(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getX() {
		return latitud;
	}

	public double getY() {
		return longitud;
	}

	public void setX(double x) {
		this.latitud = x;
	}

	public void setY(double y) {
		this.longitud = y;
	}

	private Point2D getPunto() {
		return new Point2D.Double(latitud, longitud);
	}

	public double calcularDistancia(Punto p2) {
		GeodeticCalculator calc = new GeodeticCalculator();
		calc.setStartingGeographicPoint(getPunto());
		calc.setDestinationGeographicPoint(p2.getPunto());
		return calc.getOrthodromicDistance();
	}
}