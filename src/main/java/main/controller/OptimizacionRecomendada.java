package main.controller;

public class OptimizacionRecomendada {

	private String nombreDispositivo;
	private double consumoRecomendado;

	public OptimizacionRecomendada(String nombreDispositivo, double consumoRecomendado) {
		this.nombreDispositivo = nombreDispositivo;
		this.consumoRecomendado = consumoRecomendado;
	}

	public String getNombreDispositivo() {
		return nombreDispositivo;
	}

	public void setNombreDispositivo(String nombreDispositivo) {
		this.nombreDispositivo = nombreDispositivo;
	}

	public double getConsumoRecomendado() {
		return consumoRecomendado;
	}

	public void setConsumoRecomendado(double consumoRecomendado) {
		this.consumoRecomendado = consumoRecomendado;
	}

}
