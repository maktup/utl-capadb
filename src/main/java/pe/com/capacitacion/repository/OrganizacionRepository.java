package pe.com.capacitacion.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import pe.com.capacitacion.bean.Organizacion;

/**
 * OrganizacionRepository
 * @author cguerra
 **/
 public class OrganizacionRepository{
	
		private List<Organizacion> listaOrganizacion = new ArrayList<>(); 
		
	   /**
	    * agregarOrganizacion
	    * @param  organizacion 
	    **/ 
		public void agregarOrganizacion( Organizacion organizacion ){
			   organizacion.setId( (long)( this.listaOrganizacion.size() + 1 ) );
			   this.listaOrganizacion.add( organizacion ); 
		}
		
	   /**
	    * consultarOrganizacionesPorId	
	    * @param  id
	    * @return Organizacion
	    **/
		public Organizacion consultarOrganizacionesPorId( Long id ){ 
			   Optional<Organizacion> objOptional     = this.listaOrganizacion.stream().filter( a -> a.getId().equals( id ) ).findFirst();  
			   Organizacion           objOrganizacion = null; 
			   
			   if( objOptional.isPresent() ){
				   objOrganizacion = objOptional.get();
			   }
			   else{
				   objOrganizacion = null;
			   }
			   
			   return objOrganizacion; 
		}
		
       /**
        * consultarOrganizacionesAll		
        * @return List<Organizacion>
        **/ 
		public List<Organizacion> consultarOrganizacionesAll(){
			   return this.listaOrganizacion;
		}
	
 }
