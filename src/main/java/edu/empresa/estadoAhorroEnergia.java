package edu.empresa;

import java.lang.reflect.Type;

public class estadoAhorroEnergia extends EstadoDispositivo {
	
	private final String className;
	
	public estadoAhorroEnergia() {
        this.className = getClass().getName();
	}
	
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
