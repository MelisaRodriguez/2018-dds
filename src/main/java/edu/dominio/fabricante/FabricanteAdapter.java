package edu.dominio.fabricante;

import edu.dominio.empresa.DispositivoInteligente;

public enum FabricanteAdapter {
	SONY {
		@Override
		public void apagar(DispositivoInteligente d) {
		}

		@Override
		public void encender(DispositivoInteligente d) {
		}

		@Override
		public void activarAhorroDeEnergia(DispositivoInteligente d) {
		}

		@Override
		public double cuantoConsume(DispositivoInteligente d) {
			return 10;
		}

		@Override
		public boolean estaEncendido(DispositivoInteligente d) {
			return true;
		}

		@Override
		public boolean estaApagado(DispositivoInteligente d) {
			return false;
		}

		@Override
		public boolean estaModoAhorroEnergia(DispositivoInteligente d) {
			return false;
		}

		@Override
		public double getPotencia(DispositivoInteligente d) {
			return 0;
		}

		@Override
		public double getHorasEncendido(DispositivoInteligente d) {
			return 0;
		}

		@Override
		public String getEstado(DispositivoInteligente d) {
			return "Encendido";
		}
	};

	public abstract void apagar(DispositivoInteligente d);

	public abstract void encender(DispositivoInteligente d);

	public abstract void activarAhorroDeEnergia(DispositivoInteligente d);

	public abstract double cuantoConsume(DispositivoInteligente d);

	public abstract boolean estaEncendido(DispositivoInteligente d);

	public abstract boolean estaApagado(DispositivoInteligente d);

	public abstract boolean estaModoAhorroEnergia(DispositivoInteligente d);

	public abstract double getPotencia(DispositivoInteligente d);

	public abstract double getHorasEncendido(DispositivoInteligente d);

	public abstract String getEstado(DispositivoInteligente d);

}
