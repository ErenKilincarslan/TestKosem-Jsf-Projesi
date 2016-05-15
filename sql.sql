-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.11-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema testkosem
--

CREATE DATABASE IF NOT EXISTS testkosem;
USE testkosem;

--
-- Definition of table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
CREATE TABLE `kategori` (
  `kategoriid` int(11) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`kategoriid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kategori`
--

/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` (`kategoriid`,`kategori`) VALUES 
 (8,'Matematik');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;


--
-- Definition of table `kullanici`
--

DROP TABLE IF EXISTS `kullanici`;
CREATE TABLE `kullanici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adi` varchar(45) DEFAULT NULL,
  `soyadi` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `kullaniciadi` varchar(45) DEFAULT NULL,
  `sifre` varchar(45) DEFAULT NULL,
  `yetki` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kullanici`
--

/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` (`id`,`adi`,`soyadi`,`email`,`kullaniciadi`,`sifre`,`yetki`) VALUES 
 (1,'1','1','1','1','1',2);
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;


--
-- Definition of table `soru`
--

DROP TABLE IF EXISTS `soru`;
CREATE TABLE `soru` (
  `soruid` int(11) NOT NULL AUTO_INCREMENT,
  `testid` int(11) NOT NULL,
  `soru` varchar(500) NOT NULL,
  `cevap1` varchar(200) NOT NULL,
  `cevap2` varchar(200) NOT NULL,
  `cevap3` varchar(200) NOT NULL,
  `cevap4` varchar(200) NOT NULL,
  `dogrucevap` int(11) unsigned NOT NULL,
  PRIMARY KEY (`soruid`),
  KEY `FK_soru_1` (`testid`),
  CONSTRAINT `FK_soru_1` FOREIGN KEY (`testid`) REFERENCES `test` (`testid`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `soru`
--

/*!40000 ALTER TABLE `soru` DISABLE KEYS */;
INSERT INTO `soru` (`soruid`,`testid`,`soru`,`cevap1`,`cevap2`,`cevap3`,`cevap4`,`dogrucevap`) VALUES 
 (120,15,'2*2*2','2','4','5','8',4),
 (121,15,'kök 9','9','1','2','3',4);
/*!40000 ALTER TABLE `soru` ENABLE KEYS */;


--
-- Definition of table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `testid` int(11) NOT NULL AUTO_INCREMENT,
  `kategoriid` int(11) DEFAULT NULL,
  PRIMARY KEY (`testid`),
  KEY `FK_test_1` (`kategoriid`),
  CONSTRAINT `FK_test_1` FOREIGN KEY (`kategoriid`) REFERENCES `kategori` (`kategoriid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `test`
--

/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`testid`,`kategoriid`) VALUES 
 (15,8);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
