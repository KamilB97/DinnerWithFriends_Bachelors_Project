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
-- Table structure for table `profile_dietarypreference`
--

DROP TABLE IF EXISTS `profile_dietarypreference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_dietarypreference` (
  `profile_dietary_id` int(10) NOT NULL AUTO_INCREMENT,
  `profile_id` int(10) NOT NULL,
  `diet_id` int(10) NOT NULL,
  PRIMARY KEY (`profile_dietary_id`),
  KEY `profile_id` (`profile_id`),
  KEY `diet_id` (`diet_id`),
  CONSTRAINT `profile_dietarypreference_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`) ON DELETE CASCADE,
  CONSTRAINT `profile_dietarypreference_ibfk_2` FOREIGN KEY (`diet_id`) REFERENCES `dietarypreference` (`diet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_dietarypreference`
--

LOCK TABLES `profile_dietarypreference` WRITE;
/*!40000 ALTER TABLE `profile_dietarypreference` DISABLE KEYS */;
INSERT INTO `profile_dietarypreference` VALUES (1,1,2),(2,1,3),(3,2,1),(4,2,3),(5,3,3),(6,3,4),(7,4,3),(8,4,4),(9,5,3),(10,5,4),(11,6,1),(12,7,1),(13,8,1),(14,9,1),(15,10,2),(16,11,2),(17,12,2),(18,13,6),(19,14,3),(20,14,6),(21,15,3),(22,15,6),(23,16,3),(24,16,6),(25,17,2),(26,18,3),(27,19,3),(28,19,4),(29,20,3),(30,20,4),(31,21,3),(32,21,4),(33,22,1),(34,22,3),(35,23,1),(36,23,3),(37,24,1),(38,24,3),(39,25,2),(40,25,3),(41,26,2),(42,26,3),(43,27,6),(44,28,6),(45,29,5),(46,30,1),(47,30,5),(52,33,2),(53,33,4),(54,34,2),(55,34,4),(56,35,1),(57,35,3),(58,36,1),(59,36,4),(60,37,1),(61,37,5),(65,39,1),(66,39,3),(67,39,5),(133,32,1),(134,32,2),(174,38,1),(175,38,2),(176,38,3),(177,31,1),(178,31,3),(179,31,5),(180,41,2),(181,41,3),(182,41,4),(183,43,1),(184,43,2),(185,43,6),(186,45,2),(187,45,3),(188,48,3),(189,48,4),(190,48,5),(191,49,2),(192,49,3),(193,49,5);
/*!40000 ALTER TABLE `profile_dietarypreference` ENABLE KEYS */;
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
