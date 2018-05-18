package edu.actuadoresSensores;

import java.util.function.Function;

public class Sensor {
	
	public double medida;
	public Function<Double,Boolean> condicion;

	public Sensor(Function<Double, Boolean> condicion) {
		this.condicion = condicion;
	}

	public void tomarMedicion(double medida)
	{
		this.medida = medida;
	}

	public boolean medicionCumpleCondicion () {
		return condicion.apply(medida);
	}
}
