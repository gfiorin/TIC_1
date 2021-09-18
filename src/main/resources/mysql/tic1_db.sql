CREATE DATABASE `tic1_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `tic1_db`;

CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         `email` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `countries` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `country_name` varchar(80) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;

INSERT INTO `tic1_db`.`countries` (`id`, `country_name`) VALUES
(1,'Afghanistan'),
(2,'Aland Islands'),
(3,'Albania'),
(4,'Algeria'),
(5,'American Samoa'),
(6,'Andorra'),
(7,'Angola'),
(8,'Anguilla'),
(9,'Antarctica'),
(10,'Antigua and Barbuda'),
(11,'Argentina'),
(12,'Armenia'),
(13,'Aruba'),
(14,'Australia'),
(15,'Austria'),
(16,'Azerbaijan'),
(17,'Bahamas'),
(18,'Bahrain'),
(19,'Bangladesh'),
(20,'Barbados'),
(21,'Belarus'),
(22,'Belgium'),
(23,'Belize'),
(24,'Benin'),
(25,'Bermuda'),
(26,'Bhutan'),
(27,'Bolivia'),
(28,'Bonaire, Sint Eustatius and Saba'),
(29,'Bosnia and Herzegovina'),
(30,'Botswana'),
(31,'Bouvet Island'),
(32,'Brazil'),
(33,'British Indian Ocean Territory'),
(34,'Brunei Darussalam'),
(35,'Bulgaria'),
(36,'Burkina Faso'),
(37,'Burundi'),
(38,'Cambodia'),
(39,'Cameroon'),
(40,'Canada'),
(41,'Cape Verde'),
(42,'Cayman Islands'),
(43,'Central African Republic'),
(44,'Chad'),
(45,'Chile'),
(46,'China'),
(47,'Christmas Island'),
(48,'Cocos (Keeling) Islands'),
(49,'Colombia'),
(50,'Comoros'),
(51,'Congo'),
(52,'Congo, Democratic Republic of the Congo'),
(53,'Cook Islands'),
(54,'Costa Rica'),
(55,'Cote D\'Ivoire'),
(56,'Croatia'),
(57,'Cuba'),
(58,'Curacao'),
(59,'Cyprus'),
(60,'Czech Republic'),
(61,'Denmark'),
(62,'Djibouti'),
(63,'Dominica'),
(64,'Dominican Republic'),
(65,'Ecuador'),
(66,'Egypt'),
(67,'El Salvador'),
(68,'Equatorial Guinea'),
(69,'Eritrea'),
(70,'Estonia'),
(71,'Ethiopia'),
(72,'Falkland Islands (Malvinas)'),
(73,'Faroe Islands'),
(74,'Fiji'),
(75,'Finland'),
(76,'France'),
(77,'French Guiana'),
(78,'French Polynesia'),
(79,'French Southern Territories'),
(80,'Gabon'),
(81,'Gambia'),
(82,'Georgia'),
(83,'Germany'),
(84,'Ghana'),
(85,'Gibraltar'),
(86,'Greece'),
(87,'Greenland'),
(88,'Grenada'),
(89,'Guadeloupe'),
(90,'Guam'),
(91,'Guatemala'),
(92,'Guernsey'),
(93,'Guinea'),
(94,'Guinea-Bissau'),
(95,'Guyana'),
(96,'Haiti'),
(97,'Heard Island and Mcdonald Islands'),
(98,'Holy See (Vatican City State)'),
(99,'Honduras'),
(100,'Hong Kong'),
(101,'Hungary'),
(102,'Iceland'),
(103,'India'),
(104,'Indonesia'),
(105,'Iran, Islamic Republic of'),
(106,'Iraq'),
(107,'Ireland'),
(108,'Isle of Man'),
(109,'Israel'),
(110,'Italy'),
(111,'Jamaica'),
(112,'Japan'),
(113,'Jersey'),
(114,'Jordan'),
(115,'Kazakhstan'),
(116,'Kenya'),
(117,'Kiribati'),
(118,'Korea, Democratic People\'s Republic of'),
(119,'Korea, Republic of'),
(120,'Kosovo'),
(121,'Kuwait'),
(122,'Kyrgyzstan'),
(123,'Lao People\'s Democratic Republic'),
(124,'Latvia'),
(125,'Lebanon'),
(126,'Lesotho'),
(127,'Liberia'),
(128,'Libyan Arab Jamahiriya'),
(129,'Liechtenstein'),
(130,'Lithuania'),
(131,'Luxembourg'),
(132,'Macao'),
(133,'Macedonia, the Former Yugoslav Republic of'),
(134,'Madagascar'),
(135,'Malawi'),
(136,'Malaysia'),
(137,'Maldives'),
(138,'Mali'),
(139,'Malta'),
(140,'Marshall Islands'),
(141,'Martinique'),
(142,'Mauritania'),
(143,'Mauritius'),
(144,'Mayotte'),
(145,'Mexico'),
(146,'Micronesia, Federated States of'),
(147,'Moldova, Republic of'),
(148,'Monaco'),
(149,'Mongolia'),
(150,'Montenegro'),
(151,'Montserrat'),
(152,'Morocco'),
(153,'Mozambique'),
(154,'Myanmar'),
(155,'Namibia'),
(156,'Nauru'),
(157,'Nepal'),
(158,'Netherlands'),
(159,'Netherlands Antilles'),
(160,'New Caledonia'),
(161,'New Zealand'),
(162,'Nicaragua'),
(163,'Niger'),
(164,'Nigeria'),
(165,'Niue'),
(166,'Norfolk Island'),
(167,'Northern Mariana Islands'),
(168,'Norway'),
(169,'Oman'),
(170,'Pakistan'),
(171,'Palau'),
(172,'Palestinian Territory, Occupied'),
(173,'Panama'),
(174,'Papua New Guinea'),
(175,'Paraguay'),
(176,'Peru'),
(177,'Philippines'),
(178,'Pitcairn'),
(179,'Poland'),
(180,'Portugal'),
(181,'Puerto Rico'),
(182,'Qatar'),
(183,'Reunion'),
(184,'Romania'),
(185,'Russian Federation'),
(186,'Rwanda'),
(187,'Saint Barthelemy'),
(188,'Saint Helena'),
(189,'Saint Kitts and Nevis'),
(190,'Saint Lucia'),
(191,'Saint Martin'),
(192,'Saint Pierre and Miquelon'),
(193,'Saint Vincent and the Grenadines'),
(194,'Samoa'),
(195,'San Marino'),
(196,'Sao Tome and Principe'),
(197,'Saudi Arabia'),
(198,'Senegal'),
(199,'Serbia'),
(200,'Serbia and Montenegro'),
(201,'Seychelles'),
(202,'Sierra Leone'),
(203,'Singapore'),
(204,'Sint Maarten'),
(205,'Slovakia'),
(206,'Slovenia'),
(207,'Solomon Islands'),
(208,'Somalia'),
(209,'South Africa'),
(210,'South Georgia and the South Sandwich Islands'),
(211,'South Sudan'),
(212,'Spain'),
(213,'Sri Lanka'),
(214,'Sudan'),
(215,'Suriname'),
(216,'Svalbard and Jan Mayen'),
(217,'Swaziland'),
(218,'Sweden'),
(219,'Switzerland'),
(220,'Syrian Arab Republic'),
(221,'Taiwan, Province of China'),
(222,'Tajikistan'),
(223,'Tanzania, United Republic of'),
(224,'Thailand'),
(225,'Timor-Leste'),
(226,'Togo'),
(227,'Tokelau'),
(228,'Tonga'),
(229,'Trinidad and Tobago'),
(230,'Tunisia'),
(231,'Turkey'),
(232,'Turkmenistan'),
(233,'Turks and Caicos Islands'),
(234,'Tuvalu'),
(235,'Uganda'),
(236,'Ukraine'),
(237,'United Arab Emirates'),
(238,'United Kingdom'),
(239,'United States'),
(240,'United States Minor Outlying Islands'),
(241,'Uruguay'),
(242,'Uzbekistan'),
(243,'Vanuatu'),
(244,'Venezuela'),
(245,'Viet Nam'),
(246,'Virgin Islands, British'),
(247,'Virgin Islands, U.s.'),
(248,'Wallis and Futuna'),
(249,'Western Sahara'),
(250,'Yemen'),
(251,'Zambia'),
(252,'Zimbabwe');

CREATE TABLE `tourists` (
                            `idtourist` int NOT NULL,
                            `dateOfBirth` datetime DEFAULT NULL,
                            `cellphone` varchar(45) DEFAULT NULL,
                            `documentType` enum('DNI','RG','Cedula','Passport') DEFAULT NULL,
                            `documentNumber` varchar(45) DEFAULT NULL,
                            `countryOfBirth` int DEFAULT NULL,
                            PRIMARY KEY (`idtourist`),
                            UNIQUE KEY `document_UNIQUE` (`documentNumber`),
                            KEY `fk_idcountry_idx` (`countryOfBirth`),
                            CONSTRAINT `fk_idcountry` FOREIGN KEY (`countryOfBirth`) REFERENCES `countries` (`id`),
                            CONSTRAINT `fk_idtourist` FOREIGN KEY (`idtourist`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `administrators` (
                                  `idadministrator` int NOT NULL,
                                  PRIMARY KEY (`idadministrator`),
                                  CONSTRAINT `fk_idadministrator` FOREIGN KEY (`idadministrator`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `toperators` (
                              `idtoperators` int NOT NULL,
                              `companyName` varchar(45) NOT NULL,
                              `fantasyName` varchar(45) NOT NULL,
                              `link` varchar(45) NOT NULL,
                              `cName` varchar(45) NOT NULL,
                              `cCellphone` varchar(45) NOT NULL,
                              `cPosition` varchar(45) NOT NULL,
                              `cEmail` varchar(45) NOT NULL,
                              `authorized` enum('Si','No') NOT NULL,
                              PRIMARY KEY (`idtoperators`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `operators` (
                             `idoperator` int NOT NULL,
                             `tOperator` int DEFAULT NULL,
                             PRIMARY KEY (`idoperator`),
                             KEY `fk_toperator_idx` (`tOperator`),
                             CONSTRAINT `fk_idoperator` FOREIGN KEY (`idoperator`) REFERENCES `users` (`id`),
                             CONSTRAINT `fk_toperator` FOREIGN KEY (`tOperator`) REFERENCES `toperators` (`idtoperators`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `experiences` (
                               `idexperience` int NOT NULL,
                               `authorized` enum('Si','No') NOT NULL,
                               `title` varchar(45) NOT NULL,
                               `description` varchar(45) NOT NULL,
                               `vaccination` enum('Si','No') NOT NULL,
                               `capacity` varchar(45) NOT NULL,
                               `bookable` enum('Si','No') NOT NULL,
                               PRIMARY KEY (`idexperience`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `images` (
                          `idexperience` int NOT NULL,
                          `image` blob NOT NULL,
                          PRIMARY KEY (`idexperience`),
                          CONSTRAINT `fk_idexperience` FOREIGN KEY (`idexperience`) REFERENCES `experiences` (`idexperience`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `interests` (
                             `idinterests` int NOT NULL,
                             `name` varchar(45) NOT NULL,
                             PRIMARY KEY (`idinterests`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `complaints` (
                              `idcomplaint` int NOT NULL,
                              `description` varchar(45) NOT NULL,
                              `date` datetime NOT NULL,
                              `experience` int NOT NULL,
                              `tourist` int NOT NULL,
                              PRIMARY KEY (`idcomplaint`),
                              KEY `fk_exp_idx` (`experience`),
                              KEY `fk_tou_idx` (`tourist`),
                              CONSTRAINT `fk_exp` FOREIGN KEY (`experience`) REFERENCES `experiences` (`idexperience`),
                              CONSTRAINT `fk_tou` FOREIGN KEY (`tourist`) REFERENCES `tourists` (`idtourist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tinterests` (
                              `idtourist` int NOT NULL,
                              `idinterest` int NOT NULL,
                              PRIMARY KEY (`idtourist`,`idinterest`),
                              KEY `fk_i_idx` (`idinterest`),
                              CONSTRAINT `fk_i` FOREIGN KEY (`idinterest`) REFERENCES `interests` (`idinterests`),
                              CONSTRAINT `fk_t` FOREIGN KEY (`idtourist`) REFERENCES `tourists` (`idtourist`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `intexperiences` (
                                  `idexperience` int NOT NULL,
                                  `idinterest` int NOT NULL,
                                  PRIMARY KEY (`idexperience`),
                                  KEY `fk_int_idx` (`idinterest`),
                                  CONSTRAINT `fk_e` FOREIGN KEY (`idexperience`) REFERENCES `experiences` (`idexperience`),
                                  CONSTRAINT `fk_int` FOREIGN KEY (`idinterest`) REFERENCES `interests` (`idinterests`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE USER 'springuser'@'%' identified by 'ThePassword';
GRANT ALL on tic1_db.* to 'springuser'@'%';