# Looteria Backend

API REST desarrollada con Spring Boot + Java + MySQL.

## Tecnologías
- Spring Boot 3.2
- Java 17
- MySQL 8.0
- JPA/Hibernate

## Requisitos
- Java 17+
- Maven 3.6+
- MySQL 5.7+

## Quick Start

### 1. Base de Datos
```bash
mysql -u root -p < database.sql
```

### 2. Compilar y Ejecutar
```bash
mvn spring-boot:run
```

API disponible en: `http://localhost:8081`

## Configuración (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/looteria
spring.datasource.username=root
spring.datasource.password=
server.port=8081
spring.jpa.hibernate.ddl-auto=update
```

## Estructura
```
src/main/java/com/looteria/
├── controller/      # REST Controllers
├── service/         # Lógica de negocio
├── repository/      # Acceso a datos (JPA)
└── entity/          # Modelos JPA
```
Interfaz con la base de datos (JPA).
```
/repository
├── ArticuloRepository.java
├── UsuarioRepository.java
├── IntercambioRepository.java
└── ...
```

### Entity
Modelos de datos mapeados a tablas.
```
/entity
├── Articulo.java
├── Usuario.java
├── Intercambio.java
├── Categoria.java
└── ...
```

### Config
Configuración de Spring (CORS, Security, etc).
```
/config
├── CorsConfig.java
├── SecurityConfig.java
└── ...
```

## 🔌 Endpoints Principales

```
GET    /api/articulos              Listar artículos
GET    /api/articulos/{id}         Obtener artículo
POST   /api/articulos              Crear artículo
PUT    /api/articulos/{id}         Actualizar artículo
DELETE /api/articulos/{id}         Eliminar artículo

GET    /api/usuarios               Listar usuarios
POST   /api/usuarios               Registrar usuario

GET    /api/intercambios           Listar intercambios
POST   /api/intercambios           Crear intercambio
```

## 📚 Dependencias Principales

- **spring-boot-starter-web**
- **spring-boot-starter-data-jpa**
- **mysql-connector-j**
- **lombok**
- **spring-boot-starter-validation**

## 🔄 Build & Deploy

```bash
# Compilar
mvn clean compile

# Ejecutar tests
mvn test

# Empaquetar JAR
mvn clean package

# Ejecutar JAR
java -jar target/looteria-0.0.1-SNAPSHOT.jar
```

---

**Proyecto Looteria** - Plataforma de Intercambio de Videojuegos
