package edu.dominio.fabricante;



public interface  Fabricante {

	
	public abstract void apagar();
	
	public abstract void encender();
	
	// solo lo agrego para que compile, ver despu�s
	//public abstract double cuantoConsumeDispositivo();
	public abstract void apagarDispositivo();
	public abstract void encenderDispositivo();
	public abstract void activarAhorroDeEnergiaDispositivo();
}