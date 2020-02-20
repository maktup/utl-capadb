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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 @Api( value="CapaDbController", description="'CONTRATO/API' para el utilitario 'CAPADB'." )
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
	    @ApiOperation( value="Funcionalidad para [CREAR] una ENTIDAD de tipo Empleado.", nickname="agregarEmpleado", notes="Funcionalidad para [CREAR] una ENTIDAD de tipo Empleado." )
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado completa.", nickname="consultarEmpleadosAll", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado completa." )
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por ID.", nickname="consultarEmpleadosPorId", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por ID." )
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por IDDEP.", nickname="consultarEmpleados_x_departamento", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Empleado por IDDEP." )
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
	    @ApiOperation( value="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Empleado por ID.", nickname="eliminarEmpleado", notes="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Empleado por ID." )
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
	    @ApiOperation( value="Funcionalidad para [CREAR] una ENTIDAD de tipo Departamento.", nickname="agregarDepartamento", notes="Funcionalidad para [CREAR] una ENTIDAD de tipo Empleado." )
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Departamento completa.", nickname="consultarDepartamentosAll", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Departamento completa." )  
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Departamento por ID.", nickname="consultarDepartamentosPorId", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Departamento por ID." )
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Departamento por IDORG.", nickname="consultarDepartamentos_x_organizacion", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Departamento por IDORG." ) 
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
	    @ApiOperation( value="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Departamento por ID.", nickname="eliminarDepartamento", notes="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Departamento por ID." )
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
	    @ApiOperation( value="Funcionalidad para [CREAR] una ENTIDAD de tipo Organizacion.", nickname="agregarOrganizacion", notes="Funcionalidad para [CREAR] una ENTIDAD de tipo Organizacion." )
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Organizacion completa.", nickname="consultarOrganizacionesAll", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Organizacion completa." )  
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
	    @ApiOperation( value="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Organizacion por ID.", nickname="consultarOrganizacionesPorId", notes="Funcionalidad para [CONSULTAR] una ENTIDAD de tipo Organizacion por ID." )
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
	    @ApiOperation( value="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Organizacion por ID.", nickname="eliminarOrganizacion", notes="Funcionalidad para [ELIMINAR] una ENTIDAD de tipo Organizacion por ID." ) 
		public ResponseEntity<ResponseOrgMsg> eliminarOrganizacion( @PathVariable( "id" ) Long id ){
			   log.info( "-----> CapaDbController 'eliminarOrganizacion': id={}", id ); 
			   
			   //Ejecutar: 
			   ResponseEntity<ResponseOrgMsg> objResponseMsg = this.organizacionService.eliminarOrganizacionService( id );
			   return objResponseMsg; 
		}
 
 }
 
