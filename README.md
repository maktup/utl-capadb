

IMPORTANTE:
----------
* CONSIDERAR que los DNS, deben estar registrados en el archivo HOST del S.O (Las IPs manejadas deben de ser FIJAS). 
* La 'ARCHIVO DE CONFIGURACION' para el MICROSERVICIO: 'utl-capadb', se manejara por medio de los SCRIPTs:

  - 1_utl-capadb-service_[ConfigMap-Secret].yml
  - 2_utl-capadb-service_[Deployment-Service].yml
  - 3_utl-capadb-service_[Endpoits-Service].yml
  
* Dentro del SCRIPT: 'DOCKERFILE' se estan manejando tambien 'VARIABLES DE ENTORNO' para algunos requerimientos en el MICROSERVICIO. 


SWAGGER:
--------
CREAR EL 'CONTRADO/API' ONLINE USANDO:
http://editor.swagger.io/

UNA VEZ DESPLEGADO EL 'MICROSERVICIO' ACCEDER A:  
http://capacitacion.microservicios.utlcapadb/swagger-ui.html
 
 

*********************************************************************************
********************************** [EMPLEADOS] **********************************
*********************************************************************************
Los LINK [GET] son:
------------------
 - consultarEmpleadosAll: 
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/empleados

 - consultarEmpleadosPorId:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/empleados/1
                                                                  
 - consultarEmpleadosPorDepartamento:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/empleados-departamento/1
 
 
Los LINK [POST] son:
------------------
 - agregarEmpleado:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/post/empleados
 
	{   
	   "nombre": "PAOLO GUERRERO", 
	   "edad":   35, 
	   "rol":    "CONSULTOR",
		  
	   "idDep":  "1" 
	}


Los LINK [DELETE] son:
---------------------
 - eliminarEmpleado:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/delete/empleados/1
   


*********************************************************************************
******************************** [DEPARTAMENTOS] ********************************
********************************************************************************* 
Los LINK [GET] son:
------------------
 - consultardepartamentosAll: 
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/departamentos

 - consultardepartamentosPorId:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/departamentos/1
                                                                  
 - consultardepartamentosPorDepartamento:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/departamentos-organizacion/1
  
 
Los LINK [POST] son:
------------------
 - agregarDepartamento:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/post/departamentos
 
   {    
	  "nombre": "RRHH",  
		  
	  "idOrg":  "1" 
   }


Los LINK [DELETE] son:
---------------------
 - eliminarDepartamento:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/delete/departamentos/1
     
   
   
**********************************************************************************
******************************** [ORGANIZACIONES] ********************************
********************************************************************************** 
Los LINK [GET] son:
------------------
 - consultarorganizacionesAll: 
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/organizaciones

 - consultarorganizacionesPorId:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/get/organizaciones/1
 
 
Los LINK [POST] son:
------------------
 - agregarOrganizacion:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/post/organizaciones
 
   {    
	 "nombre":    "AMAZON", 
	 "direccion": "Calle Chincheros 121, La Molina"   
   }
	

Los LINK [DELETE] son:
---------------------
 - eliminarOrganizacion:   
   http://capacitacion.microservicios.utlcapadb/utlcapadb/delete/organizaciones/1

 

DETALLE:
-------
* Para INFORMACIÓN interna del MICROSERVICIO, apoyarse en ACTUATOR ingresando a: 'http://capacitacion.microservicios.utlcapadb/actuator'
* Para acceder a 'PHOMETHEUS' acceder por medio de ACTUATOR asi: 'http://capacitacion.microservicios.employee/actuator/prometheus'


 
PASOS 'AUTOSCALAMIENTO': [Tiene que tener un LIMMIT en el CPU para el POD, sino NO funciona]:
-------------------------------------------------------------------------------------------- 
 
Este sera aplicado solo al 'MICROSERVICIOS' UTILITARIO.
 
    
 0. DEBE ESTAR ACTIVO EL 'ADD-ON':
    minikube addons list     
    minikube addons enable metrics-server   
     
 1. CREAR & ASIGNAR 'AUTOSCALING':
    kubectl autoscale deployment utl-capadb-service --min=1 --max=3 --cpu-percent=50
 
 2. OBTENER EL 'SCRIPT .YML' GENERADO:
    kubectl get hpa -o yaml utl-capadb-service
 
 3. OBTENER DETALLES 'AUTOSCALING':
    kubectl get pods,hpa -o wide
   
 4. OBTENER DETALLES DEL 'NIVEL DE CPU' EJECUTANDOSE EN EL POD.
    kubectl top pod   
       
 5. ESTRESAR 'MICROSERVICIO':
    while true; do curl -s http://capacitacion.microservicios.utlcapadb/utlcapadb/get/empleados ; done 
 
   