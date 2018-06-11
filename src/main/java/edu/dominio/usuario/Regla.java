package edu.dominio.usuario;

import java.util.List;
import java.util.stream.Collectors;

public class Regla {
	
	private List<Condicion> condiciones;
	private List<Actuador> actuadores;
	
	public Regla(List<Condicion> condiciones, List<Actuador> actuadores) {
		this.condiciones = condiciones;
		this.actuadores = actuadores;
	}
	
	public boolean revisarCondiciones() {
		return condiciones.stream().allMatch(it -> it.medicionCumpleCondicion());
	}
	
	public void ejecutar() {
		
		if (this.revisarCondiciones())
		{
			actuadores.stream().forEach(actuador -> actuador.enviarAccion());
		} 
	}
}
