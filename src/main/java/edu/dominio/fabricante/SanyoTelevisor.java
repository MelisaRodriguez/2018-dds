package edu.dominio.fabricante;


public class SanyoTelevisor implements Fabricante {
	
	public SanyoTelevisor() {
		super();
	}
	
	@Override
	public void apagar() {
		//dispositivo.apagarse();
		//MOCK
	}

	@Override
	public void encender() {
		//dispositivo.encenderse();
		//MOCK
	}

	@Override
	public void activarAhorroDeEnergia() {
		//dispositivo.encenderse();
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
	
}