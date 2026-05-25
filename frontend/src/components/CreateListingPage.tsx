import { useState } from "react";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import { Upload, X, AlertCircle } from "lucide-react";
import { useAuth } from "../context/AuthContext";
import profileService from "../api/services/profileService";

interface CreateListingPageProps {
  onNavigate: (page: string, listingId?: string) => void;
}

export function CreateListingPage({ onNavigate }: CreateListingPageProps) {
  const { user } = useAuth();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [previewImages, setPreviewImages] = useState<string[]>([]);
  const [imageFiles, setImageFiles] = useState<File[]>([]);

  const [formData, setFormData] = useState({
    producto: "",
    descripcion: "",
    plataforma: "PlayStation 5",
    tipoTransaccion: "VENTA",
    precio: 0,
    estado: "Como nuevo",
    idioma: "Español",
    region: "Europa",
    especificaciones: "",
    descripcionEstado: "",
  });

  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = Array.from(e.target.files || []);
    const remaining = 3 - previewImages.length;
    const toAdd = files.slice(0, remaining);
    toAdd.forEach((file) => {
      const reader = new FileReader();
      reader.onload = (event) => {
        setPreviewImages((prev) => [...prev, event.target?.result as string]);
      };
      reader.readAsDataURL(file);
      setImageFiles((prev) => [...prev, file]);
    });
  };

  const removeImage = (index: number) => {
    setPreviewImages((prev) => prev.filter((_, i) => i !== index));
    setImageFiles((prev) => prev.filter((_, i) => i !== index));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      if (!user?.idUsuario) {
        throw new Error("Usuario no autenticado");
      }

      if (!formData.producto.trim()) {
        throw new Error("El título es requerido");
      }

      if (formData.tipoTransaccion === "VENTA" && formData.precio <= 0) {
        throw new Error("El precio debe ser mayor a 0");
      }

      const listing = await profileService.createListing(user.idUsuario, formData);
      const listingId = listing?.idPublicacion;
      if (listingId && imageFiles.length > 0) {
        for (const file of imageFiles) {
          try {
            await profileService.uploadImage(listingId, file);
          } catch (imgErr) {
            console.error("Error subiendo imagen:", imgErr);
          }
        }
      }
      onNavigate("profile");
    } catch (err) {
      setError(err instanceof Error ? err.message : "Error al crear publicación");
      console.error("Error creating listing:", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 pt-20 pb-12">
      <div className="max-w-4xl mx-auto px-4">
        {/* Header */}
        <div className="mb-8">
          <h1 className="text-4xl font-bold text-gray-900 mb-2">Crear nueva publicación</h1>
          <p className="text-gray-600">Completa los detalles de tu artículo para publicar</p>
        </div>

        {/* Error Banner */}
        {error && (
          <div className="mb-6 bg-red-50 border border-red-200 rounded-lg p-4 flex gap-3">
            <AlertCircle className="w-5 h-5 text-red-600 flex-shrink-0 mt-0.5" />
            <div>
              <h3 className="font-semibold text-red-900">Error</h3>
              <p className="text-red-700 text-sm">{error}</p>
            </div>
          </div>
        )}

        {/* Form */}
        <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-sm border border-gray-200 p-8">
          {/* Sección 1: Información Básica */}
          <div className="mb-8">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Información básica</h2>
            <div className="space-y-6">
              {/* Título */}
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Título del artículo *
                </label>
                <Input
                  type="text"
                  placeholder="p.ej. Elden Ring - PS5"
                  value={formData.producto}
                  onChange={(e) =>
                    setFormData({ ...formData, producto: e.target.value })
                  }
                  className="w-full"
                />
                <p className="text-xs text-gray-500 mt-1">
                  Sé específico y descriptivo
                </p>
              </div>

              {/* Descripción */}
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Descripción completa *
                </label>
                <textarea
                  placeholder="Cuéntanos más detalles sobre el artículo..."
                  value={formData.descripcion}
                  onChange={(e) =>
                    setFormData({ ...formData, descripcion: e.target.value })
                  }
                  rows={5}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary text-sm"
                />
              </div>

              {/* Plataforma */}
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Plataforma
                </label>
                <select
                  value={formData.plataforma}
                  onChange={(e) =>
                    setFormData({ ...formData, plataforma: e.target.value })
                  }
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                >
                  <option>PlayStation 5</option>
                  <option>PlayStation 4</option>
                  <option>Xbox Series X</option>
                  <option>Xbox One</option>
                  <option>Nintendo Switch</option>
                  <option>PC</option>
                </select>
              </div>
            </div>
          </div>

          {/* Sección 2: Tipo de Transacción */}
          <div className="mb-8 pb-8 border-b border-gray-200">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Tipo de transacción</h2>
            <div className="grid grid-cols-2 gap-4">
              {["VENTA", "INTERCAMBIO"].map((tipo) => (
                <button
                  key={tipo}
                  type="button"
                  onClick={() =>
                    setFormData({ ...formData, tipoTransaccion: tipo })
                  }
                  className={`p-4 border-2 rounded-lg transition ${
                    formData.tipoTransaccion === tipo
                      ? "border-primary bg-primary/5"
                      : "border-gray-200 bg-gray-50 hover:border-gray-300"
                  }`}
                >
                  <div className="font-semibold text-gray-900">
                    {tipo === "VENTA" ? "Vender" : "Intercambiar"}
                  </div>
                </button>
              ))}
            </div>
          </div>

          {/* Sección 3: Precio */}
          {formData.tipoTransaccion === "VENTA" && (
            <div className="mb-8 pb-8 border-b border-gray-200">
              <h2 className="text-xl font-bold text-gray-900 mb-6">Precio</h2>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Precio (€) *
                </label>
                <div className="flex gap-2">
                  <Input
                    type="number"
                    step="0.01"
                    min="0"
                    placeholder="0.00"
                    value={formData.precio}
                    onChange={(e) =>
                      setFormData({
                        ...formData,
                        precio: parseFloat(e.target.value),
                      })
                    }
                    className="w-full"
                  />
                  <div className="px-4 py-2 bg-gray-100 rounded-lg flex items-center font-semibold">
                    €
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* Sección 4: Estado */}
          <div className="mb-8 pb-8 border-b border-gray-200">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Estado del artículo</h2>
            <div className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Condición *
                </label>
                <select
                  value={formData.estado}
                  onChange={(e) =>
                    setFormData({ ...formData, estado: e.target.value })
                  }
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                >
                  <option value="Nuevo">Nuevo</option>
                  <option value="Como nuevo">Como nuevo</option>
                  <option value="Buen estado">Buen estado</option>
                  <option value="En uso">En uso</option>
                  <option value="Defectuoso">Defectuoso</option>
                </select>
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Descripción del estado
                </label>
                <textarea
                  placeholder="Describe el estado del artículo, si tiene defectos, etc..."
                  value={formData.descripcionEstado}
                  onChange={(e) =>
                    setFormData({
                      ...formData,
                      descripcionEstado: e.target.value,
                    })
                  }
                  rows={3}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary text-sm"
                />
              </div>
            </div>
          </div>

          {/* Sección 5: Idioma y Región */}
          <div className="mb-8 pb-8 border-b border-gray-200">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Idioma y región</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Idioma
                </label>
                <select
                  value={formData.idioma}
                  onChange={(e) => setFormData({ ...formData, idioma: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                >
                  <option value="Español">Español</option>
                  <option value="Inglés">Inglés</option>
                  <option value="Francés">Francés</option>
                  <option value="Alemán">Alemán</option>
                </select>
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Región
                </label>
                <select
                  value={formData.region}
                  onChange={(e) => setFormData({ ...formData, region: e.target.value })}
                  className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                >
                  <option value="Europa">Europa</option>
                  <option value="América del Norte">América del Norte</option>
                  <option value="América del Sur">América del Sur</option>
                  <option value="Asia">Asia</option>
                </select>
              </div>
            </div>
          </div>

          {/* Sección 6: Especificaciones */}
          <div className="mb-8 pb-8 border-b border-gray-200">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Especificaciones</h2>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Detalles técnicos (versión, accesorios incluidos, etc)
              </label>
              <textarea
                placeholder="p.ej. Incluye caja original, manual, y todos los accesorios..."
                value={formData.especificaciones}
                onChange={(e) =>
                  setFormData({ ...formData, especificaciones: e.target.value })
                }
                rows={3}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary text-sm"
              />
            </div>
          </div>

          {/* Sección 6: Imágenes */}
          <div className="mb-8">
            <h2 className="text-xl font-bold text-gray-900 mb-6">Imágenes *</h2>

            {/* Upload Area */}
            {previewImages.length < 3 && (
              <label className="block border-2 border-dashed border-gray-300 rounded-xl p-8 text-center cursor-pointer hover:border-primary hover:bg-primary/5 transition">
                <Upload className="w-8 h-8 text-gray-400 mx-auto mb-2" />
                <p className="text-sm font-medium text-gray-700">
                  Haz clic o arrastra imágenes aquí
                </p>
                <p className="text-xs text-gray-500 mt-1">
                  Máximo 3 imágenes, PNG, JPG
                </p>
                <input
                  type="file"
                  multiple
                  accept="image/*"
                  onChange={handleImageUpload}
                  className="hidden"
                />
              </label>
            )}

            {/* Preview */}
            {previewImages.length > 0 && (
              <div className="mt-6">
                <h3 className="text-sm font-medium text-gray-700 mb-3">
                  Imágenes cargadas ({previewImages.length}/3)
                </h3>
                <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                  {previewImages.map((img, idx) => (
                    <div
                      key={idx}
                      className="relative group rounded-lg overflow-hidden bg-gray-100 aspect-square"
                    >
                      <img
                        src={img}
                        alt={`Preview ${idx}`}
                        className="w-full h-full object-cover"
                      />
                      <button
                        type="button"
                        onClick={() => removeImage(idx)}
                        className="absolute top-1 right-1 bg-red-500 text-white rounded-full p-1 opacity-0 group-hover:opacity-100 transition"
                      >
                        <X className="w-4 h-4" />
                      </button>
                    </div>
                  ))}
                </div>
              </div>
            )}
          </div>

          {/* Botones */}
          <div className="flex gap-4">
            <Button
              type="submit"
              disabled={loading}
              className="flex-1 bg-primary hover:bg-primary/90"
            >
              {loading ? "Publicando..." : "Publicar ahora"}
            </Button>
            <Button
              type="button"
              variant="outline"
              onClick={() => onNavigate("profile")}
              className="flex-1"
            >
              Cancelar
            </Button>
          </div>
        </form>
      </div>
    </div>
  );
}
