package pe.com.capacitacion.dto;

import java.io.Serializable;
import java.util.List; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.capacitacion.bean.Empleado;

/**
 * @author cguerra.
 * @clase: DBResponseEmpMsg.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/ 
 //@Getter            //Autogenerar GETTERs. 
 //@Setter            //Autogenerar SETTERs. 
 @NoArgsConstructor   //Autogenerar CONTRUCTOR sin parametros. 
 @AllArgsConstructor  //Autogenerar CONTRUCTOR con parametros. 
 @Builder             //Autogenerar BUILDER. 
 @Data                //Autogenerar TOSTRING/GETTERs/SETTERs & otros.
 public class DBResponseEmpMsg implements Serializable{
	 
		private static final long serialVersionUID = 6098313551930099634L;
	   
		private String  codigoOUT;
		private String  mensajeOUT;
		private Integer idRegistroOUT;
		
	    //ADICIONALES:
	    private List<Empleado> listaEmpleados = null;
	    private Empleado       objEmpleado    = null;
 
 }

 