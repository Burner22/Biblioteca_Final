-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2021 a las 14:48:53
-- Versión del servidor: 10.4.17-MariaDB
-- Versión de PHP: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblio_final`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `id_autor` int(11) NOT NULL,
  `nombre_autor` varchar(50) NOT NULL,
  `apellido_autor` varchar(50) NOT NULL,
  `dni_autor` int(11) NOT NULL,
  `fech_nac` date NOT NULL,
  `nacionalidad` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`id_autor`, `nombre_autor`, `apellido_autor`, `dni_autor`, `fech_nac`, `nacionalidad`) VALUES
(1, 'Roberto', 'Acevedo', 41710461, '1999-02-08', 'Argentina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplar`
--

CREATE TABLE `ejemplar` (
  `id_ejemplar` int(11) NOT NULL,
  `id_libro` int(11) NOT NULL,
  `estado` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ejemplar`
--

INSERT INTO `ejemplar` (`id_ejemplar`, `id_libro`, `estado`) VALUES
(7, 1, 'Retraso'),
(8, 1, 'Retraso'),
(9, 1, 'Retraso'),
(10, 1, 'Disponible'),
(11, 1, 'Disponible'),
(12, 1, 'Disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lector`
--

CREATE TABLE `lector` (
  `id_lector` int(11) NOT NULL,
  `nombre_lector` varchar(50) NOT NULL,
  `apellido_lector` varchar(50) NOT NULL,
  `dni_lector` int(11) NOT NULL,
  `dire_lector` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `lector`
--

INSERT INTO `lector` (`id_lector`, `nombre_lector`, `apellido_lector`, `dni_lector`, `dire_lector`) VALUES
(1, 'Nane', 'Chan', 41123123, 'Modulo 12'),
(4, 'Fiero', 'San', 41710461, 'Modulo 12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id_libro` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `ISBN` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `editorial` varchar(100) NOT NULL,
  `año` int(11) NOT NULL,
  `tipo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id_libro`, `id_autor`, `ISBN`, `nombre`, `editorial`, `año`, `tipo`) VALUES
(1, 1, 25, 'LocoMania', 'Salamandra', 2010, 'Aventura');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multa`
--

CREATE TABLE `multa` (
  `id_multa` int(11) NOT NULL,
  `id_lector` int(11) NOT NULL,
  `fecha_fin` date NOT NULL,
  `fecha_inicio` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `multa`
--

INSERT INTO `multa` (`id_multa`, `id_lector`, `fecha_fin`, `fecha_inicio`) VALUES
(14, 1, '2021-05-19', '2021-05-21'),
(16, 1, '2021-04-06', '2021-04-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `id_prestamo` int(11) NOT NULL,
  `id_lector` int(11) NOT NULL,
  `id_ejemplar` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `fecha_prestamo` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`id_prestamo`, `id_lector`, `id_ejemplar`, `estado`, `fecha_prestamo`) VALUES
(1, 1, 7, 0, '2021-04-21'),
(2, 1, 8, 0, '2021-04-21'),
(3, 1, 9, 0, '2021-04-21');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`id_autor`),
  ADD UNIQUE KEY `dni_autor` (`dni_autor`);

--
-- Indices de la tabla `ejemplar`
--
ALTER TABLE `ejemplar`
  ADD PRIMARY KEY (`id_ejemplar`),
  ADD KEY `id_libro` (`id_libro`);

--
-- Indices de la tabla `lector`
--
ALTER TABLE `lector`
  ADD PRIMARY KEY (`id_lector`),
  ADD UNIQUE KEY `dni_lector` (`dni_lector`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`id_libro`),
  ADD KEY `id_autor` (`id_autor`);

--
-- Indices de la tabla `multa`
--
ALTER TABLE `multa`
  ADD PRIMARY KEY (`id_multa`),
  ADD KEY `id_lector` (`id_lector`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id_prestamo`),
  ADD KEY `id_ejemplar` (`id_ejemplar`),
  ADD KEY `id_lector` (`id_lector`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autor`
--
ALTER TABLE `autor`
  MODIFY `id_autor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ejemplar`
--
ALTER TABLE `ejemplar`
  MODIFY `id_ejemplar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `lector`
--
ALTER TABLE `lector`
  MODIFY `id_lector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `id_libro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `multa`
--
ALTER TABLE `multa`
  MODIFY `id_multa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id_prestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ejemplar`
--
ALTER TABLE `ejemplar`
  ADD CONSTRAINT `ejemplar_ibfk_1` FOREIGN KEY (`id_libro`) REFERENCES `libro` (`id_libro`);

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_autor`);

--
-- Filtros para la tabla `multa`
--
ALTER TABLE `multa`
  ADD CONSTRAINT `multa_ibfk_1` FOREIGN KEY (`id_lector`) REFERENCES `lector` (`id_lector`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_ejemplar`) REFERENCES `ejemplar` (`id_ejemplar`),
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_lector`) REFERENCES `lector` (`id_lector`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
