package edu.dominio.fabricante;

import edu.dominio.empresa.DispositivoInteligente;

public class MensajeObjetos extends AccionesSegunFabricante {
	
	public MensajeObjetos() {
		super();
	}
	
	@Override
	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.apagarse();
	}

	@Override
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.encenderse();
	}

}