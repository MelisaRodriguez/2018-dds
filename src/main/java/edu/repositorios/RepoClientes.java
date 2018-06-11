package edu.repositorios;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import edu.dominio.empresa.Deserializable;
import edu.dominio.fabricante.AccionesSegunFabricante;
import edu.dominio.usuario.Cliente;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;

	private RepoClientes() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		
		Type auxTipo = new TypeToken<ArrayList<Cliente>>() {
		}.getType();
		
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(EstadoDispositivo.class, new Deserializable<EstadoDispositivo>());
		builder.registerTypeAdapter(AccionesSegunFabricante.class, new Deserializable<AccionesSegunFabricante>());
		Gson gson = builder.create();
		entidades = gson.fromJson(new FileReader("Clientes.json"), auxTipo);
	}

	@Override
	public List<Cliente> getEntidades() {
		((List<Cliente>)entidades).forEach(x->x.recategorizar());
		return entidades;
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