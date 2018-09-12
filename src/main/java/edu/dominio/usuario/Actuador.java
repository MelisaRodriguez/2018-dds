package edu.dominio.usuario;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import edu.dominio.empresa.DispositivoInteligente;

@Entity
public class Actuador {
	@Id
	@GeneratedValue
	private int idActuador;
	@ManyToOne(cascade = CascadeType.ALL)
	private DispositivoInteligente dispositivoInteligente;

	public Actuador() {}
	public Actuador(DispositivoInteligente dispositivo)
	{
		this.dispositivoInteligente = dispositivo;
	}

	public void enviarAccion()
	{
		dispositivoInteligente.accionar();
	}
	public DispositivoInteligente getDispositivoInteligente() {
		return dispositivoInteligente;
	}
	public void setDispositivoInteligente(DispositivoInteligente dispositivoInteligente) {
		this.dispositivoInteligente = dispositivoInteligente;
	}

	
}