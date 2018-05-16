package edu.fabricante;

public class Fabricante {
	private String nombre;
	private TipoDeEnvioInformacion formaDeEnvio;
	
	public Fabricante(String nombre,TipoDeEnvioInformacion formaDeEnvio) {
		this.nombre=nombre;
		this.formaDeEnvio=formaDeEnvio;
	}
	
	public TipoDeEnvioInformacion getFormaDeEnvio() {
		return formaDeEnvio;
	}
}
