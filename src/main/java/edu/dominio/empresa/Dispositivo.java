package edu.dominio.empresa;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.dominio.fabricante.Fabricante;

@DiscriminatorColumn(name="tipo")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="Dispositivos")
public abstract class Dispositivo
{
	@Id
	@GeneratedValue
	protected int idDispositivo;
	protected String nombre;
	@ManyToOne(cascade=CascadeType.PERSIST)
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