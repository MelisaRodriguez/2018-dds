package edu.dominio.empresa;

public abstract class Dispositivo
{
	public abstract double calcularConsumo();	
	public abstract double getRestriccionMinima();
	public abstract double getRestriccionMaxima();
	public abstract double getPotencia();
	public abstract String getNombre();
}