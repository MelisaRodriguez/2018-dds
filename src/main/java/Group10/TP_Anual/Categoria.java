package Group10.TP_Anual;

public class Categoria {
	
	private String nombre;
	private double cargoFijo;
	private double cargoVariable;
	private int limiteInferior;
	private long limiteSuperior;
	
	public Categoria(String nombre, double cargoFijo, double cargoVariable, int limiteInferior, long limiteSuperior) {
		this.nombre = nombre;
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
	public String toString() // NO REQUERIDO, CREADO PARA TESTEAR EL JSON
	{
		return "CATEGORIA: " + nombre + " | CARGO FIJO: " + cargoFijo + " | CARGO VARIABLE: "
				+ cargoVariable + " | LIMITE INFERIOR: " + limiteInferior + " | LIMITE SUPERIOR: "
				+ limiteSuperior;
	}
}
