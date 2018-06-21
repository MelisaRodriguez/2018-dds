package edu.dominio.empresa;

import java.awt.geom.Point2D;

public class Transformador {
	
	private long consumo;
	private Point2D lugar;
	
	public Transformador(long consumo, Point2D lugar)
	{
		this.consumo = consumo;
		this.lugar = lugar;
		//agregarTransformador(this, lugar)
	}
	
	public long getConsumo()
	{
		return this.consumo;
	}
	
	public Point2D getLugar()
	{
		return this.lugar;
	}
}
