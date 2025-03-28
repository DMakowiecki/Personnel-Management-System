-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 21, 2024 at 09:03 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `grafik`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `authorities`
--

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `authority` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`id`, `username`, `authority`) VALUES
(1, 'admin', 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kadra`
--

CREATE TABLE `kadra` (
  `id` int(11) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kadra`
--



-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kadra_przedmioty`
--

CREATE TABLE `kadra_przedmioty` (
  `id` int(11) NOT NULL,
  `id_przedmiotu` int(11) NOT NULL,
  `id_prowadzacego` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kadra_przedmioty`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `kierunki_studiow`
--

CREATE TABLE `kierunki_studiow` (
  `id_kierunku` int(11) NOT NULL,
  `nazwa_kierunku` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kierunki_studiow`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `plan_studiow`
--

CREATE TABLE `plan_studiow` (
  `id_planu_studiow` int(11) NOT NULL,
  `id_roku` int(11) DEFAULT NULL,
  `id_kierunku` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `plan_studiow`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmioty`
--

CREATE TABLE `przedmioty` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `przedmioty`
--

INSERT INTO `przedmioty` (`id`, `nazwa`) VALUES
(2, 'Język angielski/niemiecki'),
(3, 'Język angielski/niemiecki specjalistyczny'),
(4, 'Wychowanie fizyczne'),
(5, 'Komunikacja interpersonalna'),
(6, 'Współczesne problemy społeczne'),
(7, 'Ochrona własności intelektualnej'),
(8, 'Kształtowanie kompetencji społecznych'),
(9, 'Filozofia'),
(10, 'Kultura bycia i języka'),
(12, 'Efektywne metody uczenia się'),
(13, 'Matematyka dyskretna'),
(14, 'Matematyka I'),
(15, 'Matematyka II'),
(16, 'Metody probabilistyczne i statystyka'),
(17, 'Fizyka'),
(18, 'Podstawy elektroniki i elektrotechniki'),
(19, 'Algorytmy i struktury danych'),
(21, 'Architektura systemów komputerowych'),
(22, 'Bazy danych I'),
(23, 'Bazy danych II'),
(24, 'Elementy logiki i arytmetyki komputera'),
(25, 'Grafika komputerowa'),
(26, 'Interakcja człowiek-komputer'),
(29, 'Podstawy programowania'),
(30, 'Programowanie obiektowe'),
(31, 'Sieci komputerowe'),
(32, 'Systemy operacyjne'),
(34, 'Współczesne języki programowania'),
(35, 'Warsztaty zawodowe');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przedmioty_typ`
--

CREATE TABLE `przedmioty_typ` (
  `id` int(11) NOT NULL,
  `id_przedmiotu` int(11) NOT NULL,
  `typ` enum('wyklady','cwiczenia','laboratoria','zajecia_praktyczne','lektorat','seminarium','praktyki_zawodowe','inne') NOT NULL,
  `liczba_godzin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rok_akademicki`
--

CREATE TABLE `rok_akademicki` (
  `id_roku` int(11) NOT NULL,
  `rok` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rok_akademicki`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `szczegoly_planu_studiow`
--

CREATE TABLE `szczegoly_planu_studiow` (
  `id_szczegolu` int(11) NOT NULL,
  `id_planu_studiow` int(11) DEFAULT NULL,
  `numer_semestru` int(11) DEFAULT NULL,
  `id_przedmiotu_typ` int(11) DEFAULT NULL,
  `rok_akademicki` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `szczegoly_planu_studiow`
--


-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`);

--
-- Indeksy dla tabeli `kadra`
--
ALTER TABLE `kadra`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `kadra_przedmioty`
--
ALTER TABLE `kadra_przedmioty`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_przedmiotu` (`id_przedmiotu`),
  ADD KEY `id_prowadzacego` (`id_prowadzacego`);

--
-- Indeksy dla tabeli `kierunki_studiow`
--
ALTER TABLE `kierunki_studiow`
  ADD PRIMARY KEY (`id_kierunku`);

--
-- Indeksy dla tabeli `plan_studiow`
--
ALTER TABLE `plan_studiow`
  ADD PRIMARY KEY (`id_planu_studiow`),
  ADD UNIQUE KEY `unique_rok_kierunek` (`id_roku`,`id_kierunku`),
  ADD KEY `id_roku` (`id_roku`),
  ADD KEY `id_kierunku` (`id_kierunku`);

--
-- Indeksy dla tabeli `przedmioty`
--
ALTER TABLE `przedmioty`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `przedmioty_typ`
--
ALTER TABLE `przedmioty_typ`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_przedmiotu` (`id_przedmiotu`);

--
-- Indeksy dla tabeli `rok_akademicki`
--
ALTER TABLE `rok_akademicki`
  ADD PRIMARY KEY (`id_roku`),
  ADD UNIQUE KEY `rok` (`rok`);

--
-- Indeksy dla tabeli `szczegoly_planu_studiow`
--
ALTER TABLE `szczegoly_planu_studiow`
  ADD PRIMARY KEY (`id_szczegolu`),
  ADD KEY `id_planu_studiow` (`id_planu_studiow`),
  ADD KEY `id_przedmiotu_typ` (`id_przedmiotu_typ`),
  ADD KEY `unique_plan_przedmiot_semestr` (`id_planu_studiow`,`id_przedmiotu_typ`,`numer_semestru`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `kadra`
--
ALTER TABLE `kadra`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `kadra_przedmioty`
--
ALTER TABLE `kadra_przedmioty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `kierunki_studiow`
--
ALTER TABLE `kierunki_studiow`
  MODIFY `id_kierunku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `plan_studiow`
--
ALTER TABLE `plan_studiow`
  MODIFY `id_planu_studiow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `przedmioty`
--
ALTER TABLE `przedmioty`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `przedmioty_typ`
--
ALTER TABLE `przedmioty_typ`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `rok_akademicki`
--
ALTER TABLE `rok_akademicki`
  MODIFY `id_roku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `szczegoly_planu_studiow`
--
ALTER TABLE `szczegoly_planu_studiow`
  MODIFY `id_szczegolu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kadra_przedmioty`
--
ALTER TABLE `kadra_przedmioty`
  ADD CONSTRAINT `kadra_przedmioty_ibfk_1` FOREIGN KEY (`id_prowadzacego`) REFERENCES `kadra` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kadra_przedmioty_ibfk_2` FOREIGN KEY (`id_przedmiotu`) REFERENCES `przedmioty_typ` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `plan_studiow`
--
ALTER TABLE `plan_studiow`
  ADD CONSTRAINT `plan_studiow_ibfk_1` FOREIGN KEY (`id_roku`) REFERENCES `rok_akademicki` (`id_roku`),
  ADD CONSTRAINT `plan_studiow_ibfk_2` FOREIGN KEY (`id_kierunku`) REFERENCES `kierunki_studiow` (`id_kierunku`);

--
-- Constraints for table `przedmioty_typ`
--
ALTER TABLE `przedmioty_typ`
  ADD CONSTRAINT `przedmioty_typ_ibfk_1` FOREIGN KEY (`id_przedmiotu`) REFERENCES `przedmioty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `szczegoly_planu_studiow`
--
ALTER TABLE `szczegoly_planu_studiow`
  ADD CONSTRAINT `szczegoly_planu_studiow_ibfk_1` FOREIGN KEY (`id_planu_studiow`) REFERENCES `plan_studiow` (`id_planu_studiow`),
  ADD CONSTRAINT `szczegoly_planu_studiow_ibfk_2` FOREIGN KEY (`id_przedmiotu_typ`) REFERENCES `przedmioty_typ` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
