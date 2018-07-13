package edu.dominio.empresa;

import java.time.LocalDate;

import edu.dominio.fabricante.Fabricante;

public class DispositivoEstandar implements Dispositivo {

	private String nombre;
	private double kW;
	private double horasUsoxDiaSegunUsuario;
	private Fabricante fabricante;
	private double restriccionMinima;
	private double restriccionMaxima;

	public DispositivoEstandar(String nombre, double kW, double horasUso, Fabricante fabricante) {
		this.nombre = nombre;
		this.kW = kW;
		this.horasUsoxDiaSegunUsuario = horasUso;
		this.fabricante = fabricante;
	}
	
	public void actualizarHorasUso(double horas)
	{
		this.horasUsoxDiaSegunUsuario = horas;
	}

	@Override
	public double calcularConsumo()
	{
		return horasUsoxDiaSegunUsuario*kW;
	}
	
	public DispositivoInteligente convertirAInteligente()
	{
		return new DispositivoInteligente(this.nombre, LocalDate.now(), fabricante);
	}

	@Override
	public double getRestriccionMinima()
	{
		return this.restriccionMinima;
	}
	
	@Override
	public double getRestriccionMaxima()
	{
		return this.restriccionMaxima;
	}
	
	@Override
	public double getPotencia()
	{ 
		return this.kW;
	}
	
	@Override
	public String getNombre()
	{
		return nombre;
	}
}