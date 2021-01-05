-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 04 jan. 2021 à 11:17
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP :  7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetl2info`
--

-- --------------------------------------------------------

--
-- Structure de la table `autreevenement`
--
CREATE DATABASE IF NOT EXISTS projetl2info

CREATE TABLE `autreevenement` (
  `idAutreEvenement` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL,
  `DescriptionAutreEvenement` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `autreevenement`
--

INSERT INTO `autreevenement` (`idAutreEvenement`, `idEvenement`, `DescriptionAutreEvenement`) VALUES
(4, 29, 'Journer des metier toute la journer');

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE `competence` (
  `IdCompetence` int(11) NOT NULL,
  `Competence` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`IdCompetence`, `Competence`) VALUES
(9, 'JAVA'),
(10, 'SQL'),
(11, 'PHP'),
(12, 'HTML'),
(13, 'CSS'),
(14, 'PYTHON');

-- --------------------------------------------------------

--
-- Structure de la table `conference`
--

CREATE TABLE `conference` (
  `idConference` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL,
  `DureeConference` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `conference`
--

INSERT INTO `conference` (`idConference`, `idEvenement`, `DureeConference`) VALUES
(5, 28, '4:00');

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

CREATE TABLE `cours` (
  `idCours` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL,
  `Salle` varchar(5) NOT NULL,
  `Batiment` varchar(10) NOT NULL,
  `DureeCours` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`idCours`, `idEvenement`, `Salle`, `Batiment`, `DureeCours`) VALUES
(12, 27, '1045', 'BAT E', '2:00');

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

CREATE TABLE `entreprise` (
  `IDENTREPRISE` int(11) NOT NULL,
  `RAISON_SOCIALE` varchar(100) NOT NULL,
  `NUM_SIREN` int(9) NOT NULL,
  `CODE_POSTAL` int(5) NOT NULL,
  `ADRESSE` varchar(100) NOT NULL,
  `VILLE` varchar(50) NOT NULL,
  `FAX` varchar(10) DEFAULT NULL,
  `Tel` varchar(10) NOT NULL,
  `SITE_WEB` varchar(30) DEFAULT NULL,
  `AUTRE_INFO` varchar(500) DEFAULT NULL,
  `TAXE_APPRENTISSAGE` varchar(11) DEFAULT NULL,
  `DATEVERSEMENTTAXEAPRENTISSAGE` varchar(11) DEFAULT NULL,
  `IdCompetence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`IDENTREPRISE`, `RAISON_SOCIALE`, `NUM_SIREN`, `CODE_POSTAL`, `ADRESSE`, `VILLE`, `FAX`, `Tel`, `SITE_WEB`, `AUTRE_INFO`, `TAXE_APPRENTISSAGE`, `DATEVERSEMENTTAXEAPRENTISSAGE`, `IdCompetence`) VALUES
(62, 'MICROSOFT', 123456789, 37100, '65 AVENUE NATIONAL', 'TOURS', '0', '0247581825', 'NULL', 'NULL', '0', '0', 9),
(63, 'JAVA4EVER', 475869123, 86500, '5 RUE DES MARONIER ', 'CHATTELERAULT', '0758967898', '0558694520', 'java4ever.com', 'ne participe  a aucun des evenment', '1540.00', '12/12/2020', 14);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `idEvenement` int(11) NOT NULL,
  `NomEvenement` varchar(50) NOT NULL,
  `CodePostal` varchar(5) NOT NULL,
  `Ville` varchar(50) NOT NULL,
  `Adresse` varchar(100) NOT NULL,
  `Date` varchar(10) NOT NULL,
  `Heure` varchar(5) NOT NULL,
  `IdCompetence` int(11) NOT NULL,
  `idPersone` int(11) NOT NULL,
  `idtypeEvenement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`idEvenement`, `NomEvenement`, `CodePostal`, `Ville`, `Adresse`, `Date`, `Heure`, `IdCompetence`, `idPersone`, `idtypeEvenement`) VALUES
(27, 'COURS SUR LA BIO ETHIQUE', '37000', 'TOURS', '2 AVENUE GRANDMONT', '14/01/2021', '14:30', 14, 10, 1),
(28, 'CONFERENCE DE JEAN ', '37100', 'TOURS NORD', '65 RUE DES ROSIER', '07/07/2021', '8:00', 13, 9, 2),
(29, 'FORUM DES ASSOCIATION', '78000', 'CRETEIL', '65 RUE DU MAIRE', '10/10/2021', '18:45', 14, 9, 3);

-- --------------------------------------------------------

--
-- Structure de la table `evenemntentreprise`
--

CREATE TABLE `evenemntentreprise` (
  `idEvenementEntreprise` int(11) NOT NULL,
  `idEntreprise` int(11) NOT NULL,
  `idEvenement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evenemntentreprise`
--

INSERT INTO `evenemntentreprise` (`idEvenementEntreprise`, `idEntreprise`, `idEvenement`) VALUES
(5, 27, 62),
(11, 62, 28),
(12, 63, 29);

-- --------------------------------------------------------

--
-- Structure de la table `persone`
--

CREATE TABLE `persone` (
  `idPersone` int(11) NOT NULL,
  `NomPersone` varchar(50) NOT NULL,
  `PrenomPersone` varchar(50) NOT NULL,
  `TelPersone` varchar(10) NOT NULL,
  `EmailPersonne` varchar(320) NOT NULL,
  `IDENTREPRISE` int(11) NOT NULL,
  `IdCompetence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `persone`
--

INSERT INTO `persone` (`idPersone`, `NomPersone`, `PrenomPersone`, `TelPersone`, `EmailPersonne`, `IDENTREPRISE`, `IdCompetence`) VALUES
(9, 'LEPINOY', 'FELIX', '0768211647', 'Felix.lepinoy@youyou.com', 63, 14),
(10, 'JEAN', 'PIERRE', '0247581625', 'jj.pier@gmail.com', 63, 10);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `idRole` int(11) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `role`) VALUES
(1, 'Personel'),
(2, 'Etudiant');

-- --------------------------------------------------------

--
-- Structure de la table `typeevenement`
--

CREATE TABLE `typeevenement` (
  `idtypeEvenement` int(11) NOT NULL,
  `typeEvenement` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `typeevenement`
--

INSERT INTO `typeevenement` (`idtypeEvenement`, `typeEvenement`) VALUES
(1, 'Cours'),
(2, 'Conference'),
(3, 'AutreEvenement');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idPersonnes` int(11) NOT NULL,
  `Mail` varchar(320) NOT NULL,
  `Mdp` varchar(100) NOT NULL,
  `idRole` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idPersonnes`, `Mail`, `Mdp`, `idRole`) VALUES
(1, 'felix.lepinoy@etu.univ-tours.fr', 'Richelieu37', 2),
(2, 'teste.teste@univ-tours.fr', 'Richelieu37', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `autreevenement`
--
ALTER TABLE `autreevenement`
  ADD PRIMARY KEY (`idAutreEvenement`);

--
-- Index pour la table `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`IdCompetence`);

--
-- Index pour la table `conference`
--
ALTER TABLE `conference`
  ADD PRIMARY KEY (`idConference`);

--
-- Index pour la table `cours`
--
ALTER TABLE `cours`
  ADD PRIMARY KEY (`idCours`),
  ADD KEY `idEvenment` (`idEvenement`);

--
-- Index pour la table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`IDENTREPRISE`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`idEvenement`);

--
-- Index pour la table `evenemntentreprise`
--
ALTER TABLE `evenemntentreprise`
  ADD PRIMARY KEY (`idEvenementEntreprise`);

--
-- Index pour la table `persone`
--
ALTER TABLE `persone`
  ADD PRIMARY KEY (`idPersone`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`idRole`);

--
-- Index pour la table `typeevenement`
--
ALTER TABLE `typeevenement`
  ADD PRIMARY KEY (`idtypeEvenement`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idPersonnes`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `autreevenement`
--
ALTER TABLE `autreevenement`
  MODIFY `idAutreEvenement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `competence`
--
ALTER TABLE `competence`
  MODIFY `IdCompetence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pour la table `conference`
--
ALTER TABLE `conference`
  MODIFY `idConference` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `cours`
--
ALTER TABLE `cours`
  MODIFY `idCours` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `IDENTREPRISE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `idEvenement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `evenemntentreprise`
--
ALTER TABLE `evenemntentreprise`
  MODIFY `idEvenementEntreprise` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `persone`
--
ALTER TABLE `persone`
  MODIFY `idPersone` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `idRole` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `typeevenement`
--
ALTER TABLE `typeevenement`
  MODIFY `idtypeEvenement` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idPersonnes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
