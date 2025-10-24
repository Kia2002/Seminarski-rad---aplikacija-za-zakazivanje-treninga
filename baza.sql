/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.18 : Database - projekat
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`projekat` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `projekat`;

/*Table structure for table `evidencijatreninga` */

DROP TABLE IF EXISTS `evidencijatreninga`;

CREATE TABLE `evidencijatreninga` (
  `idEvidencijaTreninga` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `ukupnaCena` bigint(255) unsigned NOT NULL,
  `idKlijent` bigint(255) unsigned NOT NULL,
  `idTrener` bigint(255) unsigned NOT NULL,
  PRIMARY KEY (`idEvidencijaTreninga`),
  KEY `idKlijent` (`idKlijent`),
  KEY `idTrener` (`idTrener`),
  CONSTRAINT `evidencijatreninga_ibfk_1` FOREIGN KEY (`idKlijent`) REFERENCES `klijent` (`idKlijent`),
  CONSTRAINT `evidencijatreninga_ibfk_2` FOREIGN KEY (`idTrener`) REFERENCES `trener` (`idTrener`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `evidencijatreninga` */

insert  into `evidencijatreninga`(`idEvidencijaTreninga`,`ukupnaCena`,`idKlijent`,`idTrener`) values 
(76,7600,1,3),
(77,1200,71,3);

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `idKlijent` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prezime` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idNivoFizickeSpreme` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`idKlijent`),
  KEY `idNivoFizickeSpreme` (`idNivoFizickeSpreme`),
  CONSTRAINT `klijent_ibfk_1` FOREIGN KEY (`idNivoFizickeSpreme`) REFERENCES `nivofizickespreme` (`idNivoFizickeSpreme`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`idKlijent`,`ime`,`prezime`,`email`,`idNivoFizickeSpreme`) values 
(1,'Aleksa','Vlaski','aki@gmail.com',4),
(71,'Stefan','Jovic','stefan@gmail.com',5),
(79,'Kosta','Kostic','stefan12@gmail.com',5);

/*Table structure for table `nivofizickespreme` */

DROP TABLE IF EXISTS `nivofizickespreme`;

CREATE TABLE `nivofizickespreme` (
  `idNivoFizickeSpreme` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `nivo` varchar(100) NOT NULL,
  `opis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idNivoFizickeSpreme`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `nivofizickespreme` */

insert  into `nivofizickespreme`(`idNivoFizickeSpreme`,`nivo`,`opis`) values 
(1,'nizak','Najnizi nivo fizicke spreme'),
(2,'srednji','Srednji nivo fizicke spreme'),
(3,'visok','Visi nivo fizicke spreme'),
(4,'najvisi','Najvisi nivo fizicke spreme'),
(5,'početnik','Čovek teg u životu nije podigao..'),
(9,'Prejaki','Prejaki covek najjaci na svetu');

/*Table structure for table `sertifikat` */

DROP TABLE IF EXISTS `sertifikat`;

CREATE TABLE `sertifikat` (
  `idSertifikat` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `opis` varchar(255) NOT NULL,
  PRIMARY KEY (`idSertifikat`),
  UNIQUE KEY `unique_naziv` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sertifikat` */

insert  into `sertifikat`(`idSertifikat`,`naziv`,`opis`) values 
(1,'NASM','National Academy of Sports Medicine'),
(2,'ACE','American Council on Exercise'),
(3,'NSCA','National Strength and Conditioning Association'),
(4,'ISSA','International Sports Sciences Association'),
(5,'ACSM','American College of Sports Medicine'),
(29,'NBA','Kosarka'),
(30,'FIFA','Football');

/*Table structure for table `stavkaevidencijetreninga` */

DROP TABLE IF EXISTS `stavkaevidencijetreninga`;

CREATE TABLE `stavkaevidencijetreninga` (
  `idEvidencijaTreninga` bigint(255) unsigned NOT NULL,
  `rb` bigint(255) unsigned NOT NULL,
  `ocena` bigint(2) unsigned NOT NULL,
  `vremeOd` time(6) NOT NULL,
  `vremeDo` time(6) NOT NULL,
  `cena` bigint(100) unsigned NOT NULL,
  `idTermin` bigint(255) unsigned NOT NULL,
  PRIMARY KEY (`idEvidencijaTreninga`,`rb`),
  KEY `idTermin` (`idTermin`),
  CONSTRAINT `stavkaevidencijetreninga_ibfk_1` FOREIGN KEY (`idEvidencijaTreninga`) REFERENCES `evidencijatreninga` (`idEvidencijaTreninga`),
  CONSTRAINT `stavkaevidencijetreninga_ibfk_2` FOREIGN KEY (`idTermin`) REFERENCES `termin` (`idTermin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkaevidencijetreninga` */

insert  into `stavkaevidencijetreninga`(`idEvidencijaTreninga`,`rb`,`ocena`,`vremeOd`,`vremeDo`,`cena`,`idTermin`) values 
(76,1,1,'12:00:00.000000','13:00:00.000000',1200,1),
(76,3,1,'09:00:00.000000','10:00:00.000000',1200,1),
(76,4,1,'21:00:00.000000','22:00:00.000000',1200,1),
(76,5,1,'19:00:00.000000','21:00:00.000000',2600,22),
(76,7,1,'12:00:00.000000','13:00:00.000000',1400,24),
(77,1,1,'12:00:00.000000','13:00:00.000000',1200,1);

/*Table structure for table `termin` */

DROP TABLE IF EXISTS `termin`;

CREATE TABLE `termin` (
  `idTermin` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `datum` date NOT NULL,
  `cenaPoSatu` bigint(255) unsigned NOT NULL,
  PRIMARY KEY (`idTermin`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `termin` */

insert  into `termin`(`idTermin`,`datum`,`cenaPoSatu`) values 
(1,'2025-02-10',1200),
(22,'2025-03-10',1300),
(23,'2025-04-10',1350),
(24,'2025-05-10',1400);

/*Table structure for table `trener` */

DROP TABLE IF EXISTS `trener`;

CREATE TABLE `trener` (
  `idTrener` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `ime` varchar(100) NOT NULL,
  `prezime` varchar(100) NOT NULL,
  `korisnickoIme` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sifra` varchar(100) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idTrener`),
  UNIQUE KEY `unique_email` (`email`),
  UNIQUE KEY `unique_korisnickoIme` (`korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `trener` */

insert  into `trener`(`idTrener`,`ime`,`prezime`,`korisnickoIme`,`sifra`,`email`) values 
(1,'Marko','Markovic','marko123','marko123','marko@gmail.com'),
(2,'Nikola','Nikolic','nikola123','nikola123','nikola@gmail.com'),
(3,'Aleksa','Aleksic','aleksa123','aleksa123','aleksa@gmail.com'),
(4,'Filip','Filipovic','filip123','filip123','filip@gmail.com');

/*Table structure for table `ts` */

DROP TABLE IF EXISTS `ts`;

CREATE TABLE `ts` (
  `idTrener` bigint(255) unsigned NOT NULL,
  `idSertifikat` bigint(255) unsigned NOT NULL,
  `datum` date NOT NULL,
  PRIMARY KEY (`idTrener`,`idSertifikat`),
  KEY `idSertifikat` (`idSertifikat`),
  CONSTRAINT `ts_ibfk_1` FOREIGN KEY (`idTrener`) REFERENCES `trener` (`idTrener`),
  CONSTRAINT `ts_ibfk_2` FOREIGN KEY (`idSertifikat`) REFERENCES `sertifikat` (`idSertifikat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ts` */

insert  into `ts`(`idTrener`,`idSertifikat`,`datum`) values 
(1,4,'2019-04-25'),
(2,1,'2023-10-10'),
(2,4,'2024-04-24'),
(3,2,'2020-10-07'),
(3,3,'2022-06-22'),
(4,5,'2024-06-16');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
