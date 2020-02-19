package pe.com.capacitacion;

import javax.sql.DataSource; 
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan; 
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
/**
 * MainApp
 * @author cguerra
 **/
 @SpringBootApplication
 @EnableDiscoveryClient     //IMPORTANTE: 'EUREKA CLIENT' 
 @EnableHystrix             //IMPORTANTE: 'HYSTRIX' 
 @EnableFeignClients        //IMPORTANTE: 'FEIGN CLIENT'
 @EnableSwagger2            //IMPORTANTE: 'SWAGGER'
 @ComponentScan( basePackages = { "pe.com.capacitacion" } )
 public class MainApp{
	
	    public static final String PAQUETE_SCAN = "pe.com.capacitacion.controller";
	 
	   /**
	    * main 
	    * @param argumentos
	    **/
	    public static void main( String[] argumentos ){
		 	   SpringApplication.run( MainApp.class, argumentos );
	    }
    
	    
	    //---------------------------------------- [ORACLE] -----------------------------------------// 
	   /**
	    * getDataSource	
	    * @return DataSource
	    **/
		@Bean
		@Qualifier( "datasource_oracle" )  
		@ConfigurationProperties( prefix="spring.datasource" ) 
		public DataSource getDataSource(){ 
			   DataSource objDS = DataSourceBuilder
					              .create()
					              .build();  
			
			   return objDS; 
		}
	    //---------------------------------------- [ORACLE] ----------------------------------------// 
		
		
	    //---------------------------------------- [SWAGGER] ----------------------------------------// 
		public ApiInfo apiInfo(){
	           return new ApiInfoBuilder()
	                  .title( "CONTRATO/API PARA LA GESTION 'UTILITARIA' DE LA BD: 'CAPADB'" )
	                  .description( "CONTRATO/API DEL MICROSERVICIO: utl-capadb" )
	                  .license( "Apache 2.0" )
	                  .licenseUrl( "http://www.apache.org/licenses/LICENSE-2.0.html" )
	                  .termsOfServiceUrl( "" )
	                  .version( "1.0" )
	                  .contact( new Contact( "", "", "cesarricardo_guerra19@hotmail.com" ) )
	                  .build();
	    }

	    @Bean
	    public Docket customImplementation(){
	        return new Docket( DocumentationType.SWAGGER_2 )
	                .select()
	                .apis( RequestHandlerSelectors.basePackage( PAQUETE_SCAN ) )
	                .paths( PathSelectors.any() ) 
                    .build() 
                    .apiInfo( this.apiInfo() );
	    }
	    //---------------------------------------- [SWAGGER] ----------------------------------------// 
		
 } 

 