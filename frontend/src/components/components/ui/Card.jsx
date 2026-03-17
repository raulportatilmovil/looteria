// components/ui/Card.jsx
/**
 * Componente Card reutilizable
 * Contenedor flexible para agrupar contenido
 */

export function Card({ children, className = '', ...props }) {
  return (
    <div
      className={`bg-white rounded-lg shadow-md p-6 ${className}`}
      {...props}
    >
      {children}
    </div>
  );
}

export function CardHeader({ children, className = '' }) {
  return (
    <div className={`mb-4 pb-4 border-b border-gray-200 ${className}`}>
      {children}
    </div>
  );
}

export function CardTitle({ children, className = '' }) {
  return <h2 className={`text-xl font-bold text-gray-900 ${className}`}>{children}</h2>;
}

export function CardDescription({ children, className = '' }) {
  return <p className={`text-sm text-gray-600 mt-1 ${className}`}>{children}</p>;
}

export function CardContent({ children, className = '' }) {
  return <div className={className}>{children}</div>;
}

export function CardFooter({ children, className = '' }) {
  return (
    <div className={`mt-6 pt-4 border-t border-gray-200 flex gap-4 ${className}`}>
      {children}
    </div>
  );
}
