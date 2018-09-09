package edu.dominio.fabricante;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import edu.dominio.empresa.DispositivoInteligente;

@Entity
public class Fabricante {
	
	@Id
	@GeneratedValue
	private int idFabricante;
	private String nombre;
	
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
	public double getKW(DispositivoInteligente d) {
		return fabricanteMock.getKW(d);
	}
	public double getHorasEncendido(DispositivoInteligente d) {
		return fabricanteMock.getHorasEncendido(d);
	}
}