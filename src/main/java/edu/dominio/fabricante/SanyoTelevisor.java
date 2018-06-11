package edu.dominio.fabricante;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.AccionesSegunFabricante;

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