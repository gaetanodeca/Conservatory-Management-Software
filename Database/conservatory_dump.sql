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
  `codice` varchar(30) NOT NULL,
  `denominazione` varchar(100) NOT NULL,
  `CFU` int NOT NULL,
  PRIMARY KEY (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corsidistudio`
--

LOCK TABLES `corsidistudio` WRITE;
/*!40000 ALTER TABLE `corsidistudio` DISABLE KEYS */;
INSERT INTO `corsidistudio` VALUES ('CANT1','Tecnica vocale di base',6),('CANT10','Ear training e intonazione',12),('CANT11','Canto moderno e pop',6),('CANT12','Psicologia della performance',12),('CANT13','Musica da camera vocale',6),('CANT14','Canto gregoriano e polifonia',6),('CANT15','Laboratorio di registrazione vocale',6),('CANT2','Teoria musicale per cantanti',6),('CANT3','Repertorio lirico italiano',9),('CANT4','Interpretazione scenica',9),('CANT5','Fonazione e respirazione',6),('CANT6','Canto jazz e improvvisazione',6),('CANT7','Storia della musica vocale',6),('CANT8','Dizione e articolazione',12),('CANT9','Coro e musica d’insieme',6),('COMP1','Teoria della composizione musicale',12),('COMP10','Musica elettronica e sound design',9),('COMP11','Composizione per strumenti solisti',9),('COMP12','Laboratorio di composizione',6),('COMP13','Composizione corale',9),('COMP14','Storia della composizione musicale',9),('COMP15','Composizione sperimentale',12),('COMP2','Contrappunto',9),('COMP3','Orchestrazione',12),('COMP4','Tecniche di composizione contemporanea',6),('COMP5','Composizione per ensemble',12),('COMP6','Armonia funzionale',9),('COMP7','Analisi musicale',9),('COMP8','Scrittura per strumenti a fiato',6),('COMP9','Composizione per il cinema e media',12),('DID1','Fondamenti di didattica musicale',12),('DID10','Didattica musicale per la scuola primaria',9),('DID11','Progettazione di attività musicali',12),('DID12','Storia della didattica musicale',9),('DID13','Musica e disabilità: approcci educativi',9),('DID14','Valutazione in educazione musicale',12),('DID15','Laboratorio di improvvisazione didattica',9),('DID2','Psicopedagogia musicale',9),('DID3','Didattica della musica d’insieme',12),('DID4','Metodologie dell’insegnamento musicale',9),('DID5','Educazione al ritmo e al movimento',12),('DID6','Didattica della vocalità',9),('DID7','Tecnologie per la didattica musicale',12),('DID8','Laboratorio di pedagogia musicale',9),('DID9','Teorie dell’apprendimento musicale',12),('INS1','Tecniche di musica d’insieme',12),('INS10','Laboratorio per musica d’insieme',9),('INS11','Musica d’insieme per strumenti a tastiera',12),('INS12','Musica d’insieme pop e rock',9),('INS13','Direzione di piccoli gruppi strumentali',12),('INS14','Analisi e interpretazione in musica d’insieme',9),('INS15','Laboratorio di musica d’insieme sperimentale',12),('INS2','Laboratorio di musica d’insieme da camera',9),('INS3','Musica d’insieme jazz',12),('INS4','Musica d’insieme vocale',9),('INS5','Musica d’insieme per strumenti a fiato',12),('INS6','Musica d’insieme per strumenti ad arco',9),('INS7','Improvvisazione in musica d’insieme',12),('INS8','Repertorio contemporaneo per musica d’insieme',9),('INS9','Tecniche di ascolto e interazione',12),('STRU1','Tecnica strumentale individuale',12),('STRU10','Strumento principale: flauto traverso',12),('STRU11','Improvvisazione strumentale',9),('STRU12','Accompagnamento pianistico',9),('STRU13','Studio del repertorio solistico',12),('STRU14','Tecnica strumentale per musica moderna',9),('STRU15','Laboratorio di strumenti antichi',9),('STRU2','Laboratorio di musica d’insieme',9),('STRU3','Esecuzione per strumenti a tastiera',12),('STRU4','Pratica orchestrale',9),('STRU5','Interpretazione per strumenti a corda',12),('STRU6','Tecnica avanzata per strumenti a fiato',9),('STRU7','Strumento principale: pianoforte',12),('STRU8','Strumento principale: chitarra classica',12),('STRU9','Strumento principale: violino',12);
/*!40000 ALTER TABLE `corsidistudio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corsiselezionati`
--

DROP TABLE IF EXISTS `corsiselezionati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corsiselezionati` (
  `id_piano` varchar(10) NOT NULL,
  `id_corso` varchar(10) NOT NULL,
  PRIMARY KEY (`id_piano`,`id_corso`),
  KEY `id_corso_idx` (`id_corso`),
  CONSTRAINT `id_corso` FOREIGN KEY (`id_corso`) REFERENCES `corsidistudio` (`codice`),
  CONSTRAINT `id_piano` FOREIGN KEY (`id_piano`) REFERENCES `pianidistudio` (`idpiano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corsiselezionati`
--

LOCK TABLES `corsiselezionati` WRITE;
/*!40000 ALTER TABLE `corsiselezionati` DISABLE KEYS */;
INSERT INTO `corsiselezionati` VALUES ('245545048','CANT1'),('246521691','CANT1'),('246554907','CANT1'),('246715061','CANT1'),('247658269','CANT1'),('248479284','CANT1'),('248648626','CANT1'),('249089209','CANT1'),('249193460','CANT1'),('256803648','CANT1'),('245545048','CANT10'),('246521691','CANT10'),('246554907','CANT10'),('246715061','CANT10'),('247658269','CANT10'),('248479284','CANT10'),('248648626','CANT10'),('249089209','CANT10'),('249193460','CANT10'),('256803648','CANT10'),('245545048','CANT11'),('246521691','CANT11'),('246554907','CANT11'),('246715061','CANT11'),('247658269','CANT11'),('248479284','CANT11'),('248648626','CANT11'),('249089209','CANT11'),('249193460','CANT11'),('256803648','CANT11'),('245545048','CANT12'),('246521691','CANT12'),('246554907','CANT12'),('246715061','CANT12'),('247658269','CANT12'),('248479284','CANT12'),('248648626','CANT12'),('249089209','CANT12'),('249193460','CANT12'),('256803648','CANT12'),('245545048','CANT13'),('246521691','CANT13'),('246554907','CANT13'),('246715061','CANT13'),('247658269','CANT13'),('248479284','CANT13'),('248648626','CANT13'),('249089209','CANT13'),('249193460','CANT13'),('256803648','CANT13'),('245545048','CANT14'),('246521691','CANT14'),('246554907','CANT14'),('246715061','CANT14'),('247658269','CANT14'),('248479284','CANT14'),('248648626','CANT14'),('249089209','CANT14'),('249193460','CANT14'),('256803648','CANT14'),('245545048','CANT15'),('246521691','CANT15'),('246554907','CANT15'),('246715061','CANT15'),('247658269','CANT15'),('248479284','CANT15'),('248648626','CANT15'),('249089209','CANT15'),('249193460','CANT15'),('256803648','CANT15'),('245545048','CANT2'),('246521691','CANT2'),('246554907','CANT2'),('246715061','CANT2'),('247658269','CANT2'),('248479284','CANT2'),('248648626','CANT2'),('249089209','CANT2'),('249193460','CANT2'),('256803648','CANT2'),('245545048','CANT3'),('246521691','CANT3'),('246554907','CANT3'),('246715061','CANT3'),('245545048','CANT4'),('246521691','CANT4'),('246554907','CANT4'),('246715061','CANT4'),('247658269','CANT4'),('248479284','CANT4'),('248648626','CANT4'),('249089209','CANT4'),('249193460','CANT4'),('256803648','CANT4'),('245545048','CANT5'),('246521691','CANT5'),('246554907','CANT5'),('246715061','CANT5'),('247658269','CANT5'),('248479284','CANT5'),('248648626','CANT5'),('249089209','CANT5'),('249193460','CANT5'),('256803648','CANT5'),('245545048','CANT6'),('246521691','CANT6'),('246554907','CANT6'),('246715061','CANT6'),('247658269','CANT6'),('248479284','CANT6'),('248648626','CANT6'),('249089209','CANT6'),('249193460','CANT6'),('256803648','CANT6'),('245545048','CANT7'),('246521691','CANT7'),('246554907','CANT7'),('246715061','CANT7'),('247658269','CANT7'),('248479284','CANT7'),('248648626','CANT7'),('249089209','CANT7'),('249193460','CANT7'),('256803648','CANT7'),('245545048','CANT8'),('246521691','CANT8'),('246554907','CANT8'),('246715061','CANT8'),('247658269','CANT8'),('248479284','CANT8'),('248648626','CANT8'),('249089209','CANT8'),('249193460','CANT8'),('256803648','CANT8'),('245545048','CANT9'),('246521691','CANT9'),('246554907','CANT9'),('246715061','CANT9'),('247658269','CANT9'),('248479284','CANT9'),('248648626','CANT9'),('249089209','CANT9'),('249193460','CANT9'),('256803648','CANT9'),('245545048','COMP1'),('246521691','COMP1'),('246554907','COMP1'),('246715061','COMP1'),('247658269','COMP1'),('248479284','COMP1'),('248648626','COMP1'),('249089209','COMP1'),('249193460','COMP1'),('256803648','COMP1'),('246521691','COMP10'),('246554907','COMP10'),('246715061','COMP10'),('247658269','COMP10'),('248479284','COMP10'),('248648626','COMP10'),('249089209','COMP10'),('249193460','COMP10'),('256803648','COMP10'),('246521691','COMP11'),('246554907','COMP11'),('246715061','COMP11'),('247658269','COMP11'),('248479284','COMP11'),('248648626','COMP11'),('249089209','COMP11'),('249193460','COMP11'),('256803648','COMP11'),('246521691','COMP12'),('246554907','COMP12'),('246715061','COMP12'),('247658269','COMP12'),('248479284','COMP12'),('248648626','COMP12'),('249089209','COMP12'),('249193460','COMP12'),('256803648','COMP12'),('246521691','COMP13'),('246554907','COMP13'),('246715061','COMP13'),('247658269','COMP13'),('248479284','COMP13'),('248648626','COMP13'),('249089209','COMP13'),('249193460','COMP13'),('256803648','COMP13'),('246521691','COMP14'),('246554907','COMP14'),('246715061','COMP14'),('247658269','COMP14'),('248479284','COMP14'),('248648626','COMP14'),('249089209','COMP14'),('249193460','COMP14'),('256803648','COMP14'),('245545048','COMP15'),('246521691','COMP15'),('246554907','COMP15'),('246715061','COMP15'),('247658269','COMP15'),('248479284','COMP15'),('248648626','COMP15'),('249089209','COMP15'),('249193460','COMP15'),('256803648','COMP15'),('246715061','COMP2'),('247658269','COMP2'),('248479284','COMP2'),('248648626','COMP2'),('249089209','COMP2'),('249193460','COMP2'),('256803648','COMP2'),('245545048','COMP3'),('245545048','COMP4'),('246554907','COMP4'),('245545048','COMP5'),('245545048','COMP9'),('246521691','STRU4'),('246554907','STRU4');
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
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `ed_corso` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`matricola`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docenti`
--

LOCK TABLES `docenti` WRITE;
/*!40000 ALTER TABLE `docenti` DISABLE KEYS */;
INSERT INTO `docenti` VALUES ('11111','ccc','ccc','ccc',NULL),('D001','Luca','Rossi','pass123','CD001'),('D002','Maria','Bianchi','abc456','CD002'),('D003','Giovanni','Verdi','qwerty','CD003'),('D004','Elena','Neri','music789','CD004'),('D005','Marco','Gialli','docpass1','CD005'),('D006','Francesca','Blu','bluekey','CD006'),('D007','Antonio','Grigi','pianopass','CD007'),('D008','Chiara','Viola','violinkey','CD008'),('D009','Simone','Marroni','jazzy123','CD009'),('D010','Laura','Arancio','teachme','CD010'),('D020','Ambrogino','Culino','olololo',NULL),('D026','SIMONE','ESPOSITO','UUUUDS',NULL),('D033','PIPPO','BAUDO','testing',NULL),('D054','Charles','Leclerc','F1ismylife',NULL),('D086','TEST','TEST','aaa',NULL),('D092','cccccccccccccccccc','aaa','aaa',NULL),('D100','Ambrogio','Culo','pass1234',NULL),('D231','Pippo','Baudo','caratteri1',NULL),('D299','gianni','innaig','aaaaa',NULL),('D416','Nghedi','Thebest','oooooo',NULL),('D460','PROVA','PROVA','AAAAAAAAAA',NULL),('D484','Antonino','Cecco','1?mmmm',NULL),('D496','TEST','TEST','aaa',NULL),('D537','Frank','Matano','ciao',NULL),('D562','Ambrogiogio','Culolo','passssssss',NULL),('D636','Anna Rita','Fasolino','bestprof82',NULL),('D777','Ambrogio','Culo','culoculo',NULL),('D802','Ciccio','Games','paguri',NULL),('D914','TEST','TEST','1234567890',NULL),('D999','Abert','Einstein','fisika',NULL);
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
INSERT INTO `edizionicorso` VALUES (1,'2022/2023','D001','CANT1'),(2,'2023/2024','D001','CANT2'),(3,'2024/2025','D001','CANT3'),(4,'2024/2025','D001','CANT4'),(5,'2021/2022','D001','CANT5');
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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esami`
--

LOCK TABLES `esami` WRITE;
/*!40000 ALTER TABLE `esami` DISABLE KEYS */;
/*!40000 ALTER TABLE `esami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pianidistudio`
--

DROP TABLE IF EXISTS `pianidistudio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pianidistudio` (
  `idpiano` varchar(30) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `CFU` int NOT NULL,
  PRIMARY KEY (`idpiano`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pianidistudio`
--

LOCK TABLES `pianidistudio` WRITE;
/*!40000 ALTER TABLE `pianidistudio` DISABLE KEYS */;
INSERT INTO `pianidistudio` VALUES ('245545048','PianoBelisimo',180),('246104114','Piano2',180),('246202329','Piano3',180),('246521691','PianoGodo',180),('246554907','Piano4',180),('246715061','Piano5',180),('247658269','Piano10',180),('248479284','PIANOOO',180),('248648626','PianoFINALE',180),('249089209','Ciaoo',180),('249193460','Afammoooooooooo',180),('256803648','PianoYeee',180);
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
INSERT INTO `verbali` VALUES (412,'25/05/2020','CD001','APERTO','D001'),(706,'25/05/2024','CD001','APERTO','D001'),(725,'22/07/2014','CD001','APERTO','D001'),(728,'25/05/2022','CD001','APERTO','D001');
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

-- Dump completed on 2025-07-16 11:56:04
