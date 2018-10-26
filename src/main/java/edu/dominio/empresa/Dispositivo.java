package edu.dominio.empresa;

import java.time.LocalDate;

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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Dispositivo
{
	@Id
	@GeneratedValue
	protected int idDispositivo;
	protected String nombre;
	@ManyToOne(cascade=CascadeType.ALL)
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
	
	public abstract double getCalcularConsumo();	

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
	
	public int getId() {
		return this.idDispositivo;

	}

	public abstract double getPotencia();
	
	public abstract double getConsumoUltimoMes();
	
	
	
	
}