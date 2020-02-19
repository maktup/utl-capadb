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
import pe.com.capacitacion.bean.Departamento;
import pe.com.capacitacion.dao.mapper.DepartamentoSqlReturnType;
import pe.com.capacitacion.dto.DBResponseDepMsg; 
import pe.com.capacitacion.util.Constantes;
 
/**
 * @author cguerra.
 * @clase: DepartamentoDaoImp.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/
 @Slf4j      //Autogenerar LOG4J.
 @Repository
 public class DepartamentoDaoImp implements DepartamentoDao{
 
	    @Autowired
	    @Qualifier( "datasource_oracle" ) 
		private DataSource datasource;  
	  
	    @Autowired
	    private Constantes constantes;   
 
		
	   /**
	    * agregarDepartamento
	    * @param     DepartamentoParam
	    * @returm    DBResponseDepMsg
	    * @exception Exception
	    **/
		@Override
		@Transactional( propagation = Propagation.REQUIRED )
		public DBResponseDepMsg agregarDepartamento( Departamento DepartamentoParam ) throws Exception{  
			   log.info( "[INICIO] - METODO: [agregarDepartamento - DAO]: DepartamentoParam: [" + DepartamentoParam + "]" );
	 
			   DBResponseDepMsg   objDBResponseDeplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiDepoInicio        = System.currentTimeMillis();
	     	 
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_departamentos_package;
	     	       PROCEDURE = this.constantes.oracle_departamentos_procedure01;  
	     	   
	     	       log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	     	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "DEP_NOMBRE_IN", DepartamentoParam.getNombre(), oracle.jdbc.OracleTypes.VARCHAR )  
		           .addValue( "ORG_ID_IN",     DepartamentoParam.getIdOrg(),  oracle.jdbc.OracleTypes.INTEGER );  //REFERENCIA  
		           log.info( "PARAMETROS [INPUT]: " + "DEP_NOMBRE_IN: [" + DepartamentoParam.getNombre() + "]" ); 
		           log.info( "PARAMETROS [INPUT]: " + "ORG_ID_IN: ["     + DepartamentoParam.getIdOrg()  + "]" ); 
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
 
		           //PARAMETROS [IN / OUT]: 
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
				           .withoutProcedureColumnMetaDataAccess()
				           .withSchemaName(  OWNER )
			               .withCatalogName( PACKAGE )
			               .withProcedureName( PROCEDURE ) 
			               .declareParameters(  
				            	   new SqlParameter(    "DEP_NOMBRE_IN", oracle.jdbc.OracleTypes.VARCHAR ), 
				            	   new SqlParameter(    "ORG_ID_IN",     oracle.jdbc.OracleTypes.INTEGER ),  //REFERENCIA
				            	   
					               new SqlOutParameter( "ERR_OUT",       oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "MESSAGE_OUT",   oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "DEP_ID_OUT",    oracle.jdbc.OracleTypes.INTEGER ) 
				           )  
			       .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String  vCodigoErr_OUT  = (String)objParametrosOUT.get(  "ERR_OUT"     ); 
		     	   String  vMensajeErr_OUT = (String)objParametrosOUT.get(  "MESSAGE_OUT" );  
		     	   Integer vIdRegistro_OUT = (Integer)objParametrosOUT.get( "DEP_ID_OUT"  ); 
		     	    
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" );    
				   log.info( "DEP_ID_OUT: ["  + vIdRegistro_OUT + "]" ); 
				   
		     	   //RETURN:
		     	   objDBResponseDeplMsg = new DBResponseDepMsg();
		     	   objDBResponseDeplMsg.setCodigoOUT(     vCodigoErr_OUT  );
		     	   objDBResponseDeplMsg.setMensajeOUT(    vMensajeErr_OUT );
		     	   objDBResponseDeplMsg.setIdRegistroOUT( vIdRegistro_OUT );  
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
				     log.info( "TiDepo TOTAL Proceso: [" + (vTiDepoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [agregarDepartamento - DAO]" );
			 }
	           
	         return objDBResponseDeplMsg;
		}
	
	   /**
	    * consultarDepartamentos
	    * @param     idDepartamentoParam
	    * @returm    DBResponseDepMsg	
	    * @exception Exception
	    **/
		@Override
		@SuppressWarnings( "unchecked" )
		@Transactional( propagation = Propagation.NOT_SUPPORTED )
		public DBResponseDepMsg consultarDepartamentos( int idDepartamentoParam ) throws Exception{ 
			   log.info( "[INICIO] - METODO: [consultarDepartamentos - DAO]: idDepartamento: [" + idDepartamentoParam + "]" );
			 
			   JdbcTemplate       objJdbcTemplate      = null;
			   DBResponseDepMsg   objDBResponseDeplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiDepoInicio        = System.currentTimeMillis();
	     	   int                vTamanio             = 0;
			   
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_departamentos_package;
	     	       PROCEDURE = this.constantes.oracle_departamentos_procedure02; 
	     	   
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
		               new SqlOutParameter( "CURSOR_DEPARTAMENTOS",   
		                                    oracle.jdbc.OracleTypes.CURSOR, 
		                                    null, 
		                                    new DepartamentoSqlReturnType() ) 
	               )
	               .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String             vCodigoErr_OUT  = (String)objParametrosOUT.get( "ERR_OUT" );
		     	   String             vMensajeErr_OUT = (String)objParametrosOUT.get( "MESSAGE_OUT" ); 
				   List<Departamento> lista_OUT       = (List<Departamento>)objParametrosOUT.get( "CURSOR_DEPARTAMENTOS" );
		     	   
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" ); 
				   log.info( "CURSOR_DEPARTAMENTOS [TAMANIO]: [" + lista_OUT.size() + "]" ); 
		           
		     	   //RETURN:
		     	   objDBResponseDeplMsg = new DBResponseDepMsg();
		     	   objDBResponseDeplMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseDeplMsg.setMensajeOUT( vMensajeErr_OUT );
		     	   	           
		     	   vTamanio = lista_OUT.size();
		     	   
		     	   if( (lista_OUT != null) && 
		     		   (vTamanio > 0) ){	     		   
		     		   objDBResponseDeplMsg.setObjDepartamento( lista_OUT.get( 0 ) ); //1 Objeto  
		     		   objDBResponseDeplMsg.setListaDepartamentos( lista_OUT );   
		     	   }
		     	   else{
		     		    objDBResponseDeplMsg.setObjDepartamento( null ); 
		     		    objDBResponseDeplMsg.setListaDepartamentos( null );   
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
				     log.info( "TiDepo TOTAL Proceso: [" + (vTiDepoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [consultarDepartamentos - DAO]" );
			 }
		     
		     return objDBResponseDeplMsg;  
	     }

	   /**
	    * consultarDepartamentos_x_organizacion
	    * @param     idOrganizacionParam
	    * @returm    DBResponseDepMsg	
	    * @exception Exception
	    **/
		@Override
		@SuppressWarnings( "unchecked" )
		@Transactional( propagation = Propagation.NOT_SUPPORTED )
		public DBResponseDepMsg consultarDepartamentos_x_organizacion( int idOrganizacionParam ) throws Exception{ 
			   log.info( "[INICIO] - METODO: [consultarDepartamentos_x_organizacion - DAO]: idOrganizacionParam: [" + idOrganizacionParam + "]" );
			 
			   JdbcTemplate       objJdbcTemplate      = null;
			   DBResponseDepMsg   objDBResponseDeplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiDepoInicio        = System.currentTimeMillis();
	     	   int                vTamanio             = 0;
			   
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_departamentos_package;
	     	       PROCEDURE = this.constantes.oracle_departamentos_procedure04; 
	     	   
	     	       log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	     	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "ORG_ID_IN", idOrganizacionParam, oracle.jdbc.OracleTypes.INTEGER );
		           log.info( "PARAMETROS [INPUT]: " + "ORG_ID_IN: [" + idOrganizacionParam + "]" );
		           
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
	            	   new SqlParameter(    "ORG_ID_IN",   oracle.jdbc.OracleTypes.INTEGER ),
	            		   
		               new SqlOutParameter( "ERR_OUT",     oracle.jdbc.OracleTypes.VARCHAR ),
		               new SqlOutParameter( "MESSAGE_OUT", oracle.jdbc.OracleTypes.VARCHAR ),			                       
		               new SqlOutParameter( "CURSOR_DEPARTAMENTOS",   
		                                    oracle.jdbc.OracleTypes.CURSOR, 
		                                    null, 
		                                    new DepartamentoSqlReturnType() ) 
	               )
	               .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String             vCodigoErr_OUT  = (String)objParametrosOUT.get( "ERR_OUT" );
		     	   String             vMensajeErr_OUT = (String)objParametrosOUT.get( "MESSAGE_OUT" ); 
				   List<Departamento> lista_OUT       = (List<Departamento>)objParametrosOUT.get( "CURSOR_DEPARTAMENTOS" );
		     	   
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" ); 
				   log.info( "CURSOR_DEPARTAMENTOS [TAMANIO]: [" + lista_OUT.size() + "]" ); 
		           
		     	   //RETURN:
		     	   objDBResponseDeplMsg = new DBResponseDepMsg();
		     	   objDBResponseDeplMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseDeplMsg.setMensajeOUT( vMensajeErr_OUT );
		     	   	           
		     	   vTamanio = lista_OUT.size();
		     	   
		     	   if( (lista_OUT != null) && 
		     		   (vTamanio > 0) ){	     		   
		     		   objDBResponseDeplMsg.setObjDepartamento( lista_OUT.get( 0 ) ); //1 Objeto  
		     		   objDBResponseDeplMsg.setListaDepartamentos( lista_OUT );   
		     	   }
		     	   else{
		     		    objDBResponseDeplMsg.setObjDepartamento( null ); 
		     		    objDBResponseDeplMsg.setListaDepartamentos( null );   
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
				     log.info( "TiDepo TOTAL Proceso: [" + (vTiDepoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [consultarDepartamentos - DAO]" );
			 }
		     
		     return objDBResponseDeplMsg;  
	     }
		
	   /**
	    * eliminarDepartamento
	    * @param     idDepartamentoParam
	    * @returm    DBResponseDepMsg
	    * @exception Exception
	    **/
		@Override
		@Transactional( propagation = Propagation.REQUIRED )
		public DBResponseDepMsg eliminarDepartamento( int idDepartamentoParam ) throws Exception{
			   log.info( "[INICIO] - METODO: [eliminarDepartamento - DAO]: idDepartamentoParam: [" + idDepartamentoParam + "]" );
				 
			   DBResponseDepMsg   objDBResponseDeplMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiDepoInicio        = System.currentTimeMillis();
	  	 
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_departamentos_package;
	  	           PROCEDURE = this.constantes.oracle_departamentos_procedure03;  
	  	   
	  	           log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	  	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "DEP_ID_IN", idDepartamentoParam, oracle.jdbc.OracleTypes.INTEGER ); 
		           log.info( "PARAMETROS [INPUT]: " + "DEP_ID_IN: [" + idDepartamentoParam + "]" );
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
	
		           //PARAMETROS [IN / OUT]: 
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
				           .withoutProcedureColumnMetaDataAccess()
				           .withSchemaName(  OWNER )
			               .withCatalogName( PACKAGE )
			               .withProcedureName( PROCEDURE ) 
			               .declareParameters(  
				            	   new SqlParameter(    "DEP_ID_IN",   oracle.jdbc.OracleTypes.INTEGER ),
				            		   
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
		     	   objDBResponseDeplMsg = new DBResponseDepMsg();
		     	   objDBResponseDeplMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseDeplMsg.setMensajeOUT( vMensajeErr_OUT );   
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
				     log.info( "TiDepo TOTAL Proceso: [" + (vTiDepoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [eliminarDepartamento - DAO]" );
			 }
	        
	         return objDBResponseDeplMsg;
		} 
		
  }

