import axiosInstance from '../axiosConfig';

export interface UserProfile {
  idUsuario: number;
  email: string;
  nombreUsuario: string;
  ubicacion?: string;
  rol: string;
  puntosAcumulados: number;
  reputacionMedia: number;
  verificadoIdentidad: boolean;
  fechaRegistro: string;
}

export interface ListingItem {
  idPublicacion: number;
  producto: string;
  tipoTransaccion: string;
  precio: number;
  estadoPublicacion: string;
  descripcionEstado: string;
  fechaPublicacion: string;
}

export const profileService = {
  getProfileData: async (userId: number): Promise<UserProfile> => {
    const response = await axiosInstance.get<UserProfile>(`/perfil/datos/${userId}`);
    return response.data;
  },

  updateProfile: async (userId: number, data: { nombreUsuario?: string; ubicacion?: string }): Promise<any> => {
    const response = await axiosInstance.put(`/perfil/actualizar/${userId}`, data);
    return response.data;
  },

  getUserListings: async (userId: number): Promise<ListingItem[]> => {
    const response = await axiosInstance.get<ListingItem[]>(`/perfil/mis-publicaciones/${userId}`);
    return response.data;
  },

  deleteListing: async (listingId: number): Promise<any> => {
    const response = await axiosInstance.delete(`/perfil/publicaciones/${listingId}`);
    return response.data;
  },

  updateListing: async (listingId: number, data: any): Promise<any> => {
    const response = await axiosInstance.put(`/perfil/publicaciones/${listingId}`, data);
    return response.data;
  },
};

export default profileService;
