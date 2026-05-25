# Guía de Despliegue

## Opción 1: Railway (Recomendado - Más Sencillo)

Railway es la opción más sencilla para desplegar tu proyecto. Ofrece un tier gratuito y soporta Docker.

### Pasos:

1. **Crear cuenta en Railway**
   - Ve a [railway.app](https://railway.app)
   - Regístrate con tu cuenta de GitHub

2. **Subir el proyecto a GitHub**
   - Asegúrate de que tu repositorio esté en GitHub
   - Sube los archivos Dockerfile creados
   - El repositorio debe tener la estructura: `backend/`, `frontend/`, etc.

3. **Crear proyecto en Railway**
   - En el dashboard de Railway, haz clic en **"New Project"**
   - Selecciona **"Deploy from GitHub repo"**
   - Si no tienes GitHub vinculado, Railway te pedirá que lo vincules
   - Busca y selecciona tu repositorio `looteria`
   - **IMPORTANTE**: Selecciona **"Add Variables"** en lugar de "Deploy Now" (esto te permite configurar antes de desplegar)

4. **Añadir servicio MySQL**
   - En el Project Canvas, presiona `Ctrl/Cmd + K` o haz clic en **"+ New"**
   - Selecciona **"Database"** → **"MySQL"**
   - Railway creará automáticamente el servicio MySQL
   - Espera a que MySQL termine de desplegarse
   - **Variables de entorno generadas por Railway** (en el servicio MySQL):
     - `MYSQLHOST`
     - `MYSQLPORT`
     - `MYSQLUSER`
     - `MYSQLPASSWORD`
     - `MYSQLDATABASE`
     - `MYSQL_URL`

5. **Añadir servicio Backend**
   - Presiona `Ctrl/Cmd + K` o haz clic en **"+ New"**
   - Selecciona **"GitHub Repo"**
   - Selecciona tu repositorio `looteria`
   - Configura el servicio:
     - **Root directory**: `backend`
     - **Build command**: `mvn clean package -DskipTests`
     - **Start command**: `java -jar target/*.jar`
   - Ve a la pestaña **"Variables"** del servicio backend
   - Añade las siguientes variables de entorno (copia los valores del servicio MySQL):
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}
     SPRING_DATASOURCE_USERNAME=${MYSQLUSER}
     SPRING_DATASOURCE_PASSWORD=${MYSQLPASSWORD}
     SPRING_JPA_HIBERNATE_DDL_AUTO=update
     ```
   - Haz clic en **"Deploy"** para desplegar el backend
   - Espera a que termine de construir y desplegar
   - **Copia la URL del backend** (campo "Domain" en la pestaña del servicio, ej: `https://looteria-backend-production.up.railway.app`)

6. **Añadir servicio Frontend**
   - Presiona `Ctrl/Cmd + K` o haz clic en **"+ New"**
   - Selecciona **"GitHub Repo"**
   - Selecciona tu repositorio `looteria`
   - Configura el servicio:
     - **Root directory**: `frontend`
     - **Build command**: `npm run build`
     - **Start command**: `npm start -p 80`
   - Ve a la pestaña **"Variables"** del servicio frontend
   - Añade la variable de entorno:
     ```
     VITE_API_URL=https://<tu-backend-url>/api
     ```
     (reemplaza `<tu-backend-url>` con la URL que copiaste en el paso 5)
   - Haz clic en **"Deploy"** para desplegar el frontend
   - Espera a que termine de construir y desplegar

7. **Ejecutar script SQL**
   - En el servicio MySQL, haz clic en **"Connect"**
   - Selecciona **"MySQL CLI"** o usa el cliente web de Railway
   - Ejecuta el contenido de `backend/database.sql`
   - Esto creará todas las tablas y datos iniciales

8. **Verificar despliegue**
   - Accede a la URL del frontend (campo "Domain" en el servicio frontend)
   - Verifica que la aplicación funciona correctamente
   - Prueba crear una cuenta, subir una publicación, etc.

## Opción 2: Docker Compose (Local)

Para desplegar localmente con Docker:

```bash
# En la raíz del proyecto
docker-compose up -d
```

Esto iniciará:
- MySQL en puerto 3306
- Backend en puerto 8081
- Frontend en puerto 80

Accede a la aplicación en: `http://localhost`

## Opción 3: Render (Alternativa Gratuita)

Render es otra opción gratuita similar a Railway.

1. **Crear cuenta en [render.com](https://render.com)**
2. **Crear servicio PostgreSQL** (Render usa PostgreSQL por defecto)
3. **Crear servicio Web (Backend)**
   - Conecta tu repositorio de GitHub
   - Directorio: `backend`
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/*.jar`
   - Añade variables de entorno de la base de datos
4. **Crear servicio Web (Frontend)**
   - Directorio: `frontend`
   - Build Command: `npm run build`
   - Start Command: `npm start -p 80`

## Variables de Entorno Necesarias

### Backend:
- `SPRING_DATASOURCE_URL`: URL de conexión a MySQL
- `SPRING_DATASOURCE_USERNAME`: Usuario de base de datos
- `SPRING_DATASOURCE_PASSWORD`: Contraseña de base de datos
- `SPRING_JPA_HIBERNATE_DDL_AUTO`: `update` o `validate`

### Frontend:
- `VITE_API_URL`: URL del backend (ej: `https://tu-backend.railway.app`)

## Notas Importantes

- **Imágenes**: Las imágenes se sirven desde el backend en `/api/uploads/`. Asegúrate de configurar el almacenamiento de archivos correctamente en producción.
- **Base de datos**: En producción, usa una base de datos gestionada (Railway MySQL, Render PostgreSQL) en lugar de SQLite o MySQL local.
- **HTTPS**: Railway y Render proporcionan HTTPS automáticamente.
- **Dominio personalizado**: Puedes configurar un dominio personalizado en Railway o Render.

## Recomendación

**Railway** es la mejor opción por:
- Tier gratuito generoso
- Soporte nativo de MySQL
- Integración perfecta con GitHub
- Configuración sencilla
- HTTPS automático
- Dominios gratuitos
