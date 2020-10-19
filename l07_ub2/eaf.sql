-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: mysql:3306
-- Erstellungszeit: 15. Okt 2020 um 05:28
-- Server-Version: 8.0.21
-- PHP-Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `eaf`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `movies`
--

CREATE TABLE `movies` (
  `movie_id` bigint NOT NULL,
  `movie_releasedate` date DEFAULT NULL,
  `movie_rented` bit(1) DEFAULT NULL,
  `movie_title` varchar(255) DEFAULT NULL,
  `pricecategory_fk` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `movies`
--

INSERT INTO `movies` (`movie_id`, `movie_releasedate`, `movie_rented`, `movie_title`, `pricecategory_fk`) VALUES
(1, '2017-05-11', b'1', 'Marie Curie', 1),
(2, '2017-07-20', b'1', 'Curchill', 1),
(3, '2017-08-18', b'1', 'The Boss Baby', 2),
(4, '2017-08-31', b'0', 'Pirates of the Caribean: Salazar\'s Revenge', 1),
(5, '2017-09-07', b'0', 'Die göttliche Ordnung', 1),
(6, '2018-05-25', b'0', 'Loving Vincent', 1),
(7, '2018-08-13', b'0', 'Fast & Furious 7', 1),
(8, '2018-10-01', b'0', 'Momo', 2),
(9, '2018-10-03', b'0', 'Swimming with Men', 1),
(10, '2018-10-22', b'0', 'Jurassic World', 2),
(11, '2019-02-28', b'0', 'Bohemian Rhapsody', 1),
(12, '2019-03-13', b'0', 'Wolkenbruch', 1),
(13, '2019-09-04', b'0', 'The old Man and the Gun', 1),
(14, '2019-09-20', b'0', 'Stan and Ollie', 2),
(15, '2019-09-25', b'0', 'Zwingli', 1),
(16, '2020-01-30', b'0', 'Downtown Abbey', 1),
(17, '2020-03-12', b'0', 'Gut geten Nordwind', 3),
(18, '2020-05-01', b'0', 'Moskau einfach!', 3),
(19, '2020-07-09', b'0', 'The Invisible Man', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pricecategories`
--

CREATE TABLE `pricecategories` (
  `pricecategory_type` varchar(31) NOT NULL,
  `pricecategory_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `pricecategories`
--

INSERT INTO `pricecategories` (`pricecategory_type`, `pricecategory_id`) VALUES
('Regular', 1),
('Children', 2),
('NewRelease', 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rentals`
--

CREATE TABLE `rentals` (
  `rental_id` bigint NOT NULL,
  `rental_rentaldate` date DEFAULT NULL,
  `rental_rentaldays` int DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `rentals`
--

INSERT INTO `rentals` (`rental_id`, `rental_rentaldate`, `rental_rentaldays`, `movie_id`, `user_id`) VALUES
(1, '2019-09-28', 14, 1, 1),
(2, '2019-09-30', 14, 2, 1),
(3, '2019-09-25', 14, 3, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `user_id` bigint NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_firstname` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`user_id`, `user_email`, `user_firstname`, `user_name`) VALUES
(1, 'marc.keller@gmail.com', 'Marc', 'Keller'),
(2, 'werner.knecht@gmail.com', 'Werner', 'Knecht'),
(3, 'barbara.meyer@gmail.com', 'Barbara', 'Meyer'),
(4, 'adolf.kummer@gmail.com', 'Adolf', 'Kummer');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`movie_id`);

--
-- Indizes für die Tabelle `pricecategories`
--
ALTER TABLE `pricecategories`
  ADD PRIMARY KEY (`pricecategory_id`);

--
-- Indizes für die Tabelle `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`rental_id`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `movies`
--
ALTER TABLE `movies`
  MODIFY `movie_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT für Tabelle `pricecategories`
--
ALTER TABLE `pricecategories`
  MODIFY `pricecategory_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `rentals`
--
ALTER TABLE `rentals`
  MODIFY `rental_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
