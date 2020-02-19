package pe.com.capacitacion.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.Departamento;
import pe.com.capacitacion.dao.DepartamentoDao;
import pe.com.capacitacion.dto.DBResponseDepMsg; 
import pe.com.capacitacion.dto.ResponseDepMsg; 
import pe.com.capacitacion.exception.AuditoriaDepException; 
import pe.com.capacitacion.properties.ConfigurationData_01; 
import pe.com.capacitacion.util.Constantes;

/**
 * DepartamentoService
 * @author cguerra
 **/
 @Slf4j      //Autogenerar LOG4J.
 @Service
 public class DepartamentoService extends AuditoriaDepException{
 
		@Autowired
		private Constantes constantes; 
 
		@Autowired
		private AuditoriaDepException objAuditoriaDepException; 
 
		@Autowired
		private DepartamentoDao departamentoDao;  
		
		
        @Autowired
        private ConfigurationData_01 objConfigurationData01;   //ACCESO: inicia con [grupoconfig01]  
 
	   /**
	    * agregarOrganizacionService 	
	    * @param  departamento
	    * @return ResponseEntity<ResponseDepMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseDepMsg> agregarDepartamentoService( Departamento departamento ){ 
			   log.info( "------> Departamento 'agregarDepartamentoService': {}", departamento );
			   
			   DBResponseDepMsg objDBResponseDepMsg = null; 
			   ResponseDepMsg   objResponseDepMsg   = new ResponseDepMsg(); 
			   Auditoria        objAuditoria        = null; 
			   
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			       objDBResponseDepMsg = this.departamentoDao.agregarDepartamento( departamento );    
 			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseDepMsg.getCodigoOUT();
			   String vDetalle = objDBResponseDepMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaDepException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle ); 
			   
			   //Agregando:  
			   objResponseDepMsg.setAuditoria( objAuditoria );
			   
			   ResponseEntity<ResponseDepMsg> objRetorno = new ResponseEntity<ResponseDepMsg>( objResponseDepMsg, HttpStatus.OK ); 
			   return objRetorno;			   
		}		
	
	   /**
	    * eliminarDepartamentoService 	
	    * @param  id
	    * @return ResponseEntity<ResponseDepMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseDepMsg> eliminarDepartamentoService(  Long id ){ 
			   log.info( "------> Departamento 'eliminarDepartamentoService': {}", id );
			   
			   DBResponseDepMsg objDBResponseDepMsg = null;
			   ResponseDepMsg   objResponseDepMsg   = new ResponseDepMsg(); 
			   Auditoria        objAuditoria        = null; 
			   
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
			       objDBResponseDepMsg = this.departamentoDao.eliminarDepartamento( id.intValue() );    
 			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseDepMsg.getCodigoOUT();
			   String vDetalle = objDBResponseDepMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaDepException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle ); 
			   
			   //Agregando:  
			   objResponseDepMsg.setAuditoria( objAuditoria );
			   
			   ResponseEntity<ResponseDepMsg> objRetorno = new ResponseEntity<ResponseDepMsg>( objResponseDepMsg, HttpStatus.OK ); 
			   return objRetorno;				   
		}
				
	   /**
	    * consultarDepartamentosAllService	
	    * @return ResponseEntity<ResponseDepMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseEntity<ResponseDepMsg> consultarDepartamentosAllService(){
			   log.info( "------> Departamento 'consultarDepartamentosAllService'" );
			   
			   DBResponseDepMsg objDBResponseDepMsg = null;
			   ResponseDepMsg   objResponseDepMsg   = new ResponseDepMsg(); 
			   Auditoria        objAuditoria        = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
				   objDBResponseDepMsg = this.departamentoDao.consultarDepartamentos( 0 ); //LISTA COMPLETA.
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseDepMsg.getCodigoOUT();
			   String vDetalle = objDBResponseDepMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaDepException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseDepMsg.setListaDepartamentos( objDBResponseDepMsg.getListaDepartamentos() );
			   objResponseDepMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseDepMsg> objRetorno = new ResponseEntity<ResponseDepMsg>( objResponseDepMsg, HttpStatus.OK ); 
			   return objRetorno;	
		} 
		
	   /**
	    * consultarDepartamentos_x_organizacionService
	    * @param  idOrg
	    * @return ResponseEntity<ResponseDepMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseEntity<ResponseDepMsg> consultarDepartamentos_x_organizacionService( Long idOrg ){
			   log.info( "------> Departamento 'consultarDepartamentos_x_organizacionService': idOrg={}", idOrg ); 
			   
			   DBResponseDepMsg objDBResponseDepMsg = null;
			   ResponseDepMsg   objResponseDepMsg   = new ResponseDepMsg(); 
			   Auditoria        objAuditoria        = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
				   objDBResponseDepMsg = this.departamentoDao.consultarDepartamentos_x_organizacion( idOrg.intValue() ); //LISTA COMPLETA.
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseDepMsg.getCodigoOUT();
			   String vDetalle = objDBResponseDepMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaDepException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseDepMsg.setListaDepartamentos( objDBResponseDepMsg.getListaDepartamentos() );
			   objResponseDepMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseDepMsg> objRetorno = new ResponseEntity<ResponseDepMsg>( objResponseDepMsg, HttpStatus.OK ); 
			   return objRetorno;	
		} 
				
	   /**
	    * consultarOrganizacionesPorIdService	
	    * @param  id
	    * @return ResponseEntity<ResponseDepMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseDepMsg> consultarDepartamentosPorIdService( Long id ){
			   log.info( "------> Departamento 'consultarDepartamentosPorIdService': id={}", id ); 
			   
			   DBResponseDepMsg objDBResponseDepMsg = null;
			   ResponseDepMsg   objResponseDepMsg   = new ResponseDepMsg(); 
			   Auditoria        objAuditoria        = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01 ); 
				   objDBResponseDepMsg = this.departamentoDao.consultarDepartamentos( id.intValue() ); //OBJETO
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseDepMsg.getCodigoOUT();
			   String vDetalle = objDBResponseDepMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaDepException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseDepMsg.setListaDepartamentos( objDBResponseDepMsg.getListaDepartamentos() ); 
			   objResponseDepMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseDepMsg> objRetorno = new ResponseEntity<ResponseDepMsg>( objResponseDepMsg, HttpStatus.OK ); 
			   return objRetorno;	 
		}
 		 
	   /**
	    * mostrarVariablesEntorno
	    * @param constantesParam
	    * @param objConfigurationData01Param 
	    **/
        private void mostrarVariablesEntorno( Constantes constantesParam, ConfigurationData_01 objConfigurationData01Param ){
        	    log.info( "-----> Departamento 'mostrarVariablesEntorno'" );
        	    
			    String vNombreServicio  = constantesParam.nombreServicio; 
			    String vValor_01        = constantesParam.valor01; 
			    String vNombres         = objConfigurationData01Param.getNombres();
			    String vDni             = objConfigurationData01Param.getDni(); 	     
			   
			    log.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" );  
        }
 
 }
 
