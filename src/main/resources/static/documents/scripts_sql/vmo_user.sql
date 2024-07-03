-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: vmo
-- ------------------------------------------------------
-- Server version	8.3.0

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
  `role` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK589idila9li6a4arw1t8ht1gx` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('ADMIN',7,'2002-02-12 00:00:00.000000','sonngo@gmail.com','Son','Ngo','$2a$10$/E0SB0vpvXtSn3WyXmlFhuNa.kswAnwKkssl2my8nc.ECw1VizwOu','0562202977','ngoson',_binary ''),('MANAGER',8,'2002-11-12 00:00:00.000000','nguyenviet@gmail.com','Viet','Nguyen','$2a$10$/JTHR6VDWngU2EylWlIbgOIKlASuVrsGEg4lp4rZni1yRaaPgobJG','0123456789','nguyenviet',_binary ''),('MANAGER',9,'2002-06-05 00:00:00.000000','tranthu@gmail.com','Thu','Tran','$2a$10$7m1TdpLd6HYtQ89naZJXgO9GBKe2sPoqawsEQicmbObjmBKFaVU8q','0987654321','tranthu',_binary ''),('MANAGER',10,'2002-04-01 00:00:00.000000','phankieu@gmail.com','Kieu','Phan','$2a$10$fJwcGNidj4TrMeF4DFCWd.Wu/88CdyJcXaB9haSWotx3bsE1vWuSm','0567843215','phankieu',_binary ''),('MANAGER',11,'2002-12-01 00:00:00.000000','maihuy@gmail.com','Huy','Mai','$2a$10$EaCQDsh79/n6SmSdMkdwUOSGt/F861ML0F2WdZulmBB1/qMYzgQyS','0789452134','maihuy',_binary ''),('FRESHER',13,'2002-07-01 00:00:00.000000','anhpham@gmail.com','Anh','Pham','$2a$10$OSBE78qEmr0hPYJxZhGAHeQk/L29ndDf.XtxR9/ta0wJmDUBFJQre','0789452143','phamanh',_binary ''),('FRESHER',14,'2002-07-08 00:00:00.000000','luanhan@gmail.com','Luan','Han','$2a$10$7xbtEvbenvpjIpgkXnwGgeSz7PcYCiToi1shCHzDFKUWsX6/cKlUC','0789453256','hanluan',_binary ''),('FRESHER',15,'2002-01-12 00:00:00.000000','tamnguyen@gmail.com','Tam','Nguyen','$2a$10$..M9iDlmUCpqE68MZIBVjOXeLIf1f3jbx27BavERxvT0KIiUJP8ZS','0654453256','nguyentam',_binary '');
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

-- Dump completed on 2024-07-03 16:42:11
