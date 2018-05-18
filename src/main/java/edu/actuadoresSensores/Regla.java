package edu.actuadores_sensores;

import java.util.List;
import java.util.stream.Collectors;

public class Regla {
	
	private List<Sensor> sensores;
	private List<Actuador> actuadores;
	
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
