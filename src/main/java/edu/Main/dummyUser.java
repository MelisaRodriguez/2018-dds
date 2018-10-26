package edu.Main;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Usuario")
class dummyUser
{
	@Id
	int ID;
	String usuario;
	String contraseña;
	boolean admin;
	int id_user;
	
	public dummyUser() {}
	public dummyUser(int iD, String usuario, String contraseña, boolean admin, int id_user) {
		super();
		ID = iD;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.admin = admin;
		this.id_user = id_user;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
}