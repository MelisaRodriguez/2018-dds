package edu.dominio.fabricante;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Fabricante {
	
	@Id
	@GeneratedValue
	private int id_Fabricante;
	
	protected String nombre;
	
	public abstract void apagar();
	public abstract void encender();
	public abstract void activarAhorroDeEnergia();	
	public abstract double cuantoConsume();
	public abstract boolean estaEncendido();	
	public abstract boolean estaApagado();
	public abstract boolean estaModoAhorroEnergia();
	public abstract double getPotencia();
	public abstract double getHorasEncendido();
}