package pe.com.capacitacion.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.Organizacion; 
import pe.com.capacitacion.dao.OrganizacionDao;
import pe.com.capacitacion.dto.DBResponseOrgMsg; 
import pe.com.capacitacion.dto.ResponseOrgMsg; 
import pe.com.capacitacion.exception.AuditoriaOrgException;
import pe.com.capacitacion.properties.ConfigurationData_01; 
import pe.com.capacitacion.util.Constantes;

/**
 * OrganziacionService
 * @author cguerra
 **/
 @Slf4j      //Autogenerar LOG4J. 
 @Service
 public class OrganizacionService extends AuditoriaOrgException{
 
		@Autowired
		private Constantes constantes; 
 
		@Autowired
		private AuditoriaOrgException objAuditoriaOrgException;  
 
		@Autowired
		private OrganizacionDao OrganizacionDao;  
		
		
        @Autowired
        private ConfigurationData_01 objConfigurationData01;   //ACCESO: inicia con [grupoconfig01]  
 
	   /**
	    * agregarOrganizacionService 	
	    * @param  organizacion
	    * @return ResponseEntity<ResponseOrgMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseOrgMsg> agregarOrganizacionService( Organizacion organizacion ){ 
			   log.info( "------> Organizacion 'agregarOrganizacionService': {}", organizacion );
			   
			   DBResponseOrgMsg objDBResponseOrglMsg = null;
			   ResponseOrgMsg   objResponseOrglMsg   = new ResponseOrgMsg(); 
			   Auditoria        objAuditoria         = null; 
			   
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
			       objDBResponseOrglMsg = this.OrganizacionDao.agregarOrganizacion( organizacion );    
 			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseOrglMsg.getCodigoOUT();
			   String vDetalle = objDBResponseOrglMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaOrgException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle ); 
			   
			   //Agregando:  
			   objResponseOrglMsg.setAuditoria( objAuditoria );
			   
			   ResponseEntity<ResponseOrgMsg> objRetorno = new ResponseEntity<ResponseOrgMsg>( objResponseOrglMsg, HttpStatus.OK ); 
			   return objRetorno;				   
		}		
	
	   /**
	    * eliminarOrganizacionService 	
	    * @param  id
	    * @return ResponseEntity<ResponseOrgMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseOrgMsg> eliminarOrganizacionService(  Long id ){ 
			   log.info( "------> Organizacion 'eliminarOrganizacionService': {}", id );
			   
			   DBResponseOrgMsg objDBResponseOrglMsg = null;
			   ResponseOrgMsg   objResponseOrglMsg   = new ResponseOrgMsg(); 
			   Auditoria        objAuditoria         = null; 
			   
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
			       objDBResponseOrglMsg = this.OrganizacionDao.eliminarOrganizacion( id.intValue() );    
 			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseOrglMsg.getCodigoOUT();
			   String vDetalle = objDBResponseOrglMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaOrgException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle ); 
			   
			   //Agregando:  
			   objResponseOrglMsg.setAuditoria( objAuditoria );
			   
			   ResponseEntity<ResponseOrgMsg> objRetorno = new ResponseEntity<ResponseOrgMsg>( objResponseOrglMsg, HttpStatus.OK ); 
			   return objRetorno;			   
		}
		
		
	   /**
	    * consultarOrganizacionesAllService	
	    * @return ResponseEntity<ResponseOrgMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseEntity<ResponseOrgMsg> consultarOrganizacionesAllService(){
			   log.info( "------> Organizacion 'consultarOrganizacionesAllService'" );
			   
			   DBResponseOrgMsg objDBResponseOrglMsg = null;
			   ResponseOrgMsg   objResponseOrglMsg   = new ResponseOrgMsg(); 
			   Auditoria        objAuditoria         = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
				   objDBResponseOrglMsg = this.OrganizacionDao.consultarOrganizaciones( 0 ); //LISTA COMPLETA.
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseOrglMsg.getCodigoOUT();
			   String vDetalle = objDBResponseOrglMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaOrgException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseOrglMsg.setListaOrganizaciones( objDBResponseOrglMsg.getListaOrganizaciones() );
			   objResponseOrglMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseOrgMsg> objRetorno = new ResponseEntity<ResponseOrgMsg>( objResponseOrglMsg, HttpStatus.OK ); 
			   return objRetorno; 
		} 
		
	   /**
	    * consultarOrganizacionesPorIdService	
	    * @param  id
	    * @return ResponseEntity<ResponseOrgMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseOrgMsg> consultarOrganizacionesPorIdService( Long id ){
			   log.info( "------> Organizacion 'consultarOrganizacionesPorIdService': id={}", id ); 
			   
			   DBResponseOrgMsg objDBResponseOrglMsg = null;
			   ResponseOrgMsg   objResponseOrglMsg   = new ResponseOrgMsg(); 
			   Auditoria        objAuditoria         = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
				   objDBResponseOrglMsg = this.OrganizacionDao.consultarOrganizaciones( id.intValue() ); //OBJETO
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseOrglMsg.getCodigoOUT();
			   String vDetalle = objDBResponseOrglMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaOrgException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseOrglMsg.setListaOrganizaciones( objDBResponseOrglMsg.getListaOrganizaciones() ); 
			   objResponseOrglMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseOrgMsg> objRetorno = new ResponseEntity<ResponseOrgMsg>( objResponseOrglMsg, HttpStatus.OK ); 
			   return objRetorno; 
		}
 
		 
	   /**
	    * mostrarVariablesEntorno
	    * @param constantesParam
	    * @param objConfigurationData01Param 
	    **/
        private void mostrarVariablesEntorno( Constantes constantesParam, ConfigurationData_01 objConfigurationData01Param ){
        	    log.info( "-----> Organizacion 'mostrarVariablesEntorno'" );
        	    
			    String vNombreServicio  = constantesParam.nombreServicio; 
			    String vValor_01        = constantesParam.valor01; 
			    String vNombres         = objConfigurationData01Param.getNombres();
			    String vDni             = objConfigurationData01Param.getDni(); 
			   
			    log.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" );         
        }
        
 }
 
