
-- ********************************************************** --
-- ****************** PAQUETE: [EMPLEADOS] ****************** -- 
-- ********************************************************** --

 -- ++++++++++++++++++++++++++++ [HEAD]: ++++++++++++++++++++++++++++ --
       
 CREATE OR REPLACE PACKAGE PKG_EMPLEADOS AS

    TYPE CURSOR_TYPE IS REF CURSOR;

    --SP_REGISTRAR_EMPLEADOS:
    PROCEDURE SP_REGISTRAR_EMPLEADOS( EMP_NOMBRE_IN  IN  VARCHAR2,
                                      EMP_EDAD_IN    IN  INT,
                                      EMP_ROL_IN     IN  VARCHAR2,
                                      DEP_ID_IN      IN  INTEGER,   --REFERENCIA
                                      
                                      ERR_OUT        OUT  VARCHAR2,
                                      MESSAGE_OUT    OUT  VARCHAR2,
                                      EMP_ID_OUT     OUT  INTEGER
                                    );

    --SP_ELIMINAR_EMPLEADOS:
    PROCEDURE SP_ELIMINAR_EMPLEADOS( EMP_ID_IN      IN  INTEGER,

                                     ERR_OUT        OUT  VARCHAR2,
                                     MESSAGE_OUT    OUT  VARCHAR2 
                                   );

    --SP_LISTAR_EMPLEADOS:
    PROCEDURE SP_LISTAR_EMPLEADOS( EMP_ID_IN        IN  INTEGER,

                                   ERR_OUT          OUT VARCHAR2,
                                   MESSAGE_OUT      OUT VARCHAR2,
                                   CURSOR_EMPLEADOS OUT CGUERRA.PKG_EMPLEADOS.CURSOR_TYPE
                                 );

    --SP_LISTAR_EMPLEADOS_X_DEPARTAMENTO:
    PROCEDURE SP_LISTAR_EMPLEADOS_X_DEPARTAMENTO( DEP_ID_IN        IN  INTEGER,

                                                  ERR_OUT          OUT VARCHAR2,
                                                  MESSAGE_OUT      OUT VARCHAR2,
                                                  CURSOR_EMPLEADOS OUT CGUERRA.PKG_EMPLEADOS.CURSOR_TYPE
                                                );

 END PKG_EMPLEADOS;
 
/
 
 -- ++++++++++++++++++++++++++++ [BODY]: ++++++++++++++++++++++++++++ --

 CREATE OR REPLACE PACKAGE BODY PKG_EMPLEADOS AS

    TYPE CURSOR_TYPE IS REF CURSOR;

    --SP_REGISTRAR_EMPLEADOS:
    PROCEDURE SP_REGISTRAR_EMPLEADOS( EMP_NOMBRE_IN  IN  VARCHAR2,
                                      EMP_EDAD_IN    IN  INT,
                                      EMP_ROL_IN     IN  VARCHAR2,
                                      DEP_ID_IN      IN  INTEGER,   --REFERENCIA

                                      ERR_OUT        OUT  VARCHAR2,
                                      MESSAGE_OUT    OUT  VARCHAR2,
                                      EMP_ID_OUT     OUT  INTEGER
                                    ) IS
    BEGIN
        DECLARE
           i_EMP_ID_OUT INTEGER;

        BEGIN
           --REGISTRANDO EN: 'TB_EMPLEADOS': 
           INSERT INTO CGUERRA.TB_EMPLEADOS( EMP_ID, EMP_NOMBRE, EMP_EDAD, EMP_ROL )
           VALUES( SQ_EMPLEADOS.NEXTVAL, EMP_NOMBRE_IN, EMP_EDAD_IN, EMP_ROL_IN );

           --OBTENIENDO 'CODIGO INGRESADO':
           SELECT SQ_EMPLEADOS.CURRVAL
           INTO   i_EMP_ID_OUT
           FROM   DUAL;

           EMP_ID_OUT := i_EMP_ID_OUT;
           DBMS_OUTPUT.PUT_LINE( '- EMP_ID_OUT [CURRVAL]: ' || TO_CHAR( EMP_ID_OUT ) );
           
           --REGISTRANDO EN: 'TB_DEP_EMP': 
           INSERT INTO CGUERRA.TB_DEP_EMP( DEP_ID, EMP_ID )
           VALUES( DEP_ID_IN, i_EMP_ID_OUT );

           ERR_OUT     := '1';
           MESSAGE_OUT := 'PROCESO EXITOSO';

       EXCEPTION
            WHEN OTHERS THEN
                 ERR_OUT     := TO_CHAR( SQLCODE ); 
                 MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );  
       END;

    END SP_REGISTRAR_EMPLEADOS;
 
 
    --SP_ELIMINAR_EMPLEADOS:
    PROCEDURE SP_ELIMINAR_EMPLEADOS( EMP_ID_IN      IN  INTEGER,

                                     ERR_OUT        OUT  VARCHAR2,
                                     MESSAGE_OUT    OUT  VARCHAR2 
                                   ) IS
    BEGIN
        DECLARE
     
        BEGIN
           --ELIMINANDO EN: 'TB_EMPLEADOS': 
           DELETE FROM CGUERRA.TB_EMPLEADOS e
           WHERE  e.EMP_ID = EMP_ID_IN;
 
           --ELIMINANDO EN: 'TB_DEP_EMP': 
           DELETE FROM CGUERRA.TB_DEP_EMP dp 
           WHERE  dp.EMP_ID = EMP_ID_IN; 
           
           ERR_OUT     := '1';
           MESSAGE_OUT := 'PROCESO EXITOSO'; 
           
       EXCEPTION
            WHEN OTHERS THEN
                 ERR_OUT     := TO_CHAR( SQLCODE ); 
                 MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );  
       END;

    END SP_ELIMINAR_EMPLEADOS;
 

    --SP_LISTAR_EMPLEADOS:
    PROCEDURE SP_LISTAR_EMPLEADOS( EMP_ID_IN        IN  INTEGER,

                                   ERR_OUT          OUT VARCHAR2,
                                   MESSAGE_OUT      OUT VARCHAR2,
                                   CURSOR_EMPLEADOS OUT CGUERRA.PKG_EMPLEADOS.CURSOR_TYPE
                                 ) AS
      BEGIN
          IF( EMP_ID_IN IS NOT NULL  AND 
              EMP_ID_IN <> 0 ) THEN 
                         
               OPEN CURSOR_EMPLEADOS
                  FOR
                     SELECT X.EMP_ID,
                            X.EMP_NOMBRE,
                            X.EMP_EDAD,
                            X.EMP_ROL
                      FROM  CGUERRA.TB_EMPLEADOS X
                      WHERE X.EMP_ID = EMP_ID_IN
                      ORDER BY 1;

               ERR_OUT     := '1';
               MESSAGE_OUT := 'PROCESO EXITOSO [OBJETO]';
          ELSE               
              OPEN CURSOR_EMPLEADOS
                  FOR
                     SELECT X.EMP_ID,
                            X.EMP_NOMBRE,
                            X.EMP_EDAD,
                            X.EMP_ROL
                      FROM  CGUERRA.TB_EMPLEADOS X
                      ORDER BY 1;

               ERR_OUT     := '2';
               MESSAGE_OUT := 'PROCESO EXITOSO [LISTA]';
          END IF;

      EXCEPTION

        WHEN OTHERS THEN
             ERR_OUT     := TO_CHAR( SQLCODE ); 
             MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 ); 

    END SP_LISTAR_EMPLEADOS;

 
    --SP_LISTAR_EMPLEADOS_X_DEPARTAMENTO:
    PROCEDURE SP_LISTAR_EMPLEADOS_X_DEPARTAMENTO( DEP_ID_IN        IN  INTEGER,

                                                  ERR_OUT          OUT VARCHAR2,
                                                  MESSAGE_OUT      OUT VARCHAR2,
                                                  CURSOR_EMPLEADOS OUT CGUERRA.PKG_EMPLEADOS.CURSOR_TYPE
                                                ) AS
      BEGIN
          IF( DEP_ID_IN IS NOT NULL  AND 
              DEP_ID_IN <> 0 ) THEN 
                         
               OPEN CURSOR_EMPLEADOS
                  FOR 
                     SELECT de.EMP_ID, 
                            e.EMP_NOMBRE,
                            e.EMP_EDAD,
                            e.EMP_ROL,
                            de.DEP_ID
                     FROM   CGUERRA.TB_DEP_EMP de, CGUERRA.TB_EMPLEADOS e
                     WHERE  de.EMP_ID = e.EMP_ID
                     AND    de.DEP_ID = DEP_ID_IN 
                     ORDER BY 1;

               ERR_OUT     := '1';
               MESSAGE_OUT := 'PROCESO EXITOSO [OBJETO]';
          ELSE               
              OPEN CURSOR_EMPLEADOS
                  FOR
                     SELECT de.EMP_ID, 
                            e.EMP_NOMBRE,
                            e.EMP_EDAD,
                            e.EMP_ROL
                     FROM   CGUERRA.TB_DEP_EMP de, CGUERRA.TB_EMPLEADOS e
                     WHERE  de.EMP_ID = e.EMP_ID  
                     ORDER BY 1;

               ERR_OUT     := '2';
               MESSAGE_OUT := 'PROCESO EXITOSO [LISTA]';
          END IF;

      EXCEPTION

        WHEN OTHERS THEN
             ERR_OUT     := TO_CHAR( SQLCODE ); 
             MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 ); 

    END SP_LISTAR_EMPLEADOS_X_DEPARTAMENTO;


 END PKG_EMPLEADOS;
 
/
 
-- ********************************************************** --
-- **************** PAQUETE: [DEPARTAMENTOS] **************** -- 
-- ********************************************************** --

 -- ++++++++++++++++++++++++++++ [HEAD]: ++++++++++++++++++++++++++++ --
       
 CREATE OR REPLACE PACKAGE PKG_DEPARTAMENTOS AS

    TYPE CURSOR_TYPE IS REF CURSOR;
 
    --SP_REGISTRAR_DEPARTAMENTOS:
    PROCEDURE SP_REGISTRAR_DEPARTAMENTOS( DEP_NOMBRE_IN  IN  VARCHAR2,
                                          ORG_ID_IN      IN  INTEGER,   --REFERENCIA
                                          
                                          ERR_OUT        OUT  VARCHAR2,
                                          MESSAGE_OUT    OUT  VARCHAR2,
                                          DEP_ID_OUT     OUT  INTEGER
                                        ); 

    --SP_ELIMINAR_DEPARTAMENTOS:
    PROCEDURE SP_ELIMINAR_DEPARTAMENTOS( DEP_ID_IN      IN  INTEGER,

                                         ERR_OUT        OUT  VARCHAR2,
                                         MESSAGE_OUT    OUT  VARCHAR2 
                                       );

    --SP_LISTAR_DEPARTAMENTOS:
    PROCEDURE SP_LISTAR_DEPARTAMENTOS( DEP_ID_IN            IN  INTEGER,

                                       ERR_OUT              OUT VARCHAR2,
                                       MESSAGE_OUT          OUT VARCHAR2,
                                       CURSOR_DEPARTAMENTOS OUT CGUERRA.PKG_DEPARTAMENTOS.CURSOR_TYPE
                                     );
                                       
    --SP_LISTAR_DEPARTAMENTOS_X_ORGANIZACION:
    PROCEDURE SP_LISTAR_DEPARTAMENTOS_X_ORGANIZACION( ORG_ID_IN            IN  INTEGER,

                                                      ERR_OUT              OUT VARCHAR2,
                                                      MESSAGE_OUT          OUT VARCHAR2,
                                                      CURSOR_DEPARTAMENTOS OUT CGUERRA.PKG_DEPARTAMENTOS.CURSOR_TYPE
                                                    ); 
 END PKG_DEPARTAMENTOS;

/

 -- ++++++++++++++++++++++++++++ [BODY]: ++++++++++++++++++++++++++++ --

 CREATE OR REPLACE PACKAGE BODY PKG_DEPARTAMENTOS AS

    TYPE CURSOR_TYPE IS REF CURSOR;

    --SP_REGISTRAR_DEPARTAMENTOS:
    PROCEDURE SP_REGISTRAR_DEPARTAMENTOS( DEP_NOMBRE_IN IN  VARCHAR2,
                                          ORG_ID_IN     IN  INTEGER,   --REFERENCIA
                                          
                                          ERR_OUT       OUT  VARCHAR2,
                                          MESSAGE_OUT   OUT  VARCHAR2,
                                          DEP_ID_OUT    OUT  INTEGER
                                        ) IS
    BEGIN
        DECLARE
           i_DEP_ID_OUT INTEGER;

        BEGIN
           --REGISTRANDO EN: 'TB_DEPARTAMENTOS': 
           INSERT INTO CGUERRA.TB_DEPARTAMENTOS( DEP_ID, DEP_NOMBRE )
           VALUES( SQ_DEPARTAMENTOS.NEXTVAL, DEP_NOMBRE_IN );

           --OBTENIENDO 'CODIGO INGRESADO':
           SELECT SQ_DEPARTAMENTOS.CURRVAL
           INTO   i_DEP_ID_OUT
           FROM   DUAL;

           DEP_ID_OUT := i_DEP_ID_OUT;
           DBMS_OUTPUT.PUT_LINE( '- DEP_ID_OUT [CURRVAL]: ' || TO_CHAR( DEP_ID_OUT ) );

           --REGISTRANDO EN: 'TB_ORG_DEP': 
           INSERT INTO CGUERRA.TB_ORG_DEP( ORG_ID, DEP_ID )
           VALUES( ORG_ID_IN, i_DEP_ID_OUT );

           ERR_OUT     := '1';
           MESSAGE_OUT := 'PROCESO EXITOSO';

       EXCEPTION
            WHEN OTHERS THEN
                 ERR_OUT     := TO_CHAR( SQLCODE );
                 MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );
       END;

    END SP_REGISTRAR_DEPARTAMENTOS;
    
    
    --SP_ELIMINAR_DEPARTAMENTOS:
    PROCEDURE SP_ELIMINAR_DEPARTAMENTOS( DEP_ID_IN      IN  INTEGER,

                                         ERR_OUT        OUT  VARCHAR2,
                                         MESSAGE_OUT    OUT  VARCHAR2 
                                       ) IS
    BEGIN
        DECLARE
      
        BEGIN
           --ELIMINANDO EN: 'TB_DEPARTAMENTOS':
           DELETE FROM CGUERRA.TB_DEPARTAMENTOS d
           WHERE  d.DEP_ID = DEP_ID_IN;

           --ELIMINANDO EN: 'TB_DEP_EMP':
           DELETE FROM CGUERRA.TB_DEP_EMP dp
           WHERE  dp.DEP_ID = DEP_ID_IN;
           
           --ELIMINANDO EN: 'TB_ORG_DEP':
           DELETE FROM CGUERRA.TB_ORG_DEP od
           WHERE  od.DEP_ID = DEP_ID_IN;
           
           ERR_OUT     := '1';
           MESSAGE_OUT := 'PROCESO EXITOSO'; 
           
       EXCEPTION
            WHEN OTHERS THEN
                 ERR_OUT     := TO_CHAR( SQLCODE ); 
                 MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );  
       END;

    END SP_ELIMINAR_DEPARTAMENTOS;
    

    --SP_LISTAR_DEPARTAMENTOS:
    PROCEDURE SP_LISTAR_DEPARTAMENTOS( DEP_ID_IN            IN  INTEGER,

                                       ERR_OUT              OUT VARCHAR2,
                                       MESSAGE_OUT          OUT VARCHAR2,
                                       CURSOR_DEPARTAMENTOS OUT CGUERRA.PKG_DEPARTAMENTOS.CURSOR_TYPE
                                     ) AS
      BEGIN
          IF( DEP_ID_IN IS NOT NULL  AND 
              DEP_ID_IN <> 0 ) THEN 

               OPEN CURSOR_DEPARTAMENTOS
                  FOR
                     SELECT X.DEP_ID,
                            X.DEP_NOMBRE
                      FROM  CGUERRA.TB_DEPARTAMENTOS X
                      WHERE X.DEP_ID = DEP_ID_IN
                      ORDER BY 1;

               ERR_OUT     := '1';
               MESSAGE_OUT := 'PROCESO EXITOSO [OBJETO]';
          ELSE
              OPEN CURSOR_DEPARTAMENTOS
                  FOR
                     SELECT X.DEP_ID,
                            X.DEP_NOMBRE
                      FROM  CGUERRA.TB_DEPARTAMENTOS X
                      ORDER BY 1;

               ERR_OUT     := '2';
               MESSAGE_OUT := 'PROCESO EXITOSO [LISTA]';
          END IF;

      EXCEPTION

        WHEN OTHERS THEN
             ERR_OUT     := TO_CHAR( SQLCODE );
             MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );

    END SP_LISTAR_DEPARTAMENTOS;
    
    
    --SP_LISTAR_DEPARTAMENTOS_X_ORGANIZACION:
    PROCEDURE SP_LISTAR_DEPARTAMENTOS_X_ORGANIZACION( ORG_ID_IN            IN  INTEGER,

                                                      ERR_OUT              OUT VARCHAR2,
                                                      MESSAGE_OUT          OUT VARCHAR2,
                                                      CURSOR_DEPARTAMENTOS OUT CGUERRA.PKG_DEPARTAMENTOS.CURSOR_TYPE
                                                    ) AS
      BEGIN
          IF( ORG_ID_IN IS NOT NULL  AND 
              ORG_ID_IN <> 0 ) THEN 

               OPEN CURSOR_DEPARTAMENTOS
                  FOR
                      SELECT od.DEP_ID, 
                             d.DEP_NOMBRE, 
                             od.ORG_ID  
                      FROM   TB_ORG_DEP od, TB_DEPARTAMENTOS d
                      WHERE  od.DEP_ID = d.DEP_ID
                      AND    od.ORG_ID = ORG_ID_IN
                      ORDER BY 1;

               ERR_OUT     := '1';
               MESSAGE_OUT := 'PROCESO EXITOSO [OBJETO]';
          ELSE
              OPEN CURSOR_DEPARTAMENTOS
                  FOR
                      SELECT od.DEP_ID, d.DEP_NOMBRE 
                      FROM   TB_ORG_DEP od, TB_DEPARTAMENTOS d
                      WHERE  od.DEP_ID = d.DEP_ID 
                      ORDER BY 1;

               ERR_OUT     := '2';
               MESSAGE_OUT := 'PROCESO EXITOSO [LISTA]';
          END IF;

      EXCEPTION

        WHEN OTHERS THEN
             ERR_OUT     := TO_CHAR( SQLCODE );
             MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );

    END SP_LISTAR_DEPARTAMENTOS_X_ORGANIZACION;
                                                   
 END PKG_DEPARTAMENTOS;
 
/
 
-- ********************************************************** --
-- **************** PAQUETE: [ORGANIZACIONES] *************** -- 
-- ********************************************************** --

 -- ++++++++++++++++++++++++++++ [HEAD]: ++++++++++++++++++++++++++++ --
       
 CREATE OR REPLACE PACKAGE PKG_ORGANIZACIONES AS

    TYPE CURSOR_TYPE IS REF CURSOR;

    --SP_REGISTRAR_ORGANIZACIONES:
    PROCEDURE SP_REGISTRAR_ORGANIZACIONES( ORG_NOMBRE_IN     IN  VARCHAR2,
                                           ORG_DIRECCION_IN  IN  VARCHAR2,

                                           ERR_OUT           OUT  VARCHAR2,
                                           MESSAGE_OUT       OUT  VARCHAR2,
                                           ORG_ID_OUT        OUT  INTEGER
                                         );

    --SP_ELIMINAR_ORGANIZACIONES:
    PROCEDURE SP_ELIMINAR_ORGANIZACIONES( ORG_ID_IN      IN  INTEGER,

                                          ERR_OUT        OUT  VARCHAR2,
                                          MESSAGE_OUT    OUT  VARCHAR2 
                                        );
                                   
     --SP_LISTAR_ORGANIZACIONES:
    PROCEDURE SP_LISTAR_ORGANIZACIONES( ORG_ID_IN             IN  INTEGER,

                                        ERR_OUT               OUT VARCHAR2,
                                        MESSAGE_OUT           OUT VARCHAR2,
                                        CURSOR_ORGANIZACIONES OUT CGUERRA.PKG_ORGANIZACIONES.CURSOR_TYPE
                                      );

 END PKG_ORGANIZACIONES;
 
/

 -- ++++++++++++++++++++++++++++ [BODY]: ++++++++++++++++++++++++++++ --

 CREATE OR REPLACE PACKAGE BODY PKG_ORGANIZACIONES AS

    TYPE CURSOR_TYPE IS REF CURSOR;

    --SP_REGISTRAR_ORGANIZACIONES:
    PROCEDURE SP_REGISTRAR_ORGANIZACIONES( ORG_NOMBRE_IN     IN  VARCHAR2,
                                           ORG_DIRECCION_IN  IN  VARCHAR2,

                                           ERR_OUT           OUT  VARCHAR2,
                                           MESSAGE_OUT       OUT  VARCHAR2,
                                           ORG_ID_OUT        OUT  INTEGER
                                         ) IS
    BEGIN
        DECLARE
           i_ORG_ID_OUT INTEGER; 

        BEGIN
           INSERT INTO CGUERRA.TB_ORGANIZACIONES( ORG_ID, ORG_NOMBRE, ORG_DIRECCION )
           VALUES( SQ_ORGANIZACION.NEXTVAL, ORG_NOMBRE_IN, ORG_DIRECCION_IN );

           --OBTENER CODIGO INGRESADO:
           SELECT SQ_ORGANIZACION.CURRVAL
           INTO   i_ORG_ID_OUT
           FROM   DUAL;

           ORG_ID_OUT := i_ORG_ID_OUT;
           DBMS_OUTPUT.PUT_LINE( '- ORG_ID_OUT [CURRVAL]: ' || TO_CHAR( ORG_ID_OUT ) );

           ERR_OUT     := '1';
           MESSAGE_OUT := 'PROCESO EXITOSO';

       EXCEPTION
            WHEN OTHERS THEN
                 ERR_OUT     := TO_CHAR( SQLCODE );
                 MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 ); 
       END;

    END SP_REGISTRAR_ORGANIZACIONES;


    --SP_ELIMINAR_ORGANIZACIONES:
    PROCEDURE SP_ELIMINAR_ORGANIZACIONES( ORG_ID_IN      IN  INTEGER,

                                          ERR_OUT        OUT  VARCHAR2,
                                          MESSAGE_OUT    OUT  VARCHAR2 
                                        ) IS
    BEGIN
        DECLARE
      
        BEGIN
           --ELIMINANDO EN: 'TB_ORGANIZACIONES': 
           DELETE FROM CGUERRA.TB_ORGANIZACIONES o
           WHERE  o.ORG_ID = ORG_ID_IN; 
           
           --ELIMINANDO EN: 'TB_ORG_DEP': 
           DELETE FROM CGUERRA.TB_ORG_DEP od 
           WHERE  od.ORG_ID = ORG_ID_IN; 
           
           ERR_OUT     := '1';
           MESSAGE_OUT := 'PROCESO EXITOSO'; 
           
       EXCEPTION
            WHEN OTHERS THEN
                 ERR_OUT     := TO_CHAR( SQLCODE ); 
                 MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );  
       END;

    END SP_ELIMINAR_ORGANIZACIONES;


    --SP_LISTAR_ORGANIZACIONES:
    PROCEDURE SP_LISTAR_ORGANIZACIONES( ORG_ID_IN             IN  INTEGER,

                                        ERR_OUT               OUT VARCHAR2,
                                        MESSAGE_OUT           OUT VARCHAR2,
                                        CURSOR_ORGANIZACIONES OUT CGUERRA.PKG_ORGANIZACIONES.CURSOR_TYPE
                                      ) AS
      BEGIN
          IF( ORG_ID_IN IS NOT NULL  AND 
              ORG_ID_IN <> 0 ) THEN   

               OPEN CURSOR_ORGANIZACIONES
                  FOR
                     SELECT X.ORG_ID,
                            X.ORG_NOMBRE,
                            X.ORG_DIRECCION
                      FROM  CGUERRA.TB_ORGANIZACIONES X
                      WHERE X.ORG_ID = ORG_ID_IN
                      ORDER BY 1;

               ERR_OUT     := '1';
               MESSAGE_OUT := 'PROCESO EXITOSO [OBJETO]';
          ELSE
              OPEN CURSOR_ORGANIZACIONES
                  FOR
                     SELECT X.ORG_ID,
                            X.ORG_NOMBRE,
                            X.ORG_DIRECCION
                      FROM  CGUERRA.TB_ORGANIZACIONES X
                      ORDER BY 1;

               ERR_OUT     := '2';
               MESSAGE_OUT := 'PROCESO EXITOSO [LISTA]';
          END IF;

      EXCEPTION

        WHEN OTHERS THEN
             ERR_OUT     := TO_CHAR( SQLCODE );
             MESSAGE_OUT := SUBSTR( UPPER( DBMS_UTILITY.FORMAT_ERROR_STACK || '@' || DBMS_UTILITY.FORMAT_CALL_STACK ), 1, 100 );

    END SP_LISTAR_ORGANIZACIONES;

 END PKG_ORGANIZACIONES;

/
