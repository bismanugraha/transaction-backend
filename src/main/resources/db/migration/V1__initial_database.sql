-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: transaction_nutech
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int NOT NULL AUTO_INCREMENT,
  `banner_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `banner_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1,'https://nutech-integrasi.app/dummy.jpg','Banner 1','Lorem ipsum sit dolor amet'),(2,'https://nutech-integrasi.app/dummy.jpg','Banner 2','Lorem ipsum sit dolor amet'),(3,'https://nutech-integrasi.app/dummy.jpg','Banner 3','Lorem ipsum sit dolor amet'),(4,'https://nutech-integrasi.app/dummy.jpg','Banner 4','Lorem ipsum sit dolor amet'),(5,'https://nutech-integrasi.app/dummy.jpg','Banner 5','Lorem ipsum sit dolor amet'),(6,'https://nutech-integrasi.app/dummy.jpg','Banner 6','Lorem ipsum sit dolor amet');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `service_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `service_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `service_tariff` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,'PAJAK','https://nutech-integrasi.app/dummy.jpg','Pajak PBB',40000),(2,'PLN','https://nutech-integrasi.app/dummy.jpg','Listrik',10000),(3,'PDAM','https://nutech-integrasi.app/dummy.jpg','PDAM Berlangganan',40000),(4,'PULSA','https://nutech-integrasi.app/dummy.jpg','Pulsa',40000),(5,'PGN','https://nutech-integrasi.app/dummy.jpg','PGN Berlangganan',50000),(6,'MUSIK','https://nutech-integrasi.app/dummy.jpg','Musik Berlangganan',50000),(7,'TV','https://nutech-integrasi.app/dummy.jpg','TV Berlangganan',50000),(8,'PAKET_DATA','https://nutech-integrasi.app/dummy.jpg','Paket data',50000),(9,'VOUCHER_GAME','https://nutech-integrasi.app/dummy.jpg','Voucher Game',100000),(10,'VOUCHER_MAKANAN','https://nutech-integrasi.app/dummy.jpg','Voucher Makanan',100000),(11,'QURBAN','https://nutech-integrasi.app/dummy.jpg','Qurban',200000),(12,'ZAKAT','https://nutech-integrasi.app/dummy.jpg','Zakat',300000);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaksi` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(100) NOT NULL,
  `invoice_number` varchar(100) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `total_amount` bigint NOT NULL,
  `created_on` date NOT NULL,
  `user_email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES (1,'TOP_UP','INV31102025-0000','Top Up balance',1000,'2025-10-31','ex@example3.com'),(2,'TOP_UP','INV31102025-0002','Top Up balance',1000,'2025-10-31','ex@example2.com'),(3,'TOP_UP','INV31102025-0003','Top Up balance',15000,'2025-10-31','ex@example2.com'),(4,'TOP_UP','INV31102025-0004','Top Up balance',25000,'2025-10-31','ex@example2.com'),(5,'PAYMENT','INV-P-31102025-0005','Listrik',31000,'2025-10-31','ex@example2.com'),(6,'PAYMENT','INV-P-31102025-0006','Listrik',21000,'2025-10-31','ex@example2.com'),(7,'PAYMENT','INV-P-31102025-0007','Listrik',11000,'2025-10-31','ex@example2.com'),(8,'PAYMENT','INV-P-31102025-0008','Listrik',1000,'2025-10-31','ex@example2.com');
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `balance` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_unique` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ex@example.com','Joko','Joka','$2a$10$nbW9Ia9x.O9j4wcXcaJqvO2ia5DGlnyEHlPa2DN0y.TqXOfPConCG','https://staging-url.com/image/Go0WAsibkAAXRKt.jpg',0),(2,'ex@example2.com','Anwas','Hehe','$2a$10$ZCdeojUgzrqbBMRdthAYgOuHSLUFVjU8BQoDzWZ00wpGjsH9411KW',NULL,1000),(3,'ex@example3.com','Mie','Mawut','$2a$10$1nvSTyE0/rWux3dkQyypFuVwR2GFNXRIvqsIrv7WPyaiOCFXpVuHG','https://staging-url.com/image/Go0WAsibkAAXRKt.jpg',7000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'transaction_nutech'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-31 18:56:26
