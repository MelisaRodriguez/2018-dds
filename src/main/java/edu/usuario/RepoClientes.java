package edu.usuario;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class RepoClientes extends GenericoRepos<Cliente> {

	private static RepoClientes repo = null;

	private RepoClientes() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		
		Type auxTipo = new TypeToken<ArrayList<Cliente>>() {
		}.getType();

		info = new Gson().fromJson(new FileReader("Clientes.json"), auxTipo);
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
