import { useState } from "react";
import { ChevronDown, HelpCircle, Mail, MessageCircle } from "lucide-react";
import { Footer } from "./Footer";

interface FAQPageProps {
  onNavigate: (page: string) => void;
}

interface FAQItem {
  question: string;
  answer: string;
}

export function FAQPage({ onNavigate }: FAQPageProps) {
  const [openIndex, setOpenIndex] = useState<number | null>(0);

  const faqs: FAQItem[] = [
    {
      question: "¿Cómo funciona GameSwap?",
      answer:
        "GameSwap es una plataforma donde puedes intercambiar videojuegos con otros usuarios sin gastar dinero. Solo tienes que crear una cuenta, subir los juegos que quieres intercambiar, buscar los que te interesan y proponer intercambios a otros usuarios.",
    },
    {
      question: "¿Es completamente gratis?",
      answer:
        "Sí, GameSwap es 100% gratis. No cobramos comisiones por los intercambios ni por publicar tus juegos. La única inversión es el envío del juego si decides hacerlo por correo, o puedes quedar en persona de forma gratuita.",
    },
    {
      question: "¿Cómo puedo asegurarme de que el intercambio es seguro?",
      answer:
        "Todos los usuarios tienen un sistema de valoraciones. Te recomendamos revisar las valoraciones antes de aceptar un intercambio. También puedes optar por hacer el intercambio en persona en lugares públicos. Nunca compartas información personal sensible antes de confirmar el intercambio.",
    },
    {
      question: "¿Qué consolas están soportadas?",
      answer:
        "Actualmente soportamos PlayStation 4 y 5, Xbox One y Series X/S, Nintendo Switch, y juegos de PC. Estamos trabajando para añadir más plataformas en el futuro.",
    },
    {
      question: "¿Puedo cancelar un intercambio?",
      answer:
        "Sí, puedes cancelar un intercambio en cualquier momento antes de que sea marcado como completado. Sin embargo, ten en cuenta que las cancelaciones frecuentes pueden afectar tu reputación en la plataforma.",
    },
    {
      question: "¿Qué hago si hay un problema con un intercambio?",
      answer:
        "Si tienes algún problema con un intercambio, puedes reportarlo a través de nuestro sistema de soporte. Revisaremos el caso y tomaremos las medidas necesarias. También puedes dejar una valoración negativa para advertir a otros usuarios.",
    },
    {
      question: "¿Puedo intercambiar más de un juego a la vez?",
      answer:
        "Sí, puedes proponer intercambios de múltiples juegos. Solo tienes que especificarlo en el mensaje cuando propongas el intercambio al otro usuario.",
    },
    {
      question: "¿Cómo se calculan las valoraciones?",
      answer:
        "Las valoraciones se basan en las opiniones que dejan otros usuarios después de completar intercambios contigo. La puntuación va de 1 a 5 estrellas y se calcula como promedio de todas las valoraciones recibidas.",
    },
    {
      question: "¿Puedo vender juegos en GameSwap?",
      answer:
        "No, GameSwap es exclusivamente una plataforma de intercambio, no de compra-venta. Nuestro objetivo es fomentar el compartir entre la comunidad gamer sin transacciones monetarias.",
    },
    {
      question: "¿Cómo elimino mi cuenta?",
      answer:
        "Puedes eliminar tu cuenta desde la configuración de tu perfil. Ten en cuenta que esto borrará permanentemente todos tus datos, juegos publicados e historial de intercambios.",
    },
  ];

  return (
    <div className="min-h-screen bg-gradient-to-br from-gray-50 to-white pt-20">
      {/* Header */}
      <div className="bg-gradient-to-br from-[#007BFF] to-[#00FFC6] py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <div className="w-16 h-16 rounded-full bg-white/20 backdrop-blur-sm flex items-center justify-center mx-auto mb-4">
            <HelpCircle className="w-8 h-8 text-white" />
          </div>
          <h1 className="text-white mb-4" style={{ fontSize: "2.5rem", fontWeight: "800" }}>
            Preguntas frecuentes
          </h1>
          <p className="text-white/90 max-w-2xl mx-auto" style={{ fontSize: "1.125rem" }}>
            Encuentra respuestas a las preguntas más comunes sobre GameSwap
          </p>
        </div>
      </div>

      {/* Content */}
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
        {/* FAQ Accordion */}
        <div className="space-y-4 mb-12">
          {faqs.map((faq, index) => (
            <div key={index} className="bg-white rounded-[10px] shadow-md overflow-hidden">
              <button
                onClick={() => setOpenIndex(openIndex === index ? null : index)}
                className="w-full px-6 py-5 flex items-center justify-between hover:bg-gray-50 transition-all"
              >
                <span
                  className="text-left text-[#1A1A1A]"
                  style={{ fontSize: "1.125rem", fontWeight: "700" }}
                >
                  {faq.question}
                </span>
                <ChevronDown
                  className={`w-5 h-5 text-[#FF2D92] transition-transform ${
                    openIndex === index ? "transform rotate-180" : ""
                  }`}
                />
              </button>
              {openIndex === index && (
                <div className="px-6 pb-5 pt-2">
                  <p className="text-gray-600" style={{ fontSize: "0.9375rem", lineHeight: "1.7" }}>
                    {faq.answer}
                  </p>
                </div>
              )}
            </div>
          ))}
        </div>

        {/* Contact Section */}
        <div className="bg-gradient-to-br from-[#007BFF] to-[#00FFC6] rounded-[10px] p-8 text-center">
          <h2 className="text-white mb-3" style={{ fontSize: "1.75rem", fontWeight: "800" }}>
            ¿No encuentras tu respuesta?
          </h2>
          <p className="text-white/90 mb-6" style={{ fontSize: "1rem" }}>
            Nuestro equipo está aquí para ayudarte
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <button className="flex items-center justify-center gap-2 px-6 py-3 rounded-full bg-white text-[#007BFF] hover:shadow-lg transition-all" style={{ fontWeight: "600" }}>
              <Mail className="w-5 h-5" />
              Enviar email
            </button>
            <button className="flex items-center justify-center gap-2 px-6 py-3 rounded-full bg-white/20 backdrop-blur-sm border-2 border-white text-white hover:bg-white/30 transition-all" style={{ fontWeight: "600" }}>
              <MessageCircle className="w-5 h-5" />
              Chat en vivo
            </button>
          </div>
        </div>

        {/* Quick Links */}
        <div className="mt-12 grid md:grid-cols-3 gap-6">
          <button
            onClick={() => onNavigate("home")}
            className="bg-white rounded-[10px] shadow-md p-6 hover:shadow-xl transition-all text-center"
          >
            <div className="w-12 h-12 rounded-full bg-gradient-to-br from-[#FF2D92] to-[#A100FF] flex items-center justify-center mx-auto mb-3">
              <span className="text-white" style={{ fontSize: "1.25rem" }}>🏠</span>
            </div>
            <h3 className="text-[#1A1A1A] mb-2" style={{ fontSize: "1rem", fontWeight: "700" }}>
              Volver al inicio
            </h3>
            <p className="text-gray-600" style={{ fontSize: "0.875rem" }}>
              Explora juegos disponibles
            </p>
          </button>

          <button
            onClick={() => onNavigate("profile")}
            className="bg-white rounded-[10px] shadow-md p-6 hover:shadow-xl transition-all text-center"
          >
            <div className="w-12 h-12 rounded-full bg-gradient-to-br from-[#007BFF] to-[#00FFC6] flex items-center justify-center mx-auto mb-3">
              <span className="text-white" style={{ fontSize: "1.25rem" }}>👤</span>
            </div>
            <h3 className="text-[#1A1A1A] mb-2" style={{ fontSize: "1rem", fontWeight: "700" }}>
              Mi perfil
            </h3>
            <p className="text-gray-600" style={{ fontSize: "0.875rem" }}>
              Gestiona tus juegos
            </p>
          </button>

          <div className="bg-white rounded-[10px] shadow-md p-6 text-center">
            <div className="w-12 h-12 rounded-full bg-gradient-to-br from-[#00FFC6] to-[#007BFF] flex items-center justify-center mx-auto mb-3">
              <span className="text-white" style={{ fontSize: "1.25rem" }}>📖</span>
            </div>
            <h3 className="text-[#1A1A1A] mb-2" style={{ fontSize: "1rem", fontWeight: "700" }}>
              Guía de usuario
            </h3>
            <p className="text-gray-600" style={{ fontSize: "0.875rem" }}>
              Aprende a usar GameSwap
            </p>
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
}
