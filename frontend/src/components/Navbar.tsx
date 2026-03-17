import { Search, Menu, X, ShoppingCart, User, Plus, PackageOpen } from "lucide-react";
import { useState } from "react";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "./ui/select";

interface NavbarProps {
  onNavigate: (page: string) => void;
  currentPage: string;
  userRole?: "guest" | "registered" | "admin";
  onRoleChange?: (role: "guest" | "registered" | "admin") => void;
}

export function Navbar({ onNavigate, currentPage, userRole = "guest", onRoleChange }: NavbarProps) {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");

  const handleSearch = (e: React.FormEvent) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      onNavigate("explore");
    }
  };

  return (
    <nav className="fixed top-0 left-0 right-0 z-50 bg-white/90 backdrop-blur-lg border-b border-gray-200 shadow-sm">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16 gap-4">
          {/* Logo */}
          <button
            onClick={() => onNavigate("home")}
            className="flex items-center space-x-2 cursor-pointer flex-shrink-0"
          >
            <div className="w-10 h-10 rounded-lg bg-gradient-to-br from-[#1E40AF] to-[#3B82F6] flex items-center justify-center">
              <PackageOpen className="w-5 h-5 text-white" />
            </div>
            <span className="text-gray-900 text-xl font-bold">
              Looteria
            </span>
          </button>

          {/* Search Bar - Desktop */}
          <form onSubmit={handleSearch} className="hidden lg:flex items-center flex-1 max-w-md">
            <div className="relative w-full">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
              <input
                type="text"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                placeholder="Buscar productos..."
                className="w-full pl-10 pr-4 py-2 rounded-lg bg-gray-50 border border-gray-200 focus:outline-none focus:ring-2 focus:ring-primary transition-all"
              />
            </div>
          </form>

          {/* Desktop Navigation */}
          <div className="hidden md:flex items-center space-x-1 flex-shrink-0">
            <button
              onClick={() => onNavigate("home")}
              className={`px-4 py-2 rounded-lg transition-colors ${
                currentPage === "home"
                  ? "text-primary bg-accent"
                  : "text-gray-700 hover:text-primary hover:bg-gray-50"
              }`}
            >
              Inicio
            </button>
            <button
              onClick={() => onNavigate("explore")}
              className={`px-4 py-2 rounded-lg transition-colors ${
                currentPage === "explore"
                  ? "text-primary bg-accent"
                  : "text-gray-700 hover:text-primary hover:bg-gray-50"
              }`}
            >
              Catálogo
            </button>
            {userRole !== "guest" && (
              <button
                onClick={() => onNavigate("profile")}
                className={`px-4 py-2 rounded-lg transition-colors ${
                  currentPage === "profile"
                    ? "text-primary bg-accent"
                    : "text-gray-700 hover:text-primary hover:bg-gray-50"
                }`}
              >
                Mis publicaciones
              </button>
            )}
            {userRole === "admin" && (
              <button
                onClick={() => onNavigate("admin")}
                className={`px-4 py-2 rounded-lg transition-colors ${
                  currentPage === "admin"
                    ? "text-primary bg-accent"
                    : "text-gray-700 hover:text-primary hover:bg-gray-50"
                }`}
              >
                Admin
              </button>
            )}
          </div>

          {/* Desktop Buttons */}
          <div className="hidden md:flex items-center space-x-3 flex-shrink-0">
            {userRole !== "guest" && (
              <>
                <button
                  onClick={() => onNavigate("create-listing")}
                  className="p-2 text-gray-700 hover:text-primary hover:bg-gray-50 rounded-lg transition-colors"
                  title="Crear publicación"
                >
                  <Plus className="w-5 h-5" />
                </button>
                <button
                  onClick={() => onNavigate("cart")}
                  className="p-2 text-gray-700 hover:text-primary hover:bg-gray-50 rounded-lg transition-colors relative"
                  title="Carrito"
                >
                  <ShoppingCart className="w-5 h-5" />
                  <span className="absolute -top-1 -right-1 bg-destructive text-white text-xs w-5 h-5 rounded-full flex items-center justify-center">
                    2
                  </span>
                </button>
                <button
                  onClick={() => onNavigate("profile")}
                  className="p-2 text-gray-700 hover:text-primary hover:bg-gray-50 rounded-lg transition-colors"
                  title="Perfil"
                >
                  <User className="w-5 h-5" />
                </button>
              </>
            )}
            
            {userRole === "guest" ? (
              <>
                <button
                  onClick={() => onNavigate("login")}
                  className="px-4 py-2 text-gray-700 hover:text-primary transition-colors"
                >
                  Iniciar sesión
                </button>
                <button
                  onClick={() => onNavigate("login")}
                  className="px-6 py-2 rounded-lg bg-primary text-white hover:bg-primary/90 transition-all"
                >
                  Registrarse
                </button>
              </>
            ) : null}

            {/* Role Selector (for demo purposes) */}
            <div className="border-l border-gray-200 pl-3">
              <Select value={userRole} onValueChange={(value) => onRoleChange?.(value as "guest" | "registered" | "admin")}>
                <SelectTrigger className="w-32 h-9 text-sm">
                  <SelectValue />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="guest">Invitado</SelectItem>
                  <SelectItem value="registered">Registrado</SelectItem>
                  <SelectItem value="admin">Admin</SelectItem>
                </SelectContent>
              </Select>
            </div>
          </div>

          {/* Mobile Menu Button */}
          <button
            onClick={() => setIsMenuOpen(!isMenuOpen)}
            className="md:hidden p-2 text-gray-700"
          >
            {isMenuOpen ? <X className="w-6 h-6" /> : <Menu className="w-6 h-6" />}
          </button>
        </div>
      </div>

      {/* Mobile Menu */}
      {isMenuOpen && (
        <div className="md:hidden bg-white border-t border-gray-200">
          <div className="px-4 py-4 space-y-3">
            <form onSubmit={handleSearch}>
              <div className="relative">
                <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                <input
                  type="text"
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                  placeholder="Buscar productos..."
                  className="w-full pl-10 pr-4 py-2 rounded-lg bg-gray-50 border border-gray-200 focus:outline-none focus:ring-2 focus:ring-primary"
                />
              </div>
            </form>

            <div className="pt-2 space-y-1">
              <button
                onClick={() => {
                  onNavigate("home");
                  setIsMenuOpen(false);
                }}
                className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-50 rounded-lg"
              >
                Inicio
              </button>
              <button
                onClick={() => {
                  onNavigate("explore");
                  setIsMenuOpen(false);
                }}
                className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-50 rounded-lg"
              >
                Catálogo
              </button>
              {userRole !== "guest" && (
                <>
                  <button
                    onClick={() => {
                      onNavigate("profile");
                      setIsMenuOpen(false);
                    }}
                    className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-50 rounded-lg"
                  >
                    Mis publicaciones
                  </button>
                  <button
                    onClick={() => {
                      onNavigate("create-listing");
                      setIsMenuOpen(false);
                    }}
                    className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-50 rounded-lg"
                  >
                    Crear publicación
                  </button>
                </>
              )}
              {userRole === "admin" && (
                <button
                  onClick={() => {
                    onNavigate("admin");
                    setIsMenuOpen(false);
                  }}
                  className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-50 rounded-lg"
                >
                  Panel Admin
                </button>
              )}
            </div>

            <div className="pt-2 border-t border-gray-200">
              <div className="mb-3">
                <label className="block text-sm text-gray-600 mb-2">Rol de usuario:</label>
                <Select value={userRole} onValueChange={(value) => onRoleChange?.(value as "guest" | "registered" | "admin")}>
                  <SelectTrigger className="w-full">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="guest">Invitado</SelectItem>
                    <SelectItem value="registered">Registrado</SelectItem>
                    <SelectItem value="admin">Admin</SelectItem>
                  </SelectContent>
                </Select>
              </div>

              {userRole === "guest" ? (
                <>
                  <button
                    onClick={() => {
                      onNavigate("login");
                      setIsMenuOpen(false);
                    }}
                    className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-50 rounded-lg mb-2"
                  >
                    Iniciar sesión
                  </button>
                  <button
                    onClick={() => {
                      onNavigate("login");
                      setIsMenuOpen(false);
                    }}
                    className="block w-full px-4 py-2 rounded-lg bg-primary text-white text-center"
                  >
                    Registrarse
                  </button>
                </>
              ) : null}
            </div>
          </div>
        </div>
      )}
    </nav>
  );
}
