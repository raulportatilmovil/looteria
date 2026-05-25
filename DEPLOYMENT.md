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

3. **Crear proyecto en Railway**
   - En Railway, haz clic en "New Project"
   - Selecciona "Deploy from GitHub repo"
   - Elige tu repositorio `looteria`

4. **Configurar servicios en Railway**

   **Servicio MySQL:**
   - Añade un servicio "MySQL"
   - Railway generará automáticamente las variables de entorno

   **Servicio Backend:**
   - Añade un servicio desde tu repositorio
   - Configura:
     - **Root directory**: `backend`
     - **Build command**: `mvn clean package -DskipTests`
     - **Start command**: `java -jar target/*.jar`
   - Añade variables de entorno (copia del servicio MySQL):
     ```
     SPRING_DATASOURCE_URL=jdbc:mysql://<mysql-host>:3306/looteria
     SPRING_DATASOURCE_USERNAME=<mysql-user>
     SPRING_DATASOURCE_PASSWORD=<mysql-password>
     SPRING_JPA_HIBERNATE_DDL_AUTO=update
     ```

   **Servicio Frontend:**
   - Añade un servicio desde tu repositorio
   - Configura:
     - **Root directory**: `frontend`
     - **Build command**: `npm run build`
     - **Start command**: `npm start -p 80`
   - Añade variable de entorno:
     ```
     VITE_API_URL=https://<tu-backend-url>.railway.app/api
     ```
   - **Importante**: La URL del backend la obtienes en Railway después de desplegar el backend (aparece en la pestaña del servicio backend, campo "Domain")

5. **Ejecutar script SQL**
   - Conéctate a la base de datos MySQL en Railway
   - Ejecuta el contenido de `backend/database.sql`

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
