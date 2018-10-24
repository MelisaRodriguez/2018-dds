package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import edu.dominio.posicion.Punto;
import edu.dominio.usuario.Cliente;
import edu.repositorios.RepoZonaGeografica;


@Entity
@Table(name="Transformador")
public class Transformador {
	
	@Id
	@GeneratedValue
	public int idTransformador;
	

	@Embedded
	private Punto lugar;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "idTransformador")
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public Transformador() {}
	
	public Transformador(Punto lugar)
	{
		this.lugar = lugar;
		//RepoZonaGeografica.getSingletonInstance().agregarTransformador(this, lugar);
	}
	
	public void agregarCliente(Cliente unCliente)
	{
		this.clientes.add(unCliente);
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public double calcularConsumo()
	{
		return this.clientes.stream().mapToDouble(c->c.consumoTotal()).sum();
	}
	
	public double consumoTotalEnPeriodo(LocalDate inicio, LocalDate fin) {
		return this.clientes.stream().mapToDouble(c->c.consumoTotalEnPeriodo(inicio, fin)).sum();
	}
	
	public Punto getLugar()
	{
		return this.lugar;
	}
	public int getIdTransformador() {
		return idTransformador;
	}
	
	public void setIdTransformador(int idTransformador) {
		this.idTransformador = idTransformador;
	}
	
	public void setLugar(Punto lugar) {
		this.lugar = lugar;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}