-- phpMyAdmin SQL Dump
-- version 2.6.1
-- http://www.phpmyadmin.net
-- 
-- Хост: localhost
-- Время создания: Май 14 2008 г., 8:45
-- Версия сервера: 5.0.45
-- Версия PHP: 5.2.4
-- 
-- БД: `CardFile`
-- 
DROP DATABASE `CardFile`;
CREATE DATABASE `CardFile` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE CardFile;

-- --------------------------------------------------------

-- 
-- Структура таблицы `Category_2`
-- 

CREATE TABLE `Category_2` (
  `Id_Category` int(11) NOT NULL,
  `Category_Name` char(50) NOT NULL,
  PRIMARY KEY  (`Id_Category`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Category_2`
-- 

INSERT INTO `Category_2` VALUES (1, 'Чай');
INSERT INTO `Category_2` VALUES (2, 'Кофе');
INSERT INTO `Category_2` VALUES (3, 'Дрожжи');
INSERT INTO `Category_2` VALUES (4, 'Мука');
INSERT INTO `Category_2` VALUES (5, 'Сахар');
INSERT INTO `Category_2` VALUES (6, 'Шоколад');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Product_1`
-- 

CREATE TABLE `Product_1` (
  `Id_Product` int(11) NOT NULL,
  `Product_Name` varchar(100) NOT NULL,
  `Description` varchar(255) default NULL,
  `Price` double(10,4) NOT NULL,
  `Id_Category` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Product`),
  KEY `Id_Category` (`Id_Category`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED;

-- 
-- Дамп данных таблицы `Product_1`
-- 

INSERT INTO `Product_1` VALUES (1, 'Корона рос. империи 50г.', 'Чай в пачке', 1.8200, 1);
INSERT INTO `Product_1` VALUES (2, 'Царская корона 85г.', 'Чай в пачке', 3.0200, 1);
INSERT INTO `Product_1` VALUES (3, 'Корона рос. империи 85г.', 'Чай в упаковке', 3.0400, 1);
INSERT INTO `Product_1` VALUES (4, 'Корона рос. империи 200г.', 'Чай в упаковке', 6.0400, 1);
INSERT INTO `Product_1` VALUES (5, 'Кофе Pele Gold раств. 50г. ст/б', 'Кофе в банке', 5.9900, 2);
INSERT INTO `Product_1` VALUES (6, 'Кофе Pele Gold раств. 100г. ст/б', 'Кофе в банке', 10.6900, 2);
INSERT INTO `Product_1` VALUES (7, 'Дрожжи сухие Саф-Момент 11г.', 'Дрожжи в пачке', 0.7000, 3);
INSERT INTO `Product_1` VALUES (8, 'Дрожжи сухие Саф-Момент 50г.', 'Дрожжи в пачке', 1.1900, 3);
INSERT INTO `Product_1` VALUES (9, 'Дрожжи сухие Саф-Момент 100г.', 'Дрожжи в пачке', 2.0000, 3);
INSERT INTO `Product_1` VALUES (10, 'Дрожжи хлебопекарские 100г Купянск', NULL, 0.2900, 3);
INSERT INTO `Product_1` VALUES (11, 'Чайный напиток "Лист черники" 30гр', NULL, 4.7300, 1);
INSERT INTO `Product_1` VALUES (12, 'Шоколад Корона Черный 100 г', 'Плитка шоколада', 2.4700, 6);
INSERT INTO `Product_1` VALUES (13, 'Шоколад Корона Черный с орехом 100 г', NULL, 2.7700, 6);
INSERT INTO `Product_1` VALUES (14, 'Шоколад Корона Черный с изюмом 100 г', NULL, 2.7700, 6);
INSERT INTO `Product_1` VALUES (15, 'Шоколад Корона Черный с изюмом и орехом 100 г', 'Плитка шоколада', 2.7700, 6);
INSERT INTO `Product_1` VALUES (16, 'Шоколад Корона Молочный 100 г', 'шоколад', 2.4700, 6);
INSERT INTO `Product_1` VALUES (17, 'Шоколад Корона Молочный с орехом 100 г', NULL, 2.7700, 6);
INSERT INTO `Product_1` VALUES (18, 'Шоколад Милка Молочный 80г Крафт', 'шоколад', 2.4800, 6);
INSERT INTO `Product_1` VALUES (19, 'Шоколад Милка Молочный (Лесной орех) 80г Крафт', 'шоколад', 2.7800, 6);
INSERT INTO `Product_1` VALUES (20, 'Шоколад Милка Молочный (Изюм/орех) 80г Крафт', 'шоколад', 2.6900, 6);
INSERT INTO `Product_1` VALUES (21, 'Шоколад Спокуса молочный пористый 100г Крафт', 'спокуса-шоколад', 3.3300, 6);
INSERT INTO `Product_1` VALUES (22, 'Шоколад Спокуса черный Экстра 100г Крафт', 'спокуса-шоколад', 3.5900, 6);
INSERT INTO `Product_1` VALUES (23, 'Батончик Три Бит 45 г', 'батончик', 1.4100, 6);
INSERT INTO `Product_1` VALUES (24, 'Шоколад Черный  с  целым  орехом  200г Крафт', 'шоколад', 7.4100, 6);
INSERT INTO `Product_1` VALUES (25, 'Шоколад Милка  Джой  70г Крафт', 'шоколад', 2.1400, 6);
INSERT INTO `Product_1` VALUES (26, 'Шоколад Милка  Джой орех 70г Крафт', 'шоколад', 2.6800, 6);
INSERT INTO `Product_1` VALUES (27, 'Шоколад Милка  Джой миндаль 70г Крафт', 'шоколад', 2.6800, 6);
INSERT INTO `Product_1` VALUES (28, 'Шоколад Свиточ Орех 100г Свиточ', 'свиточ-шоколад', 2.3300, 6);
INSERT INTO `Product_1` VALUES (29, 'Шоколад Милка  Джой карамель 70г Крафт', 'свиточ-шоколад', 2.6800, 6);
INSERT INTO `Product_1` VALUES (30, 'Шоколад Свиточ с персиковым йогуртом 95г Свиточ', 'свиточ-шоколад', 2.4000, 6);
INSERT INTO `Product_1` VALUES (31, 'Шоколад Свиточ Молочный с лесным орехом 98г Свиточ', 'свиточ-шоколад', 2.8600, 6);
INSERT INTO `Product_1` VALUES (32, 'Шоколад Свиточ Молочный хрустящий 90г Свиточ', 'свиточ-шоколад', 2.6400, 6);
INSERT INTO `Product_1` VALUES (33, 'Шоколад Свиточ 2 в 1 Молочный и белый 100г Свиточ', 'свиточ-шоколад', 2.7100, 6);
INSERT INTO `Product_1` VALUES (34, 'Шоколад Свиточ Белый с Кокосом 95г Свиточ', 'свиточ-шоколад', 2.6600, 6);
        