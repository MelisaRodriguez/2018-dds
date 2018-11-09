package Server;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class dummyUser
{
	@Id
	@GeneratedValue
	int ID;
	String usuario;
	String contrasena;
	boolean admin;
	int id_user;
	
	public dummyUser() {}
	public dummyUser(String usuario, String contrasena, boolean admin, int id_user) {
		this.usuario = usuario;
		this.contrasena = contrasena;
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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