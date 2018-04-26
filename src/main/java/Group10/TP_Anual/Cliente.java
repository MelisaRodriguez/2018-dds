package Group10.TP_Anual;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cliente {

	@SerializedName("nombre")
	@Expose
	private String nombre;
	@SerializedName("apellido")
	@Expose
	private String apellido;
	@SerializedName("tipoDocumento")
	@Expose
	private TipoDocumento tipoDocumento;
	@SerializedName("nroDocumento")
	@Expose
	private int nroDocumento;
	@SerializedName("telefono")
	@Expose
	private int telefono;
	@SerializedName("domicilioServicio")
	@Expose
	private String domicilioServicio;
	@SerializedName("fechaDeAltaServicio")
	@Expose
	private Fecha fechaDeAltaServicio;
	@SerializedName("categoria")
	@Expose
	private Categoria categoria;
	@SerializedName("dispositivos")
	@Expose
	private List<Dispositivo> dispositivos;

	public Cliente(String nombre, String apellido, TipoDocumento documento,  int nroDocumento, int telefono, String domicilioServicio, Fecha fechaDeAltaServicio, List<Dispositivo> dispositivos) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = documento;
		this.nroDocumento = nroDocumento;
		this.telefono = telefono;
		this.domicilioServicio = domicilioServicio;
		this.fechaDeAltaServicio = fechaDeAltaServicio;
		this.dispositivos = dispositivos;
		this.categoria = RepoCategorias.solicitarCategoria(this.consumoTotal());
	}
	
	public void recategorizar() {
		this.categoria = RepoCategorias.solicitarCategoria(this.consumoTotal());
	}
	
	private double consumoTotal() {
		return dispositivos.stream().mapToDouble(dispositivo -> dispositivo.kwConsumoxHora()).sum();
	}
	
	public boolean existeDispositivoEncendido() {
		return dispositivos.stream().anyMatch(dispositivo -> dispositivo.estaEncendido());
	}
	
	public int cantDispositivosEncendidos() {
		return this.dispositivosEncendidos().size();
	}
	
	public int cantDispositivosApagados() {
		return this.dispositivosApagados().size();
	}
	
	public List<Dispositivo> dispositivosEncendidos() {
		return dispositivos.stream().filter(dispositivo -> dispositivo.estaEncendido()).collect(Collectors.toList());
	}
	
	public List<Dispositivo> dispositivosApagados() {
		return dispositivos.stream().filter(dispositivo -> !(dispositivo.estaEncendido())).collect(Collectors.toList());
	}
	
	public int cantDispositivos() {
		return dispositivos.size();
	}
	public String toString() // NO REQUERIDO, CREADO PARA TESTEAR EL JSON
	{
		return ("Nombre: " + nombre + " | " + "Apellido: " + apellido + " | " +
	            "Tipo de Documento: " + tipoDocumento + " | " + "N�mero de documento: " +
				nroDocumento + " | " + "Telefono: " + telefono + " | " + 
	            "Domicilio de Servicio: " + domicilioServicio + " | " + 
				"Fecha de alta de servicio: " + fechaDeAltaServicio.toString()
	            + " | " + categoria.toString());
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

}