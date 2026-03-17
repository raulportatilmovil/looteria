# Looteria Backend

API REST desarrollada con **Spring Boot 3.2** + **Java 17** + **MySQL** + **JPA/Hibernate**

## 📁 Estructura

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/looteria/
│   │   │   ├── controller/          # REST Controllers
│   │   │   ├── service/             # Lógica de negocio
│   │   │   ├── repository/          # Acceso a datos (JPA)
│   │   │   ├── entity/              # Modelos JPA (Entidades)
│   │   │   ├── config/              # Configuración
│   │   │   └── LooteriaApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
├── database.sql                     # Script de inicialización
├── pom.xml                          # Dependencias Maven
├── mvnw / mvnw.cmd                 # Maven Wrapper
└── .gitignore
```

## 🚀 Inicio Rápido

### Requisitos
- Java 17+
- Maven 3.6+
- MySQL 5.7+ ejecutándose

### Pasos

1. **Crear base de datos**
```bash
mysql -u root -p < database.sql
```

2. **Compilar**
```bash
mvn clean compile
```

3. **Ejecutar**
```bash
mvn spring-boot:run
```

API disponible en: `http://localhost:8080`

## 🛠️ Tecnologías

| Nombre | Versión |
|--------|---------|
| Spring Boot | 3.2.0 |
| Java | 17 |
| MySQL Connector | 8.0.33 |
| JPA/Hibernate | Auto |
| Lombok | Auto |
| Maven | 3.6+ |

## 📊 Configuración Base de Datos

**Archivo**: `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/looteria
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
server.port=8080
```

## 📝 Estructura de Carpetas Fuente

### Controller
Maneja las peticiones HTTP y respuestas REST.
```
/controller
├── ArticuloController.java
├── UsuarioController.java
├── IntercambioController.java
└── ...
```

### Service
Contiene la lógica de negocio.
```
/service
├── ArticuloService.java
├── UsuarioService.java
├── IntercambioService.java
└── ...
```

### Repository
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

## 🐛 Solución de Problemas

### Puerto 8080 en uso
```bash
# Cambiar puerto en application.properties
server.port=8081
```

### Conexión BD fallida
```
1. Verifica que MySQL está corriendo
2. Usuario/contraseña correctos
3. BD 'looteria' existe
```

## 📚 Dependencias Principales

- **spring-boot-starter-web**: Framework web
- **spring-boot-starter-data-jpa**: ORM
- **mysql-connector-j**: Driver MySQL
- **lombok**: Reduce boilerplate
- **spring-boot-starter-validation**: Validación

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
