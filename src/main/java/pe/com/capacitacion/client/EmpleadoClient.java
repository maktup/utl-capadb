package pe.com.capacitacion.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.com.capacitacion.bean.Empleado;

/**
 * EmpleadoClient
 * @author cguerra
 **/
 @FeignClient( name = "employee-service" )
 public interface EmpleadoClient{

	   /**
	    * consultarEmpleadosPorDepartamento
	    * @param  organizationId
	    * @return List<Empleado> 
	    **/
	    @GetMapping( "/employeeservice/get/departamentos/{id}/empleados" )
	    public List<Empleado> consultarEmpleadosPorDepartamento( @PathVariable( "id" ) Long organizationId ); 
	
 }
