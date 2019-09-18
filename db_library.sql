-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `idbook` int(11) NOT NULL AUTO_INCREMENT,
  `idcategory` int(11) NOT NULL,
  `name_book` varchar(100) DEFAULT NULL,
  `time_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `time_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enable` int(11) DEFAULT '1',
  PRIMARY KEY (`idbook`),
  KEY `book_category_idx` (`idcategory`),
  CONSTRAINT `book_category` FOREIGN KEY (`idcategory`) REFERENCES `category` (`idcategory`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,1,'Random Story Title Generator 2.0','2019-09-17 04:31:04','2019-09-18 04:51:07',2),(2,1,'Fantasy Name Generator','2019-09-17 04:31:04','2019-09-18 04:48:14',2),(3,1,'Reedsy Fantasy Title Generator','2019-09-17 04:31:04','2019-09-18 04:47:21',1),(4,1,'Random Fantasy Novel Title Generator ','2019-09-17 04:31:04','2019-09-18 04:47:21',1),(5,1,'Serendipity Fantasy Novel Generator','2019-09-17 04:31:04','2019-09-18 04:47:21',1),(6,1,'Writing Exercises Story Title Ideas','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(7,1,'Fantasy Name Generator by RinkWorks','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(8,2,'Random Story Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(9,2,'Pulp Sci-fi Title-O-Tron','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(10,2,'Reedsy Sci-Fi Book Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(11,2,'Random Sci-fi Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(12,2,'Space Adventure Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(13,3,'Random Story Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(14,3,'Fantasy Name Generator ','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(15,3,'Generator Land’s Horror Titles','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(16,3,'Writing Exercises Story Title Ideas','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(17,4,'Random Romance Novel Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(18,4,'Reedsy Romance Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(19,4,'Kitt Net’s Book Title Romance','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(20,4,'Randomly Generated Titles','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(21,4,'Amaze Story Titles','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(22,4,'Fantasy Name Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(23,5,'Reedsy Mystery Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(24,5,'Starman Series Mystery Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(25,5,'Story Toolz Half Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(26,6,'Reedsy Crime Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(27,6,'Mad Lib Thriller Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(28,6,'Amaze Story Titles','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(29,7,'Portent’s Content Idea Generator ','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(30,7,'Adazing’s Book Title Creator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(31,7,'Kopy Writing Kourse Book Name Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(32,8,'Fantasy Name Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(33,8,'Story Title Ideas for Kids Books','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(34,9,'Rum and Monkey’s Comic Book Name Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(35,9,'Ruggen Berg’s Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(36,9,'Super Hero Name Gens','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(37,10,'Autobiography Title Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1),(38,10,'Book Character Name Generator','2019-09-17 04:31:04','2019-09-17 04:31:04',1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookbrow`
--

DROP TABLE IF EXISTS `bookbrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bookbrow` (
  `idbook_brow` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) DEFAULT NULL,
  `idbook` int(11) DEFAULT NULL,
  `start_brow` timestamp NULL DEFAULT NULL,
  `end_brow` timestamp NULL DEFAULT NULL,
  `time_start` timestamp NULL DEFAULT NULL,
  `time_end` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enable` int(11) DEFAULT '1',
  PRIMARY KEY (`idbook_brow`),
  KEY `user_brow_idx` (`iduser`),
  CONSTRAINT `user_brow` FOREIGN KEY (`iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookbrow`
--

LOCK TABLES `bookbrow` WRITE;
/*!40000 ALTER TABLE `bookbrow` DISABLE KEYS */;
INSERT INTO `bookbrow` VALUES (3,4,2,'2019-09-17 04:31:04','2019-09-17 14:28:04','2019-09-18 04:31:04','2019-09-18 04:49:58',2),(4,4,1,'2019-09-17 04:31:04','2019-09-18 14:28:04',NULL,'2019-09-18 04:50:57',1);
/*!40000 ALTER TABLE `bookbrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `idcategory` int(11) NOT NULL AUTO_INCREMENT,
  `name_category` varchar(45) NOT NULL,
  `time_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `time_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enable` int(11) DEFAULT '1',
  PRIMARY KEY (`idcategory`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Fantasy','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(2,'Sci-Fi','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(3,'Horror','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(4,'Romance','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(5,'Mystery Novel','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(6,'Crime','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(7,'Non-Fiction','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(8,'Children’s Book','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(9,'Comic Book','2019-09-17 04:23:22','2019-09-17 04:23:22',1),(10,'Other Types','2019-09-17 04:23:22','2019-09-17 04:23:22',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` longtext NOT NULL,
  `role` varchar(45) DEFAULT 'ROLE_USER',
  `gender` bit(1) DEFAULT b'0',
  `dob` date DEFAULT NULL,
  `time_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `time_update` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enable` int(11) DEFAULT '1',
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'Nguyễn Đức Long','long','$2a$10$e8JYwttShh2Tjpba/Y6Upei5DHUt0rnr40eHwad2iS43CmkWiRHGi','ROLE_USER',_binary '','2019-01-01','2019-09-17 02:49:12','2019-09-17 02:49:12',1),(5,'Nam 123','Nmam','$2a$10$dCMglnkR.Af0BXidVfYYQOl15jCWNYg6qezCWXLAU4nLfzDSM5Fpi','ROLE_USER',_binary '','2019-01-02','2019-09-17 07:55:57','2019-09-17 07:58:16',1);
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

-- Dump completed on 2019-09-18 13:55:38
