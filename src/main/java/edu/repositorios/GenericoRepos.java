package edu.repositorios;

import java.util.ArrayList;
import java.util.List;

public class GenericoRepos<T> {
	protected List<T> entidades = new ArrayList<>();

	public List<T> getEntidades() {
		return entidades;
	}
}
