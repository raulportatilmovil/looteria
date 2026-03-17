# Looteria

Plataforma de intercambio de videojuegos.

## Tecnologías
- **Backend**: Spring Boot + Java + MySQL
- **Frontend**: React + TypeScript + Vite

## Requisitos
- Java 17+
- Maven 3.6+
- MySQL 5.7+
- Node.js 16+
- npm 7+

## Quick Start

### 1. Base de Datos
```bash
mysql -u root -p < backend/database.sql
```

### 2. Backend (Terminal 1)
```bash
cd backend
mvn spring-boot:run
```
API en: `http://localhost:8081`

### 3. Frontend (Terminal 2)
```bash
cd frontend
npm install
npm run dev
```
App en: `http://localhost:3000`

## Documentación
- Backend: Ver [backend/README.md](backend/README.md)
- Frontend: Ver [frontend/README.md](frontend/README.md)
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
