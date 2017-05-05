-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 03 Mai 2017 à 14:54
-- Version du serveur :  10.1.10-MariaDB
-- Version de PHP :  7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `agence`
--

--
-- Contenu de la table `adresse`
--

INSERT INTO `adresse` (`idAdd`, `adresse`, `codePostal`, `ville`, `pays`) VALUES
(1, '2 place de l''étoile', '75000', 'Paris', 'France'),
(2, '33 rue Justin', '33700', 'Merignac', 'France'),
(3, '654bis avenue des âmes', '99999', 'Paradis', 'God''s home'),
(4, '32 chemin des dames', '97586', 'Saint-Anne', 'France'),
(10, '2 place de etoile', '75000', 'Paris', 'France'),
(11, '33 rue justin', '33700', 'Merignac', 'France'),
(13, '654bis avenue des ames', '99999', 'Paradis', 'France'),
(14, '25 chemin des dames', '97227', 'Sainte-anne', 'France'),
(21, '25 chemin des quelquechose', '33140', 'Sainte-ange', 'France'),
(22, '45 route des mec', '88554', 'Sainte-anne', 'France'),
(30, '20 avenue Albert Einstein', '69600', 'Villeurbanne', 'France'),
(31, '13 rue des Soprasiens', '66666', 'VilleFormation', 'France'),
(40, '35 rue de Vignoux', '63730', 'Mirefleurs', 'France'),
(41, '12 rue de Privat', '33000', 'Bordeaux', 'France'),
(42, '15 rue de Betman', '33700', 'Mérignac', 'France'),
(43, '125 avenue de la mort', '78520', 'Hell', 'France'),
(44, '1488 rue Henry Roux', '12545', 'Nebousa', 'France'),
(50, '5 rue client 5', '55555', 'Paris', 'France'),
(51, '5 rue passager 5', '55555', 'Paris', 'France'),
(52, '6 rue passager 6', '66666', 'Paris', 'France');

--
-- Contenu de la table `aeroport`
--

INSERT INTO `aeroport` (`idAero`, `nom`) VALUES
(1, 'Charles de Gaulle'),
(2, 'Saint-Exupéry'),
(3, 'Mérignac'),
(4, 'Toulouse-Blagnac'),
(5, 'Marignane'),
(6, 'Genève'),
(7, 'Orly'),
(8, 'Notre-Dame-des-Landes');

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`idClient`, `nom`, `prenom`, `numTel`, `numFax`, `eMail`, `siret`, `idAdd`, `idLog`) VALUES
(10, 'DUPONT', 'Junior', '0124573560', '0124573570', 'j.dupont@gmail.com', NULL, 3, 1),
(21, 'Bambelle', 'Larry', '0556987452', '0556877412', 'larrybambelle@gmail.com', NULL, 21, 2),
(30, 'Sopra Steria', NULL, '+33669807145', '0969807145', 'contact@soprasteria', 32682006500083, 31, NULL),
(40, 'Roux', 'Henry', '0665550789', '0665550788', 'roux.henry@hotmail.com', NULL, 43, NULL),
(50, 'client5', 'cinq', '5555555555', '02147483647', 'clientCinq@gmail.com', NULL, 50, NULL);

--
-- Contenu de la table `compagnie_aerienne`
--

INSERT INTO `compagnie_aerienne` (`id`, `nom`) VALUES
(1, 'Air France'),
(2, 'Easy Jet'),
(3, 'Hop!');

--
-- Contenu de la table `compagnie_aerienne_vol`
--

INSERT INTO `compagnie_aerienne_vol` (`id`, `numero`, `idCompagnie`, `idVol`, `ouvert`) VALUES
(1, 'AF123456', 1, 1, 1),
(2, 'AF654987', 1, 21, 0),
(3, 'EJ123456', 2, 30, 1),
(4, 'AF245789', 1, 30, 1),
(5, 'AF444444', 1, 40, 1),
(6, 'EJ777777', 2, 51, 0),
(7, 'EJ556544', 2, 52, 0);

--
-- Contenu de la table `escale`
--

INSERT INTO `escale` (`idEscale`, `dateDepart`, `dateArrivee`, `heureDepart`, `heureArrivee`, `idAeroport`, `idVol`) VALUES
(30, '2016-08-25', '2016-08-24', '01:13:00', '22:00:00', 4, 30),
(31, '2016-08-24', '2016-08-24', '16:30:00', '18:00:00', 2, 30),
(40, '2017-01-18', '2017-01-18', '20:30:41', '20:45:48', 2, 40),
(52, '2016-09-21', '2016-08-20', '10:50:00', '15:50:00', 2, 52);

--
-- Contenu de la table `login`
--

INSERT INTO `login` (`id`, `login`, `motDePasse`, `admin`) VALUES
(1, 'j.dupont@gmail.com', 'lolilol', 0),
(2, 'larrybambelle@gmail.com', 'zzzZZZ', 1);

--
-- Contenu de la table `passager`
--

INSERT INTO `passager` (`idPassager`, `nom`, `prenom`, `idAdd`) VALUES
(1, 'Martin', 'Jacques', 1),
(22, 'Simpson', 'Omer ', 22),
(30, 'Plantefève', 'Romain', 30),
(40, 'Hollande', 'François', 41),
(51, 'Tamarre', 'Martin', 51),
(52, 'Quet', 'Roméro', 52);

--
-- Contenu de la table `reservation`
--

INSERT INTO `reservation` (`idResa`, `dateReservation`, `numero`, `etat`, `idVol`, `idPassager`, `idClient`) VALUES
(10, '2016-06-15', '159753A', 'confirmee', 1, 1, 10),
(21, '2015-06-08', '124578Q', 'confirmee', 21, 22, 21),
(30, '2016-07-02', '12345A', 'confirmee', 30, 30, 30),
(40, '2016-05-20', '123456S', 'confirmee', 40, 40, 40),
(51, '2016-07-11', '1503AAA', 'confirmee', 51, 51, 50),
(52, '2016-07-11', '1505555Z', 'confirmee', 52, 52, 50);

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`id`, `nom`) VALUES
(1, 'Paris'),
(2, 'Lyon'),
(3, 'Mérignac'),
(4, 'Toulouse'),
(5, 'Marseille'),
(6, 'Genève'),
(7, 'Nantes');

--
-- Contenu de la table `ville_aeroport`
--

INSERT INTO `ville_aeroport` (`id`, `idVille`, `idAeroport`) VALUES
(1, 1, 1),
(2, 1, 7),
(3, 2, 2),
(4, 3, 3),
(5, 4, 4),
(6, 5, 5),
(7, 6, 6),
(8, 7, 8);

--
-- Contenu de la table `vol`
--

INSERT INTO `vol` (`idVol`, `dateDepart`, `dateArrivee`, `heureDepart`, `heureArrivee`, `idAeroportDepart`, `idAeroportArrivee`) VALUES
(1, '2016-07-25', '2016-07-25', '08:13:00', '10:12:00', 2, 1),
(21, '2015-08-08', '2015-08-09', '15:25:54', '15:24:53', 1, 2),
(30, '2016-08-24', '2016-08-25', '15:45:00', '18:15:00', 1, 3),
(40, '2017-01-18', '2017-01-18', '20:00:45', '21:01:54', 1, 4),
(51, '2016-09-23', '2016-09-23', '10:50:00', '18:50:00', 1, 3),
(52, '2016-08-20', '2016-09-21', '10:50:00', '18:50:00', 4, 8);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
