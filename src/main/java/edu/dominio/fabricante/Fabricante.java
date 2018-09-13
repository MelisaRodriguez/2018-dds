package edu.dominio.fabricante;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.dominio.empresa.DispositivoInteligente;

@Entity
@Table(name="Fabricantes")
public class Fabricante {
	
	@Id
	@GeneratedValue
	private int idFabricante;
	private String nombre;
	public void setFabricanteMock(FabricanteMock fabricanteMock) {
		this.fabricanteMock = fabricanteMock;
	}
	@Transient
	private FabricanteMock fabricanteMock;
	
	public Fabricante(){}
	
	public Fabricante(String nombre,FabricanteMock fabricanteMock){
		//this.idFabricante=idFabricante;
		this.nombre=nombre;
		this.fabricanteMock=fabricanteMock;
	}
	
	public void apagar(DispositivoInteligente d) {
		fabricanteMock.apagar(d);
	}
	public void encender(DispositivoInteligente d) {
		fabricanteMock.encender(d);
	}
	public void activarAhorroDeEnergia(DispositivoInteligente d) {
		fabricanteMock.activarAhorroDeEnergia(d);
	}	
	public double cuantoConsume(DispositivoInteligente d) {
		return fabricanteMock.cuantoConsume(d);
	}
	public boolean estaEncendido(DispositivoInteligente d) {
		return fabricanteMock.estaEncendido(d);
	}	
	public boolean estaApagado(DispositivoInteligente d) {
		return fabricanteMock.estaApagado(d);
	}
	public boolean estaModoAhorroEnergia(DispositivoInteligente d) {
		return fabricanteMock.estaModoAhorroEnergia(d);
	}
	public double getPotencia(DispositivoInteligente d) {
		return fabricanteMock.getPotencia(d);
	}
	public double getHorasEncendido(DispositivoInteligente d) {
		return fabricanteMock.getHorasEncendido(d);
	}
}