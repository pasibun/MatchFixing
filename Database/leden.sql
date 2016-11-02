-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Gegenereerd op: 10 okt 2016 om 14:49
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
-- Tabelstructuur voor tabel `leden`
--

CREATE TABLE `leden` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `kaliber` varchar(20) NOT NULL,
  `gender` enum('male','female') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden geëxporteerd voor tabel `leden`
--

INSERT INTO `leden` (`id`, `name`, `password`, `age`, `kaliber`, `gender`) VALUES
(1, '', '', 0, '', ''),
(2, 'yannick', 'cincla', 20, 'goed', 'male'),
(3, 'yannick', 'cincla', 20, 'goed', 'male'),
(4, 'yannick', 'cincla', 20, 'goed', 'male'),
(5, 'yannick', 'cincla', 25, 'slecht', 'male'),
(6, 'yannick', 'cincla', 25, 'slecht', 'male'),
(7, 'yannick', 'cincla', 25, 'slecht', 'male'),
(8, 'yannick', 'cincla', 255, 'slecht', 'male'),
(9, '', '', 0, '', 'male'),
(10, '', '', 0, '', 'male'),
(11, '', '', 0, '', 'male'),
(12, '', '', 0, '', 'male'),
(13, '', '', 0, '', 'male'),
(14, '', '', 0, '', 'male'),
(15, '', '', 0, '', 'male'),
(16, '', '', 0, '', 'male'),
(17, '', '', 0, '', 'male'),
(18, '', '', 0, '', 'male'),
(19, '', '', 0, '', 'male'),
(20, '', '', 0, '', 'male'),
(21, '', '', 0, '', 'male'),
(22, '', '', 0, '', 'male'),
(23, '', '', 0, '', 'male'),
(24, '', '', 0, '', 'male'),
(25, '', '', 0, '', 'male'),
(26, '', '', 0, '', 'male'),
(27, '', '', 0, '', 'male'),
(28, '', '', 0, '', 'male'),
(29, '', '', 0, '', 'male'),
(30, '', '', 0, '', 'male'),
(31, '', '', 0, '', 'male'),
(32, '', '', 0, '', 'male'),
(33, '', '', 0, '', 'male'),
(34, '', '', 0, '', 'male'),
(35, '', '', 0, '', 'male'),
(36, '', '', 0, '', 'male'),
(37, '', '', 0, '', 'male'),
(38, '', '', 0, '', 'male'),
(39, '', '', 0, '', 'male'),
(40, '', '', 0, '', 'male'),
(41, '', '', 0, '', 'male'),
(42, '', '', 0, '', 'male'),
(43, '', '', 0, '', 'male'),
(44, '', '', 0, '', 'male'),
(45, '', '', 0, '', 'male'),
(46, '', '', 0, '', 'male'),
(47, '', '', 0, '', 'male'),
(48, '', '', 0, '', 'male'),
(49, '', '', 0, '', 'male'),
(50, '', '', 0, '', 'male'),
(51, '', '', 0, '', 'male'),
(52, 'lekker', '', 0, '', 'male'),
(53, 'lekker', '', 0, '', 'male'),
(54, 'lekker', '', 0, '', 'male'),
(55, 'lekker', '', 0, '', 'male'),
(56, 'lekker', '', 0, '', 'male'),
(57, 'lekker', '', 0, '', 'male'),
(58, 'lekker', '', 0, '', 'male'),
(59, 'lekker', '', 0, '', 'male'),
(60, 'lekker', '', 0, '', 'male'),
(61, 'lekker', '', 0, '', 'male'),
(62, 'lekker', '', 0, '', 'male'),
(63, 'lekker', '', 0, '', 'male'),
(64, 'lekker', '', 0, '', 'male'),
(65, 'lekker', '', 0, '', 'male'),
(66, 'lekker', '', 0, '', 'male'),
(67, 'lekker', '', 0, '', 'male'),
(68, '', '', 0, '', 'male'),
(69, '', '', 0, '', 'male'),
(70, '', '', 0, '', 'male'),
(71, '', '', 0, '', 'male'),
(72, '', '', 0, '', 'male'),
(73, '', '', 0, '', 'male'),
(74, '', '', 0, '', 'male'),
(75, 'yab', 'h', 55, '', 'male'),
(76, 'Yannick', 'hallo', 20, '', 'male'),
(77, 'Yannick', 'hallo', 25, '', 'male'),
(78, 'Matthijs', 'hallo', 23, '', 'male'),
(79, 'YannickStrobl', 'testpass', 20, 'ubergoed', 'male');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `leden`
--
ALTER TABLE `leden`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `leden`
--
ALTER TABLE `leden`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
