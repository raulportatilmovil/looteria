import axiosInstance from '../axiosConfig';

export interface UserDTO {
  idUsuario: number;
  email: string;
  nombreUsuario: string;
  rol: string;
  fechaRegistro: string;
  ubicacion: string;
  puntosAcumulados: number;
  verificadoIdentidad: boolean;
  reputacionMedia: number;
}

export interface ListingDetailDTO {
  idPublicacion: number;
  idUsuario: number;
  nombreUsuario: string;
  email: string;
  titulo: string;
  descripcion: string;
  plataforma: string;
  tipoArticulo: string;
  tipoTransaccion: string;
  precio: number;
  estadoArticulo: string;
  descripcionEstado: string;
  idioma: string;
  region: string;
  fechaCreacion: string;
  estadoPublicacion: string;
  envio: boolean;
}

export const adminService = {
  // USUARIOS
  getAllUsers: async () => {
    return await axiosInstance.get<UserDTO[]>('/admin/usuarios');
  },

  getUserById: async (id: number) => {
    return await axiosInstance.get<UserDTO>(`/admin/usuarios/${id}`);
  },

  deleteUser: async (id: number) => {
    return await axiosInstance.delete(`/admin/usuarios/${id}`);
  },

  // PUBLICACIONES
  getAllListings: async () => {
    return await axiosInstance.get<ListingDetailDTO[]>('/admin/publicaciones');
  },

  getListingsByUser: async (userId: number) => {
    return await axiosInstance.get<ListingDetailDTO[]>(`/admin/publicaciones/usuario/${userId}`);
  },

  getListingById: async (id: number) => {
    return await axiosInstance.get<ListingDetailDTO>(`/admin/publicaciones/${id}`);
  },

  deleteListing: async (id: number) => {
    return await axiosInstance.delete(`/admin/publicaciones/${id}`);
  }
};

export default adminService;
