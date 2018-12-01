package main.controller;

import java.util.HashMap;

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
		if (!RepoUsuarios.getInstanceOfSingleton().existeUsuario(username, password)) {
			res.status(400);
			res.redirect("/");
		} else {
			Usuario usuario = RepoUsuarios.getInstanceOfSingleton().getUsuario(password, username);
			res.status(200);
			req.session().attribute("username", username);
			if (usuario.isAdmin()) {
				res.redirect("/admin");
			} else {
				UserController.usuario = RepoClientes.getInstanceOfSingleton().getCliente(usuario.getId_user());
				res.redirect("/userPanel");
			}
		}
		return null;
	}
}
