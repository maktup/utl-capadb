package pe.com.capacitacion.service;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service; 
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Auditoria;
import pe.com.capacitacion.bean.Empleado; 
import pe.com.capacitacion.dao.EmpleadoDao;
import pe.com.capacitacion.dto.DBResponseEmpMsg;
import pe.com.capacitacion.dto.ResponseEmpMsg; 
import pe.com.capacitacion.exception.AuditoriaEmpException;
import pe.com.capacitacion.properties.ConfigurationData_01;
import pe.com.capacitacion.util.Constantes; 

/**
 * EmpleadoService
 * @author cguerra
 **/
 @Slf4j      //Autogenerar LOG4J.
 @Service
 public class EmpleadoService extends AuditoriaEmpException{
 
		@Autowired
		private Constantes constantes; 
 
		@Autowired
		private AuditoriaEmpException objAuditoriaException; 
 
		@Autowired
		private EmpleadoDao empleadoDao;  
		
		
        @Autowired
        private ConfigurationData_01 objConfigurationData01;   //ACCESO: inicia con [grupoconfig01]  
 
	   /**
	    * agregarOrganizacionService 	
	    * @param  empleado
	    * @return ResponseEntity<ResponseEmpMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmpMsg> agregarEmpleadoService( Empleado empleado ){ 
			   log.info( "------> Empleado 'agregarEmpleadoService': {}", empleado );
			   
			   DBResponseEmpMsg objDBResponseEmplMsg = null;
			   ResponseEmpMsg   objResponseEmplMsg   = new ResponseEmpMsg(); 
			   Auditoria        objAuditoria         = null; 
			   
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
			       objDBResponseEmplMsg = this.empleadoDao.agregarEmpleado( empleado );    
 			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseEmplMsg.getCodigoOUT();
			   String vDetalle = objDBResponseEmplMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle ); 
			   
			   //Agregando:  
			   objResponseEmplMsg.setAuditoria( objAuditoria );
			   
			   ResponseEntity<ResponseEmpMsg> objRetorno = new ResponseEntity<ResponseEmpMsg>( objResponseEmplMsg, HttpStatus.OK ); 
			   return objRetorno;		   
		}		
	
	   /**
	    * eliminarEmpleadoService 	
	    * @param  id
	    * @return ResponseEntity<ResponseEmpMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmpMsg> eliminarEmpleadoService(  Long id ){ 
			   log.info( "------> Empleado 'eliminarEmpleadoService': {}", id );
			   
			   DBResponseEmpMsg objDBResponseEmplMsg = null;
			   ResponseEmpMsg   objResponseEmplMsg   = new ResponseEmpMsg(); 
			   Auditoria        objAuditoria         = null; 
			   
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
			       objDBResponseEmplMsg = this.empleadoDao.eliminarEmpleado( id.intValue() );    
 			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseEmplMsg.getCodigoOUT();
			   String vDetalle = objDBResponseEmplMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle ); 
			   
			   //Agregando:  
			   objResponseEmplMsg.setAuditoria( objAuditoria );
			   
			   ResponseEntity<ResponseEmpMsg> objRetorno = new ResponseEntity<ResponseEmpMsg>( objResponseEmplMsg, HttpStatus.OK ); 
			   return objRetorno;			   
		}
				
	   /**
	    * consultarEmpleadosAllService	
	    * @return ResponseEntity<ResponseEmpMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarListaExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarListaExceptionWS].
		public ResponseEntity<ResponseEmpMsg> consultarEmpleadosAllService(){
			   log.info( "------> Empleado 'consultarEmpleadosAllService'" );
			   
			   DBResponseEmpMsg objDBResponseEmplMsg = null;
			   ResponseEmpMsg   objResponseEmplMsg   = new ResponseEmpMsg(); 
			   Auditoria        objAuditoria         = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
				   objDBResponseEmplMsg = this.empleadoDao.consultarEmpleados( 0 ); //LISTA COMPLETA.
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseEmplMsg.getCodigoOUT();
			   String vDetalle = objDBResponseEmplMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseEmplMsg.setListaEmpleados( objDBResponseEmplMsg.getListaEmpleados() );
			   objResponseEmplMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseEmpMsg> objRetorno = new ResponseEntity<ResponseEmpMsg>( objResponseEmplMsg, HttpStatus.OK ); 
			   return objRetorno;
		} 
		
	   /**
	    * consultarOrganizacionesPorIdService	
	    * @param  id
	    * @return ResponseEntity<ResponseEmpMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmpMsg> consultarEmpleadosPorIdService( Long id ){
			   log.info( "------> Empleado 'consultarEmpleadosPorIdService': id={}", id ); 
			   
			   DBResponseEmpMsg objDBResponseEmplMsg = null;
			   ResponseEmpMsg   objResponseEmplMsg   = new ResponseEmpMsg(); 
			   Auditoria        objAuditoria         = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
				   objDBResponseEmplMsg = this.empleadoDao.consultarEmpleados( id.intValue() ); //OBJETO
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseEmplMsg.getCodigoOUT();
			   String vDetalle = objDBResponseEmplMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseEmplMsg.setListaEmpleados( objDBResponseEmplMsg.getListaEmpleados() ); 
			   objResponseEmplMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseEmpMsg> objRetorno = new ResponseEntity<ResponseEmpMsg>( objResponseEmplMsg, HttpStatus.OK ); 
			   return objRetorno; 
		}
 
	   /**
	    * consultarEmpleados_x_departamentoService	
	    * @param  idDepartamento
	    * @return ResponseEntity<ResponseEmpMsg>
	    **/ 
		@HystrixCommand( fallbackMethod = "lanzarExceptionWS" )   //ANTE UNA FALLA LANZARPA EL MÉTODO: [lanzarExceptionWS].
		public ResponseEntity<ResponseEmpMsg> consultarEmpleados_x_departamentoService( Long idDepartamento ){
			   log.info( "------> Empleado 'consultarEmpleados_x_departamentoService': idDepartamento={}", idDepartamento ); 
			   
			   DBResponseEmpMsg objDBResponseEmplMsg = null;
			   ResponseEmpMsg   objResponseEmplMsg   = new ResponseEmpMsg(); 
			   Auditoria        objAuditoria         = null; 
		 
			   try{
				   this.mostrarVariablesEntorno( this.constantes, this.objConfigurationData01); 
				   objDBResponseEmplMsg = this.empleadoDao.consultarEmpleados_x_departamento( idDepartamento.intValue() ); //LISTA
			   } 
			   catch( Exception e ){ 
				      e.printStackTrace();
			   }   
			   
			   String vCodigo  = objDBResponseEmplMsg.getCodigoOUT();
			   String vDetalle = objDBResponseEmplMsg.getMensajeOUT();  
			   
			   objAuditoria = this.objAuditoriaException.cargarDatosAuditoria( Constantes.IP_APP_OK, this.constantes.nombreServicio, Constantes.USUARIO_APP_OK, vCodigo, vDetalle );  

			   //Agregando: 
			   objResponseEmplMsg.setListaEmpleados( objDBResponseEmplMsg.getListaEmpleados() ); 
			   objResponseEmplMsg.setAuditoria( objAuditoria ); 
			     
			   ResponseEntity<ResponseEmpMsg> objRetorno = new ResponseEntity<ResponseEmpMsg>( objResponseEmplMsg, HttpStatus.OK ); 
			   return objRetorno; 
		}
		 
	   /**
	    * mostrarVariablesEntorno
	    * @param constantesParam
	    * @param objConfigurationData01Param 
	    **/
        private void mostrarVariablesEntorno( Constantes constantesParam, ConfigurationData_01 objConfigurationData01Param ){
        	    log.info( "-----> Empleado 'mostrarVariablesEntorno'" );
        	    
			    String vNombreServicio  = constantesParam.nombreServicio; 
			    String vValor_01        = constantesParam.valor01; 
			    String vNombres         = objConfigurationData01Param.getNombres();
			    String vDni             = objConfigurationData01Param.getDni(); 	
			   
			    log.info( "vNombreServicio: [" + vNombreServicio + "], vValor_01: [" + vValor_01 + "], vNombres: [" + vNombres + "], vDni: [" + vDni + "]" ); 
        }
		
 }
 
