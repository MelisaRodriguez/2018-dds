package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import edu.dominio.fabricante.Fabricante;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DispositivoInteligente extends Dispositivo {
	
	private LocalDate fechaDeRegistro;
	@OneToMany
	//@JoinColum(idDispositivo) //??
	private List<RegistroMedicion> registrosConsumo; //Se asume ordenada por fecha
	
	public DispositivoInteligente(String nombre, LocalDate fechaDeRegistro, Fabricante fabricante, double restriccionMinima, double restriccionMaxima) {
		super(nombre, fabricante, restriccionMinima, restriccionMaxima);
		this.fechaDeRegistro = fechaDeRegistro;
		this.registrosConsumo = new ArrayList<RegistroMedicion>();
	}
	
	public DispositivoInteligente() {}
	
	@Override
	public double calcularConsumo() 
	{
		return this.fabricante.cuantoConsume(this);
	}

	// Este metodo se ejecutara automaticamente con un cron programado cuando se acabe la memoria del dispositivo.
	public void agregarNuevoRegistroDeConsumo()
	{
		registrosConsumo.add(new RegistroMedicion(LocalDate.now(),this.calcularConsumo(), this.getHorasEncendido() ) );
	}
	

	

	public double consumoTotalEnPeriodo (LocalDate inicio, LocalDate fin) { 
		// se asume, y se van a guardar de manera ordenada los registros
		return 	registrosConsumo.stream()
				.filter(registro -> registro.estaEntreFechas(inicio, fin))
				.mapToDouble(registro -> registro.kwConsumidos())
				.sum();
	}
	
	public double horasTotalesEnPeriodo (LocalDate inicio, LocalDate fin) { 
		// se asume, y se van a guardar de manera ordenada los registros
		System.out.println("REGISTROS = " + registrosConsumo.stream().filter(registro -> registro.estaEntreFechas(inicio, fin)).count());
		return 	registrosConsumo.stream()
				.filter(registro -> registro.estaEntreFechas(inicio, fin))
				.mapToDouble(registro -> registro.horasEncendido())
				.sum();
	}

	// estos metodos se los delega al fabricante que se comunica con el dispositivo fisico.
	
	public void apagarse() {
		fabricante.apagar(this);
	}
	
	public void encenderse() {
		fabricante.encender(this);
	}
	
	public void modoAhorroEnergia() {
		fabricante.activarAhorroDeEnergia(this);
	}
	
	public boolean estaEncendido() {
		return fabricante.estaEncendido(this);
	}
	
	public boolean estaApagado() {
		return fabricante.estaApagado(this);
	}
	
	public boolean estaModoAhorroEnergia() {
		return fabricante.estaModoAhorroEnergia(this);
	}
	
	public double getHorasEncendido()
	{
		return fabricante.getHorasEncendido(this);
	}
	
	public void accionar() {
		// se desconoce implementación, al menos hasta entrega 1.
	}

	public LocalDate getFechaDeRegistro() {
		return fechaDeRegistro;
	}

	public void setFechaDeRegistro(LocalDate fechaDeRegistro) {
		this.fechaDeRegistro = fechaDeRegistro;
	}

	public List<RegistroMedicion> getRegistrosConsumo() {
		return registrosConsumo;
	}

	public void setRegistrosConsumo(List<RegistroMedicion> registrosConsumo) {
		this.registrosConsumo = registrosConsumo;
	}

	@Override
	public double getPotencia() {
		return fabricante.getKW(this);
	}
	
	

}