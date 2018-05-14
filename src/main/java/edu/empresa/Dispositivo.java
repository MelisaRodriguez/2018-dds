package edu.empresa;

public class Dispositivo {

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

	public double consumo (int horas) {
		return kWxHora * horas;
	}
	
}