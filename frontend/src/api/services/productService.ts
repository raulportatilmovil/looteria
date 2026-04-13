import axiosInstance from '../axiosConfig';

export interface Product {
  idProducto?: number;
  titulo: string;
  descripcion: string;
  valorEstimado: number;
  fechaLanzamiento?: string;
  plataforma?: { idCategoria: number; nombre: string } | null;
  tipoArticulo?: { idCategoria: number; nombre: string } | null;
  franquicia?: { idCategoria: number; nombre: string } | null;
  fechaCreacion?: string;
}

export interface SearchListing {
  idPublicacion: number;
  idProducto: number;
  titulo: string;
  descripcion: string;
  plataforma: string;
  tipoArticulo: string;
  franquicia: string | null;
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
  // Obtener todos los productos
  getAllProducts: async () => {
    return await axiosInstance.get('/productos');
  },

  // Obtener producto por ID
  getProductById: async (id: number) => {
    return await axiosInstance.get(`/productos/${id}`);
  },

  // Buscar por título
  searchByTitle: async (titulo: string) => {
    return await axiosInstance.get(`/productos/buscar/titulo?titulo=${titulo}`);
  },

  // Filtrar por plataforma
  getByPlatform: async (plataformaId: number) => {
    return await axiosInstance.get(`/productos/plataforma/${plataformaId}`);
  },

  // Filtrar por tipo de artículo
  getByType: async (tipoId: number) => {
    return await axiosInstance.get(`/productos/tipo/${tipoId}`);
  },

  // Filtrar por franquicia
  getByFranchise: async (franquiciaId: number) => {
    return await axiosInstance.get(`/productos/franquicia/${franquiciaId}`);
  },

  // Crear producto
  createProduct: async (producto: Product) => {
    return await axiosInstance.post('/productos', producto);
  },

  // Actualizar producto
  updateProduct: async (id: number, producto: Product) => {
    return await axiosInstance.put(`/productos/${id}`, producto);
  },

  // Eliminar producto
  deleteProduct: async (id: number) => {
    return await axiosInstance.delete(`/productos/${id}`);
  },

  // Obtener total de productos
  getTotalProducts: async () => {
    return await axiosInstance.get('/productos/stats/total');
  },

  // Obtener publicaciones activas (para ExplorePage)
  getActiveListings: async () => {
    return await axiosInstance.get<SearchListing[]>('/publicaciones/activas');
  },
};
