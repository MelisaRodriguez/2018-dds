package edu.dominio.fabricante;



public interface  Fabricante {
	
	public abstract void apagar();
	public abstract void encender();
	public abstract void activarAhorroDeEnergia();	
	public abstract double cuantoConsume();
	public abstract boolean estaEncendido();	
	public abstract boolean estaApagado();
	public abstract boolean estaModoAhorroEnergia();
	
}