package edu.empresa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import edu.fabricante.Fabricante;

public class DispositivoInteligente extends Dispositivo {
	
	public EstadoDispositivo estado;
	
	public DispositivoInteligente(String nombre, double kW,Fabricante fabricante) {
		super(nombre, kW,fabricante);
		this.estado = new estadoApagado(); 
	}
	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		return inicio.until(fin, ChronoUnit.HOURS) * kWxHora;
	}
	public void apagarse () {
		estado.apagarse(this);
	}
	public void encenderse () {
		estado.encenderse(this);
	}
	
	public void modoAhorroEnergia() {
		estado.modoAhorroDeEnergia(this);
	}
	
	public void setEstado(EstadoDispositivo estado) {
		this.estado = estado;
	}
	public boolean estaEncendido() {
		return estado.estaEncendido();
	}
}
