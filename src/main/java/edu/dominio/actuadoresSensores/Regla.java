package edu.dominio.actuadoresSensores;

import java.util.List;
import java.util.stream.Collectors;

public class Regla {
	
	private List<Sensor> sensores;
	private List<Actuador> actuadores;
	
	public Regla(List<Sensor> sensores, List<Actuador> actuadores) {
		this.sensores = sensores;
		this.actuadores = actuadores;
	}
	
	public boolean revisarSensores() {

		List<Boolean> resultados = sensores.stream().map(sensor -> sensor.medicionCumpleCondicion()).collect(Collectors.toList());
		return resultados.stream().allMatch(resultado -> resultado == true);
	}
	
	public void ocurrioEvento () {
		
		if (this.revisarSensores())
		{
			actuadores.stream().forEach(actuador -> actuador.enviarAccion());
		}
	}
	
}

