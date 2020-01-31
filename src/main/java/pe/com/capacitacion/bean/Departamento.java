package pe.com.capacitacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Departamento
 * @author cguerra
 **/
 public class Departamento implements Serializable{
 
	    private static final long serialVersionUID = -7037029771976335492L;
	
		private Long           id;
		private String         nombre;
		private List<Empleado> listaEmpleados = new ArrayList<Empleado>();  //AGREGACION
		private Long           organizationId;                              //REFRENCIA
	
		public Departamento(){			
		}
 
		public Long getId() {
			return id;
		}

		public Long getOrganizationId() {
			return organizationId;
		}

		public void setOrganizationId(Long organizationId) {
			this.organizationId = organizationId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
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

		public List<Empleado> getListaEmpleados() {
			return listaEmpleados;
		}

		public void setListaEmpleados(List<Empleado> listaEmpleados) {
			this.listaEmpleados = listaEmpleados;
		}

		@Override
		public String toString(){
			   return "Departamento [id=" + this.id + ", name=" + this.nombre + "]"; 
		}

 }
