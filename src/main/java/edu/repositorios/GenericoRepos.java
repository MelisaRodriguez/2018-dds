package edu.repositorios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

public class GenericoRepos<T> {
	protected List<T> entidades = new ArrayList<>();

	public List<T> getEntidades() {
		return entidades;
	}
	
	public void agregar(T entidad)
	{
		entidades.add(entidad);
	}
	

}