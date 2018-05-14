package edu.empresa;

public class DispositivoEstandar extends Dispositivo {

	public DispositivoEstandar(String nombre, double kW) {
		super(nombre, kW);
		// TODO Auto-generated constructor stub
	}
	
	public DispositivoInteligente adaptar()
	{
		return new DispositivoInteligente(this.nombreGenerico, this.kWxHora);
	}

}
