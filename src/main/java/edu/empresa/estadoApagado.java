package edu.empresa;

public class estadoApagado implements estadoDispositivo {

	public void apagarse (DispositivoInteligente dispositivo) {}
	public void encenderse (DispositivoInteligente dispositivo) {}
	public void modoAhorroDeEnergia (DispositivoInteligente dispositivo) {}
	public boolean estaEncendido() {
		return false;
	}
	
}
