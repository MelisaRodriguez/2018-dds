package edu.dominio.empresa;

public interface Dispositivo
{
	public double calcularConsumo();	
	public double getRestriccionMinima();
	public double getRestriccionMaxima();
	public double getPotencia();
}