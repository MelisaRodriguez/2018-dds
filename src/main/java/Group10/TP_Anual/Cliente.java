package Group10.TP_Anual;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import Group10.TP_Anual.TipoDocumento;

public class Cliente {
	private String nombre;
	private String apellido;
	private TipoDocumento documento;
	private int nroDocumento;
	private int telefono;
	private String domicilioServicio;
	private LocalDate fechaDeAltaServicio;
	private Categoria categoria;
	private List<Dispositivo> dispositivos = new ArrayList<>();
	
	public Cliente(String nombre, String apellido, TipoDocumento documento,  int nroDocumento, int telefono, String domicilioServicio, LocalDate fechaDeAltaServicio, List<Dispositivo> dispositivos ) 
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
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
		return dispositivos.stream().filter(dispositivo -> dispositivo.encendido()).collect(Collectors.toList());
	}
	
	public List<Dispositivo> dispositivosApagados() {
		return dispositivos.stream().filter(dispositivo -> dispositivo.estaApagado()).collect(Collectors.toList());
	}
	
	public int cantDispositivos() {
		return dispositivos.size();
	}
}
