package Controller;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public final class LoginController {

	public static ModelAndView init(Request req, Response res) {
		res.redirect("Login-View.html");
		return null;
	}

	public static ModelAndView processLogin(Request req, Response res) {
		String username = req.queryParams("usuario");
		String password = Cifrado.Encrypt(req.queryParams("contraseña"));
		EntityManager n = PerThreadEntityManagers.getEntityManager();
		n.getTransaction().begin();
		List<dummyUser> lista = n.createQuery("select * from Usuario where usuario = :u and contraseña = :c")
				.setParameter("u", username).setParameter("c", password).getResultList();
		n.getTransaction().commit();
		n.close();
		if (lista.isEmpty() || lista == null) {
			res.status(400);
			res.redirect("/");
		} else {
			dummyUser u = lista.get(0);
			res.status(200);
			if (u.isAdmin()) {
				res.redirect("/admin");
			} else {
				// redireccionar cliente
			}
		}
		return null;
	}
}
