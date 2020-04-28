-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2020 at 12:18 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `persewaan_buku`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `data_buku` (IN `judul` VARCHAR(100), IN `buku_fav` VARCHAR(100), IN `peng` VARCHAR(100), IN `pen` VARCHAR(100), IN `kt_buku` VARCHAR(100), IN `status` VARCHAR(100))  BEGIN
INSERT INTO buku VALUES(null, judul, buku_fav, peng, pen, kt_buku, status);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `kode_buku` int(20) NOT NULL,
  `judul_buku` varchar(100) NOT NULL,
  `buku_favorit` varchar(100) NOT NULL,
  `pengarang` varchar(100) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `kategori_buku` varchar(100) NOT NULL,
  `status_buku` enum('Tersedia','Dipinjam') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`kode_buku`, `judul_buku`, `buku_favorit`, `pengarang`, `penerbit`, `kategori_buku`, `status_buku`) VALUES
(1, 'Putri Salju', 'Cinderella', 'Citra', 'Gramedia', 'Anak-anak', 'Tersedia'),
(2, 'Matematika', 'Fisika', 'Ahmad', 'Gramedia', 'Pendidikan', 'Tersedia');

--
-- Triggers `buku`
--
DELIMITER $$
CREATE TRIGGER `buku_reg` AFTER INSERT ON `buku` FOR EACH ROW BEGIN
INSERT INTO buku_log VALUES (null, new.judul_buku, now());
INSERT INTO buku_activity VALUES (null, new.judul_buku, "INS", now());
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_buku_reg` AFTER UPDATE ON `buku` FOR EACH ROW BEGIN
INSERT INTO buku_log VALUES (null, new.judul_buku, now());
INSERT INTO buku_activity VALUES (null, new.judul_buku, concat("UPD from", old.judul_buku), now());
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `buku_activity`
--

CREATE TABLE `buku_activity` (
  `act_id` int(11) NOT NULL,
  `judul_buku` varchar(255) DEFAULT NULL,
  `action` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buku_activity`
--

INSERT INTO `buku_activity` (`act_id`, `judul_buku`, `action`, `time`) VALUES
(1, 'Matematika', 'INS', '2020-03-10 18:17:20');

-- --------------------------------------------------------

--
-- Table structure for table `buku_log`
--

CREATE TABLE `buku_log` (
  `log_id` int(11) NOT NULL,
  `judul_buku` varchar(255) DEFAULT NULL,
  `added_on` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buku_log`
--

INSERT INTO `buku_log` (`log_id`, `judul_buku`, `added_on`) VALUES
(1, 'Matematika', '2020-03-10 18:17:20');

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_identitas` varchar(255) NOT NULL,
  `kode_buku` int(255) NOT NULL,
  `judul_buku` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `tgl_peminjaman` date NOT NULL,
  `tgl_kembali` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tabel_peminjaman`
--

CREATE TABLE `tabel_peminjaman` (
  `id_peminjaman` varchar(255) NOT NULL,
  `id_penyewa` varchar(255) NOT NULL,
  `nama_penyewa` varchar(255) NOT NULL,
  `kode_buku` int(255) NOT NULL,
  `tgl_sewa` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `denda` varchar(255) NOT NULL,
  `status_kembali` enum('kembali','-') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`kode_buku`);

--
-- Indexes for table `buku_activity`
--
ALTER TABLE `buku_activity`
  ADD PRIMARY KEY (`act_id`);

--
-- Indexes for table `buku_log`
--
ALTER TABLE `buku_log`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`kode_buku`),
  ADD KEY `kode_buku` (`kode_buku`);

--
-- Indexes for table `tabel_peminjaman`
--
ALTER TABLE `tabel_peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `kode_buku` (`kode_buku`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `kode_buku` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `buku_activity`
--
ALTER TABLE `buku_activity`
  MODIFY `act_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `buku_log`
--
ALTER TABLE `buku_log`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`kode_buku`) REFERENCES `buku` (`kode_buku`);

--
-- Constraints for table `tabel_peminjaman`
--
ALTER TABLE `tabel_peminjaman`
  ADD CONSTRAINT `tabel_peminjaman_ibfk_1` FOREIGN KEY (`kode_buku`) REFERENCES `peminjaman` (`kode_buku`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
