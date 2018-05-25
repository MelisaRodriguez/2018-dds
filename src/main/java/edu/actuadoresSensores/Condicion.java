package edu.actuadoresSensores;

import java.util.function.Function;

public class Condicion {
	public Sensor sensor; 
	public Function<Double,Boolean> condicionLogica;
	
	public Condicion(Sensor sensor, Function<Double, Boolean> condicion) {
		this.sensor = sensor;
		this.condicionLogica = condicion;
	}

	public boolean medicionCumpleCondicion () {
		return condicionLogica.apply(sensor.getMedida());
	}
	
}

