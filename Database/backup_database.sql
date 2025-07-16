-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: conservatoriodb
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `corsidistudio`
--

DROP TABLE IF EXISTS `corsidistudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corsidistudio` (
  `codice` varchar(5) NOT NULL,
  `denominazione` varchar(45) NOT NULL,
  `CFU` int NOT NULL,
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corsidistudio`
--

LOCK TABLES `corsidistudio` WRITE;
/*!40000 ALTER TABLE `corsidistudio` DISABLE KEYS */;
INSERT INTO `corsidistudio` VALUES ('CANT1','caca',60),('CD001','Composizione Musicale',60),('CD002','Direzione d\'Orchestra',54),('CD003','Canto Lirico',48),('CD004','Pianoforte',60),('CD005','Violino',55),('CD006','Musica Jazz',50),('CD007','Tecnologie Musicali',45),('CD008','Chitarra Classica',52),('CD009','Organo e Composizione Organistica',58),('CD010','Didattica della Musica',48),('COMP1','jrir',60),('STRU1','miim',60);
/*!40000 ALTER TABLE `corsidistudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corsiselezionati`
--

DROP TABLE IF EXISTS `corsiselezionati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corsiselezionati` (
  `idpiano` int NOT NULL,
  `idcorso` int NOT NULL,
  PRIMARY KEY (`idpiano`,`idcorso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corsiselezionati`
--

LOCK TABLES `corsiselezionati` WRITE;
/*!40000 ALTER TABLE `corsiselezionati` DISABLE KEYS */;
/*!40000 ALTER TABLE `corsiselezionati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docenti`
--

DROP TABLE IF EXISTS `docenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docenti` (
  `matricola` varchar(10) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ed_corso` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docenti`
--

LOCK TABLES `docenti` WRITE;
/*!40000 ALTER TABLE `docenti` DISABLE KEYS */;
INSERT INTO `docenti` VALUES ('11111','ccc','ccc','ccc',NULL),('D001','Luca','Rossi','pass123','CD001'),('D002','Maria','Bianchi','abc456','CD002'),('D003','Giovanni','Verdi','qwerty','CD003'),('D004','Elena','Neri','music789','CD004'),('D005','Marco','Gialli','docpass1','CD005'),('D006','Francesca','Blu','bluekey','CD006'),('D007','Antonio','Grigi','pianopass','CD007'),('D008','Chiara','Viola','violinkey','CD008'),('D009','Simone','Marroni','jazzy123','CD009'),('D010','Laura','Arancio','teachme','CD010'),('D777','Ambrogio','Culo','culoculo',NULL),('D999','Abert','Einstein','fisika',NULL);
/*!40000 ALTER TABLE `docenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edizionicorso`
--

DROP TABLE IF EXISTS `edizionicorso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edizionicorso` (
  `idedizione` int NOT NULL,
  `annoAccademico` varchar(10) NOT NULL,
  `docente_assegnato` varchar(45) DEFAULT NULL,
  `cds` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idedizione`),
  KEY `docente_idx` (`docente_assegnato`),
  KEY `cds_idx` (`cds`),
  CONSTRAINT `cds` FOREIGN KEY (`cds`) REFERENCES `corsidistudio` (`codice`),
  CONSTRAINT `docente_assegnato` FOREIGN KEY (`docente_assegnato`) REFERENCES `docenti` (`matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edizionicorso`
--

LOCK TABLES `edizionicorso` WRITE;
/*!40000 ALTER TABLE `edizionicorso` DISABLE KEYS */;
INSERT INTO `edizionicorso` VALUES (1,'2024/2025','D001','CD001'),(2,'2024/2025','D002','CD002'),(3,'2024/2025','D003','CD003'),(4,'2024/2025','D004','CD004'),(5,'2024/2025','D005','CD005'),(6,'2024/2025','D006','CD006'),(7,'2024/2025','D007','CD007'),(8,'2024/2025','D008','CD008'),(9,'2024/2025','D009','CD009'),(10,'2024/2025','D010','CD010'),(11,'2024/2025','D001','CD002'),(12,'2024/2025','D001','CD003');
/*!40000 ALTER TABLE `edizionicorso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `esami`
--

DROP TABLE IF EXISTS `esami`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `esami` (
  `matricola_studente` varchar(45) NOT NULL,
  `idesame` int NOT NULL AUTO_INCREMENT,
  `lode` tinyint NOT NULL,
  `note` varchar(100) NOT NULL,
  `voto` int NOT NULL,
  `idVerbale` int NOT NULL,
  PRIMARY KEY (`idesame`),
  KEY `matricola_studente_idx` (`matricola_studente`),
  KEY `idVerbale_idx` (`idVerbale`),
  CONSTRAINT `idVerbale` FOREIGN KEY (`idVerbale`) REFERENCES `verbali` (`idverbali`),
  CONSTRAINT `matricola_studente` FOREIGN KEY (`matricola_studente`) REFERENCES `studenti` (`matricola`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esami`
--

LOCK TABLES `esami` WRITE;
/*!40000 ALTER TABLE `esami` DISABLE KEYS */;
INSERT INTO `esami` VALUES ('S00001',18,1,'CIAO',30,766),('S00005',19,0,'ottimo',20,766),('S00001',20,1,'BRAVISSIMO',30,405),('S00006',21,1,'TEST',30,353);
/*!40000 ALTER TABLE `esami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pianidistudio`
--

DROP TABLE IF EXISTS `pianidistudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pianidistudio` (
  `idpiano` int NOT NULL,
  `nome` varchar(45) NOT NULL,
  `CFU` int NOT NULL,
  `dipartimento` varchar(45) NOT NULL,
  PRIMARY KEY (`idpiano`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pianidistudio`
--

LOCK TABLES `pianidistudio` WRITE;
/*!40000 ALTER TABLE `pianidistudio` DISABLE KEYS */;
/*!40000 ALTER TABLE `pianidistudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenti`
--

DROP TABLE IF EXISTS `studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studenti` (
  `matricola` varchar(10) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `PIN` int NOT NULL,
  PRIMARY KEY (`matricola`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `PIN_UNIQUE` (`PIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
INSERT INTO `studenti` VALUES ('S00001','Luca','Rossi','lrossi1','pass123',1001),('S00002','Marco','Bianchi','mbianchi2','pass123',1002),('S00003','Giulia','Verdi','gverdi3','pass123',1003),('S00004','Sara','Gallo','sgallo4','pass123',1004),('S00005','Andrea','Russo','arusso5','pass123',1005),('S00006','Chiara','Romano','cromano6','pass123',1006),('S00007','Matteo','Ferrari','mferrari7','pass123',1007),('S00008','Francesca','Esposito','fesposito8','pass123',1008),('S00009','Davide','Ricci','dricci9','pass123',1009),('S00010','Elisa','Marino','emarino10','pass123',1010),('S00011','Simone','Greco','sgreco11','pass123',1011),('S00012','Alessia','Conti','aconti12','pass123',1012),('S00013','Stefano','De Luca','sdeluca13','pass123',1013),('S00014','Federica','Costa','fcosta14','pass123',1014),('S00015','Giorgio','Giordano','ggiordano15','pass123',1015),('S00016','Martina','Mancini','mmancini16','pass123',1016),('S00017','Antonio','Rizzo','arizzo17','pass123',1017),('S00018','Valentina','Lombardi','vlombardi18','pass123',1018),('S00019','Nicola','Barbieri','nbarbieri19','pass123',1019),('S00020','Ilaria','Fontana','ifontana20','pass123',1020),('S00021','Tommaso','Moretti','tmoretti21','pass123',1021),('S00022','Elena','Santoro','esantoro22','pass123',1022),('S00023','Gabriele','Serra','gserra23','pass123',1023),('S00024','Marta','D’Amico','mdamico24','pass123',1024),('S00025','Fabio','Grasso','fgrasso25','pass123',1025),('S00026','Beatrice','Sanna','bsanna26','pass123',1026),('S00027','Riccardo','Orlando','rorlando27','pass123',1027),('S00028','Laura','Parisi','lparisi28','pass123',1028),('S00029','Alberto','Pellegrini','apellegrini29','pass123',1029),('S00030','Silvia','Longo','slongo30','pass123',1030),('S00031','Daniele','Gentile','dgentile31','pass123',1031),('S00032','Camilla','Testa','ctesta32','pass123',1032),('S00033','Luigi','Farina','lfarina33','pass123',1033),('S00034','Giada','Amato','gamato34','pass123',1034),('S00035','Edoardo','Palumbo','epalumbo35','pass123',1035),('S00036','Roberta','Sorrentino','rsorrentino36','pass123',1036),('S00037','Claudio','Valenti','cvalenti37','pass123',1037),('S00038','Veronica','Bianco','vbianco38','pass123',1038),('S00039','Pietro','Martini','pmartini39','pass123',1039),('S00040','Emma','Negri','enegri40','pass123',1040),('S00041','Salvatore','Colombo','scolombo41','pass123',1041),('S00042','Isabella','Fabbri','ifabbri42','pass123',1042),('S00043','Dario','Leone','dleone43','pass123',1043),('S00044','Annalisa','Monti','amonti44','pass123',1044),('S00045','Cristian','Fiore','cfiore45','pass123',1045),('S00046','Rachele','De Rosa','rderosa46','pass123',1046),('S00047','Emanuele','Caruso','ecaruso47','pass123',1047),('S00048','Irene','Rinaldi','irinaldi48','pass123',1048),('S00049','Samuele','Neri','sneri49','pass123',1049),('S00050','Alessandro','Bellini','abellini50','pass123',1050);
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verbali`
--

DROP TABLE IF EXISTS `verbali`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verbali` (
  `idverbali` int NOT NULL,
  `dataAppello` varchar(10) NOT NULL,
  `codiceCorso` varchar(5) NOT NULL,
  `stato` varchar(10) DEFAULT NULL,
  `docente` varchar(45) NOT NULL,
  PRIMARY KEY (`idverbali`),
  KEY `matricola_docente_idx` (`docente`),
  CONSTRAINT `docente` FOREIGN KEY (`docente`) REFERENCES `docenti` (`matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verbali`
--

LOCK TABLES `verbali` WRITE;
/*!40000 ALTER TABLE `verbali` DISABLE KEYS */;
INSERT INTO `verbali` VALUES (2,'23/11/2024','CD001','APERTO','D001'),(277,'22/12/2025','CD001','APERTO','D001'),(353,'25/11/2024','CD001','APERTO','D001'),(405,'22/05/2025','CD001','APERTO','D001'),(452,'20/05/2002','CD001','APERTO','D001'),(566,'20/05/2002','CD001','APERTO','D001'),(639,'23/11/2025','CD002','APERTO','D002'),(716,'22/12/2025','CD002','APERTO','D002'),(766,'25/11/2003','CD001','APERTO','D001');
/*!40000 ALTER TABLE `verbali` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-12  9:29:32
