package edu.fabricante;

import edu.empresa.DispositivoInteligente;

public class EjemploAccion implements AccionesSegunFabricante {

	private final String className;
	
	public EjemploAccion() {
		this.className = getClass().getName();
	}
	
	@Override
	public void apagar(DispositivoInteligente dispositivo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encender(DispositivoInteligente dispositivo) {
		// TODO Auto-generated method stub
		
	}

}
