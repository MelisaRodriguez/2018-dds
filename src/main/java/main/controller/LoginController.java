package main.controller;

import edu.dominio.usuario.Usuario;
import edu.repositorios.RepoClientes;
import edu.repositorios.RepoUsuarios;
import main.server.Cifrado;
import main.server.Server;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public final class LoginController {

	public static ModelAndView validarLogin(Request req, Response res) {
		if (req.session().attribute("username") == null) {
			res.redirect("login/Login-View.html");
			Spark.halt();
		}
		return null;
	}

	public static ModelAndView init(Request req, Response res) {
		res.redirect("login/Login-View.html");
		return null;
	}

	public static ModelAndView processLogin(Request req, Response res) {
		String username = req.queryParams("usuario");
		String password = req.queryParams("contraseña");
		password = Cifrado.Encrypt(req.queryParams("contraseña"));
		if (!RepoUsuarios.getInstanceOfSingleton().existeUsuario(username, password)) {
			res.status(400);
			res.redirect("login/Login-View.html");
		} else {
			Usuario usuario = RepoUsuarios.getInstanceOfSingleton().getUsuario(password, username);
			res.status(200);
			req.session().attribute("username", username);
			if (usuario.isAdmin()) {
				res.redirect("/admin");
				Server.escribirLog("./Logs.log", "LOGIN: Inició sesión el administrador " + usuario.getUsuario());
			} else {
				UserController.usuario = RepoClientes.getInstanceOfSingleton().getCliente(usuario.getId_user());
				res.redirect("/userPanel");
				Server.escribirLog("./Logs.log", "LOGIN: Inició sesión el cliente " + usuario.getUsuario());
			}
		}
		return null;
	}
}
