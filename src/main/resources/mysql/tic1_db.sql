SET NAMES 'utf8';
CREATE SCHEMA `tic1_db` ;
CREATE TABLE `tic1_db`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
CREATE USER 'springuser'@'%' identified by 'ThePassword';
GRANT ALL on db_example.* to 'springuser'@'%';