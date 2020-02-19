

IMPORTANTE:
----------
El 'ARCHIVO DE CONFIGURACION' para el MICROSERVICIO: 'utl-capadb', se manejara en la ruta del 'GITHUB': '/utl-capadb.properties' 

SWAGGER:
--------
CREAR EL 'CONTRADO/API' ONLINE USANDO:
http://editor.swagger.io/

UNA VEZ DESPLEGADO EL 'MICROSERVICIO' ACCEDER A:  
http://localhost:8093/swagger-ui.html


*********************************************************************************
********************************** [EMPLEADOS] **********************************
*********************************************************************************
Los LINK [GET] son:
------------------
1. consultarEmpleadosAll: [http://localhost:8093/utlcapadb/get/empleados]
   http://localhost:8093/utlcapadb/get/empleados

2. consultarEmpleadosPorId: [http://localhost:8093/utlcapadb/get/empleados/{id}]  
   http://localhost:8093/utlcapadb/get/empleados/1
  
3. consultarEmpleadosPorDepartamento: [http://localhost:8093/utlcapadb/get/empleados-departamento/{idDep}]  
   http://localhost:8093/utlcapadb/get/empleados-departamento/1
 
 
Los LINK [POST] son:
------------------
4. agregarEmpleado: [http://localhost:8093/utlcapadb/post/empleados]
   http://localhost:8093/utlcapadb/post/empleados


Los LINK [DELETE] son:
---------------------
5. eliminarEmpleado: [http://localhost:8093/utlcapadb/delete/empleados/{id}]  
   http://localhost:8093/utlcapadb/delete/empleados/1 
   

*********************************************************************************
******************************** [DEPARTAMENTOS] ********************************
********************************************************************************* 
Los LINK [GET] son:
------------------
1. consultarDepartamentosAll: [http://localhost:8093/utlcapadb/get/departamentos]
   http://localhost:8093/utlcapadb/get/departamentos

2. consultarDepartamentosPorId: [http://localhost:8093/utlcapadb/get/departamentos/{id}]  
   http://localhost:8093/utlcapadb/get/departamentos/1
  
3. consultarDepartamentosPorEmpleados: [http://localhost:8093/utlcapadb/get/departamentos-organizacion/{idOrg}]  
   http://localhost:8093/utlcapadb/get/departamentos-organizacion/1
  
 
Los LINK [POST] son:
------------------
4. agregarDepartamento: [http://localhost:8093/utlcapadb/post/departamentos]
   http://localhost:8093/utlcapadb/post/departamentos


Los LINK [DELETE] son:
---------------------
5. eliminarEmpleado: [http://localhost:8093/utlcapadb/delete/departamentos/{id}]  
   http://localhost:8093/utlcapadb/delete/departamentos/1 
     
   
**********************************************************************************
******************************** [ORGANIZACIONES] ********************************
********************************************************************************** 
Los LINK [GET] son:
------------------
1. consultarOrganizacionesAll: [http://localhost:8093/utlcapadb/get/organizaciones]
   http://localhost:8093/utlcapadb/get/organizaciones

2. consultarOrganizacionesPorId: [http://localhost:8093/utlcapadb/get/organizaciones/{id}]  
   http://localhost:8093/utlcapadb/get/organizaciones/1
 
 
Los LINK [POST] son:
------------------
3. agregarOrganizacion: [http://localhost:8093/utlcapadb/post/organizaciones]
   http://localhost:8093/utlcapadb/post/organizaciones


Los LINK [DELETE] son:
---------------------
4. eliminarOrganizacion: [http://localhost:8093/utlcapadb/delete/organizaciones/{id}]  
   http://localhost:8093/utlcapadb/delete/organizaciones/1 

 

DETALLE:
-------
- Para DETALLES del MICROSERVICIO, apoyarse en ACTUATOR ingresando a: 'http://localhost:8093/actuator'
 


'REQUEST' para los REGISTROS de tipo: 'POST':
--------------------------------------------

*********************************************************************************
********************************** [EMPLEADOS] **********************************
*********************************************************************************
1. REGISTRAR 'EMPLEADOS': 
	{   
	  "nombre": "PAOLO GUERRERO", 
	  "edad":   35, 
	  "rol":    "CONSULTOR",
	  
	  "idDep":  "1" 
	}

*********************************************************************************
******************************** [DEPARTAMENTOS] ********************************
********************************************************************************* 
2. REGISTRAR 'DEPARTAMENTOS':
	{    
	  "nombre": "RRHH",  
	  
      "idOrg":  "1" 
	}

**********************************************************************************
******************************** [ORGANIZACIONES] ********************************
**********************************************************************************
3. REGISTRAR 'ORGANIZACIONES':
	{    
	  "nombre":    "AMAZON", 
	  "direccion": "Calle Chincheros 121, La Molina"   
	}

