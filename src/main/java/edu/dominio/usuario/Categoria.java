package edu.dominio.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
public class Categoria {

	//@Id
	//@GeneratedValue
	private long id;
	private String nombre;
	private double cargoFijo;
	private double cargoVariable;
	private int limiteInferior;
	private long limiteSuperior;

	public Categoria() {}
	
	public Categoria(String nombre, double cargoFijo, double cargoVariable, int limiteInferior, long limiteSuperior) {
		this.nombre = nombre;
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
	}

	public boolean estaEnCategoria(double kwPorHora) {
		return kwPorHora > this.limiteInferior && kwPorHora < this.limiteSuperior;
	}

	public double calcularTarifaEstimada(double kwPorHora) {
		return cargoFijo + (kwPorHora * cargoVariable);
	}
}