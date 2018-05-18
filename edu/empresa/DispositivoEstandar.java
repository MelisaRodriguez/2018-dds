package edu.empresa;

import edu.fabricante.Fabricante;

public class DispositivoEstandar extends Dispositivo {

	public DispositivoEstandar(String nombre, double kW,Fabricante fabricante) {
		super(nombre, kW,fabricante);
		// TODO Auto-generated constructor stub
	}
	
	public DispositivoInteligente adaptar()
	{
		return new DispositivoInteligente(this.nombreGenerico, this.kWxHora,this.fabricante);
	}
}
