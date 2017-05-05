--
-- Base de données :  `agence`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `idAdd` int(15) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `codePostal` char(5) NOT NULL,
  `ville` varchar(163) NOT NULL,
  `pays` varchar(31) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `aeroport`
--

CREATE TABLE `aeroport` (
  `idAero` bigint(20) NOT NULL,
  `nom` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` bigint(15) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) DEFAULT NULL,
  `numTel` varchar(14) NOT NULL,
  `numFax` varchar(14) NOT NULL,
  `eMail` varchar(40) NOT NULL,
  `siret` bigint(20) DEFAULT NULL,
  `idAdd` int(15) DEFAULT NULL,
  `idLog` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compagnie_aerienne`
--

CREATE TABLE `compagnie_aerienne` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compagnie_aerienne_vol`
--

CREATE TABLE `compagnie_aerienne_vol` (
  `id` bigint(20) NOT NULL,
  `numero` varchar(15) NOT NULL,
  `idCompagnie` bigint(20) DEFAULT NULL,
  `idVol` bigint(20) DEFAULT NULL,
  `ouvert` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `escale`
--

CREATE TABLE `escale` (
  `idEscale` bigint(20) NOT NULL,
  `dateDepart` date NOT NULL,
  `dateArrivee` date NOT NULL,
  `heureDepart` time NOT NULL,
  `heureArrivee` time NOT NULL,
  `idAeroport` bigint(20) DEFAULT NULL,
  `idVol` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

CREATE TABLE `login` (
  `id` bigint(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `motDePasse` varchar(100) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `passager`
--

CREATE TABLE `passager` (
  `idPassager` bigint(15) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `idAdd` int(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `idResa` bigint(20) NOT NULL,
  `dateReservation` date NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `etat` enum('annulee','confirmee') DEFAULT NULL,
  `idVol` bigint(20) DEFAULT NULL,
  `idPassager` bigint(20) DEFAULT NULL,
  `idClient` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE `ville` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ville_aeroport`
--

CREATE TABLE `ville_aeroport` (
  `id` bigint(20) NOT NULL,
  `idVille` bigint(20) DEFAULT NULL,
  `idAeroport` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `vol`
--

CREATE TABLE `vol` (
  `idVol` bigint(20) NOT NULL,
  `dateDepart` date NOT NULL,
  `dateArrivee` date NOT NULL,
  `heureDepart` time NOT NULL,
  `heureArrivee` time NOT NULL,
  `idAeroportDepart` bigint(20) DEFAULT NULL,
  `idAeroportArrivee` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;