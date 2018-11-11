package edu.dominio.empresa;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RegistrosMedicion")
public class RegistroMedicion {

	@Id
	@GeneratedValue
	private int idRegistro;
	private LocalDate fecha;
	private double kwConsumidos;
	private double horasEncendido;

	public RegistroMedicion(LocalDate fecha, double kwConsumidos, double horasEncendido) {
		this.fecha = fecha;
		this.kwConsumidos = kwConsumidos;
		this.horasEncendido = horasEncendido;
	}

	public RegistroMedicion() {
	}

	public boolean estaEntreFechas(LocalDate inicio, LocalDate fin) {
		return inicio.isBefore(fecha) && fin.isAfter(fecha);
	}

	public LocalDate fecha() {
		return fecha;
	}

	public double horasEncendido() {
		return horasEncendido;
	}

	public double kwConsumidos() {
		return kwConsumidos;
	}

	@Override
	public String toString() {
		return "RegistroMedicion [idRegistro=" + idRegistro + ", fecha=" + fecha + ", kwConsumidos=" + kwConsumidos
				+ ", horasEncendido=" + horasEncendido + "]";
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public double getKwConsumidos() {
		return kwConsumidos;
	}

	public void setKwConsumidos(double kwConsumidos) {
		this.kwConsumidos = kwConsumidos;
	}

	public double getHorasEncendido() {
		return horasEncendido;
	}

	public void setHorasEncendido(double horasEncendido) {
		this.horasEncendido = horasEncendido;
	}
}