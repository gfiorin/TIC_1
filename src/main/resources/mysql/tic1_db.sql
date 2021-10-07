CREATE DATABASE `tic1_db`;

USE `tic1_db`;

CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `name` varchar(45) NOT NULL,
                         `username` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         `email` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE `countries` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `country_name` varchar(80) NOT NULL,
                             PRIMARY KEY (`id`)
);

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
                            `id_tourist` int NOT NULL,
                            `date_of_birth` datetime DEFAULT NULL,
                            `cellphone` varchar(45) DEFAULT NULL,
                            `document_type` enum('DNI','RG','Cedula','Pasaporte') DEFAULT NULL,
                            `document_number` varchar(45) DEFAULT NULL,
                            `country_of_birth` int DEFAULT NULL,
                            PRIMARY KEY (`id_tourist`),
                            UNIQUE KEY `document_UNIQUE` (`document_number`),
                            KEY `fk_idcountry_idx` (`country_of_birth`),
                            CONSTRAINT `fk_idcountry` FOREIGN KEY (`country_of_birth`) REFERENCES `countries` (`id`),
                            CONSTRAINT `fk_idtourist` FOREIGN KEY (`id_tourist`) REFERENCES `users` (`id`)
);

CREATE TABLE `administrators` (
                                  `id_administrator` int NOT NULL,
                                  PRIMARY KEY (`id_administrator`),
                                  CONSTRAINT `fk_idadministrator` FOREIGN KEY (`id_administrator`) REFERENCES `users` (`id`)
);

INSERT INTO `tic1_db`.`users` (`id`,`name`,`username`,`password`,`email`) VALUES
(1,'admin1','admin1','tic1','admin1@gmail.com');

INSERT INTO `tic1_db`.`administrators` (`id_administrator`) VALUES
(1);

INSERT INTO `tic1_db`.`users` (`id`,`name`,`username`,`password`,`email`) VALUES
    (2,'admin2','admin2','tic1','admin2@gmail.com');

INSERT INTO `tic1_db`.`administrators` (`id_administrator`) VALUES
    (2);

CREATE TABLE `tour_operators` (
                                  `id_toperators` int NOT NULL AUTO_INCREMENT,
                                  `company_name` varchar(45) NOT NULL,
                                  `fantasy_name` varchar(45) NOT NULL,
                                  `link_to_web` varchar(45) NOT NULL,
                                  `contact_name` varchar(45) NOT NULL,
                                  `contact_phone` varchar(45) NOT NULL,
                                  `contact_position` varchar(45) NOT NULL,
                                  `contact_email` varchar(45) NOT NULL,
                                  `authorized` tinyint NOT NULL,
                                  PRIMARY KEY (`id_toperators`),
                                  UNIQUE KEY `company_name_UNIQUE` (`company_name`),
                                  UNIQUE KEY `fantasy_name_UNIQUE` (`fantasy_name`),
                                  UNIQUE KEY `contact_email_UNIQUE` (`contact_email`),
                                  UNIQUE KEY `contact_phone_UNIQUE` (`contact_phone`),
                                  UNIQUE KEY `link_to_web_UNIQUE` (`link_to_web`)
);

INSERT INTO tic1_db.tour_operators (company_name,fantasy_name,link_to_web,contact_name,contact_phone,contact_position,contact_email,authorized)
VALUES ('tour_name','fantasy_name','tour.com','John Doe','123456789','Manager','jdoe@gmial.com',1);


CREATE TABLE `operators` (
                             `id_operator` int NOT NULL,
                             `toperator` int DEFAULT NULL,
                             PRIMARY KEY (`id_operator`),
                             KEY `fk_toperator_idx` (`toperator`),
                             CONSTRAINT `fk_idoperator` FOREIGN KEY (`id_operator`) REFERENCES `users` (`id`),
                             CONSTRAINT `fk_toperator` FOREIGN KEY (`toperator`) REFERENCES `tour_operators` (`id_toperators`)
);

CREATE TABLE `departments`(`id_departments` int NOT NULL AUTO_INCREMENT,
                           `department_name` varchar(45) NOT NULL,
                           PRIMARY KEY (`id_departments`)
);

INSERT INTO `tic1_db`.`departments` (`id_departments`, `department_name`) VALUES
                                                                              (1, 'Montevideo'),
                                                                              (2, 'Artigas'),
                                                                              (3, 'Canelones'),
                                                                              (4, 'Cerro Largo'),
                                                                              (5, 'Colonia'),
                                                                              (6, 'Durazno'),
                                                                              (7, 'Flores'),
                                                                              (8, 'Florida'),
                                                                              (9, 'Lavalleja'),
                                                                              (10, 'Maldonado'),
                                                                              (11, 'Paysandú'),
                                                                              (12, 'Rio Negro'),
                                                                              (13, 'Rivera'),
                                                                              (14, 'Rocha'),
                                                                              (15, 'Salto'),
                                                                              (16, 'San Jose'),
                                                                              (17, 'Soriano'),
                                                                              (18, 'Tacuarembo'),
                                                                              (19, 'Treinta y Tres');

CREATE TABLE `experiences` (
                               `id_experience` int NOT NULL AUTO_INCREMENT,
                               `id_tourist_operator` int NOT NULL,
                               `price` decimal(10,2) NOT NULL,
                               `department` int NOT NULL,
                               `authorized` tinyint NOT NULL,
                               `title` varchar(45) NOT NULL,
                               `description` varchar(1000) NOT NULL,
                               `vaccination` tinyint NOT NULL,
                               `capacity` varchar(45) NOT NULL,
                               `bookable` tinyint NOT NULL,
                               PRIMARY KEY (`id_experience`),
                               KEY `fk_tourist_operator_idx` (`id_tourist_operator`),
                               KEY `fk_department_idx` (`department`),
                               CONSTRAINT `fk_department` FOREIGN KEY (`department`) REFERENCES `departments` (`id_departments`),
                               CONSTRAINT `fk_tourist_operator` FOREIGN KEY (`id_tourist_operator`) REFERENCES `tour_operators` (`id_toperators`)
);

INSERT INTO tic1_db.experiences (id_tourist_operator,price,department,authorized,title,description,vaccination,capacity,bookable) VALUES
(1,1000.00,1,1,'Titulo de experiencia','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque ut lacus quis libero dignissim euismod. Duis rhoncus risus eu risus consectetur sagittis. Nulla facilisi. Maecenas nec dui et turpis vulputate lobortis in eget enim. Morbi libero velit, bibendum ac erat et, rutrum gravida nibh. Maecenas bibendum varius fringilla. Sed imperdiet, mauris eget consequat gravida, sem lacus sagittis quam, eget efficitur sapien urna vitae lorem. Sed laoreet lacinia dui, sed vulputate sapien sollicitudin eget. Mauris sollicitudin ac elit at placerat.',1,'100',1);

CREATE TABLE `images` (
                          `id_images` int NOT NULL AUTO_INCREMENT,
                          `id_experience` int NOT NULL,
                          `image` blob NOT NULL,
                          PRIMARY KEY (`id_images`),
                          CONSTRAINT `fk_idexperience` FOREIGN KEY (`id_experience`) REFERENCES `experiences` (id)
);

CREATE TABLE `interests` (
                             `id_interests` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(45) NOT NULL,
                             PRIMARY KEY (`id_interests`),
                             UNIQUE KEY `name_UNIQUE` (`name`)
);

CREATE TABLE `types_of_experiences` (
                                        `id_types_of_experiences` int NOT NULL AUTO_INCREMENT,
                                        `name` varchar(45) NOT NULL,
                                        `interest` int NOT NULL,
                                        PRIMARY KEY (`id_types_of_experiences`),
                                        UNIQUE KEY `namet_UNIQUE` (`name`),
                                        CONSTRAINT `fk_types_of_experiences_interests` FOREIGN KEY (`interest`) REFERENCES interests(`id_interests`)

);

INSERT INTO tic1_db.interests (name) VALUES
('A'),
('B'),
('C'),
('D');

INSERT INTO tic1_db.types_of_experiences (name,interest) VALUES
('A',1),
('B',1),
('C',1),
('D',1),
('E',2),
('F',2),
('G',2);

CREATE TABLE `complaints` (
                              `id_complaints` int NOT NULL AUTO_INCREMENT,
                              `description` varchar(45) NOT NULL,
                              `date` datetime NOT NULL,
                              `experience_id` int NOT NULL,
                              `tourist_id` int NOT NULL,
                              PRIMARY KEY (`id_complaints`),
                              KEY `fk_exp_idx` (`experience_id`),
                              KEY `fk_tou_idx` (`tourist_id`),
                              CONSTRAINT `fk_expc` FOREIGN KEY (`experience_id`) REFERENCES `experiences` (id),
                              CONSTRAINT `fk_touc` FOREIGN KEY (`tourist_id`) REFERENCES `tourists` (`id_tourist`)
);

CREATE TABLE `tourist_interests` (
                                     `id_tourist` int NOT NULL,
                                     `id_interest` int NOT NULL,
                                     PRIMARY KEY (`id_tourist`,`id_interest`),
                                     KEY `fk_i_idx` (`id_interest`),
                                     KEY `fk_t_idx` (`id_tourist`),
                                     CONSTRAINT `fk_i` FOREIGN KEY (`id_interest`) REFERENCES `interests` (`id_interests`),
                                     CONSTRAINT `fk_t` FOREIGN KEY (`id_tourist`) REFERENCES `tourists` (`id_tourist`)
);

CREATE TABLE `types_experiences` (
                                     `id_experience` int NOT NULL,
                                     `id_type_of_experience` int NOT NULL,
                                     PRIMARY KEY (`id_experience`,`id_type_of_experience`),
                                     KEY `fk_exp_idx` (`id_experience`),
                                     KEY `fk_type_idx` (`id_type_of_experience`),
                                     CONSTRAINT `fk_exp` FOREIGN KEY (`id_experience`) REFERENCES `experiences` (id),
                                     CONSTRAINT `fk_type` FOREIGN KEY (`id_type_of_experience`) REFERENCES `types_of_experiences` (`id_types_of_experiences`)
);

INSERT INTO tic1_db.types_experiences (id_experience,id_type_of_experience) VALUES
(1,2);

CREATE TABLE `reviews` (
                           `id_reviews` int NOT NULL AUTO_INCREMENT,
                           `comment` varchar(45) NOT NULL,
                           `rating` varchar(45) NOT NULL,
                           `experience_id` int NOT NULL,
                           `tourist_id` int NOT NULL,
                           PRIMARY KEY (`id_reviews`),
                           KEY `fk_exp_idx` (`experience_id`),
                           KEY `fk_tou_idx` (`tourist_id`),
                           CONSTRAINT `fk_expr` FOREIGN KEY (`experience_id`) REFERENCES `experiences` (id),
                           CONSTRAINT `fk_tour` FOREIGN KEY (`tourist_id`) REFERENCES `tourists` (`id_tourist`)
);

## CREATE TABLE 'attendance' ();

CREATE USER 'springuser'@'%' identified by 'ThePassword';
GRANT ALL on tic1_db.* to 'springuser'@'%';