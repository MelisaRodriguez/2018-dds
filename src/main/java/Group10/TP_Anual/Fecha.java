package Group10.TP_Anual;

public class Fecha {

	private int dia;
	private int mes;
	private int anio;
	
	public Fecha(int dia, int mes, int anio)
	{
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
	}
	// NO REQUERIDO, CREADO PARA TESTEAR EL JSON
	public String toString()
	{
		return dia+"/"+mes+"/"+anio;
	}
<<<<<<< HEAD
=======
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getAnio() {
		return anio;
	}
	
>>>>>>> 2ee481694f44a5c37d915a7aae91f871f6d6e748
}
