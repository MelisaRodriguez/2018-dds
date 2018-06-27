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
	protected RestriccionConsumo llamarSimplexMaxima;
	
	protected ArrayList<Dispositivo> dispositivos;
	
	protected RestriccionConsumo aireAcondicionadoMinima;
	protected RestriccionConsumo aireAcondicionadoMaxima;
	protected RestriccionConsumo lamparaMinima;
	protected RestriccionConsumo lamparaMaxima;
	protected RestriccionConsumo televisorMinima;
	protected RestriccionConsumo televisorMaxima;
	protected RestriccionConsumo pcMinima;
	protected RestriccionConsumo pcMaxima;
	protected RestriccionConsumo lavarropasMinima;
	protected RestriccionConsumo lavarropasMaxima;
	protected RestriccionConsumo microondasMinima;
	protected RestriccionConsumo microondasMaxima;
	protected RestriccionConsumo planchaMinima;
	protected RestriccionConsumo planchaMaxima;
	protected RestriccionConsumo ventiladorMinima;
	protected RestriccionConsumo ventiladorMaxima;
	
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
		llamarSimplexMaxima = mock(RestriccionConsumo.class);
		llamarSimplex = new LlamarSimplex(llamarSimplexMaxima);
		
		dispositivos = new ArrayList<Dispositivo>();
		dispositivos.add(aireAcondicionado);
		dispositivos.add(lampara);
		dispositivos.add(televisor);
		dispositivos.add(pc);
		dispositivos.add(lavarropas);
		dispositivos.add(microondas);
		dispositivos.add(plancha);
		dispositivos.add(ventilador);
		
		aireAcondicionadoMinima = mock(RestriccionConsumo.class);
		aireAcondicionadoMaxima = mock(RestriccionConsumo.class);
		lamparaMinima = mock(RestriccionConsumo.class);
		lamparaMaxima = mock(RestriccionConsumo.class);
		televisorMinima = mock(RestriccionConsumo.class);
		televisorMaxima = mock(RestriccionConsumo.class);
		pcMinima = mock(RestriccionConsumo.class);
		pcMaxima = mock(RestriccionConsumo.class);
		lavarropasMinima = mock(RestriccionConsumo.class);
		lavarropasMaxima = mock(RestriccionConsumo.class);
		microondasMinima = mock(RestriccionConsumo.class);
		microondasMaxima = mock(RestriccionConsumo.class);
		planchaMinima = mock(RestriccionConsumo.class);
		planchaMaxima = mock(RestriccionConsumo.class);
		ventiladorMinima = mock(RestriccionConsumo.class);
		ventiladorMaxima = mock(RestriccionConsumo.class);
	}
}

/*
 * public class DispositivoFixture {
	protected DispositivoInteligente dispositivoInteligente;
	protected DispositivoEstandar dispositivoEstandar;
	protected SanyoTelevisor televisor;
	
	@Before
	public void fixture() {
		televisor = mock(SanyoTelevisor.class);
		dispositivoInteligente = new DispositivoInteligente("Televisor", LocalDate.of(2017, 3, 28), televisor);
		dispositivoEstandar = new DispositivoEstandar("Televisor", 10, 5, televisor);
	}
}*/
 */