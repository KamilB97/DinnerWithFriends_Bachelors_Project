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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `phone` varchar(9) DEFAULT NULL,
  `password` varchar(68) NOT NULL,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'c.jones@gmail.com','777777777','1234','2019-08-15 12:00:00'),(2,'s.hamilton@gmail.com','888888888','1234','2019-08-15 13:30:00'),(3,'a.harrison@gmail.com','999999999','1234','2019-08-15 14:00:00'),(4,'d.robinson@gmail.com','777777777','1234','2019-08-15 12:00:00'),(5,'m.thomas@gmail.com','888888888','1234','2019-08-15 13:30:00'),(6,'h.gray@gmail.com','999999999','1234','2019-08-15 14:00:00'),(7,'a.moore@gmail.com','777777777','1234','2019-08-15 12:00:00'),(8,'a.scott@gmail.com','888888888','1234','2019-08-15 13:30:00'),(9,'m.carroll@gmail.com','999999999','1234','2019-08-15 14:00:00'),(10,'t.spencer@gmail.com','777777777','1234','2019-08-15 12:00:00'),(11,'h.alexander@gmail.com','888888888','1234','2019-08-15 13:30:00'),(12,'b.stevens@gmail.com','999999999','1234','2019-08-15 14:00:00'),(13,'a.brown@gmail.com','777777777','1234','2019-08-15 12:00:00'),(14,'c.morrison@gmail.com','888888888','1234','2019-08-15 13:30:00'),(15,'l.myers@gmail.com','999999999','1234','2019-08-15 14:00:00'),(16,'h.howard@gmail.com','777777777','1234','2019-08-15 12:00:00'),(17,'a.smith@gmail.com','888888888','1234','2019-08-15 13:30:00'),(18,'h.carroll@gmail.com','999999999','1234','2019-08-15 14:00:00'),(19,'a.parker@gmail.com','777777777','1234','2019-08-15 12:00:00'),(20,'c.johnson@gmail.com','888888888','1234','2019-08-15 13:30:00'),(21,'b.higgins@gmail.com','999999999','1234','2019-08-15 14:00:00'),(22,'d.campbell@gmail.com','777777777','1234','2019-08-15 12:00:00'),(23,'m.ross@gmail.com','888888888','1234','2019-08-15 13:30:00'),(24,'d.robinson@gmail.com','999999999','1234','2019-08-15 14:00:00'),(25,'b.cunningham@gmail.com','777777777','1234','2019-08-15 12:00:00'),(26,'e.harrison@gmail.com','888888888','1234','2019-08-15 13:30:00'),(27,'a.phillips@gmail.com','999999999','1234','2019-08-15 14:00:00'),(28,'s.phillips@gmail.com','777777777','1234','2019-08-15 12:00:00'),(29,'m.williams@gmail.com','888888888','1234','2019-08-15 13:30:00'),(30,'s.brooks@gmail.com','999999999','1234','2019-08-15 14:00:00'),(31,'s.moore@gmail.com','999999999','1234','2019-08-15 14:00:00'),(32,'l.montgomery@gmail.com','777777777','1234','2019-08-15 12:00:00'),(33,'l.bailey@gmail.com','888888888','1234','2019-08-15 13:30:00'),(34,'r.armstrong@gmail.com','999999999','1234','2019-08-15 14:00:00'),(35,'c.bennett@gmail.com','777777777','1234','2019-08-15 12:00:00'),(36,'a.ross@gmail.com','888888888','1234','2019-08-15 13:30:00'),(37,'c.chapman@gmail.com','999999999','$2a$10$m7cfNFnT8h1UjrC.hF9fL.X/M6matjmE0D5sPXoeAajYoOIH6VhUa','2019-08-15 14:00:00'),(38,'kamilbrzycki@gmail.com','796112221','$2a$10$TX4qAteaOhHT3DUZfxtFxOJTSHrqcJnAfKpS.vSBsQ7X.1VBLgcs6','2019-09-08 19:34:20'),(39,'kamillbrzycki@gmail.com','796112221','$2a$10$fwNYyvr3Ki68JQvXKIhqbOxjmGoiiz8e9Jkev3O0viIkTcuq06f2u','2019-10-01 14:10:18'),(40,'wiewie@gmail.com','987654321','$2a$10$ShKgfbePuLK0NLzpwjnZNO4mFE0lq7cQKmPNsPHbEEGxNdAEt9ela','2019-10-01 16:28:59'),(41,'whatthehell@gmai.com','123451234','$2a$10$BmCy1YLVprBqWeaRCuY.oebfF.xNgtOwx0xVw./wMPk5Wzq3S7Fky','2019-10-01 16:30:25'),(42,'kbkak@gmail.com','123456789','$2a$10$ygzrp4ElCGxW1cxibjf1nOLy52WfkuWFBhHkY/VqZEDpYvkqzT.lG','2019-10-01 16:36:22'),(43,'ezey@gmail.com','123456789','$2a$10$Pw9gb2YSNibxk4bn4f4BjOPmP0nITcDpS4F0tuyUcPH8xFoEY.xBW','2019-10-01 16:39:42'),(44,'cmmn@gmail.com','412741272','$2a$10$5VL1c/AkGcBJAt.U9cbFWOsE6CMsSIlEHwQfdbks4zZUHr/C3RhaK','2019-10-01 16:40:18'),(45,'gra@gmail.com','123456789','$2a$10$L0mkyNYSEPMUksbpCI6B5efXxK17kfKsEg8RTZR5U7PzpZ74Kvdli','2019-10-01 16:42:29'),(46,'bzbk@gmail.com','123456789','$2a$10$WK7WzH7b1Wq0ZGwJHn2DdecT/oZlCQjxYtu7cZy6iJr/.aDJGg.PG','2019-10-01 16:43:46'),(47,'kamilllbrzycki@gmail.com','796112221','$2a$10$31f578gjLa8kyWu97VCTFOXGs67mxZ7qIC3dOXx2dQv44eODVoOIW','2019-10-02 08:19:04'),(48,'kamilllsbrzycki@gmail.com','796112221','$2a$10$XZpFrhnML71tYIvS4c.L2uFLht0OjMRKPph483isTRupZIovjy6T6','2019-10-02 08:28:23'),(49,'BBbrzycki@gmail.com','796112221','$2a$10$GccvgMLwIseN5uGgCC81tuQi9Zz9.g5IdsedM/USyRfS6sBh3oHdu','2019-10-03 08:40:17'),(50,'BBBBbrzycki@gmail.com','796112221','$2a$10$reSOQeGprRYG5def.t4nCuuwwLGfAb2NOAAGJfOsHU/nxF33MC2ZS','2019-10-03 09:13:30'),(51,'BBBBBbrzycki@gmail.com','796112221','$2a$10$nejhQjQpBlAMV0skOJkZG.PHY8O4BdWakLsq/y4rpSeDsDxNd4KHK','2019-10-03 09:45:17'),(52,'BSBBbrzycki@gmail.com','796112221','$2a$10$SMqaux5ajBo2o.yV20ThjOPYo5ys0YUTV/qrjSyfwXV0GMw6/WXe.','2019-10-03 14:45:37'),(53,'BSsBBbrzycki@gmail.com','796112221','$2a$10$BBpOUdibiwNaKy0qba7RguqkOK1roN57rIgBL6LZcEQqo25k05J46','2019-10-03 14:47:00'),(54,'test123@gmail.com','123456789','$2a$10$FtL5XK9MiPn9VKqPAUKy2OCHaBe/SDH5qAoHwV3RC1ZM1tyM7a07i','2019-10-03 14:48:16'),(55,'test1234','412646123','$2a$10$.s2hnmgan4A6pbvbCF/jyurcXPwuynSTRdFWPiRa6bg9CL5059VLy','2019-10-03 14:50:08'),(56,'kssabrzycki@gmail.com','412741272','$2a$10$ja7PYWGb6E8N1xayq.bz8OutHZaByKuuFLT/J6U4ZNEpf9MglVOmu','2019-10-03 14:53:59'),(57,'kbkkak@gmail.com','412741271','$2a$10$VA1XaMeDj1B3EEImGOdBHe.UpjXpDBP78coenOPjXa5t1pxwMKl72','2019-10-03 15:12:35'),(58,'BSsSBBbrzycki@gmail.com','796112221','$2a$10$GfWE4r19cBRtb.dsCJPhvutSyIrV3QYDneIwUwb.3hFmi184mCfQ2','2019-10-03 15:30:46'),(59,'test12345','123456789','$2a$10$TW2bJQOE6X6.QV/KxklCB.KVHyTXH6YwL.1mss64h1uvJC48Ffl9q','2019-10-03 15:31:10'),(60,'test12345','123456789','$2a$10$107hV90TL8mITcZnkqj4UeYA.Ai7KGnIUPCTp4g4aXtBqcHzmGLue','2019-10-03 15:31:28'),(61,'test12345','123456789','$2a$10$YbnzhVoLf/CtoawW5VegvuLn4v4Tetm6hdT6Mfhssc6HD3u4WUqy2','2019-10-03 15:33:56'),(62,'test12345','123456789','$2a$10$TW4KQe1QIfdWhUVpsaalj.BYAkjJD2G/knWuyD6SXEetwTMMohhQK','2019-10-03 15:38:47'),(63,'test123456','123456789','$2a$10$Sqq.ji9tp.VuVnNodr13OO7rP9Nmj.RBHBbVG9yXttTz/FlILo636','2019-10-03 15:43:45'),(64,'gra@gmail.com','','$2a$10$TztCYCQy6l1o8fazJEIINOCSZ5/Y8jjpR5FODEEtXR7RpOafwB0uW','2019-11-26 15:43:15'),(65,'gra@gmail.com','','$2a$10$UfNDDBoaCHoGEQtenuKVsuIYaBiQNJCmCjp2xgUNP9UNu92jimOh6','2019-11-26 15:53:50'),(66,'nowyemail1@gmail.com','','$2a$10$SV4SDI/Wmu6nP7KD.HG7G.CrNukfSttODLOC1pWDn5C8wUQf6PGJ6','2019-12-06 15:53:54'),(67,'nowyemail2@gmail.com','','$2a$10$re/q01k02k3oA2JJO7fkx.rTGo7O7HgliTNckiyVgIY3HPcn14tba','2019-12-06 16:32:01'),(68,'nowyemail3@gmail.com','','$2a$10$9twgto2tV6M2295ZWqGCs.RTEdIA.Wp5At5YNqXBL8RK950QrmiwC','2019-12-06 20:54:37'),(69,'nowyemail4@gmail.com','','$2a$10$928Jphr3pCPZ8Qadar2qSe41/v10zyuSusj2CuSPFKdPtT3Y7E2AK','2019-12-06 20:57:49'),(70,'nowyemail5@gmail.com','','$2a$10$xbcrphgA4Ho7bGlMPg3XV.3IM7ggL6Hp8Ubos6qeNMzrHQMtRS0X.','2019-12-06 20:59:18'),(72,'nowyemail7@gmail.com','','$2a$10$uj/ZWuj6Ze60nDSztSkIyuvXiLAq1amq3zTeDo5WSVlz6J/FYz9ga','2019-12-06 21:38:21'),(74,'nowyemail11@gmail.com','','$2a$10$2S5pJNeiY94TCszNQiB4/.svdxu9xehk5qlCWpe1q60T9s2BoLOmq','2019-12-07 12:05:43'),(76,'nowyemail12@gmail.com','','$2a$10$feFFAbMr4h8uyBTLPlKcEOfPPey4PnmiKMVyLctAayG/pYH9KhM2e','2019-12-07 12:46:21'),(78,'nowyEmail14@gmail.com','','$2a$10$mUYdIzcTS3thR8xiAUKujuieqQPDJkL8CaqJ1ApmHxajaAOc80plm','2019-12-07 20:16:45'),(79,'nowyemail13@gmail.com','','$2a$10$Fg9rmYPb2bHnLMILFpdn6.0HaiT53cWzKoTyX4vvRkUz0y4Bny.ZW','2019-12-09 09:34:29'),(80,'nowyemail15@gmail.com','','$2a$10$7ftUUAxEjbAuLETy1pCfJuAHHHjTGI8plwbz5A6r0xDY5TFZF3v06','2019-12-09 10:45:31');
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

-- Dump completed on 2019-12-09 13:42:56
