package main.controller;

import java.util.HashMap;

import javax.persistence.NoResultException;

import edu.dominio.usuario.Usuario;
import edu.repositorios.RepoClientes;
import edu.repositorios.RepoUsuarios;
import main.server.Cifrado;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public final class LoginController {

	public static ModelAndView validarLogin(Request req, Response res) {
		if (req.session().attribute("username") == null) {
			res.redirect("/");
			Spark.halt();
		}
		return null;
	}

	public static ModelAndView init(Request req, Response res) {
		return new ModelAndView(new HashMap<String, Object>(), "login/Login-View.hbs");
	}

	public static ModelAndView processLogin(Request req, Response res) {
		String username = req.queryParams("usuario");
		String password = req.queryParams("contraseña");
		password = Cifrado.Encrypt(req.queryParams("contraseña"));

		try {
			Usuario usuario = RepoUsuarios.getInstanceOfSingleton().getUsuario(username, password);
			res.status(200);
			req.session().attribute("username", username);
			if (usuario.isAdmin()) {
				res.redirect("/admin");
			} else {
				req.session().attribute("Cliente",
						RepoClientes.getInstanceOfSingleton().findByID(usuario.getId_user()));
				res.redirect("/userPanel");
			}
		} catch (NoResultException e) {
			res.status(400);
			res.redirect("/");
		}
		return null;
	}
}
