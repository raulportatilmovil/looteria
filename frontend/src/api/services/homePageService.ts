import axiosInstance from '../axiosConfig';

export interface HomePageListingDTO {
  idPublicacion: number;
  idProducto: number | null;
  titulo: string;
  descripcion: string;
  plataformaNombre: string;
  tipoArticuloNombre: string;
  precio: number;
  estadoArticuloNombre: string;
  tipoTransaccion: string;
  regionNombre: string;
  nombreUsuario: string;
  descripcionEstado: string;
  idiomaNombre: string;
  fechaCreacion: string;
  destacado: boolean;
  imagenes: string[];
}

export const homePageService = {
  getFeaturedListings: async () => {
    return await axiosInstance.get<HomePageListingDTO[]>('/home/featured');
  },

  getRecentListings: async (limit: number = 6) => {
    return await axiosInstance.get<HomePageListingDTO[]>('/home/recent', { params: { limit } });
  },

  getPopularListings: async (limit: number = 4) => {
    return await axiosInstance.get<HomePageListingDTO[]>('/home/popular', { params: { limit } });
  },

  toggleFeatured: async (id: number) => {
    return await axiosInstance.post(`/home/listings/${id}/toggle-featured`);
  }
};

export default homePageService;
