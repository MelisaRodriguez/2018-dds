package edu.empresa;

import java.lang.reflect.Type;

public class estadoEncendido extends EstadoDispositivo {

	
	public estadoEncendido() {
        super();
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
