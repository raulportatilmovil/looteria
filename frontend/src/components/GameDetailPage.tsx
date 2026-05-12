import { useState, useEffect, useCallback } from "react";
import { ArrowLeft, MapPin, Star, User, Calendar, ShoppingCart, Repeat, ShieldCheck, Heart, Package, Truck } from "lucide-react";
import { useAuth } from "../context/AuthContext";
import { Footer } from "./Footer";
import { Game } from "./GameCard";
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle } from "./ui/dialog";
import { Button } from "./ui/button";
import { Badge } from "./ui/badge";
import { Textarea } from "./ui/textarea";
import { toast } from "sonner";
import profileService from "../api/services/profileService";

interface GameDetailPageProps {
  gameId: string;
  onNavigate: (page: string) => void;
  userRole?: "guest" | "registered" | "admin";
}

interface Review {
  id: string;
  userName: string;
  rating: number;
  comment: string;
  date: string;
}

export function GameDetailPage({ gameId, onNavigate, userRole = "guest" }: GameDetailPageProps) {
  const [showBuyModal, setShowBuyModal] = useState(false);
  const [showExchangeModal, setShowExchangeModal] = useState(false);
  const [showVerificationModal, setShowVerificationModal] = useState(false);
  const [exchangeMessage, setExchangeMessage] = useState("");
  const [selectedImageIndex, setSelectedImageIndex] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  
  const [gameData, setGameData] = useState<any>(null);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [newReview, setNewReview] = useState({ calificacion: 5, comentario: "" });
  const [submittingReview, setSubmittingReview] = useState(false);
  const { user } = useAuth();

  const loadListingData = useCallback(async () => {
    try {
      setLoading(true);
      setError(null);
      
      const listingId = parseInt(gameId, 10);
      
      const listingData = await profileService.getListingDetail(listingId);
      
      const reviewsData = await profileService.getListingReviews(listingId);
      
      setGameData({
        id: gameId,
        title: listingData.nombreProducto || listingData.producto || "Producto",
        description: listingData.descripcion || "",
        price: listingData.precio || 0,
        console: listingData.plataforma || "",
        condition: listingData.estado || "No especificado",
        platform: listingData.plataforma || "",
        franchise: listingData.tipoArticulo || "",
        releaseDate: listingData.fechaCreacion ? new Date(listingData.fechaCreacion).toLocaleDateString('es-ES') : "",
        estimatedValue: listingData.precio ? Math.round(listingData.precio * 1.2) : 0,
        itemType: listingData.tipoArticulo || "Artículo",
        language: listingData.idioma || "Desconocido",
        shipping: true,
        shippingCost: 5,
        location: listingData.ubicacion || "España",
        rating: listingData.usuarioReputacion || 0,
        transactionType: listingData.tipoTransaccion === "both" ? "both" : "sale",
        seller: {
          name: listingData.usuarioNombre || "Vendedor",
          reputation: listingData.usuarioReputacion || 0,
          totalSales: listingData.usuarioVentas || 0,
          verified: true,
        },
        imagenes: listingData.imagenes || [],
      });
      
      const transformedReviews = reviewsData.map((review: any) => ({
        id: review.idResena?.toString() || Math.random().toString(),
        userName: review.usuarioRevisor || "Usuario",
        rating: review.calificacion || 0,
        comment: review.comentario || "",
        date: review.fecha ? new Date(review.fecha).toLocaleDateString('es-ES') : "",
      }));
      
      setReviews(transformedReviews);
      setLoading(false);
    } catch (err: any) {
      console.error("Error loading listing:", err);
      setError(err.message || "Error cargando la publicación");
      setLoading(false);
      toast.error("Error al cargar la publicación");
    }
  }, [gameId]);

  useEffect(() => {
    if (gameId) {
      loadListingData();
    }
  }, [gameId, loadListingData]);

  const handleBuy = () => {
    if (userRole === "guest") {
      toast.error("Debes iniciar sesión para comprar");
      onNavigate("login");
      return;
    }
    setShowBuyModal(true);
  };

  const handleExchange = () => {
    if (userRole === "guest") {
      toast.error("Debes iniciar sesión para proponer un intercambio");
      onNavigate("login");
      return;
    }
    setShowExchangeModal(true);
  };

  const handleSubmitReview = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!user?.idUsuario) {
      toast.error("Debes iniciar sesión para dejar una reseña");
      onNavigate("login");
      return;
    }
    if (!newReview.comentario.trim()) {
      toast.error("Por favor escribe un comentario");
      return;
    }
    try {
      setSubmittingReview(true);
      await profileService.addReview(parseInt(gameId, 10), user.idUsuario, newReview.calificacion, newReview.comentario);
      await loadListingData();
      setNewReview({ calificacion: 5, comentario: "" });
      toast.success("Reseña añadida correctamente");
    } catch (err: any) {
      toast.error("Error al añadir reseña: " + (err.message || "Error desconocido"));
    } finally {
      setSubmittingReview(false);
    }
  };

  const handleRequestVerification = () => {
    if (userRole === "guest") {
      toast.error("Debes iniciar sesión para solicitar verificación");
      onNavigate("login");
      return;
    }
    setShowVerificationModal(true);
  };

  const confirmPurchase = () => {
    toast.success("Compra realizada con éxito. El pago está en retención hasta la entrega.");
    setShowBuyModal(false);
  };

  const confirmExchange = () => {
    toast.success("Propuesta de intercambio enviada correctamente");
    setShowExchangeModal(false);
    setExchangeMessage("");
  };

  const confirmVerification = () => {
    toast.success("Solicitud de verificación enviada. Recibirás una notificación cuando sea procesada.");
    setShowVerificationModal(false);
  };

  return (
    <div className="min-h-screen bg-gray-50 pt-20">
      {/* Loading State */}
      {loading && (
        <div className="flex items-center justify-center h-screen">
          <div className="text-center">
            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-primary mx-auto mb-4"></div>
            <p className="text-gray-600">Cargando publicación...</p>
          </div>
        </div>
      )}

      {/* Error State */}
      {error && !loading && (
        <div className="flex items-center justify-center h-screen">
          <div className="text-center">
            <p className="text-red-600 mb-4">Error: {error}</p>
            <Button onClick={() => onNavigate("explore")} className="bg-primary">
              Volver al catálogo
            </Button>
          </div>
        </div>
      )}

      {/* Content - Show only when data is loaded */}
      {!loading && !error && gameData && (
        <>
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-6">
          <button
            onClick={() => onNavigate("explore")}
            className="flex items-center gap-2 text-gray-600 hover:text-primary transition-colors mb-4"
          >
            <ArrowLeft className="w-5 h-5" />
            <span>Volver al catálogo</span>
          </button>
        </div>
      </div>

      {/* Content */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="grid lg:grid-cols-2 gap-8 mb-12">
          {/* Images */}
          <div className="space-y-4">
            <div className="bg-white rounded-xl overflow-hidden border border-gray-200">
              <img
                src={gameData.imagenes && gameData.imagenes.length > 0 ? gameData.imagenes[selectedImageIndex] : "https://via.placeholder.com/500x500?text=Imagen+no+disponible"}
                alt={gameData.title}
                className="w-full h-[500px] object-cover"
              />
            </div>
            <div className="grid grid-cols-3 gap-3">
              {(gameData.imagenes && gameData.imagenes.length > 0 ? gameData.imagenes : []).map((img: string, index: number) => (
                <button
                  key={index}
                  onClick={() => setSelectedImageIndex(index)}
                  className={`rounded-lg overflow-hidden border-2 transition-all ${
                    selectedImageIndex === index ? "border-primary" : "border-gray-200 hover:border-gray-300"
                  }`}
                >
                  <img src={img} alt={`Vista ${index + 1}`} className="w-full h-24 object-cover" />
                </button>
              ))}
            </div>
          </div>

          {/* Details */}
          <div className="space-y-6">
            <div>
              <div className="flex items-start justify-between mb-3">
                <h1 className="text-3xl font-bold text-gray-900">{gameData.title}</h1>
                <button className="p-2 hover:bg-gray-100 rounded-lg transition-colors">
                  <Heart className="w-6 h-6 text-gray-400 hover:text-red-500" />
                </button>
              </div>
              
              <div className="flex items-center gap-3 mb-4">
                <Badge className="bg-blue-100 text-blue-700 border-0">{gameData.condition}</Badge>
                <Badge className="bg-purple-100 text-purple-700 border-0">{gameData.itemType}</Badge>
                {gameData.transactionType === "both" && (
                  <Badge className="bg-green-100 text-green-700 border-0">
                    <Repeat className="w-3 h-3 mr-1" />
                    Intercambio disponible
                  </Badge>
                )}
              </div>

              <div className="flex items-center gap-2 text-gray-600 mb-4">
                <MapPin className="w-4 h-4" />
                <span className="text-sm">{gameData.location}</span>
              </div>

              <div className="flex items-center gap-2 mb-6">
                <div className="flex items-center gap-1">
                  <Star className="w-5 h-5 text-yellow-400 fill-yellow-400" />
                  <span className="font-semibold text-gray-900">{gameData.rating}</span>
                </div>
                <span className="text-gray-500 text-sm">({reviews.length} reseñas)</span>
              </div>

              <div className="bg-gradient-to-r from-primary/10 to-blue-50 rounded-xl p-6 mb-6">
                <div className="flex items-baseline gap-2 mb-2">
                  <span className="text-4xl font-bold text-primary">{gameData.price}€</span>
                  {gameData.shipping && (
                    <span className="text-sm text-gray-600">+ {gameData.shippingCost}€ envío</span>
                  )}
                </div>
                <p className="text-sm text-gray-600">Valor estimado: {gameData.estimatedValue}€</p>
              </div>

              {/* Action Buttons */}
              <div className="space-y-3">
                {gameData.transactionType !== "exchange" && (
                  <Button
                    onClick={handleBuy}
                    className="w-full bg-primary hover:bg-primary/90 text-white py-6 text-lg"
                  >
                    <ShoppingCart className="w-5 h-5 mr-2" />
                    Comprar ahora
                  </Button>
                )}
                
                {(gameData.transactionType === "exchange" || gameData.transactionType === "both") && (
                  <Button
                    onClick={handleExchange}
                    variant="outline"
                    className="w-full border-primary text-primary hover:bg-primary hover:text-white py-6 text-lg"
                  >
                    <Repeat className="w-5 h-5 mr-2" />
                    Proponer intercambio
                  </Button>
                )}

                {gameData.estimatedValue > 100 && (
                  <Button
                    onClick={handleRequestVerification}
                    variant="outline"
                    className="w-full border-green-600 text-green-600 hover:bg-green-600 hover:text-white py-3"
                  >
                    <ShieldCheck className="w-5 h-5 mr-2" />
                    Solicitar verificación profesional
                  </Button>
                )}
              </div>

              {/* Seller Info */}
              <div className="bg-white rounded-xl border border-gray-200 p-6 mt-6">
                <h3 className="font-semibold text-gray-900 mb-4">Información del vendedor</h3>
                {gameData.seller ? (
                  <div className="flex items-center gap-3 mb-3">
                    <div className="w-12 h-12 bg-primary text-white rounded-full flex items-center justify-center font-bold">
                      {gameData.seller.name?.charAt(0) || "?"}
                    </div>
                    <div>
                      <div className="flex items-center gap-2">
                        <span className="font-medium text-gray-900">{gameData.seller.name || "Vendedor"}</span>
                        {gameData.seller.verified && (
                          <ShieldCheck className="w-4 h-4 text-green-600" />
                        )}
                      </div>
                      <div className="flex items-center gap-1 text-sm">
                        <Star className="w-4 h-4 text-yellow-400 fill-yellow-400" />
                        <span className="text-gray-600">{gameData.seller.reputation || 0} • {gameData.seller.totalSales || 0} ventas</span>
                      </div>
                    </div>
                  </div>
                ) : (
                  <p className="text-gray-600">Información del vendedor no disponible</p>
                )}
              </div>
            </div>
          </div>
        </div>

        {/* Product Information */}
        <div className="grid lg:grid-cols-3 gap-8 mb-12">
          <div className="lg:col-span-2 space-y-8">
            {/* Description */}
            <div className="bg-white rounded-xl border border-gray-200 p-6">
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Descripción</h2>
              <p className="text-gray-700 leading-relaxed">{gameData.description}</p>
            </div>

            {/* Specifications */}
            <div className="bg-white rounded-xl border border-gray-200 p-6">
              <h2 className="text-2xl font-bold text-gray-900 mb-4">Especificaciones</h2>
              <div className="grid md:grid-cols-2 gap-4">
                <div className="flex justify-between py-3 border-b border-gray-100">
                  <span className="text-gray-600">Plataforma:</span>
                  <span className="font-medium text-gray-900">{gameData.platform}</span>
                </div>
                <div className="flex justify-between py-3 border-b border-gray-100">
                  <span className="text-gray-600">Fecha lanzamiento:</span>
                  <span className="font-medium text-gray-900">{gameData.releaseDate}</span>
                </div>
                <div className="flex justify-between py-3 border-b border-gray-100">
                  <span className="text-gray-600">Estado:</span>
                  <span className="font-medium text-gray-900">{gameData.condition}</span>
                </div>
                <div className="flex justify-between py-3 border-b border-gray-100">
                  <span className="text-gray-600">Idioma:</span>
                  <span className="font-medium text-gray-900">{gameData.language}</span>
                </div>
                <div className="flex justify-between py-3 border-b border-gray-100">
                  <span className="text-gray-600">Envío:</span>
                  <span className="font-medium text-gray-900">
                    {gameData.shipping ? `Disponible (${gameData.shippingCost}€)` : "No disponible"}
                  </span>
                </div>
              </div>
            </div>

            {/* Reviews */}
            <div className="bg-white rounded-xl border border-gray-200 p-6">
              <h2 className="text-2xl font-bold text-gray-900 mb-6">Reseñas ({reviews.length})</h2>

              {user && (
                <form onSubmit={handleSubmitReview} className="mb-8 space-y-4">
                  <h3 className="font-bold text-gray-900">Deja tu reseña</h3>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">Calificación</label>
                    <div className="flex gap-2">
                      {[1, 2, 3, 4, 5].map((rating) => (
                        <button
                          key={rating}
                          type="button"
                          onClick={() => setNewReview({ ...newReview, calificacion: rating })}
                          className={`p-2 ${newReview.calificacion >= rating ? "text-yellow-400" : "text-gray-300"}`}
                        >
                          <Star className="w-6 h-6 fill-current" />
                        </button>
                      ))}
                    </div>
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">Tu comentario</label>
                    <textarea
                      value={newReview.comentario}
                      onChange={(e) => setNewReview({ ...newReview, comentario: e.target.value })}
                      placeholder="Cuéntanos tu experiencia..."
                      rows={4}
                      className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                    />
                  </div>
                  <Button type="submit" disabled={submittingReview} className="bg-primary hover:bg-primary/90">
                    {submittingReview ? "Enviando..." : "Enviar reseña"}
                  </Button>
                </form>
              )}

              {!user && (
                <div className="bg-blue-50 border border-blue-200 rounded-xl p-6 mb-8">
                  <p className="text-blue-900 text-sm"><strong>Inicia sesión</strong> para dejar una reseña</p>
                </div>
              )}

              <div className="space-y-6">
                {reviews.map((review) => (
                  <div key={review.id} className="border-b border-gray-100 last:border-0 pb-6 last:pb-0">
                    <div className="flex items-start gap-3 mb-3">
                      <div className="w-10 h-10 bg-gray-200 rounded-full flex items-center justify-center">
                        <User className="w-5 h-5 text-gray-600" />
                      </div>
                      <div className="flex-1">
                        <div className="flex items-center justify-between mb-1">
                          <span className="font-medium text-gray-900">{review.userName}</span>
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
                        <p className="text-gray-700">{review.comment}</p>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>

          {/* Sidebar */}
          <div className="space-y-6">
            {/* Shipping Info */}
            {gameData.shipping && (
              <div className="bg-white rounded-xl border border-gray-200 p-6">
                <div className="flex items-center gap-3 mb-4">
                  <Truck className="w-6 h-6 text-primary" />
                  <h3 className="font-semibold text-gray-900">Información de envío</h3>
                </div>
                <ul className="space-y-2 text-sm text-gray-600">
                  <li className="flex items-start gap-2">
                    <span className="text-green-600 mt-1">✓</span>
                    <span>Envío en 24-48h laborables</span>
                  </li>
                  <li className="flex items-start gap-2">
                    <span className="text-green-600 mt-1">✓</span>
                    <span>Embalaje seguro y protegido</span>
                  </li>
                  <li className="flex items-start gap-2">
                    <span className="text-green-600 mt-1">✓</span>
                    <span>Seguimiento online incluido</span>
                  </li>
                </ul>
              </div>
            )}

            {/* Secure Payment */}
            <div className="bg-white rounded-xl border border-gray-200 p-6">
              <div className="flex items-center gap-3 mb-4">
                <ShieldCheck className="w-6 h-6 text-green-600" />
                <h3 className="font-semibold text-gray-900">Pago seguro</h3>
              </div>
              <ul className="space-y-2 text-sm text-gray-600">
                <li className="flex items-start gap-2">
                  <span className="text-green-600 mt-1">✓</span>
                  <span>Pago retenido hasta la entrega</span>
                </li>
                <li className="flex items-start gap-2">
                  <span className="text-green-600 mt-1">✓</span>
                  <span>Protección total del comprador</span>
                </li>
                <li className="flex items-start gap-2">
                  <span className="text-green-600 mt-1">✓</span>
                  <span>Garantía de devolución</span>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>

      {/* Buy Modal */}
      <Dialog open={showBuyModal} onOpenChange={setShowBuyModal}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Confirmar compra</DialogTitle>
            <DialogDescription>
              Estás a punto de comprar "{gameData.title}"
            </DialogDescription>
          </DialogHeader>
          <div className="space-y-4">
            <div className="bg-gray-50 rounded-lg p-4">
              <div className="flex justify-between mb-2">
                <span className="text-gray-600">Precio del producto:</span>
                <span className="font-medium">{gameData.price}€</span>
              </div>
              <div className="flex justify-between mb-2">
                <span className="text-gray-600">Gastos de envío:</span>
                <span className="font-medium">{gameData.shippingCost}€</span>
              </div>
              <div className="border-t border-gray-200 pt-2 mt-2">
                <div className="flex justify-between">
                  <span className="font-semibold text-gray-900">Total:</span>
                  <span className="font-bold text-primary text-xl">
                    {gameData.price + gameData.shippingCost}€
                  </span>
                </div>
              </div>
            </div>
            <p className="text-sm text-gray-600">
              El pago se mantendrá en retención hasta que confirmes la recepción del producto.
            </p>
            <div className="flex gap-3">
              <Button variant="outline" onClick={() => setShowBuyModal(false)} className="flex-1">
                Cancelar
              </Button>
              <Button onClick={confirmPurchase} className="flex-1 bg-primary">
                Confirmar compra
              </Button>
            </div>
          </div>
        </DialogContent>
      </Dialog>

      {/* Exchange Modal */}
      <Dialog open={showExchangeModal} onOpenChange={setShowExchangeModal}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Proponer intercambio</DialogTitle>
            <DialogDescription>
              Selecciona el producto que quieres intercambiar y añade un mensaje
            </DialogDescription>
          </DialogHeader>
          <div className="space-y-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Tu producto para intercambiar
              </label>
              <select className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary">
                <option>Selecciona un producto de tu colección</option>
                <option>God of War Ragnarök - PS5</option>
                <option>FIFA 24 - PS5</option>
                <option>Elden Ring - PC</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Mensaje (opcional)
              </label>
              <Textarea
                value={exchangeMessage}
                onChange={(e) => setExchangeMessage(e.target.value)}
                placeholder="Cuéntale al vendedor por qué te interesa este intercambio..."
                rows={4}
              />
            </div>
            <div className="flex gap-3">
              <Button variant="outline" onClick={() => setShowExchangeModal(false)} className="flex-1">
                Cancelar
              </Button>
              <Button onClick={confirmExchange} className="flex-1 bg-primary">
                Enviar propuesta
              </Button>
            </div>
          </div>
        </DialogContent>
      </Dialog>

      {/* Verification Modal */}
      <Dialog open={showVerificationModal} onOpenChange={setShowVerificationModal}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Solicitar verificación profesional</DialogTitle>
            <DialogDescription>
              Este servicio incluye autenticación y valoración profesional del artículo
            </DialogDescription>
          </DialogHeader>
          <div className="space-y-4">
            <div className="bg-blue-50 rounded-lg p-4">
              <p className="text-sm text-blue-900 mb-2">
                <strong>Precio del servicio:</strong> 15€
              </p>
              <p className="text-sm text-blue-800">
                Un experto verificará la autenticidad y estado del producto, proporcionando un
                certificado de verificación oficial.
              </p>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Motivo de la verificación
              </label>
              <Textarea
                placeholder="Explica por qué solicitas la verificación..."
                rows={3}
              />
            </div>
            <div className="flex gap-3">
              <Button variant="outline" onClick={() => setShowVerificationModal(false)} className="flex-1">
                Cancelar
              </Button>
              <Button onClick={confirmVerification} className="flex-1 bg-green-600 hover:bg-green-700">
                Solicitar verificación
              </Button>
            </div>
          </div>
        </DialogContent>
      </Dialog>

      <Footer onNavigate={onNavigate} />
        </>
      )}
    </div>
  );
}
