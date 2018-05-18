package edu.usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.empresa.Dispositivo;
import edu.empresa.DispositivoEstandar;
import edu.empresa.DispositivoInteligente;


public class Cliente {

	private String nombre;
	private String apellido;
	private TipoDocumento tipoDocumento;
	private String nroDocumento;
	private String telefono;
	private String domicilioServicio;
	private LocalDate fechaDeAltaServicio;
	private Categoria categoria;
	private List<DispositivoInteligente> dispositivosInteligentes;
	private List<DispositivoEstandar> dispositivosEstandar;
	private long puntos;

	
	public Cliente(String nombre, String apellido, TipoDocumento documento, String nroDocumento, String telefono,
			String domicilioServicio, LocalDate fechaDeAltaServicio, List<DispositivoInteligente> dispositivosI,
			List<DispositivoEstandar> dispositivosEstandar) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = documento;
		this.nroDocumento = nroDocumento;
		this.telefono = telefono;
		this.domicilioServicio = domicilioServicio;
		this.fechaDeAltaServicio = fechaDeAltaServicio;
		this.dispositivosInteligentes = dispositivosI;
		this.puntos = this.dispositivosInteligentes.size() * 15;
		this.dispositivosEstandar = dispositivosEstandar;
		this.recategorizar();
	}
	
	public void recategorizar() {
		this.categoria = RepoCategorias.getSingletonInstance()
				.solicitarCategoria(Categoria -> Categoria.estaEnCategoria(this.consumoTotal()));
	}

	private List<Dispositivo> dispositivosTotales() {
		return Stream.concat(dispositivosInteligentes.stream(), dispositivosEstandar.stream())
				.collect(Collectors.toList());
	}

	public void agregarDispositivo(DispositivoInteligente unDispositivo) {
		this.dispositivosInteligentes.add(unDispositivo);
		puntos += 15;
	}
	
	public void agregarDispositivo(DispositivoEstandar unDispositivo) {
		this.dispositivosEstandar.add(unDispositivo);
	}

	public double consumoTotal() {
		return this.dispositivosTotales().stream().mapToDouble(dispositivo -> dispositivo.kwConsumoxHora()).sum();
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

	public List<DispositivoInteligente> dispositivosEncendidos() {
		return this.filtrarDispositivos(dispositivo -> dispositivo.estaEncendido());
	}

	public List<DispositivoInteligente> dispositivosApagados() {
		return this.filtrarDispositivos(dispositivo -> !(dispositivo.estaEncendido()));
	}

	private List<DispositivoInteligente> filtrarDispositivos(Predicate<DispositivoInteligente> unaCondicion) {
		return dispositivosInteligentes.stream().filter(unaCondicion).collect(Collectors.toList());
	}

	public int cantDispositivos() {
		return dispositivosTotales().size();
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void convertirDispositivo(int indice) {
		this.dispositivosInteligentes.add(this.dispositivosEstandar.get(indice).adaptar());
		this.puntos += 10;
	}
	
	public long getPuntos() {
		return this.puntos;
	}
	
}
