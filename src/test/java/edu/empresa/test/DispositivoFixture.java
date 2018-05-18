package edu.empresa.test;

import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

//import edu.empresa.DispositivoEstandar;
import edu.empresa.DispositivoInteligente;
import edu.fabricante.MensajeObjetos;
import edu.fabricante.Fabricante;
import edu.actuadoresSensores.*;

//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.List;
import java.util.function.Function;

public class DispositivoFixture {
	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoInteligente dispositivoInteligenteNoAcciona;
	protected Fabricante fabricante;
	protected MensajeObjetos formaDeEnvio;
	protected Regla reglaCumpleMedicion;
	protected Regla reglaNoCumpleMedicion;
	protected Sensor sensorCumpleMedicion ;
	protected Sensor sensorNoCumpleMedicion ;
	protected List<Sensor> sensoresCumpleMedicion;
	protected List<Sensor> sensoresNoCumpleMedicion;
	protected List<Actuador> actuadores;
	protected Actuador actuador;
	protected double valor;
	protected Function<Double, Boolean> condicionSensorCumpleMedicion;
	protected Function<Double, Boolean> condicionSensorNoCumpleMedicion;
	
	
	@Before
	public void fixture() {
		formaDeEnvio = new MensajeObjetos();
		fabricante = new Fabricante("GAMA", formaDeEnvio);
		dispositivoInteligente = new DispositivoInteligente("Heladera", 10, fabricante);
		valor = 10;

		actuador = new Actuador(dispositivoInteligente);
		actuadores = Arrays.asList(actuador);
		
		// creo la regla en donde todos los sensores cumplan la medicion
		condicionSensorCumpleMedicion = (Double valor) -> {return valor < 20;};
		sensorCumpleMedicion = new Sensor(condicionSensorCumpleMedicion);
		sensoresCumpleMedicion = Arrays.asList(sensorCumpleMedicion);
		
		reglaCumpleMedicion = new Regla(sensoresCumpleMedicion, actuadores);
		
		// creo la regla en donde haya un sensor de los sensores que NO cumpla la medicion
		condicionSensorNoCumpleMedicion = (Double valor) -> {return valor > 20;};
		sensorNoCumpleMedicion = new Sensor(condicionSensorNoCumpleMedicion);
		sensoresNoCumpleMedicion = Arrays.asList(sensorNoCumpleMedicion);
		
		reglaNoCumpleMedicion = new Regla(sensoresNoCumpleMedicion, actuadores);
		
	}
}
