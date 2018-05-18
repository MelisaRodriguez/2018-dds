package edu.empresa;

import java.lang.reflect.Type;

public class estadoEncendido extends EstadoDispositivo {

	private final String className;
	
	public estadoEncendido() {
        this.className = getClass().getName();
	}
	
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
