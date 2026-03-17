// components/ui/Button.jsx
/**
 * Componente Button reutilizable
 * Ejemplo basado en el diseño de Figma
 */

export default function Button({
  children,
  variant = 'primary',
  size = 'md',
  disabled = false,
  className = '',
  ...props
}) {
  // Estilos base
  const baseStyles = 'font-medium rounded-lg transition-colors duration-200 focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed';

  // Variantes de color
  const variants = {
    primary: 'bg-blue-600 text-white hover:bg-blue-700 focus:ring-blue-500',
    secondary: 'bg-gray-200 text-gray-900 hover:bg-gray-300 focus:ring-gray-400',
    danger: 'bg-red-600 text-white hover:bg-red-700 focus:ring-red-500',
    outline: 'border-2 border-gray-300 text-gray-900 hover:bg-gray-50 focus:ring-gray-400',
    ghost: 'text-gray-700 hover:bg-gray-100 focus:ring-gray-300',
  };

  // Tamaños
  const sizes = {
    sm: 'px-3 py-1 text-sm',
    md: 'px-4 py-2 text-base',
    lg: 'px-6 py-3 text-lg',
  };

  return (
    <button
      className={`${baseStyles} ${variants[variant]} ${sizes[size]} ${className}`}
      disabled={disabled}
      {...props}
    >
      {children}
    </button>
  );
}
