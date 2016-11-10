-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2016 at 01:59 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

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
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `kaliber` varchar(20) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `email` text NOT NULL,
  `address` text NOT NULL,
  `city` varchar(50) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `mobilePhone` text NOT NULL,
  `phone` text NOT NULL,
  `scoreSingle` int(11) NOT NULL,
  `scoreDouble` int(11) NOT NULL,
  `invited` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `username`, `password`, `age`, `kaliber`, `gender`, `firstName`, `lastName`, `email`, `address`, `city`, `dateOfBirth`, `mobilePhone`, `phone`, `scoreSingle`, `scoreDouble`, `invited`) VALUES
(83, 'test', 'test', 73, 'sterk', 'male', '', '', '', '', '', '0000-00-00', '', '', 0, 0, 0),
(84, 'test2', 'test2', 73, 'slecht', 'male', '', '', '', '', '', '0000-00-00', '', '', 0, 0, 0),
(85, 'matthijs', 'aweds', 83, 'middelmatig', 'male', '', '', '', '', '', '0000-00-00', '', '', 0, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
