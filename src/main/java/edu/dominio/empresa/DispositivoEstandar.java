package edu.dominio.empresa;

import java.time.LocalDate;

public class DispositivoEstandar implements Dispositivo {

	private String nombre;
	private double kWxHora;

	public DispositivoEstandar(String nombre, double kWxHora) {
		this.nombre = nombre;
		this.kWxHora = kWxHora;
	}
	
	public double consumo(double horas)  // Estas horas las especifica el cliente y hace un calculo aproximado.
	{
		return horas*kWxHora;
	}
	
	public DispositivoInteligente convertirAInteligente()
	{
		return new DispositivoInteligente(this.nombre, LocalDate.now());
	}
}
