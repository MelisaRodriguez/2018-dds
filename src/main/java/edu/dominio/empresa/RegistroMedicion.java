package edu.dominio.empresa;

class RegistroMedicion
{
	private LocalDate fecha;
	private double kwConsumidos;

	public RegistroMedicion(LocalDate fecha, double, kwConsumidos)
	{
		this.fecha = fecha;
		this.kwConsumidos = kwConsumidos;
	}

	public LocalDate fecha()
	{
		return fecha;
	}
	public double kwConsumidos
	{
		return kwConsumidos;
	}
}