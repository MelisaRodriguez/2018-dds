package Group10.TP_Anual;
import java.time.LocalDate; 

public class Administrador {
	
		private String nombre;
		private String apellido;
		private String domicilio;
		private int nroIdentificacion;
		private Fecha fechaDeAlta;
		
		public Administrador(String nombre, String apellido, String domicilio, int nroIdentificacion, Fecha fechaDeAlta) 
		{
			this.nombre = nombre;
			this.apellido = apellido;
			this.domicilio = domicilio;
			this.nroIdentificacion = nroIdentificacion;
			this.fechaDeAlta = fechaDeAlta;
		}

    //  public int getMesesComoAdmin() { //<-- CORREGIR ESTE METODO
	//		return ((LocalDate.now().getYear() - fechaDeAlta.getYear()) * 12) + (LocalDate.now().getMonthValue() - fechaDeAlta.getMonthValue()); 
	//	};
}
