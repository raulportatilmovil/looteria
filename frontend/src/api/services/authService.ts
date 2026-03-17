import axiosInstance from '../axiosConfig';

export const authService = {
  login: async (email: string, password: string) => {
    const response = await axiosInstance.post('/usuarios/login', {
      email,
      contrasena: password,
    });
    return response.data;
  },

  register: async (email: string, username: string, password: string) => {
    const response = await axiosInstance.post('/usuarios/register', {
      email,
      nombreUsuario: username,
      contrasena: password,
    });
    return response.data;
  },

  logout: () => {
    localStorage.removeItem('authToken');
  },
};

export default authService;
