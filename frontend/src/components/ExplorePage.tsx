import { useState, useMemo } from "react";
import { Search, SlidersHorizontal } from "lucide-react";
import { GameCard, Game } from "./GameCard";
import { Footer } from "./Footer";

interface ExplorePageProps {
  onNavigate: (page: string, gameId?: string) => void;
}

export function ExplorePage({ onNavigate }: ExplorePageProps) {
  const [searchQuery, setSearchQuery] = useState("");
  const [showFilters, setShowFilters] = useState(true);
  const [filters, setFilters] = useState({
    platform: "",
    itemType: "",
    location: "",
    condition: "",
    priceRange: "",
    transactionType: "",
  });

  const allGames: Game[] = [
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
    {
      id: "7",
      title: "God of War Ragnarök",
      console: "PlayStation 5",
      condition: "Nuevo",
      image: "https://images.unsplash.com/photo-1641564341083-161e6aa17a8b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxnYW1lciUyMHBsYXlpbmd8ZW58MXx8fHwxNzYyMjgwMDEzfDA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Sevilla",
      rating: 5.0,
      price: 60,
      type: "both",
      itemType: "videogame",
      platform: "PlayStation 5",
    },
    {
      id: "8",
      title: "Cartas Pokémon - Charizard Edición Limitada",
      console: "Pokémon TCG",
      condition: "Nuevo",
      image: "https://images.unsplash.com/photo-1742743032749-187b17179e0f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHx0cmFkaW5nJTIwY2FyZHMlMjBjb2xsZWN0aWJsZXxlbnwxfHx8fDE3NzM2ODUzMTF8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Valencia",
      rating: 4.8,
      price: 250,
      type: "sale",
      itemType: "collectible",
      platform: "Trading Cards",
    },
    {
      id: "9",
      title: "Colección Figuras Anime",
      console: "Varios",
      condition: "Muy bueno",
      image: "https://images.unsplash.com/photo-1751110479291-36300997a064?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w3Nzg4Nzd8MHwxfHNlYXJjaHwxfHxjb2xsZWN0aWJsZSUyMHRveXMlMjBzaGVsZnxlbnwxfHx8fDE3NzM2ODUzMTR8MA&ixlib=rb-4.1.0&q=80&w=1080&utm_source=figma&utm_medium=referral",
      location: "Madrid",
      rating: 4.5,
      price: 180,
      type: "both",
      itemType: "figure",
      platform: "Figura coleccionable",
    },
  ];

  const filteredGames = useMemo(() => {
    return allGames.filter((game) => {
      const matchesSearch =
        searchQuery === "" ||
        game.title.toLowerCase().includes(searchQuery.toLowerCase()) ||
        game.console.toLowerCase().includes(searchQuery.toLowerCase()) ||
        game.platform?.toLowerCase().includes(searchQuery.toLowerCase());

      const matchesPlatform =
        !filters.platform ||
        (filters.platform === "ps5" && game.platform === "PlayStation 5") ||
        (filters.platform === "xbox" && game.platform?.includes("Xbox")) ||
        (filters.platform === "switch" && game.platform === "Nintendo Switch") ||
        (filters.platform === "pc" && game.platform === "PC");

      const matchesItemType =
        !filters.itemType ||
        game.itemType === filters.itemType;

      const matchesCondition =
        !filters.condition ||
        (filters.condition === "new" && game.condition === "Nuevo") ||
        (filters.condition === "like-new" && game.condition === "Como nuevo") ||
        (filters.condition === "very-good" && game.condition === "Muy bueno") ||
        (filters.condition === "good" && game.condition === "Bueno");

      const matchesLocation =
        !filters.location ||
        game.location?.toLowerCase() === filters.location.toLowerCase();

      const matchesPriceRange =
        !filters.priceRange ||
        (filters.priceRange === "0-25" && (game.price || 0) <= 25) ||
        (filters.priceRange === "25-50" && (game.price || 0) > 25 && (game.price || 0) <= 50) ||
        (filters.priceRange === "50-100" && (game.price || 0) > 50 && (game.price || 0) <= 100) ||
        (filters.priceRange === "100+" && (game.price || 0) > 100);

      const matchesTransactionType =
        !filters.transactionType ||
        game.type === filters.transactionType ||
        game.type === "both";

      return matchesSearch && matchesPlatform && matchesItemType && matchesCondition && 
             matchesLocation && matchesPriceRange && matchesTransactionType;
    });
  }, [allGames, searchQuery, filters]);

  return (
    <div className="min-h-screen bg-gray-50 pt-20">
      {/* Header */}
      <div className="bg-gradient-to-r from-primary to-blue-600 py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <h1 className="text-white text-4xl font-bold mb-3">
            Catálogo de productos
          </h1>
          <p className="text-blue-100 text-lg mb-6">
            Descubre videojuegos, figuras, cómics y artículos de colección
          </p>

          {/* Search Bar */}
          <div className="max-w-3xl">
            <div className="relative">
              <Search className="absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Busca por nombre, categoría, plataforma..."
                className="w-full pl-12 pr-4 py-4 rounded-lg bg-white shadow-lg focus:outline-none focus:ring-2 focus:ring-white transition-all"
              />
            </div>
          </div>
        </div>
      </div>

      {/* Content */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="flex items-center justify-between mb-6">
          <div>
            <p className="text-gray-700">
              Se encontraron <span className="text-primary font-bold">{filteredGames.length}</span> productos
            </p>
          </div>
          <button
            onClick={() => setShowFilters(!showFilters)}
            className="flex items-center gap-2 px-4 py-2 rounded-lg bg-white border border-gray-200 hover:border-primary hover:bg-primary/5 transition-all"
          >
            <SlidersHorizontal className="w-5 h-5 text-primary" />
            <span className="text-gray-700 font-medium">
              {showFilters ? "Ocultar filtros" : "Mostrar filtros"}
            </span>
          </button>
        </div>

        {/* Filters */}
        {showFilters && (
          <div className="bg-white rounded-xl border border-gray-200 p-6 mb-8">
            <h3 className="font-semibold text-gray-900 mb-4">Filtrar por:</h3>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
              <div>
                <label className="block text-gray-700 text-sm font-medium mb-2">
                  Tipo de artículo
                </label>
                <select
                  value={filters.itemType}
                  onChange={(e) => setFilters({ ...filters, itemType: e.target.value })}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary bg-white"
                >
                  <option value="">Todos</option>
                  <option value="videogame">Videojuegos</option>
                  <option value="figure">Figuras</option>
                  <option value="comic">Cómics</option>
                  <option value="collectible">Coleccionables</option>
                </select>
              </div>
              
              <div>
                <label className="block text-gray-700 text-sm font-medium mb-2">
                  Plataforma
                </label>
                <select
                  value={filters.platform}
                  onChange={(e) => setFilters({ ...filters, platform: e.target.value })}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary bg-white"
                >
                  <option value="">Todas</option>
                  <option value="ps5">PlayStation 5</option>
                  <option value="xbox">Xbox Series X/S</option>
                  <option value="switch">Nintendo Switch</option>
                  <option value="pc">PC</option>
                </select>
              </div>

              <div>
                <label className="block text-gray-700 text-sm font-medium mb-2">
                  Estado
                </label>
                <select
                  value={filters.condition}
                  onChange={(e) => setFilters({ ...filters, condition: e.target.value })}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary bg-white"
                >
                  <option value="">Cualquiera</option>
                  <option value="new">Nuevo</option>
                  <option value="like-new">Como nuevo</option>
                  <option value="very-good">Muy bueno</option>
                  <option value="good">Bueno</option>
                </select>
              </div>

              <div>
                <label className="block text-gray-700 text-sm font-medium mb-2">
                  Rango de precio
                </label>
                <select
                  value={filters.priceRange}
                  onChange={(e) => setFilters({ ...filters, priceRange: e.target.value })}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary bg-white"
                >
                  <option value="">Todos</option>
                  <option value="0-25">0€ - 25€</option>
                  <option value="25-50">25€ - 50€</option>
                  <option value="50-100">50€ - 100€</option>
                  <option value="100+">100€+</option>
                </select>
              </div>

              <div>
                <label className="block text-gray-700 text-sm font-medium mb-2">
                  Tipo de transacción
                </label>
                <select
                  value={filters.transactionType}
                  onChange={(e) => setFilters({ ...filters, transactionType: e.target.value })}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary bg-white"
                >
                  <option value="">Todas</option>
                  <option value="sale">Venta</option>
                  <option value="exchange">Intercambio</option>
                </select>
              </div>

              <div>
                <label className="block text-gray-700 text-sm font-medium mb-2">
                  Ubicación
                </label>
                <select
                  value={filters.location}
                  onChange={(e) => setFilters({ ...filters, location: e.target.value })}
                  className="w-full px-4 py-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary bg-white"
                >
                  <option value="">Todas</option>
                  <option value="madrid">Madrid</option>
                  <option value="barcelona">Barcelona</option>
                  <option value="valencia">Valencia</option>
                  <option value="sevilla">Sevilla</option>
                  <option value="bilbao">Bilbao</option>
                </select>
              </div>
            </div>

            <button
              onClick={() => setFilters({
                platform: "",
                itemType: "",
                location: "",
                condition: "",
                priceRange: "",
                transactionType: "",
              })}
              className="mt-4 text-sm text-primary hover:underline"
            >
              Limpiar filtros
            </button>
          </div>
        )}

        {/* Games Grid */}
        {filteredGames.length > 0 ? (
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
            {filteredGames.map((game) => (
              <GameCard
                key={game.id}
                game={game}
                onClick={() => onNavigate("game", game.id)}
              />
            ))}
          </div>
        ) : (
          <div className="text-center py-16">
            <div className="text-gray-400 mb-4">
              <Search className="w-16 h-16 mx-auto" />
            </div>
            <h3 className="text-xl font-semibold text-gray-900 mb-2">
              No se encontraron productos
            </h3>
            <p className="text-gray-600">
              Intenta ajustar tus filtros de búsqueda
            </p>
          </div>
        )}

        {/* Pagination */}
        {filteredGames.length > 0 && (
          <div className="mt-12 flex justify-center">
            <button className="px-6 py-3 rounded-lg border border-gray-300 bg-white hover:bg-gray-50 text-gray-700 font-medium transition-colors">
              Cargar más productos
            </button>
          </div>
        )}
      </div>

      <Footer onNavigate={onNavigate} />
    </div>
  );
}
