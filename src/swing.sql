-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2019 at 04:58 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


--
-- Database: `swing`
--

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `faculty` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `faculty`, `address`, `email`) VALUES
                                                                           (1, 'Saurav Bhandari', 'Computer Science', 'Baneshwor', 'saurav.bhandari@yahoo.com'),
                                                                           (2, 'Ramesh', 'Nepali', 'Baneshwor', 'Ramesh@yahoo.com'),
                                                                           (3, 'Saras', 'Science', 'Japan', 'Saras@yahoo.com'),
                                                                           (4, 'Sabina', 'Nepali', 'China', 'Sabina@yahoo.com'),
                                                                           (5, 'Ayush', 'Science', 'USA', 'Ayush@yahoo.com'),
                                                                           (6, 'bist', 'English', 'UK', 'bist@yahoo.com'),
                                                                           (7, 'kusal', 'Japanese', 'Germany', 'kusal@yahoo.com'),
                                                                           (8, 'shahil', 'Nepali', 'Baneshwor', 'shahil@yahoo.com'),
                                                                           (9, 'raju', 'Nepali', 'Japan', 'raju@yahoo.com'),
                                                                           (10, 'Dipesh', 'Science', 'Dipesh', 'dipesh@gmail.com'),
                                                                           (11, 'Prajwal', 'Nepali', 'Prajwal', 'saurav.bhandari@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `contact` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `address`, `contact`) VALUES
                                                                               (1, 'admin', 'admin', 'Nepal', '9843500114');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
