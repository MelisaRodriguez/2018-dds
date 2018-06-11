package edu.dominio.empresa;

import java.time.LocalDate;

import edu.dominio.fabricante.Fabricante;

public class DispositivoEstandar implements Dispositivo {

	private String nombre;
	private double kWxHora;
	private double horasUsoxDiaSegunUsuario;
	private Fabricante fabricante;

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

	// Metodos vacios
	public void apagarse(){};
	public void encenderse(){};
	public void modoAhorroEnergia(){};
	public void estaEncendido(){};
	public void estaApagado(){};
}