package edu.empresa;

public class estadoAhorroEnergia implements estadoDispositivo {
	
	public void apagarse (DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new estadoApagado());
	}
	
	public void encenderse (DispositivoInteligente dispositivo) {
		dispositivo.setEstado(new estadoEncendido());
	}
	
	public void modoAhorroDeEnergia (DispositivoInteligente dispositivo) {}
	
	public boolean estaEncendido() {
		return true;
	}
}
