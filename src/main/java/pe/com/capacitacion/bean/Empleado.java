package pe.com.capacitacion.bean;

import java.io.Serializable;

/**
 * Empleado
 * @author cguerra
 **/
 public class Empleado implements Serializable{
 
	    private static final long serialVersionUID = -2569051539525119808L;
	
		private Long   id;
		private String nombre; 
		private int    edad; 
		private String rol; 
	
		public Empleado(){	
		}
		
		public Empleado( String nombre, int edad, String rol ){ 
			   this.nombre = nombre;
			   this.edad   = edad;
			   this.rol    = rol;
		}
	     
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public int getEdad() {
			return edad;
		}

		public void setEdad(int edad) {
			this.edad = edad;
		}

		public String getRol() {
			return rol;
		}

		public void setRol(String rol) {
			this.rol = rol;
		}

		@Override
		public String toString(){
			   return "Empleado [id=" + this.id + ", nombre=" + this.nombre + ", edad=" + this.edad + ", rol=" + this.rol + "]";
		}

 }
 
