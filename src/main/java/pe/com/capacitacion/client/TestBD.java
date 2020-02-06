package pe.com.capacitacion.client;

import java.sql.DriverManager;
import java.sql.Connection; 
import java.sql.SQLException; 

/**
 * TestBD
 */
 public class TestBD{
 
	private static final String	DB_DRIVER	  = "oracle.jdbc.driver.OracleDriver";
	private static final String	DB_CONNECTION = "jdbc:oracle:thin:@192.168.153.1:1521:XE";
	private static final String	DB_USER		  = "cguerra";
	private static final String	DB_PASSWORD	  = "12345678";
	
   /**
    * main
    * @param argumentos
    * @throws Throwable 
    */
	public static void main( String[] argumentos ) throws Throwable{
		try{
			registrarDatosBD();
		}
		catch( Exception e ){
			   e.printStackTrace();
		}
	}
  
   /**
    * registrarDatosBD	
    * @throws Throwable 
    * @throws SQLException
    */
	private static void registrarDatosBD() throws Throwable{ 
		
		Connection        objConexion = null; 
 
		try{
			objConexion = getConexionBD();
			System.out.println( "CONEXION: [" + objConexion + "]" ); 
		}
		catch( Exception e ){
			   System.out.println( e.getMessage() );
		}
		finally{ 
		}
	}
  
   /**
    * getConexionBD	
    * @return Connection
    */
	private static Connection getConexionBD(){
		
		Connection dbConnection = null;
		
		try{
			Class.forName( DB_DRIVER );
		}
		catch( ClassNotFoundException e ){
			   System.out.println( e.getMessage() );
		}
		try{
			dbConnection = DriverManager.getConnection( DB_CONNECTION, DB_USER, DB_PASSWORD );
			return dbConnection;
		}
		catch( SQLException e ){
			   e.printStackTrace();
		}
		
		return dbConnection;
	}
	
}

