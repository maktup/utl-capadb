package pe.com.capacitacion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonRootName; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.Departamento; 

/**
 * ResponseDepMsgDep
 * @author cguerra
 **/  
 //@Getter            //Autogenerar GETTERs. 
 //@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros.
 @JsonRootName( value = "ResponseDepMsg" ) 
 public class ResponseDepMsg implements Serializable{
	
	    private static final long serialVersionUID = -7883259734944595123L;
	
	    private Auditoria          auditoria;
		private List<Departamento> listaDepartamentos = new ArrayList<Departamento>();
 
	}
	
