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

<<<<<<< HEAD
    //  public int getMesesComoAdmin() { //<-- CORREGIR ESTE METODO
	//		return ((LocalDate.now().getYear() - fechaDeAlta.getYear()) * 12) + (LocalDate.now().getMonthValue() - fechaDeAlta.getMonthValue()); 
	//	};
=======
      public int getMesesComoAdmin() { 
			return  considerarAnio() +considerarMes() + considerarDia(); 
		};
		
	private int considerarAnio () {
		return ((LocalDate.now().getYear() - fechaDeAlta.getAnio()) * 12);
	};
	
	private int considerarMes() {
		return LocalDate.now().getMonthValue() - fechaDeAlta.getMes() ;
	};
	
	private int considerarDia () {
		if(LocalDate.now().getDayOfMonth() >= fechaDeAlta.getDia()) 
		{return 0;} else {return -1;}
	};
	
>>>>>>> 2ee481694f44a5c37d915a7aae91f871f6d6e748
}
