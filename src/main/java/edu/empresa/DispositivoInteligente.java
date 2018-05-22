package edu.empresa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import edu.fabricante.Fabricante;

public class DispositivoInteligente extends Dispositivo {
	
	
	public DispositivoInteligente(String nombre, double kW,Fabricante fabricante) {
		super(nombre, kW,fabricante);
	}
	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) {
		return inicio.until(fin, ChronoUnit.DAYS) * kWxHora * 24;
	}

	public void accionar()
	{}
	
}
