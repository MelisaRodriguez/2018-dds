package edu.empresa;

public class estadoApagado implements estadoDispositivo {
	
	private DispositivoInteligente dispositivo;
	
	public estadoApagado(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	public void apagarse () {}
	public void encenderse () {}
	public void modoAhorroDeEnergia () {}
	public boolean estaEncendido() {
		return false;
	}
	
}
