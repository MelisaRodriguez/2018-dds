package edu.empresa;

public abstract class Dispositivo {

	protected String nombreGenerico;
	protected double kWxHora;


	public Dispositivo(String nombre, double kW) {
		this.nombreGenerico = nombre;
		this.kWxHora = kW;

	}

	public String nombreGenerico() {
		return nombreGenerico;
	}

	public double kwConsumoxHora() {
		return kWxHora;
	}

	public abstract double consumo(int horas);
	
}