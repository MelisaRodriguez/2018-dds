package edu.dominio.usuario;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;

@Entity
public class Condicion {
	@Id
	@GeneratedValue
	private int idCondicion;
	@ManyToOne
	private Sensor sensor; 
	@Enumerated
	private Funciones Funcion;
	double limite;
	
	public Condicion() {}
	public Condicion(Sensor sensor,Funciones Funcion, double limite) {
		this.sensor = sensor;
		this.Funcion = Funcion;
		this.limite = limite;
	}

	public boolean medicionCumpleCondicion () {
		return Funcion.operar(sensor.getMedida(), limite);
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public Funciones getFuncion() {
		return Funcion;
	}
	public void setFuncion(Funciones funcion) {
		Funcion = funcion;
	}
	public double getLimite() {
		return limite;
	}
	public void setLimite(double limite) {
		this.limite = limite;
	}
	
}