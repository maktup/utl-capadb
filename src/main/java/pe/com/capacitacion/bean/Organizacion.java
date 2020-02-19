package pe.com.capacitacion.bean;

import java.io.Serializable; 
import javax.xml.bind.annotation.XmlRootElement; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Organizacion
 * @author cguerra
 **/
 //@Getter            //Autogenerar GETTERs. 
 //@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros.
 @XmlRootElement( name = "organizacion" ) 
 public class Organizacion implements Serializable{
 
	    private static final long serialVersionUID = 3652516949709446375L;
	
		private Long    id;
		private String  nombre;
		private String  direccion; 
  
 }
 
