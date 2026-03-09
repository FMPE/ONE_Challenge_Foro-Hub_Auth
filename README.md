# Foro Hub - API REST - Challenge ONE

## Descripción
Esta es una API REST en Java que provee las funcionalidades de un foro de discusión (Foro Hub), permitiendo a los usuarios crear tópicos con preguntas o sugerencias y a otros listar esa información. El proyecto fue desarrollado como parte del Challenge de Java del programa Oracle Next Education (ONE). La API incluye un sistema de autenticación basado en tokens JWT y utiliza persistencia en una base de datos SQLite.

## Características
- Autenticación y autorización: Implementación de seguridad con tokens JWT para proteger endpoints sensibles.
- Endpoint de Autenticación (`/login`) para verificar credenciales de usuarios.
- Operaciones sobre tópicos (preguntas o temas del foro):
  - Creación de nuevos tópicos con validación estricta de datos (requiere autenticación).
  - Listado de todos los tópicos existentes, incluyendo soporte automático de paginación.
  - Eliminación de tópicos específicos mediante su ID (requiere autenticación).
- Base de datos relacional en archivo (SQLite) para un fácil almacenamiento sin instalaciones externas.
- Manejo global de excepciones para proporcionar respuestas estandarizadas y claras ante errores del cliente (ej. 400 Bad Request, 404 Not Found).

## Cómo usar la aplicación
1. Clona el repositorio en tu máquina local.
2. Abre la carpeta del proyecto que contiene la API en tu IDE preferido (IntelliJ IDEA, Eclipse, VS Code).
3. Asegúrate de dejar que Maven descargue todas las dependencias listadas en el `pom.xml`.
4. La aplicación está configurada para usar SQLite. Al ejecutar la API, se creará o actualizará automáticamente el archivo local `foro.db` en la raíz del proyecto. No necesitas instalar ni levantar un servidor de base de datos extra.
5. Ejecuta la clase principal `ApiApplication.java` para iniciar la aplicación Spring Boot.
6. Utiliza Postman, Insomnia o la interfaz de Swagger autogenerada (disponible en `http://localhost:8080/swagger-ui.html`) para hacer peticiones a los endpoints:
   - Iniciar sesión para obtener un token: `POST http://localhost:8080/login`
   - Listado de todos los tópicos: `GET http://localhost:8080/topics`
   - Creación de tópico: `POST http://localhost:8080/topics` (Asegúrate de enviar el Token JWT en el header `Authorization` con el formato `Bearer <token>`).
   - Eliminar tópico: `DELETE http://localhost:8080/topics/{id}` (También requiere el Token JWT en los headers).

## Requisitos y Configuración
- **Java 17** o superior.
- **Maven** para la gestión de dependencias y construcción del proyecto.

## Tecnologías utilizadas
- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework principal para la estructuración y creación de la API REST (versión 3.3.0).
- **Spring Data JPA**: Abstracción y especificación (usando Hibernate en el fondo) para mapear objetos a la base de datos relacional.
- **Spring Security**: Para blindar la aplicación configurando el control de acceso e interceptores HTTP.
- **JWT (com.auth0)**: Estándar utilizado para la generación, firma y validación de tokens de sesión de manera "stateless".
- **SQLite (sqlite-jdbc)**: Empleado como motor de base de datos relacional local sin servidor.
- **Springdoc / Swagger**: Incorporado para la documentación automática y la exploración interactiva de la API.
- **Lombok**: Herramienta muy útil para reducir el código redundante o repetitivo (boilerplate) en las clases Java.

## Estructura del proyecto
- `domain/`: Contiene las clases centrales de negocio representadas como entidades (`Topic.java`, `User.java`) preparadas para JPA, sus respectivos repositorios o DAOs para interactuar con los datos (`TopicRepository.java`, `UserRepository.java`), y los Data Transfer Objects (DTOs con Java Records) usados para modelar el JSON entrante/saliente de forma inmutable.
- `infra/`: Alberga los componentes técnicos y globales o de configuración de la app. Aquí se encuentra toda la estructura de Seguridad (`SecurityConfigurations.java`, `SecurityFilter.java`, `TokenService.java`) y el `GlobalExceptionHandler.java` que captura las excepciones arrojadas por los controladores para dar una única respuesta estandarizada.
- `controller/`: Aloja los REST controllers (`TopicController.java`, `AuthenticationController.java`), estas son las puertas de entrada de nuestra API que escuchan las rutas web, delegan lógica, procesan la información por medio de DTOs y formatean la respuesta HTTP (JSON) final.

---
Desarrollado como parte del challenge de Back End (Foro Hub) del programa Oracle Next Education (ONE).