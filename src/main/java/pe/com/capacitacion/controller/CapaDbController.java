package pe.com.capacitacion.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Departamento;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.bean.Organizacion;
import pe.com.capacitacion.dto.ResponseDepMsg;
import pe.com.capacitacion.dto.ResponseEmpMsg;
import pe.com.capacitacion.dto.ResponseOrgMsg;
import pe.com.capacitacion.service.DepartamentoService;
import pe.com.capacitacion.service.EmpleadoService;
import pe.com.capacitacion.service.OrganizacionService; 

/**
 * CapaDbController
 * @author cguerra
 **/
 @Slf4j      //Autogenerar LOG4J.
 @RestController 
 @RequestMapping( "/utlcapadb" ) //NO USAR: [server.servlet.context-path], 'BOOT-ADMIN' reconocera el 'ACTUATOR'.
 public class CapaDbController{
 
		@Autowired
		private EmpleadoService empleadoService; 
 
		@Autowired
		private DepartamentoService departamentoService;  
		
		@Autowired
		private OrganizacionService organizacionService; 
		
		
		/*************************************************************************************/
		/************************************ [EMPLEADOS] ************************************/
		/*************************************************************************************/
       /**
        * agregarEmpleado
        * @param  empleado
        * @return ResponseEntity<ResponseEmpMsg>
        **/
		@PostMapping( "/post/empleados" )
		public ResponseEntity<ResponseEmpMsg> agregarEmpleado( @RequestBody Empleado empleado ){ 
			   log.info( "-----> CapaDbController 'agregarEmpleado': {}", empleado ); 
			   
			   //Ejecutar:  
			   ResponseEntity<ResponseEmpMsg> objResponseMsg = this.empleadoService.agregarEmpleadoService( empleado ); 
			   return objResponseMsg; 
		}
 
       /**
        * consultarEmpleadosAll 
        * @return ResponseEntity<ResponseEmpMsg>
        **/
		@GetMapping( "/get/empleados" )
		public ResponseEntity<ResponseEmpMsg> consultarEmpleadosAll(){
			   log.info( "-----> CapaDbController 'consultarEmpleadosAll'" ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseEmpMsg> objResponseMsg = this.empleadoService.consultarEmpleadosAllService(); 
			   return objResponseMsg; 
		}
 
       /**
        * consultarEmpleadosPorId
        * @param  id
        * @return ResponseEntity<ResponseEmpMsg>
        **/
		@GetMapping( "/get/empleados/{id}" )
		public ResponseEntity<ResponseEmpMsg> consultarEmpleadosPorId( @PathVariable( "id" ) Long id ){
			   log.info( "-----> CapaDbController 'consultarEmpleadosPorId': id={}", id ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseEmpMsg> objResponseMsg = this.empleadoService.consultarEmpleadosPorIdService( id );
			   return objResponseMsg; 
		}
 
       /**
        * consultarEmpleados_x_departamento
        * @param  idDep
        * @return ResponseEntity<ResponseEmpMsg>
        **/
		@GetMapping( "/get/empleados-departamento/{idDep}" )
		public ResponseEntity<ResponseEmpMsg> consultarEmpleados_x_departamento( @PathVariable( "idDep" ) Long idDep ){
			   log.info( "-----> CapaDbController 'consultarEmpleados_x_departamento': idDep={}", idDep ); 
			    
			   //Ejecutar: 
			   ResponseEntity<ResponseEmpMsg> objResponseMsg = this.empleadoService.consultarEmpleados_x_departamentoService( idDep );
			   return objResponseMsg; 
		}
		
       /**
        * eliminarEmpleado
        * @param  id
        * @return ResponseEntity<ResponseEmpMsg>
        **/
		@DeleteMapping( "/delete/empleados/{id}" )
		public ResponseEntity<ResponseEmpMsg> eliminarEmpleado( @PathVariable( "id" ) Long id ){
			   log.info( "-----> CapaDbController 'eliminarEmpleado': id={}", id ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseEmpMsg> objResponseMsg = this.empleadoService.eliminarEmpleadoService( id );
			   return objResponseMsg; 
		}
 
		
		/*************************************************************************************/
		/********************************** [DEPARTAMENTOS] **********************************/
		/*************************************************************************************/ 
       /**
        * agregarDepartamento
        * @param  departamento
        * @return ResponseEntity<ResponseDepMsg>
        **/
		@PostMapping( "/post/departamentos" )
		public ResponseEntity<ResponseDepMsg> agregarDepartamento( @RequestBody Departamento departamento ){
			   log.info( "-----> CapaDbController 'agregarDepartamento': {}", departamento ); 
			  
			   //Ejecutar: 
			   ResponseEntity<ResponseDepMsg> objResponseMsg = this.departamentoService.agregarDepartamentoService( departamento );  
			   return objResponseMsg; 
		}
		
       /**
        * consultarDepartamentosAll 
        * @return ResponseEntity<ResponseDepMsg>
        **/
		@GetMapping( "/get/departamentos" )
		public ResponseEntity<ResponseDepMsg> consultarDepartamentosAll(){ 
			   log.info( "-----> CapaDbController 'consultarDepartamentosAll'" );
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseDepMsg> objResponseMsg = this.departamentoService.consultarDepartamentosAllService(); 	 
			   return objResponseMsg; 
		}
		
       /**
        * consultarDepartamentosPorId
        * @param  id
        * @return ResponseEntity<ResponseDepMsg>
        **/
		@GetMapping( "/get/departamentos/{id}" )
		public ResponseEntity<ResponseDepMsg> consultarDepartamentosPorId( @PathVariable( "id" ) Long id ){ 
			   log.info( "-----> CapaDbController 'consultarDepartamentosPorId': id={}", id );
			  
			   //Ejecutar: 
			   ResponseEntity<ResponseDepMsg> objResponseMsg = this.departamentoService.consultarDepartamentosPorIdService( id ); 
			   return objResponseMsg; 
		}	
 
       /**
        * consultarDepartamentos_x_organizacion
        * @param  idOrg
        * @return ResponseEntity<ResponseDepMsg>
        **/
		@GetMapping( "/get/departamentos-organizacion/{idOrg}" )
		public ResponseEntity<ResponseDepMsg> consultarDepartamentos_x_organizacion( @PathVariable( "idOrg" ) Long idOrg ){
			   log.info( "-----> CapaDbController 'consultarDepartamentos_x_organizacion': idOrg={}", idOrg ); 
			    
			   //Ejecutar: 
			   ResponseEntity<ResponseDepMsg> objResponseMsg = this.departamentoService.consultarDepartamentos_x_organizacionService( idOrg );
			   return objResponseMsg; 
		}
		
       /**
        * eliminarDepartamento
        * @param  id
        * @return ResponseEntity<ResponseDepMsg>
        **/
		@DeleteMapping( "/delete/departamentos/{id}" )
		public ResponseEntity<ResponseDepMsg> eliminarDepartamento( @PathVariable( "id" ) Long id ){
			   log.info( "-----> CapaDbController 'eliminarDepartamento': id={}", id ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseDepMsg> objResponseMsg = this.departamentoService.eliminarDepartamentoService( id );
			   return objResponseMsg; 
		}
    
				
		
		/*************************************************************************************/
		/********************************** [ORGANIZACION] ***********************************/
		/*************************************************************************************/
		
       /**
        * agregarOrganizacion
        * @param  organizacion
        * @return ResponseEntity<ResponseOrgMsg>
        **/
		@PostMapping( "/post/organizaciones" )
		public ResponseEntity<ResponseOrgMsg> agregarOrganizacion( @RequestBody Organizacion organizacion ){ 
			   log.info( "-----> CapaDbService 'agregarOrganizacion': {}", organizacion ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseOrgMsg> objResponseMsg = this.organizacionService.agregarOrganizacionService( organizacion ); 
			   return objResponseMsg; 
		}
		
       /**
        * consultarOrganizacionesAll 
        * @return ResponseEntity<ResponseOrgMsg>
        **/
		@GetMapping( "/get/organizaciones" ) 
		public ResponseEntity<ResponseOrgMsg> consultarOrganizacionesAll(){
			   log.info( "-----> CapaDbService 'consultarOrganizacionesAll'" ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseOrgMsg> objResponseMsg = this.organizacionService.consultarOrganizacionesAllService(); 
			   return objResponseMsg; 
		}
  
       /**
        * consultarOrganizacionesPorId 
        * @param  id
        * @return ResponseEntity<ResponseOrgMsg>
        **/
		@GetMapping( "/get/organizaciones/{id}" ) 
		public ResponseEntity<ResponseOrgMsg> consultarOrganizacionesPorId( @PathVariable( "id" ) Long id ){ 
			   log.info( "-----> CapaDbService 'consultarOrganizacionesPorId': id={}", id );  
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseOrgMsg> objResponseMsg = this.organizacionService.consultarOrganizacionesPorIdService( id ); 
			   return objResponseMsg; 
		}
		
       /**
        * eliminarOrganizacion 
        * @param  id
        * @return ResponseEntity<ResponseOrgMsg>
        **/
		@DeleteMapping( "/delete/organizaciones/{id}" )
		public ResponseEntity<ResponseOrgMsg> eliminarOrganizacion( @PathVariable( "id" ) Long id ){
			   log.info( "-----> CapaDbController 'eliminarOrganizacion': id={}", id ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseOrgMsg> objResponseMsg = this.organizacionService.eliminarOrganizacionService( id );
			   return objResponseMsg; 
		}
 
 }
 
