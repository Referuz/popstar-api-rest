# PopStar API

Backend API REST desarrollada en Java para la gestión de una cadena de restaurantes.  
Implementa arquitectura MVC, operaciones CRUD sobre MySQL y separación por capas.  
Incluye definición de base de datos y pruebas de consumo de endpoints mediante clientes HTTP.

---

## Arquitectura del Proyecto

### Modelo (`popstar-model2`)
Define las entidades del sistema:
- Empleados
- Sucursales

Representa la estructura de los datos persistidos en la base de datos MySQL.

---

### Controlador (`popstarCore2`)
Gestiona el flujo de datos entre los servicios y la base de datos.
Incluye:
- Acceso a datos persistentes (MySQL)
- Manejo de datos no persistentes (`DataPopStar.java`)

---

### Servicios / CRUD (`popstar-servicios2`)
Implementa los endpoints REST para:
- Crear
- Leer
- Actualizar
- Eliminar

Datos persistidos en MySQL mediante arquitectura MVC.

---

### Consumo de la API
Incluye pruebas de consumo de los endpoints REST utilizando clientes HTTP para validación del funcionamiento de la API.

---

### Base de Datos (`database`)
Contiene el esquema de la base de datos:
- DDL (definición de tablas)
- DML (datos de prueba)

---

## Tecnologías Utilizadas

- **Java**
- **Jersey** – Framework para construcción de APIs REST
- **Gson** – Serialización y deserialización JSON
- **Apache Tomcat** – Servidor de aplicaciones
- **MySQL** – Base de datos relacional
- **Bootstrap** – Estilos para cliente de consumo
- **HTML, CSS, JavaScript**

---

## Ejecución del Proyecto

1. Ejecutar el script `database/schema.sql` en MySQL.
2. Configurar credenciales de conexión a la base de datos.
3. Desplegar el proyecto en Apache Tomcat.
4. Probar los endpoints mediante cliente HTTP (Postman o navegador).

---

## Endpoints (ejemplo)

- `GET /empleados/getall`
- `GET /sucursales/getall`

---
