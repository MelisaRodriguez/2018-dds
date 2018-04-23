package Group10.TP_Anual;

public class Categoria {
	double cargoFijo;
	double cargoVariable;
	int limiteInferior;
	long limiteSuperior;
	
	public Categoria(double cargoFijo, double cargoVariable, int limiteInferior, long limiteSuperior) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
		this.limiteInferior = limiteInferior;
		this.limiteSuperior = limiteSuperior;
	}
	
	public boolean estaEnLimites(double kwPorHora)
	{
		return kwPorHora > this.limiteInferior && kwPorHora < this.limiteSuperior;
	}	
	
	public double calcularTarifaEstimada(double kwPorHora)
	{
		return cargoFijo + (kwPorHora*cargoVariable);
	}
}
