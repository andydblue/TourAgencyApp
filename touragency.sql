-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Мар 03 2021 г., 09:44
-- Версия сервера: 10.4.17-MariaDB
-- Версия PHP: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `touragency`
--

-- --------------------------------------------------------

--
-- Структура таблицы `countries`
--

CREATE TABLE `countries` (
  `id_ctry` int(11) NOT NULL,
  `countryName` varchar(45) DEFAULT NULL,
  `season` enum('лето','зима','весна','осень') DEFAULT NULL,
  `flightCoast` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `countries`
--

INSERT INTO `countries` (`id_ctry`, `countryName`, `season`, `flightCoast`) VALUES
(1, 'Греция', 'лето', 25000),
(2, 'Грузия', 'зима', 35200),
(3, 'Египет', 'зима', 40000),
(4, 'Италия', 'лето', 37000),
(5, 'Испания', 'весна', 50000),
(6, 'Франция', 'осень', 62000),
(7, 'Турция', 'осень', 42000);

-- --------------------------------------------------------

--
-- Структура таблицы `excursions`
--

CREATE TABLE `excursions` (
  `id_exc` int(11) NOT NULL,
  `id_ctry_for_exc` int(11) NOT NULL,
  `excursionName` varchar(70) DEFAULT NULL,
  `excursionCoast` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `excursions`
--

INSERT INTO `excursions` (`id_exc`, `id_ctry_for_exc`, `excursionName`, `excursionCoast`) VALUES
(1, 1, 'кушаем лепешечки, валяемся у моря, пляшем сиртаки', 0),
(2, 1, 'покупаем шубы и репродукции фреск', 12000),
(3, 1, 'катаемся по островам, фоткаемся на фоне развалин, дышим античностью', 35000),
(4, 2, 'ай, зачем тебе эти экскурсии? Дядя Гиви сам тебе все покажет', 0),
(5, 2, 'горнолыжный тур break a leg', 30000),
(6, 2, 'гастротур непросыхайка', 60000),
(7, 3, 'песок на зубах: пирамиды, гробницы, храмы', 45000),
(8, 3, 'шоп-тур: Наташа мы сделаем скидку', 8000),
(9, 3, 'греем жопки у моря, ловим рыбок панамками', 0),
(10, 4, 'шмоток много не бывает', 58000),
(11, 4, 'гуляем пешочком, ищем нормальную пиццу как у мамы', 0),
(12, 4, 'слушаем оперу (удиви своих знакомых)', 25000),
(13, 5, 'футбол, коррида, парк аттракционов', 36000),
(14, 5, 'верни мне мой 2007: тур по готической архитектуре', 27000),
(15, 5, 'боремся с ветряными мельницами', 0),
(16, 6, 'огни Парижа', 70000),
(17, 6, 'тур по винам', 12000),
(18, 6, 'берем палатку и идем в Лувр', 20000),
(19, 7, 'у меня ол инклюзив и трое детей', 0),
(20, 7, 'а ты купи слона - тур по рынкам', 26000),
(21, 7, 'там нет моря и придется ходить, оно вам надо?', 45000);

-- --------------------------------------------------------

--
-- Структура таблицы `hotels`
--

CREATE TABLE `hotels` (
  `id_htl` int(11) NOT NULL,
  `id_ctry_for_htl` int(11) NOT NULL,
  `hotelName` varchar(45) DEFAULT NULL,
  `starsRating` enum('1','2','3','4','5') DEFAULT NULL,
  `roomCoast` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `hotels`
--

INSERT INTO `hotels` (`id_htl`, `id_ctry_for_htl`, `hotelName`, `starsRating`, `roomCoast`) VALUES
(2, 2, 'Tiflis Palace', '4', 57000),
(3, 2, 'Lopota Lake Resort & Spa', '5', 87000),
(4, 2, 'Hotel Terrace Kutaisi', '3', 29000),
(5, 1, 'Electra Metropolis', '5', 122000),
(6, 1, 'Acropolis View Hotel', '3', 55000),
(7, 1, 'Phidias Piraeus Hotel', '2', 35800),
(8, 3, 'Steigenberger Aldau Beach Hotel', '5', 107000),
(9, 3, 'Sofitel Cairo Nile El Gezirah', '5', 59000),
(10, 3, 'Tolip Resort El Galala Majestic', '5', 97000),
(11, 7, 'Celal Aga Konagi Metro Hotel', '5', 41000),
(12, 7, 'Sura Design Hotel & Suites', '4', 73000),
(13, 7, 'Sealife Family Resort Hotel', '5', 65000),
(14, 5, 'Riu Plaza España', '4', 113000),
(15, 5, 'Palm Oasis Maspalomas', '3', 65000),
(16, 5, 'El Avenida Palace', '4', 76000),
(17, 4, 'Hotel Giolli Nazionale', '3', 40000),
(18, 4, 'Hotel Abbazia', '3', 62000),
(19, 4, 'The Square Milano Duomo', '4', 150000),
(20, 6, 'Hôtel des Etats-Unis', '2', 68000),
(21, 6, 'Radisson Blu Hotel, Lyon', '4', 109000),
(22, 6, 'citizenM Paris Gare de Lyon', '4', 62800);

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `id_order` int(11) NOT NULL,
  `orderNumber` bigint(11) DEFAULT NULL,
  `customer` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `hotel` varchar(45) DEFAULT NULL,
  `excursion` varchar(70) DEFAULT NULL,
  `totalCoast` bigint(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`id_order`, `orderNumber`, `customer`, `country`, `hotel`, `excursion`, `totalCoast`) VALUES
(2, 423739905, 'fse4 gf5', 'Италия', 'Hotel Giolli Nazionale', 'слушаем оперу (удиви своих знакомых)', 102000);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id_ctry`);

--
-- Индексы таблицы `excursions`
--
ALTER TABLE `excursions`
  ADD PRIMARY KEY (`id_exc`),
  ADD KEY `id_ctry_for_exc` (`id_ctry_for_exc`);

--
-- Индексы таблицы `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id_htl`),
  ADD KEY `id_ctry_for_htl` (`id_ctry_for_htl`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id_order`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id_ctry` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `excursions`
--
ALTER TABLE `excursions`
  MODIFY `id_exc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT для таблицы `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id_htl` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `excursions`
--
ALTER TABLE `excursions`
  ADD CONSTRAINT `excursions_ibfk_1` FOREIGN KEY (`id_ctry_for_exc`) REFERENCES `countries` (`id_ctry`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `hotels`
--
ALTER TABLE `hotels`
  ADD CONSTRAINT `hotels_ibfk_1` FOREIGN KEY (`id_ctry_for_htl`) REFERENCES `countries` (`id_ctry`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
