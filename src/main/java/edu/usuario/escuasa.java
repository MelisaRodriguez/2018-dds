package edu.usuario;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import edu.empresa.DispositivoEstandar;
import edu.empresa.DispositivoInteligente;
import edu.fabricante.Fabricante;
import edu.fabricante.MensajeObjetos;
import edu.usuario.test.ClienteFixture;

public class escuasa {

	public static void main(String[] args) {
		MensajeObjetos x=new MensajeObjetos(); 
		Fabricante samsung=new Fabricante("Sammsung",x);
		Cliente cli = new Cliente("Brashan","Ferrari",TipoDocumento.DNI,"36892014","42172304","Av EverViva 254",null,
				Arrays.asList(new DispositivoInteligente("plancha", 22, samsung)),
				Arrays.asList(new DispositivoEstandar("celular", 45, samsung)));
		
		//System.out.println(json);
		List<Cliente> clientes = RepoClientes.getInstanceOfSingleton().getInfo();
		
		clientes.forEach(c -> System.out.println(c.infoCliente()));
	}

}
