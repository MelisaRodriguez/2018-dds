package edu.fabricante;

import edu.empresa.DispositivoInteligente;

public abstract class AccionesSegunFabricante {
	protected final String className;
	
	public AccionesSegunFabricante() {
		this.className = getClass().getName();
	}	
	
	public abstract void apagar(DispositivoInteligente dispositivo);
	
	public abstract void encender(DispositivoInteligente dispositivo);
}
