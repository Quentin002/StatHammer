-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 17, 2025 at 09:24 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stathammer`
--
CREATE DATABASE stathammer;
USE stathammer;

-- --------------------------------------------------------

--
-- Table structure for table `aptitude`
--

CREATE TABLE `aptitude` (
  `id_aptitude` int NOT NULL,
  `nom_aptitude` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aptitude_arme`
--

CREATE TABLE `aptitude_arme` (
  `id_aptitude_arme` int NOT NULL,
  `nom_aptitude_arme` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `aptitude_arme`
--

INSERT INTO `aptitude_arme` (`id_aptitude_arme`, `nom_aptitude_arme`) VALUES
(1, 'Assaut'),
(2, 'Tir Rapide 1'),
(3, 'Tir Rapide 2'),
(4, 'Tir Rapide 3'),
(5, 'Tir Rapide 4'),
(6, 'Ignore le Couvert'),
(7, 'Jumelé'),
(8, 'Pistolet'),
(9, 'Torrent'),
(10, 'Touches Fatales'),
(11, 'Lance'),
(12, 'Tir Indirect'),
(13, 'Précision'),
(14, 'Déflagration'),
(15, 'Fusion 1'),
(16, 'Fusion 2'),
(17, 'Fusion 3'),
(18, 'Fusion 4'),
(19, 'Lourd'),
(20, 'À Risque'),
(21, 'Blessures Dévastatrices'),
(22, 'Touches Soutenues 1'),
(23, 'Touches Soutenues 2'),
(24, 'Touches Soutenues 3'),
(25, 'Touches Soutenues 4'),
(26, 'Attaques Bonus'),
(27, 'Anti-Véhicule 4+');

-- --------------------------------------------------------

--
-- Table structure for table `arme`
--

CREATE TABLE `arme` (
  `id_arme` int NOT NULL,
  `nom_arme` varchar(200) DEFAULT NULL,
  `PORTEE` varchar(50) DEFAULT NULL,
  `A` varchar(50) DEFAULT NULL,
  `F` int DEFAULT NULL,
  `PA` int DEFAULT NULL,
  `D` varchar(50) DEFAULT NULL,
  `id_tir` int DEFAULT NULL,
  `id_melee` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `arme`
--

INSERT INTO `arme` (`id_arme`, `nom_arme`, `PORTEE`, `A`, `F`, `PA`, `D`, `id_tir`, `id_melee`) VALUES
(1, 'Arme combinée', '24\"', '1', 4, 0, '1', 3, NULL),
(2, 'Arme de corps à corps', 'Mêlée', '1', 3, 0, '1', NULL, 2),
(3, 'Arme de corps à corps', 'Mêlée', '2', 4, 0, '1', NULL, 2),
(4, 'Arme de corps à corps', 'Mêlée', '3', 4, 0, '1', NULL, 2),
(5, 'Arme de corps à corps', 'Mêlée', '4', 4, 0, '1', NULL, 2),
(6, 'Arme de corps à corps', 'Mêlée', '5', 4, 0, '1', NULL, 2),
(7, 'Arme de corps à corps', 'Mêlée', '5', 6, 0, '1', NULL, 2),
(8, 'Arme de corps à corps', 'Mêlée', '6', 4, 0, '1', NULL, 2),
(9, 'Arme de force', 'Mêlée', '4', 6, -1, 'D3', NULL, 3),
(10, 'Arme de mêlée de Dreadnought', 'Mêlée', '5', 12, -2, '3', NULL, 3),
(11, 'Arme énergétique de maître', 'Mêlée', '5', 5, -2, '2', NULL, 2),
(12, 'Arme énergétique de maître', 'Mêlée', '6', 5, -2, '2', NULL, 2),
(13, 'Arme énergétique', 'Mêlée', '3', 4, -2, '1', NULL, 2),
(14, 'Arme énergétique', 'Mêlée', '3', 5, -2, '1', NULL, 2),
(15, 'Arme énergétique', 'Mêlée', '4', 5, -2, '1', NULL, 2),
(16, 'Arme énergétique', 'Mêlée', '5', 5, -2, '1', NULL, 2),
(17, 'Arme patrimoniale', 'Mêlée', '4', 5, -1, '1', NULL, 3),
(18, 'Arme relique', 'Mêlée', '6', 5, -2, '2', NULL, 2),
(19, 'Arquebuse laser', '36\"', '1', 9, -3, 'D6', 3, NULL),
(20, 'Autocanon Accelerator', '48\"', '3', 8, -1, '2', 4, NULL),
(21, 'Autocanon Firestrike jumelé', '48\"', '3', 9, -1, '3', 2, NULL),
(22, 'Autocanon Ironhail jumelé', '48\"', '3', 9, -1, '3', 3, NULL),
(23, 'Autocanon Predator', '48\"', '4', 9, -1, '3', 3, NULL),
(24, 'Autocanon Reaper', '36\"', '4', 7, -1, '1', 3, NULL),
(25, 'Bâton de Tigurius', 'Mêlée', '5', 7, -2, 'D3', NULL, 3),
(26, 'Bolter de la forge', '24\"', '3', 5, -1, '2', 2, NULL),
(27, 'Bolter de maître', '24\"', '2', 4, 0, '2', 2, NULL),
(28, 'Bolter de maître', '24\"', '2', 4, -1, '2', 2, NULL),
(29, 'Bolter Hurricane', '24\"', '6', 4, 0, '1', 3, NULL),
(30, 'Bolter Hurricane', '24\"', '6', 8, 0, '1', 3, NULL),
(31, 'Bolter jumelé', '24\"', '2', 4, 0, '1', 3, NULL),
(32, 'Bolter lourd de Sternguard', '36\"', '3', 5, -1, '2', 4, NULL),
(33, 'Bolter lourd jumelé', '12\"', '3', 5, -1, '2', 3, NULL),
(34, 'Bolter lourd jumelé', '36\"', '3', 5, -1, '2', 3, NULL),
(35, 'Bolter lourd jumelé', '36\"', '3', 5, -1, '2', 3, NULL),
(36, 'Bolter lourd', '36\"', '3', 5, -1, '2', 3, NULL),
(37, 'Bolter Storm jumelé', '24\"', '2', 4, 0, '1', 3, NULL),
(38, 'Bolter Storm', '24\"', '2', 4, 0, '1', 3, NULL),
(39, 'Bolter Tempest', '24\"', '4', 4, -1, '1', 4, NULL),
(40, 'Bolter', '24\"', '2', 4, 0, '1', 3, NULL),
(41, 'Bolters Centurion', '24\"', '3', 4, 0, '1', 3, NULL),
(42, 'Bolters d’assaut', '18\"', '3', 5, -1, '2', 3, NULL),
(43, 'Bordée de bolters lourds Hammerfall', '36\"', '6', 5, -1, '2', 4, NULL),
(44, 'Bordée de lance-flammes lourds Hammerfall', '12\"', '2D6', 5, 0, '1', 1, NULL),
(45, 'Bordée de lance-flammes lourds Hammerfall', '12\"', '2D6', 5, -1, '1', 1, NULL),
(46, 'Bordée de missiles Bellicatus – Frag', '48\"', 'D6', 4, 0, '1', 3, NULL),
(47, 'Bordée de missiles Bellicatus – Icarus', '48\"', 'D3', 8, -1, '2', 3, NULL),
(48, 'Bordée de missiles Bellicatus – Krak', '48\"', '1', 8, -2, 'D6', 3, NULL),
(49, 'Bordée défensive Repulsor', '24\"', '10', 4, 0, '1', 3, NULL),
(50, 'Bordée défensive Repulsor', '24\"', '18', 4, 0, '1', 3, NULL),
(51, 'Bordée Skytalon Ironhail', '36\"', '8', 4, 0, '1', 3, NULL),
(52, 'Canon à gravitons', '24\"', '3', 6, -1, '3', 4, NULL),
(53, 'Canon à gravitons', '24\"', '3', 6, -1, '3', 4, NULL),
(54, 'Canon à plasma – standard', '36\"', 'D3', 7, -2, '1', 4, NULL),
(55, 'Canon à plasma – surchage', '36\"', 'D3', 8, -3, '2', 4, NULL),
(56, 'Canon à plasma lourd – standard', '36\"', 'D3', 7, -2, '2', 3, NULL),
(57, 'Canon à plasma lourd – surcharge', '36\"', 'D3', 8, -3, '3', 3, NULL),
(58, 'Canon à plasma lourd jumelé – standard', '36\"', 'D3', 7, -2, '2', 3, NULL),
(59, 'Canon à plasma lourd jumelé – surchage', '36\"', 'D3', 8, -3, '3', 3, NULL),
(60, 'Canon d’assaut jumelé', '24\"', '6', 6, 0, '1', 3, NULL),
(61, 'Canon d’assaut modèle Kheres', '24\"', '6', 7, -1, '1', 3, NULL),
(62, 'Canon d’assaut', '24\"', '6', 6, 0, '1', 3, NULL),
(63, 'Canon Demolisher', '24\"', 'D6+3', 14, -3, 'D6', 3, NULL),
(64, 'Canon Flamestorm', '12\"', 'D6+3', 6, -2, '2', 1, NULL),
(65, 'Canon gatling Onslaught lourd jumelé', '24\"', '12', 6, 0, '1', 3, NULL),
(66, 'Canon gatling Onslaught lourd', '24\"', '12', 6, 0, '1', 3, NULL),
(67, 'Canon gatling Onslaught', '24\"', '8', 5, 0, '1', 3, NULL),
(68, 'Canon Incendium', '12\"', 'D6+3', 6, -1, '1', 1, NULL),
(69, 'Canon laser Ballistus', '48\"', '2', 12, -3, 'D6+1', 3, NULL),
(70, 'Canon laser Godhammer', '48\"', '2', 12, -3, 'D6+1', 3, NULL),
(71, 'Canon laser jumelé', '36\"', '1', 12, -3, 'D6+1', 3, NULL),
(72, 'Canon laser jumelé', '48\"', '1', 12, -3, 'D6+1', 3, NULL),
(73, 'Canon laser jumelé', '48\"', '1', 12, -3, 'D6+1', 3, NULL),
(74, 'Canon laser Predator jumelé', '48\"', '1', 14, -3, 'D6+1', 3, NULL),
(75, 'Canon laser', '48\"', '1', 12, -3, 'D6+1', 3, NULL),
(76, 'Canon Storm Icarus', '48\"', '6', 7, -1, '2', 3, NULL),
(77, 'Canon Thunderfire', '48\"', 'D6+4', 5, 0, '1', 3, NULL),
(78, 'Carabine bolter à lunette de maître', '24\"', '2', 4, 0, '2', 2, NULL),
(79, 'Carabine bolter d’élite', '24\"', '2', 4, 0, '1', 3, NULL),
(80, 'Carabine bolter Instigator', '24\"', '1', 4, -2, '2', 2, NULL),
(81, 'Carabine bolter Instigator', '24\"', '1', 4, -2, '3', 3, NULL),
(82, 'Carabine bolter Occulus', '24\"', '2', 4, 0, '1', 3, NULL),
(83, 'Carabine bolter', '24\"', '2', 4, 0, '1', 3, NULL),
(84, 'Chargeur volkite', '18\"', '2', 5, 0, '2', 3, NULL),
(85, 'Châtiment – feu sorcier focalisé', '24\"', 'D6', 6, -2, 'D3', 3, NULL),
(86, 'Châtiment – feu sorcier', '24\"', 'D6', 5, -1, 'D3', 3, NULL),
(87, 'Chenilles blindées', 'Mêlée', '3', 6, 0, '1', NULL, 4),
(88, 'Chenilles blindées', 'Mêlée', '6', 8, 0, '1', NULL, 4),
(89, 'Combi-bolter', '24\"', '2', 4, 0, '1', 3, NULL),
(90, 'Coque blindée', 'Mêlée', '3', 6, 0, '1', NULL, 4),
(91, 'Coque blindée', 'Mêlée', '6', 8, 0, '1', NULL, 4),
(92, 'Couteau de combat', 'Mêlée', '3', 4, 0, '1', NULL, 3),
(93, 'Couteau de combat', 'Mêlée', '4', 4, 0, '1', NULL, 3),
(94, 'Couteau de combat', 'Mêlée', '6', 4, 0, '1', NULL, 3),
(95, 'Croc Lunaire', 'Mêlée', '6', 5, -2, '2', NULL, 2),
(96, 'Crozius arcanum', 'Mêlée', '5', 6, -1, '2', NULL, 2),
(97, 'Crozius d’artificier', 'Mêlée', '5', 6, -1, '2', NULL, 2),
(98, 'Découpeur à plasma', 'Mêlée', '2', 8, -2, '1', NULL, 3),
(99, 'Découpeur à plasma', 'Mêlée', '2', 8, -2, '2', NULL, 3),
(100, 'Destructeur à fusion', '18\"', '3', 9, -4, 'D6', 3, NULL),
(101, 'Destructeur laser lancer', '72\"', '2', 14, -4, 'D6+3', 3, NULL),
(102, 'Destructeur laser lourd', '72\"', '2', 16, -4, 'D6+4', 3, NULL),
(103, 'Drakkis', '12\"', 'D6+3', 4, -1, '1', 1, NULL),
(104, 'Éclateur à plasma – standard', '18\"', '2', 7, -2, '1', 3, NULL),
(105, 'Éclateur à plasma – surcharge', '18\"', '2', 8, -3, '2', 3, NULL),
(106, 'Épée d’Idaeus', 'Mêlée', '6', 6, -2, '2', NULL, 2),
(107, 'Épée de l’Empereur', 'Mêlée', '14', 8, -3, '2', NULL, 2),
(108, 'Épée énergétique Victrix', 'Mêlée', '5', 5, -2, '2', NULL, 2),
(109, 'Épée tronçonneuse Astartes', 'Mêlée', '3', 4, -1, '1', NULL, 2),
(110, 'Épée tronçonneuse Astartes', 'Mêlée', '4', 4, -1, '1', NULL, 2),
(111, 'Épée tronçonneuse Astartes', 'Mêlée', '5', 4, -1, '1', NULL, 2),
(112, 'Épée tronçonneuse Astartes', 'Mêlée', '6', 4, -1, '1', NULL, 2),
(113, 'Épée tronçonneuse Astartes', 'Mêlée', '7', 4, -1, '1', NULL, 2),
(114, 'Épée tronçonneuse relique', 'Mêlée', '3', 4, -1, '2', NULL, 2),
(115, 'Eviscerator', 'Mêlée', '3', 7, -2, '2', NULL, 4),
(116, 'Exterminateurs à plasma – standard', '18\"', '2', 7, -2, '2', 3, NULL),
(117, 'Exterminateurs à plasma – surcharge', '18\"', '2', 8, -3, '3', 3, NULL),
(118, 'Flèche de Dorn', '24\"', '2', 5, -1, '2', 2, NULL),
(119, 'Foreuses de siège', 'Mêlée', '3', 10, -2, '3', NULL, 3),
(120, 'Fuseur jumelé', '12\"', '1', 9, -4, 'D6', 3, NULL),
(121, 'Fuseur', '12\"', '1', 9, -4, 'D6', 3, NULL),
(122, 'Fusil à gravitons d’artificier', '18\"', '2', 5, -1, '2', 2, NULL),
(123, 'Fusil à gravitons', '18\"', '2', 5, -1, '1', 3, NULL),
(124, 'Fusil à gravitons', '18\"', '2', 5, -1, '2', 3, NULL),
(125, 'Fusil à plasma – standard', '24\"', '1', 7, -2, '1', 3, NULL),
(126, 'Fusil à plasma – surchage', '24\"', '1', 8, -3, '2', 3, NULL),
(127, 'Fusil à pompe Astartes', '18\"', '2', 4, 0, '1', 3, NULL),
(128, 'Fusil bolter de maître', '24\"', '2', 4, -1, '2', 2, NULL),
(129, 'Fusil bolter de sniper', '36\"', '1', 5, -2, '3', 3, NULL),
(130, 'Fusil bolter de Sternguard', '24\"', '2', 4, -1, '1', 3, NULL),
(131, 'Fusil bolter jumelé', '24\"', '2', 4, -1, '1', 3, NULL),
(132, 'Fusil bolter lourd de maître', '30\"', '2', 5, -1, '2', 2, NULL),
(133, 'Fusil bolter lourd', '30\"', '2', 5, -1, '1', 3, NULL),
(134, 'Fusil bolter', '24\"', '2', 4, -1, '1', 3, NULL),
(135, 'Fusil de sniper de Scout', '36\"', '1', 4, -2, '2', 3, NULL),
(136, 'Fusil fuseur', '18\"', '1', 9, -4, 'D6', 3, NULL),
(137, 'Fusils bolters Brutalis', '24\"', '4', 4, -1, '1', 3, NULL),
(138, 'Gantelet Boltstorm', '12\"', '3', 4, -1, '1', 2, NULL),
(139, 'Gantelet de la Forge', '12\"', 'D6+3', 6, -1, '1', 1, NULL),
(140, 'Gantelet énergétique', 'Mêlée', '2', 8, -2, '2', NULL, 2),
(141, 'Gantelet énergétique', 'Mêlée', '3', 8, -2, '2', NULL, 2),
(142, 'Gantelet énergétique', 'Mêlée', '4', 8, -2, '2', NULL, 2),
(143, 'Gantelet énergétique', 'Mêlée', '5', 8, -2, '2', NULL, 2),
(144, 'Gantelet énergétique', 'Mêlée', '6', 8, -2, '2', NULL, 2),
(145, 'Gantelet Invictor', 'Mêlée', '5', 14, -2, '3', NULL, 3),
(146, 'Gantelet relique', 'Mêlée', '1', 8, -2, '2', NULL, 2),
(147, 'Gantelets Boltstorm automatiques', '18\"', '3', 4, 0, '1', 3, NULL),
(148, 'Gantelets d’Ultramar', '18\"', '4', 4, -1, '2', 2, NULL),
(149, 'Gantelets d’Ultramar', 'Mêlée', '6', 8, -3, '3', NULL, 2),
(150, 'Gantelets Flamestorm', '12\"', 'D6+1', 4, 0, '1', 1, NULL),
(151, 'Hache énergétique de l’Omnimessie', 'Mêlée', '4', 6, -2, '2', NULL, 3),
(152, 'Hersemain', 'Mêlée', '6', 7, -2, '2', NULL, 3),
(153, 'Incinérateur à plasma – standard', '24\"', '2', 7, -2, '1', 3, NULL),
(154, 'Incinérateur à plasma – surcharge', '24\"', '2', 8, -3, '2', 3, NULL),
(155, 'Infernus', '12\"', 'D6', 4, -1, '1', 1, NULL),
(156, 'Invictus', '24\"', '2', 4, -1, '2', 2, NULL),
(157, 'Ire de la Gorgone', '36\"', '3', 5, -1, '2', 2, NULL),
(158, 'Lame de Champion', 'Mêlée', '4', 5, -2, '2', NULL, 3),
(159, 'Lame relique d’exécuteur', 'Mêlée', '5', 7, -2, '2', NULL, 2),
(160, 'Lame relique', 'Mêlée', '2', 5, -2, '2', NULL, 2),
(161, 'Lame Tempête de Talassar', 'Mêlée', '6', 5, -2, '2', NULL, 2),
(162, 'Lance de Vulkan', 'Mêlée', '6', 6, -2, '2', NULL, 2),
(163, 'Lance-flammes jumelé', '12\"', 'D6', 4, 0, '1', 1, NULL),
(164, 'Lance-flammes léger', '12\"', 'D6', 3, 0, '1', 1, NULL),
(165, 'Lance-flammes lourd', '12\"', 'D6', 5, -1, '1', 1, NULL),
(166, 'Lance-flammes', '12\"', 'D6', 4, 0, '1', 1, NULL),
(167, 'Lance-grenades Astartes – Frag', '24\"', 'D3', 4, 0, '1', 3, NULL),
(168, 'Lance-grenades Astartes – Krak', '24\"', '1', 9, -2, 'D3', 3, NULL),
(169, 'Lance-grenades Fragstorm jumelé', '18\"', 'D6', 4, 0, '1', 3, NULL),
(170, 'Lance-grenades Fragstorm', '18\"', 'D6', 4, 0, '1', 3, NULL),
(171, 'Lance-grenades Krakstorm', '18\"', '1', 9, -1, 'D3', 3, NULL),
(172, 'Lance-missiles – Frag', '48\"', 'D6', 4, 0, '1', 4, NULL),
(173, 'Lance-missiles – Krak', '48\"', '1', 9, -2, 'D6', 4, NULL),
(174, 'Lance-missiles Ballistus – Frag', '48\"', '2D6', 5, 0, '1', 3, NULL),
(175, 'Lance-missiles Ballistus – Krak', '48\"', '2', 10, -2, 'D6', 3, NULL),
(176, 'Lance-missiles Centurion', '36\"', 'D3', 9, -2, 'D3', 3, NULL),
(177, 'Lance-missiles Cyclone – Frag', '36\"', '2D6', 4, 0, '1', 3, NULL),
(178, 'Lance-missiles Cyclone – Krak', '36\"', '2', 9, -2, 'D6', 3, NULL),
(179, 'Lance-missiles Hammerfall – superfrag', '48\"', '2D6+2', 5, 0, '1', 4, NULL),
(180, 'Lance-missiles Hammerfall – superfrag', '48\"', '2D6+2', 5, 0, '1', 4, NULL),
(181, 'Lance-missiles Hammerfall – superkrak', '48\"', '2', 10, -2, 'D6+1', 4, NULL),
(182, 'Lance-missiles Hammerfall – superkrak', '48\"', '2', 10, -2, 'D6+1', 4, NULL),
(183, 'Lance-missiles Hammerstrike', '36\"', '2', 10, -3, 'D8', 3, NULL),
(184, 'Lance-missiles Skyhammer', '48\"', '3', 8, -1, 'D3', 3, NULL),
(185, 'Lance-missiles Skyspear', '36\"', '1', 10, -3, 'D6+2', 2, NULL),
(186, 'Lance-missiles Stormstrike', '48\"', '1', 10, -2, '3', 3, NULL),
(187, 'Lance-missiles Typhoon – Frag', '48\"', '2D6', 4, 0, '1', 3, NULL),
(188, 'Lance-missiles Typhoon – Krak', '48\"', '2', 9, -2, 'D6', 3, NULL),
(189, 'Lance-roquettes Superfrag', '48\"', 'D6+1', 5, 0, '1', 4, NULL),
(190, 'Lance-roquettes Superkrak', '48\"', '1', 10, -2, 'D6+1', 4, NULL),
(191, 'Lanceur Castellan', '36\"', 'D3', 4, 0, '1', 3, NULL),
(192, 'Lanceur Cerberus', '18\"', 'D6+3', 4, 0, '1', 3, NULL),
(193, 'Lanceur Deathwind', '12\"', 'D6+1', 5, 0, '1', 3, NULL),
(194, 'Lanceur Vengor', '48\"', 'D6', 7, -1, '2', 2, NULL),
(195, 'Lanceur Whirlwind Vengeance', '72\"', 'D6+3', 8, -2, '2', 3, NULL),
(196, 'Macro-incinérateur à plasma – standard', '36\"', 'D6+1', 8, -3, '2', 3, NULL),
(197, 'Macro-incinérateur à plasma – surcharge', '36\"', 'D6+1', 9, -4, '3', 3, NULL),
(198, 'Main de Défi', 'Mêlée', '5', 12, -2, '2', NULL, 2),
(199, 'Main de Domination', '30\"', '2', 6, -2, '2', 2, NULL),
(200, 'Main de Domination', 'Mêlée', '7', 14, -4, '4', NULL, 2),
(201, 'Malleus Noctum', 'Mêlée', '5', 10, -2, '3', NULL, 2),
(202, 'Manipuli Medusiens', 'Mêlée', '2', 8, -2, '3', NULL, 3),
(203, 'Marteau sismique', 'Mêlée', '3', 14, -3, 'D6+2', NULL, 3),
(204, 'Marteau Thunder', 'Mêlée', '2', 8, -2, '2', NULL, 4),
(205, 'Marteau Thunder', 'Mêlée', '3', 8, -2, '2', NULL, 4),
(206, 'Marteau Thunder', 'Mêlée', '4', 8, -2, '2', NULL, 4),
(207, 'Marteau Thunder', 'Mêlée', '5', 8, -2, '2', NULL, 4),
(208, 'Missile Hunter-slayer', '48\"', '1', 14, -3, 'D6', 2, NULL),
(209, 'Missile traqueur', '48\"', '1', 14, -3, 'D6', 2, NULL),
(210, 'Missiles Stormfury', '48\"', '1', 12, -3, 'D6+1', 2, NULL),
(211, 'Mitrailleuse Ironhail Icarus jumelée', '36\"', '3', 4, 0, '1', 3, NULL),
(212, 'Mitrailleuse Ironhail Icarus jumelée', '36\"', '3', 4, -1, '1', 3, NULL),
(213, 'Mitrailleuse Ironhail jumelée', '36\"', '3', 4, 0, '1', 3, NULL),
(214, 'Mitrailleuse Ironhail', '36\"', '3', 4, 0, '1', 3, NULL),
(215, 'Multi-fuseur jumelé', '18\"', '2', 9, -4, 'D6', 3, NULL),
(216, 'Multi-fuseur', '18\"', '2', 10, -4, 'D6', 4, NULL),
(217, 'Multi-fuseur', '18\"', '2', 9, -4, 'D6', 4, NULL),
(218, 'Nacelle lance-roquettes Icarus jumelée', '24\"', 'D3', 8, -1, '2', 3, NULL),
(219, 'Nacelle lance-roquettes Icarus', '24\"', 'D3', 8, -1, '2', 3, NULL),
(220, 'Paire de griffes Lightning', 'Mêlée', '5', 5, -2, '1', NULL, 2),
(221, 'Paire de griffes Lightning', 'Mêlée', '6', 5, -2, '1', NULL, 2),
(222, 'Paire de griffes Lightning', 'Mêlée', '7', 5, -2, '1', NULL, 2),
(223, 'Paire de griffes-Lightning', 'Mêlée', '4', 4, -2, '1', NULL, 2),
(224, 'Paire de griffes-Lightning', 'Mêlée', '5', 4, -2, '1', NULL, 2),
(225, 'Paire de lames de combat', 'Mêlée', '3', 4, 0, '1', NULL, 2),
(226, 'Paire de lames de combat', 'Mêlée', '5', 4, 0, '1', NULL, 2),
(227, 'Pieds blindés', 'Mêlée', '5', 7, 0, '1', NULL, 3),
(228, 'Pistolet à gravitons', '12\"', '1', 4, -1, '1', 3, NULL),
(229, 'Pistolet à gravitons', '12\"', '1', 4, -1, '2', 3, NULL),
(230, 'Pistolet à plasma – standard', '12\"', '1', 7, -2, '1', 2, NULL),
(231, 'Pistolet à plasma – standard', '12\"', '1', 7, -2, '1', 3, NULL),
(232, 'Pistolet à plasma – surcharge', '12\"', '1', 8, -3, '2', 2, NULL),
(233, 'Pistolet à plasma – surcharge', '12\"', '1', 8, -3, '2', 3, NULL),
(235, 'Pistolet bolter Absolvor', '18\"', '1', 5, -1, '2', 3, NULL),
(236, 'Pistolet bolter artisanal', '12\"', '1', 8, -3, '2', 2, NULL),
(237, 'Pistolet bolter de Sternguard', '12\"', '1', 4, 0, '1', 3, NULL),
(238, 'Pistolet bolter lourd', '18\"', '1', 4, -1, '1', 2, NULL),
(239, 'Pistolet bolter spécialisé de maître', '12\"', '1', 4, 0, '2', 2, NULL),
(240, 'Pistolet bolter spécialisé', '12\"', '1', 4, -1, '1', 3, NULL),
(241, 'Pistolet bolter', '12\"', '1', 4, 0, '1', 3, NULL),
(242, 'Pistolet Inferno', '6\"', '1', 8, -4, 'D3', 3, NULL),
(243, 'Pistolet néo-volkite', '12\"', '1', 5, 0, '2', 2, NULL),
(244, 'Pistolet Reductor', '3\"', '1', 4, -4, '2', 3, NULL),
(245, 'Poing de Dorn', 'Mêlée', '5', 10, -3, '3', NULL, 2),
(246, 'Poing de la Vengeance', 'Mêlée', '5', 8, -3, '3', NULL, 2),
(247, 'Poing de Redemptor', 'Mêlée', '5', 12, -2, '3', NULL, 3),
(248, 'Poing tronçonneur de Dreadnought', 'Mêlée', '4', 12, -2, '3', NULL, 3),
(249, 'Poing tronçonneur', 'Mêlée', '3', 8, -2, '2', NULL, 4),
(250, 'Poing tronçonneur', 'Mêlée', '4', 8, -2, '2', NULL, 3),
(251, 'Poing tronçonneur', 'Mêlée', '5', 8, -2, '2', NULL, 3),
(252, 'Poings de Brutalis', 'Mêlée', '6', 12, -2, '3', NULL, 3),
(253, 'Poings de Centurion', 'Mêlée', '5', 14, -2, '3', NULL, 4),
(254, 'Pyro-éclateur', '12\"', 'D6', 5, 0, '2', 1, NULL),
(255, 'Quietus', '36\"', '2', 4, -2, '3', 2, NULL),
(256, 'Serre laser Firestrike jumelée', '36\"', '2', 10, -3, 'D6+1', 2, NULL),
(257, 'Serre laser jumelée', '36\"', '2', 10, -3, 'D6+1', 3, NULL),
(258, 'Serre laser Thunderstrike', '36\"', '2', 9, -3, 'D6+1', 2, NULL),
(259, 'Serre laser', '36\"', '2', 10, -3, 'D6', 3, NULL),
(260, 'Serre laser', '36\"', '2', 10, -3, 'D6+1', 3, NULL),
(261, 'Serres de Brutalis – balayage', 'Mêlée', '10', 7, -2, '1', NULL, 3),
(262, 'Serres de Brutalis – frappe', 'Mêlée', '6', 12, -2, '3', NULL, 3),
(263, 'Serres du Corbeau', 'Mêlée', '7', 5, -2, '2', NULL, 2),
(264, 'Servobras d’Artilleur', 'Mêlée', '2', 8, -2, '3', NULL, 3),
(265, 'Servobras de Chronus', 'Mêlée', '3', 8, -2, '3', NULL, 3),
(266, 'Servobras de Chronus', 'Mêlée', '4', 4, 0, '1', NULL, 3),
(267, 'Servobras de Servitor', 'Mêlée', '1', 6, -2, '3', NULL, 5),
(268, 'Servobras', 'Mêlée', '1', 8, -2, '3', NULL, 3),
(269, 'Syncope', '18\"', '2', 5, -1, '2', 2, NULL),
(270, 'Tempête de la Colère de l’Empereur – feu sorcier focalisé', '18\"', '2D6', 6, -2, '2', 2, NULL),
(271, 'Tempête de la Colère de l’Empereur – feu sorcier', '18\"', 'D6', 6, -2, '2', 2, NULL),
(272, 'Épée tronçonneuse Astartes', 'Mêlée', '5', 4, -1, '1', NULL, 3),
(273, 'Arme énergétique de maître', 'Mêlée', '4', 5, -2, '2', NULL, 2),
(274, 'Pistolet bolter', '12', '1', 4, 0, '1', 2, NULL),
(275, 'Épée Noire – frappe', 'Mêlée', '6', 8, -3, '3', NULL, 2),
(276, 'Épée Noire – balayage', 'Mêlée', '10', 6, -2, '1', NULL, 2),
(277, 'Arme de corps à corps', 'Mêlée', '1', 3, 0, '1', NULL, 4),
(278, 'Épée tronçonneuse Astartes', 'Mêlée', '3', 4, -1, '1', NULL, 3),
(279, 'Gantelet énergétique', 'Mêlée', '2', 8, -2, '2', NULL, 3),
(280, 'Arme énergétique', 'Mêlée', '3', 5, -2, '1', NULL, 3),
(281, 'Arme combinée', '24\"', '1', 4, 0, '1', 4, NULL),
(282, 'Pistolet à gravitons', '12\"', '1', 5, -1, '2', 3, NULL),
(283, 'Bolter Storm', '12\"', '1', 8, -3, '2', 3, NULL),
(284, 'Pistolet bolter lourd', '18\"', '1', 4, -1, '1', 3, NULL),
(285, 'Pyropistolet', '12\"', 'D6', 4, 0, '1', 1, NULL),
(286, 'Férocité', '24\"', '2', 5, -1, '2', 2, NULL),
(287, 'Épée des Grands Sénéchaux – à une main', 'Mêlée', '12', 6, -3, '1', NULL, 2),
(288, 'Épée des Grands Sénéchaux – à deux mains', 'Mêlée', '6', 8, -3, '3', NULL, 2),
(289, 'Pistolet bolter', '18\"', '1', 4, 0, '1', 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `armee`
--

CREATE TABLE `armee` (
  `id_armee` int NOT NULL,
  `nom_armee` varchar(50) DEFAULT NULL,
  `logo_armee` varchar(50) DEFAULT NULL,
  `id_faction` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `armee`
--

INSERT INTO `armee` (`id_armee`, `nom_armee`, `logo_armee`, `id_faction`) VALUES
(1, 'Space Marines', NULL, 1),
(2, 'Black Templars', NULL, 1),
(3, 'Blood Angels', NULL, 1),
(4, 'Dark Angels', NULL, 1),
(5, 'Deathwatch', NULL, 1),
(6, 'Grey Knights', NULL, 1),
(7, 'Space Wolves', NULL, 1),
(8, 'Adepta Sororitas', NULL, 2),
(9, 'Adeptus Custodes', NULL, 2),
(10, 'Adeptus Mechanicus', NULL, 2),
(11, 'Astra Militarum', NULL, 2),
(12, 'Space Marines du Chaos', NULL, 3),
(13, 'Death Guard', NULL, 3),
(14, 'Thousand Sons', NULL, 3),
(15, 'World Eaters', NULL, 3),
(16, 'Démons du Chaos', NULL, 3),
(17, 'Aeldari', NULL, 4),
(18, 'Drukhari', NULL, 4),
(19, 'Tyranides', NULL, 4),
(20, 'Cultes Genestealers', NULL, 4),
(21, 'Ligues de Votann', NULL, 4),
(22, 'Nécrons', NULL, 4),
(23, 'Orks', NULL, 4),
(24, 'Empire T\'au', NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `contenir`
--

CREATE TABLE `contenir` (
  `id_unite` int NOT NULL,
  `id_liste` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `equiper`
--

CREATE TABLE `equiper` (
  `id_figurine` int NOT NULL,
  `id_arme` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `equiper`
--

INSERT INTO `equiper` (`id_figurine`, `id_arme`) VALUES
(2, 5),
(3, 5),
(1, 103),
(1, 201),
(2, 235),
(3, 235),
(3, 244);

-- --------------------------------------------------------

--
-- Table structure for table `faction`
--

CREATE TABLE `faction` (
  `id_faction` int NOT NULL,
  `nom_faction` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `faction`
--

INSERT INTO `faction` (`id_faction`, `nom_faction`) VALUES
(1, 'Space Marines'),
(2, 'Armées de l\'Imperium'),
(3, 'Forces du Chaos'),
(4, 'La Menace Xéno');

-- --------------------------------------------------------

--
-- Table structure for table `figurine`
--

CREATE TABLE `figurine` (
  `id_figurine` int NOT NULL,
  `nom_figurine` varchar(200) DEFAULT NULL,
  `M` varchar(50) DEFAULT NULL,
  `E` int DEFAULT NULL,
  `SV` varchar(50) DEFAULT NULL,
  `PV` int DEFAULT NULL,
  `CD` varchar(50) DEFAULT NULL,
  `CO` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `figurine`
--

INSERT INTO `figurine` (`id_figurine`, `nom_figurine`, `M`, `E`, `SV`, `PV`, `CD`, `CO`) VALUES
(1, 'Adrax Agatone', '6\"', 4, '2+', 5, '6+', 1),
(2, 'Apothicaire Biologis', '5\"', 6, '3+', 5, '6+', 3),
(3, 'Apothicaire Primaris', '6\"', 4, '3+', 4, '6+', 1),
(4, 'Archiviste', '6\"', 4, '3+', 4, '6+', 1),
(5, 'Archiviste à Réacteur Dorsal', '12\"', 4, '3+', 4, '6+', 1),
(6, 'Archiviste Primaris', '6\"', 4, '3+', 4, '6+', 1),
(7, 'Archiviste en Armure Phobos', '6\"', 4, '3+', 4, '6+', 1),
(8, 'Archiviste en Armure Terminator', '5\"', 5, '2+', 5, '6+', 1),
(9, 'Bunker Hammerfall', '-', 12, '2+', 14, '6+', 0),
(10, 'Canon Thunderfire', '3”', 6, '2+', 6, '6+', 2),
(11, 'Artilleur Techmarine', '3”', 6, '2+', 6, '6+', 2),
(12, 'Capitaine', '6\"', 4, '3+', 5, '6+', 1),
(13, 'Capitaine Primaris', '6\"', 4, '3+', 5, '6+', 1),
(14, 'Capitaine Sicarius', '6\"', 4, '2+', 5, '6+', 1),
(15, 'Capitaine en Armure Gravis', '6\"', 6, '3+', 6, '6+', 1),
(16, 'Capitaine en Armure Phobos', '6\"', 4, '3+', 5, '6+', 1),
(17, 'Capitaine en Armure Terminator', '5\"', 5, '2+', 6, '6+', 1),
(18, 'Capitaine à Moto', '12\"', 5, '3+', 6, '6+', 2),
(19, 'Capitaine à Réacteur Dorsal', '12\"', 4, '3+', 5, '6+', 1),
(20, 'Champion de Compagnie Primaris', '6\"', 4, '3+', 4, '6+', 1),
(21, 'Chapelain', '6\"', 4, '3+', 4, '5+', 1),
(22, 'Chapelain Cassius', '6\"', 4, '3+', 4, '5+', 1),
(23, 'Chapelain Primaris', '6\"', 4, '3+', 4, '5+', 1),
(24, 'Chapelain en Armure Terminator', '5\"', 5, '2+', 5, '5+', 1),
(25, 'Chapelain à Moto', '12\"', 5, '3+', 5, '5+', 1),
(26, 'Chapelain à Réacteur Dorsal', '12\"', 4, '3+', 4, '6+', 1),
(27, 'Darnath Lysander', '5\"', 5, '2+', 6, '6+', 1),
(28, 'Doyen Bladeguard', '6\"', 4, '3+', 4, '6+', 1),
(29, 'Doyen en Armure Terminator', '5\"', 5, '2+', 5, '6+', 1),
(30, 'Doyen Primaris', '6\"', 4, '3+', 4, '6+', 1),
(31, 'Dreadnought', '6\"', 9, '2+', 8, '6+', 3),
(32, 'Dreadnought Ballistus', '8\"', 10, '2+', 12, '6+', 4),
(33, 'Dreadnought Brutalis', '8\"', 10, '2+', 12, '6+', 4),
(34, 'Dreadnought Contemptor', '6\"', 9, '2+', 10, '6+', 3),
(35, 'Dreadnought Ironclad', '6\"', 10, '2+', 8, '6+', 3),
(36, 'Dreadnought Redemptor', '8\"', 10, '2+', 12, '6+', 4),
(37, 'Escorteur Stormraven', '20\"', 10, '3+', 14, '6+', 0),
(38, 'Escorteur Stormtalon', '20\"', 8, '3+', 10, '6+', 0),
(39, 'Aggressor Sergent', '5\"', 6, '3+', 3, '6+', 1),
(40, 'Aggressor', '5\"', 6, '3+', 3, '6+', 1),
(41, 'Sergent Centurion Devastator', '4\"', 7, '2+', 4, '6+', 2),
(42, 'Centurion Devastator', '4\"', 7, '2+', 4, '6+', 2),
(43, 'Sergent d’Assaut', '6\"', 4, '3+', 2, '6+', 1),
(44, 'Marine d’Assaut', '6\"', 4, '3+', 2, '6+', 1),
(45, 'Sergent Centurion d\'Assaut', '4\"', 7, '2+', 4, '6+', 2),
(46, 'Centurion d\'Assaut', '4\"', 7, '2+', 4, '6+', 2),
(47, 'Sergent Intecessor d’Assaut', '6\"', 4, '3+', 2, '6+', 2),
(48, 'Intercessor d’Assaut', '6\"', 4, '3+', 2, '6+', 2),
(49, 'Assaut Terminator Sergent', '5\"', 5, '2+', 3, '6+', 1),
(50, 'Assaut Terminator', '5\"', 5, '2+', 3, '6+', 1),
(51, 'Sergent d’Assaut à Réacteur Dorsal', '12\"', 4, '3+', 2, '6+', 1),
(52, 'Marine d’Assaut à Réacteur Dorsal', '12\"', 4, '3+', 2, '6+', 1),
(53, 'Apothicaire', '6\"', 4, '3+', 3, '6+', 1),
(54, 'Doyen de Compagnie', '6\"', 4, '3+', 3, '6+', 1),
(55, 'Champion de Compagnie', '6\"', 4, '3+', 3, '6+', 1),
(56, 'Vétéran de Compagnie', '6\"', 4, '3+', 3, '6+', 1),
(57, 'Sergent Desolator', '6\"', 4, '3+', 2, '6+', 1),
(58, 'Marine Desolator', '6\"', 4, '3+', 2, '6+', 1),
(59, 'Sergent Devastator', '6\"', 4, '3+', 2, '6+', 1),
(60, 'Marine Devastator', '6\"', 4, '3+', 2, '6+', 1),
(61, 'Sergent Eliminator', '6\"', 4, '3+', 2, '6+', 1),
(62, 'Eliminator', '6\"', 4, '3+', 2, '6+', 1),
(63, 'Sergent Eradicator', '5\"', 6, '3+', 6, '6+', 1),
(64, 'Eradicator', '5\"', 6, '3+', 6, '6+', 1),
(65, 'Sergent Hellblaster', '6\"', 4, '3+', 2, '6+', 1),
(66, 'Hellblaster', '6\"', 4, '3+', 2, '6+', 1),
(67, 'Sergent Inceptor', '10\"', 6, '3+', 3, '6+', 1),
(68, 'Inceptor', '10\"', 6, '3+', 3, '6+', 1),
(69, 'Sergent Incursor', '6\"', 4, '3+', 2, '6+', 1),
(70, 'Incursor', '6\"', 4, '3+', 2, '6+', 1),
(71, 'Sergent Infernus', '6\"', 4, '3+', 2, '6+', 1),
(72, 'Marine Infernus', '6\"', 4, '3+', 2, '6+', 1),
(73, 'Sergent Infiltrator', '6\"', 4, '3+', 2, '6+', 1),
(74, 'Infiltrateur', '6\"', 4, '3+', 2, '6+', 1),
(75, 'Sergent Intercessor', '6\"', 4, '3+', 2, '6+', 2),
(76, 'Intercessor', '6\"', 4, '3+', 2, '6+', 2),
(77, 'Sergent Intercessor Lourd', '5\"', 6, '3+', 3, '6+', 2),
(78, 'Intercessor Lourd', '5\"', 6, '3+', 3, '6+', 2),
(79, 'Sergent Outrider', '12\"', 5, '3+', 4, '6+', 2),
(80, 'Outrider', '12\"', 5, '3+', 4, '6+', 2),
(81, 'Quad Invader', '12\"', 5, '3+', 8, '6+', 2),
(82, 'Sergent Reiver', '6\"', 4, '3+', 2, '6+', 1),
(83, 'Reiver', '6\"', 4, '3+', 2, '6+', 1),
(84, 'Sergent Suppressor', '12\"', 4, '3+', 2, '6+', 1),
(85, 'Suppressor', '12\"', 4, '3+', 2, '6+', 1),
(86, 'Sergent Tactique', '6\"', 4, '3+', 2, '6+', 2),
(87, 'Marine Tactique', '6\"', 4, '3+', 2, '6+', 2),
(88, 'Sergent Terminator', '5\"', 5, '2+', 3, '6+', 1),
(89, 'Terminator', '5\"', 5, '2+', 3, '6+', 1),
(90, 'Sergent Terminator Relic', '5\"', 5, '2+', 3, '6+', 1),
(91, 'Terminator Relic', '5\"', 5, '2+', 3, '6+', 1),
(92, 'Sergent Motard', '12\"', 5, '3+', 3, '6+', 2),
(93, 'Motard Space Marine', '12\"', 5, '3+', 3, '6+', 2),
(94, 'Moto d’Assaut', '12\"', 5, '3+', 5, '6+', 2),
(95, 'Sergent Motard Scout', '12\"', 5, '4+', 3, '6+', 2),
(96, 'Motard Scout', '12\"', 5, '4+', 3, '6+', 2),
(97, 'Moto d’Assaut', '12\"', 5, '3+', 5, '6+', 2),
(98, 'Sergent Scout', '6\"', 4, '4+', 2, '6+', 1),
(99, 'Scout', '6\"', 4, '4+', 2, '6+', 1),
(100, 'Sergent Scout Sniper', '6\"', 4, '4+', 2, '6+', 1),
(101, 'Scout Sniper', '6\"', 4, '4+', 2, '6+', 1),
(102, 'Sergent Vétéran Bladeguard', '6\"', 4, '3+', 3, '6+', 1),
(103, 'Vétéran Bladeguard', '6\"', 4, '3+', 3, '6+', 1),
(104, 'Sergent Vétéran Sternguard', '6\"', 4, '3+', 2, '6+', 1),
(105, 'Vétéran Sternguard', '6\"', 4, '3+', 2, '6+', 1),
(106, 'Sergent Vétéran Vanguard', '6\"', 4, '3+', 2, '6+', 1),
(107, 'Vétéran Vanguard', '6\"', 4, '3+', 2, '6+', 1),
(108, 'Sergent Vétéran Vanguard à Réacteur Dorsal', '12\"', 4, '3+', 2, '6+', 1),
(109, 'Vétéran Vanguard à Réacteur Dorsal', '12\"', 4, '3+', 2, '6+', 1),
(110, 'Exo-harnais Tactique Invictor', '8\"', 8, '3+', 12, '6+', 4),
(111, 'Gladiator Lancer', '10\"', 10, '3+', 12, '6+', 3),
(112, 'Gladiator Reaper', '10\"', 10, '3+', 12, '6+', 3),
(113, 'Gladiator Valiant', '10\"', 10, '3+', 12, '6+', 3),
(114, 'Hunter', '9\"', 11, '3+', 11, '6+', 3),
(115, 'Impulsor', '12\"', 9, '3+', 11, '6+', 2),
(116, 'Intercepteur Stormhawk', '20\"', 9, '3+', 10, '6+', 0),
(117, 'Judicateur', '6\"', 4, '3+', 4, '5+', 1),
(118, 'Kayvaan Shrike', '12\"', 4, '3+', 5, '6+', 1),
(119, 'Kor’sarro Khan', '6\"', 4, '3+', 5, '6+', 1),
(120, 'Land Raider', '10\"', 12, '2+', 16, '6+', 5),
(121, 'Land Raider Crusader', '12\"', 12, '2+', 16, '6+', 5),
(122, 'Land Raider Redeemer', '12\"', 12, '2+', 16, '6+', 5),
(123, 'Land Speeder', '14\"', 7, '3+', 6, '6+', 2),
(124, 'Land Speeder Storm', '14\"', 7, '4+', 7, '6+', 1),
(125, 'Land Speeder Tornado', '14\"', 7, '3+', 6, '6+', 2),
(126, 'Land Speeder Typhoon', '14\"', 7, '3+', 6, '6+', 2),
(127, 'Lieutenant', '6\"', 4, '3+', 4, '6+', 1),
(128, 'Lieutenant avec Arme Combinée', '6\"', 4, '3+', 4, '6+', 1),
(129, 'Lieutenant Primaris', '6\"', 4, '3+', 4, '6+', 1),
(130, 'Lieutenant en Armure Phobos', '6\"', 4, '3+', 4, '6+', 1),
(131, 'Lieutenant en Armure Reiver', '6\"', 4, '3+', 4, '6+', 1),
(132, 'Marneus Calgar', '6\"', 6, '2+', 6, '6+', 1),
(133, 'Garde d’Honneur Victrix', '6\"', 4, '2+', 3, '6+', 1),
(134, 'Maître Archiviste Tigurius', '6\"', 4, '3+', 4, '6+', 1),
(135, 'Module de Largage', '-', 7, '3+', 8, '6+', 2),
(136, 'Pedro Kantor', '6\"', 4, '2+', 5, '6+', 1),
(137, 'Predator Annihilator', '10\"', 10, '3+', 11, '6+', 3),
(138, 'Predator Destructor', '10\"', 10, '3+', 11, '6+', 3),
(139, 'Quad Invader', '12\"', 5, '3+', 8, '6+', 2),
(140, 'Razorback', '12\"', 9, '3+', 10, '6+', 2),
(141, 'Repulsor', '10\"', 12, '3+', 16, '6+', 5),
(142, 'Repulsor Executioner', '10\"', 12, '3+', 16, '6+', 5),
(143, 'Rhino', '12\"', 9, '3+', 10, '6+', 2),
(144, 'Roboute Guilliman', '8\"', 9, '2+', 10, '5+', 4),
(145, 'Révérend de Fer Feirros', '5\"', 6, '2+', 6, '6+', 1),
(146, 'Sergent Chronus', '6\"', 4, '2+', 3, '6+', 1),
(147, 'Sergent Telion', '6\"', 4, '4+', 3, '6+', 1),
(148, 'Servitor de l’Astartes', '6\"', 4, '4+', 1, '8+', 0),
(149, 'Servo-tourelle Firestrike', '3\"', 6, '2+', 6, '6+', 2),
(150, 'Stalker', '9\"', 11, '3+', 11, '6+', 3),
(151, 'Storm Speeder Hailstrike', '14\"', 9, '3+', 11, '6+', 3),
(152, 'Storm Speeder Hammerstrike', '14\"', 9, '3+', 11, '6+', 3),
(153, 'Storm Speeder Thunderstrike', '14\"', 9, '3+', 11, '6+', 3),
(154, 'Techmarine', '6\"', 4, '2+', 4, '6+', 1),
(155, 'Techmarine Primaris', '6\"', 4, '2+', 4, '6+', 1),
(156, 'Tor Garadon', '5\"', 6, '3+', 6, '6+', 1),
(157, 'Uriel Ventris', '6\"', 4, '3+', 5, '6+', 1),
(158, 'Vindicator', '9\"', 11, '2+', 11, '6+', 3),
(159, 'Vulkan He’stan', '6\"', 4, '2+', 5, '6+', 1),
(160, 'Sergent Vétéran', '6\"', 4, '3+', 2, '6+', 1),
(161, 'Vétéran des Guerres Tyraniques', '6\"', 4, '3+', 2, '6+', 1),
(162, 'Whirlwind', '10\"', 10, '3+', 11, '6+', 3),
(163, 'Castellan Frère d\'Épée', '6\"', 4, '3+', 3, '6+', 1),
(164, 'Castellan', '6\"', 4, '3+', 4, '6+', 1),
(165, 'Champion de l’Empereur', '6\"', 4, '2+', 5, '6+', 1),
(166, 'Chapelain Grimaldus', '6\"', 4, '3+', 4, '5+', 1),
(167, 'Frère d\'Épée', '6\"', 4, '3+', 2, '6+', 2),
(168, 'Gladiator Lancer', '10\"', 10, '3+', 12, '6+', 3),
(169, 'Gladiator Reaper', '10\"', 10, '3+', 12, '6+', 3),
(170, 'Gladiator Valiant', '10\"', 10, '3+', 12, '6+', 3),
(171, 'Grand Sénéchal Helbrecht', '6\"', 4, '2+', 5, '5+', 2),
(172, 'Impulsor', '12\"', 9, '3+', 11, '6+', 2),
(173, 'Initié Primaris', '6\"', 4, '3+', 2, '6+', 2),
(174, 'Initié', '6\"', 4, '3+', 2, '6+', 2),
(175, 'Maréchal', '6\"', 4, '3+', 4, '6+', 1),
(176, 'Néophyte Primaris', '6\"', 4, '4+', 2, '6+', 2),
(177, 'Néophyte', '6\"', 4, '4+', 2, '6+', 2),
(178, 'Primaris Frère d\'Épée', '6\"', 4, '3+', 2, '6+', 2),
(179, 'Repulsor Executioner', '10\"', 12, '3+', 16, '6+', 5),
(180, 'Repulsor', '10\"', 12, '3+', 16, '6+', 5),
(181, 'Servitor Cénobyte', '6\"', 4, '2+', 1, '8+', 1),
(182, 'Frère d\'Épée Primaris', '6\"', 4, '3+', 3, '6+', 1);

-- --------------------------------------------------------

--
-- Table structure for table `liste`
--

CREATE TABLE `liste` (
  `id_liste` int NOT NULL,
  `nom_liste` varchar(50) DEFAULT NULL,
  `description_liste` varchar(50) DEFAULT NULL,
  `data_liste` varchar(500) DEFAULT NULL,
  `id_utilisateur` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `melee`
--

CREATE TABLE `melee` (
  `id_melee` int NOT NULL,
  `CC` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `melee`
--

INSERT INTO `melee` (`id_melee`, `CC`) VALUES
(1, 'N/A'),
(2, '2+'),
(3, '3+'),
(4, '4+'),
(5, '5+');

-- --------------------------------------------------------

--
-- Table structure for table `permettre`
--

CREATE TABLE `permettre` (
  `id_figurine` int NOT NULL,
  `id_aptitude` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `posseder`
--

CREATE TABLE `posseder` (
  `id_arme` int NOT NULL,
  `id_aptitude_arme` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `remplir`
--

CREATE TABLE `remplir` (
  `id_figurine` int NOT NULL,
  `id_unite` int NOT NULL,
  `nb_figurine` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `remplir`
--

INSERT INTO `remplir` (`id_figurine`, `id_unite`, `nb_figurine`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 1),
(5, 5, 1),
(6, 6, 1),
(7, 7, 1),
(8, 8, 1),
(9, 9, 1),
(10, 10, 1),
(11, 10, 1),
(12, 11, 1),
(13, 12, 1),
(14, 13, 1),
(15, 14, 1),
(16, 15, 1),
(17, 16, 1),
(18, 17, 1),
(19, 18, 1),
(20, 19, 1),
(21, 20, 1),
(22, 21, 1),
(23, 22, 1),
(24, 23, 1),
(25, 24, 1),
(26, 25, 1),
(27, 26, 1),
(28, 27, 1),
(29, 29, 1),
(30, 28, 1),
(31, 30, 1),
(32, 31, 1),
(33, 32, 1),
(34, 33, 1),
(35, 34, 1),
(36, 35, 1),
(37, 36, 1),
(38, 37, 1),
(39, 38, 1),
(39, 39, 1),
(40, 38, 2),
(40, 39, 5),
(41, 40, 1),
(41, 41, 1),
(42, 40, 2),
(42, 41, 5),
(43, 42, 1),
(43, 43, 1),
(44, 42, 4),
(44, 43, 9),
(45, 44, 1),
(45, 45, 1),
(46, 44, 2),
(46, 45, 5),
(47, 46, 1),
(47, 47, 1),
(48, 46, 4),
(48, 47, 9),
(49, 48, 1),
(49, 49, 1),
(50, 48, 4),
(50, 49, 9),
(51, 50, 1),
(51, 51, 1),
(52, 50, 4),
(52, 51, 9),
(53, 52, 1),
(54, 52, 1),
(55, 52, 1),
(56, 52, 2),
(57, 53, 1),
(57, 54, 1),
(58, 53, 4),
(58, 54, 9),
(59, 55, 1),
(59, 56, 1),
(60, 55, 4),
(60, 56, 9),
(61, 57, 1),
(62, 57, 2),
(63, 58, 1),
(63, 59, 1),
(64, 58, 2),
(64, 59, 5),
(65, 60, 1),
(65, 61, 1),
(66, 60, 4),
(66, 61, 9),
(67, 62, 1),
(67, 63, 1),
(68, 62, 2),
(68, 63, 5),
(69, 64, 1),
(69, 65, 1),
(70, 64, 4),
(70, 65, 9),
(71, 66, 1),
(71, 67, 1),
(72, 66, 4),
(72, 67, 9),
(73, 68, 1),
(73, 69, 1),
(74, 68, 4),
(74, 69, 9),
(75, 70, 1),
(75, 71, 1),
(76, 70, 4),
(76, 71, 9),
(77, 72, 1),
(77, 73, 1),
(78, 72, 4),
(78, 73, 9),
(79, 74, 1),
(79, 75, 1),
(80, 74, 2),
(80, 75, 5),
(82, 76, 1),
(82, 77, 1),
(83, 76, 4),
(83, 77, 9),
(84, 78, 1),
(85, 78, 2),
(86, 79, 1),
(87, 79, 9),
(88, 80, 1),
(88, 81, 1),
(89, 80, 4),
(89, 81, 9),
(90, 82, 1),
(90, 83, 1),
(91, 82, 4),
(91, 83, 9),
(92, 84, 1),
(92, 85, 1),
(93, 84, 2),
(93, 85, 5),
(94, 88, 1),
(94, 89, 2),
(94, 90, 3),
(95, 86, 1),
(95, 87, 1),
(96, 86, 2),
(96, 87, 5),
(98, 91, 1),
(98, 92, 1),
(99, 91, 4),
(99, 92, 9),
(100, 91, 1),
(100, 92, 1),
(101, 91, 4),
(101, 92, 9),
(102, 95, 1),
(102, 96, 1),
(103, 95, 2),
(103, 96, 5),
(104, 97, 1),
(104, 98, 1),
(105, 97, 4),
(105, 98, 9),
(106, 99, 1),
(106, 100, 1),
(107, 99, 5),
(107, 100, 9),
(108, 101, 1),
(108, 102, 1),
(109, 101, 4),
(109, 102, 9),
(111, 166, 1),
(112, 167, 1),
(113, 168, 1),
(115, 170, 1),
(141, 173, 1),
(142, 172, 1),
(163, 164, 1),
(163, 165, 1),
(164, 155, 1),
(165, 156, 1),
(166, 157, 1),
(167, 158, 1),
(167, 159, 1),
(167, 160, 1),
(167, 161, 1),
(171, 169, 1),
(173, 162, 5),
(173, 163, 11),
(174, 158, 4),
(174, 159, 9),
(174, 160, 4),
(174, 161, 9),
(175, 171, 1),
(176, 162, 4),
(176, 163, 8),
(177, 160, 5),
(177, 161, 10),
(178, 162, 1),
(178, 163, 1),
(181, 157, 3),
(182, 164, 4),
(182, 165, 10);

-- --------------------------------------------------------

--
-- Table structure for table `tir`
--

CREATE TABLE `tir` (
  `id_tir` int NOT NULL,
  `CT` varchar(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tir`
--

INSERT INTO `tir` (`id_tir`, `CT`) VALUES
(1, 'N/A'),
(2, '2+'),
(3, '3+'),
(4, '4+'),
(5, '5+');

-- --------------------------------------------------------

--
-- Table structure for table `unite`
--

CREATE TABLE `unite` (
  `id_unite` int NOT NULL,
  `nom_unite` varchar(200) DEFAULT NULL,
  `points_unite` int DEFAULT NULL,
  `logo_unite` varchar(50) DEFAULT NULL,
  `id_armee` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `unite`
--

INSERT INTO `unite` (`id_unite`, `nom_unite`, `points_unite`, `logo_unite`, `id_armee`) VALUES
(1, 'Adrax Agatone', 100, NULL, 1),
(2, 'Apothicaire Biologis', 55, NULL, 1),
(3, 'Apothicaire Primaris', 55, NULL, 1),
(4, 'Archiviste', 75, NULL, 1),
(5, 'Archiviste à Réacteur Dorsal', 75, NULL, 1),
(6, 'Archiviste Primaris', 70, NULL, 1),
(7, 'Archiviste en Armure Phobos', 75, NULL, 1),
(8, 'Archiviste en Armure Terminator', 90, NULL, 1),
(9, 'Bunker Hammerfall', 210, NULL, 1),
(10, 'Canon Thunderfire', 90, NULL, 1),
(11, 'Capitaine', 80, NULL, 1),
(12, 'Capitaine Primaris', 80, NULL, 1),
(13, 'Capitaine Sicarius', 95, NULL, 1),
(14, 'Capitaine en Armure Gravis', 95, NULL, 1),
(15, 'Capitaine en Armure Phobos', 80, NULL, 1),
(16, 'Capitaine en Armure Terminator', 95, NULL, 1),
(17, 'Capitaine à Moto', 95, NULL, 1),
(18, 'Capitaine à Réacteur Dorsal', 100, NULL, 1),
(19, 'Champion de Compagnie Primaris', 55, NULL, 1),
(20, 'Chapelain', 70, NULL, 1),
(21, 'Chapelain Cassius', 80, NULL, 1),
(22, 'Chapelain Primaris', 65, NULL, 1),
(23, 'Chapelain en Armure Terminator', 90, NULL, 1),
(24, 'Chapelain à Moto', 80, NULL, 1),
(25, 'Chapelain à Réacteur Dorsal', 85, NULL, 1),
(26, 'Darnath Lysander', 115, NULL, 1),
(27, 'Doyen Bladeguard', 50, NULL, 1),
(28, 'Doyen Primaris', 55, NULL, 1),
(29, 'Doyen en Armure Terminator', 90, NULL, 1),
(30, 'Dreadnought', 160, NULL, 1),
(31, 'Dreadnought Ballistus', 170, NULL, 1),
(32, 'Dreadnought Brutalis', 220, NULL, 1),
(33, 'Dreadnought Contemptor', 170, NULL, 1),
(34, 'Dreadnought Ironclad', 150, NULL, 1),
(35, 'Dreadnought Redemptor', 225, NULL, 1),
(36, 'Escorteur Stormraven', 255, NULL, 1),
(37, 'Escorteur Stormtalon', 175, NULL, 1),
(38, 'Escouade Aggressor', 110, NULL, 1),
(39, 'Escouade Aggressor', 220, NULL, 1),
(40, 'Escouade Centurion Devastator', 205, NULL, 1),
(41, 'Escouade Centurion Devastator', 410, NULL, 1),
(42, 'Escouade d’Assaut', 95, NULL, 1),
(43, 'Escouade d’Assaut', 190, NULL, 1),
(44, 'Escouade d’Assaut Centurion', 175, NULL, 1),
(45, 'Escouade d’Assaut Centurion', 350, NULL, 1),
(46, 'Escouade d’Assaut Intercessor', 90, NULL, 1),
(47, 'Escouade d’Assaut Intercessor', 180, NULL, 1),
(48, 'Escouade d’Assaut Terminator', 200, NULL, 1),
(49, 'Escouade d’Assaut Terminator', 400, NULL, 1),
(50, 'Escouade d’Assaut à Réacteurs Dorsaux', 115, NULL, 1),
(51, 'Escouade d’Assaut à Réacteurs Dorsaux', 230, NULL, 1),
(52, 'Escouade d’État-major', 165, NULL, 1),
(53, 'Escouade Desolator', 120, NULL, 1),
(54, 'Escouade Desolator', 240, NULL, 1),
(55, 'Escouade Devastator', 120, NULL, 1),
(56, 'Escouade Devastator', 200, NULL, 1),
(57, 'Escouade Eliminator', 95, NULL, 1),
(58, 'Escouade Eradicator', 95, NULL, 1),
(59, 'Escouade Eradicator', 190, NULL, 1),
(60, 'Escouade Hellblaster', 125, NULL, 1),
(61, 'Escouade Hellblaster', 250, NULL, 1),
(62, 'Escouade Inceptor', 115, NULL, 1),
(63, 'Escouade Inceptor', 230, NULL, 1),
(64, 'Escouade Incursor', 90, NULL, 1),
(65, 'Escouade Incursor', 180, NULL, 1),
(66, 'Escouade Infernus', 90, NULL, 1),
(67, 'Escouade Infernus', 180, NULL, 1),
(68, 'Escouade Infiltrator', 90, NULL, 1),
(69, 'Escouade Infiltrator', 180, NULL, 1),
(70, 'Escouade Intercessor', 95, NULL, 1),
(71, 'Escouade Intercessor', 190, NULL, 1),
(72, 'Escouade Lourde Intercessor', 110, NULL, 1),
(73, 'Escouade Lourde Intercessor', 220, NULL, 1),
(74, 'Escouade Outrider', 115, NULL, 1),
(75, 'Escouade Outrider', 230, NULL, 1),
(76, 'Escouade Reiver', 95, NULL, 1),
(77, 'Escouade Reiver', 190, NULL, 1),
(78, 'Escouade Suppressor', 95, NULL, 1),
(79, 'Escouade Tactique', 175, NULL, 1),
(80, 'Escouade Terminator', 205, NULL, 1),
(81, 'Escouade Terminator', 410, NULL, 1),
(82, 'Escouade Terminator Relic', 200, NULL, 1),
(83, 'Escouade Terminator Relic', 400, NULL, 1),
(84, 'Escouade de Motos', 80, NULL, 1),
(85, 'Escouade de Motos', 160, NULL, 1),
(86, 'Escouade de Motos Scouts', 75, NULL, 1),
(87, 'Escouade de Motos Scouts', 150, NULL, 1),
(88, 'Escouade de Motos d’Assaut', 55, NULL, 1),
(89, 'Escouade de Motos d’Assaut', 110, NULL, 1),
(90, 'Escouade de Motos d’Assaut', 165, NULL, 1),
(91, 'Escouade de Scouts', 70, NULL, 1),
(92, 'Escouade de Scouts', 140, NULL, 1),
(93, 'Escouade de Scouts Snipers', 75, NULL, 1),
(94, 'Escouade de Scouts Snipers', 150, NULL, 1),
(95, 'Escouade de Vétérans Bladeguards', 100, NULL, 1),
(96, 'Escouade de Vétérans Bladeguards', 200, NULL, 1),
(97, 'Escouade de Vétérans Sternguards', 105, NULL, 1),
(98, 'Escouade de Vétérans Sternguards', 210, NULL, 1),
(99, 'Escouade de Vétérans Vanguards', 120, NULL, 1),
(100, 'Escouade de Vétérans Vanguards', 240, NULL, 1),
(101, 'Escouade de Vétérans Vanguards à Réacteurs Dorsaux', 130, NULL, 1),
(102, 'Escouade de Vétérans Vanguards à Réacteurs Dorsaux', 260, NULL, 1),
(103, 'Exo-harnais Tactique Invictor', 160, NULL, 1),
(104, 'Gladiator Lancer', 145, NULL, 1),
(105, 'Gladiator Reaper', 155, NULL, 1),
(106, 'Gladiator Valiant', 155, NULL, 1),
(107, 'Hunter', 100, NULL, 1),
(108, 'Impulsor', 95, NULL, 1),
(109, 'Intercepteur Stormhawk', 165, NULL, 1),
(110, 'Judicateur', 75, NULL, 1),
(111, 'Kayvaan Shrike', 110, NULL, 1),
(112, 'Kor’sarro Khan', 80, NULL, 1),
(113, 'Land Raider', 275, NULL, 1),
(114, 'Land Raider Crusader', 255, NULL, 1),
(115, 'Land Raider Redeemer', 295, NULL, 1),
(116, 'Land Speeder', 80, NULL, 1),
(117, 'Land Speeder Storm', 70, NULL, 1),
(118, 'Land Speeder Tornado', 95, NULL, 1),
(119, 'Land Speeder Typhoon', 100, NULL, 1),
(120, 'Lieutenant', 80, NULL, 1),
(121, 'Lieutenant avec Arme Combinée', 80, NULL, 1),
(122, 'Lieutenant Primaris', 75, NULL, 1),
(123, 'Lieutenant en Armure Phobos', 60, NULL, 1),
(124, 'Lieutenant en Armure Reiver', 65, NULL, 1),
(125, 'Marneus Calgar', 205, NULL, 1),
(126, 'Maître Archiviste Tigurius', 85, NULL, 1),
(127, 'Module de Largage', 70, NULL, 1),
(128, 'Pedro Kantor', 100, NULL, 1),
(129, 'Predator Annihilator', 130, NULL, 1),
(130, 'Predator Destructor', 135, NULL, 1),
(131, 'Quad Invader', 80, NULL, 1),
(132, 'Razorback', 100, NULL, 1),
(133, 'Repulsor', 195, NULL, 1),
(134, 'Repulsor Executioner', 230, NULL, 1),
(135, 'Rhino', 85, NULL, 1),
(136, 'Roboute Guilliman', 355, NULL, 1),
(137, 'Révérend de Fer Feirros', 105, NULL, 1),
(138, 'Sergent Chronus', 75, NULL, 1),
(139, 'Sergent Telion', 70, NULL, 1),
(140, 'Servitors de l’Astartes', 55, NULL, 1),
(141, 'Servo-tourelles Firestrike', 80, NULL, 1),
(142, 'Servo-tourelles Firestrike', 160, NULL, 1),
(143, 'Stalker', 140, NULL, 1),
(144, 'Storm Speeder Hailstrike', 130, NULL, 1),
(145, 'Storm Speeder Hammerstrike', 160, NULL, 1),
(146, 'Storm Speeder Thunderstrike', 160, NULL, 1),
(147, 'Techmarine', 70, NULL, 1),
(148, 'Techmarine Primaris', 65, NULL, 1),
(149, 'Tor Garadon', 100, NULL, 1),
(150, 'Uriel Ventris', 85, NULL, 1),
(151, 'Vindicator', 205, NULL, 1),
(152, 'Vulkan He’stan', 95, NULL, 1),
(153, 'Vétérans des Guerres Tyraniques', 85, NULL, 1),
(154, 'Whirlwind', 125, NULL, 1),
(155, 'Castellan', 70, NULL, 2),
(156, 'Le Champion de l’Empereur', 85, NULL, 2),
(157, 'Chapelain Grimaldus', 120, NULL, 2),
(158, 'Escouade Crusader', 75, NULL, 2),
(159, 'Escouade Crusader', 145, NULL, 2),
(160, 'Escouade Crusader', 145, NULL, 2),
(161, 'Escouade Crusader', 290, NULL, 2),
(162, 'Escouade Crusader Primaris', 160, NULL, 2),
(163, 'Escouade Crusader Primaris', 320, NULL, 2),
(164, 'Frères d’Épée Primaris', 150, NULL, 2),
(165, 'Frères d’Épée Primaris', 330, NULL, 2),
(166, 'Gladiator Lancer des Black Templars', 160, NULL, 2),
(167, 'Gladiator Reaper des Black Templars', 175, NULL, 2),
(168, 'Gladiator Valiant des Black Templars', 180, NULL, 2),
(169, 'Grand Sénéchal Helbrecht', 105, NULL, 2),
(170, 'Impulsor des Black Templars', 115, NULL, 2),
(171, 'Maréchal', 75, NULL, 2),
(172, 'Repulsor Executioner des Black Templars', 245, NULL, 2),
(173, 'Repulsor des Black Templars', 220, NULL, 2),
(174, 'Astorath', 100, NULL, 3),
(175, 'Capitaine Tycho', 75, NULL, 3),
(176, 'Commandeur Dante', 135, NULL, 3),
(177, 'Dreadnought Archiviste', 185, NULL, 3),
(178, 'Dreadnought de la Compagnie de la Mort', 195, NULL, 3),
(179, 'Dreadnought Furioso', 165, NULL, 3),
(180, 'Frère Corbulo', 75, NULL, 3),
(181, 'Gabriel Seth', 110, NULL, 3),
(182, 'Garde Sanguinienne', 215, NULL, 3),
(183, 'Garde Sanguinienne', 430, NULL, 3),
(184, 'Intercessors de la Compagnie de la Mort', 90, NULL, 3),
(185, 'Intercessors de la Compagnie de la Mort', 180, NULL, 3),
(186, 'Lemartes', 135, NULL, 3),
(187, 'Marines de la Compagnie de la Mort', 130, NULL, 3),
(188, 'Marines de la Compagnie de la Mort', 260, NULL, 3),
(189, 'Marines de la Compagnie de la Mort à Réacteurs Dorsaux', 155, NULL, 3),
(190, 'Marines de la Compagnie de la Mort à Réacteurs Dorsaux', 310, NULL, 3),
(191, 'Maître Archiviste Méphiston', 120, NULL, 3),
(192, 'Predator Baal', 125, NULL, 3),
(193, 'Prêtre Sanguinien', 100, NULL, 3),
(194, 'Prêtre Sanguinien à Réacteur Dorsal', 110, NULL, 3),
(195, 'Le Sanguinor', 160, NULL, 3),
(196, 'Tycho l’Égaré', 90, NULL, 3),
(197, 'Asmodaï', 70, NULL, 4),
(198, 'Azraël', 120, NULL, 4),
(199, 'Bélial', 100, NULL, 4),
(200, 'Chasseur Néphilim', 195, NULL, 4),
(201, 'Chevaliers Noirs de la Ravenwing', 115, NULL, 4),
(202, 'Chevaliers Noirs de la Ravenwing', 230, NULL, 4),
(203, 'Chevaliers de la Deathwing', 235, NULL, 4),
(204, 'Chevaliers de la Deathwing', 470, NULL, 4),
(205, 'Dark Talon de la Ravenwing', 210, NULL, 4),
(206, 'Darkshroud de la Ravenwing', 125, NULL, 4),
(207, 'Escouade Terminator de la Deathwing', 205, NULL, 4),
(208, 'Escouade Terminator de la Deathwing', 410, NULL, 4),
(209, 'Escouade d’État-major de la Deathwing', 215, NULL, 4),
(210, 'Escouade d’État-major de la Deathwing', 430, NULL, 4),
(211, 'Escouade d’État-major de la Ravenwing', 145, NULL, 4),
(212, 'Escouade d’État-major de la Ravenwing', 290, NULL, 4),
(213, 'Land Speeder Vengeance de la Ravenwing', 160, NULL, 4),
(214, 'Lazarus', 80, NULL, 4),
(215, 'Lion El’Jonson', 380, NULL, 4),
(216, 'Maître de Frappe de la Deathwing', 90, NULL, 4),
(217, 'Maître de Serre de la Ravenwing', 115, NULL, 4),
(218, 'Sammaël', 145, NULL, 4),
(219, 'Ézékiel', 80, NULL, 4),
(220, 'Capitaine du Guet Artemis', 75, NULL, 5),
(221, 'Corvus Blackstar', 180, NULL, 5),
(222, 'Escouade Terminator Deathwatch', 210, NULL, 5),
(223, 'Escouade Terminator Deathwatch', 420, NULL, 5),
(224, 'Escouade de Vétérans à Motos', 85, NULL, 5),
(225, 'Escouade de Vétérans à Motos', 170, NULL, 5),
(226, 'Kill Team Cassius', 255, NULL, 5),
(227, 'Kill Team Fortis', 115, NULL, 5),
(228, 'Kill Team Fortis', 230, NULL, 5),
(229, 'Kill Team Indomitor', 135, NULL, 5),
(230, 'Kill Team Indomitor', 270, NULL, 5),
(231, 'Kill Team Proteus', 165, NULL, 5),
(232, 'Kill Team Proteus', 330, NULL, 5),
(233, 'Kill Team Spectrus', 110, NULL, 5),
(234, 'Kill Team Spectrus', 220, NULL, 5),
(235, 'Maître du Guet', 115, NULL, 5),
(236, 'Vétérans Deathwatch', 100, NULL, 5),
(237, 'Vétérans Deathwatch', 200, NULL, 5),
(238, 'Archiviste de Confrérie', 110, NULL, 6),
(239, 'Castellan Crowe', 95, NULL, 6),
(240, 'Champion de Confrérie', 85, NULL, 6),
(241, 'Chapelain de Confrérie', 90, NULL, 6),
(242, 'Cuirassier Némésis', 215, NULL, 6),
(243, 'Dreadnought Vénérable des Grey Knights', 155, NULL, 6),
(244, 'Escouade d’Incursion', 135, NULL, 6),
(245, 'Escouade d’Incursion', 270, NULL, 6),
(246, 'Escouade Interceptor', 160, NULL, 6),
(247, 'Escouade Interceptor', 320, NULL, 6),
(248, 'Escouade Paladin', 255, NULL, 6),
(249, 'Escouade Paladin', 510, NULL, 6),
(250, 'Escouade Purgator', 165, NULL, 6),
(251, 'Escouade Purgator', 275, NULL, 6),
(252, 'Escouade Purificator', 150, NULL, 6),
(253, 'Escouade Purificator', 300, NULL, 6),
(254, 'Escouade Terminator de Confrérie', 225, NULL, 6),
(255, 'Escouade Terminator de Confrérie', 450, NULL, 6),
(256, 'Escorteur Stormraven des Grey Knights', 265, NULL, 6),
(257, 'Escorteur Stormtalon des Grey Knights', 170, NULL, 6),
(258, 'Frère-Capitaine', 95, NULL, 6),
(259, 'Frère-Capitaine Stern', 110, NULL, 6),
(260, 'Grand Maître', 115, NULL, 6),
(261, 'Grand Maître Voldus', 115, NULL, 6),
(262, 'Grand Maître en Cuirasse Némésis', 245, NULL, 6),
(263, 'Intercepteur Stormhawk des Grey Knights', 160, NULL, 6),
(264, 'Kaldor Draigo', 155, NULL, 6),
(265, 'Land Raider des Grey Knights', 270, NULL, 6),
(266, 'Land Raider Crusader des Grey Knights', 250, NULL, 6),
(267, 'Land Raider Redeemer des Grey Knights', 290, NULL, 6),
(268, 'Razorback des Grey Knights', 95, NULL, 6),
(269, 'Rhino des Grey Knights', 80, NULL, 6),
(270, 'Servitors', 50, NULL, 6),
(271, 'Techmarine de Confrérie', 85, NULL, 6),
(272, 'Arjac Rockfist', 105, NULL, 7),
(273, 'Björn Main Funeste', 220, NULL, 7),
(274, 'Canis Wolfborn', 85, NULL, 7),
(275, 'Cavalerie sur Loups Tonnerre', 100, NULL, 7),
(276, 'Cavalerie sur Loups Tonnerre', 200, NULL, 7),
(277, 'Chasseurs Gris', 90, NULL, 7),
(278, 'Chasseurs Gris', 180, NULL, 7),
(279, 'Chef de Bataille Garde Loup en Armure Terminator', 80, NULL, 7),
(280, 'Chef de Bataille Garde Loup sur Loup Tonnerre', 95, NULL, 7),
(281, 'Chef de Meute Garde Loup', 30, NULL, 7),
(282, 'Chef de Meute Garde Loup à Réacteur Dorsal', 40, NULL, 7),
(283, 'Chef de Meute Garde Loup en Armure Terminator', 45, NULL, 7),
(284, 'Cyberloup', 20, NULL, 7),
(285, 'Dreadnought Vénérable des Space Wolves', 170, NULL, 7),
(286, 'Dreadnought Wulfen', 155, NULL, 7),
(287, 'Escorteur Stormfang', 300, NULL, 7),
(288, 'Gardes Loups', 95, NULL, 7),
(289, 'Gardes Loups', 190, NULL, 7),
(290, 'Griffes Sanglantes', 140, NULL, 7),
(291, 'Griffes Sanglantes', 210, NULL, 7),
(292, 'Harald Deathwolf', 95, NULL, 7),
(293, 'Krom Dragongaze', 80, NULL, 7),
(294, 'Logan Grimnar', 145, NULL, 7),
(295, 'Logan Grimnar sur Stormrider', 235, NULL, 7),
(296, 'Longs Crocs', 150, NULL, 7),
(297, 'Longs Crocs', 180, NULL, 7),
(298, 'Loups Fenrissiens', 30, NULL, 7),
(299, 'Loups Fenrissiens', 60, NULL, 7),
(300, 'Lukas le Trompeur', 55, NULL, 7),
(301, 'Molosses de Morkaï', 95, NULL, 7),
(302, 'Molosses de Morkaï', 190, NULL, 7),
(303, 'Murderfang', 190, NULL, 7),
(304, 'Njál Stormcaller', 95, NULL, 7),
(305, 'Prêtre de Fer', 60, NULL, 7),
(306, 'Ragnar Blackmane', 105, NULL, 7),
(307, 'Scouts Space Wolves', 80, NULL, 7),
(308, 'Scouts Space Wolves', 160, NULL, 7),
(309, 'Seigneur Loup sur Loup Tonnerre', 100, NULL, 7),
(310, 'Serres Sanglantes', 95, NULL, 7),
(311, 'Serres Sanglantes', 190, NULL, 7),
(312, 'Serres Sanglantes', 285, NULL, 7),
(313, 'Stormwolf', 250, NULL, 7),
(314, 'Terminators Gardes Loups', 200, NULL, 7),
(315, 'Terminators Gardes Loups', 400, NULL, 7),
(316, 'Ulrik le Tueur', 85, NULL, 7),
(317, 'Wulfen', 100, NULL, 7),
(318, 'Wulfen', 200, NULL, 7),
(319, 'Aestred Thurga et Agathae Dolan', 70, NULL, 8),
(320, 'Arco-flagellants', 45, NULL, 8),
(321, 'Arco-flagellants', 150, NULL, 8),
(322, 'Assassins du Culte de la Mort', 35, NULL, 8),
(323, 'Assassins du Culte de la Mort', 70, NULL, 8),
(324, 'Assassins du Culte de la Mort', 105, NULL, 8),
(325, 'Castigator', 150, NULL, 8),
(326, 'Chanoinesse', 60, NULL, 8),
(327, 'Croisés', 20, NULL, 8),
(328, 'Croisés', 40, NULL, 8),
(329, 'Croisés', 60, NULL, 8),
(330, 'Célestes Sacro-saintes', 65, NULL, 8),
(331, 'Célestes Sacro-saintes', 130, NULL, 8),
(332, 'Dialogus', 35, NULL, 8),
(333, 'Dogmata', 50, NULL, 8),
(334, 'Démonifuge', 80, NULL, 8),
(335, 'Escouade de Sœurs Novices', 90, NULL, 8),
(336, 'Escouade de Sœurs de Bataille', 110, NULL, 8),
(337, 'Escouade Dominion', 130, NULL, 8),
(338, 'Escouade Repentia', 75, NULL, 8),
(339, 'Escouade Repentia', 150, NULL, 8),
(340, 'Escouade Retributor', 130, NULL, 8),
(341, 'Escouade Séraphine', 70, NULL, 8),
(342, 'Escouade Séraphine', 140, NULL, 8),
(343, 'Escouade Zéphyrine', 70, NULL, 8),
(344, 'Escouade Zéphyrine', 140, NULL, 8),
(345, 'Exo-harnais Parangon', 240, NULL, 8),
(346, 'Exorcist', 140, NULL, 8),
(347, 'Hospitalière', 40, NULL, 8),
(348, 'Imagifère', 40, NULL, 8),
(349, 'Immolator', 130, NULL, 8),
(350, 'Junith Eruita', 100, NULL, 8),
(351, 'Machines de Pénitence', 60, NULL, 8),
(352, 'Machines de Pénitence', 120, NULL, 8),
(353, 'Missionnaire', 35, NULL, 8),
(354, 'Mortificatrices', 60, NULL, 8),
(355, 'Mortificatrices', 120, NULL, 8),
(356, 'Morvenn Vahl', 135, NULL, 8),
(357, 'Palatine', 55, NULL, 8),
(358, 'Prêcheur', 45, NULL, 8),
(359, 'Rhino Sororitas', 80, NULL, 8),
(360, 'Sainte Célestine', 150, NULL, 8),
(361, 'Triomphe de Sainte Katherine', 150, NULL, 8),
(362, 'Aleya', 80, NULL, 9),
(363, 'Capitaine-Rempart', 120, NULL, 9),
(364, 'Capitaine-Rempart en Armure Terminator Allarus', 120, NULL, 9),
(365, 'Capitaine-Rempart sur Motojet Dawneagle', 145, NULL, 9),
(366, 'Champion des Lames', 100, NULL, 9),
(367, 'Chevalier-Centura', 75, NULL, 9),
(368, 'Custodiens Allarus', 130, NULL, 9),
(369, 'Custodiens Allarus', 195, NULL, 9),
(370, 'Custodiens Allarus', 325, NULL, 9),
(371, 'Custodiens Allarus', 390, NULL, 9),
(372, 'Dreadnought Contemptor Vénérable', 185, NULL, 9),
(373, 'Gardes Custodiens', 180, NULL, 9),
(374, 'Gardes Custodiens', 225, NULL, 9),
(375, 'Gardes Custodiens', 405, NULL, 9),
(376, 'Gardes Custodiens', 450, NULL, 9),
(377, 'Land Raider Vénérable', 260, NULL, 9),
(378, 'Praetors Vertus', 180, NULL, 9),
(379, 'Praetors Vertus', 270, NULL, 9),
(380, 'Praetors Vertus', 450, NULL, 9),
(381, 'Praetors Vertus', 540, NULL, 9),
(382, 'Prosecutors', 40, NULL, 9),
(383, 'Prosecutors', 50, NULL, 9),
(384, 'Prosecutors', 90, NULL, 9),
(385, 'Prosecutors', 100, NULL, 9),
(386, 'Rhino Anathema Psykana', 75, NULL, 9),
(387, 'Répurgatrices', 60, NULL, 9),
(388, 'Répurgatrices', 75, NULL, 9),
(389, 'Répurgatrices', 135, NULL, 9),
(390, 'Répurgatrices', 150, NULL, 9),
(391, 'Trajann Valoris', 145, NULL, 9),
(392, 'Valerian', 115, NULL, 9),
(393, 'Veilleurs Custodiens', 150, NULL, 9),
(394, 'Veilleurs Custodiens', 300, NULL, 9),
(395, 'Vigilators', 60, NULL, 9),
(396, 'Vigilators', 75, NULL, 9),
(397, 'Vigilators', 135, NULL, 9),
(398, 'Vigilators', 150, NULL, 9),
(399, 'Archéoptère Fusilave', 160, NULL, 10),
(400, 'Archéoptère Stratoraptor', 165, NULL, 10),
(401, 'Archéoptère Transvector', 155, NULL, 10),
(402, 'Belisarius Cawl', 185, NULL, 10),
(403, 'Brécheurs Kataphron', 150, NULL, 10),
(404, 'Brécheurs Kataphron', 300, NULL, 10),
(405, 'Compilateur Cybernétique', 35, NULL, 10),
(406, 'Corrôdeurs Sicariens', 75, NULL, 10),
(407, 'Corrôdeurs Sicariens', 150, NULL, 10),
(408, 'Destructeurs Kataphron', 120, NULL, 10),
(409, 'Destructeurs Kataphron', 240, NULL, 10),
(410, 'Dragons Sydoniens', 75, NULL, 10),
(411, 'Dragons Sydoniens', 150, NULL, 10),
(412, 'Dragons Sydoniens', 225, NULL, 10),
(413, 'Désintégrateur Skorpius', 195, NULL, 10),
(414, 'Électroprêtres Corpuscarii', 65, NULL, 10),
(415, 'Électroprêtres Corpuscarii', 130, NULL, 10),
(416, 'Électroprêtres Fulgurites', 80, NULL, 10),
(417, 'Électroprêtres Fulgurites', 160, NULL, 10),
(418, 'Ferro-échassiers Ballistarii', 50, NULL, 10),
(419, 'Ferro-échassiers Ballistarii', 100, NULL, 10),
(420, 'Ferro-échassiers Ballistarii', 150, NULL, 10),
(421, 'Glisseur Skorpius', 80, NULL, 10),
(422, 'Hussards Serberys', 75, NULL, 10),
(423, 'Hussards Serberys', 150, NULL, 10),
(424, 'Infiltrateurs Sicariens', 80, NULL, 10),
(425, 'Infiltrateurs Sicariens', 160, NULL, 10),
(426, 'Maréchal Skitarii', 45, NULL, 10),
(427, 'Onagre des Dunes', 140, NULL, 10),
(428, 'Patrouilleurs Skitarii', 100, NULL, 10),
(429, 'Rangers Skitarii', 125, NULL, 10),
(430, 'Robots Kastelan', 215, NULL, 10),
(431, 'Robots Kastelan', 430, NULL, 10),
(432, 'Servitors', 50, NULL, 10),
(433, 'Soufredogues Serberys', 65, NULL, 10),
(434, 'Soufredogues Serberys', 130, NULL, 10),
(435, 'Stérilisateurs Pteraxii', 75, NULL, 10),
(436, 'Stérilisateurs Pteraxii', 150, NULL, 10),
(437, 'Technaugure', 45, NULL, 10),
(438, 'Technoarchéologue', 45, NULL, 10),
(439, 'Technoprêtre Dominus', 75, NULL, 10),
(440, 'Technoprêtre Manipulus', 60, NULL, 10),
(441, 'Vautours Pteraxii', 70, NULL, 10),
(442, 'Vautours Pteraxii', 140, NULL, 10),
(443, 'Attachés Régimentaires', 40, NULL, 11),
(444, 'Baneblade', 540, NULL, 11),
(445, 'Banehammer', 490, NULL, 11),
(446, 'Banesword', 515, NULL, 11),
(447, 'Basilisk', 110, NULL, 11),
(448, 'Batterie d’Artillerie', 100, NULL, 11),
(449, 'Castellan Cadien', 50, NULL, 11),
(450, 'Cavaliers d’Attila', 80, NULL, 11),
(451, 'Cavaliers d’Attila', 160, NULL, 11),
(452, 'Char de Combat Leman Russ', 195, NULL, 11),
(453, 'Char de Combat Rogal Dorn', 285, NULL, 11),
(454, 'Chimera', 85, NULL, 11),
(455, 'Combattants des Jungles de Catachan', 65, NULL, 11),
(456, 'Combattants des Jungles de Catachan', 130, NULL, 11),
(457, 'Commandant Blindé', 240, NULL, 11),
(458, 'Commissaire', 35, NULL, 11),
(459, 'Death Korps de Krieg', 65, NULL, 11),
(460, 'Death Korps de Krieg', 130, NULL, 11),
(461, 'Deathstrike', 135, NULL, 11),
(462, 'Doomhammer', 455, NULL, 11),
(463, 'Escouade d’Armes Lourdes', 60, NULL, 11),
(464, 'Escouade d’État-Major Cadienne', 65, NULL, 11),
(465, 'Escouade d’État-Major de Peloton', 60, NULL, 11),
(466, 'Escouade d’État-Major du Militarum Tempestus', 80, NULL, 11),
(467, 'Escouade d’Infanterie', 65, NULL, 11),
(468, 'Escouade d’Infanterie', 130, NULL, 11),
(469, 'Escouade d’Ogryns', 75, NULL, 11),
(470, 'Escouade d’Ogryns', 150, NULL, 11),
(471, 'Escouade de Taurogryns', 90, NULL, 11),
(472, 'Escouade de Taurogryns', 180, NULL, 11),
(473, 'Fantômes de Gaunt', 115, NULL, 11),
(474, 'Fils du Tempestus', 60, NULL, 11),
(475, 'Fils du Tempestus', 120, NULL, 11),
(476, 'Garde du Corps Ogryn', 40, NULL, 11),
(477, 'Hellhammer', 510, NULL, 11),
(478, 'Hellhound', 125, NULL, 11),
(479, 'Hydra', 95, NULL, 11),
(480, 'Kasrkin', 120, NULL, 11),
(481, 'Leman Russ Demolisher', 220, NULL, 11),
(482, 'Leman Russ Eradicator', 180, NULL, 11),
(483, 'Leman Russ Executioner', 195, NULL, 11),
(484, 'Leman Russ Exterminator', 200, NULL, 11),
(485, 'Leman Russ Punisher', 180, NULL, 11),
(486, 'Leman Russ Vanquisher', 190, NULL, 11),
(487, 'Ligne de Défense Aegis', 145, NULL, 11),
(488, 'Manticore', 105, NULL, 11),
(489, 'Nork Deddog', 70, NULL, 11),
(490, 'Prêcheur Régimentaire', 35, NULL, 11),
(491, 'Psyker Primaris', 60, NULL, 11),
(492, 'Seigneur Solaire Leontus', 125, NULL, 11),
(493, 'Sentinel Blindés', 70, NULL, 11),
(494, 'Sentinel Blindés', 140, NULL, 11),
(495, 'Sentinel Blindés', 210, NULL, 11),
(496, 'Sentinel de Reconnaissance', 50, NULL, 11),
(497, 'Sentinel de Reconnaissance', 100, NULL, 11),
(498, 'Sentinel de Reconnaissance', 150, NULL, 11),
(499, 'Sergent Harker', 60, NULL, 11),
(500, 'Servitors du Munitorum', 35, NULL, 11),
(501, 'Shadowsword', 440, NULL, 11),
(502, 'Sly Marbo', 75, NULL, 11),
(503, 'Snipers Ratlings', 70, NULL, 11),
(504, 'Stormlord', 460, NULL, 11),
(505, 'Stormsword', 520, NULL, 11),
(506, 'Straken “Poing de Fer”', 80, NULL, 11),
(507, 'Taurox', 65, NULL, 11),
(508, 'Taurox Primus', 90, NULL, 11),
(509, 'Technaugure Régimentaire', 45, NULL, 11),
(510, 'Troupes de Choc Cadiennes', 65, NULL, 11),
(511, 'Troupes de Choc Cadiennes', 130, NULL, 11),
(512, 'Ursula Creed', 55, NULL, 11),
(513, 'Valkyrie', 200, NULL, 11),
(514, 'Wyvern', 90, NULL, 11),
(515, 'Abaddon le Fléau', 280, NULL, 12),
(516, 'Accusateur Renégat', 65, NULL, 12),
(517, 'Accusateur Renégat', 65, NULL, 12),
(518, 'Apôtre Noir', 85, NULL, 12),
(519, 'Bande de Cultistes', 55, NULL, 12),
(520, 'Bande de Cultistes', 110, NULL, 12),
(521, 'Champion Exalté', 70, NULL, 12),
(522, 'Couronne de Noctilithe', 125, NULL, 12),
(523, 'Cultistes Maudits', 95, NULL, 12),
(524, 'Cultistes Maudits', 190, NULL, 12),
(525, 'Cypher', 105, NULL, 12),
(526, 'Escouade Terminator du Chaos', 195, NULL, 12),
(527, 'Escouade Terminator du Chaos', 390, NULL, 12),
(528, 'Escouade de Gardes Renégats', 70, NULL, 12),
(529, 'Fabius Bile', 100, NULL, 12),
(530, 'Ferrocentaurus', 165, NULL, 12),
(531, 'Ferrocerberus', 155, NULL, 12),
(532, 'Haarken Worldclaimer', 120, NULL, 12),
(533, 'Havocs', 135, NULL, 12),
(534, 'Hommes-bêtes Affregors', 95, NULL, 12),
(535, 'Huron Blackheart', 80, NULL, 12),
(536, 'Land Raider du Chaos', 255, NULL, 12),
(537, 'Lucius l’Éternel', 95, NULL, 12),
(538, 'Légionnaires', 100, NULL, 12),
(539, 'Légionnaires', 200, NULL, 12),
(540, 'Marines du Vacarme', 85, NULL, 12),
(541, 'Marines du Vacarme', 170, NULL, 12),
(542, 'Maître de la Possession', 70, NULL, 12),
(543, 'Maître des Exécutions', 80, NULL, 12),
(544, 'Motards du Chaos', 95, NULL, 12),
(545, 'Motards du Chaos', 190, NULL, 12),
(546, 'Métabrutus', 155, NULL, 12),
(547, 'Métadrak', 205, NULL, 12),
(548, 'Métaragne', 140, NULL, 12),
(549, 'Obliterators', 160, NULL, 12),
(550, 'Obliterators', 320, NULL, 12),
(551, 'Possédés', 145, NULL, 12),
(552, 'Possédés', 290, NULL, 12),
(553, 'Predator Annihilator du Chaos', 135, NULL, 12),
(554, 'Predator Destructor du Chaos', 130, NULL, 12),
(555, 'Prince Démon de l’Hereticus Astartes', 180, NULL, 12),
(556, 'Prince Démon de l’Hereticus Astartes Ailé', 195, NULL, 12),
(557, 'Profanateur', 210, NULL, 12),
(558, 'Rapaces', 95, NULL, 12),
(559, 'Rapaces', 190, NULL, 12),
(560, 'Rejetons du Chaos', 80, NULL, 12),
(561, 'Rhino du Chaos', 85, NULL, 12),
(562, 'Seigneur de la Discorde sur Métarôdeur', 220, NULL, 12),
(563, 'Seigneur des Crânes de Khorne', 420, NULL, 12),
(564, 'Seigneur du Chaos', 75, NULL, 12),
(565, 'Seigneur du Chaos en Armure Terminator', 100, NULL, 12),
(566, 'Serres du Warp', 130, NULL, 12),
(567, 'Serres du Warp', 260, NULL, 12),
(568, 'Sombre Communion', 55, NULL, 12),
(569, 'Sorcier', 60, NULL, 12),
(570, 'Sorcier en Armure Terminator', 90, NULL, 12),
(571, 'Techmancien', 70, NULL, 12),
(572, 'Vashtorr l’Arkifane', 225, NULL, 12),
(573, 'Vindicator du Chaos', 210, NULL, 12),
(574, 'Élus', 115, NULL, 12),
(575, 'Élus', 230, NULL, 12),
(576, 'Biologus Putréfacteur', 60, NULL, 13),
(577, 'Chenillé Crachepeste', 175, NULL, 13),
(578, 'Chirurgien de la Peste', 65, NULL, 13),
(579, 'Corrupteur Nidoreux', 55, NULL, 13),
(580, 'Cultistes de la Death Guard', 55, NULL, 13),
(581, 'Cultistes de la Death Guard', 110, NULL, 13),
(582, 'Délabreur Délétère', 60, NULL, 13),
(583, 'Drone Fétide', 135, NULL, 13),
(584, 'Essaimeur Répugnant', 75, NULL, 13),
(585, 'Exhausteur Pandémique', 115, NULL, 13),
(586, 'Intendant', 55, NULL, 13),
(587, 'Land Raider de la Death Guard', 250, NULL, 13),
(588, 'Marines de la Peste', 100, NULL, 13),
(589, 'Marines de la Peste', 200, NULL, 13),
(590, 'Mortarion', 370, NULL, 13),
(591, 'Métabrutus de la Death Guard', 155, NULL, 13),
(592, 'Porte-icône de la Death Guard', 55, NULL, 13),
(593, 'Predator Annihilator de la Death Guard', 130, NULL, 13),
(594, 'Predator Destructor de la Death Guard', 140, NULL, 13),
(595, 'Prince Démon de la Death Guard', 170, NULL, 13),
(596, 'Prince Démon de la Death Guard Ailé', 215, NULL, 13),
(597, 'Profanateur de la Death Guard', 205, NULL, 13),
(598, 'Rejetons du Chaos de la Death Guard', 75, NULL, 13),
(599, 'Rhino de la Death Guard', 80, NULL, 13),
(600, 'Seigneur de la Contagion', 100, NULL, 13),
(601, 'Seigneur de la Virulence', 115, NULL, 13),
(602, 'Seigneur du Chaos de la Death Guard', 75, NULL, 13),
(603, 'Seigneur du Chaos de la Death Guard en Armure Terminator', 100, NULL, 13),
(604, 'Semi-chenillés Méphitiques', 115, NULL, 13),
(605, 'Semi-chenillés Méphitiques', 230, NULL, 13),
(606, 'Semi-chenillés Méphitiques', 345, NULL, 13),
(607, 'Sorcier de la Death Guard en Armure Terminator', 80, NULL, 13),
(608, 'Terminators Rouillarques', 175, NULL, 13),
(609, 'Terminators Rouillarques', 350, NULL, 13),
(610, 'Terminators du Linceul', 140, NULL, 13),
(611, 'Terminators du Linceul', 280, NULL, 13),
(612, 'Typhus', 115, NULL, 13),
(613, 'Véroleux', 60, NULL, 13),
(614, 'Véroleux', 120, NULL, 13),
(615, 'Ahriman', 110, NULL, 14),
(616, 'Ahriman sur Disque de Tzeentch', 115, NULL, 14),
(617, 'Chaman Tzaangor', 60, NULL, 14),
(618, 'Cultistes des Thousand Sons', 65, NULL, 14),
(619, 'Cultistes des Thousand Sons', 130, NULL, 14),
(620, 'Ferrocentaurus des Thousand Sons', 135, NULL, 14),
(621, 'Ferrocerberus des Thousand Sons', 140, NULL, 14),
(622, 'Land Raider des Thousand Sons', 250, NULL, 14),
(623, 'Magnus le Rouge', 410, NULL, 14),
(624, 'Marines Rubricae', 95, NULL, 14),
(625, 'Marines Rubricae', 190, NULL, 14),
(626, 'Maître Infernal', 75, NULL, 14),
(627, 'Mutalithe à Vortex', 145, NULL, 14),
(628, 'Métabrutus des Thousand Sons', 145, NULL, 14),
(629, 'Métadrak des Thousand Sons', 195, NULL, 14),
(630, 'Predator Annihilator des Thousand Sons', 120, NULL, 14),
(631, 'Predator Destructor des Thousand Sons', 125, NULL, 14),
(632, 'Prince Démon des Thousand Sons', 210, NULL, 14),
(633, 'Prince Démon des Thousand Sons Ailé', 190, NULL, 14),
(634, 'Profanateur des Thousand Sons', 200, NULL, 14),
(635, 'Rejetons du Chaos des Thousand Sons', 65, NULL, 14),
(636, 'Rhino des Thousand Sons', 75, NULL, 14),
(637, 'Sorcier Exalté', 90, NULL, 14),
(638, 'Sorcier Exalté sur Disque de Tzeentch', 105, NULL, 14),
(639, 'Sorcier des Thousand Sons', 85, NULL, 14),
(640, 'Sorcier des Thousand Sons en Armure Terminator', 105, NULL, 14),
(641, 'Terminators du Scarabée Occulte', 205, NULL, 14),
(642, 'Terminators du Scarabée Occulte', 410, NULL, 14),
(643, 'Tzaangors', 65, NULL, 14),
(644, 'Tzaangors', 130, NULL, 14),
(645, 'Tzaangors Éclairés', 45, NULL, 14),
(646, 'Tzaangors Éclairés', 90, NULL, 14),
(647, 'Vindicator des Thousand Sons', 200, NULL, 14),
(648, 'Angron', 415, NULL, 15),
(649, 'Berzerks de Khorne', 115, NULL, 15),
(650, 'Berzerks de Khorne', 230, NULL, 15),
(651, 'Chakhals', 75, NULL, 15),
(652, 'Chakhals', 150, NULL, 15),
(653, 'Escouade Terminator des World Eaters', 195, NULL, 15),
(654, 'Escouade Terminator des World Eaters', 390, NULL, 15),
(655, 'Ferrocentaurus des World Eaters', 150, NULL, 15),
(656, 'Ferrocerberus des World Eaters', 175, NULL, 15),
(657, 'Khârn le Félon', 95, NULL, 15),
(658, 'Land Raider des World Eaters', 255, NULL, 15),
(659, 'Maître des Exécutions des World Eaters', 80, NULL, 15),
(660, 'Métabrutus des World Eaters', 150, NULL, 15),
(661, 'Métadrak des World Eaters', 215, NULL, 15),
(662, 'Octoliés', 155, NULL, 15),
(663, 'Octoliés', 310, NULL, 15),
(664, 'Octoliés Exaltés', 180, NULL, 15),
(665, 'Octoliés Exaltés', 360, NULL, 15),
(666, 'Predator Annihilator des World Eaters', 130, NULL, 15),
(667, 'Predator Destructor des World Eaters', 130, NULL, 15),
(668, 'Prince Démon des World Eaters', 220, NULL, 15),
(669, 'Prince Démon des World Eaters Ailé', 215, NULL, 15),
(670, 'Profanateur des World Eaters', 210, NULL, 15),
(671, 'Rejetons du Chaos des World Eaters', 65, NULL, 15),
(672, 'Rhino des World Eaters', 85, NULL, 15),
(673, 'Seigneur Invocatus', 155, NULL, 15),
(674, 'Seigneur des Crânes de Khorne', 420, NULL, 15),
(675, 'Seigneur des World Eaters sur Juggernaut', 120, NULL, 15),
(676, 'Autel des Crânes', 105, NULL, 16),
(677, 'Be’lakor', 325, NULL, 16),
(678, 'Bourdons de la Peste', 120, NULL, 16),
(679, 'Bourdons de la Peste', 240, NULL, 16),
(680, 'Broyeur d’Âmes', 215, NULL, 16),
(681, 'Buveur de Sang', 300, NULL, 16),
(682, 'Bêtes de Nurgle', 75, NULL, 16),
(683, 'Bêtes de Nurgle', 150, NULL, 16),
(684, 'Canon à Crânes', 115, NULL, 16),
(685, 'Char Ardent', 135, NULL, 16),
(686, 'Char Traqueur', 75, NULL, 16),
(687, 'Char Traqueur', 150, NULL, 16),
(688, 'Char Traqueur Exalté', 115, NULL, 16),
(689, 'Char Écorcheur', 110, NULL, 16),
(690, 'Char Écorcheur', 220, NULL, 16),
(691, 'Charnelles', 150, NULL, 16),
(692, 'Charnelles', 300, NULL, 16),
(693, 'Comique Tripier', 55, NULL, 16),
(694, 'Duc du Changement', 230, NULL, 16),
(695, 'Démonettes', 140, NULL, 16),
(696, 'Epidemius', 85, NULL, 16),
(697, 'Falsificateur', 65, NULL, 16),
(698, 'Fluctuarque', 60, NULL, 16),
(699, 'Fortunarque', 105, NULL, 16),
(700, 'Gardien des Secrets', 330, NULL, 16),
(701, 'Grand Immonde', 280, NULL, 16),
(702, 'Gueule Noueuse Immonde', 100, NULL, 16),
(703, 'Horreurs Bleues', 125, NULL, 16),
(704, 'Horreurs Roses', 140, NULL, 16),
(705, 'Horticulous Slimux', 120, NULL, 16),
(706, 'Hurleurs', 90, NULL, 16),
(707, 'Hurleurs', 180, NULL, 16),
(708, 'Incendiaire Exalté', 70, NULL, 16),
(709, 'Incendiaires', 65, NULL, 16),
(710, 'Incendiaires', 130, NULL, 16),
(711, 'Kairos Fateweaver', 285, NULL, 16),
(712, 'Karanak', 85, NULL, 16),
(713, 'Maître des Crânes', 105, NULL, 16),
(714, 'Maître du Sang', 85, NULL, 16),
(715, 'Le Masque de Slaanesh', 105, NULL, 16),
(716, 'Molosses de Khorne', 70, NULL, 16),
(717, 'Molosses de Khorne', 140, NULL, 16),
(718, 'Mutileur sur Trône de Sang', 150, NULL, 16),
(719, 'Le Mystificateur', 75, NULL, 16),
(720, 'Nurglings', 40, NULL, 16),
(721, 'Nurglings', 80, NULL, 16),
(722, 'Nurglings', 120, NULL, 16),
(723, 'Porte-vérole', 75, NULL, 16),
(724, 'Portepestes', 145, NULL, 16),
(725, 'Preneur de Crânes', 95, NULL, 16),
(726, 'Prince Démon du Chaos', 215, NULL, 16),
(727, 'Prince Démon du Chaos Ailé', 195, NULL, 16),
(728, 'Rotigus', 285, NULL, 16),
(729, 'Sanguinaires', 160, NULL, 16),
(730, 'Scribe Avarieur', 65, NULL, 16),
(731, 'Les Scribes Bleus', 65, NULL, 16),
(732, 'Shalaxi Helbane', 400, NULL, 16),
(733, 'Skarbrand', 365, NULL, 16),
(734, 'Syll’esske', 140, NULL, 16),
(735, 'Séductrice Infernale', 65, NULL, 16),
(736, 'Tissetranse', 60, NULL, 16),
(737, 'Tourmenteuse sur Char Traqueur Exalté', 160, NULL, 16),
(738, 'Veneuses', 95, NULL, 16),
(739, 'Veneuses', 190, NULL, 16),
(740, 'Épitome Tortueux', 85, NULL, 16),
(741, 'Équarrisseurs', 120, NULL, 16),
(742, 'Équarrisseurs', 240, NULL, 16),
(743, 'Araignées Spectrales', 100, NULL, 17),
(744, 'Araignées Spectrales', 200, NULL, 17),
(745, 'Arme d’Appui', 85, NULL, 17),
(746, 'Asurmen', 120, NULL, 17),
(747, 'Autarque', 65, NULL, 17),
(748, 'Autarque Coureur Céleste', 80, NULL, 17),
(749, 'Autarque Sautevoie', 80, NULL, 17),
(750, 'Avatar de Khaine', 295, NULL, 17),
(751, 'Baharroth', 125, NULL, 17),
(752, 'Banshees Huantes', 85, NULL, 17),
(753, 'Banshees Huantes', 170, NULL, 17),
(754, 'Bouffon Tragique', 70, NULL, 17),
(755, 'Chasseur Fantôme Conium', 155, NULL, 17),
(756, 'Chasseur Écarlate', 160, NULL, 17),
(757, 'Chevalier Fantôme', 370, NULL, 17),
(758, 'Conclave de Psycharques', 60, NULL, 17),
(759, 'Conclave de Psycharques', 120, NULL, 17),
(760, 'Conclave de Psycharques Coureurs Célestes', 100, NULL, 17),
(761, 'Conclave de Psycharques Coureurs Célestes', 150, NULL, 17),
(762, 'Corsaires Neantari', 70, NULL, 17),
(763, 'Corsaires Neantari', 140, NULL, 17),
(764, 'Corsaires Neanti', 90, NULL, 17),
(765, 'Corsaires Neanti', 180, NULL, 17),
(766, 'Coryphée', 55, NULL, 17),
(767, 'Coureurs Mirages', 80, NULL, 17),
(768, 'Coureurs Mirages', 160, NULL, 17),
(769, 'Dragons Flamboyants', 85, NULL, 17),
(770, 'Dragons Flamboyants', 170, NULL, 17),
(771, 'Eldrad Ulthran', 100, NULL, 17),
(772, 'Faucheurs Noirs', 75, NULL, 17),
(773, 'Faucheurs Noirs', 150, NULL, 17),
(774, 'Faucon', 140, NULL, 17),
(775, 'Fuegan', 115, NULL, 17),
(776, 'Gardes Fantômes', 155, NULL, 17),
(777, 'Gardes Fantômes', 310, NULL, 17),
(778, 'Gardiens Défenseurs', 110, NULL, 17),
(779, 'Gardiens de Choc', 115, NULL, 17),
(780, 'Gardiens du Vent', 80, NULL, 17),
(781, 'Gardiens du Vent', 160, NULL, 17),
(782, 'Gardiens du Vent', 240, NULL, 17),
(783, 'Grand Prophète', 65, NULL, 17),
(784, 'Grand Prophète Coureur Céleste', 75, NULL, 17),
(785, 'Guerriers Fantômes', 170, NULL, 17),
(786, 'Guerriers Fantômes', 340, NULL, 17),
(787, 'Illic Nightspear', 65, NULL, 17),
(788, 'Jain Zar', 105, NULL, 17),
(789, 'Karandras', 100, NULL, 17),
(790, 'Lances Étincelantes', 120, NULL, 17),
(791, 'Lances Étincelantes', 240, NULL, 17),
(792, 'Marcheur de Guerre', 95, NULL, 17),
(793, 'Maugan Ra', 130, NULL, 17),
(794, 'Portail sur la Toile', 220, NULL, 17),
(795, 'Prince Yriel', 100, NULL, 17),
(796, 'Prisme de Feu', 125, NULL, 17),
(797, 'Prophète de l’Ombre', 60, NULL, 17),
(798, 'Psycharque', 45, NULL, 17),
(799, 'Psycharque Coureur Céleste', 55, NULL, 17),
(800, 'Rangers', 55, NULL, 17),
(801, 'Rangers', 110, NULL, 17),
(802, 'Scorpions Foudroyants', 75, NULL, 17),
(803, 'Scorpions Foudroyants', 150, NULL, 17),
(804, 'Seigneur Fantôme', 160, NULL, 17),
(805, 'Serpent Ondoyant', 120, NULL, 17),
(806, 'Solitaire', 115, NULL, 17),
(807, 'Spirite', 65, NULL, 17),
(808, 'Tisseur Stellaire', 80, NULL, 17),
(809, 'Tisseur de Nuit', 140, NULL, 17),
(810, 'Tisseur du Néant', 100, NULL, 17),
(811, 'Tisseurs Célestes', 95, NULL, 17),
(812, 'Tisseurs Célestes', 190, NULL, 17),
(813, 'Troupe', 75, NULL, 17),
(814, 'Troupe', 90, NULL, 17),
(815, 'Troupe', 165, NULL, 17),
(816, 'Troupe', 180, NULL, 17),
(817, 'Vengeurs Lugubres', 70, NULL, 17),
(818, 'Vengeurs Lugubres', 140, NULL, 17),
(819, 'Le Visarque', 90, NULL, 17),
(820, 'Vyper', 75, NULL, 17),
(821, 'L’Yncarne', 270, NULL, 17),
(822, 'Yvraine', 100, NULL, 17),
(823, 'Éperviers Voltigeurs', 75, NULL, 17),
(824, 'Éperviers Voltigeurs', 150, NULL, 17),
(825, 'Archonte', 85, NULL, 18),
(826, 'Belluaire', 120, NULL, 18),
(827, 'Bombardier Korvide', 195, NULL, 18),
(828, 'Chasseur Stymphale', 170, NULL, 18),
(829, 'Cour de l’Archonte', 95, NULL, 18),
(830, 'Cour de l’Archonte', 95, NULL, 18),
(831, 'Cronos', 50, NULL, 18),
(832, 'Cronos', 100, NULL, 18),
(833, 'Cérastes', 110, NULL, 18),
(834, 'Drazhar', 105, NULL, 18),
(835, 'Fléaux', 120, NULL, 18),
(836, 'Fléaux', 200, NULL, 18),
(837, 'Gorgones', 65, NULL, 18),
(838, 'Gorgones', 130, NULL, 18),
(839, 'Grotesques', 105, NULL, 18),
(840, 'Grotesques', 210, NULL, 18),
(841, 'Guerriers Kabalites', 120, NULL, 18),
(842, 'Hellions', 110, NULL, 18),
(843, 'Hellions', 220, NULL, 18),
(844, 'Hémoncule', 75, NULL, 18),
(845, 'Incubes', 85, NULL, 18),
(846, 'Incubes', 170, NULL, 18),
(847, 'Lelith Hesperax', 105, NULL, 18),
(848, 'Mandragores', 70, NULL, 18),
(849, 'Mandragores', 140, NULL, 18),
(850, 'Ravageur', 95, NULL, 18),
(851, 'Saccageur', 90, NULL, 18),
(852, 'Succube', 70, NULL, 18),
(853, 'Talos', 90, NULL, 18),
(854, 'Talos', 180, NULL, 18),
(855, 'Urien Rakarth', 105, NULL, 18),
(856, 'Venom', 80, NULL, 18),
(857, 'Écumeurs', 75, NULL, 18),
(858, 'Écumeurs', 150, NULL, 18),
(859, 'Barbgaunts', 50, NULL, 19),
(860, 'Barbgaunts', 100, NULL, 19),
(861, 'Biovores', 40, NULL, 19),
(862, 'Biovores', 80, NULL, 19),
(863, 'Biovores', 120, NULL, 19),
(864, 'Bondisseurs de Von Ryan', 75, NULL, 19),
(865, 'Bondisseurs de Von Ryan', 150, NULL, 19),
(866, 'Carnifex', 125, NULL, 19),
(867, 'Carnifex', 250, NULL, 19),
(868, 'Exocrine', 135, NULL, 19),
(869, 'Gardes Tyranides', 95, NULL, 19),
(870, 'Gardes Tyranides', 190, NULL, 19),
(871, 'Gardes des Ruches', 100, NULL, 19),
(872, 'Gardes des Ruches', 200, NULL, 19),
(873, 'Gargouilles', 75, NULL, 19),
(874, 'Gargouilles', 150, NULL, 19),
(875, 'Genestealers', 90, NULL, 19),
(876, 'Genestealers', 180, NULL, 19),
(877, 'Guerriers Tyranides avec Bio-armes de Mêlée', 90, NULL, 19),
(878, 'Guerriers Tyranides avec Bio-armes de Mêlée', 180, NULL, 19),
(879, 'Guerriers Tyranides avec Bio-armes de Tir', 70, NULL, 19),
(880, 'Guerriers Tyranides avec Bio-armes de Tir', 140, NULL, 19),
(881, 'Génocrate', 100, NULL, 19),
(882, 'Harpie', 170, NULL, 19),
(883, 'Haruspex', 125, NULL, 19),
(884, 'Hormagaunts', 70, NULL, 19),
(885, 'Hormagaunts', 140, NULL, 19),
(886, 'Lictor', 75, NULL, 19),
(887, 'Le Maître des Essaims', 250, NULL, 19),
(888, 'Maleceptor', 165, NULL, 19),
(889, 'Mawloc', 145, NULL, 19),
(890, 'La Mort Subite', 80, NULL, 19),
(891, 'Neurogaunts', 45, NULL, 19),
(892, 'Neurogaunts', 90, NULL, 19),
(893, 'Neurotyran', 105, NULL, 19),
(894, 'Nuées de Voraces', 35, NULL, 19),
(895, 'Nuées de Voraces', 70, NULL, 19),
(896, 'Parasite de Mortrex', 90, NULL, 19),
(897, 'Primat Tyranide Ailé', 80, NULL, 19),
(898, 'Psychophage', 125, NULL, 19),
(899, 'Pyrovores', 30, NULL, 19),
(900, 'Pyrovores', 60, NULL, 19),
(901, 'Pyrovores', 90, NULL, 19),
(902, 'Rôdeurs', 75, NULL, 19),
(903, 'Rôdeurs', 150, NULL, 19),
(904, 'Spores MInes', 50, NULL, 19),
(905, 'Spores MInes', 100, NULL, 19),
(906, 'Spores Mucolides', 45, NULL, 19),
(907, 'Spores Mucolides', 90, NULL, 19),
(908, 'Sporokyste', 145, NULL, 19),
(909, 'Termagants', 60, NULL, 19),
(910, 'Termagants', 120, NULL, 19),
(911, 'Tervigon', 200, NULL, 19),
(912, 'Toxicrène', 200, NULL, 19),
(913, 'Trygon', 180, NULL, 19),
(914, 'Tueur-hurleur', 180, NULL, 19),
(915, 'Tyran des Ruches', 220, NULL, 19),
(916, 'Tyran des Ruches Ailé', 195, NULL, 19),
(917, 'Tyrannocyte', 105, NULL, 19),
(918, 'Tyrannofex', 200, NULL, 19),
(919, 'Venomthropes', 70, NULL, 19),
(920, 'Venomthropes', 140, NULL, 19),
(921, 'Le Vieux Borgne', 140, NULL, 19),
(922, 'Virago des Ruches', 200, NULL, 19),
(923, 'Zoanthropes', 90, NULL, 19),
(924, 'Zoanthropes', 180, NULL, 19),
(925, 'Aberrants', 165, NULL, 20),
(926, 'Aberrants', 330, NULL, 20),
(927, 'Abominant', 75, NULL, 20),
(928, 'Acolyte Garde-icône', 50, NULL, 20),
(929, 'Alphus Chacal', 60, NULL, 20),
(930, 'Biophagus', 50, NULL, 20),
(931, 'Camion Goliath', 110, NULL, 20),
(932, 'Chacals Atalans', 80, NULL, 20),
(933, 'Chacals Atalans', 160, NULL, 20),
(934, 'Clamavus', 40, NULL, 20),
(935, 'Concasseur Goliath', 155, NULL, 20),
(936, 'Genestealers Pure-souche', 90, NULL, 20),
(937, 'Genestealers Pure-souche', 180, NULL, 20),
(938, 'Hybrides Acolytes', 75, NULL, 20),
(939, 'Hybrides Acolytes', 150, NULL, 20),
(940, 'Hybrides Métamorphes', 80, NULL, 20),
(941, 'Hybrides Métamorphes', 160, NULL, 20),
(942, 'Hybrides Néophytes', 80, NULL, 20),
(943, 'Hybrides Néophytes', 160, NULL, 20),
(944, 'Kelermorphe', 55, NULL, 20),
(945, 'Locus', 40, NULL, 20),
(946, 'Magus', 50, NULL, 20),
(947, 'Nexos', 50, NULL, 20),
(948, 'Patriarche', 85, NULL, 20),
(949, 'Primus', 70, NULL, 20),
(950, 'Saboteur Reductus', 55, NULL, 20),
(951, 'Sanctus', 50, NULL, 20),
(952, 'Tout-terrain Achilles', 75, NULL, 20),
(953, 'Tout-terrain Achilles', 150, NULL, 20),
(954, 'Beserks Cthoniens', 135, NULL, 21),
(955, 'Beserks Cthoniens', 270, NULL, 21),
(956, 'Champion Einhyr', 75, NULL, 21),
(957, 'Forteresse Mobile Hekaton', 245, NULL, 21),
(958, 'Grimnyr', 75, NULL, 21),
(959, 'Guerriers Âtrekogs', 135, NULL, 21),
(960, 'Kâhl', 90, NULL, 21),
(961, 'Maître-fer Brôkhyr', 75, NULL, 21),
(962, 'Pionniers Hernkogs', 105, NULL, 21),
(963, 'Pionniers Hernkogs', 210, NULL, 21),
(964, 'Sagitaur', 120, NULL, 21),
(965, 'Tonnekogs Brokhyrs', 95, NULL, 21),
(966, 'Tonnekogs Brokhyrs', 190, NULL, 21),
(967, 'Âtregardes Einhyrs', 165, NULL, 21),
(968, 'Âtregardes Einhyrs', 330, NULL, 21),
(969, 'Ûthar le Destiné', 115, NULL, 21),
(970, 'Anrakyr le Voyageur', 95, NULL, 22),
(971, 'Arachnydes Canopteks', 75, NULL, 22),
(972, 'Arachnydes Canopteks', 150, NULL, 22),
(973, 'Arche Fantôme', 125, NULL, 22),
(974, 'Arche du Jugement Dernier', 185, NULL, 22),
(975, 'Chronomancien', 50, NULL, 22),
(976, 'Console de Commandement', 150, NULL, 22),
(977, 'Console d’Annihilation', 115, NULL, 22),
(978, 'Convergence de Domination', 255, NULL, 22),
(979, 'Crypte Tesseract', 425, NULL, 22),
(980, 'Cryptoserfs', 40, NULL, 22),
(981, 'C’tan Transcendant', 280, NULL, 22),
(982, 'Destroyer Hexmark', 70, NULL, 22),
(983, 'Destroyers Lokhusts', 30, NULL, 22),
(984, 'Destroyers Lokhusts', 60, NULL, 22),
(985, 'Destroyers Lokhusts', 90, NULL, 22),
(986, 'Destroyers Lokhusts', 180, NULL, 22),
(987, 'Destroyers Lourds Lokhusts', 45, NULL, 22),
(988, 'Destroyers Lourds Lokhusts', 90, NULL, 22),
(989, 'Destroyers Lourds Lokhusts', 135, NULL, 22),
(990, 'Destroyers Ophydiens', 110, NULL, 22),
(991, 'Destroyers Ophydiens', 220, NULL, 22),
(992, 'Destroyers Skorpekhs', 110, NULL, 22),
(993, 'Destroyers Skorpekhs', 220, NULL, 22),
(994, 'Dynaste', 65, NULL, 22),
(995, 'Dépeceurs', 70, NULL, 22),
(996, 'Dépeceurs', 140, NULL, 22),
(997, 'Factionnaires', 95, NULL, 22),
(998, 'Factionnaires', 190, NULL, 22),
(999, 'Faucheur', 225, NULL, 22),
(1000, 'Garde Royal', 40, NULL, 22),
(1001, 'Guerriers Nécrons', 120, NULL, 22),
(1002, 'Guerriers Nécrons', 240, NULL, 22),
(1003, 'Illuminor Szeras', 220, NULL, 22),
(1004, 'Immortels', 70, NULL, 22),
(1005, 'Immortels', 140, NULL, 22),
(1006, 'Imotekh le Seigneur des Tempêtes', 105, NULL, 22),
(1007, 'Maraudeur Canoptek', 125, NULL, 22),
(1008, 'Moissonneur', 145, NULL, 22),
(1009, 'Monolithe', 385, NULL, 22),
(1010, 'Mécanoptères', 80, NULL, 22),
(1011, 'Mécanoptères', 160, NULL, 22),
(1012, 'Nuées de Scarabées Canopteks', 40, NULL, 22),
(1013, 'Nuées de Scarabées Canopteks', 80, NULL, 22),
(1014, 'Némésor Zahndrekh', 85, NULL, 22),
(1015, 'Obélisque', 325, NULL, 22),
(1016, 'Orikan le Devin', 80, NULL, 22),
(1017, 'Plasmancien', 55, NULL, 22),
(1018, 'Prétoriens du Triarcat', 135, NULL, 22),
(1019, 'Prétoriens du Triarcat', 270, NULL, 22),
(1020, 'Psychomancien', 50, NULL, 22),
(1021, 'Le Roi Silencieux', 470, NULL, 22),
(1022, 'Réanimateur Canoptek', 95, NULL, 22),
(1023, 'Rôdeur du Triarcat', 125, NULL, 22),
(1024, 'Seigneur Lokhust', 85, NULL, 22),
(1025, 'Seigneur Skorpekh', 115, NULL, 22),
(1026, 'Spectres Canopteks', 110, NULL, 22),
(1027, 'Spectres Canopteks', 220, NULL, 22),
(1028, 'Technomancien', 60, NULL, 22),
(1029, 'Traqueurs', 65, NULL, 22),
(1030, 'Traqueurs', 130, NULL, 22),
(1031, 'Trazyn l’Infini', 75, NULL, 22),
(1032, 'Tétrarque', 85, NULL, 22),
(1033, 'Vargarde Obyron', 85, NULL, 22),
(1034, 'Écharde C’tan du Dragon du Néant', 270, NULL, 22),
(1035, 'Écharde C’tan du Mystificateur', 265, NULL, 22),
(1036, 'Écharde C’tan du Nyctophore', 255, NULL, 22),
(1037, 'Atelier de Mekboy', 80, NULL, 23),
(1038, 'Bizarboy', 55, NULL, 23),
(1039, 'Blitza-bomba', 115, NULL, 23),
(1040, 'Boosta-klata Kustom', 85, NULL, 23),
(1041, 'Boss Dresseur', 80, NULL, 23),
(1042, 'Boss Dresseur sur Squigosaure', 165, NULL, 23),
(1043, 'Boss Snikrot', 105, NULL, 23),
(1044, 'Boss Zagstruk', 100, NULL, 23),
(1045, 'Boss de Guerre', 70, NULL, 23),
(1046, 'Boss de Guerre en Méga-armure', 95, NULL, 23),
(1047, 'Boyz', 85, NULL, 23),
(1048, 'Boyz', 170, NULL, 23),
(1049, 'Boyz Alpagueurs', 105, NULL, 23),
(1050, 'Boyz Alpagueurs', 210, NULL, 23),
(1051, 'Boyz sur Squigliers', 110, NULL, 23),
(1052, 'Boyz sur Squigliers', 220, NULL, 23),
(1053, 'Boît’kitu', 150, NULL, 23),
(1054, 'Boît’kitu', 300, NULL, 23),
(1055, 'Brikojet Mégach’nillé', 90, NULL, 23),
(1056, 'Buggy Fourre-squigs', 110, NULL, 23),
(1057, 'Chariot Boumdakka de lux’', 90, NULL, 23),
(1058, 'Chariot d’Guerre', 185, NULL, 23),
(1059, 'Chokboyz', 65, NULL, 23),
(1060, 'Chokboyz', 130, NULL, 23),
(1061, 'Dakkajet', 135, NULL, 23),
(1062, 'Dragsta Shokk', 85, NULL, 23),
(1063, 'Dred eud’ la Mort', 150, NULL, 23),
(1064, 'Frimeurs', 95, NULL, 23),
(1065, 'Frimeurs', 190, NULL, 23),
(1066, 'Ghazghkull Thraka', 235, NULL, 23),
(1067, 'Gorkanaute', 295, NULL, 23),
(1068, 'Gretchins', 45, NULL, 23),
(1069, 'Gretchins', 90, NULL, 23),
(1070, 'Gros Mek avec Champ de Force Kustom', 65, NULL, 23),
(1071, 'Gros Mek avec Kanon Shokk', 75, NULL, 23),
(1072, 'Gros Mek en Méga-armure', 100, NULL, 23),
(1073, 'Gro’Bunker d’Boss', 135, NULL, 23),
(1074, 'Kamion', 50, NULL, 23),
(1075, 'Kanons d’Mek', 45, NULL, 23),
(1076, 'Kanons d’Mek', 90, NULL, 23),
(1077, 'Kanons d’Mek', 135, NULL, 23),
(1078, 'Kap’tain’ Badrukk', 95, NULL, 23),
(1079, 'Kariol’ Kichass’', 180, NULL, 23),
(1080, 'Kariol’ Kitu', 220, NULL, 23),
(1081, 'Kass’tanks', 135, NULL, 23),
(1082, 'Klat’jet Wazboum', 175, NULL, 23),
(1083, 'Kommandos', 135, NULL, 23),
(1084, 'Kopters d’La Mort', 115, NULL, 23),
(1085, 'Kopters d’La Mort', 230, NULL, 23),
(1086, 'Krabouilleur', 800, NULL, 23),
(1087, 'Krama-bomba', 125, NULL, 23),
(1088, 'Kram’boyz', 65, NULL, 23),
(1089, 'Kram’boyz', 130, NULL, 23),
(1090, 'Kram’boyz', 195, NULL, 23),
(1091, 'Mad Dok Grotsnik', 75, NULL, 23),
(1092, 'Mek', 45, NULL, 23),
(1093, 'Morkanaute', 305, NULL, 23),
(1094, 'Motards de Guerre', 75, NULL, 23),
(1095, 'Motards de Guerre', 150, NULL, 23),
(1096, 'Mozrog Skragbad', 195, NULL, 23),
(1097, 'Médiboss', 70, NULL, 23),
(1098, 'Médiko', 80, NULL, 23),
(1099, 'Méganobz', 65, NULL, 23),
(1100, 'Méganobz', 100, NULL, 23),
(1101, 'Méganobz', 165, NULL, 23),
(1102, 'Méganobz', 200, NULL, 23),
(1103, 'Nob avec Bannière Waaagh!', 70, NULL, 23),
(1104, 'Nob sur Squig Krazeur', 75, NULL, 23),
(1105, 'Nobz', 115, NULL, 23),
(1106, 'Nobz', 230, NULL, 23),
(1107, 'Pillards', 55, NULL, 23),
(1108, 'Pillards', 110, NULL, 23),
(1109, 'Pillards', 165, NULL, 23),
(1110, 'Trike Morkitu', 90, NULL, 23),
(1111, 'Zarboy', 60, NULL, 23),
(1112, 'Zodgrod Wortsnagga', 80, NULL, 23),
(1113, 'Aun’Shi', 60, NULL, 24),
(1114, 'Aun’Va', 65, NULL, 24),
(1115, 'Bombardier Sun Shark', 150, NULL, 24),
(1116, 'Carnivores Kroots', 75, NULL, 24),
(1117, 'Carnivores Kroots', 150, NULL, 24),
(1118, 'Cavaliers Krootox', 35, NULL, 24),
(1119, 'Cavaliers Krootox', 70, NULL, 24),
(1120, 'Cavaliers Krootox', 105, NULL, 24),
(1121, 'Char Hammerhead', 145, NULL, 24),
(1122, 'Char Sky Ray', 160, NULL, 24),
(1123, 'Chasseur Razorshark', 165, NULL, 24),
(1124, 'Chiens Kroots', 30, NULL, 24),
(1125, 'Chiens Kroots', 60, NULL, 24),
(1126, 'Chiens Kroots', 90, NULL, 24),
(1127, 'Commandant Farsight', 120, NULL, 24),
(1128, 'Commandant en Exo-armure Coldstar', 125, NULL, 24),
(1129, 'Commandant en Exo-armure Crisis', 110, NULL, 24),
(1130, 'Commandant en Exo-armure Enforcer', 135, NULL, 24),
(1131, 'Commandante Shadowsun', 140, NULL, 24),
(1132, 'Darkstrider', 75, NULL, 24),
(1133, 'Devilfish', 95, NULL, 24),
(1134, 'Droneport Tidewall', 85, NULL, 24),
(1135, 'Drones Tactiques', 70, NULL, 24),
(1136, 'Drones Tactiques', 140, NULL, 24),
(1137, 'Drones Tactiques', 210, NULL, 24),
(1138, 'Équipe d’Attaque', 100, NULL, 24),
(1139, 'Équipe de Brécheurs', 115, NULL, 24),
(1140, 'Équipe de Cibleurs', 120, NULL, 24),
(1141, 'Équipe de Tir Furtive', 70, NULL, 24),
(1142, 'Éthéré', 50, NULL, 24),
(1143, 'Exo-armure Ghostkeel', 170, NULL, 24),
(1144, 'Exo-armure Riptide', 235, NULL, 24),
(1145, 'Exo-armures Broadside', 110, NULL, 24),
(1146, 'Exo-armures Broadside', 220, NULL, 24),
(1147, 'Exo-armures Broadside', 330, NULL, 24),
(1148, 'Exo-armures Crisis', 195, NULL, 24),
(1149, 'Exo-armures Crisis', 390, NULL, 24),
(1150, 'Exo-armures Stealth', 75, NULL, 24),
(1151, 'Exo-armures Stealth', 150, NULL, 24),
(1152, 'Exorôdeurs Kroots', 105, NULL, 24),
(1153, 'Frelons Vespides', 75, NULL, 24),
(1154, 'Ligne-bouclier Tidewall', 85, NULL, 24),
(1155, 'Plateforme de Défense Tidewall', 20, NULL, 24),
(1156, 'Longstrike', 170, NULL, 24),
(1157, 'Mentor Kroot', 50, NULL, 24),
(1158, 'Piranha', 55, NULL, 24),
(1159, 'Piranha', 110, NULL, 24),
(1160, 'Piranha', 165, NULL, 24),
(1161, 'Sabre de Feu', 50, NULL, 24),
(1162, 'Stormsurge', 405, NULL, 24),
(1163, 'Tourelle Tidewall', 90, NULL, 24);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int NOT NULL,
  `nom_utilisateur` varchar(50) DEFAULT NULL,
  `adresse_utilisateur` varchar(50) DEFAULT NULL,
  `mdp_utilisateur` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aptitude`
--
ALTER TABLE `aptitude`
  ADD PRIMARY KEY (`id_aptitude`);

--
-- Indexes for table `aptitude_arme`
--
ALTER TABLE `aptitude_arme`
  ADD PRIMARY KEY (`id_aptitude_arme`);

--
-- Indexes for table `arme`
--
ALTER TABLE `arme`
  ADD PRIMARY KEY (`id_arme`),
  ADD KEY `id_tir` (`id_tir`),
  ADD KEY `id_melee` (`id_melee`);

--
-- Indexes for table `armee`
--
ALTER TABLE `armee`
  ADD PRIMARY KEY (`id_armee`),
  ADD KEY `id_faction` (`id_faction`);

--
-- Indexes for table `contenir`
--
ALTER TABLE `contenir`
  ADD PRIMARY KEY (`id_unite`,`id_liste`),
  ADD KEY `id_liste` (`id_liste`);

--
-- Indexes for table `equiper`
--
ALTER TABLE `equiper`
  ADD PRIMARY KEY (`id_figurine`,`id_arme`),
  ADD KEY `id_arme` (`id_arme`);

--
-- Indexes for table `faction`
--
ALTER TABLE `faction`
  ADD PRIMARY KEY (`id_faction`);

--
-- Indexes for table `figurine`
--
ALTER TABLE `figurine`
  ADD PRIMARY KEY (`id_figurine`);

--
-- Indexes for table `liste`
--
ALTER TABLE `liste`
  ADD PRIMARY KEY (`id_liste`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Indexes for table `melee`
--
ALTER TABLE `melee`
  ADD PRIMARY KEY (`id_melee`);

--
-- Indexes for table `permettre`
--
ALTER TABLE `permettre`
  ADD PRIMARY KEY (`id_figurine`,`id_aptitude`),
  ADD KEY `id_aptitude` (`id_aptitude`);

--
-- Indexes for table `posseder`
--
ALTER TABLE `posseder`
  ADD PRIMARY KEY (`id_arme`,`id_aptitude_arme`),
  ADD KEY `id_aptitude_arme` (`id_aptitude_arme`);

--
-- Indexes for table `remplir`
--
ALTER TABLE `remplir`
  ADD PRIMARY KEY (`id_figurine`,`id_unite`),
  ADD KEY `id_unite` (`id_unite`);

--
-- Indexes for table `tir`
--
ALTER TABLE `tir`
  ADD PRIMARY KEY (`id_tir`);

--
-- Indexes for table `unite`
--
ALTER TABLE `unite`
  ADD PRIMARY KEY (`id_unite`),
  ADD KEY `id_armee` (`id_armee`);

--
-- Indexes for table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aptitude`
--
ALTER TABLE `aptitude`
  MODIFY `id_aptitude` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `aptitude_arme`
--
ALTER TABLE `aptitude_arme`
  MODIFY `id_aptitude_arme` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `arme`
--
ALTER TABLE `arme`
  MODIFY `id_arme` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=290;

--
-- AUTO_INCREMENT for table `armee`
--
ALTER TABLE `armee`
  MODIFY `id_armee` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `faction`
--
ALTER TABLE `faction`
  MODIFY `id_faction` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `figurine`
--
ALTER TABLE `figurine`
  MODIFY `id_figurine` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=183;

--
-- AUTO_INCREMENT for table `liste`
--
ALTER TABLE `liste`
  MODIFY `id_liste` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `melee`
--
ALTER TABLE `melee`
  MODIFY `id_melee` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tir`
--
ALTER TABLE `tir`
  MODIFY `id_tir` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `unite`
--
ALTER TABLE `unite`
  MODIFY `id_unite` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1164;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `arme`
--
ALTER TABLE `arme`
  ADD CONSTRAINT `arme_ibfk_1` FOREIGN KEY (`id_tir`) REFERENCES `tir` (`id_tir`),
  ADD CONSTRAINT `arme_ibfk_2` FOREIGN KEY (`id_melee`) REFERENCES `melee` (`id_melee`);

--
-- Constraints for table `armee`
--
ALTER TABLE `armee`
  ADD CONSTRAINT `armee_ibfk_1` FOREIGN KEY (`id_faction`) REFERENCES `faction` (`id_faction`);

--
-- Constraints for table `contenir`
--
ALTER TABLE `contenir`
  ADD CONSTRAINT `contenir_ibfk_1` FOREIGN KEY (`id_unite`) REFERENCES `unite` (`id_unite`),
  ADD CONSTRAINT `contenir_ibfk_2` FOREIGN KEY (`id_liste`) REFERENCES `liste` (`id_liste`);

--
-- Constraints for table `equiper`
--
ALTER TABLE `equiper`
  ADD CONSTRAINT `equiper_ibfk_1` FOREIGN KEY (`id_figurine`) REFERENCES `figurine` (`id_figurine`),
  ADD CONSTRAINT `equiper_ibfk_2` FOREIGN KEY (`id_arme`) REFERENCES `arme` (`id_arme`);

--
-- Constraints for table `liste`
--
ALTER TABLE `liste`
  ADD CONSTRAINT `liste_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Constraints for table `permettre`
--
ALTER TABLE `permettre`
  ADD CONSTRAINT `permettre_ibfk_1` FOREIGN KEY (`id_figurine`) REFERENCES `figurine` (`id_figurine`),
  ADD CONSTRAINT `permettre_ibfk_2` FOREIGN KEY (`id_aptitude`) REFERENCES `aptitude` (`id_aptitude`);

--
-- Constraints for table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`id_arme`) REFERENCES `arme` (`id_arme`),
  ADD CONSTRAINT `posseder_ibfk_2` FOREIGN KEY (`id_aptitude_arme`) REFERENCES `aptitude_arme` (`id_aptitude_arme`);

--
-- Constraints for table `remplir`
--
ALTER TABLE `remplir`
  ADD CONSTRAINT `remplir_ibfk_1` FOREIGN KEY (`id_figurine`) REFERENCES `figurine` (`id_figurine`),
  ADD CONSTRAINT `remplir_ibfk_2` FOREIGN KEY (`id_unite`) REFERENCES `unite` (`id_unite`);

--
-- Constraints for table `unite`
--
ALTER TABLE `unite`
  ADD CONSTRAINT `unite_ibfk_1` FOREIGN KEY (`id_armee`) REFERENCES `armee` (`id_armee`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
