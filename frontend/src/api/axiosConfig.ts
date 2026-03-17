import axios from 'axios';

// Configuración del cliente HTTP para comunicarse con el backend
const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8081/api';

const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
  withCredentials: true, // Habilitar para JWT tokens
});

export default axiosInstance;
