-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Gegenereerd op: 07 nov 2016 om 12:34
-- Serverversie: 10.1.16-MariaDB
-- PHP-versie: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `androidtest`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `matches`
--

CREATE TABLE `matches` (
  `MatchID` int(11) NOT NULL,
  `matchDate` date DEFAULT NULL,
  `matchTime` time DEFAULT NULL,
  `MatchType` enum('Single','Double','Tournament','') DEFAULT NULL,
  `GroupID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `MatchDegree` enum('fun','competition') NOT NULL,
  `playerRankMin` int(11) NOT NULL,
  `playerRankMax` int(11) NOT NULL,
  `Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `matches`
--

INSERT INTO `matches` (`MatchID`, `matchDate`, `matchTime`, `MatchType`, `GroupID`, `UserID`, `MatchDegree`, `playerRankMin`, `playerRankMax`, `Description`) VALUES
(1, '2016-10-28', '16:30:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(2, '2016-10-28', '15:00:00', 'Single', 0, 0, 'fun', 0, 0, ''),
(3, '2016-10-28', '15:25:00', 'Tournament', 0, 0, 'fun', 0, 0, ''),
(4, '2016-10-28', '19:30:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(5, '2016-10-28', '18:30:00', 'Single', 0, 0, 'fun', 0, 0, ''),
(8, '2016-11-10', '15:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(9, '2016-11-10', '15:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(10, '2016-11-10', '14:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(11, '2016-11-10', '14:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(12, '2016-11-10', '14:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(13, '2016-11-10', '14:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(14, '2016-11-10', '14:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(15, '0000-00-00', '00:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(16, '0000-00-00', '00:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(17, '0000-00-00', '00:00:00', 'Double', 0, 0, 'fun', 0, 0, ''),
(18, '0000-00-00', '00:00:00', 'Single', 0, 0, 'fun', 0, 0, ''),
(19, '0000-00-00', '00:00:00', 'Single', 0, 0, 'fun', 0, 0, ''),
(20, '2016-11-05', '10:30:00', 'Single', 0, 0, 'fun', 0, 0, ''),
(21, '2016-11-16', '19:45:00', 'Single', 0, 0, 'fun', 0, 0, '');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`MatchID`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `matches`
--
ALTER TABLE `matches`
  MODIFY `MatchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
