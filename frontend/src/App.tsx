import { useState, useEffect } from "react";
import { Navbar } from "./components/Navbar";
import { HomePage } from "./components/HomePage";
import { LoginRegisterPage } from "./components/LoginRegisterPage";
import { GameDetailPage } from "./components/GameDetailPage";
import { ExplorePage } from "./components/ExplorePage";
import { UserProfilePage } from "./components/UserProfilePage";
import { ExchangesPage } from "./components/ExchangesPage";
import { FAQPage } from "./components/FAQPage";
import { Toaster } from "./components/ui/sonner";

type Page = "home" | "login" | "game" | "explore" | "profile" | "exchanges" | "faq" | "admin" | "create-listing" | "cart" | "tracking";
type UserRole = "guest" | "registered" | "admin";

export default function App() {
  const [currentPage, setCurrentPage] = useState<Page>("home");
  const [selectedGameId, setSelectedGameId] = useState<string>("1");
  const [userRole, setUserRole] = useState<UserRole>("guest");

  const handleNavigate = (page: string, gameId?: string) => {
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

  return (
    <div className="min-h-screen bg-white">
      <Toaster position="top-right" />
      {currentPage !== "login" && (
        <Navbar 
          onNavigate={handleNavigate} 
          currentPage={currentPage}
          userRole={userRole}
          onRoleChange={setUserRole}
        />
      )}

      {currentPage === "home" && <HomePage onNavigate={handleNavigate} />}
      {currentPage === "login" && <LoginRegisterPage onNavigate={handleNavigate} />}
      {currentPage === "game" && (
        <GameDetailPage gameId={selectedGameId} onNavigate={handleNavigate} userRole={userRole} />
      )}
      {currentPage === "explore" && <ExplorePage onNavigate={handleNavigate} />}
      {currentPage === "profile" && <UserProfilePage onNavigate={handleNavigate} userRole={userRole} />}
      {currentPage === "exchanges" && <ExchangesPage onNavigate={handleNavigate} />}
      {currentPage === "faq" && <FAQPage onNavigate={handleNavigate} />}
    </div>
  );
}