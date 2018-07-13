package edu.dominio.usuario;

import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.ZonaGeografica;
import edu.repositorios.RepoCategorias;
import edu.repositorios.RepoZonaGeografica;


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
	private int puntos;
	private Point2D.Double ubicacion;
	//private Transformador miTransformador;

	
	public Cliente(String nombre, String apellido, TipoDocumento documento, String nroDocumento, String telefono,
			String domicilioServicio, LocalDate fechaDeAltaServicio, List<DispositivoInteligente> dispositivosI,
			List<DispositivoEstandar> dispositivosEstandar, Point2D.Double ubicacion) {
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
		this.ubicacion = ubicacion;
		
		ZonaGeografica bolivia=new ZonaGeografica("Bolivia1",new Point2D.Double(-0.127512, 51.507222),8000);
		
		RepoZonaGeografica.getSingletonInstance().SolicitarTransformador(this, ubicacion);
	}
	
	public void recategorizar() {
		this.categoria = RepoCategorias.getSingletonInstance()
				.solicitarCategoria(Categoria -> Categoria.estaEnCategoria(this.consumoTotal()));
	}

	private List<Dispositivo> todosSusDispositivos() {
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
		return this.todosSusDispositivos().stream().mapToDouble(dispositivo -> dispositivo.calcularConsumo()).sum();
	}	

	private List<DispositivoInteligente> filtrarDispositivos(Predicate<DispositivoInteligente> unaCondicion) {
		return dispositivosInteligentes.stream().filter(unaCondicion).collect(Collectors.toList());
	}

	public List<DispositivoInteligente> dispositivosEncendidos() {
		return this.filtrarDispositivos(dispositivo -> dispositivo.estaEncendido());
	}

	public int cantDispositivosEncendidos() {
		return this.dispositivosEncendidos().size();
	}

	public boolean tieneDispositivoEncendido() {
		return this.cantDispositivosEncendidos() > 0;
	}

	public List<DispositivoInteligente> dispositivosApagados() {
		return this.filtrarDispositivos(dispositivo -> !(dispositivo.estaEncendido()));
	}

	public int cantDispositivosApagados() {
		return this.dispositivosApagados().size();
	}

	public int cantDispositivosEnTotal() {
		return this.todosSusDispositivos().size();
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void convertirDispositivo(int indice) {
		this.dispositivosInteligentes.add(this.dispositivosEstandar.get(indice).convertirAInteligente());
		this.dispositivosEstandar.remove(indice);
		this.puntos += 10;
	}
	
	public int getPuntos() {
		return this.puntos;
	}
	
}