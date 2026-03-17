# 🎮 Looteria

**Plataforma de intercambio de videojuegos**

Proyecto Full Stack moderno con arquitectura separada:
- **Backend**: Spring Boot 3.2 + Java 17 + MySQL
- **Frontend**: React 18 + TypeScript + Vite + Tailwind CSS

---

## 📂 Estructura del Proyecto

```
looteria/
│
├── 📁 backend/                    # API REST (Spring Boot)
│   ├── src/
│   │   ├── main/java/com/looteria/
│   │   │   ├── controller/        # REST Controllers
│   │   │   ├── service/           # Lógica de negocio
│   │   │   ├── repository/        # JPA Repositories
│   │   │   ├── entity/            # Entidades JPA
│   │   │   └── config/            # Configuración
│   │   └── main/resources/
│   │       └── application.properties
│   ├── database.sql               # Script SQL
│   ├── pom.xml
│   ├── README.md
│   └── mvnw / mvnw.cmd
│
├── 📁 frontend/                   # Aplicación React
│   ├── src/
│   │   ├── components/            # Componentes React
│   │   ├── pages/                 # Páginas/Vistas
│   │   ├── hooks/                 # Custom Hooks
│   │   ├── api/                   # Cliente HTTP
│   │   ├── utils/                 # Utilidades
│   │   ├── styles/                # Estilos globales
│   │   ├── assets/                # Recursos estáticos
│   │   ├── App.tsx
│   │   └── main.tsx
│   ├── public/                    # Assets públicos
│   ├── index.html
│   ├── package.json
│   ├── vite.config.ts
│   ├── tsconfig.json
│   ├── README.md
│   └── .eslintrc.cjs
│
├── .gitignore
└── README.md                      # Este archivo
```

---

## 🚀 Inicio Rápido

### ✅ Requisitos Previos

**Backend:**
- Java 17+
- Maven 3.6+
- MySQL 5.7+ (ejecutándose)

**Frontend:**
- Node.js 16+
- npm 7+

### 1️⃣ Base de Datos

```bash
# Crear BD e importar datos
mysql -u root -p < backend/database.sql
```

### 2️⃣ Backend (Terminal 1)

```bash
cd backend
mvn spring-boot:run
```

✅ API en: `http://localhost:8080`

### 3️⃣ Frontend (Terminal 2)

```bash
cd frontend
npm install
npm run dev
```

✅ App en: `http://localhost:5173`

---

## 🛠️ Tecnologías

### Backend
| Tech | Versión |
|------|---------|
| Spring Boot | 3.2.0 |
| Java | 17 |
| MySQL | 8.0 |
| JPA/Hibernate | Auto |
| Maven | 3.6+ |

### Frontend
| Tech | Versión |
|------|---------|
| React | 18+ |
| TypeScript | Latest |
| Vite | 5+ |
| Tailwind CSS | Latest |
| Radix UI | Latest |

---

## 📚 Documentación

- **Backend**: Ver `backend/README.md`
- **Frontend**: Ver `frontend/README.md`

---

## 🗄️ Base de Datos

**Tablas principales:**
- `usuarios` - Usuarios registrados
- `articulos` - Artículos para intercambiar
- `intercambios` - Transacciones de intercambio
- `categorias` - Plataformas, tipos, idiomas, regiones
- `valoraciones` - Reseñas entre usuarios

**Archivo**: `backend/database.sql`

---

## 📊 API Endpoints (Ejemplos)

```
GET    /api/articulos              Listar artículos
GET    /api/articulos/{id}         Obtener artículo
POST   /api/articulos              Crear artículo
PUT    /api/articulos/{id}         Actualizar
DELETE /api/articulos/{id}         Eliminar

GET    /api/usuarios               Listar usuarios
POST   /api/usuarios/auth/login    Iniciar sesión

GET    /api/intercambios           Listar intercambios
POST   /api/intercambios           Crear intercambio
```

---

## ⚙️ Configuración

### Backend (application.properties)

```properties
# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/looteria
spring.datasource.username=root
spring.datasource.password=

# Server
server.port=8080

# Hibernate
spring.jpa.hibernate.ddl-auto=update
```

### Frontend (.env.local)

```env
VITE_API_BASE_URL=http://localhost:8080
VITE_APP_NAME=Looteria
```

---

## 🐛 Solución de Problemas

### Backend no inicia
```
❌ Error: Connection refused

✅ Soluciones:
- Verifica MySQL esté corriendo
- Comprueba usuario/contraseña
- BD 'looteria' debe existir
```

### Frontend no compila
```
❌ Error: npm command not found

✅ Soluciones:
- Instala Node.js: https://nodejs.org
- Verifica: node --version && npm --version
```

### Puerto ocupado
```bash
# Backend: Cambiar en application.properties
server.port=8081

# Frontend: Vite elige puerto automáticamente
```

---

## 📦 Scripts

### Backend
```bash
mvn clean compile          # Compilar
mvn test                   # Tests
mvn spring-boot:run        # Ejecutar
mvn clean package          # Empaquetar JAR
```

### Frontend
```bash
npm install                # Instalar dependencias
npm run dev                # Desarrollo
npm run build              # Compilar
npm run lint               # Verificar código
```

---

## 🔄 Hot Reload

- **Backend**: Requiere reiniciar (usar Spring Boot DevTools si deseas hot reload)
- **Frontend**: Automático con Vite

---

## 📝 Notas

- Todos los archivos de configuración están en su carpeta correspondiente
- Estructura limpia y escalable
- Listo para desarrollo y producción

---

## 👨‍💻 Desarrollo

El proyecto está estructurado para ser fácil de mantener y escalar:

```
backend/  → Backend independiente
frontend/ → Frontend independiente
```

Cada uno tiene su propio:
- Configuración (pom.xml / package.json)
- README.md con instrucciones específicas
- .gitignore
- Estructura de carpetas estándar
```

---

**Looteria** - Plataforma de Intercambio de Videojuegos 🎮
