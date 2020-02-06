package pe.com.capacitacion.controller;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.com.capacitacion.bean.Departamento;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.Organizacion;
import pe.com.capacitacion.bean.ResponseMsg;
import pe.com.capacitacion.service.CapaDbService;
import pe.com.capacitacion.util.UtilJeager;

/**
 * CapaDbController
 * @author cguerra
 **/
 @RestController 
 @RequestMapping( "/capaDbService" )
 public class CapaDbController extends UtilJeager{
	
		private static final Logger LOGGER = LoggerFactory.getLogger( CapaDbController.class );
        
		//@Autowired
		//private CapaDbService capaDbService;
 
		
		/*************************************************************************************/
		/************************************ [EMPLEADOS] ************************************/
		/*************************************************************************************/
 
		@PostMapping( "/post/empleados" )
		public ResponseMsg agregarEmpleado( @RequestBody Empleado empleado ){ 
			   LOGGER.info( "CapaDbService 'agregarEmpleado': {}", empleado );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar:  
			   ResponseMsg objResponseMsg = null; //this.objEmpleadoService.agregarEmpleadoService( empleado ); 
			   return objResponseMsg; 
		}
 
		@GetMapping( "/get/empleados" )
		public ResponseMsg consultarEmpleadosAll(){
			   LOGGER.info( "CapaDbService 'consultarEmpleadosAll'" );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objEmpleadoService.consultarEmpleadosAllService(); 
			   return objResponseMsg; 
		}
 
		@GetMapping( "/get/empleados/{id}" )
		public ResponseMsg consultarEmpleadosPorId( @PathVariable( "id" ) Long id ){
			   LOGGER.info( "CapaDbService 'consultarEmpleadosPorId': id={}", id );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objEmpleadoService.consultarEmpleadosPorIdService( id );
			   return objResponseMsg; 
		}
 
		@GetMapping( "/get/departamentos/{departmentId}/empleados" )
		public ResponseMsg consultarEmpleadosPorDepartamento( @PathVariable( "departmentId" ) Long departmentId ){
			   LOGGER.info( "CapaDbService 'consultarEmpleadosPorDepartamento': departmentId={}", departmentId );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objEmpleadoService.consultarEmpleadosPorDepartamentoService( departmentId );
			   return objResponseMsg; 
		}
		
		
		/*************************************************************************************/
		/********************************** [DEPARTAMENTOS] **********************************/
		/*************************************************************************************/
		@PostMapping( "/post/departamentos" )
		public ResponseMsg agregarDepartamento( @RequestBody Departamento departamento ){
			   LOGGER.info( "-----> CapaDbService 'agregarDepartamento': {}", departamento ); 
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objDepartamentoService.agregarDepartamentoService( departamento );  
			   return objResponseMsg; 
		}
		
		@GetMapping( "/get/departamentos" )
		public ResponseMsg consultarDepartamentosAll(){ 
			   LOGGER.info( "-----> CapaDbService 'consultarDepartamentosAll'" );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objDepartamentoService.consultarDepartamentosAllService(); 	 
			   return objResponseMsg; 
		}
		
		@GetMapping( "/get/departamentos/{id}" )
		public ResponseMsg consultarDepartamentosPorId( @PathVariable( "id" ) Long id ){ 
			   LOGGER.info( "-----> CapaDbService 'consultarDepartamentosPorId': id={}", id );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objDepartamentoService.consultarDepartamentosPorIdService( id ); 
			   return objResponseMsg; 
		}	
		
		@GetMapping( "/get/organizaciones/{organizationId}/departamentos" )
		public ResponseMsg consultarDepartamentosPorOrganizacion( @PathVariable( "organizationId" ) Long organizationId ){
			   LOGGER.info( "-----> CapaDbService 'consultarDepartamentosPorOrganizacion': organizationId={}", organizationId );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar:  
			   ResponseMsg objResponseMsg = null; //this.objDepartamentoService.consultarDepartamentosPorOrganizacionService( organizationId ); 
			   return objResponseMsg; 
		}
   
	   /**
	    * consultarDepartamentosConEmpleadosPorOrganizacion	
	    * @param  organizationId 
	    * @return ResponseMsg
	    **/ 
		@GetMapping( "/get/organizaciones/{organizationId}/departamentos/empleados" )
		public ResponseMsg consultarDepartamentosConEmpleadosPorOrganizacion( @PathVariable( "organizationId" ) Long organizationId ){ 
			   LOGGER.info( "-----> CapaDbService 'consultarPorOrganizacionConEmpleados': organizationId={}", organizationId);
			   
			   //Ejecutar:  
			   ResponseMsg objResponseMsg = null; //this.objDepartamentoService.consultarPorOrganizacionConEmpleadosService( organizationId ); 
			   return objResponseMsg; 
		}
				
		
		/*************************************************************************************/
		/********************************** [ORGANIZACION] ***********************************/
		/*************************************************************************************/
		@PostMapping( "/post/organizaciones" )
		public ResponseMsg agregarOrganizacion( @RequestBody Organizacion organizacion ){ 
			   LOGGER.info( "-----> CapaDbService 'agregarOrganizacion': {}", organizacion );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objOrganizacionService.agregarOrganizacionService( organizacion ); 
			   return objResponseMsg; 
		}
		
		@GetMapping( "/get/organizaciones" ) 
		public ResponseMsg consultarOrganizacionesAll(){
			   LOGGER.info( "-----> CapaDbService 'consultarOrganizacionesAll'" );
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objOrganizacionService.consultarOrganizacionesAllService(); 
			   return objResponseMsg; 
		}
  
		@GetMapping( "/get/organizaciones/{id}" ) 
		public ResponseMsg consultarOrganizacionesPorId( @PathVariable( "id" ) Long id ){ 
			   LOGGER.info( "-----> CapaDbService 'consultarOrganizacionesPorId': id={}", id ); 
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objOrganizacionService.consultarOrganizacionesPorIdService( id ); 
			   return objResponseMsg; 
		}
		
		@GetMapping( "/get/organizaciones/{id}/departamentos/empleados" ) 
		public ResponseMsg consultarOrganizacionConDepartamentosPorId( @PathVariable( "id" ) Long id ){
			   LOGGER.info( "-----> CapaDbService 'consultarOrganizacionConDepartamentosPorId': id={}", id ); 
			   //this.jaegerAlertTracer(); 
			   
			   //Ejecutar: 
			   ResponseMsg objResponseMsg = null; //this.objOrganizacionService.consultarOrganizacionConDepartamentosPorIdService( id ); 
			   return objResponseMsg; 
		} 
 
 }
 
