import { useState } from "react";
import { Package, Clock, CheckCircle, XCircle, MessageCircle } from "lucide-react";
import { Footer } from "./Footer";

interface ExchangesPageProps {
  onNavigate: (page: string) => void;
}

interface Exchange {
  id: string;
  gameOffered: string;
  gameRequested: string;
  otherUser: string;
  status: "pending" | "accepted" | "rejected" | "completed";
  date: string;
  message: string;
  imageOffered: string;
  imageRequested: string;
}

export function ExchangesPage({ onNavigate }: ExchangesPageProps) {
  const [activeTab, setActiveTab] = useState<"sent" | "received">("received");

  const exchanges: Exchange[] = [
    {
      id: "1",
      gameOffered: "FIFA 24",
      gameRequested: "God of War Ragnarök",
      otherUser: "María García",
      status: "pending",
      date: "Hace 2 horas",
      message: "Hola! Me interesa tu juego. Mi FIFA está en perfecto estado, con caja original.",
      imageOffered: "https://images.unsplash.com/photo-1652734935726-7afd52076e7f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx2aWRlbyUyMGdhbWUlMjBwbGF5fGVufDF8fHx8MTc2MjI4MDAxMHww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      imageRequested: "https://images.unsplash.com/photo-1641564341083-161e6aa17a8b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1lciUyMHBsYXlpbmd8ZW58MXx8fHwxNzYyMjgwMDEzfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    },
    {
      id: "2",
      gameOffered: "Spider-Man 2",
      gameRequested: "The Last of Us Part II",
      otherUser: "Juan Pérez",
      status: "pending",
      date: "Hace 5 horas",
      message: "Me encantaría intercambiar. ¿Quedamos en Madrid centro?",
      imageOffered: "https://images.unsplash.com/photo-1593024579758-6221e85efbe6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwbGF5c3RhdGlvbiUyMGdhbWV8ZW58MXx8fHwxNzYyMjgwMDExfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      imageRequested: "https://images.unsplash.com/photo-1593024579758-6221e85efbe6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwbGF5c3RhdGlvbiUyMGdhbWV8ZW58MXx8fHwxNzYyMjgwMDExfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    },
    {
      id: "3",
      gameOffered: "Zelda: Tears of Kingdom",
      gameRequested: "Mario Kart 8",
      otherUser: "Ana López",
      status: "accepted",
      date: "Hace 1 día",
      message: "¡Perfecto! Nos vemos el sábado.",
      imageOffered: "https://images.unsplash.com/photo-1612036781124-847f8939b154?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxuaW50ZW5kbyUyMHN3aXRjaHxlbnwxfHx8fDE3NjIyMzc5MDd8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      imageRequested: "https://images.unsplash.com/photo-1550745165-9bc0b252726f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxyZXRybyUyMGdhbWluZ3xlbnwxfHx8fDE3NjIyODAwMTN8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    },
    {
      id: "4",
      gameOffered: "Halo Infinite",
      gameRequested: "Forza Horizon 5",
      otherUser: "Pedro Sánchez",
      status: "completed",
      date: "Hace 3 días",
      message: "Intercambio completado. ¡Todo perfecto!",
      imageOffered: "https://images.unsplash.com/photo-1631896928983-2c94ea6f97e8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx4Ym94JTIwY29udHJvbGxlcnxlbnwxfHx8fDE3NjIyODAwMTJ8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      imageRequested: "https://images.unsplash.com/photo-1655976796204-308e6f3deaa8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBjb25zb2xlJTIwY29udHJvbGxlcnxlbnwxfHx8fDE3NjIyMzc0OTN8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
    },
  ];

  const getStatusInfo = (status: Exchange["status"]) => {
    switch (status) {
      case "pending":
        return {
          icon: Clock,
          text: "Pendiente",
          color: "text-yellow-500",
          bg: "bg-yellow-50",
        };
      case "accepted":
        return {
          icon: CheckCircle,
          text: "Aceptado",
          color: "text-green-500",
          bg: "bg-green-50",
        };
      case "rejected":
        return {
          icon: XCircle,
          text: "Rechazado",
          color: "text-red-500",
          bg: "bg-red-50",
        };
      case "completed":
        return {
          icon: Package,
          text: "Completado",
          color: "text-blue-500",
          bg: "bg-blue-50",
        };
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-50 to-white pt-20">
      {/* Header */}
      <div className="bg-gradient-to-br from-[#007BFF] to-[#00FFC6] py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 className="text-white mb-2" style={{ fontSize: "2.5rem", fontWeight: "800" }}>
            Mis intercambios
          </h1>
          <p className="text-white/90" style={{ fontSize: "1.125rem" }}>
            Gestiona tus propuestas de intercambio
          </p>
        </div>
      </div>

      {/* Content */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        {/* Tabs */}
        <div className="flex gap-4 mb-8">
          <button
            onClick={() => setActiveTab("received")}
            className={`px-6 py-3 rounded-full transition-all ${
              activeTab === "received"
                ? "bg-gradient-to-r from-[#FF2D92] to-[#A100FF] text-white shadow-lg"
                : "bg-white text-gray-600 hover:bg-gray-50"
            }`}
            style={{ fontWeight: "600" }}
          >
            Recibidas ({exchanges.filter((e) => e.status === "pending").length})
          </button>
          <button
            onClick={() => setActiveTab("sent")}
            className={`px-6 py-3 rounded-full transition-all ${
              activeTab === "sent"
                ? "bg-gradient-to-r from-[#FF2D92] to-[#A100FF] text-white shadow-lg"
                : "bg-white text-gray-600 hover:bg-gray-50"
            }`}
            style={{ fontWeight: "600" }}
          >
            Enviadas
          </button>
        </div>

        {/* Exchange Cards */}
        <div className="space-y-6">
          {exchanges.map((exchange) => {
            const statusInfo = getStatusInfo(exchange.status);
            const StatusIcon = statusInfo.icon;

            return (
              <div
                key={exchange.id}
                className="bg-white rounded-[10px] shadow-md hover:shadow-xl transition-all overflow-hidden"
              >
                <div className="p-6">
                  <div className="flex items-center justify-between mb-4">
                    <div className="flex items-center gap-3">
                      <div className="w-10 h-10 rounded-full bg-gradient-to-br from-[#007BFF] to-[#00FFC6] flex items-center justify-center">
                        <span className="text-white" style={{ fontWeight: "700" }}>
                          {exchange.otherUser[0]}
                        </span>
                      </div>
                      <div>
                        <p className="text-[#1A1A1A]" style={{ fontWeight: "700" }}>
                          {exchange.otherUser}
                        </p>
                        <p className="text-gray-500" style={{ fontSize: "0.875rem" }}>
                          {exchange.date}
                        </p>
                      </div>
                    </div>
                    <div className={`flex items-center gap-2 px-4 py-2 rounded-full ${statusInfo.bg}`}>
                      <StatusIcon className={`w-4 h-4 ${statusInfo.color}`} />
                      <span className={statusInfo.color} style={{ fontSize: "0.875rem", fontWeight: "600" }}>
                        {statusInfo.text}
                      </span>
                    </div>
                  </div>

                  {/* Exchange Details */}
                  <div className="grid md:grid-cols-3 gap-6 mb-4">
                    {/* Game Offered */}
                    <div>
                      <p className="text-gray-500 mb-2" style={{ fontSize: "0.875rem", fontWeight: "600" }}>
                        Ofrece
                      </p>
                      <div className="relative h-32 rounded-lg overflow-hidden mb-2">
                        <img
                          src={exchange.imageOffered}
                          alt={exchange.gameOffered}
                          className="w-full h-full object-cover"
                        />
                      </div>
                      <p className="text-[#1A1A1A]" style={{ fontWeight: "600" }}>
                        {exchange.gameOffered}
                      </p>
                    </div>

                    {/* Arrow */}
                    <div className="flex items-center justify-center">
                      <div className="text-[#FF2D92]" style={{ fontSize: "2rem" }}>
                        ⇄
                      </div>
                    </div>

                    {/* Game Requested */}
                    <div>
                      <p className="text-gray-500 mb-2" style={{ fontSize: "0.875rem", fontWeight: "600" }}>
                        Por
                      </p>
                      <div className="relative h-32 rounded-lg overflow-hidden mb-2">
                        <img
                          src={exchange.imageRequested}
                          alt={exchange.gameRequested}
                          className="w-full h-full object-cover"
                        />
                      </div>
                      <p className="text-[#1A1A1A]" style={{ fontWeight: "600" }}>
                        {exchange.gameRequested}
                      </p>
                    </div>
                  </div>

                  {/* Message */}
                  <div className="bg-gray-50 rounded-lg p-4 mb-4">
                    <div className="flex items-start gap-2">
                      <MessageCircle className="w-4 h-4 text-gray-400 mt-1 flex-shrink-0" />
                      <p className="text-gray-700" style={{ fontSize: "0.9375rem" }}>
                        {exchange.message}
                      </p>
                    </div>
                  </div>

                  {/* Actions */}
                  {exchange.status === "pending" && activeTab === "received" && (
                    <div className="flex gap-3">
                      <button className="flex-1 py-2 px-6 rounded-full bg-gradient-to-r from-[#FF2D92] to-[#A100FF] text-white hover:shadow-lg transition-all" style={{ fontWeight: "600" }}>
                        Aceptar intercambio
                      </button>
                      <button className="flex-1 py-2 px-6 rounded-full bg-gray-200 text-gray-700 hover:bg-gray-300 transition-all" style={{ fontWeight: "600" }}>
                        Rechazar
                      </button>
                      <button className="py-2 px-6 rounded-full bg-white border-2 border-[#007BFF] text-[#007BFF] hover:bg-[#007BFF] hover:text-white transition-all" style={{ fontWeight: "600" }}>
                        Mensaje
                      </button>
                    </div>
                  )}

                  {exchange.status === "accepted" && (
                    <div className="flex gap-3">
                      <button className="flex-1 py-2 px-6 rounded-full bg-gradient-to-r from-[#007BFF] to-[#00FFC6] text-white hover:shadow-lg transition-all" style={{ fontWeight: "600" }}>
                        Marcar como completado
                      </button>
                      <button className="py-2 px-6 rounded-full bg-white border-2 border-[#007BFF] text-[#007BFF] hover:bg-[#007BFF] hover:text-white transition-all" style={{ fontWeight: "600" }}>
                        Enviar mensaje
                      </button>
                    </div>
                  )}

                  {exchange.status === "completed" && (
                    <button className="w-full py-2 px-6 rounded-full bg-gradient-to-r from-[#007BFF] to-[#00FFC6] text-white hover:shadow-lg transition-all" style={{ fontWeight: "600" }}>
                      Dejar valoración
                    </button>
                  )}
                </div>
              </div>
            );
          })}
        </div>

        {/* Empty State */}
        {exchanges.length === 0 && (
          <div className="bg-white rounded-[10px] shadow-md p-12 text-center">
            <Package className="w-16 h-16 text-gray-300 mx-auto mb-4" />
            <h3 className="text-[#1A1A1A] mb-2" style={{ fontSize: "1.25rem", fontWeight: "700" }}>
              No hay intercambios
            </h3>
            <p className="text-gray-600 mb-6">
              Empieza a explorar juegos y propón intercambios
            </p>
            <button
              onClick={() => onNavigate("explore")}
              className="px-8 py-3 rounded-full bg-gradient-to-r from-[#FF2D92] to-[#A100FF] text-white hover:shadow-lg transition-all"
              style={{ fontWeight: "600" }}
            >
              Explorar juegos
            </button>
          </div>
        )}
      </div>

      <Footer />
    </div>
  );
}
