# PopStar

Backend API REST en java con arquitectura MVC y persistencia en MySQL. Incluye endpoints CRUD, separación por capas (modelo, servicios, controladores) y pruebas de consumo mediante clientes HTTP.


## Modelo (popstar-model2)
>   Estructuración de los datos *Empleados* y *Sucursales*


## Controlador (popstarCore2)
>   Control de utilización de datos persistentes MySQL
>   Además incluye para datos sin DB (DataPopStar.java)


## CRUD (popstar-servicios2) 
>   Definición de endpoints para CRUD de los datos persistentes de la DB MySQL


## Consumo de API
>   Consumo de los endpoints creados en la API  


## Definición de datos prueba (database)
>   Esquema de definición de la base de datos, contiene DDL y DML



### Se trabajó con: 
>   * **Librería** *Jersey* - para construcción de la API
>   * **Librería** *Gson* - Mejora la utilización de lenguaje JSON
>   * **Servidor** *Apache Tomcat* - Indispensable para servir APIS REST
>   * **Herramienta** *Bootstrap* - Mejora la rapidez al dar estilos
>   * **Lenguajes:** *Java, Xml, Html, Css, JavaScript*