package edu.actuadores_sensores;

import java.util.function.Function;

public class sensor {
	
	public int valor;
	public Function<Integer,Boolean> condicion;

	public sensor(int valor, Function<Integer, Boolean> condicion) {
		this.valor = valor;
		this.condicion = condicion;
	}

	public boolean seCumplio () {
		return condicion.apply(valor);
	}
}
