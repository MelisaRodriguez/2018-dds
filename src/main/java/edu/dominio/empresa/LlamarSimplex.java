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
		// PRINTS <-- Okey... :( se encarga Gonzalo :/
		
		if (cliente.getAhorroAutomatico()) {
			this.mejorarEficienciaHogar(cliente,resultado);
		}
		
	}

	public void mejorarEficienciaHogar(Cliente cliente, PointValuePair resultado) {
		
		double [] arrayResultados = resultado.getPoint(); 
		
		// cliente.todosSusDispositivos me da una lista con dispositivos inteligentes y estandares, los entandares nos dan problemas
		int posicion = 0;
		for (Dispositivo dispositivo : cliente.todosSusDispositivos())
		{
			this.revisarEficienciaDispositivo(dispositivo,arrayResultados[posicion]);
			posicion++;
		}
		
		
	}
	
	private LocalDate primeriDiaDelMes(LocalDate fechaActual) {
		return LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), 1) ;
		// es por que la consigna me pide que revise las horas de uso del dispositivo desde principio de mes hasta la fecha actual
	}
	
	private void revisarEficienciaDispositivo(DispositivoInteligente dispositivo, double horasConsumoOptimas) {
		//no tengo forma de saber en un dispositivo estandar cuanto consumo lleva en lo que va del mes
		//no tengo forma de saber las horas de uso que lleva un dispositivo en un periodo de tiempo
		//solo a los dispositivos inteligentes se les puede decir que se apaguen
		
		//en el if debo comparar las horas de uso en lo que va del mes del sipositivo contra las horas de consumo optimas
		if() {
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
		double [] lista = {};
		for(int i = 0; i < cantDispositivos; i++) {
			lista[i] = (1.0);
		}
		return lista;
	}
	
	private double[] listaRestriccion(int dispositivoActual, int cantDispositivos){
		double [] lista = {};
		for(int i = 0; i < cantDispositivos; i++) {
			if(dispositivoActual == i)
				lista[i] = (1.0);
			else
				lista[i] = (0.0);
		}
		return lista;
	}

}
