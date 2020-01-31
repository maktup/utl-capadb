package pe.com.capacitacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Organizacion
 * @author cguerra
 **/
 public class Organizacion implements Serializable{
 
	    private static final long serialVersionUID = 3652516949709446375L;
	
		private Long    id;
		private String  nombre;
		private String  direccion; 
		private List<Departamento> listaDepartamentos = new ArrayList<>();
 	
		public Organizacion(){	
		}
		
		public Organizacion( String nombre, String direccion ){
			   super();
			   this.nombre    = nombre; 
			   this.direccion = direccion; 
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

		public String getDireccion() {
			return direccion;
		}

		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}

		public List<Departamento> getListaDepartamentos() {
			return listaDepartamentos;
		}

		public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
			this.listaDepartamentos = listaDepartamentos;
		}
 
		@Override
		public String toString() {
			return "Organizacion [id=" + this.id + ", nombre=" + nombre + ", direccion=" + this.direccion + "]";
		}

 }
 
