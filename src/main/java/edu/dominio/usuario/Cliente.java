package edu.dominio.usuario;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.Simplex;
import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Categoria;
import edu.repositorios.RepoCategorias;
import edu.repositorios.RepoZonaGeografica;

@Entity
public class Cliente {

	@Id
	@GeneratedValue
	private int idCliente;
	private String nombre;
	private String apellido;
	@Enumerated(EnumType.ORDINAL)
	private TipoDocumento tipoDocumento;
	private String nroDocumento;
	private String telefono;
	private String domicilioServicio;
	private LocalDate fechaDeAltaServicio;
	@ManyToOne(cascade=CascadeType.ALL)
	private Categoria categoria;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private List<DispositivoInteligente> dispositivosInteligentes;
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private List<DispositivoEstandar> dispositivosEstandar;
	private int puntos;
	private boolean ahorroAutomatico;
	@Embedded
	private Punto ubicacion;

	public Cliente() {}
	public Cliente(String nombre, String apellido, TipoDocumento documento, String nroDocumento, String telefono,
			String domicilioServicio, LocalDate fechaDeAltaServicio, List<DispositivoInteligente> dispositivosI,
			List<DispositivoEstandar> dispositivosEstandar, boolean ahorroAutomatico, Punto ubicacion) {
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
		this.ahorroAutomatico = ahorroAutomatico;
		this.ubicacion = ubicacion;		
		RepoZonaGeografica.getSingletonInstance().SolicitarTransformador(this, ubicacion);

	}

	public void recategorizar() {
		this.categoria = RepoCategorias.getSingletonInstance()
				.solicitarCategoria(Categoria -> Categoria.estaEnCategoria(this.consumoTotal()));
	}

	public List<Dispositivo> todosSusDispositivos() {
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
	
	public double consumoTotalEnPeriodo(LocalDate inicio, LocalDate fin) {
		return dispositivosInteligentes.stream().mapToDouble(dispositivo -> dispositivo.consumoTotalEnPeriodo(inicio, fin)).sum();
	}
	
	public double cantRegistrosMedicion() {
		return dispositivosInteligentes.stream().mapToDouble(d -> d.getRegistrosConsumo().size()).sum();
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
	
	public void solicitarRecomendacion(double restriccionMaxima)
	{
		new Simplex(restriccionMaxima).generarRecomendacion(this);
	} 

	public void setAhorroAutomatico(boolean ahorro)
	{
		this.ahorroAutomatico = ahorro;
	}
	public boolean getAhorroAutomatico()
	{
		return this.ahorroAutomatico;
	}
	
	public List<DispositivoInteligente> dispositivosInteligentes (){
		return dispositivosInteligentes;
	}
	public Punto getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Punto ubicacion) {
		this.ubicacion = ubicacion;
	}
	public List<DispositivoInteligente> getDispositivosInteligentes() {
		return dispositivosInteligentes;
	}
	public int getId() {
		return idCliente;
	}
	public void setId(int id) {
		this.idCliente = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilioServicio() {
		return domicilioServicio;
	}
	public void setDomicilioServicio(String domicilioServicio) {
		this.domicilioServicio = domicilioServicio;
	}
	public LocalDate getFechaDeAltaServicio() {
		return fechaDeAltaServicio;
	}
	public void setFechaDeAltaServicio(LocalDate fechaDeAltaServicio) {
		this.fechaDeAltaServicio = fechaDeAltaServicio;
	}
	public List<DispositivoEstandar> getDispositivosEstandar() {
		return dispositivosEstandar;
	}
	public void setDispositivosEstandar(List<DispositivoEstandar> dispositivosEstandar) {
		this.dispositivosEstandar = dispositivosEstandar;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setDispositivosInteligentes(List<DispositivoInteligente> dispositivosInteligentes) {
		this.dispositivosInteligentes = dispositivosInteligentes;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	
}