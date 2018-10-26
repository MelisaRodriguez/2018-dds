package Controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import Server.Cifrado;
import Server.dummyUser;
import edu.dominio.fabricante.Fabricante;
import edu.dominio.fabricante.Sony;
import edu.dominio.usuario.Cliente;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public final class LoginController {

	public static ModelAndView init(Request req, Response res) {
		res.redirect("login/Login-View.html");
		return null;
	}

	public static ModelAndView processLogin(Request req, Response res) {
		String username = req.queryParams("usuario");
		String password = Cifrado.Encrypt(req.queryParams("contraseña"));
		EntityManager n = PerThreadEntityManagers.getEntityManager();
		n.getTransaction().begin();
		List<dummyUser> lista = n
				.createQuery("from dummyUser c where c.usuario = :u and c.contraseña = :p", dummyUser.class)
				.setParameter("u", username).setParameter("p", password).getResultList();
		n.getTransaction().commit();
		if (lista.isEmpty() || lista == null) {
			res.status(400);
			res.redirect("login/Login-View.html");
		} else {
			dummyUser u = lista.get(0);
			res.status(200);
			if (u.isAdmin()) {
				res.redirect("/admin");
			} else {
				UserController.usuario = n.find(Cliente.class, u.getId_user());
				UserController.usuario.getDispositivosInteligentes().stream()
						.forEach(d -> d.setFabricante(new Fabricante("Sony", new Sony())));
				res.redirect("/userPanel");
			}
		}
		n.close();
		return null;
	}
}
