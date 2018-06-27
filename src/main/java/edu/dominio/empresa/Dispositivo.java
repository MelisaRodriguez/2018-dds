package edu.dominio.empresa;

import edu.dominio.usuario.RestriccionConsumo;

public interface Dispositivo
{
	public double calcularConsumo();	
	public RestriccionConsumo getMinima();
	public RestriccionConsumo getMaxima();
	
}