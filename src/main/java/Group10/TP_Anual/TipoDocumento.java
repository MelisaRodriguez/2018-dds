package Group10.TP_Anual;

public enum TipoDocumento {
	DNI("DNI"),
	CI("CI"),
	LC("LC"),
	LE("LE");
	
	private String tipo;
	private TipoDocumento(String tipo)
	{
		this.tipo = tipo;
	}
}
