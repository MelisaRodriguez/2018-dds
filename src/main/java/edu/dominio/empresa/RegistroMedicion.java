package edu.dominio.empresa;

import java.time.LocalDate;

class RegistroMedicion
{
	private LocalDate fecha;
	private double kwConsumidos;

	public RegistroMedicion(LocalDate fecha, double kwConsumidos)
	{
		this.fecha = fecha;
		this.kwConsumidos = kwConsumidos;
	}

	public boolean estaEntreFechas(LocalDate inicio, LocalDate fin)
	{
		return inicio.isBefore(fecha) && fin.isAfter(fecha);
	}
	public LocalDate fecha()
	{
		return fecha;
	}
	public double kwConsumidos()
	{
		return kwConsumidos;
	}
}