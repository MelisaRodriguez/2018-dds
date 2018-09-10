package edu.dominio.usuario;

import java.util.function.Function;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

//@Entity
public class Condicion {
	//@Id
	//@GeneratedValue
	private int idCondicion;
	//@ManyToOne
	private Sensor sensor; 
	private Function<Double,Boolean> condicionLogica;
	
	public Condicion() {}
	public Condicion(Sensor sensor, Function<Double, Boolean> condicion) {
		this.sensor = sensor;
		this.condicionLogica = condicion;
	}

	public boolean medicionCumpleCondicion () {
		return condicionLogica.apply(sensor.getMedida());
	}
	public Sensor getSensor() {
		return sensor;
	}
	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	public Function<Double, Boolean> getCondicionLogica() {
		return condicionLogica;
	}
	public void setCondicionLogica(Function<Double, Boolean> condicionLogica) {
		this.condicionLogica = condicionLogica;
	}
	
}