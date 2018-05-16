package edu.empresa;

public class estadoEncendido implements estadoDispositivo {

	
	public void apagarse (DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new estadoApagado());
	}
	
	public void encenderse (DispositivoInteligente dispositivo) {}
	
	public void modoAhorroDeEnergia (DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new estadoAhorroEnergia());
	}
	
	public boolean estaEncendido() {
		return true;
	}
}
