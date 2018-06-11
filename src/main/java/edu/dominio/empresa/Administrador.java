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
}