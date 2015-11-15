CREATE DATABASE  IF NOT EXISTS `cobsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cobsdb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: cobsdb
-- ------------------------------------------------------
-- Server version	5.6.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `advertisements`
--

DROP TABLE IF EXISTS `advertisements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertisements` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AvailableFrom` datetime DEFAULT NULL,
  `AvailableTill` datetime DEFAULT NULL,
  `BookedBy` varchar(255) DEFAULT NULL,
  `Length` int(11) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `Owner` varchar(255) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  `Type` varchar(255) DEFAULT NULL,
  `Width` int(11) DEFAULT NULL,
  `publisherId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_okff507rjcj6hdj4b609qqk0w` (`publisherId`),
  CONSTRAINT `FK_okff507rjcj6hdj4b609qqk0w` FOREIGN KEY (`publisherId`) REFERENCES `publisher` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisements`
--

LOCK TABLES `advertisements` WRITE;
/*!40000 ALTER TABLE `advertisements` DISABLE KEYS */;
INSERT INTO `advertisements` VALUES (1,'2015-12-08 00:00:00',NULL,'bhanusingh',500,'mumbai','rutuja',500,'','Banner',500,2),(2,'2015-05-20 00:00:00','2015-08-21 00:00:00',NULL,500,'mumbai','rutuja',250,'','Banner',500,2),(3,'2015-05-16 00:00:00',NULL,'bhanusingh',1000,'boston','rutuja',250,'','Banner',1000,2),(5,'2012-01-01 00:00:00','2012-01-02 00:00:00',NULL,500,'delhi','rutuja',300,'\0','Glowsign',500,2);
/*!40000 ALTER TABLE `advertisements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertiser`
--

DROP TABLE IF EXISTS `advertiser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertiser` (
  `CompanyName` varchar(255) DEFAULT NULL,
  `TotalAdBooked` int(11) DEFAULT NULL,
  `Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK_9l9e30gaeyh11qbr0wbya27n` FOREIGN KEY (`Id`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertiser`
--

LOCK TABLES `advertiser` WRITE;
/*!40000 ALTER TABLE `advertiser` DISABLE KEYS */;
INSERT INTO `advertiser` VALUES (NULL,0,3);
/*!40000 ALTER TABLE `advertiser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `advertisementId` int(11) DEFAULT NULL,
  `FromBookedDate` datetime DEFAULT NULL,
  `publisherId` int(11) DEFAULT NULL,
  `TillBookedDate` datetime DEFAULT NULL,
  `advertiserId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_igb4wbpaj9ku1psxqtl3omrpm` (`advertiserId`),
  CONSTRAINT `FK_igb4wbpaj9ku1psxqtl3omrpm` FOREIGN KEY (`advertiserId`) REFERENCES `advertiser` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,'2015-04-24 03:02:14',2,'2015-12-01 00:00:00',3),(2,3,'2015-04-25 00:00:00',2,'2015-05-09 00:00:00',3),(3,3,'2015-05-09 00:00:00',2,'2015-05-16 00:00:00',3),(4,1,'2015-12-01 00:00:00',2,'2015-12-08 00:00:00',3);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publisher` (
  `TotalAdPosted` int(11) DEFAULT NULL,
  `Id` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `FK_o0f9p9s8gpdykuhr83rsmmarw` FOREIGN KEY (`Id`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (0,2);
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `confirmPassword` varchar(255) DEFAULT NULL,
  `contactnumber` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(15) NOT NULL,
  `registrationstatus` bit(1) DEFAULT NULL,
  `subscriptionTill` datetime DEFAULT NULL,
  `susbcriptionFrom` datetime DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Yash1991','6173140545','yashscool@gmail.com','yash','Yash1991','',NULL,NULL,'Admin','admin'),(2,'123456','4567654345','rchahare@gmail.com','rutuja','123456','','2016-04-24 02:45:47','2015-04-24 02:45:47','Publisher','rutu'),(3,'123456','4565675649','b@c.com','bhanusingh','12345678','','2016-04-24 02:47:05','2015-04-24 02:47:05','Advertiser','bhanu');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-24 16:20:36
