package edu.dominio.usuario;

import edu.dominio.empresa.DispositivoInteligente;

public class Actuador {

	private DispositivoInteligente dispositivoInteligente;

	public Actuador(DispositivoInteligente dispositivo)
	{
		this.dispositivoInteligente = dispositivo;
	}

	public void enviarAccion()
	{
		dispositivoInteligente.accionar();
	}

}