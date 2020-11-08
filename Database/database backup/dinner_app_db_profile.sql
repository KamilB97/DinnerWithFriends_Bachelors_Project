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
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `profile_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `image_id` int(10) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL DEFAULT 'nie ustawiono',
  `lastname` varchar(45) NOT NULL DEFAULT 'nie ustawiono',
  `gender` enum('male','female') DEFAULT NULL,
  `age` int(3) DEFAULT '0',
  `about` varchar(400) DEFAULT 'nie ustawiono',
  `update_date` timestamp NULL DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '2',
  `street` varchar(80) DEFAULT 'nie ustawiono',
  `longitude` varchar(45) DEFAULT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`profile_id`),
  KEY `image_id` (`image_id`),
  KEY `profile_ibfk_1` (`user_id`),
  CONSTRAINT `profile_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `profile_ibfk_2` FOREIGN KEY (`image_id`) REFERENCES `image` (`image_id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (1,1,16,'Camila','Jones','female',22,'I\'m Camile, I\'m very interesting person','2019-08-15 12:00:00',2,'Kamienna','17.033650','51.088500'),(2,2,17,'Stuart','Hamilton','male',22,'I\'m Stuard, I\'m very interesting person','2019-08-15 13:30:00',2,'Kamienna','17.033650','51.088500'),(3,3,47,'Alford','Harrison','male',22,'I\'m Alford, I\'m very interesting person','2019-08-15 14:00:00',2,'Ruska','17.026150','51.110420'),(4,4,18,'Dexter','Robinson','male',22,'I\'m Dexter Robinson, I\'m very interesting person','2019-08-15 12:00:00',2,'Rzepakowa','17.019280','51.058710'),(5,5,48,'Maya','Thomas','female',22,'I\'m Maya, I\'m very interesting person','2019-08-15 13:30:00',2,'Traugutta','17.050100','51.103370'),(6,6,19,'Hailey','Gray','male',31,'I\'m Hailey, I\'m very interesting person','2019-08-15 14:00:00',2,'Komuny Paryskiej','17.046540','51.101900'),(7,7,46,'Amelia','Moore','female',20,'I\'m Amelia, I\'m very interesting person','2019-08-15 12:00:00',2,'Rzeczna','17.123600','51.138180'),(8,8,49,'Aldus','Scott','male',33,'I\'m Aldus, I\'m very interesting person','2019-08-15 13:30:00',2,'Rzeźnicza','17.028450','51.111500'),(9,9,50,'Max','Carroll','male',33,'I\'m Max, I\'m very interesting person','2019-08-15 14:00:00',2,'Ryżowa','16.926870','51.126530'),(10,10,20,'Tyler','Spencer','male',32,'I\'m Tyler, I\'m very interesting person','2019-08-15 12:00:00',2,'Rysia','16.989820','51.125300'),(11,11,21,'Honey','Alexander','male',23,'I\'m Honey, I\'m very interesting person','2019-08-15 13:30:00',2,'Rysia','16.989820','51.125300'),(12,12,51,'Belinda','Stevens','female',43,'I\'m Belinda, I\'m very interesting person','2019-08-15 14:00:00',2,'Rybnicka','17.084640','51.080880'),(13,13,22,'Alina','Brown','female',24,'I\'m Alina, I\'m very interesting person','2019-08-15 12:00:00',2,'Gołębia','17.084390','51.134760'),(14,14,23,'Connie','Morrison','male',43,'I\'m Connie, I\'m very interesting person','2019-08-15 13:30:00',2,'Grabarska','17.025080','51.111090'),(15,15,24,'Lana','Myers','female',54,'I\'m Lana, I\'m very interesting person','2019-08-15 14:00:00',2,'Masztowa','17.127030','51.162070'),(16,16,25,'Harold','Howard','male',23,'I\'m Harold, I\'m very interesting person','2019-08-15 12:00:00',2,'Grabarska','17.025080','51.111090'),(17,17,26,'Alisa','Smith','female',31,'I\'m Alisa, I\'m very interesting person','2019-08-15 13:30:00',2,'Ryżowa','16.926870','51.126530'),(18,18,27,'Henry','Carroll','male',42,'I\'m Henry, I\'m very interesting person','2019-08-15 14:00:00',2,'Komuny Paryskiej','17.046540','51.101900'),(19,19,28,'Arianna','Parker','female',43,'I\'m Arianna, I\'m very interesting person','2019-08-15 12:00:00',2,'Wygodna','17.047280','51.123500'),(20,20,29,'Cherry','Johnson','female',41,'I\'m Cherry, I\'m very interesting person','2019-08-15 13:30:00',2,'Wschodnia','17.140940','51.114400'),(21,21,30,'Brian','Higgins','male',42,'I\'m Brian, I\'m very interesting person','2019-08-15 14:00:00',2,'Wrzosowa','16.984310','51.163060'),(22,22,52,'Dexter','Campbell','male',23,'I\'m Dexter Campbell, I\'m very interesting person','2019-08-15 12:00:00',2,'Rubinowa','17.021080','51.050200'),(23,23,31,'Madaline','Ross','female',23,'I\'m Madaline, I\'m very interesting person','2019-08-15 13:30:00',2,'Zajęcza','16.987640','51.127780'),(24,24,32,'Derek','Robinson','male',54,'I\'m Derek, I\'m very interesting person','2019-08-15 14:00:00',2,'Załęże','16.985680','51.191280'),(25,25,33,'Brianna','Cunningham','female',43,'I\'m Brianna, I\'m very interesting person','2019-08-15 12:00:00',2,'Morelowa','17.053820','51.079330'),(26,26,NULL,'Elise','Harrison','female',23,'I\'m Elise, I\'m very interesting person','2019-08-15 13:30:00',2,'Morska','16.949470','51.128220'),(27,27,34,'Alissa','Phillips','female',25,'I\'m Alissa, I\'m very interesting person','2019-08-15 12:00:00',2,'Morska','16.949470','51.128220'),(28,28,35,'Sophia','Phillips','female',41,'I\'m Sophia, I\'m very interesting person','2019-08-15 13:30:00',2,'Traugutta','17.050100','51.103370'),(29,29,36,'Maximilian','Williams','male',42,'I\'m Maximilian, I\'m very interesting person','2019-08-15 14:00:00',2,'Halana','16.999240','51.070900'),(30,30,37,'Samantha','Brooks','female',32,'I\'m Samantha, I\'m very interesting person','2019-08-15 12:00:00',2,'Halana','16.999240','51.070900'),(31,31,38,'Yao','Ming','male',22,'Cześć, jestem Yao Ming. Pochodzę z południowych Chin oraz studiuję na Politechnice Wrocławskiej. Pasjonuję się ekonomią a w szczególności mikroekonomią. ','2019-11-21 23:36:04',2,'Kamienna','17.033650','51.088500'),(32,32,39,'Kamil','Bryzcki','male',32,'I\'m Kamil, I\'m very interesting person','2019-10-28 00:15:28',2,'Gorlicka','17.118760','51.143980'),(33,33,53,'Lilianna','Bailey','female',28,'I\'m Lianna, I\'m very interesting person','2019-08-15 12:00:00',2,'Sępia','17.005260','51.080010'),(34,34,40,'Reid','Armstrong','male',22,'I\'m Reid Armstrong, I\'m very interesting person','2019-08-15 13:30:00',2,'Zdrowa','17.011221','51.103329'),(35,35,41,'Chester','Bennett','male',19,'I\'m Chester, I\'m very interesting person','2019-08-15 12:00:00',2,'Zgorzelecka','17.000090','51.118400'),(36,36,42,'Adam','Ross','male',18,'I\'m Adam, I\'m very interesting person','2019-08-15 13:30:00',2,'Sernicka','17.028910','51.093730'),(37,37,43,'Charlotte','Chapman','female',25,'I\'m Charlotte, I\'m very interesting person','2019-08-15 14:00:00',2,'Sanocka','17.028910','51.093730'),(38,38,44,'Kamil','Brzycki','male',22,'Cześć jestem Kamil :) Jestem studentem Politechniki Wrocławskiej. Jednym z moich największych hobby jest gotowanie. Bardzo lubię podróżować. W przyszłości zamierzam zwiedzić Azję, a może nawet tam zamieszkać. ','2019-10-28 01:22:28',2,'Jelenia','17.03853760000004','51.1078852'),(39,40,45,'Erwin','Brzycki','male',22,'Full passion economist, likes football, voleyball, eager to meet other pasionats','2019-10-17 22:00:17',2,'Popowicka','16.993060','51.126950'),(41,72,54,'Zenek','Test','male',21,'edytuję opis profilu, testowo','2019-12-07 11:18:01',2,'śląska','17.03853760000004','51.1078852'),(43,74,NULL,'Abelard','Giza','male',48,'Jestem Abelard','2019-12-09 09:31:09',2,'Młodych Techników','17.03853760000004','51.1078852'),(45,76,55,'Andrzej','Sapkowski','male',65,'Jestem Andrzej Sapkowski','2019-12-07 12:47:22',2,'Poniatowskiego','17.03853760000004','51.1078852'),(47,78,NULL,'nie ustawiono','nie ustawiono',NULL,0,'nie ustawiono','2019-12-07 20:16:45',2,NULL,NULL,NULL),(48,79,56,'Kamil','Nowak','male',43,'Prezentacja pracy inżynierskiej','2019-12-09 09:41:26',2,'Poniatowskiego','17.03853760000004','51.1078852'),(49,80,57,'Jacek','Kornaszewski','male',21,'Jestem Jacek','2019-12-09 10:46:24',2,'Ruska','17.03853760000004','51.1078852');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-09 13:42:53
