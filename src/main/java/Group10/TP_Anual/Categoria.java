package Group10.TP_Anual;

public abstract class Categoria {
	Double cargoFijo;
	Double cargoVariable;
	
	public Categoria(Double cargoFijo, Double cargoVariable) {
		this.cargoFijo = cargoFijo;
		this.cargoVariable = cargoVariable;
	}
	
	public Double calcularTarifa(Double consumo) {
		return cargoFijo + consumo*cargoVariable;
	}
	
	public abstract boolean perteneceAEstaCategoria(Double consumo);

	//Propongo, que cada clase tenga su propia condicion
	
	//R1->return Consumo<150
	//R2->return 150<Consumo<=325 
	//R3->return 325<Consumo<=400 ,...Etc
	
	//=> tener una lista de categorias en otra clase (tirada) 
	
	// Despues hacer algo como: 
	
	/*
	 * Cliente.Recategorizar(){
	 * 	this.categoria= listaCategorias.find( x->x.perteneceAEstaCategoria(this.getConsumo() ) ). first();//verificar sintax
	 *
	 * agregar horas utilizadas en el dispositivo para esta sol.
	 * 
	 *preguntar donde guardar clientes y dispositivos (osea en que parte del programa los busco)
	 * }
	 */
}
