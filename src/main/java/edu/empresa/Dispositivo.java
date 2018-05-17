package edu.empresa;

import edu.fabricante.Fabricante;

public class Dispositivo {

	protected String nombreGenerico;
	protected double kWxHora;
	protected Fabricante fabricante;

	public Dispositivo(String nombre, double kW,Fabricante fabricante) {
		this.nombreGenerico = nombre;
		this.kWxHora = kW;
		this.fabricante=fabricante;
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
	public Fabricante fabricante() {
		return fabricante;
	}
}