package pe.com.capacitacion.service;

import java.util.ArrayList;
import java.util.List; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;  
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.ConfigurationData_01;
import pe.com.capacitacion.bean.ConfigurationData_02;
import pe.com.capacitacion.bean.Organizacion;
import pe.com.capacitacion.bean.ResponseMsg; 
import pe.com.capacitacion.exception.AuditoriaException;
import pe.com.capacitacion.repository.OrganizacionRepository;
import pe.com.capacitacion.util.Constantes;

/**
 * CapaDbService
 * @author cguerra
 **/
 @Service
 public class CapaDbService extends AuditoriaException{
		
		private static final Logger LOGGER = LoggerFactory.getLogger( CapaDbService.class );
	 
		//@Autowired
		//private OrganizacionRepository objRepositorio; 
		
		@Autowired
		private Constantes constantes; 
 
		@Autowired
		private AuditoriaException objAuditoriaException; 
	 
		@Autowired
		private RestTemplateBuilder objTemplate;  
		
		
        //@Autowired
        //private ConfigurationData_01 objConfigurationData01;   //ACCESO: inicia con [grupoconfig01]  

        //@Autowired
        //private ConfigurationData_02 objConfigurationData02;   //ACCESO: inicia con [grupoconfig02]  
		
        //@Autowired
        //private org.springframework.core.env.Environment env;
        
	   /**
	    * agregarOrganizacionService 	
	    * @param  organizacion
	    * @return ResponseMsg
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseMsg agregarOrganizacionService( Organizacion organizacion ){ 
			   LOGGER.info( "------> Organizacion 'agregarOrganizacionService': {}", organizacion );
			   
			   ResponseMsg objResponseMsg = new ResponseMsg();
			   //this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01, this.objConfigurationData02 ); 
			   
			   //this.objRepositorio.agregarOrganizacion( organizacion );    
			   Auditoria objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK ); 
  
			   //Agregando: 
			   objResponseMsg.setListaOrganizaciones( new ArrayList<Organizacion>() );
			   objResponseMsg.setAuditoria( objAuditoria );
			   
			   return objResponseMsg;			   
		}		
		
	   /**
	    * consultarOrganizacionesAllService	
	    * @return List<Organizacion>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseMsg consultarOrganizacionesAllService(){
			   LOGGER.info( "------> Organizacion 'consultarOrganizacionesAllService'" );
			   
			   ResponseMsg objResponseMsg = new ResponseMsg(); 
			   //this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01, this.objConfigurationData02 ); 
			   
			   //IMPORTANTE: RECUPERA EL 'SECRET' YA 'DESENCRIPTADO'.
			   //String vUsuario  =  this.env.getProperty( "BOOTADMIN_USUARIO" ); 
			   //String vPassword =  this.env.getProperty( "BOOTADMIN_USUARIO" ); 
			   //LOGGER.info( "- vUsuario: [" + vUsuario + "], - vPassword: [" + vPassword + "]" );
			   /*
			   List<Organizacion> listaOrganizacion = this.objRepositorio.consultarOrganizacionesAll();
			   Auditoria          objAuditoria      = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK ); 

			   //Agregando: 
			   objResponseMsg.setListaOrganizaciones( listaOrganizacion );
			   objResponseMsg.setAuditoria( objAuditoria );
			   */
			   return objResponseMsg; 
		}	
		
	   /**
	    * consultarOrganizacionesPorIdService	
	    * @param  id
	    * @return ResponseMsg
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseMsg consultarOrganizacionesPorIdService( Long id ){
			   LOGGER.info( "------> Organizacion 'consultarOrganizacionesPorId': id={}", id ); 
			   
		       ResponseMsg objResponseMsg = new ResponseMsg();
			   //this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01, this.objConfigurationData02 ); 
			   /*
			   Organizacion objOrganizacion = this.objRepositorio.consultarOrganizacionesPorId( id ); 
		       Auditoria    objAuditoria    = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK );
						
			   List<Organizacion> listaOrganizacion = new ArrayList<Organizacion>();
			   listaOrganizacion.add( objOrganizacion );
			
			   //Agregando: 
		       objResponseMsg.setListaOrganizaciones( listaOrganizacion );  
		       objResponseMsg.setAuditoria( objAuditoria );
		   */
		       return objResponseMsg; 
		}
		
	   /**
	    * consultarOrganizacionConDepartamentosPorIdService	
	    * @param  id
	    * @return ResponseMsg
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseMsg consultarOrganizacionConDepartamentosPorIdService( Long id ){
			   LOGGER.info( "------> Organizacion 'consultarOrganizacionConDepartamentosPorIdService': id={}", id ); 
			   
		       ResponseMsg objResponseMsg = new ResponseMsg();
			   //this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01, this.objConfigurationData02 ); 
	 /*
			   //Ejecutar: 
			   Organizacion objOrganizacion = this.objRepositorio.consultarOrganizacionesPorId( id );
			   LOGGER.info( "------> objOrganizacion: [" + objOrganizacion + "]" ); 
			   
			   
			   RestTemplate objRspTmp = this.objTemplate.build();  
			   String vURL01 = (this.constantes.ingressDepartment + "/" + Constantes.SERVICE_NAME_02 + "/" + Constantes.HTTP_METHOD_01 + "/organizaciones/" + id + "/departamentos/empleados"); 
			   LOGGER.info( "========>: vURL01 [" + vURL01 + "]" );
			   
			   String vCadenaJSON_01 = objRspTmp.getForObject( vURL01, String.class );
			   Gson objGson = new Gson();		
			   pe.com.capacitacion.bean.ResponseMsgDep objResponseMsgDep = objGson.fromJson( vCadenaJSON_01, pe.com.capacitacion.bean.ResponseMsgDep.class ); 
			   
			   //Agregando: 
			   objOrganizacion.setListaDepartamentos( objResponseMsgDep.getListaDepartamentos() ); 
			   
			   List<Organizacion> listaOrganizacion = new ArrayList<Organizacion>();
			   listaOrganizacion.add( objOrganizacion ); 
  
			   Auditoria objAuditoria  = this.objAuditoriaException.cargarDatosAuditoria( this.constantes.IP_APP, this.constantes.nombreServicio, this.constantes.USUARIO_APP, this.constantes.MSJ_APP_OK ); 
 
			   //Agregando: 
		       objResponseMsg.setListaOrganizaciones( listaOrganizacion ); 
		       objResponseMsg.setAuditoria( objAuditoria );
		   */
		       return objResponseMsg; 
		}
 
		 
	   /**
	    * mostrarVariablesEntorno
	    * @param constantesParam
	    * @param objConfigurationData01Param
	    * @param objConfigurationData02Param
	    **/
        private void mostrarVariablesEntorno( Constantes constantesParam, ConfigurationData_01 objConfigurationData01Param, ConfigurationData_02 objConfigurationData02Param ){
        	    LOGGER.info( "-----> Departamento 'mostrarVariablesEntorno'" );
        	    
			    String vNombreServicio  = constantesParam.nombreServicio; 
			    String vValor_01        = constantesParam.valor01; 
			    String vNombres         = objConfigurationData01Param.getNombres();
			    String vDni             = objConfigurationData01Param.getDni(); 		
			    String vDnsEmployee     = objConfigurationData02Param.getEmployee(); 
			    String vDnsDepartment   = objConfigurationData02Param.getDepartment(); 
			    String vDnsOrganization = objConfigurationData02Param.getOrganization();  
			   
			    LOGGER.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" ); 
			    LOGGER.info( "vDnsEmployee: [" + vDnsEmployee + "], vDnsDepartment: [" + vDnsDepartment + "], vDnsOrganization: [" + vDnsOrganization + "]" ); 
        }
		
 }
 
