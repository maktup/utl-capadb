package pe.com.capacitacion.dao;
 
import pe.com.capacitacion.bean.Organizacion;
import pe.com.capacitacion.dto.DBResponseOrgMsg;
 
 public interface OrganizacionDao{
  
	    public DBResponseOrgMsg agregarOrganizacion( Organizacion OrganizacionParam ) throws Exception;
	  
		public DBResponseOrgMsg consultarOrganizaciones( int idOrganizacionParam ) throws Exception;
		
		public DBResponseOrgMsg eliminarOrganizacion( int idOrganizacionParam ) throws Exception;
		 
 }
