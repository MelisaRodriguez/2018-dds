package edu.dominio.empresa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;

public class ZonaGeografica {
	
	private String ID;
	private List<Transformador> transformadores = new ArrayList<>();
	private double radio;
	private Punto centro;
	
	public List<Transformador> getTransformadores() {
		return transformadores;
	}

	public double getRadio() {
		return radio;
	}

	public ZonaGeografica(String ID, Punto centro, double radio)
	{
		this.ID = ID;
		this.centro = centro;
		this.radio = radio;
	}
	
	public Punto getcentro() {
		return this.centro;
	}
	
	public String getID()
	{
		return this.ID;
	}
	
	public void agregarTransformador(Transformador nuevoTransformador)
	{
		transformadores.add(nuevoTransformador);
	}
	
	private double calcularDistancia(Punto p1, Punto p2)
	{
		return Punto.calcularDistancia(p1, p2);
	}
	
	public void agregarCliente(Cliente unCliente, Punto lugar)
	{
		
		transformadores.stream().min(Comparator.comparing(t->calcularDistancia(t.getLugar(), lugar))).get().agregarCliente(unCliente);
	}
	
	public void agregarTransformadores(List<Transformador> nuevosTransformadores)
	{
		this.transformadores.addAll(nuevosTransformadores);
	}
	
	public double verConsumo()
	{
		return this.transformadores.stream().mapToDouble(transf->transf.calcularConsumo()).sum();
	}
	
	public boolean estaEnRango(Punto unPunto)
	{
		return radio > this.calcularDistancia(unPunto, centro);
	}
	
}
