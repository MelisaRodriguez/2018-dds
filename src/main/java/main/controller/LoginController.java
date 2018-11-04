package main.controller;

import edu.dominio.usuario.Usuario;
import edu.repositorios.RepoClientes;
import edu.repositorios.RepoUsuarios;
import main.server.Cifrado;
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
		if (!RepoUsuarios.getInstanceOfSingleton().existeUsuario(username, password)) {
			res.status(400);
			res.redirect("login/Login-View.html");
		} else {
			Usuario usuario = RepoUsuarios.getInstanceOfSingleton().getUsuario(password, username);
			res.status(200);
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
