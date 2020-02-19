package pe.com.capacitacion.dao.mapper;
 
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.*; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Departamento;  
  
/**
 * @author cguerra.
 * @clase: DepartamentoSqlReturnType.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/
 @Slf4j      //Autogenerar LOG4J.
 public class DepartamentoSqlReturnType implements SqlReturnType{
	  
	   /**
	    * mapRow
	    * @param  rs
	    * @param  numeroFila 
	    * @return Departamento
	    **/	 
	    @SuppressWarnings( "rawtypes" )
	    private RowMapper rowMapper = new RowMapper<Departamento>(){
	        
	    	   /**
	    	    * mapRow
	    	    * @param  rs
	    	    * @param  numeroFila
	    	    * @return Departamento
	    	    **/
	            public Departamento mapRow( ResultSet rs, int numeroFila ) throws SQLException{
	                
	            	   Departamento objBean = new Departamento(); 
	            	   objBean.setId(     rs.getLong(   "DEP_ID"     ) ); 
	            	   objBean.setNombre( rs.getString( "DEP_NOMBRE" ) );
	            	   
	                   Long vIdReferencia = null;
	                 
	                   try{
	                	   vIdReferencia = rs.getLong( "ORG_ID" );
	                   }
	                   catch( Exception e ){ 
	                   } 
	 
	                   if( vIdReferencia != null ){  
	                       objBean.setIdOrg( vIdReferencia );  //REFERENCIA 
	                   } 
	                   
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

