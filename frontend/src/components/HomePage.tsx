import { Hero } from "./Hero";
import { Filters } from "./Filters";
import { GameCard, Game } from "./GameCard";
import { HowItWorks } from "./HowItWorks";
import { Footer } from "./Footer";
import { ChevronLeft, ChevronRight } from "lucide-react";
import { useRef } from "react";

interface HomePageProps {
  onNavigate: (page: string, gameId?: string) => void;
}

export function HomePage({ onNavigate }: HomePageProps) {
  const carouselRef = useRef<HTMLDivElement>(null);

  const featuredGames: Game[] = [
    {
      id: "1",
      title: "The Last of Us Part II",
      console: "PlayStation 5",
      condition: "Como nuevo",
      image: "https://images.unsplash.com/photo-1593024579758-6221e85efbe6?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxwbGF5c3RhdGlvbiUyMGdhbWV8ZW58MXx8fHwxNzYyMjgwMDExfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Madrid",
      rating: 4.8,
      price: 45,
      type: "both",
      itemType: "videogame",
      platform: "PlayStation 5",
    },
    {
      id: "2",
      title: "Zelda: Tears of Kingdom",
      console: "Nintendo Switch",
      condition: "Nuevo",
      image: "https://images.unsplash.com/photo-1612036781124-847f8939b154?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxuaW50ZW5kbyUyMHN3aXRjaHxlbnwxfHx8fDE3NjIyMzc5MDd8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Barcelona",
      rating: 5.0,
      price: 55,
      type: "sale",
      itemType: "videogame",
      platform: "Nintendo Switch",
    },
    {
      id: "3",
      title: "Halo Infinite",
      console: "Xbox Series X",
      condition: "Como nuevo",
      image: "https://images.unsplash.com/photo-1631896928983-2c94ea6f97e8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx4Ym94JTIwY29udHJvbGxlcnxlbnwxfHx8fDE3NjIyODAwMTJ8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Valencia",
      rating: 4.5,
      price: 35,
      type: "both",
      itemType: "videogame",
      platform: "Xbox Series X",
    },
    {
      id: "4",
      title: "Figura Iron Man Mark 50",
      console: "Marvel",
      condition: "Nuevo",
      image: "https://images.unsplash.com/photo-1700909416178-40b292788200?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxhY3Rpb24lMjBmaWd1cmUlMjBjb2xsZWN0aWJsZXxlbnwxfHx8fDE3NzM2NjkxMjd8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Madrid",
      rating: 4.9,
      price: 120,
      type: "sale",
      itemType: "figure",
      platform: "Figura coleccionable",
    },
    {
      id: "5",
      title: "Cyberpunk 2077",
      console: "PC",
      condition: "Como nuevo",
      image: "https://images.unsplash.com/photo-1617507171089-6cb9aa5add36?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBzZXR1cCUyMG5lb258ZW58MXx8fHwxNzYyMjQyNzU3fDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Bilbao",
      rating: 4.6,
      price: 25,
      type: "exchange",
      itemType: "videogame",
      platform: "PC",
    },
    {
      id: "6",
      title: "Colección Cómics Spider-Man Vol. 1-10",
      console: "Marvel Comics",
      condition: "Muy bueno",
      image: "https://images.unsplash.com/photo-1759863738666-7584248cdf7e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb21pYyUyMGJvb2slMjBjb2xsZWN0aW9ufGVufDF8fHx8MTc3MzU5NTU0MHww&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Barcelona",
      rating: 4.7,
      price: 85,
      type: "sale",
      itemType: "comic",
      platform: "Cómic",
    },
  ];

  const popularGames: Game[] = [
    {
      id: "7",
      title: "God of War Ragnarök",
      console: "PlayStation 5",
      condition: "Nuevo",
      image: "https://images.unsplash.com/photo-1641564341083-161e6aa17a8b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1lciUyMHBsYXlpbmd8ZW58MXx8fHwxNzYyMjgwMDEzfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Barcelona",
      rating: 5.0,
      price: 60,
      type: "both",
      itemType: "videogame",
      platform: "PlayStation 5",
    },
    {
      id: "8",
      title: "Forza Horizon 5",
      console: "Xbox Series X",
      condition: "Muy bueno",
      image: "https://images.unsplash.com/photo-1655976796204-308e6f3deaa8?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1pbmclMjBjb25zb2xlJTIwY29udHJvbGxlcnxlbnwxfHx8fDE3NjIyMzc0OTN8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Valencia",
      rating: 4.7,
      price: 40,
      type: "sale",
      itemType: "videogame",
      platform: "Xbox Series X",
    },
    {
      id: "9",
      title: "Cartas Pokémon - Charizard Edición Limitada",
      console: "Pokémon TCG",
      condition: "Nuevo",
      image: "https://images.unsplash.com/photo-1742743032749-187b17179e0f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0cmFkaW5nJTIwY2FyZHMlMjBjb2xsZWN0aWJsZXxlbnwxfHx8fDE3NzM2ODUzMTF8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Madrid",
      rating: 4.9,
      price: 250,
      type: "sale",
      itemType: "collectible",
      platform: "Trading Cards",
    },
    {
      id: "10",
      title: "Colección Figuras Anime",
      console: "Varios",
      condition: "Muy bueno",
      image: "https://images.unsplash.com/photo-1751110479291-36300997a064?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb2xsZWN0aWJsZSUyMHRveXMlMjBzaGVsZnxlbnwxfHx8fDE3NzM2ODUzMTR8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Sevilla",
      rating: 4.8,
      price: 180,
      type: "both",
      itemType: "figure",
      platform: "Figura coleccionable",
    },
  ];

  const scrollCarousel = (direction: "left" | "right") => {
    if (carouselRef.current) {
      const scrollAmount = 320;
      const newScrollPosition =
        direction === "left"
          ? carouselRef.current.scrollLeft - scrollAmount
          : carouselRef.current.scrollLeft + scrollAmount;
      
      carouselRef.current.scrollTo({
        left: newScrollPosition,
        behavior: "smooth",
      });
    }
  };

  return (
    <div className="min-h-screen">
      {/* Hero Section */}
      <Hero onNavigate={onNavigate} />

      {/* Featured Games Section */}
      <section className="py-16 bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="mb-8">
            <h2 className="text-gray-900 text-3xl font-bold mb-2">
              Productos destacados
            </h2>
            <p className="text-gray-600">
              Los artículos más populares de esta semana
            </p>
          </div>

          {/* Filters */}
          <Filters />

          {/* Games Grid */}
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {featuredGames.map((game) => (
              <GameCard
                key={game.id}
                game={game}
                onClick={() => onNavigate("game", game.id)}
              />
            ))}
          </div>
        </div>
      </section>

      {/* Popular Games Carousel */}
      <section className="py-16 bg-gray-50">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex justify-between items-center mb-8">
            <div>
              <h2 className="text-gray-900 text-3xl font-bold mb-2">
                Más buscados
              </h2>
              <p className="text-gray-600">
                Los favoritos de la comunidad
              </p>
            </div>
            <div className="flex gap-2">
              <button
                onClick={() => scrollCarousel("left")}
                className="w-10 h-10 rounded-full bg-white border border-gray-200 flex items-center justify-center hover:bg-gray-50 transition-all"
              >
                <ChevronLeft className="w-5 h-5 text-gray-700" />
              </button>
              <button
                onClick={() => scrollCarousel("right")}
                className="w-10 h-10 rounded-full bg-white border border-gray-200 flex items-center justify-center hover:bg-gray-50 transition-all"
              >
                <ChevronRight className="w-5 h-5 text-gray-700" />
              </button>
            </div>
          </div>

          {/* Carousel */}
          <div
            ref={carouselRef}
            className="flex gap-6 overflow-x-auto scrollbar-hide scroll-smooth pb-4"
            style={{ scrollbarWidth: "none" }}
          >
            {popularGames.map((game) => (
              <div key={game.id} className="flex-shrink-0 w-80">
                <GameCard game={game} onClick={() => onNavigate("game", game.id)} />
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* How It Works */}
      <HowItWorks />

      {/* Footer */}
      <Footer onNavigate={onNavigate} />
    </div>
  );
}