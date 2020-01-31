

IMPORTANTE:
----------
- CONSIDERAR que los DNS, deben estar registrados en el archivo HOST del S.O. 
- El 'ARCHIVO DE CONFIGURACION' para el MICROSERVICIO: 'organization-service', se manejara configurara dentro del SCRIPT: [3_organization-service_[Deployment].yml] 
- Dentro del archivo: 'DOCKERFILE' se estan manejando 'VARIABLES DE ENTORNO' para algunas configuraciones.   

Los LINK [GET] son:

1. consultarOrganizacionesAll: [http://capacitacion.microservicios.organization/organizationservice/get/organizaciones]  
   http://capacitacion.microservicios.organization/organizationservice/get/organizaciones
   
2. consultarOrganizacionesPorId: [http://capacitacion.microservicios.organization/organizationservice/get/organizaciones/{id}]  
   http://capacitacion.microservicios.organization/organizationservice/get/organizaciones/1
                                                                       
3. consultarOrganizacionConDepartamentosPorId: [http://capacitacion.microservicios.organization/organizationservice/get/organizaciones/{id}/departamentos/empleados]  
   http://capacitacion.microservicios.organization/organizationservice/get/organizaciones/1/departamentos/empleados
                                              

DETALLE:
-------
- Para DETALLES del MICROSERVICIO, apoyarse en ACTUATOR ingresando a: 'http://capacitacion.microservicios.organization/actuator'

- Para acceder a 'PHOMETHEUS' acceder por medio de ACTUATOR asi: 'http://capacitacion.microservicios.organization/actuator/prometheus'

