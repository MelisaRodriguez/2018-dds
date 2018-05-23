package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.List;

public class DispositivoInteligente implements Dispositivo {
	
	private String nombre;
	private LocalDate fechaDeRegistro;
	//private Fabricante fabricante;
	private List<Double> registrosConsumo;

	public DispositivoInteligente(String nombre, LocalDate fechaDeRegistro) {
		this.nombre = nombre;
	}
	public double consumo(double horas)
	{
		// las horas las obtiene segun lo que disponga su fabricante y todavía no se como implementarlo
	}
	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		// tomar de la lista de registros los comprendidos en este periodo, va a ser un filter, luego map y por ultimo sum.
	}
	public void apagarse () {
		// a implementar
	}
	public void encenderse () {
		// a implementar
	}
	public void modoAhorroEnergia() {
		// a implementar
	}
	public boolean estaEncendido() {
		// a implementar
	}
	public boolean estaApagado() {
		// a implementar
	}

}
