package edu.dominio.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Sensor {
	//@Id
	//@GeneratedValue
	private int idSensor;
	private double medida;
	
	public void tomarMedicion(double medida)
	{
		this.medida = medida;
	}
	
	public double getMedida()
	{
		return medida;
	}

	public void setMedida(double medida) {
		this.medida = medida;
	}	
	
	
}