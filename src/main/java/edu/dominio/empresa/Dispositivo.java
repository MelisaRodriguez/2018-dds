package edu.dominio.empresa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Dispositivo
{
	@Id
	@GeneratedValue
	private int idDispositivo;
	
	public abstract double calcularConsumo();	
	public abstract double getRestriccionMinima();
	public abstract double getRestriccionMaxima();
	public abstract double getPotencia();
	public abstract String getNombre();
}