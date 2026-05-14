EBAC API — Proyecto REST con Spring Boot

API REST desarrollada con Java y Spring Boot para gestión de recursos utilizando arquitectura en capas, serialización JSON y endpoints CRUD.

---

# Tabla de contenidos

- Arquitectura del proyecto
- Tecnologías y dependencias
- Estructura del proyecto
- Configuración
- Ejecución
- Endpoints REST
- Arquitectura en capas
- Validaciones
- Pruebas

---

# Arquitectura del proyecto

La aplicación sigue una arquitectura basada en capas:

┌──────────────────────────────┐
│        Controllers           │ ← Endpoints REST
├──────────────────────────────┤
│         Services             │ ← Lógica de negocio
├──────────────────────────────┤
│       Repositories           │ ← Acceso a datos
├──────────────────────────────┤
│         Entities             │ ← Modelo de dominio
├──────────────────────────────┤
│        Base de datos         │ ← Persistencia
└──────────────────────────────┘

---

# Tecnologías

| Tecnología | Propósito |
|------------|-----------|
| Java 17 | Lenguaje principal |
| Spring Boot | Framework backend |
| Maven | Gestión de dependencias |
| Spring Web | API REST |
| Jackson | Serialización JSON |
| MySQL | Persistencia |
| JUnit 5 | Pruebas unitarias |

---

# Estructura del proyecto

ebac-api/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/ebac/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   └── EbacApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/

---

# Configuración

Editar el archivo:

src/main/resources/application.properties

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ebac_db
spring.datasource.username=root
spring.datasource.password=password
```

---

# Ejecución

## Compilar

```bash
mvn clean install
```

## Ejecutar

```bash
mvn spring-boot:run
```

---

# Endpoints REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/items | Obtener todos |
| GET | /api/items/{id} | Obtener por ID |
| POST | /api/items | Crear |
| PUT | /api/items/{id} | Actualizar |
| DELETE | /api/items/{id} | Eliminar |

---

# Validaciones

La aplicación implementa:

- Validación de datos
- Manejo de errores HTTP
- Serialización JSON
- Respuestas RESTful

---

# Pruebas

## Ejecutar pruebas

```bash
mvn test
```

---

# Autor

Xavier Santamaria**
