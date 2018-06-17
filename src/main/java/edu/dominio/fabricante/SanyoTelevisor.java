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
	
	// solo lo agrego para que compile, ver despues
	@Override
	public void apagarDispositivo() {
		//dispositivo.apagarse();
		//MOCK
	}

	@Override
	public void encenderDispositivo() {
		//dispositivo.encenderse();
		//MOCK
	}

	@Override
	public void activarAhorroDeEnergiaDispositivo() {
		//dispositivo.encenderse();
		//MOCK
	}
	
}