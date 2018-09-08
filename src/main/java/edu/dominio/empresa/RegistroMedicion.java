package edu.dominio.empresa;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
class RegistroMedicion
{
	@Id
	@GeneratedValue
	private int idRegistro;
	private LocalDate fecha;
	private double kwConsumidos;
	private double horasEncendido;

	public RegistroMedicion(LocalDate fecha, double kwConsumidos, double horasEncendido )
	{
		this.fecha = fecha;
		this.kwConsumidos = kwConsumidos;
		this.horasEncendido = horasEncendido;
	}

	public boolean estaEntreFechas(LocalDate inicio, LocalDate fin)
	{
		return inicio.isBefore(fecha) && fin.isAfter(fecha);
	}
	
	public LocalDate fecha()
	{
		return fecha;
	}
	
	public double horasEncendido()
	{
		return horasEncendido;
	}

	public double kwConsumidos()
	{
		return kwConsumidos;
	}
}