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
-- Table structure for table `conversation`
--

DROP TABLE IF EXISTS `conversation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversation` (
  `conversation_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `custom_created` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`conversation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversation`
--

LOCK TABLES `conversation` WRITE;
/*!40000 ALTER TABLE `conversation` DISABLE KEYS */;
INSERT INTO `conversation` VALUES (1,'conversation name 1','2019-08-15 12:00:00',0),(2,'conversation name 2','2019-08-15 12:00:00',0),(3,'name 3','2019-08-15 14:00:00',0),(4,'name 4','2019-08-15 14:00:00',0),(5,'name 5','2019-08-15 14:00:00',0),(6,'name 6','2019-08-15 14:00:00',0),(7,'name 7','2019-08-15 14:00:00',0),(8,'name 8','2019-08-15 14:00:00',0),(9,'name 9','2019-08-15 14:00:00',0),(10,'name 10','2019-08-15 14:00:00',0),(11,'name 11','2019-08-15 14:00:00',0),(12,'name 12','2019-08-15 14:00:00',0),(13,'name 13','2019-08-15 14:00:00',0),(14,'name 14','2019-08-15 14:00:00',0),(15,'name 15','2019-08-15 14:00:00',0),(16,'name 16','2019-08-15 14:00:00',0),(17,'name 17','2019-08-15 14:00:00',0),(18,'name 18','2019-08-15 14:00:00',0),(19,'name 19','2019-08-15 14:00:00',0),(20,'name 20','2019-08-15 14:00:00',0),(21,'name 21','2019-08-15 14:00:00',0),(22,'name 22','2019-08-15 14:00:00',0),(23,'name 18','2019-08-15 14:00:00',0),(24,'name 19','2019-08-15 14:00:00',0),(25,'name 20','2019-08-15 14:00:00',0),(26,'name 21','2019-08-15 14:00:00',0),(27,'name 22','2019-08-15 14:00:00',0),(28,'przykladowa nazwa','2019-08-15 14:00:00',0),(29,'przykladowa nazwa','2019-08-15 14:00:00',0),(32,'Miłośnicy kuchni włoskiej',NULL,1),(33,'Pasjonaci sportu',NULL,1),(34,'jeszczeCiekawszaNazwa',NULL,1),(35,'Wielbiciele poezji',NULL,1),(36,'MeAndTheBoys',NULL,1),(37,'Szlachta',NULL,1),(38,'Akka',NULL,1),(39,'Urodzinki',NULL,1),(40,'Zarząd Wioski',NULL,1),(50,'przykladowa nazwa',NULL,NULL),(51,'przykladowa nazwa',NULL,NULL),(66,'przykladowa nazwa',NULL,NULL),(67,'przykladowa nazwa',NULL,NULL);
/*!40000 ALTER TABLE `conversation` ENABLE KEYS */;
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
