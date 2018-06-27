package edu.dominio.usuario;

public class RestriccionConsumo
{
	String tipoRestriccion;  //("<=", ">=", "==")
	double valor;
	
	public String getTipoRestriccion() {
		return this.tipoRestriccion;
	}
	
	public double getValor() {
		return this.valor;
	}
}
