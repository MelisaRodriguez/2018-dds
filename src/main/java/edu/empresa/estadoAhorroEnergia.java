package edu.empresa;

public class estadoAhorroEnergia implements estadoDispositivo {
	
	private DispositivoInteligente dispositivo;
	
	public estadoAhorroEnergia(DispositivoInteligente dispositivo) {
		this.dispositivo = dispositivo;
	}
	
	public void apagarse () {
		dispositivo.setEstado(new estadoApagado(dispositivo));
	}
	
	public void encenderse () {
		dispositivo.setEstado(new estadoEncendido(dispositivo));
	}
	
	public void modoAhorroDeEnergia () {}
	
	public boolean estaEncendido() {
		return true;
	}
}
