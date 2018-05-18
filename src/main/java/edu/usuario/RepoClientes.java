package edu.usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import edu.empresa.EstadoDispositivo;
import edu.empresa.estadoAhorroEnergia;
import edu.empresa.estadoApagado;
import edu.empresa.estadoEncendido;
import edu.fabricante.AccionesSegunFabricante;
import edu.empresa.Deserializable;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;

	private RepoClientes() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		
		Type auxTipo = new TypeToken<ArrayList<Cliente>>() {
		}.getType();
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EstadoDispositivo.class, new Deserializable<EstadoDispositivo>());
		builder.registerTypeAdapter(AccionesSegunFabricante.class, new Deserializable<AccionesSegunFabricante>());
		Gson gson = builder.create();
		info = gson.fromJson(new FileReader("Clientes.json"), auxTipo);
	}

	public static RepoClientes getInstanceOfSingleton()
	{
		if (repo == null)
			try {
				repo = new RepoClientes();
			} catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return repo;
	}
}
