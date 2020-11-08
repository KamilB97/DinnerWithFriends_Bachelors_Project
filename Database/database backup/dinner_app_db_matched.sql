-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dinner_app_db
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `matched`
--

DROP TABLE IF EXISTS `matched`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matched` (
  `profile1` int(10) NOT NULL,
  `profile2` int(10) NOT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `conversation_id` int(11) NOT NULL,
  PRIMARY KEY (`profile1`,`profile2`),
  KEY `profile2` (`profile2`),
  KEY `conversation_id` (`conversation_id`),
  CONSTRAINT `matched_ibfk_1` FOREIGN KEY (`profile1`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `matched_ibfk_2` FOREIGN KEY (`profile2`) REFERENCES `profile` (`profile_id`),
  CONSTRAINT `matched_ibfk_3` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matched`
--

LOCK TABLES `matched` WRITE;
/*!40000 ALTER TABLE `matched` DISABLE KEYS */;
INSERT INTO `matched` VALUES (4,1,'2019-08-31 18:00:00',29),(16,1,'2019-08-31 18:00:00',28),(38,3,'2019-08-15 14:00:00',18),(38,4,'2019-08-15 14:00:00',19),(38,5,'2019-08-15 14:00:00',20),(38,6,'2019-08-15 14:00:00',23),(38,7,'2019-08-15 14:00:00',24),(38,8,'2019-08-15 14:00:00',25),(38,9,'2019-08-15 14:00:00',26),(38,11,'2019-08-15 14:00:00',16),(38,13,'2019-08-15 14:00:00',21),(38,22,'2019-08-15 14:00:00',17),(38,23,'2019-08-15 14:00:00',22),(38,35,'2019-08-31 18:00:00',51),(38,37,'2019-08-31 18:00:00',50),(43,27,'2019-08-31 18:00:00',66),(45,38,'2019-08-31 18:00:00',67);
/*!40000 ALTER TABLE `matched` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 13:42:57
