package edu.dominio.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Operador;
import edu.dominio.usuario.Regla;
import edu.dominio.usuario.Sensor;

public class Simplex {
	private double restriccionMaxima;

	public Simplex(double restriccionMaxima) {
		this.restriccionMaxima = restriccionMaxima;
	}

	// recomendacion que pide el cliente - MANUAL
	public List<Double> generarRecomendacion(Cliente cliente) {

		PointValuePair resultado = this.ejecutarSimplex(cliente);

		List<Double> resultados = new ArrayList<>();
		for (double d : resultado.getPoint())
			resultados.add(d); // necesitamos el array
		resultados.add(resultado.getValue());
		return resultados;

		// le doy al cliente las horas de cada dispositivo, y el valor máximo Z.
	}

	public void optimizacionAutomatica(Cliente cliente) {
		PointValuePair resultado = this.ejecutarSimplex(cliente);
		this.mejorarEficienciaHogar(cliente, resultado);
	}

	public void mejorarEficienciaHogar(Cliente cliente, PointValuePair resultado) {

		double[] arrayResultados = resultado.getPoint();

		int posicion = 0;
		for (DispositivoInteligente dispositivo : cliente.dispositivosInteligentes()) {
			this.revisarEficienciaDispositivo(dispositivo, arrayResultados[posicion]);
			posicion++;
		}
	}

	private void revisarEficienciaDispositivo(DispositivoInteligente dispositivo, double horasConsumoOptimas) {

		Actuador actuador = new Actuador(dispositivo);
		Sensor sensor = new Sensor();
		sensor.tomarMedicion(dispositivo.horasTotalesEnPeriodo(this.primerDiaDelMes(LocalDate.now()), LocalDate.now()));
		Condicion condicion = new Condicion(sensor, Operador.MAYOR_IGUAL);
		Regla regla = new Regla(Arrays.asList(condicion), Arrays.asList(actuador));
		regla.ejecutar();
	}

	public LocalDate primerDiaDelMes(LocalDate fechaActual) {
		return LocalDate.of(fechaActual.getYear(), fechaActual.getMonth(), 1);
	}

	private PointValuePair ejecutarSimplex(Cliente cliente) {

		SimplexSolver simplex = new SimplexSolver();

		LinearObjectiveFunction fx = new LinearObjectiveFunction(
				this.coeficientesUno(cliente.cantDispositivosEnTotal()), 0.0);

		List<LinearConstraint> restricciones = new ArrayList<LinearConstraint>();

		restricciones.add(new LinearConstraint(
				cliente.todosSusDispositivos().stream().mapToDouble(dispositivo -> dispositivo.getPotencia()).toArray(),
				Relationship.LEQ, this.restriccionMaxima));

		int cant = 0;
		for (Dispositivo dispositivo : cliente.todosSusDispositivos()) {
			double[] coeficientes = this.listaRestriccion(cant, cliente.cantDispositivosEnTotal());
			restricciones.add(new LinearConstraint(coeficientes, Relationship.GEQ, dispositivo.getRestriccionMinima()));
			restricciones.add(new LinearConstraint(coeficientes, Relationship.LEQ, dispositivo.getRestriccionMaxima()));
			cant++;
		}
		return simplex.optimize(new MaxIter(50), fx, new LinearConstraintSet(restricciones), GoalType.MAXIMIZE,
				new NonNegativeConstraint(true));
	}

	private double[] coeficientesUno(int cantDispositivos) {
		double[] lista = new double[cantDispositivos];
		for (int i = 0; i < cantDispositivos; i++) {
			lista[i] = (1.0);
		}
		return lista;
	}

	private double[] listaRestriccion(int dispositivoActual, int cantDispositivos) {
		double[] lista = new double[cantDispositivos];
		for (int i = 0; i < cantDispositivos; i++) {
			if (dispositivoActual == i)
				lista[i] = (1.0);
			else
				lista[i] = (0.0);
		}
		return lista;
	}

}
