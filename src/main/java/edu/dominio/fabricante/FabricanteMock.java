package edu.dominio.fabricante;

import edu.dominio.empresa.DispositivoInteligente;


public interface FabricanteMock {
	
	public void apagar(DispositivoInteligente d);
	public void encender(DispositivoInteligente d);
	public void activarAhorroDeEnergia(DispositivoInteligente d);
	public double cuantoConsume(DispositivoInteligente d);
	public boolean estaEncendido(DispositivoInteligente d) ;
	public boolean estaApagado(DispositivoInteligente d) ;
	public boolean estaModoAhorroEnergia(DispositivoInteligente d) ;
	public double getPotencia(DispositivoInteligente d);
	public double getHorasEncendido(DispositivoInteligente d);
}
