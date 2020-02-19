package pe.com.capacitacion.dao;
 
import pe.com.capacitacion.bean.Departamento;
import pe.com.capacitacion.dto.DBResponseDepMsg;
 
 public interface DepartamentoDao{
  
	    public DBResponseDepMsg agregarDepartamento( Departamento DepartamentoParam ) throws Exception;
	  
		public DBResponseDepMsg consultarDepartamentos( int idDepartamentoParam ) throws Exception;
		
		public DBResponseDepMsg consultarDepartamentos_x_organizacion( int idOrganizacionParam ) throws Exception;
		
		public DBResponseDepMsg eliminarDepartamento( int idDepartamentoParam ) throws Exception;
		 
 }
