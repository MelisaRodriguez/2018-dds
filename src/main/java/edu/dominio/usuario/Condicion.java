package edu.dominio.usuario;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
//	operador: >, <, >=, <=
	private double valor;

	public Condicion() {
	}

	public Condicion(Sensor sensor, Operador op) {
		this.sensor = sensor;
		this.operador = op;
	}

	public Condicion(Sensor sensor, Operador operador, double valor) {
		this.sensor = sensor;
		this.operador = operador;
		this.valor = valor;
	}

	public boolean medicionCumpleCondicion() {
		return operador.ejecutarCondLogica(sensor.getMedida(), this.valor);
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
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

}