-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `looteria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carrito`
--

CREATE TABLE `carrito` (
  `id_carrito` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_publicacion` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT 1,
  `fecha_agregado` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id_categoria` bigint(20) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id_categoria`, `tipo`, `nombre`, `fecha_creacion`) VALUES
(1, 'PLATAFORMA', 'PlayStation 5', '2026-02-24 12:17:11'),
(2, 'PLATAFORMA', 'Xbox Series X', '2026-02-24 12:17:11'),
(3, 'PLATAFORMA', 'Nintendo Switch', '2026-02-24 12:17:11'),
(4, 'PLATAFORMA', 'PC', '2026-02-24 12:17:11'),
(5, 'PLATAFORMA', 'PlayStation 4', '2026-02-24 12:17:11'),
(6, 'PLATAFORMA', 'Xbox One', '2026-02-24 12:17:11'),
(7, 'TIPO_ARTICULO', 'Videojuego', '2026-02-24 12:17:11'),
(8, 'TIPO_ARTICULO', 'Accesorio', '2026-02-24 12:17:11'),
(9, 'TIPO_ARTICULO', 'Consola', '2026-02-24 12:17:11'),
(10, 'TIPO_ARTICULO', 'Merchandise', '2026-02-24 12:17:11'),
(11, 'ESTADO_ARTICULO', 'Nuevo', '2026-02-24 12:17:11'),
(12, 'ESTADO_ARTICULO', 'Como nuevo', '2026-02-24 12:17:11'),
(13, 'ESTADO_ARTICULO', 'Buen estado', '2026-02-24 12:17:11'),
(14, 'ESTADO_ARTICULO', 'En uso', '2026-02-24 12:17:11'),
(15, 'ESTADO_ARTICULO', 'Defectuoso', '2026-02-24 12:17:11'),
(16, 'IDIOMA', 'Español', '2026-02-24 12:17:11'),
(17, 'IDIOMA', 'Inglés', '2026-02-24 12:17:11'),
(18, 'IDIOMA', 'Francés', '2026-02-24 12:17:11'),
(19, 'IDIOMA', 'Alemán', '2026-02-24 12:17:11'),
(20, 'REGION', 'Europa', '2026-02-24 12:17:11'),
(21, 'REGION', 'América del Norte', '2026-02-24 12:17:11'),
(22, 'REGION', 'América del Sur', '2026-02-24 12:17:11'),
(23, 'REGION', 'Asia', '2026-02-24 12:17:11'),
(26, 'PLATAFORMA', 'Nintendo Switch', '2026-02-24 12:20:03'),
(27, 'PLATAFORMA', 'PC', '2026-02-24 12:20:03'),
(28, 'PLATAFORMA', 'PlayStation 4', '2026-02-24 12:20:03'),
(29, 'PLATAFORMA', 'Xbox One', '2026-02-24 12:20:03'),
(30, 'TIPO_ARTICULO', 'Videojuego', '2026-02-24 12:20:03'),
(31, 'TIPO_ARTICULO', 'Accesorio', '2026-02-24 12:20:03'),
(32, 'TIPO_ARTICULO', 'Consola', '2026-02-24 12:20:03'),
(33, 'TIPO_ARTICULO', 'Merchandise', '2026-02-24 12:20:03'),
(34, 'ESTADO_ARTICULO', 'Nuevo', '2026-02-24 12:20:03'),
(35, 'ESTADO_ARTICULO', 'Como nuevo', '2026-02-24 12:20:03'),
(36, 'ESTADO_ARTICULO', 'Buen estado', '2026-02-24 12:20:03'),
(37, 'ESTADO_ARTICULO', 'En uso', '2026-02-24 12:20:03'),
(38, 'ESTADO_ARTICULO', 'Defectuoso', '2026-02-24 12:20:03'),
(39, 'IDIOMA', 'Español', '2026-02-24 12:20:03'),
(40, 'IDIOMA', 'Inglés', '2026-02-24 12:20:03'),
(41, 'IDIOMA', 'Francés', '2026-02-24 12:20:03'),
(42, 'IDIOMA', 'Alemán', '2026-02-24 12:20:03'),
(43, 'REGION', 'Europa', '2026-02-24 12:20:03'),
(44, 'REGION', 'América del Norte', '2026-02-24 12:20:03'),
(45, 'REGION', 'América del Sur', '2026-02-24 12:20:03'),
(46, 'REGION', 'Asia', '2026-02-24 12:20:03'),
(47, 'PLATAFORMA', 'PlayStation 5', '2026-02-24 12:20:03'),
(48, 'PLATAFORMA', 'Xbox Series X', '2026-02-24 12:20:03'),
(49, 'PLATAFORMA', 'Nintendo Switch', '2026-02-24 12:20:03'),
(50, 'PLATAFORMA', 'PC', '2026-02-24 12:20:03'),
(51, 'PLATAFORMA', 'PlayStation 4', '2026-02-24 12:20:03'),
(52, 'PLATAFORMA', 'Xbox One', '2026-02-24 12:20:03'),
(53, 'TIPO_ARTICULO', 'Videojuego', '2026-02-24 12:20:03'),
(54, 'TIPO_ARTICULO', 'Accesorio', '2026-02-24 12:20:03'),
(55, 'TIPO_ARTICULO', 'Consola', '2026-02-24 12:20:03'),
(56, 'TIPO_ARTICULO', 'Merchandise', '2026-02-24 12:20:03'),
(57, 'ESTADO_ARTICULO', 'Nuevo', '2026-02-24 12:20:03'),
(58, 'ESTADO_ARTICULO', 'Como nuevo', '2026-02-24 12:20:03'),
(59, 'ESTADO_ARTICULO', 'Buen estado', '2026-02-24 12:20:03'),
(60, 'ESTADO_ARTICULO', 'En uso', '2026-02-24 12:20:03'),
(61, 'ESTADO_ARTICULO', 'Defectuoso', '2026-02-24 12:20:03'),
(62, 'IDIOMA', 'Español', '2026-02-24 12:20:03'),
(63, 'IDIOMA', 'Inglés', '2026-02-24 12:20:03'),
(64, 'IDIOMA', 'Francés', '2026-02-24 12:20:03'),
(65, 'IDIOMA', 'Alemán', '2026-02-24 12:20:03'),
(66, 'REGION', 'Europa', '2026-02-24 12:20:03'),
(67, 'REGION', 'América del Norte', '2026-02-24 12:20:03'),
(68, 'REGION', 'América del Sur', '2026-02-24 12:20:03'),
(69, 'REGION', 'Asia', '2026-02-24 12:20:03'),
(70, 'PLATAFORMA', 'PlayStation 5', '2026-02-24 12:20:11'),
(71, 'PLATAFORMA', 'Xbox Series X', '2026-02-24 12:20:11'),
(72, 'PLATAFORMA', 'Nintendo Switch', '2026-02-24 12:20:11'),
(73, 'PLATAFORMA', 'PC', '2026-02-24 12:20:11'),
(74, 'PLATAFORMA', 'PlayStation 4', '2026-02-24 12:20:11'),
(75, 'PLATAFORMA', 'Xbox One', '2026-02-24 12:20:11'),
(76, 'TIPO_ARTICULO', 'Videojuego', '2026-02-24 12:20:11'),
(77, 'TIPO_ARTICULO', 'Accesorio', '2026-02-24 12:20:11'),
(78, 'TIPO_ARTICULO', 'Consola', '2026-02-24 12:20:11'),
(79, 'TIPO_ARTICULO', 'Merchandise', '2026-02-24 12:20:11'),
(80, 'ESTADO_ARTICULO', 'Nuevo', '2026-02-24 12:20:11'),
(81, 'ESTADO_ARTICULO', 'Como nuevo', '2026-02-24 12:20:11'),
(82, 'ESTADO_ARTICULO', 'Buen estado', '2026-02-24 12:20:11'),
(83, 'ESTADO_ARTICULO', 'En uso', '2026-02-24 12:20:11'),
(84, 'ESTADO_ARTICULO', 'Defectuoso', '2026-02-24 12:20:11'),
(85, 'IDIOMA', 'Español', '2026-02-24 12:20:11'),
(86, 'IDIOMA', 'Inglés', '2026-02-24 12:20:11'),
(87, 'IDIOMA', 'Francés', '2026-02-24 12:20:11'),
(88, 'IDIOMA', 'Alemán', '2026-02-24 12:20:11'),
(89, 'REGION', 'Europa', '2026-02-24 12:20:11'),
(90, 'REGION', 'América del Norte', '2026-02-24 12:20:11'),
(91, 'REGION', 'América del Sur', '2026-02-24 12:20:11'),
(92, 'REGION', 'Asia', '2026-02-24 12:20:11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `envios`
--

CREATE TABLE `envios` (
  `id_envio` bigint(20) NOT NULL,
  `id_transaccion` bigint(20) NOT NULL,
  `proveedor` varchar(255) NOT NULL,
  `numero_seguimiento` varchar(100) DEFAULT NULL,
  `detalles_seguimiento` text DEFAULT NULL,
  `coste` decimal(10,2) DEFAULT NULL,
  `estado` enum('PREPARANDO','ENVIADO','EN_TRANSITO','ENTREGADO','DEVUELTO') DEFAULT 'PREPARANDO',
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes`
--

CREATE TABLE `imagenes` (
  `id_imagen` bigint(20) NOT NULL,
  `id_publicacion` bigint(20) NOT NULL,
  `ruta_imagen` varchar(500) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `imagenes`
--

INSERT INTO `imagenes` (`id_imagen`, `id_publicacion`, `ruta_imagen`, `descripcion`, `fecha_creacion`) VALUES
(1, 1, 'https://images.unsplash.com/photo-1538481143235-67d5c6f2de5c?w=500&h=500&fit=crop', 'Zelda BOTW - Caja y cartucho original', '2026-04-12 21:32:33'),
(2, 1, 'https://images.unsplash.com/photo-1552806861-20d5892e67c7?w=500&h=500&fit=crop', 'Zelda BOTW - Vista del cartucho', '2026-04-12 21:32:33'),
(3, 2, 'https://images.unsplash.com/photo-1535905557558-afc4877a26fc?w=500&h=500&fit=crop', 'Elden Ring PS5 - Disco en perfectas condiciones', '2026-04-12 21:32:33'),
(4, 2, 'https://images.unsplash.com/photo-1516394175018-af557b90f834?w=500&h=500&fit=crop', 'Elden Ring PS5 - Estuche original', '2026-04-12 21:32:33'),
(7, 4, 'https://images.unsplash.com/photo-1493246507139-91e8fad9978e?w=500&h=500&fit=crop', 'Cyberpunk 2077 Xbox - Disco en buen estado', '2026-04-12 21:32:33'),
(8, 4, 'https://images.unsplash.com/photo-1516394175018-af557b90f834?w=500&h=500&fit=crop', 'Cyberpunk 2077 - Con caja original', '2026-04-12 21:32:33'),
(9, 5, 'https://images.unsplash.com/photo-1551288049-bebda4e267f71?w=500&h=500&fit=crop', 'Smash Bros Ultimate - Juego y GameCube controller', '2026-04-12 21:32:33'),
(10, 5, 'https://images.unsplash.com/photo-1493246507139-91e8fad9978e?w=500&h=500&fit=crop', 'Smash Bros - Todos los accesorios incluidos', '2026-04-12 21:32:33');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `intercambios`
--

CREATE TABLE `intercambios` (
  `id_intercambio` bigint(20) NOT NULL,
  `id_publicacion` bigint(20) NOT NULL,
  `id_solicitante` bigint(20) NOT NULL,
  `id_solicitado` bigint(20) NOT NULL,
  `mensaje` text DEFAULT NULL,
  `estado` enum('PENDIENTE','ACEPTADA','RECHAZADA','CANCELADA') DEFAULT 'PENDIENTE',
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` bigint(20) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `plataforma_id` bigint(20) DEFAULT NULL,
  `tipo_articulo_id` bigint(20) DEFAULT NULL,
  `fecha_lanzamiento` datetime DEFAULT NULL,
  `valor_estimado` decimal(10,2) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `titulo`, `descripcion`, `plataforma_id`, `tipo_articulo_id`, `fecha_lanzamiento`, `valor_estimado`, `fecha_creacion`) VALUES
(1, 'The Legend of Zelda: Breath of the Wild', 'Juego de acción-aventura épico para Nintendo Switch. Completo con caja y manual original.', 3, 7, NULL, 45.99, '2026-04-12 21:32:33'),
(2, 'Elden Ring', 'RPG de acción desafiante de FromSoftware. Mundo abierto con jefes épicos.', 1, 7, NULL, 59.99, '2026-04-12 21:32:33'),
(3, 'Fortnite: Battle Royale', 'Battle royale multiplayer popular. Juego F2P con skins exclusivas.', 4, 7, NULL, 0.00, '2026-04-12 21:32:33'),
(4, 'Cyberpunk 2077', 'RPG futurista en mundo abierto de CD Projekt Red. Versión mejorada post-parches.', 2, 7, NULL, 49.99, '2026-04-12 21:32:33'),
(5, 'Super Smash Bros. Ultimate', 'Juego de lucha con todos los personajes Nintendo. Incluye GameCube controller.', 3, 7, NULL, 59.99, '2026-04-12 21:32:33'),
(6, 'Hollow Knight', 'hola', NULL, NULL, NULL, NULL, '2026-05-11 14:48:46'),
(7, 'hola', 'sdasd', NULL, NULL, NULL, NULL, '2026-05-12 10:47:14'),
(8, 'asadas', 'asd', NULL, NULL, NULL, NULL, '2026-05-12 10:48:18'),
(9, 'asda', 'sada', NULL, NULL, NULL, NULL, '2026-05-12 10:50:03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicaciones`
--

CREATE TABLE `publicaciones` (
  `id_publicacion` bigint(20) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_producto` bigint(20) NOT NULL,
  `tipo_transaccion` enum('VENTA','INTERCAMBIO') NOT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `estado_articulo_id` bigint(20) DEFAULT NULL,
  `descripcion_estado` text DEFAULT NULL,
  `idioma_id` bigint(20) DEFAULT NULL,
  `region_id` bigint(20) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado_publicacion` enum('ACTIVA','VENDIDA','CANCELADA','PAUSADA') DEFAULT 'ACTIVA',
  `envio` tinyint(1) DEFAULT 0,
  `destacado` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `publicaciones`
--

INSERT INTO `publicaciones` (`id_publicacion`, `id_usuario`, `id_producto`, `tipo_transaccion`, `precio`, `estado_articulo_id`, `descripcion_estado`, `idioma_id`, `region_id`, `fecha_creacion`, `estado_publicacion`, `envio`) VALUES
(1, 2, 1, 'INTERCAMBIO', 45.99, 12, 'Como nuevo, solo 5 horas de juego. Cambio por otros juegos Nintendo Switch', 16, 20, '2026-04-12 21:32:33', 'ACTIVA', 1),
(2, 3, 2, 'VENTA', 39.99, 13, 'Elden Ring PS5 usado pero en perfecto estado, solo 20 horas de juego. Incluye CD original', 17, 20, '2026-04-12 21:32:33', 'ACTIVA', 1),
(4, 11, 4, 'VENTA', 30.00, 13, 'Cyberpunk 2077 Xbox Series X. Funciona perfectamente post-patches, incluye caja', 16, 20, '2026-04-12 21:32:33', 'ACTIVA', 1),
(5, 3, 5, 'INTERCAMBIO', 45.00, 13, 'Smash Bros Ultimate con todos los accesorios (GameCube controller original). Cambio por Zelda Tears of the Kingdom', 16, 20, '2026-04-12 21:32:33', 'ACTIVA', 1),
(8, 11, 8, 'VENTA', 40.00, NULL, 'si', NULL, NULL, '2026-05-12 10:48:18', 'ACTIVA', 0),
(9, 11, 9, 'VENTA', 23.00, 11, 'asdas', 16, 20, '2026-05-12 10:50:03', 'ACTIVA', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `resenas`
--

CREATE TABLE `resenas` (
  `id_resena` bigint(20) NOT NULL,
  `id_transaccion` bigint(20) DEFAULT NULL,
  `id_autor` bigint(20) NOT NULL,
  `id_receptor` bigint(20) NOT NULL,
  `puntuacion` int(11) NOT NULL,
  `comentario` text DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `id_publicacion` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id_transaccion` bigint(20) NOT NULL,
  `id_publicacion` bigint(20) NOT NULL,
  `id_comprador` bigint(20) NOT NULL,
  `id_vendedor` bigint(20) NOT NULL,
  `tipo` enum('VENTA','INTERCAMBIO') NOT NULL,
  `precio_final` decimal(10,2) NOT NULL,
  `fecha_transaccion` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` enum('PENDIENTE','COMPLETADA','CANCELADA','DEVUELTA') DEFAULT 'PENDIENTE',
  `comision` decimal(10,2) DEFAULT NULL,
  `retencion_pago` decimal(10,2) DEFAULT 0.00,
  `fecha_entrega_prevista` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `nombre_usuario` varchar(100) NOT NULL,
  `rol` enum('INVITADO','REGISTRADO','ADMIN') NOT NULL DEFAULT 'REGISTRADO',
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp(),
  `ubicacion` varchar(255) DEFAULT NULL,
  `puntos_acumulados` bigint(20) DEFAULT 0,
  `verificado_identidad` tinyint(1) DEFAULT 0,
  `reputacion_media` decimal(3,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `email`, `contrasena`, `nombre_usuario`, `rol`, `fecha_registro`, `ubicacion`, `puntos_acumulados`, `verificado_identidad`, `reputacion_media`) VALUES
(1, 'admin@looteria.com', 'admin123', 'admin', 'ADMIN', '2026-02-24 12:17:11', 'España', 1000, 1, 5.00),
(2, 'user1@example.com', 'password123', 'jugador1', 'REGISTRADO', '2026-02-24 12:17:11', 'Madrid', 100, 1, 4.50),
(3, 'user2@example.com', 'password456', 'coleccionista', 'REGISTRADO', '2026-02-24 12:17:11', 'Barcelona', 50, 0, 3.75),
(8, 'testuser@example.com', 'Password123!', 'testuser123', 'REGISTRADO', '2026-03-16 20:35:35', NULL, 0, 0, 0.00),
(9, 'usuario2@example.com', 'SecurePass456!', 'usuario2', 'REGISTRADO', '2026-03-16 20:35:52', NULL, 0, 0, 0.00),
(10, 'raul@gmail.com', '12345', 'rauulvt', 'REGISTRADO', '2026-03-16 20:44:41', NULL, 0, 0, 0.00),
(11, 'josemi@gmail.com', '123456', 'Minino', 'REGISTRADO', '2026-03-16 22:09:06', 'Sevilla', 0, 0, 0.00),
(12, 'narvaez@gmail.com', '123456', 'Israel', 'REGISTRADO', '2026-03-17 15:05:29', NULL, 0, 0, 0.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `verificaciones`
--

CREATE TABLE `verificaciones` (
  `id_verificacion` bigint(20) NOT NULL,
  `id_transaccion` bigint(20) NOT NULL,
  `id_publicacion` bigint(20) NOT NULL,
  `id_admin_verificador` bigint(20) DEFAULT NULL,
  `estado` enum('PENDIENTE','APROBADA','RECHAZADA','CANCELADA') DEFAULT 'PENDIENTE',
  `fecha_solicitud` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_respuesta` datetime DEFAULT NULL,
  `comentario_admin` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD PRIMARY KEY (`id_carrito`),
  ADD UNIQUE KEY `unique_cart_item` (`id_usuario`,`id_publicacion`),
  ADD KEY `id_publicacion` (`id_publicacion`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id_categoria`),
  ADD KEY `idx_tipo` (`tipo`),
  ADD KEY `idx_nombre` (`nombre`);

--
-- Indices de la tabla `envios`
--
ALTER TABLE `envios`
  ADD PRIMARY KEY (`id_envio`),
  ADD UNIQUE KEY `numero_seguimiento` (`numero_seguimiento`),
  ADD KEY `idx_transaccion` (`id_transaccion`),
  ADD KEY `idx_numero_seguimiento` (`numero_seguimiento`);

--
-- Indices de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  ADD PRIMARY KEY (`id_imagen`),
  ADD KEY `idx_publicacion` (`id_publicacion`);

--
-- Indices de la tabla `intercambios`
--
ALTER TABLE `intercambios`
  ADD PRIMARY KEY (`id_intercambio`),
  ADD KEY `id_publicacion` (`id_publicacion`),
  ADD KEY `id_solicitante` (`id_solicitante`),
  ADD KEY `id_solicitado` (`id_solicitado`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `idx_titulo` (`titulo`),
  ADD KEY `idx_plataforma` (`plataforma_id`),
  ADD KEY `idx_tipo_articulo` (`tipo_articulo_id`);

--
-- Indices de la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD PRIMARY KEY (`id_publicacion`),
  ADD KEY `estado_articulo_id` (`estado_articulo_id`),
  ADD KEY `idioma_id` (`idioma_id`),
  ADD KEY `region_id` (`region_id`),
  ADD KEY `idx_usuario` (`id_usuario`),
  ADD KEY `idx_producto` (`id_producto`),
  ADD KEY `idx_estado_publicacion` (`estado_publicacion`),
  ADD KEY `idx_tipo_transaccion` (`tipo_transaccion`);

--
-- Indices de la tabla `resenas`
--
ALTER TABLE `resenas`
  ADD PRIMARY KEY (`id_resena`),
  ADD KEY `id_autor` (`id_autor`),
  ADD KEY `idx_transaccion` (`id_transaccion`),
  ADD KEY `idx_receptor` (`id_receptor`),
  ADD KEY `resenas_ibfk_4` (`id_publicacion`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`id_transaccion`),
  ADD KEY `idx_publicacion` (`id_publicacion`),
  ADD KEY `idx_comprador` (`id_comprador`),
  ADD KEY `idx_vendedor` (`id_vendedor`),
  ADD KEY `idx_estado` (`estado`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `nombre_usuario` (`nombre_usuario`),
  ADD KEY `idx_email` (`email`),
  ADD KEY `idx_nombre_usuario` (`nombre_usuario`),
  ADD KEY `idx_rol` (`rol`);

--
-- Indices de la tabla `verificaciones`
--
ALTER TABLE `verificaciones`
  ADD PRIMARY KEY (`id_verificacion`),
  ADD KEY `id_publicacion` (`id_publicacion`),
  ADD KEY `id_admin_verificador` (`id_admin_verificador`),
  ADD KEY `idx_transaccion` (`id_transaccion`),
  ADD KEY `idx_estado` (`estado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carrito`
--
ALTER TABLE `carrito`
  MODIFY `id_carrito` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id_categoria` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT de la tabla `envios`
--
ALTER TABLE `envios`
  MODIFY `id_envio` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  MODIFY `id_imagen` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `intercambios`
--
ALTER TABLE `intercambios`
  MODIFY `id_intercambio` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  MODIFY `id_publicacion` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `resenas`
--
ALTER TABLE `resenas`
  MODIFY `id_resena` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `id_transaccion` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `verificaciones`
--
ALTER TABLE `verificaciones`
  MODIFY `id_verificacion` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carrito`
--
ALTER TABLE `carrito`
  ADD CONSTRAINT `carrito_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  ADD CONSTRAINT `carrito_ibfk_2` FOREIGN KEY (`id_publicacion`) REFERENCES `publicaciones` (`id_publicacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `envios`
--
ALTER TABLE `envios`
  ADD CONSTRAINT `envios_ibfk_1` FOREIGN KEY (`id_transaccion`) REFERENCES `transacciones` (`id_transaccion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `imagenes`
--
ALTER TABLE `imagenes`
  ADD CONSTRAINT `imagenes_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicaciones` (`id_publicacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `intercambios`
--
ALTER TABLE `intercambios`
  ADD CONSTRAINT `intercambios_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicaciones` (`id_publicacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `intercambios_ibfk_2` FOREIGN KEY (`id_solicitante`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `intercambios_ibfk_3` FOREIGN KEY (`id_solicitado`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`plataforma_id`) REFERENCES `categorias` (`id_categoria`) ON DELETE SET NULL,
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`tipo_articulo_id`) REFERENCES `categorias` (`id_categoria`) ON DELETE SET NULL;

--
-- Filtros para la tabla `publicaciones`
--
ALTER TABLE `publicaciones`
  ADD CONSTRAINT `publicaciones_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE,
  ADD CONSTRAINT `publicaciones_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`) ON DELETE CASCADE,
  ADD CONSTRAINT `publicaciones_ibfk_3` FOREIGN KEY (`estado_articulo_id`) REFERENCES `categorias` (`id_categoria`) ON DELETE SET NULL,
  ADD CONSTRAINT `publicaciones_ibfk_4` FOREIGN KEY (`idioma_id`) REFERENCES `categorias` (`id_categoria`) ON DELETE SET NULL,
  ADD CONSTRAINT `publicaciones_ibfk_5` FOREIGN KEY (`region_id`) REFERENCES `categorias` (`id_categoria`) ON DELETE SET NULL;

--
-- Filtros para la tabla `resenas`
--
ALTER TABLE `resenas`
  ADD CONSTRAINT `resenas_ibfk_1` FOREIGN KEY (`id_transaccion`) REFERENCES `transacciones` (`id_transaccion`) ON DELETE CASCADE,
  ADD CONSTRAINT `resenas_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `resenas_ibfk_3` FOREIGN KEY (`id_receptor`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `resenas_ibfk_4` FOREIGN KEY (`id_publicacion`) REFERENCES `publicaciones` (`id_publicacion`) ON DELETE CASCADE;

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`id_publicacion`) REFERENCES `publicaciones` (`id_publicacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `transacciones_ibfk_2` FOREIGN KEY (`id_comprador`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `transacciones_ibfk_3` FOREIGN KEY (`id_vendedor`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `verificaciones`
--
ALTER TABLE `verificaciones`
  ADD CONSTRAINT `verificaciones_ibfk_1` FOREIGN KEY (`id_transaccion`) REFERENCES `transacciones` (`id_transaccion`) ON DELETE CASCADE,
  ADD CONSTRAINT `verificaciones_ibfk_2` FOREIGN KEY (`id_publicacion`) REFERENCES `publicaciones` (`id_publicacion`) ON DELETE CASCADE,
  ADD CONSTRAINT `verificaciones_ibfk_3` FOREIGN KEY (`id_admin_verificador`) REFERENCES `usuarios` (`id_usuario`) ON DELETE SET NULL;

--
-- Estructura de tabla para la tabla `verification_codes`
--

CREATE TABLE `verification_codes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `fecha_expiracion` datetime NOT NULL,
  `usado` tinyint(1) NOT NULL DEFAULT 0,
  `id_usuario` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `verification_codes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
