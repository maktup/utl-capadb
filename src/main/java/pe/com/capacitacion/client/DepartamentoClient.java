package pe.com.capacitacion.client;
 
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import pe.com.capacitacion.bean.ResponseMsgDep;
 
/**
 * DepartamentoClient
 * @author cguerra
 **/
 @FeignClient( name="department-service" )
 public interface DepartamentoClient{
 
	   /**
	    * consultarDepartamentosPorOrganizacionConEmpleados	
	    * @param  organizationId
	    * @return ResponseMsg
	    **/  
		@GetMapping( value="/departmentservice/organizaciones/{id}/departamentos/empleados" )
		public ResponseMsgDep consultarDepartamentosConEmpleadosPorOrganizacion( @PathVariable( "id" ) Long id ); 
		
 }

 