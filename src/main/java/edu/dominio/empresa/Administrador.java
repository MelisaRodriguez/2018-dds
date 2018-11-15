package edu.dominio.empresa;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Administrador {

	private String nombre;
	private String apellido;
	private String domicilio;
	private int nroIdentificacion;
	private LocalDate fechaDeAlta;

	public Administrador(String nombre, String apellido, String domicilio, int nroIdentificacion,
			LocalDate fechaDeAlta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.nroIdentificacion = nroIdentificacion;
		this.fechaDeAlta = fechaDeAlta;
	}

	public long getMesesComoAdmin() {
		return fechaDeAlta.until(LocalDate.now(), ChronoUnit.MONTHS);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(int nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public LocalDate getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(LocalDate fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

}