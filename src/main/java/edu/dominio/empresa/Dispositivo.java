package edu.dominio.empresa;

public interface Dispositivo
{
	public double calcularConsumo();
	public void apagarse();
	public void encenderse();
	public void modoAhorroEnergia();
	public void estaEncendido();
	public void estaApagado();
}