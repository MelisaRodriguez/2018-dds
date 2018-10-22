package Server;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import edu.dominio.empresa.Administrador;
import edu.repositorios.RepoAdministrador;

public class Bootstrap {
	
	
	public static void init() {
		
		
		Administrador admin = new Administrador("Gaston", "Prieto", "Mozart 1800 - CABA", 101, LocalDate.of(2017, 4, 28));

		RepoAdministrador repo=new RepoAdministrador();
		
		repo.agregar(admin);
		
	}
	
	
}
