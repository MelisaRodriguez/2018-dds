package edu.empresa.test;

import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.fabricante.SanyoTelevisor;
import edu.dominio.usuario.Actuador;
import edu.dominio.usuario.Sensor;
import edu.dominio.usuario.Regla;
import edu.dominio.usuario.Condicion;

import java.time.LocalDate;
import java.util.Arrays;

public class DispositivoFixture {
	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoInteligente dispositivoInteligenteNoAcciona;
	protected SanyoTelevisor televisor;
	protected Regla reglaCumpleMedicion;
	protected Regla reglaNoCumpleMedicion;
	protected Sensor sensorCumpleMedicion ;
	protected Sensor sensorNoCumpleMedicion ;
	protected List<Sensor> sensoresCumpleMedicion;
	protected List<Sensor> sensoresNoCumpleMedicion;
	protected List<Actuador> actuadores;
	protected List<Condicion> condicionesCumpleMedicion;
	protected List<Condicion> condicionesNoCumpleMedicion;
	protected Actuador actuador;
	protected double medicion;
	//protected Function<Double, Boolean> condicionSensorCumpleMedicion;
	protected Condicion condicionSensorCumpleMedicion;
	//protected Function<Double, Boolean> condicionSensorNoCumpleMedicion;
	protected Condicion condicionSensorNoCumpleMedicion;
	
	
	@Before
	public void fixture() {
		televisor = new SanyoTelevisor();
		dispositivoInteligente = new DispositivoInteligente("Heladera", LocalDate.of(2017, 3, 28), televisor);
		medicion = 10;

		actuador = new Actuador(dispositivoInteligente);
		actuadores = Arrays.asList(actuador);
		
		// creo la regla en donde todos los sensores cumplan la medicion
		//condicionSensorCumpleMedicion = (Double valor) -> {return valor < 20;};
		//sensorCumpleMedicion = new Sensor(condicionSensorCumpleMedicion);
		sensorCumpleMedicion = new Sensor();
		condicionSensorCumpleMedicion = new Condicion(sensorCumpleMedicion, (Double valor) -> {return valor < 20;});
		condicionesCumpleMedicion = Arrays.asList(condicionSensorCumpleMedicion);
		reglaCumpleMedicion = new Regla(condicionesCumpleMedicion, actuadores);
		
		// creo la regla en donde haya un sensor de los sensores que NO cumpla la medicion
		//condicionSensorNoCumpleMedicion = (Double valor) -> {return valor > 20;};
		condicionSensorNoCumpleMedicion = new Condicion(sensorCumpleMedicion, (Double valor) -> {return valor < 20;});
		sensorNoCumpleMedicion = new Sensor();
		sensoresNoCumpleMedicion = Arrays.asList(sensorNoCumpleMedicion);
		condicionesNoCumpleMedicion = Arrays.asList(condicionSensorNoCumpleMedicion);
		reglaNoCumpleMedicion = new Regla(condicionesNoCumpleMedicion, actuadores);
		
	}
}
