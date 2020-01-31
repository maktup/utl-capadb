package pe.com.capacitacion.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * ResponseMsg
 * @author cguerra
 **/  
 @JsonRootName( value = "ResponseMsgDep" ) 
 public class ResponseMsgDep implements Serializable{
	
	    private static final long serialVersionUID = -7883259734944595249L;
	
	    private Auditoria          auditoria;
		private List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
	
		public ResponseMsgDep(){
		}
	
		public ResponseMsgDep( Auditoria auditoria, List<Departamento> listaDepartamento ){
			   super();
			   this.auditoria = auditoria;
			   this.listaDepartamentos  = listaDepartamento;
		}
	
		public List<Departamento> getListaDepartamentos() {
			   return listaDepartamentos;
		}
	
		public void setListaDepartamentos(List<Departamento> listaDepartamento ) {
			   this.listaDepartamentos = listaDepartamento;
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
			   return "ResponseMsg [auditoria=" + this.auditoria + ", listaDepartamento=" + this.listaDepartamentos + "]";
		}
	
	}
	
