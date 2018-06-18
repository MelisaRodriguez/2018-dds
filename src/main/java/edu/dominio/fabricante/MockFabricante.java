package edu.dominio.fabricante;

public class MockFabricante implements Fabricante {

	@Override
	public void apagar() {
		// TODO MOCK

	}

	@Override
	public void encender() {
		// TODO MOCK
	}

	// solo lo agrego para que compile, ver despues
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
