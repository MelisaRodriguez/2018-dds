package edu.dominio.empresa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import edu.dominio.fabricante.Fabricante;

@Entity
public abstract class Dispositivo
{
	@Id
	@GeneratedValue
	private int idDispositivo;
	protected String nombre;
	protected Fabricante fabricante;
	protected double restriccionMinima;
	protected double restriccionMaxima;
	
	public Dispositivo(String nombre, Fabricante fabricante, double restriccionMinima, double restriccionMaxima) {
		this.nombre = nombre;
		this.fabricante = fabricante;
		this.restriccionMinima = restriccionMinima;
		this.restriccionMaxima = restriccionMaxima;
	}
	
	public Dispositivo() {}
	
	public abstract double calcularConsumo();	

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getRestriccionMinima() {
		return restriccionMinima;
	}
	
	public void setRestriccionMinima(double restriccionMinima) {
		this.restriccionMinima = restriccionMinima;
	}

	public double getRestriccionMaxima() {
		return restriccionMaxima;
	}
	public void setRestriccionMaxima(double restriccionMaxima) {
		this.restriccionMaxima = restriccionMaxima;
	}
	

	public abstract double getPotencia();
	
}