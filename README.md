
# Spring Boot Demo Project

Esta es una demostración de un proyecto de Spring Boot usando Java como lenguaje de programación. El proyecto sigue el modelo de arquitectura MVC y utiliza MySQL como base de datos. El servicio gestiona las principales actividades sobre una clase `Movie`, incluyendo las operaciones CRUD y un método adicional para realizar votaciones.

## Requisitos

-   Java 11 o superior
-   Spring Boot 2.5 o superior
-   MySQL 5.7 o superior
-   Gradle 6.8 o superior

## Configuración del Proyecto

### Base de Datos

Configura tu base de datos MySQL con la siguiente información en el archivo `application.properties`:

properties

Copiar código

`spring.application.name=movies
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/movies
spring.datasource.username=root
spring.datasource.password=123123
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver` 

### Estructura del Proyecto

El proyecto sigue el patrón de diseño MVC y está estructurado de la siguiente manera:

-   **Controller**: Maneja las solicitudes HTTP y las respuestas.
-   **Repository**: Interactúa con la base de datos.
-   **Model**: Representa la entidad `Movie`.

### Operaciones CRUD para Movies

-   **GET /movies**: Obtiene todas las películas.
-   **GET /movies/{id}**: Obtiene una película por su ID.
-   **POST /movies**: Crea una nueva película.
-   **PUT /movies/{id}**: Actualiza una película existente.
-   **DELETE /movies/{id}**: Elimina una película.

### Endpoint Adicional: Votación de Movies

-   **GET /vote/{id}/{rating}**: Permite votar una película específica con un rating determinado.
