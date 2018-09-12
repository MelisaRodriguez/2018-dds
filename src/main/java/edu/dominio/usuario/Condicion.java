package edu.dominio.usuario;

import java.util.function.Function;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Condicion {
	@Id
	@GeneratedValue
	private int idCondicion;
	@ManyToOne(cascade = CascadeType.ALL)
	private Sensor sensor; 
	
	@Column(name = "condicion")
	@Enumerated(EnumType.STRING)
	private Operador operador;
//	private String operador; // >, <, >=, <=
	private double valor;
	
	@Transient
	private Function<Double,Boolean> condicionLogica;
	
	public Condicion() {}
	public Condicion(Sensor sensor, Function<Double, Boolean> condicion) {
		this.sensor = sensor;
		this.condicionLogica = condicion;
	}
	
	public Condicion(Sensor sensor, Operador operador, double valor)
	{
		this.sensor = sensor;
		this.operador = operador;
		this.valor = valor;
		this.updateCondicionLogica();
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
	public int getIdCondicion() {
		return idCondicion;
	}
	public void setIdCondicion(int idCondicion) {
		this.idCondicion = idCondicion;
	}
	public Operador getOperador() {
		return operador;
	}
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	private void updateCondicionLogica() {
		
		switch (operador)
		{
			case MAYOR:
			{
				this.condicionLogica = (Double medida) -> {return medida > this.valor;};
				break;
			}
			case MAYOR_IGUAL:
			{
				this.condicionLogica = (Double medida) -> {return medida >= this.valor;};
				break;
			}
			case MENOR:
			{
				this.condicionLogica = (Double medida) -> {return medida < this.valor;};
				break;
			}
			case MENOR_IGUAL:
			{
				this.condicionLogica = (Double medida) -> {return medida <= this.valor;};
				break;
			}
		}	
	}
	@Override
	public String toString() {
		return "Condicion [idCondicion=" + idCondicion + ", sensor=" + sensor + ", operador=" + operador + ", valor="
				+ valor + ", condicionLogica=" + condicionLogica + "]";
	}
	
	
	
}