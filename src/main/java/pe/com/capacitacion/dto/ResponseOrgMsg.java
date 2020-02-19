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
import pe.com.capacitacion.bean.Organizacion; 

/**
 * ResponseOrgMsg
 * @author cguerra
 **/
 //@Getter            //Autogenerar GETTERs. 
 //@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros.
 @JsonRootName( value = "ResponseOrgMsg" ) 
 public class ResponseOrgMsg implements Serializable{
 
        private static final long serialVersionUID = -7883259734965595666L;

        private Auditoria          auditoria;
		private List<Organizacion> listaOrganizaciones = new ArrayList<Organizacion>();
 
 }

 