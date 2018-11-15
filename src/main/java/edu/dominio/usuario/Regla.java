package edu.dominio.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Regla {
	@Id
	@GeneratedValue
	private int idRegla;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Condicion> condiciones;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Actuador> actuadores;

	public Regla() {
	}

	public Regla(List<Condicion> condiciones, List<Actuador> actuadores) {
		this.condiciones = condiciones;
		this.actuadores = actuadores;
	}

	public boolean revisarCondiciones() {
		return condiciones.stream().allMatch(it -> it.medicionCumpleCondicion());
	}

	public void ejecutar() {

		if (this.revisarCondiciones()) {
			actuadores.stream().forEach(actuador -> actuador.enviarAccion());
		}
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public List<Actuador> getActuadores() {
		return actuadores;
	}

	public void setActuadores(List<Actuador> actuadores) {
		this.actuadores = actuadores;
	}

	public int getIdRegla() {
		return idRegla;
	}

	public void setIdRegla(int idRegla) {
		this.idRegla = idRegla;
	}

}
