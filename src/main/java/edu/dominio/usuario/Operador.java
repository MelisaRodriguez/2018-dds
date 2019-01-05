package edu.dominio.usuario;

public enum Operador {
	MAYOR {
		@Override
		public boolean ejecutarCondLogica(double val1, double val2) {
			return val1 > val2;
		}
	}, MENOR {
		@Override
		public boolean ejecutarCondLogica(double val1, double val2) {
			return val1 < val2;
		}
	}, MAYOR_IGUAL {
		@Override
		public boolean ejecutarCondLogica(double val1, double val2) {
			return val1 >= val2;
		}
	}, MENOR_IGUAL {
		@Override
		public boolean ejecutarCondLogica(double val1, double val2) {
			return val1 <= val2;
		}
	};
	
	public abstract boolean ejecutarCondLogica(double val1, double val2);
}
