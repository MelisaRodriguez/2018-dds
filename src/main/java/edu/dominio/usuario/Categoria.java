package edu.dominio.usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Categoria {

	@Id
	@GeneratedValue
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getCargoFijo() {
		return cargoFijo;
	}

	public void setCargoFijo(double cargoFijo) {
		this.cargoFijo = cargoFijo;
	}

	public double getCargoVariable() {
		return cargoVariable;
	}

	public void setCargoVariable(double cargoVariable) {
		this.cargoVariable = cargoVariable;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public long getLimiteSuperior() {
		return limiteSuperior;
	}

	public void setLimiteSuperior(long limiteSuperior) {
		this.limiteSuperior = limiteSuperior;
	}
}