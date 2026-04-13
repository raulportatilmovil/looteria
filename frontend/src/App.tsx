import { useState, useEffect } from "react";
import { Navbar } from "./components/Navbar";
import { HomePage } from "./components/HomePage";
import { LoginRegisterPage } from "./components/LoginRegisterPage";
import { GameDetailPage } from "./components/GameDetailPage";
import { ExplorePage } from "./components/ExplorePage";
import { UserProfilePage } from "./components/UserProfilePage";
import { ExchangesPage } from "./components/ExchangesPage";
import { FAQPage } from "./components/FAQPage";
import AdminPanel from "./components/AdminPanel";
import { Toaster } from "./components/ui/sonner";
import { AuthProvider, useAuth } from "./context/AuthContext";

type Page = "home" | "login" | "game" | "explore" | "profile" | "exchanges" | "faq" | "admin" | "create-listing" | "cart" | "tracking";

function AppContent() {
  const [currentPage, setCurrentPage] = useState<Page>("home");
  const [selectedGameId, setSelectedGameId] = useState<string>("1");
  const { user } = useAuth();

  const handleNavigate = (page: string, gameId?: string) => {
  
    if (user?.rol === 'ADMIN' && page === 'profile') {
      setCurrentPage('admin' as Page);
      window.scrollTo({ top: 0, behavior: "smooth" });
      return;
    }

    if (user && user?.rol !== 'ADMIN' && page === 'admin') {
      setCurrentPage('home' as Page);
      window.scrollTo({ top: 0, behavior: "smooth" });
      return;
    }
    setCurrentPage(page as Page);
    if (gameId) {
      setSelectedGameId(gameId);
    }
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  useEffect(() => {
    const handleCustomNavigate = (event: Event) => {
      const customEvent = event as CustomEvent;
      handleNavigate(customEvent.detail);
    };

    window.addEventListener('navigate', handleCustomNavigate);
    return () => window.removeEventListener('navigate', handleCustomNavigate);
  }, []);


  const getNavbarRole = () => {
    if (!user) return "guest";
    if (user.rol === 'ADMIN') return "admin";
    if (user.rol === 'REGISTRADO') return "registered";
    return "guest";
  };

  return (
    <div className="min-h-screen bg-white">
      <Toaster position="top-right" />
      {currentPage !== "login" && (
        <Navbar 
          onNavigate={handleNavigate} 
          currentPage={currentPage}
          userRole={getNavbarRole() as "guest" | "registered" | "admin"}
        />
      )}

      {currentPage === "home" && <HomePage onNavigate={handleNavigate} />}
      {currentPage === "login" && <LoginRegisterPage onNavigate={handleNavigate} />}
      {currentPage === "game" && (
        <GameDetailPage gameId={selectedGameId} onNavigate={handleNavigate} userRole={getNavbarRole() as "guest" | "registered" | "admin"} />
      )}
      {currentPage === "explore" && <ExplorePage onNavigate={handleNavigate} />}
      {currentPage === "admin" && <AdminPanel />}
      {currentPage === "profile" && <UserProfilePage onNavigate={handleNavigate} userRole={getNavbarRole() as "guest" | "registered" | "admin"} />}
      {currentPage === "exchanges" && <ExchangesPage onNavigate={handleNavigate} />}
      {currentPage === "faq" && <FAQPage onNavigate={handleNavigate} />}
    </div>
  );
}

export default function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}