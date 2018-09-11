package edu.dominio.usuario;

interface Funcion {
	public boolean operar (double a, double b);
}

public enum Funciones implements Funcion {
	MayorQue{
		public boolean operar (double valor, double limite) {
			return limite >= valor;
		}
	},
	
	MenorQue{
		public boolean operar (double valor, double limite) {
			return limite <= valor;
		}
	},
	
	IgualQue{
		public boolean operar (double valor, double limite) {
			return limite == valor;
		}
	}
}
