package edu.actuadores_sensores;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class regla {
	
	private List<sensor> listaSensores;
	private List<actuador> listaActuadores;
	
	public boolean revisarSensores() {
		return true; // este return es por que no me gusta la x roja! >=(
		
		// aca hay que hacer que revise si todos los sensores estan en true
		// y si lo estan, llamar a ocurrio evento, si los nombres de los methodos no les copan, los pueden cambiar
	}
	
	public void ocurrioEvento () {
		if (revisarSensores())
		{
			
		}
	}
	
}
