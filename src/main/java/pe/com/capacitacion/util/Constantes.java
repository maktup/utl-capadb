package pe.com.capacitacion.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Constantes
 * @author cguerra
 **/
 @Component
 public class Constantes{
	    
	    @Value( "${ws.nombre.servicio}" )             //ACCESO: al valor REMOTO [ws.nombre.servicio]
	    public String nombreServicio;
	 
		@Value( "${propiedades.config.valor_01}" )    //ACCESO: [propiedades.config.valor_01:]
		public String valor01; 
 
	    @Value( "${spring.application.name}" )        //ACCESO: al valor REMOTO [spring.application.name]
	    public String nombreMicroServicio;
	    

	    //[GENERICOS]
		@Value( "${propiedades.oracle.owner}" )       //ACCESO: [propiedades.oracle.owner]
		public String oracle_owner; 
	    
		@Value( "${propiedades.oracle.timeout}" )     //ACCESO: [propiedades.oracle.timeout]
		public Integer oracle_timeout; 
		
		
		//----------------------------------------------------------------------------------------------------------------------------//
		//[EMPLEADOS]: 
		@Value( "${propiedades.oracle.empleados.package}" )           //ACCESO: [propiedades.oracle.empleados.package]
		public String oracle_empleados_package;  
	    
		@Value( "${propiedades.oracle.empleados.procedure01}" )       //ACCESO: [propiedades.oracle.empleados.procedure01]
		public String oracle_empleados_procedure01; 
		
		@Value( "${propiedades.oracle.empleados.procedure02}" )       //ACCESO: [propiedades.oracle.empleados.procedure02]
		public String oracle_empleados_procedure02; 
		
		@Value( "${propiedades.oracle.empleados.procedure03}" )       //ACCESO: [propiedades.oracle.empleados.procedure03]
		public String oracle_empleados_procedure03; 
		
		@Value( "${propiedades.oracle.empleados.procedure04}" )       //ACCESO: [propiedades.oracle.empleados.procedure04]
		public String oracle_empleados_procedure04; 
		
		
		//[DEPARTAMENTOS]: 
		@Value( "${propiedades.oracle.departamentos.package}" )       //ACCESO: [propiedades.oracle.departamentos.package]
		public String oracle_departamentos_package;  
	    
		@Value( "${propiedades.oracle.departamentos.procedure01}" )   //ACCESO: [propiedades.oracle.departamentos.procedure01]
		public String oracle_departamentos_procedure01; 
		
		@Value( "${propiedades.oracle.departamentos.procedure02}" )   //ACCESO: [propiedades.oracle.departamentos.procedure02]
		public String oracle_departamentos_procedure02; 
		
		@Value( "${propiedades.oracle.departamentos.procedure03}" )   //ACCESO: [propiedades.oracle.departamentos.procedure03]
		public String oracle_departamentos_procedure03;
		
		@Value( "${propiedades.oracle.departamentos.procedure04}" )   //ACCESO: [propiedades.oracle.departamentos.procedure04]
		public String oracle_departamentos_procedure04;
		
		
		//[ORGANIZACIONES]: 
		@Value( "${propiedades.oracle.organizaciones.package}" )       //ACCESO: [propiedades.oracle.organizaciones.package]
		public String oracle_organizaciones_package;  
	    
		@Value( "${propiedades.oracle.organizaciones.procedure01}" )   //ACCESO: [propiedades.oracle.organizaciones.procedure01]
		public String oracle_organizaciones_procedure01; 
		
		@Value( "${propiedades.oracle.organizaciones.procedure02}" )   //ACCESO: [propiedades.oracle.organizaciones.procedure02]
		public String oracle_organizaciones_procedure02; 
		
		@Value( "${propiedades.oracle.organizaciones.procedure03}" )   //ACCESO: [propiedades.oracle.organizaciones.procedure03]
		public String oracle_organizaciones_procedure03; 	
		//----------------------------------------------------------------------------------------------------------------------------//
  
		
		public static String IP_APP_OK      = "1.1.1.1";
		public static String USUARIO_APP_OK = "RGUERRA"; 
		
		public static String IP_APP_NOK      = "1.1.1.1";
		public static String NOMBRE_APP_NOK  = "IL";
		public static String USUARIO_APP_NOK = "RGUERRA";
		public static String COD_APP_NOK     = "-1";
		public static String COD_APP_OK      = "0";
		public static String MSJ_APP_NOK     = "ERROR EN EL PROCESO: ";
		public static String MSJ_APP_OK      = "PROCESO OK";
		
 }

 