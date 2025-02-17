-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 28, 2025 at 02:59 PM
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
  `nom_aptitude` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `aptitude_arme`
--

CREATE TABLE `aptitude_arme` (
  `id_aptitude_arme` int NOT NULL,
  `nom_aptitude_arme` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_x` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `arme`
--

CREATE TABLE `arme` (
  `id_arme` int NOT NULL,
  `nom_arme` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PORTEE` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `A` int DEFAULT NULL,
  `F` int DEFAULT NULL,
  `PA` int DEFAULT NULL,
  `D` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `armee`
--

CREATE TABLE `armee` (
  `id_armee` int NOT NULL,
  `nom_armee` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `logo_armee` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_faction` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `armer`
--

CREATE TABLE `armer` (
  `id_figurine` int NOT NULL,
  `id_arme` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `augmentation_x`
--

CREATE TABLE `augmentation_x` (
  `id_x` int NOT NULL,
  `valeur_x` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `contenir`
--

CREATE TABLE `contenir` (
  `id_unite` int NOT NULL,
  `id_liste` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `faction`
--

CREATE TABLE `faction` (
  `id_faction` int NOT NULL,
  `nom_faction` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `figurine`
--

CREATE TABLE `figurine` (
  `id_figurine` int NOT NULL,
  `nom_figurine` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `logo_figurine` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Mouvement` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Endurance` int DEFAULT NULL,
  `Sauvegarde` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `PV` int DEFAULT NULL,
  `CD` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CO` int DEFAULT NULL,
  `id_unite` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `integrer`
--

CREATE TABLE `integrer` (
  `id_arme` int NOT NULL,
  `id_aptitude_arme` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `liste`
--

CREATE TABLE `liste` (
  `id_liste` int NOT NULL,
  `nom_liste` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description_liste` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_liste` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_utilisateur` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `melee`
--

CREATE TABLE `melee` (
  `id_melee` int NOT NULL,
  `CC` varchar(3) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_arme` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `permettre`
--

CREATE TABLE `permettre` (
  `id_figurine` int NOT NULL,
  `id_aptitude` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tir`
--

CREATE TABLE `tir` (
  `id_tir` int NOT NULL,
  `CT` varchar(3) COLLATE utf8mb4_general_ci NOT NULL,
  `id_arme` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `unite`
--

CREATE TABLE `unite` (
  `id_unite` int NOT NULL,
  `nom_unite` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `points_unite` int DEFAULT NULL,
  `id_armee` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int NOT NULL,
  `nom_utilisateur` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `adresse_utilisateur` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mdp_utilisateur` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  ADD PRIMARY KEY (`id_aptitude_arme`),
  ADD KEY `id_x` (`id_x`);

--
-- Indexes for table `arme`
--
ALTER TABLE `arme`
  ADD PRIMARY KEY (`id_arme`);

--
-- Indexes for table `armee`
--
ALTER TABLE `armee`
  ADD PRIMARY KEY (`id_armee`),
  ADD KEY `id_faction` (`id_faction`);

--
-- Indexes for table `armer`
--
ALTER TABLE `armer`
  ADD PRIMARY KEY (`id_figurine`,`id_arme`),
  ADD KEY `id_arme` (`id_arme`);

--
-- Indexes for table `augmentation_x`
--
ALTER TABLE `augmentation_x`
  ADD PRIMARY KEY (`id_x`);

--
-- Indexes for table `contenir`
--
ALTER TABLE `contenir`
  ADD PRIMARY KEY (`id_unite`,`id_liste`),
  ADD KEY `id_liste` (`id_liste`);

--
-- Indexes for table `faction`
--
ALTER TABLE `faction`
  ADD PRIMARY KEY (`id_faction`);

--
-- Indexes for table `figurine`
--
ALTER TABLE `figurine`
  ADD PRIMARY KEY (`id_figurine`),
  ADD KEY `id_unite` (`id_unite`);

--
-- Indexes for table `integrer`
--
ALTER TABLE `integrer`
  ADD PRIMARY KEY (`id_arme`,`id_aptitude_arme`),
  ADD KEY `id_aptitude_arme` (`id_aptitude_arme`);

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
  ADD PRIMARY KEY (`id_melee`),
  ADD UNIQUE KEY `id_arme` (`id_arme`);

--
-- Indexes for table `permettre`
--
ALTER TABLE `permettre`
  ADD PRIMARY KEY (`id_figurine`,`id_aptitude`),
  ADD KEY `id_aptitude` (`id_aptitude`);

--
-- Indexes for table `tir`
--
ALTER TABLE `tir`
  ADD PRIMARY KEY (`id_tir`),
  ADD UNIQUE KEY `id_arme` (`id_arme`);

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
  MODIFY `id_aptitude_arme` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `arme`
--
ALTER TABLE `arme`
  MODIFY `id_arme` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `armee`
--
ALTER TABLE `armee`
  MODIFY `id_armee` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `augmentation_x`
--
ALTER TABLE `augmentation_x`
  MODIFY `id_x` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `faction`
--
ALTER TABLE `faction`
  MODIFY `id_faction` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `figurine`
--
ALTER TABLE `figurine`
  MODIFY `id_figurine` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `liste`
--
ALTER TABLE `liste`
  MODIFY `id_liste` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `melee`
--
ALTER TABLE `melee`
  MODIFY `id_melee` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tir`
--
ALTER TABLE `tir`
  MODIFY `id_tir` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `unite`
--
ALTER TABLE `unite`
  MODIFY `id_unite` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `aptitude_arme`
--
ALTER TABLE `aptitude_arme`
  ADD CONSTRAINT `aptitude_arme_ibfk_1` FOREIGN KEY (`id_x`) REFERENCES `augmentation_x` (`id_x`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `armee`
--
ALTER TABLE `armee`
  ADD CONSTRAINT `armee_ibfk_1` FOREIGN KEY (`id_faction`) REFERENCES `faction` (`id_faction`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `armer`
--
ALTER TABLE `armer`
  ADD CONSTRAINT `armer_ibfk_1` FOREIGN KEY (`id_figurine`) REFERENCES `figurine` (`id_figurine`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `armer_ibfk_2` FOREIGN KEY (`id_arme`) REFERENCES `arme` (`id_arme`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `contenir`
--
ALTER TABLE `contenir`
  ADD CONSTRAINT `contenir_ibfk_1` FOREIGN KEY (`id_unite`) REFERENCES `unite` (`id_unite`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `contenir_ibfk_2` FOREIGN KEY (`id_liste`) REFERENCES `liste` (`id_liste`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `integrer`
--
ALTER TABLE `integrer`
  ADD CONSTRAINT `integrer_ibfk_1` FOREIGN KEY (`id_arme`) REFERENCES `arme` (`id_arme`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `integrer_ibfk_2` FOREIGN KEY (`id_aptitude_arme`) REFERENCES `aptitude_arme` (`id_aptitude_arme`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `liste`
--
ALTER TABLE `liste`
  ADD CONSTRAINT `liste_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `melee`
--
ALTER TABLE `melee`
  ADD CONSTRAINT `melee_ibfk_1` FOREIGN KEY (`id_arme`) REFERENCES `arme` (`id_arme`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `permettre`
--
ALTER TABLE `permettre`
  ADD CONSTRAINT `permettre_ibfk_1` FOREIGN KEY (`id_aptitude`) REFERENCES `aptitude` (`id_aptitude`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `permettre_ibfk_2` FOREIGN KEY (`id_figurine`) REFERENCES `figurine` (`id_figurine`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `tir`
--
ALTER TABLE `tir`
  ADD CONSTRAINT `tir_ibfk_1` FOREIGN KEY (`id_arme`) REFERENCES `arme` (`id_arme`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `unite`
--
ALTER TABLE `unite`
  ADD CONSTRAINT `unite_ibfk_1` FOREIGN KEY (`id_armee`) REFERENCES `armee` (`id_armee`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
