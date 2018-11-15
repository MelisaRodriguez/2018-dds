package edu.usuario.test;

import static org.mockito.Mockito.mock;

import org.junit.Before;

import edu.dominio.usuario.Condicion;
import edu.dominio.usuario.Sensor;

public class CondicionFixture {

	protected Condicion condicion;
	protected Sensor sensor;

	@Before
	public void fixture() {
		sensor = mock(Sensor.class);
		condicion = new Condicion(sensor, (Double valor) -> {
			return valor > 20;
		});
	}
}
