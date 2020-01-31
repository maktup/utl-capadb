package pe.com.capacitacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients; 
import org.springframework.context.annotation.Bean;
 
/**
 * MainApp
 * @author cguerra
 **/
 @SpringBootApplication
 @EnableHystrix             //IMPORTANTE: 'HYSTRIX' 
 @EnableFeignClients        //IMPORTANTE: 'FEIGN CLIENT'
 public class MainApp{
	
		public static void main( String[] argumentos ){
			   SpringApplication.run( MainApp.class, argumentos );  
		}
 
 } 

 