--
-- Index pour les tables exportées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`idAdd`);

--
-- Index pour la table `aeroport`
--
ALTER TABLE `aeroport`
  ADD PRIMARY KEY (`idAero`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD KEY `idAdd` (`idAdd`),
  ADD KEY `idLog` (`idLog`);

--
-- Index pour la table `compagnie_aerienne`
--
ALTER TABLE `compagnie_aerienne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compagnie_aerienne_vol`
--
ALTER TABLE `compagnie_aerienne_vol`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCompagnie` (`idCompagnie`),
  ADD KEY `idVol` (`idVol`);

--
-- Index pour la table `escale`
--
ALTER TABLE `escale`
  ADD PRIMARY KEY (`idEscale`),
  ADD KEY `idAeroport` (`idAeroport`),
  ADD KEY `idVol` (`idVol`);

--
-- Index pour la table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `passager`
--
ALTER TABLE `passager`
  ADD PRIMARY KEY (`idPassager`),
  ADD KEY `idAdd` (`idAdd`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`idResa`),
  ADD KEY `idPassager` (`idPassager`),
  ADD KEY `idClient` (`idClient`),
  ADD KEY `idVol` (`idVol`);

--
-- Index pour la table `ville`
--
ALTER TABLE `ville`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ville_aeroport`
--
ALTER TABLE `ville_aeroport`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idVille` (`idVille`),
  ADD KEY `idAeroport` (`idAeroport`);

--
-- Index pour la table `vol`
--
ALTER TABLE `vol`
  ADD PRIMARY KEY (`idVol`),
  ADD KEY `idAeroportDepart` (`idAeroportDepart`),
  ADD KEY `idAeroportArrivee` (`idAeroportArrivee`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `idAdd` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `aeroport`
--
ALTER TABLE `aeroport`
  MODIFY `idAero` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` bigint(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT pour la table `compagnie_aerienne`
--
ALTER TABLE `compagnie_aerienne`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `compagnie_aerienne_vol`
--
ALTER TABLE `compagnie_aerienne_vol`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `escale`
--
ALTER TABLE `escale`
  MODIFY `idEscale` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `login`
--
ALTER TABLE `login`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `passager`
--
ALTER TABLE `passager`
  MODIFY `idPassager` bigint(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `idResa` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `ville`
--
ALTER TABLE `ville`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `ville_aeroport`
--
ALTER TABLE `ville_aeroport`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `vol`
--
ALTER TABLE `vol`
  MODIFY `idVol` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`idAdd`) REFERENCES `adresse` (`idAdd`),
  ADD CONSTRAINT `client_ibfk_2` FOREIGN KEY (`idLog`) REFERENCES `login` (`id`);

--
-- Contraintes pour la table `compagnie_aerienne_vol`
--
ALTER TABLE `compagnie_aerienne_vol`
  ADD CONSTRAINT `compagnie_aerienne_vol_ibfk_1` FOREIGN KEY (`idCompagnie`) REFERENCES `compagnie_aerienne` (`id`),
  ADD CONSTRAINT `compagnie_aerienne_vol_ibfk_2` FOREIGN KEY (`idVol`) REFERENCES `vol` (`idVol`);

--
-- Contraintes pour la table `escale`
--
ALTER TABLE `escale`
  ADD CONSTRAINT `escale_ibfk_1` FOREIGN KEY (`idAeroport`) REFERENCES `aeroport` (`idAero`),
  ADD CONSTRAINT `escale_ibfk_2` FOREIGN KEY (`idVol`) REFERENCES `vol` (`idVol`);

--
-- Contraintes pour la table `passager`
--
ALTER TABLE `passager`
  ADD CONSTRAINT `passager_ibfk_1` FOREIGN KEY (`idAdd`) REFERENCES `adresse` (`idAdd`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`idPassager`) REFERENCES `passager` (`idPassager`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`idVol`) REFERENCES `vol` (`idVol`);

--
-- Contraintes pour la table `ville_aeroport`
--
ALTER TABLE `ville_aeroport`
  ADD CONSTRAINT `ville_aeroport_ibfk_1` FOREIGN KEY (`idVille`) REFERENCES `ville` (`id`),
  ADD CONSTRAINT `ville_aeroport_ibfk_2` FOREIGN KEY (`idAeroport`) REFERENCES `aeroport` (`idAero`);

--
-- Contraintes pour la table `vol`
--
ALTER TABLE `vol`
  ADD CONSTRAINT `vol_ibfk_1` FOREIGN KEY (`idAeroportDepart`) REFERENCES `aeroport` (`idAero`),
  ADD CONSTRAINT `vol_ibfk_2` FOREIGN KEY (`idAeroportArrivee`) REFERENCES `aeroport` (`idAero`);