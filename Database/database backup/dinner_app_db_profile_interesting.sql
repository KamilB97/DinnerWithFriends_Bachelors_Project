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
-- Table structure for table `profile_interesting`
--

DROP TABLE IF EXISTS `profile_interesting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile_interesting` (
  `profile_interesting_id` int(10) NOT NULL AUTO_INCREMENT,
  `profile_id` int(10) NOT NULL,
  `interesting_id` int(10) NOT NULL,
  PRIMARY KEY (`profile_interesting_id`),
  KEY `profile_id` (`profile_id`),
  KEY `interesting_id` (`interesting_id`),
  CONSTRAINT `profile_interesting_ibfk_1` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`) ON DELETE CASCADE,
  CONSTRAINT `profile_interesting_ibfk_2` FOREIGN KEY (`interesting_id`) REFERENCES `interesting` (`interesting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile_interesting`
--

LOCK TABLES `profile_interesting` WRITE;
/*!40000 ALTER TABLE `profile_interesting` DISABLE KEYS */;
INSERT INTO `profile_interesting` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,3),(5,3,1),(6,3,5),(7,4,2),(8,4,3),(9,5,2),(10,6,3),(11,7,4),(12,8,5),(13,9,5),(14,9,4),(15,10,5),(16,10,4),(17,11,5),(18,11,1),(19,12,1),(20,12,4),(21,13,1),(22,13,4),(23,14,1),(24,14,4),(25,15,1),(26,15,2),(27,16,1),(28,17,1),(29,18,1),(30,19,4),(31,20,4),(32,21,4),(33,22,4),(34,23,3),(35,24,3),(36,25,1),(37,25,3),(38,26,4),(39,26,3),(40,27,3),(41,27,2),(42,28,3),(43,28,2),(44,29,5),(45,29,4),(46,30,5),(49,33,5),(50,34,5),(51,35,5),(52,36,4),(53,36,3),(54,37,4),(55,37,2),(59,39,1),(60,39,4),(61,39,5),(126,32,1),(127,32,2),(161,38,4),(162,38,5),(163,31,1),(164,31,4),(165,31,5),(166,41,1),(167,41,3),(168,41,4),(169,43,2),(170,43,3),(171,43,4),(172,45,1),(173,45,2),(174,48,1),(175,48,3),(176,48,4),(177,49,1),(178,49,2),(179,49,3);
/*!40000 ALTER TABLE `profile_interesting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 13:42:58
