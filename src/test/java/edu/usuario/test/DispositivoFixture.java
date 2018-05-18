package edu.usuario.test;

import org.junit.Before;

import edu.empresa.DispositivoEstandar;
import edu.empresa.DispositivoInteligente;
import edu.fabricante.MensajeObjetos;
import edu.fabricante.Fabricante;

public class DispositivoFixture {
	protected DispositivoEstandar dispositivoEstandar;
	protected DispositivoInteligente dispositivoInteligente;
	protected Fabricante fabricante;
	protected MensajeObjetos formaDeEnvio;
	@Before
	public void fixture() {
		formaDeEnvio = new MensajeObjetos();
		fabricante = new Fabricante("GAMA", formaDeEnvio);
		dispositivoEstandar = new DispositivoEstandar("Secadora", 10, fabricante);
		dispositivoInteligente = new DispositivoInteligente("Heladera", 10, fabricante);
	}
}
