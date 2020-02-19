#!/bin/sh

## *********************************************************************
## * - DESCRIPCION: Shell para el despliegue de MICROSERVICES JAVA     *
## * - EJECUCION:   SHELL    								           *
## * - AUTOR:       Cesar Ricardo Guerra Arnaiz   		  	 	       *
## * - FECHA:       21/01/2020				      				       *
## * - VERSION:     1.0									               *
## *********************************************************************

vFECHA=$(date +%F)
vHORA=$(date +%T)

vFECHA_ACTUAL="$vFECHA [$vHORA]"
vTRANSACCION="$vFECHA_ACTUAL - [INFO]" 

vRUTA_FILE_SYSTEM='D:\WORKSPACE_Eclipse\Capacitaciones\ArquitecturaMicroserviciosEnLaPractica\ArquitecturaMicroserviciosClasica\capacitacionArquitecturaMicroServicios\'
vRUTA_JDK='C:\JAVA\JDK\jdk1.8.0_91\bin\'
vNOMBRE_MICROSERVICE='utl-capadb'
vCOMPLEMENTO='\target\'

ECHO 
ECHO [$vTRANSACCION] -------------------- [INICIO] - [DESPLIEGUE 'MICROSERVICES'] --------------------
ECHO 


ECHO $vTRANSACCION -- DESPLIEGUE [$vNOMBRE_MICROSERVICE]:
${vRUTA_JDK}java -jar ${vRUTA_FILE_SYSTEM}${vNOMBRE_MICROSERVICE}${vCOMPLEMENTO}${vNOMBRE_MICROSERVICE}-1.0.0.jar


ECHO 
ECHO [$vTRANSACCION] --------------------------------------- [FIN] ---------------------------------------
ECHO 

read -p "Pulsar para cerrar ...."

exit

