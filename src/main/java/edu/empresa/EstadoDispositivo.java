package edu.empresa;

import edu.dominio.empresa.DispositivoInteligente;

public abstract class EstadoDispositivo {
	
	public abstract void apagarse (DispositivoInteligente dispositivo);
	public abstract void encenderse (DispositivoInteligente dispositivo);
	public abstract void modoAhorroDeEnergia (DispositivoInteligente dispositivo);
	public abstract boolean estaEncendido();
		
}
