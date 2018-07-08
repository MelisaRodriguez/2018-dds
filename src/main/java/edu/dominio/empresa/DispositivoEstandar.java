package edu.dominio.empresa;

import java.time.LocalDate;

import edu.dominio.fabricante.Fabricante;
import edu.dominio.usuario.RestriccionConsumo;

public class DispositivoEstandar implements Dispositivo {

	private String nombre;
	private double kWxHora;
	private double horasUsoxDiaSegunUsuario;
	private Fabricante fabricante;
	private double restriccionMinima;
	private double restriccionMaxima;

	public DispositivoEstandar(String nombre, double kWxHora, double horasUso, Fabricante fabricante) {
		this.nombre = nombre;
		this.kWxHora = kWxHora;
		this.horasUsoxDiaSegunUsuario = horasUso;
		this.fabricante = fabricante;
	}
	
	public void actualizarHorasUso(double horas)
	{
		this.horasUsoxDiaSegunUsuario = horas;
	}

	public double calcularConsumo()
	{
		return horasUsoxDiaSegunUsuario*kWxHora;
	}
	
	public DispositivoInteligente convertirAInteligente()
	{
		return new DispositivoInteligente(this.nombre, LocalDate.now(), fabricante);
	}

	public double getRestriccionMinima()
	{
		return this.restriccionMinima;
	}
	
	public double getRestriccionMaxima()
	{
		return this.restriccionMaxima;
	}
	
	public double getPotencia()
	{ 
		return this.kWxHora;
	}
	
}