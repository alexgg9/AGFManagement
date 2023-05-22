-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-05-2023 a las 13:45:18
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agfpromotionsbd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `ID_evento` int(3) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `recinto` varchar(150) NOT NULL,
  `ciudad` varchar(70) NOT NULL,
  `pais` varchar(70) NOT NULL,
  `modalidad` varchar(70) NOT NULL,
  `fecha` date DEFAULT NULL,
  `dni_peleador1` varchar(9) NOT NULL,
  `dni_peleador2` varchar(9) NOT NULL,
  `dni_matchmaker` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`ID_evento`, `nombre`, `recinto`, `ciudad`, `pais`, `modalidad`, `fecha`, `dni_peleador1`, `dni_peleador2`, `dni_matchmaker`) VALUES
(12, 'dwq', 'dqw', 'dwqd', 'dwq', 'MUAYTHAI', '2023-05-26', '101010C', '1212W', '2222W'),
(16, 'dwqd', 'dwq', 'dwq', 'dwqd', 'MMA', '2023-05-10', '101010C', '1212W', '2222W'),
(17, 'wdq', 'dwqd', 'dwq', 'dwqd', 'MMA', '2023-05-27', '1212W', '101010C', '2222W'),
(31, 'dwqd', 'dqwd', 'dwq', 'dwq', 'MMA', '2023-05-10', '1212W', '101010C', '2222W'),
(45, 'deqw', 'dqw', 'dqw', 'wqd', 'BJJ', '2023-06-04', '1212W', '101010C', '2222W');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matchmaker`
--

CREATE TABLE `matchmaker` (
  `dni_matchmaker` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `promotora` varchar(50) NOT NULL,
  `usuario` varchar(60) NOT NULL,
  `contraseña` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `matchmaker`
--

INSERT INTO `matchmaker` (`dni_matchmaker`, `nombre`, `apellidos`, `promotora`, `usuario`, `contraseña`) VALUES
('11122C', 'Mike', 'Maynard', 'UFC', 'Mike1', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
('20953342C', 'Alex', 'Galvez', 'UFC', 'alex1', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),
('2222W', 'Dana', 'White', 'UFC', 'dana01', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peleador`
--

CREATE TABLE `peleador` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `edad` int(2) NOT NULL,
  `genero` varchar(50) NOT NULL,
  `peso` int(3) NOT NULL,
  `altura` int(3) NOT NULL,
  `record` varchar(20) NOT NULL,
  `pais` varchar(100) NOT NULL,
  `background` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `peleador`
--

INSERT INTO `peleador` (`dni`, `nombre`, `apellidos`, `edad`, `genero`, `peso`, `altura`, `record`, `pais`, `background`) VALUES
('0000W', 'ds', 'dsd', 20, 'FEMALE', 60, 170, '12-0', 'dwqa', 'STRIKING'),
('101010C', 'Ilia', 'Topuria', 27, 'MALE', 66, 170, '14-0-0', 'Georgia', 'GRAPPLING'),
('1212W', 'Yair', 'Rodriguez', 31, 'MALE', 66, 180, '14-3', 'Mexico', 'STRIKING'),
('2020W', 'Jon', 'Jones', 34, 'MALE', 117, 195, '21-2', 'EEUU', 'WRESTLING');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`ID_evento`),
  ADD KEY `dni_peleador1` (`dni_peleador1`),
  ADD KEY `dni` (`dni_peleador2`),
  ADD KEY `dni_matchmaker` (`dni_matchmaker`);

--
-- Indices de la tabla `matchmaker`
--
ALTER TABLE `matchmaker`
  ADD PRIMARY KEY (`dni_matchmaker`);

--
-- Indices de la tabla `peleador`
--
ALTER TABLE `peleador`
  ADD PRIMARY KEY (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `ID_evento` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`dni_peleador2`) REFERENCES `peleador` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`dni_peleador1`) REFERENCES `peleador` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `evento_ibfk_3` FOREIGN KEY (`dni_matchmaker`) REFERENCES `matchmaker` (`dni_matchmaker`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_peleador_1` FOREIGN KEY (`dni_peleador1`) REFERENCES `peleador` (`dni`),
  ADD CONSTRAINT `fk_peleador_2` FOREIGN KEY (`dni_peleador2`) REFERENCES `peleador` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
