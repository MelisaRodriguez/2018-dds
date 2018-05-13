package edu.empresa;

public abstract class Dispositivo {

	private String nombreGenerico;
	private double kWxHora;
	private boolean encendido;

	public Dispositivo(String nombre, double kW) {
		this.nombreGenerico = nombre;
		this.kWxHora = kW;
		this.encendido = false; // POR DEFECTO un dispositivo esta apagado
	}

	public String nombreGenerico() {
		return nombreGenerico;
	}

	public double kwConsumoxHora() {
		return kWxHora;
	}

	public boolean estaEncendido() {
		return encendido;
	}

	public abstract double consumo(int horas);
}