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
-- Tabelstructuur voor tabel `groups`
--

CREATE TABLE `groups` (
  `GroupID` int(11) NOT NULL,
  `Member1` varchar(50) NOT NULL,
  `Member2` varchar(50) DEFAULT NULL,
  `Member3` varchar(50) DEFAULT NULL,
  `Member4` varchar(50) DEFAULT NULL,
  `Member5` varchar(50) DEFAULT NULL,
  `Member6` varchar(50) DEFAULT NULL,
  `Member7` varchar(50) DEFAULT NULL,
  `Member8` varchar(50) DEFAULT NULL,
  `Member9` varchar(50) DEFAULT NULL,
  `Member10` varchar(50) DEFAULT NULL,
  `Member11` varchar(50) DEFAULT NULL,
  `Member12` varchar(50) DEFAULT NULL,
  `Member13` varchar(50) DEFAULT NULL,
  `Member14` varchar(50) DEFAULT NULL,
  `Member15` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`GroupID`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `groups`
--
ALTER TABLE `groups`
  MODIFY `GroupID` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
