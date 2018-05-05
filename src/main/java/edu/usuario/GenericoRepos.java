package edu.usuario;

import java.util.ArrayList;
import java.util.List;

public class GenericoRepos<T> {
	protected List<T> info = new ArrayList<>();

	public List<T> getInfo() {
		return info;
	}
}
