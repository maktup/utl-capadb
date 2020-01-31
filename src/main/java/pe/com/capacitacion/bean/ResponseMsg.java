package pe.com.capacitacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List; 
import com.fasterxml.jackson.annotation.JsonRootName; 

/**
 * ResponseMsg
 * @author cguerra
 **/  
 @JsonRootName( value = "ResponseMsg" ) 
 public class ResponseMsg implements Serializable{
 
        private static final long serialVersionUID = -7883259734965595249L;

        private Auditoria          auditoria;
		private List<Organizacion> listaOrganizaciones = new ArrayList<Organizacion>();
  
		public ResponseMsg(){
		}

		public ResponseMsg( Auditoria auditoria, List<Organizacion> listaOrganizacion ){
			   super();
			   this.auditoria = auditoria;
			   this.listaOrganizaciones  = listaOrganizacion;
		}
 
		public List<Organizacion> getListaOrganizaciones() {
			   return listaOrganizaciones;
		}
 
		public void setListaOrganizaciones(List<Organizacion> listaOrganizaciones ) {
			   this.listaOrganizaciones = listaOrganizaciones;
		}

		public static long getSerialversionuid() {
			   return serialVersionUID;
		}

		public Auditoria getAuditoria() {
			   return auditoria;
		}

		public void setAuditoria(Auditoria auditoria) {
			   this.auditoria = auditoria;
		}

		@Override
		public String toString() {
			   return "ResponseMsg [auditoria=" + this.auditoria + ", listaOrganizacion=" + this.listaOrganizaciones + "]";
		}
 
 }

 