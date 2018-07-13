package edu.dominio.fabricante;


public class SanyoTelevisor implements Fabricante {
	
	@Override
	public void apagar() {
		//MOCK
	}

	@Override
	public void encender() {
		//MOCK
	}

	@Override
	public void activarAhorroDeEnergia() {
		//MOCK
	}
	
	@Override
	public double cuantoConsume() {
		return 1.0;
		//MOCK
	}
	
	@Override
	public boolean estaEncendido() {
		return true;
		//MOCK
	}
	
	@Override
	public boolean estaApagado() {
		return true;
		//MOCK
	}
	
	@Override
	public boolean estaModoAhorroEnergia() {
		return true;
		//MOCK
	}

	@Override
	public double getPotencia() {
		return 1.0;
		// MOCK
	}
	
	@Override
	public double getHorasEncendido() {
		return 1.0;
		// MOCK
	}
	
}