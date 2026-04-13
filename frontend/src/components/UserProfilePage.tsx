import { useState, useEffect } from "react";
import { User, Mail, MapPin, Star, Package, ShoppingBag, Truck, Trophy, Edit, Trash2, Plus } from "lucide-react";
import { Footer } from "./Footer";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "./ui/tabs";
import { Badge } from "./ui/badge";
import { Button } from "./ui/button";
import { useAuth } from "../context/AuthContext";
import profileService from "../api/services/profileService";

interface UserProfilePageProps {
  onNavigate: (page: string, gameId?: string) => void;
  userRole?: "guest" | "registered" | "admin";
}

export function UserProfilePage({ onNavigate, userRole = "registered" }: UserProfilePageProps) {
  const { user } = useAuth();
  const [profile, setProfile] = useState<any>(null);
  const [myListings, setMyListings] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);
  const [editingProfile, setEditingProfile] = useState(false);
  const [profileData, setProfileData] = useState({ nombreUsuario: "", ubicacion: "" });

  useEffect(() => {
    if (user?.idUsuario) {
      loadProfileData();
    }
  }, [user]);

  const loadProfileData = async () => {
    try {
      const data = await profileService.getProfileData(user!.idUsuario!);
      setProfile(data);
      setProfileData({ nombreUsuario: data.nombreUsuario, ubicacion: data.ubicacion || "" });

      const listings = await profileService.getUserListings(user!.idUsuario!);
      setMyListings(listings);
    } catch (error) {
      console.error("Error loading profile:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleUpdateProfile = async () => {
    try {
      await profileService.updateProfile(user!.idUsuario!, profileData);
      setProfile({ ...profile, ...profileData });
      setEditingProfile(false);
      alert("Perfil actualizado correctamente");
    } catch (error) {
      alert("Error al actualizar el perfil");
    }
  };

  const handleDeleteListing = async (listingId: number) => {
    if (confirm("¿Estás seguro de que quieres eliminar esta publicación?")) {
      try {
        await profileService.deleteListing(listingId);
        setMyListings(myListings.filter(l => l.idPublicacion !== listingId));
        alert("Publicación eliminada");
      } catch (error) {
        alert("Error al eliminar la publicación");
      }
    }
  };

  // Mock data para otras funcionalidades
  const transactions = [
    {
      id: "t1",
      type: "sale",
      product: "Elden Ring - PS5",
      buyer: "Juan M.",
      amount: 35,
      status: "completed",
      date: "15 Mar 2026",
    },
    {
      id: "t2",
      type: "purchase",
      product: "Silent Hill 2 Remake - PC",
      seller: "Laura P.",
      amount: 45,
      status: "completed",
      date: "12 Mar 2026",
    },
    {
      id: "t3",
      type: "exchange",
      product: "Zelda BOTW ↔️ Mario Kart 8",
      user: "Pedro Sánchez",
      amount: 0,
      status: "completed",
      date: "10 Mar 2026",
    },
  ];

  const shipments = [
    {
      id: "s1",
      trackingNumber: "ES2026031500123",
      carrier: "Correos Express",
      product: "Cyberpunk 2077 - PC",
      status: "in_transit",
      estimatedDelivery: "18 Mar 2026",
    },
    {
      id: "s2",
      trackingNumber: "ES2026031200456",
      carrier: "SEUR",
      product: "FIFA 24 - PS5",
      status: "delivered",
      deliveredDate: "16 Mar 2026",
    },
  ];

  const reviews = [
    {
      id: "r1",
      from: "María G.",
      rating: 5,
      comment: "Excelente vendedor, producto en perfecto estado.",
      product: "The Last of Us Part II",
      date: "10 Mar 2026",
    },
    {
      id: "r2",
      from: "Pedro R.",
      rating: 4,
      comment: "Todo correcto, envío rápido.",
      product: "God of War",
      date: "5 Mar 2026",
    },
  ];

  const getStatusBadge = (status: string) => {
    switch (status) {
      case "active":
        return <Badge className="bg-green-100 text-green-700 border-0">Activo</Badge>;
      case "sold":
        return <Badge className="bg-gray-100 text-gray-700 border-0">Vendido</Badge>;
      case "pending":
        return <Badge className="bg-yellow-100 text-yellow-700 border-0">Pendiente</Badge>;
      case "hidden":
        return <Badge className="bg-red-100 text-red-700 border-0">Oculto</Badge>;
      default:
        return <Badge className="bg-gray-100 text-gray-700 border-0">{status}</Badge>;
    }
  };

  const getTransactionStatus = (status: string) => {
    switch (status) {
      case "completed":
        return <Badge className="bg-green-100 text-green-700 border-0">Completado</Badge>;
      case "in_transit":
        return <Badge className="bg-blue-100 text-blue-700 border-0">En tránsito</Badge>;
      case "pending":
        return <Badge className="bg-yellow-100 text-yellow-700 border-0">Pendiente</Badge>;
      default:
        return <Badge className="bg-gray-100 text-gray-700 border-0">{status}</Badge>;
    }
  };

  const getShipmentStatus = (status: string) => {
    switch (status) {
      case "delivered":
        return <Badge className="bg-green-100 text-green-700 border-0">Entregado</Badge>;
      case "in_transit":
        return <Badge className="bg-blue-100 text-blue-700 border-0">En tránsito</Badge>;
      case "preparing":
        return <Badge className="bg-yellow-100 text-yellow-700 border-0">Preparando</Badge>;
      default:
        return <Badge className="bg-gray-100 text-gray-700 border-0">{status}</Badge>;
    }
  };

  if (loading) {
    return <div className="min-h-screen pt-20 flex items-center justify-center">Cargando perfil...</div>;
  }

  if (!profile) {
    return <div className="min-h-screen pt-20 flex items-center justify-center">Error al cargar perfil</div>;
  }

  return (
    <div className="min-h-screen bg-gray-50 pt-20">
      {/* Edit Profile Modal */}
      {editingProfile && (
        <div className="fixed inset-0 bg-black/50 z-50 flex items-center justify-center p-4">
          <div className="bg-white rounded-lg p-6 max-w-md w-full">
            <h2 className="text-2xl font-bold mb-4">Editar perfil</h2>
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Nombre de usuario
                </label>
                <input
                  type="text"
                  value={profileData.nombreUsuario}
                  onChange={(e) => setProfileData({...profileData, nombreUsuario: e.target.value})}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Ubicación
                </label>
                <input
                  type="text"
                  value={profileData.ubicacion}
                  onChange={(e) => setProfileData({...profileData, ubicacion: e.target.value})}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                />
              </div>
              <div className="flex gap-2 pt-4">
                <Button
                  className="flex-1 bg-primary hover:bg-primary/90"
                  onClick={handleUpdateProfile}
                >
                  Guardar
                </Button>
                <Button
                  className="flex-1"
                  variant="outline"
                  onClick={() => setEditingProfile(false)}
                >
                  Cancelar
                </Button>
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Header */}
      <div className="bg-gradient-to-r from-primary to-blue-600 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex flex-col md:flex-row items-center gap-6">
            {/* Avatar */}
            <div className="w-32 h-32 rounded-full bg-white flex items-center justify-center text-5xl font-bold text-primary border-4 border-white shadow-xl">
              {profile.nombreUsuario?.charAt(0).toUpperCase() || "U"}
            </div>

            {/* Info */}
            <div className="flex-1 text-center md:text-left">
              <div className="flex items-center gap-3 justify-center md:justify-start mb-2">
                <h1 className="text-white text-4xl font-bold">{profile.nombreUsuario || "Usuario"}</h1>
                {profile.verificadoIdentidad && (
                  <Badge className="bg-white/20 text-white border-white/30">
                    ✓ Verificado
                  </Badge>
                )}
              </div>
              <div className="flex flex-col md:flex-row items-center gap-4 text-white/90 mb-3">
                <div className="flex items-center gap-2">
                  <Mail className="w-4 h-4" />
                  <span>{profile.email}</span>
                </div>
                <div className="flex items-center gap-2">
                  <MapPin className="w-4 h-4" />
                  <span>{profile.ubicacion || "Sin ubicación"}</span>
                </div>
              </div>
            </div>

            {/* Edit Button */}
            <Button
              onClick={() => setEditingProfile(true)}
              className="!bg-transparent border-2 border-white text-white hover:bg-white hover:text-primary font-semibold px-6 py-2"
            >
              <Edit className="w-4 h-4 mr-2" />
              Editar perfil
            </Button>
          </div>

          {/* Stats */}
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mt-8">
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-4 border border-white/20">
              <div className="flex items-center gap-3">
                <Trophy className="w-8 h-8 text-yellow-300" />
                <div>
                  <div className="text-2xl font-bold text-white">{profile.puntosAcumulados || 0}</div>
                  <div className="text-sm text-white/80">Puntos</div>
                </div>
              </div>
            </div>
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-4 border border-white/20">
              <div className="flex items-center gap-3">
                <Star className="w-8 h-8 text-yellow-300" />
                <div>
                  <div className="text-2xl font-bold text-white">{(profile.reputacionMedia || 0).toFixed(2)}</div>
                  <div className="text-sm text-white/80">Reputación</div>
                </div>
              </div>
            </div>
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-4 border border-white/20">
              <div className="flex items-center gap-3">
                <ShoppingBag className="w-8 h-8 text-white" />
                <div>
                  <div className="text-2xl font-bold text-white">{myListings.filter(l => l.estadoPublicacion === "VENDIDA").length}</div>
                  <div className="text-sm text-white/80">Ventas</div>
                </div>
              </div>
            </div>
            <div className="bg-white/10 backdrop-blur-sm rounded-xl p-4 border border-white/20">
              <div className="flex items-center gap-3">
                <Package className="w-8 h-8 text-white" />
                <div>
                  <div className="text-2xl font-bold text-white">{myListings.length}</div>
                  <div className="text-sm text-white/80">Publicaciones</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Content */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <Tabs defaultValue="listings" className="w-full">
          <TabsList className="grid w-full grid-cols-5 mb-8">
            <TabsTrigger value="listings">Mis publicaciones</TabsTrigger>
            <TabsTrigger value="transactions">Transacciones</TabsTrigger>
            <TabsTrigger value="shipments">Envíos</TabsTrigger>
            <TabsTrigger value="reviews">Reseñas</TabsTrigger>
            <TabsTrigger value="points">Puntos</TabsTrigger>
          </TabsList>

          {/* My Listings */}
          <TabsContent value="listings" className="space-y-4">
            <div className="flex justify-between items-center mb-6">
              <h2 className="text-2xl font-bold text-gray-900">Mis publicaciones</h2>
              <Button
                onClick={() => onNavigate("create-listing")}
                className="bg-primary hover:bg-primary/90"
              >
                Crear nueva publicación
              </Button>
            </div>

            {myListings.length === 0 ? (
              <div className="text-center py-12 bg-white rounded-lg border border-gray-200">
                <Package className="w-12 h-12 text-gray-400 mx-auto mb-3" />
                <p className="text-gray-600">No tienes publicaciones aún</p>
              </div>
            ) : (
              <div className="grid gap-4">
                {myListings.map((listing) => (
                  <div
                    key={listing.idPublicacion}
                    className="bg-white rounded-xl border border-gray-200 p-6 hover:shadow-lg transition-shadow"
                  >
                    <div className="flex gap-6">
                      <div className="w-32 h-32 bg-gray-200 rounded-lg flex items-center justify-center flex-shrink-0">
                        <ShoppingBag className="w-8 h-8 text-gray-400" />
                      </div>
                      <div className="flex-1">
                        <div className="flex items-start justify-between mb-2">
                          <div>
                            <h3 className="text-xl font-semibold text-gray-900 mb-1">
                              {listing.producto}
                            </h3>
                            <p className="text-gray-600 text-sm">
                              {listing.tipoTransaccion}
                            </p>
                          </div>
                          <Badge className={
                            listing.estadoPublicacion === "ACTIVA" 
                              ? "bg-green-100 text-green-700 border-0"
                              : listing.estadoPublicacion === "VENDIDA"
                              ? "bg-gray-100 text-gray-700 border-0"
                              : "bg-red-100 text-red-700 border-0"
                          }>
                            {listing.estadoPublicacion}
                          </Badge>
                        </div>
                        <div className="flex items-center gap-6 text-sm text-gray-600 mb-4">
                          <span className="text-2xl font-bold text-primary">{listing.precio}€</span>
                          <span>{new Date(listing.fechaCreacion.split('T')[0]).toLocaleDateString('es-ES')}</span>
                        </div>
                        <p className="text-gray-600 text-sm mb-4">{listing.descripcionEstado}</p>
                        <div className="flex gap-2">
                          <Button
                            variant="outline"
                            size="sm"
                            onClick={() => onNavigate("game", listing.idPublicacion)}
                          >
                            Ver publicación
                          </Button>
                          <Button variant="outline" size="sm">
                            Editar
                          </Button>
                          <Button
                            variant="outline"
                            size="sm"
                            className="text-red-600 hover:text-red-700"
                            onClick={() => handleDeleteListing(listing.idPublicacion)}
                          >
                            Eliminar
                          </Button>
                        </div>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </TabsContent>

          {/* Transactions */}
          <TabsContent value="transactions" className="space-y-4">
            <h2 className="text-2xl font-bold text-gray-900 mb-6">Historial de transacciones</h2>
            <div className="bg-white rounded-xl border border-gray-200 overflow-hidden">
              <div className="overflow-x-auto">
                <table className="w-full">
                  <thead className="bg-gray-50 border-b border-gray-200">
                    <tr>
                      <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                        Tipo
                      </th>
                      <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                        Producto
                      </th>
                      <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                        Usuario
                      </th>
                      <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                        Importe
                      </th>
                      <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                        Estado
                      </th>
                      <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">
                        Fecha
                      </th>
                    </tr>
                  </thead>
                  <tbody className="divide-y divide-gray-200">
                    {transactions.map((transaction) => (
                      <tr key={transaction.id} className="hover:bg-gray-50">
                        <td className="px-6 py-4 whitespace-nowrap">
                          <Badge
                            className={
                              transaction.type === "sale"
                                ? "bg-green-100 text-green-700 border-0"
                                : transaction.type === "purchase"
                                ? "bg-blue-100 text-blue-700 border-0"
                                : "bg-purple-100 text-purple-700 border-0"
                            }
                          >
                            {transaction.type === "sale"
                              ? "Venta"
                              : transaction.type === "purchase"
                              ? "Compra"
                              : "Intercambio"}
                          </Badge>
                        </td>
                        <td className="px-6 py-4 text-sm text-gray-900">{transaction.product}</td>
                        <td className="px-6 py-4 text-sm text-gray-600">
                          {transaction.type === "sale"
                            ? transaction.buyer
                            : transaction.type === "purchase"
                            ? transaction.seller
                            : transaction.user}
                        </td>
                        <td className="px-6 py-4 text-sm font-medium text-gray-900">
                          {transaction.amount > 0 ? `${transaction.amount}€` : "-"}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                          {getTransactionStatus(transaction.status)}
                        </td>
                        <td className="px-6 py-4 text-sm text-gray-600">{transaction.date}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </TabsContent>

          {/* Shipments */}
          <TabsContent value="shipments" className="space-y-4">
            <h2 className="text-2xl font-bold text-gray-900 mb-6">Mis envíos</h2>
            <div className="grid gap-4">
              {shipments.map((shipment) => (
                <div
                  key={shipment.id}
                  className="bg-white rounded-xl border border-gray-200 p-6"
                >
                  <div className="flex items-start justify-between mb-4">
                    <div className="flex items-center gap-3">
                      <Truck className="w-8 h-8 text-primary" />
                      <div>
                        <h3 className="font-semibold text-gray-900">{shipment.product}</h3>
                        <p className="text-sm text-gray-600">
                          {shipment.carrier} • {shipment.trackingNumber}
                        </p>
                      </div>
                    </div>
                    {getShipmentStatus(shipment.status)}
                  </div>
                  <div className="flex items-center justify-between">
                    <div className="text-sm text-gray-600">
                      {shipment.status === "delivered"
                        ? `Entregado el ${shipment.deliveredDate}`
                        : `Entrega estimada: ${shipment.estimatedDelivery}`}
                    </div>
                    <Button
                      variant="outline"
                      size="sm"
                      onClick={() => onNavigate("tracking")}
                    >
                      Rastrear envío
                    </Button>
                  </div>
                </div>
              ))}
            </div>
          </TabsContent>

          {/* Reviews */}
          <TabsContent value="reviews" className="space-y-4">
            <h2 className="text-2xl font-bold text-gray-900 mb-6">Mis reseñas recibidas</h2>
            <div className="grid gap-4">
              {reviews.map((review) => (
                <div
                  key={review.id}
                  className="bg-white rounded-xl border border-gray-200 p-6"
                >
                  <div className="flex items-start gap-4">
                    <div className="w-12 h-12 bg-gray-200 rounded-full flex items-center justify-center">
                      <User className="w-6 h-6 text-gray-600" />
                    </div>
                    <div className="flex-1">
                      <div className="flex items-center justify-between mb-2">
                        <h4 className="font-semibold text-gray-900">{review.from}</h4>
                        <span className="text-sm text-gray-500">{review.date}</span>
                      </div>
                      <div className="flex items-center gap-1 mb-2">
                        {[...Array(5)].map((_, i) => (
                          <Star
                            key={i}
                            className={`w-4 h-4 ${
                              i < review.rating
                                ? "text-yellow-400 fill-yellow-400"
                                : "text-gray-300"
                            }`}
                          />
                        ))}
                      </div>
                      <p className="text-gray-700 mb-1">{review.comment}</p>
                      <p className="text-sm text-gray-500">Producto: {review.product}</p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </TabsContent>

          {/* Points */}
          <TabsContent value="points" className="space-y-6">
            <div className="bg-gradient-to-r from-yellow-400 to-orange-500 rounded-xl p-8 text-white">
              <h2 className="text-3xl font-bold mb-2">Tus puntos Looteria</h2>
              <div className="text-6xl font-bold mb-4">{profile.puntosAcumulados || 0}</div>
              <p className="text-white/90">
                Gana puntos con cada venta y canjéalos por descuentos y beneficios exclusivos
              </p>
            </div>

            <div className="bg-white rounded-xl border border-gray-200 p-6">
              <h3 className="text-xl font-bold text-gray-900 mb-4">Cómo ganar puntos</h3>
              <div className="space-y-3">
                <div className="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                  <div className="flex items-center gap-3">
                    <ShoppingBag className="w-5 h-5 text-primary" />
                    <span className="text-gray-700">Por cada venta completada</span>
                  </div>
                  <span className="font-bold text-primary">+50 puntos</span>
                </div>
                <div className="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                  <div className="flex items-center gap-3">
                    <Star className="w-5 h-5 text-primary" />
                    <span className="text-gray-700">Por cada reseña positiva recibida</span>
                  </div>
                  <span className="font-bold text-primary">+25 puntos</span>
                </div>
                <div className="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
                  <div className="flex items-center gap-3">
                    <User className="w-5 h-5 text-primary" />
                    <span className="text-gray-700">Verificación de identidad</span>
                  </div>
                  <span className="font-bold text-primary">+100 puntos</span>
                </div>
              </div>
            </div>

            <div className="bg-white rounded-xl border border-gray-200 p-6">
              <h3 className="text-xl font-bold text-gray-900 mb-4">Canjear puntos</h3>
              <div className="grid md:grid-cols-2 gap-4">
                <div className="border-2 border-gray-200 rounded-lg p-4 hover:border-primary transition-colors cursor-pointer">
                  <div className="text-lg font-bold text-gray-900 mb-2">500 puntos</div>
                  <div className="text-gray-600 mb-3">Descuento 5€ en tu próxima compra</div>
                  <Button className="w-full" variant="outline">
                    Canjear
                  </Button>
                </div>
                <div className="border-2 border-gray-200 rounded-lg p-4 hover:border-primary transition-colors cursor-pointer">
                  <div className="text-lg font-bold text-gray-900 mb-2">1000 puntos</div>
                  <div className="text-gray-600 mb-3">Envío gratis en tu próximo pedido</div>
                  <Button className="w-full" variant="outline">
                    Canjear
                  </Button>
                </div>
              </div>
            </div>
          </TabsContent>
        </Tabs>
      </div>

      <Footer onNavigate={onNavigate} />
    </div>
  );
}
