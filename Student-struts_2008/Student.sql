-- phpMyAdmin SQL Dump
-- version 2.6.1
-- http://www.phpmyadmin.net
-- 
-- Хост: localhost
-- Время создания: Июн 20 2008 г., 10:30
-- Версия сервера: 5.0.45
-- Версия PHP: 5.2.4
-- 
-- БД: `Student`
-- 

-- --------------------------------------------------------

-- 
-- Структура таблицы `Admin_3`
-- 

CREATE TABLE `Admin_3` (
  `Id_People` int(11) NOT NULL,
  PRIMARY KEY  (`Id_People`),
  UNIQUE KEY `XPKAdmin_3` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Admin_3`
-- 

INSERT INTO `Admin_3` VALUES (1);

-- --------------------------------------------------------

-- 
-- Структура таблицы `Alternative_14`
-- 

CREATE TABLE `Alternative_14` (
  `Id_Task` int(11) NOT NULL,
  `Alternative_name` varchar(30) NOT NULL,
  PRIMARY KEY  (`Id_Task`,`Alternative_name`),
  UNIQUE KEY `XPKAlternative_14` (`Id_Task`,`Alternative_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Alternative_14`
-- 

INSERT INTO `Alternative_14` VALUES (1, 'Ильгов А.Г.');
INSERT INTO `Alternative_14` VALUES (1, 'Кучерявенко О.В.');
INSERT INTO `Alternative_14` VALUES (1, 'Петров А.И');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Comment_9`
-- 

CREATE TABLE `Comment_9` (
  `Id_Chapter` int(11) NOT NULL,
  `Comment_number` int(11) NOT NULL,
  `Id_People` int(11) NOT NULL,
  `Comment_value` varchar(5000) default NULL,
  `Comment_Date` date NOT NULL,
  PRIMARY KEY  (`Id_Chapter`,`Comment_number`),
  UNIQUE KEY `XPKComment_9` (`Id_Chapter`,`Comment_number`),
  KEY `Id_People` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Comment_9`
-- 

INSERT INTO `Comment_9` VALUES (1, 2, 1, 'Прочел задание, вот сделал небольшой обзор предметной области. ', '2008-02-19');
INSERT INTO `Comment_9` VALUES (1, 1, 3, 'Здравствуйте Владимир, вот ваше задание на курсовую работу этого семестра.', '2008-06-19');
INSERT INTO `Comment_9` VALUES (1, 3, 4, 'Также прочтите: <a href="http://science.kharkov.ua/higher-education/vissheye-obrazovaniye-ukraine.html">высшее образование Украины</a>', '2008-06-19');
INSERT INTO `Comment_9` VALUES (1, 4, 1, 'Закончил диаграмму вариантов использования и диаграмму классов.', '2008-06-19');
INSERT INTO `Comment_9` VALUES (1, 5, 4, 'В целом нормально, но необходимо также доделать:\r\nВ диаграмме вариантов использования - выделить четвертый тип пользователя Администратор, и добавить вариант просмотр резюме.\r\nВ диаграмме классов переделать класс Director. ', '2008-06-19');
INSERT INTO `Comment_9` VALUES (1, 6, 3, 'В первой части записки не соблюдается гост, исправьте.', '2008-06-20');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Comment_Object_17`
-- 

CREATE TABLE `Comment_Object_17` (
  `Id_Chapter` int(11) NOT NULL,
  `Id_Object` int(11) NOT NULL,
  `Comment_number` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Chapter`,`Id_Object`,`Comment_number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Comment_Object_17`
-- 

INSERT INTO `Comment_Object_17` VALUES (1, 21, 1);
INSERT INTO `Comment_Object_17` VALUES (1, 22, 2);
INSERT INTO `Comment_Object_17` VALUES (1, 23, 4);

-- --------------------------------------------------------

-- 
-- Структура таблицы `Criterion_15`
-- 

CREATE TABLE `Criterion_15` (
  `Criterion_name` varchar(30) NOT NULL,
  `Id_Task` int(11) NOT NULL,
  PRIMARY KEY  (`Criterion_name`,`Id_Task`),
  UNIQUE KEY `XPKCriterion_15` (`Criterion_name`,`Id_Task`),
  KEY `Id_Task` (`Id_Task`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Criterion_15`
-- 

INSERT INTO `Criterion_15` VALUES ('Возраст претендента', 1);
INSERT INTO `Criterion_15` VALUES ('Образование в данной области', 1);
INSERT INTO `Criterion_15` VALUES ('Профессиональный опыт', 1);

-- --------------------------------------------------------

-- 
-- Структура таблицы `Data_Object_21`
-- 

CREATE TABLE `Data_Object_21` (
  `Id_Object` int(11) NOT NULL,
  `Object` varchar(100) NOT NULL,
  PRIMARY KEY  (`Id_Object`),
  UNIQUE KEY `XPKData_Object_21` (`Id_Object`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Data_Object_21`
-- 

INSERT INTO `Data_Object_21` VALUES (1, '/Student/Upload/Bolotov/index.html');
INSERT INTO `Data_Object_21` VALUES (2, '/Student/Upload/Bolotov/index.html');
INSERT INTO `Data_Object_21` VALUES (3, '/Student/Upload/Postrigan/lab5.xls');
INSERT INTO `Data_Object_21` VALUES (21, '/Student/Upload/Balashov/Postrigan.rtf');
INSERT INTO `Data_Object_21` VALUES (22, '/Student/Upload/Postrigan/Часть1.doc');
INSERT INTO `Data_Object_21` VALUES (23, '/Student/Upload/Postrigan/CIPS.mdl');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Employee_6`
-- 

CREATE TABLE `Employee_6` (
  `Id_People` int(11) NOT NULL,
  PRIMARY KEY  (`Id_People`),
  UNIQUE KEY `XPKEmployee_6` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Employee_6`
-- 

INSERT INTO `Employee_6` VALUES (4);

-- --------------------------------------------------------

-- 
-- Структура таблицы `Forum_8`
-- 

CREATE TABLE `Forum_8` (
  `Id_Chapter` int(11) NOT NULL,
  `Chapter_Name` varchar(150) NOT NULL,
  `Chapter_Password` varchar(20) default NULL,
  `Id_People` int(11) NOT NULL,
  `Chapter_Date` date NOT NULL,
  PRIMARY KEY  (`Id_Chapter`),
  UNIQUE KEY `XPKForum_8` (`Id_Chapter`),
  KEY `Id_People` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Forum_8`
-- 

INSERT INTO `Forum_8` VALUES (1, 'Разработка информационно-справочной системы для целевой подготовки студентов Постригань В.А.', '1', 1, '2008-02-07');
INSERT INTO `Forum_8` VALUES (3, 'Разработка информационно-справочной системы для автоматизации товаров супермаркета Болотов А.Т', '1', 5, '2007-01-14');
INSERT INTO `Forum_8` VALUES (4, 'Разработка информационно-справочной системы для решения задачи выбора инвестиционных проектов в сфере ВОУ Петров А.И.', '1', 6, '2007-09-19');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Message_7`
-- 

CREATE TABLE `Message_7` (
  `Id_Message` int(11) NOT NULL,
  `Id_People_Creator` int(11) NOT NULL,
  `Message` varchar(5000) default NULL,
  `Theme` varchar(30) NOT NULL,
  `Id_People_Receiver` int(11) NOT NULL,
  `Message_Date` date NOT NULL,
  PRIMARY KEY  (`Id_Message`),
  UNIQUE KEY `XPKMessage_7` (`Id_Message`),
  KEY `Id_People` (`Id_People_Creator`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Message_7`
-- 

INSERT INTO `Message_7` VALUES (1, 5, 'Здравствуйте, Владимир.<br />\r\n<p>Вашему вниманию предлагается методическое пособие на тему "Установка \r\nприложений Mandrake Linux из RPM-пакетов, записанных на образы типа \r\nISO". Поскольку Вам уже знаком процесс подключения ISO-диска (вкратце \r\nсводящийся к выбору пункта "Capture ISO" из меню "CD"), подробно на этом \r\nостанавливаться не станем.</p>', 'Установка приложения', 1, '2008-06-19');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Message_Object_23`
-- 

CREATE TABLE `Message_Object_23` (
  `Id_Message` int(11) NOT NULL,
  `Id_Object` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Message`,`Id_Object`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Message_Object_23`
-- 

INSERT INTO `Message_Object_23` VALUES (1, 1);

-- --------------------------------------------------------

-- 
-- Структура таблицы `People_1`
-- 

CREATE TABLE `People_1` (
  `Id_People` int(11) NOT NULL,
  `Surname` varchar(25) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Patronymic` varchar(30) NOT NULL,
  `Login` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `People_type` varchar(20) NOT NULL,
  `Age` int(11) NOT NULL,
  `Address` varchar(80) NOT NULL,
  `Avatar` varchar(100) default NULL,
  PRIMARY KEY  (`Id_People`),
  UNIQUE KEY `XPKPeople_1` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `People_1`
-- 

INSERT INTO `People_1` VALUES (1, 'Постригань', 'Владимир', 'Александрович', 'Postrigan', 'pos', 'Admin', 1980, 'г. Харьков, ул. 18, кв. 10', '/Student/Upload/Postrigan/Pirit_Avatar_RC2.PNG');
INSERT INTO `People_1` VALUES (2, 'Алёхин', 'Андрей', 'Витальевич', 'Alehin', 'ale', 'Student', 1986, 'г. Харьков, ул. Ленина 15', '/Student/Upload/ale/Pirit_Avatar_beta1.bmp');
INSERT INTO `People_1` VALUES (3, 'Балашов', 'Виктор', 'Петрович', 'Balashov', 'bal', 'Teacher', 1978, 'г. Харьков, ул. Петрова 27', '/Student/Upload/Balashov/AvatarDefault.jpg');
INSERT INTO `People_1` VALUES (4, 'Ежов', 'Александр', 'Иванович', 'Egov', 'ego', 'Employee', 1980, 'г. Киев, ул. Победы, 5  ', '/Student/Upload/Egov/an_other_014.gif');
INSERT INTO `People_1` VALUES (7, 'Кучерявенко', 'Олег', 'Викторович', 'Kucher', 'kuc', 'Student', 1987, 'г. Харьков, ул. Пионеров 76, кв 1.', '/Student/css/img/AvatarDefault.jpg');
INSERT INTO `People_1` VALUES (5, 'Болотов', 'Андрей', 'Тимофеевич', 'Bolotov', 'bol', 'Student', 1987, 'г. Харьков, ул. Иванова 23, кв 34', '/Student/css/img/AvatarDefault.jpg');
INSERT INTO `People_1` VALUES (6, 'Петров', 'Александр', 'Иванович', 'Petrov', 'pet', 'Student', 1988, 'г. Харьков, ул. Тракторостроителей 8, кв. 10 ', '/Student/css/img/AvatarDefault.jpg');
INSERT INTO `People_1` VALUES (8, 'Ильгов', 'Алексей', 'Генадиевич', 'Ilgov', 'ilg', 'Student', 1987, 'г. Харьков, ул. Красной армии 4, кв 120', '/Student/css/img/AvatarDefault.jpg');
INSERT INTO `People_1` VALUES (9, 'Сорокин', 'Петр', 'Иванович', 'Sorokin', 'sor', 'Student', 1988, 'г. Харьков, ул. Гришенко 1, кв 45', '/Student/css/img/AvatarDefault.jpg');
INSERT INTO `People_1` VALUES (10, 'Новиков', 'Виктор', 'Павлович', 'Novikov', 'nov', 'Student', 1985, 'г. Харьков, ул. Новикова 7, кв 23', '/Student/css/img/AvatarDefault.jpg');

-- --------------------------------------------------------

-- 
-- Структура таблицы `People_Email_19`
-- 

CREATE TABLE `People_Email_19` (
  `Id_People` int(11) NOT NULL,
  `E_mail` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id_People`,`E_mail`),
  UNIQUE KEY `XPKPeople_Email_19` (`Id_People`,`E_mail`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `People_Email_19`
-- 

INSERT INTO `People_Email_19` VALUES (1, 'Test11@mail.ru');
INSERT INTO `People_Email_19` VALUES (1, 'PoVTest@mail.ru');

-- --------------------------------------------------------

-- 
-- Структура таблицы `People_Employment_20`
-- 

CREATE TABLE `People_Employment_20` (
  `Id_People` int(11) NOT NULL,
  `Employment` varchar(50) NOT NULL,
  PRIMARY KEY  (`Id_People`,`Employment`),
  UNIQUE KEY `XPKPeople_Employment_20` (`Id_People`,`Employment`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `People_Employment_20`
-- 

INSERT INTO `People_Employment_20` VALUES (1, 'НТУ "ХПИ"');

-- --------------------------------------------------------

-- 
-- Структура таблицы `People_Phone_2`
-- 

CREATE TABLE `People_Phone_2` (
  `Id_People` int(11) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  PRIMARY KEY  (`Id_People`,`Phone`),
  UNIQUE KEY `XPKPeople_Phone_2` (`Id_People`,`Phone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `People_Phone_2`
-- 

INSERT INTO `People_Phone_2` VALUES (1, '61-29-79');
INSERT INTO `People_Phone_2` VALUES (1, '8 099 40 11 108');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Preference_16`
-- 

CREATE TABLE `Preference_16` (
  `Preference_name` varchar(30) NOT NULL,
  `Preference_value` varchar(30) NOT NULL,
  `Id_Task` int(11) NOT NULL,
  PRIMARY KEY  (`Preference_name`,`Preference_value`,`Id_Task`),
  UNIQUE KEY `XPKPreference_16` (`Preference_name`,`Preference_value`,`Id_Task`),
  KEY `Id_Task` (`Id_Task`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Preference_16`
-- 


-- --------------------------------------------------------

-- 
-- Структура таблицы `Result_18`
-- 

CREATE TABLE `Result_18` (
  `Id_Task` int(11) NOT NULL,
  `Result_value` varchar(20) NOT NULL,
  PRIMARY KEY  (`Id_Task`,`Result_value`),
  UNIQUE KEY `XPKResult_18` (`Id_Task`,`Result_value`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Result_18`
-- 


-- --------------------------------------------------------

-- 
-- Структура таблицы `Student_4`
-- 

CREATE TABLE `Student_4` (
  `Id_People` int(11) NOT NULL,
  `Resume` varchar(5000) default NULL,
  PRIMARY KEY  (`Id_People`),
  UNIQUE KEY `XPKStudent_4` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Student_4`
-- 

INSERT INTO `Student_4` VALUES (2, '<html>Resume...</html>');
INSERT INTO `Student_4` VALUES (6, '');
INSERT INTO `Student_4` VALUES (5, '');
INSERT INTO `Student_4` VALUES (7, '');
INSERT INTO `Student_4` VALUES (8, '');
INSERT INTO `Student_4` VALUES (9, '');
INSERT INTO `Student_4` VALUES (10, '');

-- --------------------------------------------------------

-- 
-- Структура таблицы `Student_Test_22`
-- 

CREATE TABLE `Student_Test_22` (
  `Id_Student_Test` int(11) NOT NULL,
  `Date_Answer` date NOT NULL,
  `Id_People` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Student_Test`),
  UNIQUE KEY `XPKStudent_Test_22` (`Id_Student_Test`),
  KEY `Id_People` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Student_Test_22`
-- 


-- --------------------------------------------------------

-- 
-- Структура таблицы `Student_Test_Result_12`
-- 

CREATE TABLE `Student_Test_Result_12` (
  `Id_Student_Test` int(11) NOT NULL,
  `Id_Test` int(11) NOT NULL,
  `Id_Test_Question` int(11) NOT NULL,
  `Student_Answer` varchar(20) default NULL,
  `Mark` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Student_Test`,`Id_Test`,`Id_Test_Question`),
  UNIQUE KEY `XPKStudent_Test_Result_12` (`Id_Student_Test`,`Id_Test`,`Id_Test_Question`),
  KEY `Id_Test` (`Id_Test`,`Id_Test_Question`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Student_Test_Result_12`
-- 


-- --------------------------------------------------------

-- 
-- Структура таблицы `Task_13`
-- 

CREATE TABLE `Task_13` (
  `Id_Task` int(11) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Date` date NOT NULL,
  `Id_People` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Task`),
  UNIQUE KEY `XPKTask_13` (`Id_Task`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Task_13`
-- 

INSERT INTO `Task_13` VALUES (1, 'Выбор программистов', '2008-06-19', 4);

-- --------------------------------------------------------

-- 
-- Структура таблицы `Teacher_5`
-- 

CREATE TABLE `Teacher_5` (
  `Id_People` int(11) NOT NULL,
  PRIMARY KEY  (`Id_People`),
  UNIQUE KEY `XPKTeacher_5` (`Id_People`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Teacher_5`
-- 

INSERT INTO `Teacher_5` VALUES (3);

-- --------------------------------------------------------

-- 
-- Структура таблицы `Test_10`
-- 

CREATE TABLE `Test_10` (
  `Id_Test` int(11) NOT NULL,
  `Date_Create` date default NULL,
  `Test_Name` varchar(30) NOT NULL,
  PRIMARY KEY  (`Id_Test`),
  UNIQUE KEY `XPKTest_10` (`Id_Test`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Test_10`
-- 


-- --------------------------------------------------------

-- 
-- Структура таблицы `Test_Question_11`
-- 

CREATE TABLE `Test_Question_11` (
  `Id_Test_Question` int(11) NOT NULL,
  `Question` varchar(100) NOT NULL,
  `Answer` varchar(100) NOT NULL,
  `Id_Test` int(11) NOT NULL,
  PRIMARY KEY  (`Id_Test`,`Id_Test_Question`),
  UNIQUE KEY `XPKTest_Question_11` (`Id_Test`,`Id_Test_Question`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Дамп данных таблицы `Test_Question_11`
-- 
