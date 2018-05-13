package edu.empresa;

public class estadoEncendido implements estadoDispositivo {
	
	private DispositivoInteligente dispositivo;
	
	public estadoEncendido(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	public void apagarse () {
		dispositivo.setEstado(new estadoApagado(dispositivo));
	}
	
	public void encenderse () {}
	
	public void modoAhorroDeEnergia () {
		dispositivo.setEstado(new estadoAhorroEnergia(dispositivo));
	}
	
	public boolean estaEncendido() {
		return true;
	}
}
