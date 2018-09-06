package edu.dominio.empresa;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import edu.dominio.fabricante.Fabricante;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class DispositivoEstandar extends Dispositivo {

	private String nombre;
	private double kW;
	private double horasUsoxDiaSegunUsuario;
	private Fabricante fabricante;
	private double restriccionMinima;
	private double restriccionMaxima;

	public DispositivoEstandar(String nombre, double kW, double horasUso, Fabricante fabricante, double restriccionMinima, double restriccionMaxima) {
		this.nombre = nombre;
		this.kW = kW;
		this.horasUsoxDiaSegunUsuario = horasUso;
		this.fabricante = fabricante;
		this.restriccionMinima = restriccionMinima;
		this.restriccionMaxima = restriccionMaxima;
	}
	
	public DispositivoEstandar() {}
	
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
		return new DispositivoInteligente(this.nombre, LocalDate.now(), fabricante, restriccionMinima, restriccionMaxima);
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