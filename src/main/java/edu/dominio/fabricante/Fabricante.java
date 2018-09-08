package edu.dominio.fabricante;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Fabricante {
	@Id
	@GeneratedValue
	private int idFabricante;
	private String nombre;
	private FabricanteMock fabricanteMock;
	
	public abstract void apagar(DispositivoInteligente d) {
		fabricanteMock.apagar(d);
	}
	public abstract void encender(DispositivoInteligente d) {
		fabricanteMock.encender(d);
	}
	public abstract void activarAhorroDeEnergia(DispositivoInteligente d) {
		fabricanteMock.activarAhorroDeEnergia(d);
	}	
	public abstract double cuantoConsume(DispositivoInteligente d) {
		return fabricanteMock.cuantoConsume(d);
	}
	public abstract boolean estaEncendido(DispositivoInteligente d) {
		return fabricanteMock.estaEncendido(d);
	}	
	public abstract boolean estaApagado(DispositivoInteligente d) {
		return fabricanteMock.estaApagado(d);
	}
	public abstract boolean estaModoAhorroEnergia(DispositivoInteligente d) {
		return fabricanteMock.estaModoAhorroEnergia(d);
	}
	public abstract double getPotencia(DispositivoInteligente d) {
		return fabricanteMock.getPotencia(d);
	}
	public abstract double getHorasEncendido(DispositivoInteligente d) {
		return fabricanteMock.getHorasEncendido(d);
	}
}