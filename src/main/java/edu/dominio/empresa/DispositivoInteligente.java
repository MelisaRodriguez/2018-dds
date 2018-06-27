package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.dominio.fabricante.Fabricante;
import edu.dominio.usuario.RestriccionConsumo;

public class DispositivoInteligente implements Dispositivo {
	
	private String nombre;
	private LocalDate fechaDeRegistro;
	private Fabricante fabricante; // Adapter
	private List<RegistroMedicion> registrosConsumo; //Se asume ordenada por fecha
	private RestriccionConsumo minima;
	private RestriccionConsumo maxima;
	
	public DispositivoInteligente(String nombre, LocalDate fechaDeRegistro, Fabricante fabricante) {
		this.nombre = nombre;
		this.fechaDeRegistro = fechaDeRegistro;
		this.fabricante = fabricante;
		this.registrosConsumo = new ArrayList<RegistroMedicion>();
	}

	public double calcularConsumo() 
	{
		return fabricante.cuantoConsume();
	}

	// Este metodo se ejecutara automaticamente con un cron programado cuando se acabe la memoria del dispositivo.
	public void agregarNuevoRegistroDeConsumo()
	{
		registrosConsumo.add(new RegistroMedicion(LocalDate.now(),this.calcularConsumo()));
	}

	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) { 
		// se asume, y se van a guardar de manera ordenada los registros
		return 	registrosConsumo.stream()
				.filter(registro -> registro.estaEntreFechas(inicio, fin))
				.mapToDouble(registro -> registro.kwConsumidos())
				.sum();
	}

	// estos metodos se los delega al fabricante que se comunica con el dispositivo fisico.
	
	public void apagarse() {
		fabricante.apagar();
	}
	
	public void encenderse() {
		fabricante.encender();
	}
	
	public void modoAhorroEnergia() {
		fabricante.activarAhorroDeEnergia();
	}
	
	public boolean estaEncendido() {
		return fabricante.estaEncendido();
	}
	
	public boolean estaApagado() {
		return fabricante.estaApagado();
	}
	
	public boolean estaModoAhorroEnergia() {
		return fabricante.estaModoAhorroEnergia();
	}
	
	public void accionar() { // se desconoce implementación, al menos hasta entrega 1.
	}
	
	public RestriccionConsumo getMinima()
	{
		return this.minima;
	}
	
	public RestriccionConsumo getMaxima()
	{
		return this.maxima;
	}

}