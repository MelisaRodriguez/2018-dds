package edu.empresa.test;

import java.time.LocalDate;

import org.junit.Before;

import edu.dominio.empresa.Administrador;

public class AdministradorFixture {

	protected Administrador admin;

	@Before
	public void fixture() {
		admin = new Administrador("Gastón", "Prieto", "Mozart 1800 - CABA", 101, LocalDate.of(2017, 4, 28));
	}
}
