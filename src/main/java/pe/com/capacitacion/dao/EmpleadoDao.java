package pe.com.capacitacion.dao;
 
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.dto.DBResponseEmpMsg;
 
 public interface EmpleadoDao{
  
	    public DBResponseEmpMsg agregarEmpleado( Empleado empleadoParam ) throws Exception;
	  
		public DBResponseEmpMsg consultarEmpleados( int idEmpleadoParam ) throws Exception;
		
		public DBResponseEmpMsg consultarEmpleados_x_departamento( int idDepartamentoParam ) throws Exception;
		
		public DBResponseEmpMsg eliminarEmpleado( int idEmpleadoParam ) throws Exception;
		 
 }
