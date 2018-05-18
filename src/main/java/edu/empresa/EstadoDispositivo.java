package edu.empresa;

public abstract class EstadoDispositivo {
	
	protected final String className;
	
	public EstadoDispositivo() {
        this.className = getClass().getName();
	}
	
	public abstract void apagarse (DispositivoInteligente dispositivo);
	public abstract void encenderse (DispositivoInteligente dispositivo);
	public abstract void modoAhorroDeEnergia (DispositivoInteligente dispositivo);
	public abstract boolean estaEncendido();
		
}
