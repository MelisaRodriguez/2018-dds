package edu.empresa.test;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import edu.dominio.empresa.DispositivoEstandar;
import edu.dominio.empresa.DispositivoInteligente;
import edu.dominio.empresa.Dispositivo;
import edu.dominio.empresa.LlamarSimplex;
import edu.dominio.usuario.Cliente;
import edu.dominio.usuario.RestriccionConsumo;

public class LlamarSimplexFixture {
	protected DispositivoInteligente aireAcondicionado;
	protected DispositivoInteligente televisor;
	protected DispositivoEstandar lavarropas;
	protected DispositivoInteligente ventilador;
	protected DispositivoInteligente lampara;
	protected DispositivoInteligente pc;
	protected DispositivoEstandar microondas;
	protected DispositivoEstandar plancha;
	
	protected Cliente cliente;
	protected LlamarSimplex llamarSimplex;
	
	protected ArrayList<Dispositivo> dispositivos;

	
	@Before
	public void fixture() {
		aireAcondicionado = mock(DispositivoInteligente.class);
		lampara = mock(DispositivoInteligente.class);
		televisor = mock(DispositivoInteligente.class);
		pc = mock(DispositivoInteligente.class);
		lavarropas = mock(DispositivoEstandar.class);
		microondas = mock(DispositivoEstandar.class);
		plancha = mock(DispositivoEstandar.class);
		ventilador = mock(DispositivoInteligente.class);
		
		cliente = mock(Cliente.class);
		
		dispositivos = new ArrayList<Dispositivo>();
		dispositivos.add(aireAcondicionado);
		dispositivos.add(lampara);
		dispositivos.add(televisor);
		dispositivos.add(pc);
		dispositivos.add(lavarropas);
		dispositivos.add(microondas);
		dispositivos.add(plancha);
		dispositivos.add(ventilador);
	}
}