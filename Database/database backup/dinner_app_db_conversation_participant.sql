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
-- Table structure for table `conversation_participant`
--

DROP TABLE IF EXISTS `conversation_participant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation_participant` (
  `conversation_participant_id` int(11) NOT NULL AUTO_INCREMENT,
  `conversation_id` int(11) NOT NULL,
  `profile_id` int(11) NOT NULL,
  `active` int(11) NOT NULL,
  PRIMARY KEY (`conversation_participant_id`),
  KEY `conversation_id` (`conversation_id`),
  KEY `profile_id` (`profile_id`),
  CONSTRAINT `conversation_participant_ibfk_1` FOREIGN KEY (`conversation_id`) REFERENCES `conversation` (`conversation_id`),
  CONSTRAINT `conversation_participant_ibfk_2` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation_participant`
--

LOCK TABLES `conversation_participant` WRITE;
/*!40000 ALTER TABLE `conversation_participant` DISABLE KEYS */;
INSERT INTO `conversation_participant` VALUES (1,3,38,2),(2,3,31,2),(3,4,38,2),(4,4,18,2),(5,5,38,2),(6,5,10,2),(7,6,38,2),(8,6,20,2),(9,7,38,2),(10,7,25,2),(11,9,25,2),(13,9,38,2),(14,9,27,2),(15,10,38,2),(16,10,28,2),(17,11,38,2),(18,11,29,2),(19,12,38,2),(20,12,30,2),(21,13,38,2),(22,13,31,2),(23,14,38,2),(24,14,32,2),(25,15,38,2),(26,15,33,2),(27,28,1,2),(28,28,16,2),(29,29,1,2),(30,29,4,2),(31,32,10,2),(32,32,38,2),(33,32,15,2),(34,33,22,2),(35,33,38,2),(36,33,18,2),(37,34,31,2),(38,34,20,2),(39,34,11,2),(40,35,18,2),(41,35,11,2),(42,35,29,2),(43,35,38,2),(44,36,19,2),(45,36,20,2),(46,36,38,2),(47,37,28,2),(48,37,19,2),(49,37,20,2),(50,37,38,2),(51,38,19,2),(52,38,18,2),(53,38,38,2),(54,39,20,2),(55,39,33,2),(56,39,38,2),(57,40,19,2),(58,40,32,2),(59,40,31,2),(60,40,38,2),(61,20,38,2),(62,20,5,2),(71,50,37,2),(72,50,38,2),(73,51,35,2),(74,51,38,2),(87,37,12,2),(88,37,22,2),(89,37,29,2),(98,38,20,2),(101,66,27,2),(102,66,43,2),(103,67,38,2),(104,67,45,2);
/*!40000 ALTER TABLE `conversation_participant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 13:42:59
