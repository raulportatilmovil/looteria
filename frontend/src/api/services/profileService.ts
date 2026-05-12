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

export interface Review {
  idResena: number;
  usuarioRevisor: string;
  calificacion: number;
  comentario: string;
  fecha: string;
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

  createListing: async (userId: number, listingData: any): Promise<any> => {
    const payload = {
      userId,
      producto: listingData.producto,
      descripcion: listingData.descripcion,
      plataforma: listingData.plataforma,
      tipoTransaccion: listingData.tipoTransaccion,
      precio: listingData.precio,
      estado: listingData.estado,
      descripcionEstado: listingData.descripcionEstado,
      especificaciones: listingData.especificaciones,
      idioma: listingData.idioma,
      region: listingData.region,
    };
    const response = await axiosInstance.post('/publicaciones', payload);
    return response.data;
  },

  getListingDetail: async (listingId: number): Promise<any> => {
    const response = await axiosInstance.get(`/publicaciones/${listingId}`);
    return response.data;
  },

  getListingReviews: async (listingId: number): Promise<Review[]> => {
    const response = await axiosInstance.get<Review[]>(`/publicaciones/${listingId}/resenas`);
    return response.data;
  },

  addReview: async (listingId: number, autorId: number, puntuacion: number, comentario: string): Promise<any> => {
    const response = await axiosInstance.post(`/publicaciones/${listingId}/resenas`, { autorId, puntuacion, comentario });
    return response.data;
  },

  getReceivedReviews: async (userId: number): Promise<Review[]> => {
    const response = await axiosInstance.get<Review[]>(`/perfil/resenas-recibidas/${userId}`);
    return response.data;
  },

  deleteReview: async (reviewId: number): Promise<any> => {
    const response = await axiosInstance.delete(`/resenas/${reviewId}`);
    return response.data;
  },

  getActiveListings: async (): Promise<any[]> => {
    const response = await axiosInstance.get('/publicaciones/activas');
    return response.data;
  },

  // ─── Transaction endpoints ────────────────────────────────────────────────────

  createTransaction: async (publicacionId: number, compradorId: number, vendedorId: number, tipo: string, precioFinal: number): Promise<any> => {
    const response = await axiosInstance.post('/transacciones', {
      publicacionId,
      compradorId,
      vendedorId,
      tipo,
      precioFinal,
    });
    return response.data;
  },

  getTransactionById: async (transactionId: number): Promise<any> => {
    const response = await axiosInstance.get(`/transacciones/${transactionId}`);
    return response.data;
  },

  getTransactionsByBuyer: async (compradorId: number): Promise<any[]> => {
    const response = await axiosInstance.get(`/transacciones/comprador/${compradorId}`);
    return response.data;
  },

  getTransactionsBySeller: async (vendedorId: number): Promise<any[]> => {
    const response = await axiosInstance.get(`/transacciones/vendedor/${vendedorId}`);
    return response.data;
  },

  updateTransactionStatus: async (transactionId: number, estado: string): Promise<any> => {
    const response = await axiosInstance.put(`/transacciones/${transactionId}/estado`, {
      estado,
    });
    return response.data;
  },

  // ─── Exchange endpoints ──────────────────────────────────────────────────────

  createExchange: async (publicacionId: number, solicitanteId: number, mensaje: string): Promise<any> => {
    const response = await axiosInstance.post('/intercambios', {
      publicacionId,
      solicitanteId,
      mensaje,
    });
    return response.data;
  },

  getExchangeById: async (exchangeId: number): Promise<any> => {
    const response = await axiosInstance.get(`/intercambios/${exchangeId}`);
    return response.data;
  },

  getExchangesBySolicitante: async (solicitanteId: number): Promise<any[]> => {
    const response = await axiosInstance.get(`/intercambios/solicitante/${solicitanteId}`);
    return response.data;
  },

  getExchangesBySolicitado: async (solicitadoId: number): Promise<any[]> => {
    const response = await axiosInstance.get(`/intercambios/solicitado/${solicitadoId}`);
    return response.data;
  },

  getExchangesByPublicacion: async (publicacionId: number): Promise<any[]> => {
    const response = await axiosInstance.get(`/intercambios/publicacion/${publicacionId}`);
    return response.data;
  },

  updateExchangeStatus: async (exchangeId: number, estado: string): Promise<any> => {
    const response = await axiosInstance.put(`/intercambios/${exchangeId}/estado`, {
      estado,
    });
    return response.data;
  },
};

export default profileService;
