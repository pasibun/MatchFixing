-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Gegenereerd op: 11 jan 2017 om 11:51
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
  `MatchDegree` enum('Friendly','Competitive') NOT NULL,
  `playerRankMin` float NOT NULL,
  `playerRankMax` float NOT NULL,
  `Description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `matches`
--

INSERT INTO `matches` (`MatchID`, `matchDate`, `matchTime`, `MatchType`, `GroupID`, `UserID`, `MatchDegree`, `playerRankMin`, `playerRankMax`, `Description`) VALUES
(1, '2016-10-28', '16:30:00', 'Double', 0, 0, '', 0, 0, ''),
(2, '2016-10-28', '15:00:00', 'Single', 0, 0, '', 0, 0, ''),
(3, '2016-10-28', '15:25:00', 'Tournament', 0, 0, '', 0, 0, ''),
(4, '2016-10-28', '19:30:00', 'Double', 0, 0, '', 0, 0, ''),
(5, '2016-10-28', '18:30:00', 'Single', 0, 0, '', 0, 0, ''),
(8, '2016-11-10', '15:00:00', 'Double', 0, 0, '', 0, 0, ''),
(9, '2016-11-10', '15:00:00', 'Double', 0, 0, '', 0, 0, ''),
(10, '2016-11-10', '14:00:00', 'Double', 0, 0, '', 0, 0, ''),
(11, '2016-11-10', '14:00:00', 'Double', 0, 0, '', 0, 0, ''),
(12, '2016-11-10', '14:00:00', 'Double', 0, 0, '', 0, 0, ''),
(13, '2016-11-10', '14:00:00', 'Double', 0, 0, '', 0, 0, ''),
(14, '2016-11-10', '14:00:00', 'Double', 0, 0, '', 0, 0, ''),
(15, '0000-00-00', '00:00:00', 'Double', 0, 0, '', 0, 0, ''),
(16, '0000-00-00', '00:00:00', 'Double', 0, 0, '', 0, 0, ''),
(17, '0000-00-00', '00:00:00', 'Double', 0, 0, '', 0, 0, ''),
(18, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(19, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(20, '2016-11-05', '10:30:00', 'Single', 0, 0, '', 0, 0, ''),
(21, '2016-11-16', '19:45:00', 'Single', 0, 0, '', 0, 0, ''),
(22, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(23, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(24, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(25, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(26, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(27, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(28, '0000-00-00', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(29, '2016-11-09', '21:00:00', 'Tournament', 0, 0, '', 0, 0, ''),
(30, '2016-11-11', '16:35:00', 'Double', 0, 0, '', 0, 0, ''),
(31, '2016-11-11', '17:10:00', 'Double', 0, 0, '', 0, 0, ''),
(32, '2016-11-09', '13:00:00', 'Single', 0, 123, '', 0, 0, ''),
(33, '2016-11-09', '13:00:00', 'Single', 0, 123, '', 0, 0, ''),
(34, '2016-11-09', '13:00:00', 'Single', 0, 123, '', 0, 0, ''),
(35, '2016-11-09', '13:00:00', 'Single', 0, 0, '', 0, 0, ''),
(36, '2016-11-09', '01:00:00', 'Single', 0, 0, '', 0, 0, ''),
(37, '2016-11-10', '13:00:00', 'Single', 0, 0, '', 0, 0, ''),
(38, '2016-11-10', '00:00:00', 'Single', 0, 0, '', 0, 0, ''),
(39, '2016-11-09', '02:00:00', 'Double', 0, 0, '', 0, 0, ''),
(40, '2016-11-09', '13:00:00', 'Double', 0, 0, '', 0, 0, ''),
(41, '2016-11-09', '13:00:00', 'Double', 0, 0, '', 0, 0, ''),
(42, '2016-11-09', '19:25:00', 'Double', 0, 123, '', 0, 0, ''),
(43, '2016-11-11', '13:00:00', 'Double', 0, 0, 'Friendly', 0, 0, ''),
(44, '0000-00-00', '00:00:00', '', 0, 0, 'Friendly', 0, 0, ''),
(45, '0000-00-00', '00:00:00', '', 0, 0, 'Friendly', 0, 0, ''),
(46, '0000-00-00', '00:00:00', '', 0, 0, 'Friendly', 0, 0, ''),
(47, '0000-00-00', '00:00:00', '', 0, 0, 'Friendly', 0, 0, ''),
(48, '0000-00-00', '00:00:00', '', 0, 0, 'Friendly', 7.4, 8, 'HHHH'),
(49, '0000-00-00', '00:00:00', '', 123, 12, 'Friendly', 7.4, 8, 'HHHH'),
(50, '2016-11-11', '14:50:00', 'Double', 123, 123, '', 0, 0, ''),
(51, '2016-11-11', '00:00:00', 'Single', 123, 123, 'Competitive', 6.3, 7.6, 'h'),
(52, '2016-11-11', '03:00:00', 'Single', 123, 123, 'Competitive', 4.8, 7.6, 'h'),
(53, '2016-11-11', '03:00:00', 'Single', 123, 123, 'Competitive', 4.8, 8.5, 'h'),
(54, '2016-11-16', '18:05:00', 'Single', 123, 123, 'Competitive', 8, 9.5, 'Ik wil godverdomme bowlen'),
(55, '0000-00-00', '00:00:00', '', 0, 0, '', 0, 0, '');

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
  MODIFY `MatchID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
