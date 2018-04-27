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
	public int getDia() {
		return dia;
	}
	
	public int getMes() {
		return mes;
	}
	
	public int getAnio() {
		return anio;
	}
	
}
