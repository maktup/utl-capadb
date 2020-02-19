package pe.com.capacitacion.bean;

import java.io.Serializable; 
import javax.xml.bind.annotation.XmlRootElement; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Departamento
 * @author cguerra
 **/
 //@Getter            //Autogenerar GETTERs. 
 //@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros.
 @XmlRootElement( name = "departamento" ) 
 public class Departamento implements Serializable{
 
	    private static final long serialVersionUID = -7037029771976335492L;
	
		private Long   id;
		private String nombre; 
		private Long   idOrg;   //REFERENCIA
 
 
  }

 