-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: marathon
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
                        `CityId` int NOT NULL AUTO_INCREMENT,
                        `CityName` varchar(500) NOT NULL,
                        PRIMARY KEY (`CityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Suzhou'),(2,'Nanjing'),(3,'Wuxi'),(4,'Changzhou'),(5,'Nantong'),(6,'Xuzhou'),(7,'Yangzhou'),(8,'Lianyungang'),(9,'Huaian'),(10,'Yancheng'),(11,'Zhenjiang'),(12,'Taizhou'),(13,'Suqian');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competition` (
                               `Id` int NOT NULL AUTO_INCREMENT,
                               `Name` varchar(50) NOT NULL,
                               `RegCost` decimal(10,2) NOT NULL,
                               PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,'Zhejiang Competition 1',12.32);
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `racekitoption`
--

DROP TABLE IF EXISTS `racekitoption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `racekitoption` (
                                 `OptionId` int NOT NULL AUTO_INCREMENT,
                                 `Option` varchar(100) NOT NULL,
                                 `Cost` decimal(10,2) NOT NULL,
                                 PRIMARY KEY (`OptionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `racekitoption`
--

LOCK TABLES `racekitoption` WRITE;
/*!40000 ALTER TABLE `racekitoption` DISABLE KEYS */;
INSERT INTO `racekitoption` VALUES (1,'Runner\'s bib + RFID bracelet',0.00),(2,'Option A + hat + water bottle',20.00),(3,'Option B + T-shirt + souvenir booklet',45.00);
/*!40000 ALTER TABLE `racekitoption` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raceresult`
--

DROP TABLE IF EXISTS `raceresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raceresult` (
                              `ResultId` int NOT NULL AUTO_INCREMENT,
                              `Status` int NOT NULL,
                              `CompletionTime` int DEFAULT NULL,
                              `CompetitionId` int NOT NULL,
                              `RunnerEmail` varchar(50) NOT NULL,
                              PRIMARY KEY (`ResultId`),
                              KEY `raceresult_competition_Id_fk` (`CompetitionId`),
                              KEY `raceresult_runner_Email_fk` (`RunnerEmail`),
                              UNIQUE KEY `uq_raceresult_competition_runner` (`CompetitionId`,`RunnerEmail`),
                              CONSTRAINT `raceresult_competition_Id_fk` FOREIGN KEY (`CompetitionId`) REFERENCES `competition` (`Id`),
                              CONSTRAINT `raceresult_runner_Email_fk` FOREIGN KEY (`RunnerEmail`) REFERENCES `runner` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raceresult`
--

LOCK TABLES `raceresult` WRITE;
/*!40000 ALTER TABLE `raceresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `raceresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
                                `Email` varchar(50) NOT NULL,
                                `OptionId` int NOT NULL,
                                `CompetitionId` int NOT NULL,
                                `TotalPrice` decimal(10,2) NOT NULL,
                                PRIMARY KEY (`Email`,`CompetitionId`),
                                KEY `FK_Register_RaceKitOption` (`OptionId`),
                                KEY `FK_Register_Competition` (`CompetitionId`),
                                CONSTRAINT `FK_Register_Competition` FOREIGN KEY (`CompetitionId`) REFERENCES `competition` (`Id`),
                                CONSTRAINT `FK_Register_RaceKitOption` FOREIGN KEY (`OptionId`) REFERENCES `racekitoption` (`OptionId`),
                                CONSTRAINT `FK_Register_Runner` FOREIGN KEY (`Email`) REFERENCES `runner` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('a.sama@keef.com',2,1,0),('c.borgman@outlook.com',1,1,0),('dianne.helton@ew.net',3,1,0),('evalyn.christian@hr.gov',3,1,0),('h.thurston@hotmail.com',2,1,0),('irene.hager@ramoz.com',2,1,0),('lizzie.whitt@sizzling.com',1,1,0),('luther.bowser@seeley.net',3,1,0),('p.vann@yahoo.com',1,1,0),('rlangston88@hotmail.com',1,1,0);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `RoleId` int NOT NULL AUTO_INCREMENT,
                        `RoleName` varchar(50) NOT NULL,
                        PRIMARY KEY (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator'),(2,'Runner');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `runner`
--

DROP TABLE IF EXISTS `runner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `runner` (
                              `Email` varchar(50) NOT NULL,
                              `Name` varchar(50) NOT NULL,
                              `Gender` enum('Male','Female') NOT NULL,
                              `DateOfBirth` date NOT NULL,
                              `CityId` int NOT NULL,
                              `Experience` varchar(20) NOT NULL,
                              `Photo` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`Email`),
                          KEY `FK_Runner_City` (`CityId`),
                          CONSTRAINT `FK_Runner_City` FOREIGN KEY (`CityId`) REFERENCES `city` (`CityId`),
                          CONSTRAINT `FK_Runner_User` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `runner`
--

LOCK TABLES `runner` WRITE;
/*!40000 ALTER TABLE `runner` DISABLE KEYS */;
INSERT INTO `runner` VALUES ('a.beaulieu@hotmail.com','BREE','Male','1993-07-12',7,'Experienced',NULL),('a.copeland@hotmail.com','DEBRA','Female','1934-06-01',4,'Have some experience',NULL),('a.mccollum@hotmail.com','NICK','Female','1965-09-14',8,'Experienced',NULL),('a.sama@keef.com','TAMATHA','Male','1912-07-25',1,'First time',NULL),('abattle56@hotmail.com','CLEO','Female','1984-11-01',9,'Experienced',NULL),('acombs235@hr.gov','KIRI','Female','1965-06-29',9,'Have some experience',NULL),('alberto.lancaster@milkwoodproductions.com','ANTON','Male','1941-12-01',7,'Experienced',NULL),('alittle270@gmail.com','LORI','Female','1960-06-19',10,'Have some experience',NULL),('alyce@ew.net','JULES','Female','1939-11-17',5,'Experienced',NULL),('arran.clements@gmail.com','FELICIA','Male','1939-08-24',12,'First time',NULL),('audry.fair@ew.net','PEARL','Female','1936-08-01',8,'Have some experience',NULL),('austin.cash@ew.net','KASSANDRA','Male','1975-06-22',13,'Have some experience',NULL),('b.patak@gmail.com','OLIVER','Male','1998-05-18',2,'Have some experience',NULL),('becky.lipke@milkwoodproductions.com','HORTENSIA','Female','1949-04-06',11,'Experienced',NULL),('benita@education.gov','MITCH','Female','1936-11-11',2,'Have some experience',NULL),('bflint398@gmail.com','MARGO','Female','1988-12-18',5,'Have some experience',NULL),('bgentry398@seeley.net','DOMINICK','Female','1966-07-22',2,'Have some experience',NULL),('bkerr195@sizzling.com','CORI','Female','2001-07-08',10,'Have some experience',NULL),('brent.puckett@hotmail.com','RUBIN','Male','1949-08-17',6,'First time',NULL),('c.borgman@outlook.com','KERRI','Male','1973-10-26',4,'First time',NULL),('c.bragg@gmail.com','GEOFFREY','Male','1986-02-17',7,'First time',NULL),('c.mugnolo@outlook.com','ROSS','Female','1955-01-16',7,'Have some experience',NULL),('c.pinilla@dayrep.net','KATHERINE','Female','1989-07-25',9,'Experienced',NULL),('cherise@gmail.com','RHONDA','Female','2000-11-17',13,'Have some experience',NULL),('csherrill145@gmail.com','ALENA','Female','1963-02-17',6,'Have some experience',NULL),('d.mullen@gmail.com','DESIREE','Female','1979-07-30',12,'Have some experience',NULL),('d.pope@hotmail.com','DAYNA','Female','1998-12-21',9,'Have some experience',NULL),('dchristian488@gmail.com','BEN','Female','1938-09-01',13,'Experienced',NULL),('dianne.helton@ew.net','BARBRA','Female','1967-08-17',2,'First time',NULL),('dnix171@seeley.net','RODGER','Male','1986-09-19',11,'First time',NULL),('dominik@gmail.com','FELICITAS','Male','1983-10-16',12,'Experienced',NULL),('dorothea.similton@gmail.com','GERMAINE','Female','1985-02-03',13,'Have some experience',NULL),('dtabor280@gmail.com','DONALD','Male','1960-04-09',10,'Have some experience',NULL),('e.givens@gmail.com','EASTER','Male','1963-07-14',4,'Experienced',NULL),('e.michaels@hotmail.com','FRANKIE','Male','1994-10-19',12,'Have some experience',NULL),('e.smullen@gmail.com','NANNIE','Female','1967-02-22',1,'Experienced',NULL),('edwardo.yum@gmail.com','ELMO','Male','1985-08-25',8,'Experienced',NULL),('ester.huntley@outlook.com','ALDO','Female','1966-03-11',2,'First time',NULL),('evalyn.christian@hr.gov','SERENA','Female','1992-01-02',3,'First time',NULL),('fbruce444@gmail.com','JOANNE','Male','1932-08-31',12,'Have some experience',NULL),('g.humphreys@gmail.com','LAURA','Male','1951-07-20',1,'Have some experience',NULL),('g.hurt@hotmail.com','HUGH','Male','1999-09-23',3,'Have some experience',NULL),('gperkins90@hotmail.com','LINO','Male','1980-02-02',13,'Have some experience',NULL),('gregg@gmail.com','DEBBIE','Male','1989-05-27',11,'Have some experience',NULL),('guy@nord.net','EVAN','Male','1992-08-05',10,'Have some experience',NULL),('h.arnett@dayrep.net','JACKIE','Female','1953-11-14',2,'Experienced',NULL),('h.roth@nord.net','TARA','Male','1981-11-18',4,'Have some experience',NULL),('h.thurston@hotmail.com','ROBERT','Female','1951-11-29',9,'First time',NULL),('i.thacker@gmail.com','BRIANNA','Female','1937-02-25',13,'First time',NULL),('irene.hager@ramoz.com','EARL','Female','1967-12-18',10,'First time',NULL),('iris@seeley.net','CARTER','Female','1966-09-27',1,'Have some experience',NULL),('ivan@nord.net','CAROLINE','Male','1943-07-17',3,'Have some experience',NULL),('j.herman@keef.com','RENA','Male','1937-11-14',10,'Experienced',NULL),('jason.putnam@seeley.net','RAYMOND','Male','1973-04-01',7,'Have some experience',NULL),('jbusby243@gmail.com','ANGELIQUE','Male','1934-02-01',11,'Have some experience',NULL),('jessie.whittaker@gmail.com','SHELLY','Female','1932-10-15',12,'First time',NULL),('johnny.antila@gmail.com','COLLIN','Male','1912-11-09',3,'Experienced',NULL),('jschell52@gmail.com','SAMUEL','Male','1940-01-14',4,'Have some experience',NULL),('jtrotter32@finance.gov','CHARLENE','Male','1932-06-30',3,'Experienced',NULL),('judith.bravo@gmail.com','JERALDINE','Female','1943-11-07',1,'First time',NULL),('jules.parker@gmail.com','DELMAR','Male','1937-09-09',4,'First time',NULL),('k.colaizzo@sizzling.com','ORVILLE','Male','1934-10-31',8,'Have some experience',NULL),('k.corcoran@education.gov','PATRICK','Female','1999-10-19',4,'Experienced',NULL),('k.pawlowicz@hotmail.com','DEAN','Female','1983-11-27',13,'First time',NULL),('k.sutton@gmail.com','ERWIN','Female','1939-10-18',5,'Have some experience',NULL),('kelsey.rivers@gmail.com','EVANGELINE','Female','1912-05-30',12,'Have some experience',NULL),('l.beasley@hotmail.com','ISAAC','Male','1981-12-06',1,'First time',NULL),('laurie.baggett@gmail.com','ED','Female','1967-03-18',7,'Have some experience',NULL),('lbenimadho458@gmail.com','MACKENZIE','Male','1951-04-22',5,'Have some experience',NULL),('liam.chin@gmail.com','EMELIA','Male','1948-03-17',6,'Have some experience',NULL),('lizzie.whitt@sizzling.com','KATHLYN','Female','1990-04-21',6,'First time',NULL),('luther.bowser@seeley.net','VICKY','Male','1934-03-17',7,'First time',NULL),('m.gotter@tyasa.com','LYLA','Female','1963-09-24',6,'Have some experience',NULL),('m.pritchett@milkwoodproductions.com','GEMMA','Female','1939-10-16',9,'Have some experience',NULL),('m.shaver@nster.gov','DEREK','Female','1932-12-25',3,'Have some experience',NULL),('mercedes@dayrep.net','FELICIA','Female','1964-11-20',5,'First time',NULL),('migdalia.hurd@dayrep.net','WERNER','Female','1973-03-18',11,'Have some experience',NULL),('murray.dykes@gmail.com','ORLANDO','Male','1972-09-29',1,'Have some experience',NULL),('n.stringer@gmail.com','JOHNIE','Female','1982-11-16',10,'First time',NULL),('nicolas.varriano@gmail.com','CLARK','Male','1950-08-03',1,'Have some experience',NULL),('nsutton292@hotmail.com','REINALDO','Female','1975-08-06',3,'Have some experience',NULL),('o.husser@sizzling.com','NOVA','Male','1987-08-11',6,'Have some experience',NULL),('p.vann@yahoo.com','QUINCY','Female','1990-08-20',8,'First time',NULL),('pkonopacki110@outlook.com','JOSEF','Female','1945-02-16',8,'First time',NULL),('pmills88@milkwoodproductions.com','NORA','Male','1912-11-03',6,'Experienced',NULL),('rachelle.powers@gmail.com','SUSANN','Female','1993-02-04',6,'Experienced',NULL),('rich.ezell@milkwoodproductions.com','RENE','Male','1935-12-01',2,'First time',NULL),('rick.ruta@education.gov','MALIA','Male','1979-03-01',4,'Have some experience',NULL),('rlangston88@hotmail.com','MERCEDES','Male','1996-08-10',5,'First time',NULL),('roderick.barry@gmail.com','JOANN','Male','1942-12-24',7,'Have some experience',NULL),('rodolfo.rubin@hotmail.com','BENITA','Male','1969-06-01',11,'Have some experience',NULL),('stevie.bullard@outlook.com','KIRI','Male','1950-12-22',9,'First time',NULL),('tanesha.montoya@nord.net','ROGELIO','Female','1972-09-22',5,'Experienced',NULL),('tlassiter478@gmail.com','TIMMY','Male','1940-01-24',5,'Have some experience',NULL),('toby.hammond@education.gov','DIEGO','Female','1936-09-06',11,'First time',NULL),('vernon.lim@ew.net','SHANNON','Male','1952-11-18',9,'Have some experience',NULL),('vicenta@gmail.com','RUBEN','Female','1981-09-26',8,'Have some experience',NULL),('w.hutson@gmail.com','HARLAND','Female','1942-04-07',8,'Have some experience',NULL),('w.small@gmail.com','KENNITH','Male','1946-05-05',3,'First time',NULL),('winona.wall@gmail.com','SADIE','Female','1945-10-05',2,'Have some experience',NULL);
/*!40000 ALTER TABLE `runner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `Email` varchar(50) NOT NULL,
                        `Password` varchar(50) NOT NULL,
                        `RoleId` int NOT NULL,
                        PRIMARY KEY (`Email`),
                        KEY `FK_User_Role` (`RoleId`),
                        CONSTRAINT `FK_User_Role` FOREIGN KEY (`RoleId`) REFERENCES `role` (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('a.beaulieu@hotmail.com','RZ#7$2sK',2),('a.copeland@hotmail.com','IX76175j',2),('a.mccollum@hotmail.com','gGfSZaz7',2),('a.sama@keef.com','66vN@kDw',2),('a.sellers@gmail.com','kJngErtj',1),('a.shields@outlook.com','1xI7LyqA',1),('a.skelton@gmail.com','FVq7eDu8',1),('a.smith@keef.com','PrKi5ZBv',1),('a.sneed@gmail.com','ii#CvSyV',1),('a.snell@hotmail.com','M!DApkml',1),('abattle56@hotmail.com','vGbKQysb',2),('acombs235@hr.gov','2s!6Tj57',2),('alberto.lancaster@milkwoodproductions.com','zTyuU1sE',2),('alittle270@gmail.com','Pd3vW84r',2),('alyce@ew.net','yOZO!V#5',2),('arran.clements@gmail.com','o4Wvtdwy',2),('audry.fair@ew.net','CHYfs2lR',2),('austin.cash@ew.net','8h7qHHgg',2),('b.patak@gmail.com','KZPGJwj6',2),('becky.lipke@milkwoodproductions.com','BIWpwFaS',2),('benita@education.gov','@KdSeMWg',2),('bflint398@gmail.com','HNb1ZfbI',2),('bgentry398@seeley.net','ibKF2bq#',2),('bkerr195@sizzling.com','lGVrvS!K',2),('brent.puckett@hotmail.com','NixV0GVc',2),('c.borgman@outlook.com','OPuDrw9O',2),('c.bragg@gmail.com','OmVI2YOE',2),('c.mugnolo@outlook.com','p5H2o@h6',2),('c.pinilla@dayrep.net','hYpU2w8G',2),('cherise@gmail.com','9m!448#g',2),('csherrill145@gmail.com','Ej3ydhMc',2),('d.mullen@gmail.com','1CcCFZ5U',2),('d.pope@hotmail.com','5AsS7REB',2),('dchristian488@gmail.com','B0XttMcW',2),('dianne.helton@ew.net','7XbvvZud',2),('dnix171@seeley.net','YFUwZMq#',2),('dominik@gmail.com','l#qFu#xv',2),('dorothea.similton@gmail.com','aZuLptLI',2),('dtabor280@gmail.com','qWZV08c$',2),('e.givens@gmail.com','nudl7I!5',2),('e.michaels@hotmail.com','PydicEo9',2),('e.smullen@gmail.com','0jFw!eu2',2),('edwardo.yum@gmail.com','WF@VFpmG',2),('ester.huntley@outlook.com','scBTgwZL',2),('evalyn.christian@hr.gov','9ApEdKdo',2),('fbruce444@gmail.com','yw$HahDi',2),('g.humphreys@gmail.com','l7FLpGzL',2),('g.hurt@hotmail.com','vDw2CWzp',2),('gperkins90@hotmail.com','adO38G$I',2),('gregg@gmail.com','fTcya2HR',2),('guy@nord.net','CtOzRwtD',2),('h.arnett@dayrep.net','bvq02A9i',2),('h.roth@nord.net','N8cS1zY5',2),('h.thurston@hotmail.com','qWOvG6TJ',2),('i.thacker@gmail.com','L$J7oyxv',2),('irene.hager@ramoz.com','7XbvvZud',2),('iris@seeley.net','BDqAGuaR',2),('ivan@nord.net','kwHCKJLX',2),('j.herman@keef.com','PWqib29a',2),('jason.putnam@seeley.net','gYNiPBiZ',2),('jbusby243@gmail.com','pTN5gvPr',2),('jessie.whittaker@gmail.com','JeHgbD@1',2),('johnny.antila@gmail.com','l7gkjE6#',2),('jschell52@gmail.com','Xw9OeeKZ',2),('jtrotter32@finance.gov','FhM0iUfK',2),('judith.bravo@gmail.com','1GFI@Xug',2),('jules.parker@gmail.com','LQm0Db#m',2),('k.colaizzo@sizzling.com','xll9uZAk',2),('k.corcoran@education.gov','sWlX1L!z',2),('k.pawlowicz@hotmail.com','hQankB0k',2),('k.sutton@gmail.com','sNgtLv7b',2),('kelsey.rivers@gmail.com','HMgECMWU',2),('l.beasley@hotmail.com','fa5w!#$U',2),('laurie.baggett@gmail.com','baZNHnLr',2),('lbenimadho458@gmail.com','n5KhkHpw',2),('liam.chin@gmail.com','tx@t71Qj',2),('lizzie.whitt@sizzling.com','CPgaZBgb',2),('luther.bowser@seeley.net','4jmwa6yH',2),('m.gotter@tyasa.com','a!FxkIkZ',2),('m.pritchett@milkwoodproductions.com','!EYdiDL8',2),('m.shaver@nster.gov','YayOEcvm',2),('mercedes@dayrep.net','6w1GMUFt',2),('migdalia.hurd@dayrep.net','VJn@$e2L',2),('murray.dykes@gmail.com','kW9rcjNX',2),('n.stringer@gmail.com','lZXF!0VX',2),('nicolas.varriano@gmail.com','k@8GIg#9',2),('nsutton292@hotmail.com','S3#$veIk',2),('o.husser@sizzling.com','hH#l4IIP',2),('p.vann@yahoo.com','QimhGU$d',2),('pkonopacki110@outlook.com','H0euIpYf',2),('pmills88@milkwoodproductions.com','lcWv#6IP',2),('rachelle.powers@gmail.com','QcJ6beMj',2),('rich.ezell@milkwoodproductions.com','!#C3zItn',2),('rick.ruta@education.gov','9tFBWRmy',2),('rlangston88@hotmail.com','sFpGd60D',2),('roderick.barry@gmail.com','@gnYMnkM',2),('rodolfo.rubin@hotmail.com','ULPA!03I',2),('stevie.bullard@outlook.com','zY@sNmN4',2),('tanesha.montoya@nord.net','W2$0#1A2',2),('tlassiter478@gmail.com','#2CQddoy',2),('toby.hammond@education.gov','X$9rJpYU',2),('vernon.lim@ew.net','Y8OY9ZXw',2),('vicenta@gmail.com','u0ur!diH',2),('w.hutson@gmail.com','Gpzj6J0r',2),('w.small@gmail.com','!79fwZYi',2),('winona.wall@gmail.com','HrKvRwBx',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volunteer`
--

DROP TABLE IF EXISTS `volunteer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `volunteer` (
                             `Id` int NOT NULL AUTO_INCREMENT,
                             `Name` varchar(50) NOT NULL,
                             `CityId` int NOT NULL,
                             `DateOfBirth` date NOT NULL,
                             `Gender` enum('Male','Female') NOT NULL,
                             PRIMARY KEY (`Id`),
                               KEY `CityId` (`CityId`),
                               CONSTRAINT `volunteer_ibfk_1` FOREIGN KEY (`CityId`) REFERENCES `city` (`CityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volunteer`
--

LOCK TABLES `volunteer` WRITE;
/*!40000 ALTER TABLE `volunteer` DISABLE KEYS */;
/*!40000 ALTER TABLE `volunteer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-18 11:05:36
