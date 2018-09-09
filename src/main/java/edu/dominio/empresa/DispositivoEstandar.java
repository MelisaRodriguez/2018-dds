package edu.dominio.empresa;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import edu.dominio.fabricante.Fabricante;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DispositivoEstandar extends Dispositivo {

	private double kW;
	private double horasUsoxDiaSegunUsuario;

	public DispositivoEstandar(String nombre, double kW, double horasUso, Fabricante fabricante, double restriccionMinima, double restriccionMaxima) {
		super(nombre, fabricante, restriccionMinima, restriccionMaxima);
		this.kW = kW;
		this.horasUsoxDiaSegunUsuario = horasUso;
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

	public void setkW(double kW) {
		this.kW = kW;
	}

	public double getHorasUsoxDiaSegunUsuario() {
		return horasUsoxDiaSegunUsuario;
	}

	public void setHorasUsoxDiaSegunUsuario(double horasUsoxDiaSegunUsuario) {
		this.horasUsoxDiaSegunUsuario = horasUsoxDiaSegunUsuario;
	}
	
	
	@Override
	public double getPotencia() {
		return kW;
	}
}