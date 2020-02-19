package pe.com.capacitacion.dao.mapper;
 
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.*; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Empleado; 
  
/**
 * @author cguerra.
 * @clase: EmpleadoSqlReturnType.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/
 @Slf4j      //Autogenerar LOG4J.
 public class EmpleadoSqlReturnType implements SqlReturnType{
	 
	   /**
	    * mapRow
	    * @param  rs
	    * @param  numeroFila 
	    * @return Empleado
	    **/	 
	    @SuppressWarnings( "rawtypes" )
	    private RowMapper rowMapper = new RowMapper<Empleado>(){
	        
	    	   /**
	    	    * mapRow
	    	    * @param  rs
	    	    * @param  numeroFila
	    	    * @return Empleado
	    	    **/
	            public Empleado mapRow( ResultSet rs, int numeroFila ) throws SQLException{
	                
	                   Empleado objBean = new Empleado();
 
	                   objBean.setId(     rs.getLong(   "EMP_ID"     ) );
	                   objBean.setNombre( rs.getString( "EMP_NOMBRE" ) );
	                   objBean.setEdad(   rs.getInt(    "EMP_EDAD"   ) );
	                   objBean.setRol(    rs.getString( "EMP_ROL"    ) );
	                   
	                   Long vIdReferencia = null;
	                   
	                   try{
	                	   vIdReferencia = rs.getLong( "DEP_ID" );
	                   }
	                   catch( Exception e ){ 
	                   } 
	                   
	                   if( vIdReferencia != null ){  
	                       objBean.setIdDep( vIdReferencia );  //REFERENCIA
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

