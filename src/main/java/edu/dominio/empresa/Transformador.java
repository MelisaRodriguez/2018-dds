package edu.dominio.empresa;

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
	public Long idTransformador;
	

	@Embedded
	private Punto lugar;
	
	
	@OneToMany(cascade=CascadeType.PERSIST)
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
	
	public Punto getLugar()
	{
		return this.lugar;
	}
	public Long getIdTransformador() {
		return idTransformador;
	}
	
	public void setIdTransformador(Long idTransformador) {
		this.idTransformador = idTransformador;
	}
	
	public void setLugar(Punto lugar) {
		this.lugar = lugar;
	}
	
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}