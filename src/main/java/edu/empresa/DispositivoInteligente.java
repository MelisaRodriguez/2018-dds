package edu.empresa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DispositivoInteligente extends Dispositivo {

	protected estadoDispositivo estado;
	
	public DispositivoInteligente(String nombre, double kW) {
		super(nombre, kW);
		this.estado = new estadoApagado(this); 
	}

	@Override
	public double consumo(int horas) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double energiaConsumidaEnHoras (int horas) {
		return kWxHora * horas;
	}
	
	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		return inicio.until(fin, ChronoUnit.HOURS) * kWxHora;
	}
	
	public void apagarse () {
		estado.apagarse();
	}
	public void encenderse () {
		estado.encenderse();
	}
	
	public void modoAhorroEnergia() {
		estado.modoAhorroDeEnergia();
	}
	
	public void setEstado(estadoDispositivo estado) {
		this.estado = estado;
	}
	
	public boolean estaEncendido () {
		return estado.estaEncendido();
	}

}
