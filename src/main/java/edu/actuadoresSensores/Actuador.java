package edu.actuadores_sensores;

import edu.empresa.DispositivoInteligente;

public class Actuador {

	private DispositivoInteligente dispositivo;

	public Actuador(DispositivoInteligente dispositivo)
	{
		this.dispositivo = dispositivo;
	}

	public void enviarAccion()
	{
		dispositivo.accionar();
	}

}
