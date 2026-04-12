import axios from 'axios';

// Configuración del cliente HTTP para comunicarse con el backend
const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8081';

const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  // DESARROLLO: withCredentials: false (permite CORS con wildcard *)
  withCredentials: false,
  // PRODUCCIÓN (AZURE): withCredentials: true (para JWT tokens seguros)
  // withCredentials: true,
});

export default axiosInstance;
