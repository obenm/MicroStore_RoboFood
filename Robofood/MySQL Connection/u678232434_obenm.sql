
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 01-12-2016 a las 16:04:00
-- Versión del servidor: 10.0.20-MariaDB-log
-- Versión de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `u678232434_obenm`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coupons`
--

CREATE TABLE IF NOT EXISTS `coupons` (
  `idcoupon` int(11) NOT NULL AUTO_INCREMENT,
  `coupon` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`idcoupon`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `coupons`
--

INSERT INTO `coupons` (`idcoupon`, `coupon`, `discount`) VALUES
(1, 'GRATIS8', 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `idproduct` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `details` longtext COLLATE utf8_unicode_ci,
  `image` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idproduct`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`idproduct`, `name`, `price`, `details`, `image`) VALUES
(1, 'Pizza', 35.8, 'Pizza is a baked flatbread made with wheat flour, salt, water and yeast, and topped with tomato sauce and other ingredients like cheese, salami, mushrooms, onion, ham or olives.', 'http://staging03.pro.totalhousehold.com/wp-content/uploads/sites/143/2014/09/pizzaCate.jpg'),
(2, 'Burger', 79.5, 'A hamburger is a sandwich-shaped meal of minced meat in the form of a steak, cooked on the grill or grilled, but can also be fried or baked.', 'http://www.comoserunkiwi.com/wp-content/uploads/2012/10/fergburger_queenstown_hamburguesa.jpg'),
(3, 'Coffe', 23.5, 'Coffee is the drink that is obtained from the roasted and ground seeds of the fruits of the coffee plant. It is a highly stimulant drink because of its caffeine content.', 'http://www.amantesdelcafe.org/wp-content/uploads/2012/03/cafe-moka.jpg'),
(4, 'Sushi', 130.8, 'It is a dish of Japanese origin based on rice that is usually accompanied with: sesame, salmon or trout, depending on the tastes and has to be cooked marinated with rice vinegar, sugar, salt and other ingredients', 'http://dondecomequilmes.com/wp-content/uploads/2016/02/5-5-500x500.jpg'),
(5, 'Tacos', 45.6, 'The tacos usually contain some dressing inside the tortilla that can be from a simple sprinkled of salt, a taco with salt, to the more complex preparations like the tacos al pastor or the flutes that are fried tacos.', 'http://67.media.tumblr.com/e3767002ae298f06501dd074d1b69785/tumblr_mmhw9w0bsj1qf623go1_500.jpg'),
(6, 'Pozole', 67.3, 'Pozole (from Nahuatl pozolli, from tlapozonalli, ''boiled'' or ''frothy'', or from cahíta posoli, ''baking corn'') is a dish of Mexico, a kind of broth made from corn kernels of a type commonly known Like cacahuazintle.', 'https://65.media.tumblr.com/e1adb6b65c248f9a9e1554259a101237/tumblr_nusj8orvC61r83777o1_500.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `picture` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`iduser`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`iduser`, `user`, `password`, `name`, `picture`) VALUES
(1, 'root', '1234', 'Sr. Root', NULL),
(2, 'frida', 'chucuchu', 'Frida Ibarra', NULL);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
