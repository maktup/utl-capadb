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
import pe.com.capacitacion.bean.Organizacion;
import pe.com.capacitacion.dao.mapper.OrganizacionSqlReturnType; 
import pe.com.capacitacion.dto.DBResponseOrgMsg; 
import pe.com.capacitacion.util.Constantes;
 
/**
 * @author cguerra.
 * @clase: OrganizacionDaoImp.java
 * @descripcion descripcion de la clase.
 * @fecha_de_creacion: dd-mm-yyyy.
 * @fecha_de_ultima_actualizacion: dd-mm-yyyy.
 * @version 1.0
 **/
 @Slf4j      //Autogenerar LOG4J.
 @Repository
 public class OrganizacionDaoImp implements OrganizacionDao{
 
	    @Autowired
	    @Qualifier( "datasource_oracle" ) 
		private DataSource datasource;  
	  
	    @Autowired
	    private Constantes constantes;   
 
		
	   /**
	    * agregarOrganizacion
	    * @param     OrganizacionParam
	    * @returm    DBResponseOrgMsg
	    * @exception Exception
	    **/
		@Override
		@Transactional( propagation = Propagation.REQUIRED )
		public DBResponseOrgMsg agregarOrganizacion( Organizacion OrganizacionParam ) throws Exception{  
			   log.info( "[INICIO] - METODO: [agregarOrganizacion - DAO]: OrganizacionParam: [" + OrganizacionParam + "]" );
	 
			   DBResponseOrgMsg   objDBResponseOrglMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiOrgoInicio        = System.currentTimeMillis();
	     	 
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_organizaciones_package;
	     	       PROCEDURE = this.constantes.oracle_organizaciones_procedure01;  
	     	   
	     	       log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	     	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "ORG_NOMBRE_IN",    OrganizacionParam.getNombre(),    oracle.jdbc.OracleTypes.VARCHAR )
		           .addValue( "ORG_DIRECCION_IN", OrganizacionParam.getDireccion(), oracle.jdbc.OracleTypes.VARCHAR ); 
		           log.info( "PARAMETROS [INPUT]: " + "ORG_NOMBRE_IN: ["    + OrganizacionParam.getNombre()    + "]" );
		           log.info( "PARAMETROS [INPUT]: " + "ORG_DIRECCION_IN: [" + OrganizacionParam.getDireccion() + "]" ); 
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
 
		           //PARAMETROS [IN / OUT]: 
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
				           .withoutProcedureColumnMetaDataAccess()
				           .withSchemaName(  OWNER )
			               .withCatalogName( PACKAGE )
			               .withProcedureName( PROCEDURE ) 
			               .declareParameters(  
				            	   new SqlParameter(    "ORG_NOMBRE_IN",    oracle.jdbc.OracleTypes.VARCHAR ),
				            	   new SqlParameter(    "ORG_DIRECCION_IN", oracle.jdbc.OracleTypes.VARCHAR ),
				            		   
					               new SqlOutParameter( "ERR_OUT",          oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "MESSAGE_OUT",      oracle.jdbc.OracleTypes.VARCHAR ),
					               new SqlOutParameter( "ORG_ID_OUT",       oracle.jdbc.OracleTypes.INTEGER ) 
				           )  
			       .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String  vCodigoErr_OUT  = (String)objParametrosOUT.get(  "ERR_OUT"     ); 
		     	   String  vMensajeErr_OUT = (String)objParametrosOUT.get(  "MESSAGE_OUT" );  
		     	   Integer vIdRegistro_OUT = (Integer)objParametrosOUT.get( "ORG_ID_OUT"  ); 
		     	    
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" );    
				   log.info( "ORG_ID_OUT: ["  + vIdRegistro_OUT + "]" ); 
				   
		     	   //RETURN:
		     	   objDBResponseOrglMsg = new DBResponseOrgMsg();
		     	   objDBResponseOrglMsg.setCodigoOUT(     vCodigoErr_OUT  );
		     	   objDBResponseOrglMsg.setMensajeOUT(    vMensajeErr_OUT );
		     	   objDBResponseOrglMsg.setIdRegistroOUT( vIdRegistro_OUT );  
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
				     log.info( "TiOrgo TOTAL Proceso: [" + (vTiOrgoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [agregarOrganizacion - DAO]" );
			 }
	           
	         return objDBResponseOrglMsg;
		}
	
	   /**
	    * consultarOrganizaciones
	    * @param     idOrganizacionParam
	    * @returm    DBResponseOrgMsg	
	    * @exception Exception
	    **/
		@Override
		@SuppressWarnings( "unchecked" )
		@Transactional( propagation = Propagation.NOT_SUPPORTED )
		public DBResponseOrgMsg consultarOrganizaciones( int idOrganizacionParam ) throws Exception{ 
			   log.info( "[INICIO] - METODO: [consultarOrganizaciones - DAO]: idOrganizacion: [" + idOrganizacionParam + "]" );
			 
			   JdbcTemplate       objJdbcTemplate      = null;
			   DBResponseOrgMsg   objDBResponseOrglMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiOrgoInicio        = System.currentTimeMillis();
	     	   int                vTamanio             = 0;
			   
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_organizaciones_package;
	     	       PROCEDURE = this.constantes.oracle_organizaciones_procedure02; 
	     	   
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
		               new SqlOutParameter( "CURSOR_ORGANIZACIONES",   
		                                    oracle.jdbc.OracleTypes.CURSOR, 
		                                    null, 
		                                    new OrganizacionSqlReturnType() ) 
	               )
	               .execute( objParametrosIN );  
		     	   log.info( "PARAMETROS [OUPUT]: [" + objParametrosOUT + "]" );
	  	     	   
		     	   //PARAMETROS [OUT]: 
		     	   String         vCodigoErr_OUT  = (String)objParametrosOUT.get( "ERR_OUT" );
		     	   String         vMensajeErr_OUT = (String)objParametrosOUT.get( "MESSAGE_OUT" ); 
				   List<Organizacion> lista_OUT       = (List<Organizacion>)objParametrosOUT.get( "CURSOR_ORGANIZACIONES" );
		     	   
				   log.info( "ERR_OUT: ["     + vCodigoErr_OUT  + "]" ); 
				   log.info( "MESSAGE_OUT: [" + vMensajeErr_OUT + "]" ); 
				   log.info( "CURSOR_ORGANIZACIONES [TAMANIO]: [" + lista_OUT.size() + "]" ); 
		           
		     	   //RETURN:
		     	   objDBResponseOrglMsg = new DBResponseOrgMsg();
		     	   objDBResponseOrglMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseOrglMsg.setMensajeOUT( vMensajeErr_OUT );
		     	   	           
		     	   vTamanio = lista_OUT.size();
		     	   
		     	   if( (lista_OUT != null) && 
		     		   (vTamanio > 0) ){	     		   
		     		   objDBResponseOrglMsg.setObjOrganizacion( lista_OUT.get( 0 ) ); //1 Objeto  
		     		   objDBResponseOrglMsg.setListaOrganizaciones( lista_OUT );   
		     	   }
		     	   else{
		     		    objDBResponseOrglMsg.setObjOrganizacion( null ); 
		     		    objDBResponseOrglMsg.setListaOrganizaciones( null );   
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
				     log.info( "TiOrgo TOTAL Proceso: [" + (vTiOrgoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [consultarOrganizaciones - DAO]" );
			 }
		     
		     return objDBResponseOrglMsg;  
	     }

	   /**
	    * eliminarOrganizacion
	    * @param     idOrganizacionParam
	    * @returm    DBResponseOrgMsg
	    * @exception Exception
	    **/
		@Override
		@Transactional( propagation = Propagation.REQUIRED )
		public DBResponseOrgMsg eliminarOrganizacion( int idOrganizacionParam ) throws Exception{
			   log.info( "[INICIO] - METODO: [eliminarOrganizacion - DAO]: idOrganizacionParam: [" + idOrganizacionParam + "]" );
				 
			   DBResponseOrgMsg   objDBResponseOrglMsg = null;
			   String             OWNER                = null;
			   String             PACKAGE              = null;
			   String             PROCEDURE            = null;
			   long               vTiOrgoInicio        = System.currentTimeMillis();
	  	 
			   try{ 
				   log.info( "DATA SOURCE: [" + this.datasource + "]" ); 			   
				  
				   OWNER     = this.constantes.oracle_owner;
				   PACKAGE   = this.constantes.oracle_organizaciones_package;
	  	           PROCEDURE = this.constantes.oracle_organizaciones_procedure03;  
	  	   
	  	           log.info( "PROCEDURE: [" + OWNER + "." + PACKAGE + "." + PROCEDURE + "]" ); 
	  	       
		     	   //PARAMETROS [IN]:
		           SqlParameterSource objParametrosIN = new MapSqlParameterSource() 
		           .addValue( "Org_ID_IN", idOrganizacionParam, oracle.jdbc.OracleTypes.INTEGER ); 
		           log.info( "PARAMETROS [INPUT]: " + "Org_ID_IN: [" + idOrganizacionParam + "]" );
		           
		           SimpleJdbcCall objJdbcCall = new SimpleJdbcCall( this.datasource );
	
		           //PARAMETROS [IN / OUT]: 
		     	   Map<String, Object> objParametrosOUT = objJdbcCall
				           .withoutProcedureColumnMetaDataAccess()
				           .withSchemaName(  OWNER )
			               .withCatalogName( PACKAGE )
			               .withProcedureName( PROCEDURE ) 
			               .declareParameters(  
				            	   new SqlParameter(    "Org_ID_IN",   oracle.jdbc.OracleTypes.INTEGER ),
				            		   
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
		     	   objDBResponseOrglMsg = new DBResponseOrgMsg();
		     	   objDBResponseOrglMsg.setCodigoOUT(  vCodigoErr_OUT  );
		     	   objDBResponseOrglMsg.setMensajeOUT( vMensajeErr_OUT );   
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
				     log.info( "TiOrgo TOTAL Proceso: [" + (vTiOrgoInicio-System.currentTimeMillis()) + " milisegundos ]" );
				     log.info( "[FIN] - METODO: [eliminarOrganizacion - DAO]" );
			 }
	        
	         return objDBResponseOrglMsg;
		} 
		
  }

