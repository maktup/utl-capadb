package pe.com.capacitacion.dao;

import java.util.List;
import java.util.Map; 
import javax.sql.DataSource;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.NestedRuntimeException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional; 
import lombok.extern.slf4j.Slf4j;
import pe.com.capacitacion.bean.Empleado;
import pe.com.capacitacion.dao.mapper.EmpleadoSqlReturnType;
import pe.com.capacitacion.dto.DBResponseEmpMsg; 
import pe.com.capacitacion.util.Constantes;
 
/**
 * @author cguerra.
 * @clase: EmpleadoDaoImp.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/
 @Slf4j      //Autogenerar LOG4J.
 @Repository
 public class EmpleadoDaoImp implements EmpleadoDao{
	 
	    @Autowired
	    @Qualifier( "datasource_oracle" ) 
		private DataSource datasource;  
	  
	    @Autowired
	    private Constantes constantes;   
 
		
	   /**
	    * agregarEmpleado
	    * @param     empleadoParam
	    * @returm    DBResponseEmpMsg
	    * @exception Exception
	    **/
		@Override
		@Transactional( propagation = Propagation.REQUIRED )
		public DBResponseEmpMsg agregarEmpleado( Empleado empleadoParam ) throws Exception{  
			   log.info( "[INICIO] - METODO: [agregarEmpleado - DAO]: empleadoParam: [" + empleadoParam + "]" );
	 
			   DBResponseEmpMsg   objDBResponseEmplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiempoInicio        = System.currentTimeMillis();
	     	 
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_empleados_package;
	     	       PROCEDURE = this.constantes.oracle_empleados_procedure01;  
	     	   
	     	       log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	     	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "EMP_NOMBRE_IN", empleadoParam.getNombre(), oracle.jdbc.OracleTypes.VARCHAR )
		           .addValue( "EMP_EDAD_IN",   empleadoParam.getEdad(),   oracle.jdbc.OracleTypes.INTEGER )
		           .addValue( "EMP_ROL_IN",    empleadoParam.getRol(),    oracle.jdbc.OracleTypes.VARCHAR )  
		           .addValue( "DEP_ID_IN",     empleadoParam.getIdDep(),  oracle.jdbc.OracleTypes.INTEGER );  //REFERENCIA 
		           log.info( "PARAMETROS [INPUT]: " + "EMP_NOMBRE_IN: [" + empleadoParam.getNombre() + "]" );
		           log.info( "PARAMETROS [INPUT]: " + "EMP_EDAD_IN: ["   + empleadoParam.getEdad()   + "]" );
		           log.info( "PARAMETROS [INPUT]: " + "EMP_ROL_IN: ["    + empleadoParam.getRol()    + "]" );
		           log.info( "PARAMETROS [INPUT]: " + "DEP_ID_IN: ["     + empleadoParam.getIdDep()  + "]" );
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
 
		           //PARAMETROS [IN / OUT]: 
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
				           .withoutProcedureColumnMetaDataAccess()
				           .withSchemaName(  OWNER )
			               .withCatalogName( PACKAGE )
			               .withProcedureName( PROCEDURE ) 
			               .declareParameters(  
				            	   new SqlParameter(    "EMP_NOMBRE_IN", oracle.jdbc.OracleTypes.VARCHAR ),
				            	   new SqlParameter(    "EMP_EDAD_IN",   oracle.jdbc.OracleTypes.INTEGER ),
				            	   new SqlParameter(    "EMP_ROL_IN",    oracle.jdbc.OracleTypes.VARCHAR ),
				            	   new SqlParameter(    "DEP_ID_IN",     oracle.jdbc.OracleTypes.INTEGER ),  //REFERENCIA
				            	   
					               new SqlOutParameter( "ERR_OUT",       oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "MESSAGE_OUT",   oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "EMP_ID_OUT",    oracle.jdbc.OracleTypes.INTEGER ) 
				           )  
			       .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String  vCodigoErr_OUT  = (String)objParametrosOUT.get(  "ERR_OUT"     ); 
		     	   String  vMensajeErr_OUT = (String)objParametrosOUT.get(  "MESSAGE_OUT" );  
		     	   Integer vIdRegistro_OUT = (Integer)objParametrosOUT.get( "EMP_ID_OUT"  ); 
		     	    
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" );    
				   log.info( "EMP_ID_OUT: ["  + vIdRegistro_OUT + "]" ); 
				   
		     	   //RETURN:
		     	   objDBResponseEmplMsg = new DBResponseEmpMsg();
		     	   objDBResponseEmplMsg.setCodigoOUT(     vCodigoErr_OUT  );
		     	   objDBResponseEmplMsg.setMensajeOUT(    vMensajeErr_OUT );
		     	   objDBResponseEmplMsg.setIdRegistroOUT( vIdRegistro_OUT );  
			 } 
		     catch( NestedRuntimeException e ){
		            log.error( "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e );                
		            throw new Exception( e );
		     }   
		     catch( Exception e ){
		  	        log.error( "ERROR: [Exception] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }
		     catch( Throwable e ){
		  	        log.error( "ERROR: [Throwable] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }  
			 finally{
				     log.info( "Tiempo TOTAL Proceso: [" + (vTiempoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [agregarEmpleado - DAO]" );
			 }
	           
	         return objDBResponseEmplMsg;
		}
	
	   /**
	    * consultarEmpleados
	    * @param     idEmpleadoParam
	    * @returm    DBResponseEmpMsg	
	    * @exception Exception
	    **/
		@Override
		@SuppressWarnings( "unchecked" )
		@Transactional( propagation = Propagation.NOT_SUPPORTED )
		public DBResponseEmpMsg consultarEmpleados( int idEmpleadoParam ) throws Exception{ 
			   log.info( "[INICIO] - METODO: [consultarEmpleados - DAO]: idEmpleado: [" + idEmpleadoParam + "]" );
			 
			   JdbcTemplate       objJdbcTemplate      = null;
			   DBResponseEmpMsg   objDBResponseEmplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiempoInicio        = System.currentTimeMillis();
	     	   int                vTamanio             = 0;
			   
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_empleados_package;
	     	       PROCEDURE = this.constantes.oracle_empleados_procedure02; 
	     	   
	     	       log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	     	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "EMP_ID_IN", idEmpleadoParam, oracle.jdbc.OracleTypes.INTEGER );
		           log.info( "PARAMETROS [INPUT]: " + "EMP_ID_IN: [" + idEmpleadoParam + "]" );
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
		           objJdbcTemplate = objJdbcCall.getJdbcTemplate();
		           objJdbcTemplate.setQueryTimeout( this.constantes.oracle_timeout );
		            
		           //PARAMETROS [IN / OUT]:
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
		           .withoutProcedureColumnMetaDataAccess()
		           .withSchemaName(  OWNER )
	               .withCatalogName( PACKAGE )
	               .withProcedureName( PROCEDURE )   
	               .declareParameters(  
	            	   new SqlParameter(    "EMP_ID_IN",   oracle.jdbc.OracleTypes.INTEGER ),
	            		   
		               new SqlOutParameter( "ERR_OUT",     oracle.jdbc.OracleTypes.VARCHAR ),
		               new SqlOutParameter( "MESSAGE_OUT", oracle.jdbc.OracleTypes.VARCHAR ),			                       
		               new SqlOutParameter( "CURSOR_EMPLEADOS",   
		                                    oracle.jdbc.OracleTypes.CURSOR, 
		                                    null, 
		                                    new EmpleadoSqlReturnType() ) 
	               )
	               .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String         vCodigoErr_OUT  = (String)objParametrosOUT.get( "ERR_OUT" );
		     	   String         vMensajeErr_OUT = (String)objParametrosOUT.get( "MESSAGE_OUT" ); 
				   List<Empleado> lista_OUT       = (List<Empleado>)objParametrosOUT.get( "CURSOR_EMPLEADOS" );
		     	   
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" ); 
				   log.info( "CURSOR_EMPLEADOS [TAMANIO]: [" + lista_OUT.size() + "]" ); 
		           
		     	   //RETURN:
		     	   objDBResponseEmplMsg = new DBResponseEmpMsg();
		     	   objDBResponseEmplMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseEmplMsg.setMensajeOUT( vMensajeErr_OUT );
		     	   	           
		     	   vTamanio = lista_OUT.size();
		     	   
		     	   if( (lista_OUT != null) && 
		     		   (vTamanio > 0) ){	     		   
		     		   objDBResponseEmplMsg.setObjEmpleado( lista_OUT.get( 0 ) ); //1 Objeto  
		     		   objDBResponseEmplMsg.setListaEmpleados( lista_OUT );   
		     	   }
		     	   else{
		     		    objDBResponseEmplMsg.setObjEmpleado( null ); 
		     		    objDBResponseEmplMsg.setListaEmpleados( null );   
		     	   }
			 } 
		     catch( NestedRuntimeException e ){
		            log.error( "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e );                
		            throw new Exception( e );
		     }   
		     catch( Exception e ){
		  	        log.error( "ERROR: [Exception] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }
		     catch( Throwable e ){
		  	        log.error( "ERROR: [Throwable] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }  
			 finally{
				     log.info( "Tiempo TOTAL Proceso: [" + (vTiempoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [consultarEmpleados - DAO]" );
			 }
		     
		     return objDBResponseEmplMsg;  
	     }

	
	   /**
	    * consultarEmpleados_x_departamento
	    * @param     idDepartamentoParam
	    * @returm    DBResponseEmpMsg	
	    * @exception Exception
	    **/
		@Override
		@SuppressWarnings( "unchecked" )
		@Transactional( propagation = Propagation.NOT_SUPPORTED )
		public DBResponseEmpMsg consultarEmpleados_x_departamento( int idDepartamentoParam ) throws Exception{ 
			   log.info( "[INICIO] - METODO: [consultarEmpleados_x_departamento - DAO]: idDepartamentoParam: [" + idDepartamentoParam + "]" );
			 
			   JdbcTemplate       objJdbcTemplate      = null;
			   DBResponseEmpMsg   objDBResponseEmplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiempoInicio        = System.currentTimeMillis();
	     	   int                vTamanio             = 0;
			   
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_empleados_package;
	     	       PROCEDURE = this.constantes.oracle_empleados_procedure04;
	     	   
	     	       log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	     	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "DEP_ID_IN", idDepartamentoParam, oracle.jdbc.OracleTypes.INTEGER );
		           log.info( "PARAMETROS [INPUT]: " + "DEP_ID_IN: [" + idDepartamentoParam + "]" );
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
		           objJdbcTemplate = objJdbcCall.getJdbcTemplate();
		           objJdbcTemplate.setQueryTimeout( this.constantes.oracle_timeout );
		            
		           //PARAMETROS [IN / OUT]:
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
		           .withoutProcedureColumnMetaDataAccess()
		           .withSchemaName(  OWNER )
	               .withCatalogName( PACKAGE )
	               .withProcedureName( PROCEDURE )   
	               .declareParameters(  
	            	   new SqlParameter(    "DEP_ID_IN",   oracle.jdbc.OracleTypes.INTEGER ),
	            		   
		               new SqlOutParameter( "ERR_OUT",     oracle.jdbc.OracleTypes.VARCHAR ),
		               new SqlOutParameter( "MESSAGE_OUT", oracle.jdbc.OracleTypes.VARCHAR ),			                       
		               new SqlOutParameter( "CURSOR_EMPLEADOS",   
		                                    oracle.jdbc.OracleTypes.CURSOR, 
		                                    null, 
		                                    new EmpleadoSqlReturnType() ) 
	               )
	               .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String         vCodigoErr_OUT  = (String)objParametrosOUT.get( "ERR_OUT" );
		     	   String         vMensajeErr_OUT = (String)objParametrosOUT.get( "MESSAGE_OUT" ); 
				   List<Empleado> lista_OUT       = (List<Empleado>)objParametrosOUT.get( "CURSOR_EMPLEADOS" );
		     	   
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" ); 
				   log.info( "CURSOR_EMPLEADOS [TAMANIO]: [" + lista_OUT.size() + "]" ); 
		           
		     	   //RETURN:
		     	   objDBResponseEmplMsg = new DBResponseEmpMsg();
		     	   objDBResponseEmplMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseEmplMsg.setMensajeOUT( vMensajeErr_OUT );
		     	   	           
		     	   vTamanio = lista_OUT.size();
		     	   
		     	   if( (lista_OUT != null) && 
		     		   (vTamanio > 0) ){	     		   
		     		   objDBResponseEmplMsg.setObjEmpleado( lista_OUT.get( 0 ) ); //1 Objeto  
		     		   objDBResponseEmplMsg.setListaEmpleados( lista_OUT );   
		     	   }
		     	   else{
		     		    objDBResponseEmplMsg.setObjEmpleado( null ); 
		     		    objDBResponseEmplMsg.setListaEmpleados( null );   
		     	   }
			 } 
		     catch( NestedRuntimeException e ){
		            log.error( "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e );                
		            throw new Exception( e );
		     }   
		     catch( Exception e ){
		  	        log.error( "ERROR: [Exception] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }
		     catch( Throwable e ){
		  	        log.error( "ERROR: [Throwable] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }  
			 finally{
				     log.info( "Tiempo TOTAL Proceso: [" + (vTiempoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [consultarEmpleados - DAO]" );
			 }
		     
		     return objDBResponseEmplMsg;  
	     }
		
	   /**
	    * eliminarEmpleado
	    * @param     idEmpleadoParam
	    * @returm    DBResponseEmpMsg
	    * @exception Exception
	    **/
		@Override
		@Transactional( propagation = Propagation.REQUIRED )
		public DBResponseEmpMsg eliminarEmpleado( int idEmpleadoParam ) throws Exception{
			   log.info( "[INICIO] - METODO: [eliminarEmpleado - DAO]: idEmpleadoParam: [" + idEmpleadoParam + "]" );
				 
			   DBResponseEmpMsg   objDBResponseEmplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiempoInicio        = System.currentTimeMillis();
	  	 
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_empleados_package;
	  	           PROCEDURE = this.constantes.oracle_empleados_procedure03;  
	  	   
	  	           log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	  	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "EMP_ID_IN", idEmpleadoParam, oracle.jdbc.OracleTypes.INTEGER ); 
		           log.info( "PARAMETROS [INPUT]: " + "EMP_ID_IN: [" + idEmpleadoParam + "]" );
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
	
		           //PARAMETROS [IN / OUT]: 
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
				           .withoutProcedureColumnMetaDataAccess()
				           .withSchemaName(  OWNER )
			               .withCatalogName( PACKAGE )
			               .withProcedureName( PROCEDURE ) 
			               .declareParameters(  
				            	   new SqlParameter(    "EMP_ID_IN",   oracle.jdbc.OracleTypes.INTEGER ),
				            		   
					               new SqlOutParameter( "ERR_OUT",     oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "MESSAGE_OUT", oracle.jdbc.OracleTypes.VARCHAR ) 
				           )  
			       .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
		     	   
		     	   //PARAMETROS [OUT]: 
		     	   String  vCodigoErr_OUT  = (String)objParametrosOUT.get(  "ERR_OUT"     ); 
		     	   String  vMensajeErr_OUT = (String)objParametrosOUT.get(  "MESSAGE_OUT" );  
		     	    
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" );  
				   
		     	   //RETURN:
		     	   objDBResponseEmplMsg = new DBResponseEmpMsg();
		     	   objDBResponseEmplMsg.setCodigoOUT(     vCodigoErr_OUT  );
		     	   objDBResponseEmplMsg.setMensajeOUT(    vMensajeErr_OUT );   
			 } 
		     catch( NestedRuntimeException e ){
		            log.error( "ERROR: [NestedRuntimeException] - [" + e.getMessage() + "] ", e );                
		            throw new Exception( e );
		     }   
		     catch( Exception e ){
		  	        log.error( "ERROR: [Exception] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }
		     catch( Throwable e ){
		  	        log.error( "ERROR: [Throwable] - [" + e.getMessage() + "] ", e );     	          	       
		  	        throw new Exception( e );  
		     }  
			 finally{
				     log.info( "Tiempo TOTAL Proceso: [" + (vTiempoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [eliminarEmpleado - DAO]" );
			 }
	        
	         return objDBResponseEmplMsg;
		} 
		
  }

