-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2017 at 09:37 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `miripchef`
--

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

CREATE TABLE `resep` (
  `id_resep` int(20) NOT NULL,
  `nama_resep` text NOT NULL,
  `gambar_resep` text NOT NULL,
  `deskripsi_resep` text NOT NULL,
  `bahan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`id_resep`, `nama_resep`, `gambar_resep`, `deskripsi_resep`, `bahan`) VALUES
(1, 'CAPCAY BAKSO', 'http://192.168.43.250/miripchef/image/capcaybakso.jpg', 'https://cookpad.com/id/resep/1439615-capcay-goreng-bakso', '5 buah bakso, diiris sesuai selera\r\n1 ikat sawi hijau yang sudah dipotong\r\n'),
(2, 'SATE TAI', 'http://192.168.43.250/miripchef/image/sate_tai.jpg', 'https://cookpad.com/id/resep/2657322-sate-taichan', ''),
(3, 'Kepiting Saos Telur Asin', 'http://192.168.43.250/miripchef/image/kepiting.jpg', 'https://cookpad.com/id/resep/2644267-kepiting-saos-telur-asin', ''),
(4, 'Iga Bakar Rempah Madu', 'http://192.168.43.250/miripchef/image/igabakar.jpg', 'https://cookpad.com/id/resep/2488554-iga-bakar-rempah-madu', ''),
(5, 'Pizza Cheesy Bites', 'http://192.168.43.250/miripchef/image/pizza.jpg', 'https://cookpad.com/id/resep/2653206-pizza-cheesy-bites', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `resep`
--
ALTER TABLE `resep`
  ADD PRIMARY KEY (`id_resep`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
