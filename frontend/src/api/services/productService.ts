import axiosInstance from '../axiosConfig';

export interface Product {
  idProducto?: number;
  titulo: string;
  descripcion: string;
  valorEstimado: number;
  fechaLanzamiento?: string;
  plataforma?: { idCategoria: number; nombre: string } | null;
  tipoArticulo?: { idCategoria: number; nombre: string } | null;
  fechaCreacion?: string;
}

export interface SearchListing {
  idPublicacion: number;
  idProducto: number;
  titulo: string;
  descripcion: string;
  plataforma: string;
  tipoArticulo: string;
  precio: number;
  estadoArticulo: string;
  tipoTransaccion: string;
  region: string;
  usuario: string;
  descripcionEstado: string | null;
  idioma: string;
  fechaCreacion: string;
}

export const productService = {
  getAllProducts: async () => {
    return await axiosInstance.get('/productos');
  },

  getProductById: async (id: number) => {
    return await axiosInstance.get(`/productos/${id}`);
  },

  searchByTitle: async (titulo: string) => {
    return await axiosInstance.get(`/productos/buscar/titulo?titulo=${titulo}`);
  },

  getByPlatform: async (plataformaId: number) => {
    return await axiosInstance.get(`/productos/plataforma/${plataformaId}`);
  },

  getByType: async (tipoId: number) => {
    return await axiosInstance.get(`/productos/tipo/${tipoId}`);
  },

  createProduct: async (producto: Product) => {
    return await axiosInstance.post('/productos', producto);
  },

  updateProduct: async (id: number, producto: Product) => {
    return await axiosInstance.put(`/productos/${id}`, producto);
  },

  deleteProduct: async (id: number) => {
    return await axiosInstance.delete(`/productos/${id}`);
  },

  getTotalProducts: async () => {
    return await axiosInstance.get('/productos/stats/total');
  },

  getActiveListings: async () => {
    return await axiosInstance.get<SearchListing[]>('/publicaciones/activas');
  },
};
