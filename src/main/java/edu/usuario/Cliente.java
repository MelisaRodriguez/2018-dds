package edu.usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.empresa.Dispositivo;

public class Cliente {

	private String nombre;
	private String apellido;
	private TipoDocumento tipoDocumento;
	private String nroDocumento;
	private String telefono;
	private String domicilioServicio;
	private LocalDate fechaDeAltaServicio;
	private Categoria categoria;
	private List<Dispositivo> dispositivos;

	public Cliente(String nombre, String apellido, TipoDocumento documento, String nroDocumento, String telefono,
			String domicilioServicio, LocalDate fechaDeAltaServicio, List<Dispositivo> dispositivos) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = documento;
		this.nroDocumento = nroDocumento;
		this.telefono = telefono;
		this.domicilioServicio = domicilioServicio;
		this.fechaDeAltaServicio = fechaDeAltaServicio;
		this.dispositivos = dispositivos;
		this.recategorizar();
	}

	public void recategorizar() {
		this.categoria = RepoCategorias.getSingletonInstance()
				.solicitarCategoria(Categoria -> Categoria.estaEnCategoria(this.consumoTotal()));
	}

	public double consumoTotal() {
		return dispositivos.stream().mapToDouble(dispositivo -> dispositivo.kwConsumoxHora()).sum();
	}

	public boolean tieneDispositivoEncendido() {
		return this.cantDispositivosEncendidos() > 0;
	}

	public int cantDispositivosEncendidos() {
		return this.dispositivosEncendidos().size();
	}

	public int cantDispositivosApagados() {
		return this.cantDispositivos() - this.cantDispositivosEncendidos();
	}

	public List<Dispositivo> dispositivosEncendidos() {
		return this.filtrarDispositivos(dispositivo -> dispositivo.estaEncendido());
	}

	public List<Dispositivo> dispositivosApagados() {
		return this.filtrarDispositivos(dispositivo -> !(dispositivo.estaEncendido()));
	}

	private List<Dispositivo> filtrarDispositivos(Predicate<Dispositivo> unaCondicion) {
		return dispositivos.stream().filter(unaCondicion).collect(Collectors.toList());
	}
	
	public int cantDispositivos() {
		return dispositivos.size();
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(List<Dispositivo> dispositivos) {
		this.dispositivos = dispositivos;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

}