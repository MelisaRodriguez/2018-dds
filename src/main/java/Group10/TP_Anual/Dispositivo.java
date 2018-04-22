package Group10.TP_Anual;

public class Dispositivo {

	private String nombreGenerico;
	private double kWxHora;
	private boolean encendido;
	
	// DUDO DEL CONSTRUCTOR, CONSULTAR AL PROFE SI ES NECESARIO!
	public Dispositivo(String nombre, double kW)
	{
		this.nombreGenerico = nombre;
		this.kWxHora = kW;
		this.encendido = false; // POR DEFECTO un dispositivo esta¡ apagado
	}
	
	// GETTERS PEDIDOS EN ENTREGA 0
	public String nombreGenerico()
	{
		return nombreGenerico;
	}
	public double kwConsumoxHora()
	{
		return kWxHora;
	}
	public boolean encendido()
	{
		return encendido;
	}
	public boolean estaEncendido() {
		return encendido == true;
	}
	public boolean estaApagado() {
		return encendido == false;
	}
}
