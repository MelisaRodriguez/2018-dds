package edu.dominio.empresa;

import edu.dominio.usuario.Cliente;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.optim.MaxIter;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;


public class LlamarSimplex {
	private double restriccionMaxima;
	
	public LlamarSimplex(double restriccionMaxima) {
		this.restriccionMaxima = restriccionMaxima;
	}
	
	public void generarRecomendacion(Cliente cliente) {
	
		PointValuePair resultado = ejecutarSimplex(cliente);
		
		double [] arrayResultados = resultado.getPoint();
		double z = resultado.getValue(); 
		List<Dispositivo> dispositivos = cliente.todosSusDispositivos();
		int posicion = 0;
		for (Dispositivo d : dispositivos)
		{
			System.out.println("Horas máximas posibles de uso para " + d.getNombre() + " son " + arrayResultados[posicion]);
			posicion++;
		}
		System.out.println("Sumatoria de horas máximas posibles de consumo de todos los dispositivos: " + z);
		
		if (cliente.getAhorroAutomatico()) {
			this.mejorarEficienciaHogar(cliente,resultado);
		}
		
	}

	public void mejorarEficienciaHogar(Cliente cliente, PointValuePair resultado) {
		
		double [] arrayResultados = resultado.getPoint(); 
		
		int posicion = 0;
		for (DispositivoInteligente dispositivo : cliente.dispositivosInteligentes() )
		{
			this.revisarEficienciaDispositivo(dispositivo,arrayResultados[posicion]);
			posicion++;
		}
		
	}
	
	private LocalDate primerDiaDelMes(LocalDate fechaActual) {
		return LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), 1) ;
	}
	
	private void revisarEficienciaDispositivo(DispositivoInteligente dispositivo, double horasConsumoOptimas) {
		
		if(horasConsumoOptimas <= dispositivo.horasTotalesEnPeriodo( this.primerDiaDelMes(LocalDate.now() ), LocalDate.now() ) ) {
			dispositivo.apagarse();
		}
		
	}

	private PointValuePair ejecutarSimplex(Cliente cliente) {
		
		SimplexSolver simplex = new SimplexSolver();
		
		LinearObjectiveFunction fx = new LinearObjectiveFunction(this.coeficientesUno(cliente.cantDispositivosEnTotal()), 0.0);
		
		List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>(); 
		
		restricciones.add(new LinearConstraint(cliente.todosSusDispositivos().stream().mapToDouble(dispositivo -> dispositivo.getPotencia()).toArray(), Relationship.LEQ, this.restriccionMaxima));
	
		int cant = 0;
		for (Dispositivo dispositivo : cliente.todosSusDispositivos())
		{
			double [] coeficientes = this.listaRestriccion(cant, cliente.cantDispositivosEnTotal());
			restricciones.add(new LinearConstraint(coeficientes, Relationship.GEQ, dispositivo.getRestriccionMinima()));
			restricciones.add(new LinearConstraint(coeficientes, Relationship.LEQ, dispositivo.getRestriccionMaxima()));
			cant++;
		}
		return simplex.optimize(new MaxIter(50),fx, new LinearConstraintSet(restricciones),GoalType.MAXIMIZE, new NonNegativeConstraint(true));
	}
	
	private double[] coeficientesUno(int cantDispositivos){
		double [] lista = new double [cantDispositivos];
		for(int i = 0; i < cantDispositivos; i++) {
			lista[i] = (1.0);
		}
		return lista;
	}
	
	private double[] listaRestriccion(int dispositivoActual, int cantDispositivos){
		double [] lista = new double [cantDispositivos];
		for(int i = 0; i < cantDispositivos; i++) {
			if(dispositivoActual == i)
				lista[i] = (1.0);
			else
				lista[i] = (0.0);
		}
		return lista;
	}

}
