package edu.empresa;

import java.lang.reflect.Type;

public class estadoApagado extends EstadoDispositivo {

	public estadoApagado() {
       super();
	}
	
	public void apagarse (DispositivoInteligente dispositivo) {}
	public void encenderse (DispositivoInteligente dispositivo) {}
	public void modoAhorroDeEnergia (DispositivoInteligente dispositivo) {}
	public boolean estaEncendido() {
		return false;
	}
	
}
