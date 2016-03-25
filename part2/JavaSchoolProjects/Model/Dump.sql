CREATE DATABASE  IF NOT EXISTS `shoponlinejpa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shoponlinejpa`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: shoponlinejpa
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `completed` tinyint(1) NOT NULL,
  `user` bigint(20) DEFAULT NULL,
  `deliveryMethod` varchar(255) DEFAULT NULL,
  `payMethod` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_18isiyqe7rke5ss0samkhinpp` (`user`),
  CONSTRAINT `FK_18isiyqe7rke5ss0samkhinpp` FOREIGN KEY (`user`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,1,3,'cash','cash'),(2,0,15,NULL,NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cartitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `booking` bigint(20) DEFAULT NULL,
  `product` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h3wam91pn3o604nbrt7qgvi28` (`booking`),
  KEY `FK_gfl1nq6c44mn6u0iybegu3l5g` (`product`),
  CONSTRAINT `FK_gfl1nq6c44mn6u0iybegu3l5g` FOREIGN KEY (`product`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_h3wam91pn3o604nbrt7qgvi28` FOREIGN KEY (`booking`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cartitem`
--

LOCK TABLES `cartitem` WRITE;
/*!40000 ALTER TABLE `cartitem` DISABLE KEYS */;
INSERT INTO `cartitem` VALUES (2,9,2,6),(3,1,2,2);
/*!40000 ALTER TABLE `cartitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthDay` varchar(255) DEFAULT NULL,
  `birthMonth` varchar(255) DEFAULT NULL,
  `birthYear` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (2,'20','February','1991','July@gmail.com','Joulianna','9fff14d9451ea0541eaa1c486ef73f6f','Martin',NULL),(3,'17','April','1987','George@gmail.com','George','37ca0c55e87ea29d025d6514c206a518','Clay',NULL),(6,NULL,NULL,NULL,'newemail@gmail.com',NULL,'5f4dcc3b5aa765d61d8327deb882cf99',NULL,'Client'),(7,'17','January','1995','TestUser@gmail.com','TestName','61e88c8bcfe600c152156198c20b917e','TestSurname','Employee'),(8,'17','January','1995','TestUser@gmail.com','TestName','61e88c8bcfe600c152156198c20b917e','TestSurname','Employee'),(9,'1','January','1441','e@seefasd.com','','a5c27ec0eb953a85751f53cb065aa05e','','Client'),(10,'1','January','1111','test@fgf.com','','9613ed2252b4fc94dcdb934b305280b8','','Client'),(11,'1','January','1111','fsfsdf@sdfsdf.com','','26460f32a3164e6382436aba45eaf862','','Client'),(12,'2','January','2222','saad@asdad.ru','','a4608b9ec930833c5015dcb9176a29f5','','Client'),(13,'1','January','1222','test@gmail.com','','b5b037a78522671b89a2c1b21d9b80c6','','Client'),(14,'1','January','1111','ererte@gmail.com','','b5b037a78522671b89a2c1b21d9b80c6','','Client'),(15,'13','January','1111','Rose@gmail.com','Rose','070dc80e78b2b566de78a61371deebba','RoseSurname','Employee'),(16,'1','January','1111','test@gmail.com','ererwr','fd6cde7c2f4913f22297c948dd530c84','','Client');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `volume` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,5,'landscape','Philip Barlow','blurred people',3000,'360 x 180','2 kg'),(2,1,'impress','Magritte','bird in the sky',2000,'450 x 220','3 kg'),(6,1,'surr','Dali','flower',5000,'480 x 250','5 kg'),(8,6,'realism','ValentinGubarev','man',2000,'380 x 120','3 kg'),(9,3,'realism','Gerhard Glück','man',1000,'360 x 150','2 kg'),(10,2,'surr','Dali','book',3000,'220 x 130','1 kg'),(11,5,'landscape','Joe  Dowden','forest',5000,'350 x 480','5 kg'),(12,7,'stiil life','Кelvin Lei','women',7000,'230 x 110','2 kg'),(13,3,'still life','Laurent Parcelier','house',2000,'450 x 220','3 kg'),(14,4,'surr','Magritte','eye in the sky',1000,'380 x 120','2 kg'),(15,8,'landscape','Manfred Rapp','house',5000,'480 x 250','5 kg'),(16,4,'realism','Michael Sowa','man',3000,'220 x 130','3 kg'),(17,NULL,NULL,'TestProduct',NULL,NULL,NULL,NULL),(18,NULL,NULL,'TestProduct',NULL,NULL,NULL,NULL),(19,NULL,NULL,'TestProduct',NULL,NULL,NULL,NULL),(20,NULL,NULL,'TestProduct',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Employee'),(2,'Client'),(3,'Employee'),(4,'Client'),(5,'Employee'),(6,'Client'),(7,'Employee'),(8,'Client'),(9,'Employee'),(10,'Client'),(11,'Employee'),(12,'Client'),(13,'Employee'),(14,'Client'),(15,'Employee'),(16,'Client'),(17,'Employee'),(18,'Client'),(19,'Employee'),(20,'Client'),(21,'Employee'),(22,'Client'),(23,'Employee'),(24,'Client'),(25,'Employee'),(26,'Client'),(27,'Employee'),(28,'Client'),(29,'Employee'),(30,'Client'),(31,'Employee'),(32,'Employee'),(33,'Employee'),(34,'Employee'),(35,'Employee'),(36,'Employee'),(37,'Employee'),(38,'Employee'),(39,'Employee'),(40,'Employee'),(41,'Employee');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-25 22:57:33
