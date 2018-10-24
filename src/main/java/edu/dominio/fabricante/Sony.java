package edu.dominio.fabricante;

import edu.dominio.empresa.DispositivoInteligente;

public class Sony implements FabricanteMock{

	public Sony() {}
	@Override
	public void apagar(DispositivoInteligente d) {}

	@Override
	public void encender(DispositivoInteligente d) {}

	@Override
	public void activarAhorroDeEnergia(DispositivoInteligente d) {}

	@Override
	public double cuantoConsume(DispositivoInteligente d) {
		return 10;
	}

	@Override
	public boolean estaEncendido(DispositivoInteligente d) {
		return true;
	}

	@Override
	public boolean estaApagado(DispositivoInteligente d) {
		return false;
	}

	@Override
	public boolean estaModoAhorroEnergia(DispositivoInteligente d) {
		return false;
	}

	@Override
	public double getPotencia(DispositivoInteligente d) {
		return 0;
	}

	@Override
	public double getHorasEncendido(DispositivoInteligente d) {
		return 0;
	}
	
	@Override
	public String getEstado(DispositivoInteligente d) {
		return "Encendido";
	}
}