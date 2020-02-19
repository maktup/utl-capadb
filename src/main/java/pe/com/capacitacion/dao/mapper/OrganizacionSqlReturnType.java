package pe.com.capacitacion.dao.mapper;
 
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.*; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Organizacion; 
  
/**
 * @author cguerra.
 * @clase: OrganizacionSqlReturnType.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/
 @Slf4j      //Autogenerar LOG4J.
 public class OrganizacionSqlReturnType implements SqlReturnType{
 
	   /**
	    * mapRow
	    * @param  rs
	    * @param  numeroFila 
	    * @return Organizacion
	    **/	 
	    @SuppressWarnings( "rawtypes" )
	    private RowMapper rowMapper = new RowMapper<Organizacion>(){
	        
	    	   /**
	    	    * mapRow
	    	    * @param  rs
	    	    * @param  numeroFila
	    	    * @return Organizacion
	    	    **/
	            public Organizacion mapRow( ResultSet rs, int numeroFila ) throws SQLException{
	                
	                   Organizacion objBean = new Organizacion();
 
	                   objBean.setId(        rs.getLong(   "ORG_ID"        ) );
	                   objBean.setNombre(    rs.getString( "ORG_NOMBRE"    ) );
	                   objBean.setDireccion( rs.getString( "ORG_DIRECCION" ) );  
	                 
	                   return objBean;
	            }
	     };
		
	   /**
	    * getTypeValue
	    * @param  cs
	    * @param  ix 
	    * @param  sqlType 
	    * @param  typeName 
	    * @return Object
	    **/
	    @SuppressWarnings( { "rawtypes", "unchecked" } )
	    @Override
	    public Object getTypeValue( CallableStatement cs, int ix, int sqlType, String typeName ) throws SQLException{
	        
	        ResultSet rs    = null;
	        List      lista = null;   
	        
	        try{
	            rs    = (ResultSet)cs.getObject( ix );
	            lista = new ArrayList(); 
	            
	            for( int i=0; rs.next(); i++ ){
	                 lista.add( rowMapper.mapRow( rs, i ) );
	            }
	            
	            return lista;
	        }
	        catch( SQLException e ){               
	               log.info( "ERROR [SQLException]: " + e.getMessage() );
	               
	               if( (e.getMessage() != null) && 
	                   (e.getMessage().startsWith( "Cursor is closed" ) ) ){
	                   log.info( "[Cursor is closed]: " );
	                   
	                   return new ArrayList();
	               }
	               else{
	                    throw e;
	               }
	        }
	        finally{
	                if( rs != null ){
	                    rs.close();
	                }
	        }
	    }
	
 }

