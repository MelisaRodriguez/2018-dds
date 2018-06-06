package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.List;

public class DispositivoInteligente implements Dispositivo {
	
	private String nombre;
	private LocalDate fechaDeRegistro;
	private Fabricante fabricante; // Adapter
	private List<RegistroMedicion> registrosConsumo; //Se asume ordenada por fecha

	public DispositivoInteligente(String nombre, LocalDate fechaDeRegistro) {
		this.nombre = nombre;
	}

	public double calcularConsumo()
	{
		// a implementar
	}

	// Este método se ejecutaría automáticamente con un cron programado cuando se acabe la memoria del dispositivo.
	public void agregarNuevoRegistroDeConsumo()
	{
		registrosConsumo.add(new RegistroMedicion(LocalDate.now(),this.calcularConsumo))
	}
	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		if (inicio.isAfter(registrosConsumo.get(0).fecha()))
		{
			//A implementar
		}
	}

	// estos métodos se los delega al fabricante que se comunica con el dispositivo físico.
	public void apagarse () {
		Fabricante.apagarDispositivo();
	}
	public void encenderse () {
		Fabricante.encenderDispositivo();
	}
	public void modoAhorroEnergia() {
		Fabricante.activarAhorroDeEnergiaDispositivo()
	}
	public boolean estaEncendido() {
		Fabricante.estaEncendidoDispositivo()
	}
	public boolean estaApagado() {
		Fabricante.estaApagadoDispositivo()
	}

}
