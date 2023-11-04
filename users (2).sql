-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 04 nov. 2023 à 15:12
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pos`
--

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `question` varchar(50) NOT NULL,
  `answer` varchar(40) NOT NULL,
  `date` datetime NOT NULL,
  `update_date` date DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `email`, `username`, `password`, `question`, `answer`, `date`, `update_date`, `type`) VALUES
(1, 'WAELWAEL', 'WAELWAEL', 'WAELWAELWAEL', 'what is your favourite color?', 'WAELWAEL', '2023-08-03 00:00:00', '2023-08-04', 'user'),
(2, 'WAELWAEL', 'WAELWAEL1', 'WAELWAEL', 'what is your favourite color?', 'WAELWAEL', '2023-08-03 00:00:00', NULL, 'admin'),
(3, 'waelgabsi', 'waelgabsi', 'adminadmin', 'what is your favourite food?', 'waelgabsi', '2023-08-04 00:00:00', '2023-11-01', 'user'),
(4, 'waelgabsi1', 'waelgabsi11', 'waelgabsi', 'what is your favourite food?', 'waelgabsi', '2023-08-04 00:00:00', NULL, 'admin'),
(5, 'MHAMED123@gmail.com', 'mohamed', 'mohamed123', 'what is your favourite food?', 'makrouna', '2023-08-04 00:00:00', NULL, 'user'),
(6, 'waelwael', 'waelgabsi0', 'waelgabsi', 'what is your favourite food?', 'wael', '2023-08-11 00:00:00', NULL, 'user'),
(7, 'ADMIN', 'WAELGABSI00', 'adminadmin', 'what is your favourite color?', 'red', '2023-09-10 00:00:00', '2023-09-10', 'user'),
(8, 'ADMIN', 'ADMIN', 'ADMINADMIN', 'what is your favourite food?', 'ADMIN', '2023-10-23 00:00:00', NULL, 'user'),
(9, 'NexusTocks', 'WaelGabsi00111111', 'NexusNexus', 'what is your favourite color?', 'green', '2023-11-01 00:00:00', NULL, 'user');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
