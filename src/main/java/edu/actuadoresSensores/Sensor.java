package edu.actuadoresSensores;

public class Sensor {
	
	public double medida;
	
	public void tomarMedicion(double medida)
	{
		this.medida = medida;
	}
	
	public double getMedida()
	{
		return medida;
	}	
}
