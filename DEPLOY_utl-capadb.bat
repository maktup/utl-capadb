@ECHO OFF

REM *********************************************************************
REM * - DESCRIPCION: Shell para el despliegue de MICROSERVICES JAVA     *
REM * - EJECUCION:   SHELL    								            *
REM * - AUTOR:       Cesar Ricardo Guerra Arnaiz   		  	 	        *
REM * - FECHA:       21/01/2020				      				        *
REM * - VERSION:     1.0									            *
REM *********************************************************************

SET vFECHA=%DATE%
SET vHORA=%TIME%

SET vFECHA_ACTUAL=%vFECHA% [%vHORA%]
SET vTRANSACCION=%vFECHA_ACTUAL% - [INFO]: 

SET vRUTA_FILE_SYSTEM=D:\WORKSPACE_Eclipse\Capacitaciones\ArquitecturaMicroserviciosEnLaPractica\ArquitecturaMicroserviciosClasica\capacitacionArquitecturaMicroServicios\
SET vRUTA_JDK=C:\JAVA\JDK\jdk1.8.0_91\bin\
SET vNOMBRE_MICROSERVICE=utl-capadb

ECHO. 
@ECHO %vTRANSACCION% -------------------- [INICIO] - [DESPLIEGUE 'MICROSERVICES'] --------------------
ECHO.


@ECHO %vTRANSACCION% -- DESPLIEGUE [%vNOMBRE_MICROSERVICE%]:
%vRUTA_JDK%java -jar %vRUTA_FILE_SYSTEM%%vNOMBRE_MICROSERVICE%\target\%vNOMBRE_MICROSERVICE%-1.0.0.jar


ECHO.
@ECHO %vTRANSACCION% --------------------------------------- [FIN] ---------------------------------------

ECHO Pulsar para cerrar ....
PAUSE

EXIT

