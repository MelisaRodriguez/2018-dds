package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.dominio.fabricante.Fabricante;

public class DispositivoInteligente implements Dispositivo {
	
	private String nombre;
	private LocalDate fechaDeRegistro;
	private Fabricante fabricante; // Adapter
	private List<RegistroMedicion> registrosConsumo; //Se asume ordenada por fecha

	public DispositivoInteligente(String nombre, LocalDate fechaDeRegistro, Fabricante fabricante) {
		this.nombre = nombre;
		this.fechaDeRegistro = fechaDeRegistro;
		this.fabricante = fabricante;
		this.registrosConsumo = new ArrayList<RegistroMedicion>();
	}

	public double calcularConsumo()
	{
		fabricante.cuantoConsumeDispositivo();
	}

	// Este metodo se ejecutara automaticamente con un cron programado cuando se acabe la memoria del dispositivo.
	public void agregarNuevoRegistroDeConsumo()
	{
		registrosConsumo.add(new RegistroMedicion(LocalDate.now(),this.calcularConsumo()));
	}

	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		// se asume, y se van a guardar de manera ordenada los registros
		return 	registrosConsumo.stream()
				.filter(registro -> registro.estaEntreFechas(registrosConsumo.get(0).fecha(), registrosConsumo.get(registrosConsumo.size()-1).fecha()))
				.mapToDouble(registro -> registro.kwConsumidos())
				.sum();
	}

	// estos metodos se los delega al fabricante que se comunica con el dispositivo fisico.
	
	public void apagarse() {
		fabricante.apagarDispositivo();
	}
	public void encenderse() {
		fabricante.encenderDispositivo();
	}
	public void modoAhorroEnergia() {
		fabricante.activarAhorroDeEnergiaDispositivo();
	}
	public boolean estaEncendido() {
		return fabricante.estaEncendidoDispositivo();
	}
	public boolean estaApagado() {
		return fabricante.estaApagadoDispositivo();
	}

}