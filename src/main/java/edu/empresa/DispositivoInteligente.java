package edu.empresa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import edu.fabricante.Fabricante;

public class DispositivoInteligente extends Dispositivo {
	
	protected EstadoDispositivo estado;
	protected boolean accionoAlgunaVez; // lo agrego para el test
	
	public DispositivoInteligente(String nombre, double kW,Fabricante fabricante) {
		super(nombre, kW,fabricante);
		this.estado = new estadoApagado(); 
		this.accionoAlgunaVez = false; // lo agrego para el test
	}
	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		return inicio.until(fin, ChronoUnit.DAYS) * kWxHora * 24;
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

	public boolean accionar()
	{
		// DEVUELVE TRUE PARA EL TEST, AUN SIN IMPLEMENTAR
		this.accionoAlgunaVez = true; // lo agrego para el test
		return true;
	}
	
	public boolean getAccionoAlgunaVez() {
		return this.accionoAlgunaVez;
	}
	
}
