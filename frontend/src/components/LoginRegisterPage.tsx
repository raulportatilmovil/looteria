import { useState } from "react";
import { Mail, Lock, User, MapPin, Eye, EyeOff, PackageOpen, AlertCircle, CheckCircle } from "lucide-react";
import { Button } from "./ui/button";
import { Checkbox } from "./ui/checkbox";
import authService from "../api/services/authService.ts";

interface LoginRegisterPageProps {
  onNavigate: (page: string) => void;
}

export function LoginRegisterPage({ onNavigate }: LoginRegisterPageProps) {
  const [isLogin, setIsLogin] = useState(true);
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    location: "",
    password: "",
    confirmPassword: "",
    acceptTerms: false,
    verifyIdentity: false,
  });

  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setMessage(null);

    try {
      if (isLogin) {
        // LOGIN
        await authService.login(formData.email, formData.password);
        setMessage({ type: 'success', text: 'Login exitoso. Redirigiendo...' });
        setTimeout(() => onNavigate('explore'), 1500);
      } else {
        // REGISTRO
        if (formData.password !== formData.confirmPassword) {
          setMessage({ type: 'error', text: 'Las contraseñas no coinciden' });
          setLoading(false);
          return;
        }

        if (!formData.acceptTerms) {
          setMessage({ type: 'error', text: 'Debes aceptar los términos y condiciones' });
          setLoading(false);
          return;
        }

        await authService.register(formData.email, formData.username, formData.password);
        setMessage({ type: 'success', text: '¡Cuenta creada exitosamente! Redirigiendo...' });
        setTimeout(() => onNavigate('explore'), 1500);
      }
    } catch (error: any) {
      setMessage({ type: 'error', text: error.response?.data?.message || 'Error de conexión con el servidor' });
      console.error('Error:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen relative flex items-center justify-center py-20 px-4 overflow-hidden bg-gradient-to-br from-primary to-blue-600">
      {/* Decorative background elements */}
      <div className="absolute inset-0 opacity-10">
        <div className="absolute top-0 right-0 w-96 h-96 bg-white rounded-full mix-blend-multiply filter blur-3xl animate-blob" />
        <div className="absolute bottom-0 left-0 w-96 h-96 bg-white rounded-full mix-blend-multiply filter blur-3xl animate-blob animation-delay-2000" />
      </div>

      {/* Content */}
      <div className="relative z-10 w-full max-w-md">
        {/* Logo */}
        <button
          onClick={() => onNavigate("home")}
          className="flex items-center justify-center space-x-2 mb-8 mx-auto"
        >
          <div className="w-12 h-12 rounded-lg bg-white flex items-center justify-center">
            <PackageOpen className="w-7 h-7 text-primary" />
          </div>
          <span className="text-white text-2xl font-bold">
            Looteria
          </span>
        </button>

        {/* Form Card */}
        <div className="bg-white rounded-2xl shadow-2xl p-8">
          {/* Tabs */}
          <div className="flex gap-2 mb-8 bg-gray-100 p-1 rounded-lg">
            <button
              onClick={() => setIsLogin(true)}
              className={`flex-1 py-3 px-4 rounded-lg font-medium transition-all ${
                isLogin
                  ? "bg-white text-primary shadow-sm"
                  : "text-gray-600 hover:text-gray-900"
              }`}
            >
              Iniciar sesión
            </button>
            <button
              onClick={() => setIsLogin(false)}
              className={`flex-1 py-3 px-4 rounded-lg font-medium transition-all ${
                !isLogin
                  ? "bg-white text-primary shadow-sm"
                  : "text-gray-600 hover:text-gray-900"
              }`}
            >
              Registrarse
            </button>
          </div>

          {/* Form */}
          <form onSubmit={handleSubmit} className="space-y-5">
            {message && (
              <div className={`p-4 rounded-lg flex items-center gap-3 ${
                message.type === 'success' 
                  ? 'bg-green-100 text-green-800' 
                  : 'bg-red-100 text-red-800'
              }`}>
                {message.type === 'success' ? (
                  <CheckCircle className="w-5 h-5 flex-shrink-0" />
                ) : (
                  <AlertCircle className="w-5 h-5 flex-shrink-0" />
                )}
                <span className="text-sm">{message.text}</span>
              </div>
            )}
            {!isLogin && (
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Nombre de usuario
                </label>
                <div className="relative">
                  <User className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                  <input
                    type="text"
                    value={formData.username}
                    onChange={(e) =>
                      setFormData({ ...formData, username: e.target.value })
                    }
                    placeholder="Tu nombre de usuario"
                    className="w-full pl-10 pr-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary transition-all"
                    required={!isLogin}
                  />
                </div>
              </div>
            )}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Email
              </label>
              <div className="relative">
                <Mail className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                <input
                  type="email"
                  value={formData.email}
                  onChange={(e) =>
                    setFormData({ ...formData, email: e.target.value })
                  }
                  placeholder="tu@email.com"
                  className="w-full pl-10 pr-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary transition-all"
                  required
                />
              </div>
            </div>

            {!isLogin && (
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Ubicación
                </label>
                <div className="relative">
                  <MapPin className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                  <input
                    type="text"
                    value={formData.location}
                    onChange={(e) =>
                      setFormData({ ...formData, location: e.target.value })
                    }
                    placeholder="Tu ciudad"
                    className="w-full pl-10 pr-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary transition-all"
                    required={!isLogin}
                  />
                </div>
              </div>
            )}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Contraseña
              </label>
              <div className="relative">
                <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                <input
                  type={showPassword ? "text" : "password"}
                  value={formData.password}
                  onChange={(e) =>
                    setFormData({ ...formData, password: e.target.value })
                  }
                  placeholder="••••••••"
                  className="w-full pl-10 pr-12 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary transition-all"
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 hover:text-gray-600"
                >
                  {showPassword ? (
                    <EyeOff className="w-5 h-5" />
                  ) : (
                    <Eye className="w-5 h-5" />
                  )}
                </button>
              </div>
            </div>

            {!isLogin && (
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Confirmar contraseña
                </label>
                <div className="relative">
                  <Lock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
                  <input
                    type={showPassword ? "text" : "password"}
                    value={formData.confirmPassword}
                    onChange={(e) =>
                      setFormData({ ...formData, confirmPassword: e.target.value })
                    }
                    placeholder="••••••••"
                    className="w-full pl-10 pr-4 py-3 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-primary transition-all"
                    required={!isLogin}
                  />
                </div>
              </div>
            )}

            {isLogin && (
              <div className="flex items-center justify-between">
                <label className="flex items-center">
                  <input
                    type="checkbox"
                    className="w-4 h-4 text-primary border-gray-300 rounded focus:ring-primary"
                  />
                  <span className="ml-2 text-sm text-gray-600">Recordarme</span>
                </label>
                <button
                  type="button"
                  className="text-sm text-primary hover:underline"
                >
                  ¿Olvidaste tu contraseña?
                </button>
              </div>
            )}

            {!isLogin && (
              <div className="space-y-3">
                <label className="flex items-start gap-3">
                  <Checkbox
                    checked={formData.acceptTerms}
                    onCheckedChange={(checked: boolean) =>
                      setFormData({ ...formData, acceptTerms: !!checked })
                    }
                    className="mt-1"
                  />
                  <span className="text-sm text-gray-600">
                    Acepto los{" "}
                    <a href="#" className="text-primary hover:underline">
                      términos y condiciones
                    </a>{" "}
                    y la{" "}
                    <a href="#" className="text-primary hover:underline">
                      política de privacidad
                    </a>
                  </span>
                </label>
                <label className="flex items-start gap-3">
                  <Checkbox
                    checked={formData.verifyIdentity}
                    onCheckedChange={(checked: boolean) =>
                      setFormData({ ...formData, verifyIdentity: !!checked })
                    }
                    className="mt-1"
                  />
                  <span className="text-sm text-gray-600">
                    Quiero verificar mi identidad para obtener la insignia de usuario verificado
                  </span>
                </label>
              </div>
            )}

            <Button
              type="submit"
              disabled={loading}
              className="w-full py-6 text-lg bg-primary hover:bg-primary/90 disabled:opacity-50 disabled:cursor-not-allowed"
            >
              {loading ? (
                <>
                  <span className="inline-block animate-spin mr-2">⏳</span>
                  {isLogin ? "Iniciando sesión..." : "Creando cuenta..."}
                </>
              ) : (
                isLogin ? "Iniciar sesión" : "Crear cuenta"
              )}
            </Button>
          </form>

          {/* Divider */}
          <div className="relative my-6">
            <div className="absolute inset-0 flex items-center">
              <div className="w-full border-t border-gray-300" />
            </div>
            <div className="relative flex justify-center text-sm">
              <span className="px-4 bg-white text-gray-500">O continúa con</span>
            </div>
          </div>

          {/* Social Login */}
          <div className="grid grid-cols-2 gap-3">
            <Button variant="outline" className="w-full">
              <svg className="w-5 h-5 mr-2" viewBox="0 0 24 24">
                <path
                  fill="currentColor"
                  d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z"
                />
                <path
                  fill="currentColor"
                  d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z"
                />
                <path
                  fill="currentColor"
                  d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z"
                />
                <path
                  fill="currentColor"
                  d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z"
                />
              </svg>
              Google
            </Button>
            <Button variant="outline" className="w-full">
              <svg className="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 24 24">
                <path d="M12 0c-6.626 0-12 5.373-12 12 0 5.302 3.438 9.8 8.207 11.387.599.111.793-.261.793-.577v-2.234c-3.338.726-4.033-1.416-4.033-1.416-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.729.083-.729 1.205.084 1.839 1.237 1.839 1.237 1.07 1.834 2.807 1.304 3.492.997.107-.775.418-1.305.762-1.604-2.665-.305-5.467-1.334-5.467-5.931 0-1.311.469-2.381 1.236-3.221-.124-.303-.535-1.524.117-3.176 0 0 1.008-.322 3.301 1.23.957-.266 1.983-.399 3.003-.404 1.02.005 2.047.138 3.006.404 2.291-1.552 3.297-1.23 3.297-1.23.653 1.653.242 2.874.118 3.176.77.84 1.235 1.911 1.235 3.221 0 4.609-2.807 5.624-5.479 5.921.43.372.823 1.102.823 2.222v3.293c0 .319.192.694.801.576 4.765-1.589 8.199-6.086 8.199-11.386 0-6.627-5.373-12-12-12z"/>
              </svg>
              GitHub
            </Button>
          </div>

          {/* Register Link */}
          <div className="mt-6 text-center text-sm text-gray-600">
            {isLogin ? (
              <>
                ¿No tienes cuenta?{" "}
                <button
                  type="button"
                  onClick={() => setIsLogin(false)}
                  className="text-primary font-medium hover:underline"
                >
                  Regístrate aquí
                </button>
              </>
            ) : (
              <>
                ¿Ya tienes cuenta?{" "}
                <button
                  type="button"
                  onClick={() => setIsLogin(true)}
                  className="text-primary font-medium hover:underline"
                >
                  Inicia sesión
                </button>
              </>
            )}
          </div>
        </div>

        {/* Back to Home */}
        <button
          onClick={() => onNavigate("home")}
          className="mt-6 w-full text-center text-white hover:text-white/80 transition-colors"
        >
          ← Volver a inicio
        </button>
      </div>

      <style>{`
        @keyframes blob {
          0%, 100% { transform: translate(0, 0) scale(1); }
          33% { transform: translate(30px, -50px) scale(1.1); }
          66% { transform: translate(-20px, 20px) scale(0.9); }
        }
        .animate-blob {
          animation: blob 7s infinite;
        }
        .animation-delay-2000 {
          animation-delay: 2s;
        }
      `}</style>
    </div>
  );
}
