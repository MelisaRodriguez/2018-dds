package edu.fabricante;

public class Fabricante {
	private String nombre;
	private AccionesSegunFabricante formaDeEnvio;
	
	public Fabricante(String nombre,AccionesSegunFabricante formaDeEnvio) {
		this.nombre=nombre;
		this.formaDeEnvio=formaDeEnvio;
	}
	
	public AccionesSegunFabricante getFormaDeEnvio() {
		return formaDeEnvio;
	}
}
