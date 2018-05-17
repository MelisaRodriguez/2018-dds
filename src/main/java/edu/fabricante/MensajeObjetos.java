package edu.fabricante;

import edu.empresa.DispositivoInteligente;

public class MensajeObjetos implements AccionesSegunFabricante {

	@Override
	public void apagar(DispositivoInteligente dispositivo) {
		dispositivo.apagarse();
	}

	@Override
	public void encender(DispositivoInteligente dispositivo) {
		dispositivo.encenderse();
	}

}
